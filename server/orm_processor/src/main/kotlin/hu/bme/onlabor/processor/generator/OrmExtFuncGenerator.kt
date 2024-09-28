package hu.bme.onlabor.processor.generator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.symbol.KSClassDeclaration
import hu.bme.onlabor.annotation.GenMapper
import hu.bme.onlabor.annotation.MapperDataSide
import hu.bme.onlabor.annotation.MapperEntitySide

object OrmExtFuncGenerator {

    const val ORM_EXT_PACKAGE = "hu.bme.onlabor.gen"
    const val ORM_EXT_FILENAME = "GenExt"

    @OptIn(KspExperimental::class)
    fun generateDataEntityMapperExtensionFunction(
        it: KSClassDeclaration,
        dataKsClass: KSClassDeclaration,
        entityKsClass: KSClassDeclaration,
        className: String,
        dataToEntityPropNamePairs: MutableList<Pair<String, String>>
    ): String {
        val genMapperObj = it.getAnnotationsByType(GenMapper::class).first()
        val classPackage = genMapperObj.filePackage

        val dataClassName = dataKsClass.simpleName.getShortName()
        val dataPackage =
            dataKsClass.getAnnotationsByType(MapperDataSide::class).first().filePackage

        val entityClassName = entityKsClass.simpleName.getShortName()
        val entityPackage =
            entityKsClass.getAnnotationsByType(MapperEntitySide::class).first().filePackage

        val rowToDataMapper = """
                    package hu.bme.onlabor.ext
                    
                    import $classPackage.$className
                    import $dataPackage.$dataClassName
                    import $entityPackage.$entityClassName
                    import org.jetbrains.exposed.sql.ResultRow
                    
                    fun $className.resultRowTo$dataClassName(row: ResultRow): $dataClassName{
                        return $dataClassName(
                            ${
                                dataToEntityPropNamePairs
                                    .joinToString(separator = "\n") { (dataName, entityName) ->
                                        "$dataName = row[$entityClassName.$entityName],"
                                    }
                            }
                        )
                    }
                """.trim()
        return rowToDataMapper
    }
}