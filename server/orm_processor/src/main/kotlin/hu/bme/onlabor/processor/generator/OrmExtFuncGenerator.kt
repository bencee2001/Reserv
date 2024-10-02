package hu.bme.onlabor.processor.generator

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import hu.bme.onlabor.annotation.interfaces.AbstractDao
import hu.bme.onlabor.annotation.interfaces.AttributeMapper
import hu.bme.onlabor.processor.dto.DataEntityPropDto

object OrmExtFuncGenerator {

    const val ORM_EXT_PACKAGE = "hu.bme.onlabor.gen"
    const val ORM_EXT_FILENAME = "GenExt"

    fun generateDataEntityExtensionFunction(
        genMapperKsClass: KSClassDeclaration,
        dataKsClass: KSClassDeclaration,
        entityKsClass: KSClassDeclaration,
        dataEntityPropNamePairs: MutableList<DataEntityPropDto>,
    ): String {

        val targetClassPackage = genMapperKsClass.packageName.asString()
        val targetClassName = genMapperKsClass.toString()

        val dataClassName = dataKsClass.simpleName.getShortName()
        val dataPackage = dataKsClass.packageName.asString()

        val entityClassName = entityKsClass.simpleName.getShortName()
        val entityPackage = entityKsClass.packageName.asString()

        val mapperPaths =
            dataEntityPropNamePairs.filter { it.mapperPath != null }.map { it.mapperPath!! }

        val imports = listOf<String>(
            "$targetClassPackage.$targetClassName",
            "$dataPackage.$dataClassName",
            "$entityPackage.$entityClassName",
            "org.jetbrains.exposed.sql.ResultRow",
            *mapperPaths.toTypedArray(),
            "org.jetbrains.exposed.sql.insert"
        )

        val rowToDataMapper = """
package $ORM_EXT_PACKAGE
        
${generateImports(imports)}

${generateRowToData(targetClassName, dataClassName, dataEntityPropNamePairs, entityClassName)}

${generateInsertStatement(targetClassName, dataClassName, entityClassName, dataEntityPropNamePairs)}
        
""".trimIndent()
        return rowToDataMapper
    }

    private fun generateImports(imports: List<String>) =
        imports.joinToString(separator = "\n") {
            "import $it"
        }

    private fun generateRowToData(
        targetClassName: String,
        dataClassName: String,
        dataEntityPropNamePairs: MutableList<DataEntityPropDto>,
        entityClassName: String
    ): String {
        return """
fun $targetClassName.resultRowTo$dataClassName(row: ResultRow): $dataClassName{
    return $dataClassName(
    ${
dataEntityPropNamePairs
    .joinToString(separator = "\n") { (dataName, entityName, mapperPath) ->
        if (mapperPath == null)
            "\t\t$dataName = row[$entityClassName.$entityName],"
        else {
            val splitPath = mapperPath.split(".")
            val mapperClass = splitPath.takeLast(1).first()
            "\t\t$dataName = $mapperClass.${AttributeMapper.TO_DATA_FUNC}(row[$entityClassName.$entityName]),"
        }
    }
    }
    )
}
        """.trimIndent()
    }

    private fun generateInsertStatement(
        targetClassName: String,
        dataClassName: String,
        entityClassName: String,
        dataEntityPropNamePairs: MutableList<DataEntityPropDto>
    ): String {
        require(
            dataEntityPropNamePairs.filter { it.entityName == "id" }.size == 1
        ) {"$entityClassName primary key's name need to be \"id\""}
        val insertObj = "entity"
        val paramObj = "data"
        return """
fun $targetClassName.insertStatement($paramObj: $dataClassName) = $entityClassName.insert { $insertObj ->
        ${
            dataEntityPropNamePairs
                .filter { it.entityName != "id" }
                .joinToString(separator = "\n") { (dataName, entityName, mapperPath) -> 
                    if(mapperPath == null)
                        "\t$insertObj[$entityName] = $paramObj.$dataName"
                    else {
                        val splitPath = mapperPath.split(".")
                        val mapperClass = splitPath.takeLast(1).first()
                        "\t$insertObj[$entityName] = $mapperClass.${AttributeMapper.TO_ENTITY_FUNC}($paramObj.$dataName)"
                    }
            }
        }
}
    
        """.trimIndent()
    }


}