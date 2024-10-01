package hu.bme.onlabor.processor.generator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.symbol.KSClassDeclaration
import hu.bme.onlabor.annotation.interfaces.AttributeMapper
import hu.bme.onlabor.processor.dto.DataEntityPropDto

object OrmExtFuncGenerator {

    const val ORM_EXT_PACKAGE = "hu.bme.onlabor.gen"
    const val ORM_EXT_FILENAME = "GenExt"

    @OptIn(KspExperimental::class)
    fun generateDataEntityMapperExtensionFunction(
        genMapperKsClass: KSClassDeclaration,
        dataKsClass: KSClassDeclaration,
        entityKsClass: KSClassDeclaration,
        dataEntityPropNamePairs: MutableList<DataEntityPropDto>
    ): String {
        val targetClassPackage = genMapperKsClass.packageName.asString()
        val targetClassName = genMapperKsClass.toString()

        val dataClassName = dataKsClass.simpleName.getShortName()
        val dataPackage = dataKsClass.packageName.asString()

        val entityClassName = entityKsClass.simpleName.getShortName()
        val entityPackage = entityKsClass.packageName.asString()

        val mapperPaths = dataEntityPropNamePairs.filter { it.mapperPath != null }.map { it.mapperPath }

        val rowToDataMapper = """
                    package $ORM_EXT_PACKAGE
                    
                    import $targetClassPackage.$targetClassName
                    import $dataPackage.$dataClassName
                    import $entityPackage.$entityClassName
                    import org.jetbrains.exposed.sql.ResultRow
                    ${
                        mapperPaths.joinToString(separator = "\n"){
                            "import $it"
                        }
                    }
                    
                    fun $targetClassName.resultRowTo$dataClassName(row: ResultRow): $dataClassName{
                        return $dataClassName(
                            ${
                                dataEntityPropNamePairs
                                    .joinToString(separator = "\n") { (dataName, entityName, mapperPath) ->
                                        if (mapperPath == null)
                                            "$dataName = row[$entityClassName.$entityName],"
                                        else {
                                            val splitPath = mapperPath.split(".")
                                            val mapperClass = splitPath.takeLast(1).first()
                                            "$dataName = $mapperClass.${AttributeMapper.TO_DATA_FUNC}(row[$entityClassName.$entityName]),"
                                        }
                                    }
                            }
                        )
                    }
                """.trim()
        return rowToDataMapper
    }
}