package hu.bme.onlabor.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import hu.bme.onlabor.annotation.GenMapper
import hu.bme.onlabor.annotation.MapTo
import hu.bme.onlabor.annotation.MapperDataSide
import hu.bme.onlabor.annotation.MapperEntitySide
import hu.bme.onlabor.processor.generator.OrmExtFuncGenerator
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

class OrmProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
): SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("Process started.")

        val genMappers = resolver
            .getSymbolsWithAnnotation(GenMapper::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()

        logger.info("Found GenMappers: ${genMappers.map { it.simpleName.getShortName() }.toList()}")

        val dataClasses = resolver
            .getSymbolsWithAnnotation(MapperDataSide::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()

        logger.info("Found MapperDataSide: ${dataClasses.map { it.simpleName.getShortName() }.toList()}")


        val entityClasses = resolver
            .getSymbolsWithAnnotation(MapperEntitySide::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()

        logger.info("Found MapperEntitySide: ${entityClasses.map { it.simpleName.getShortName() }.toList()}")

        genMappers.forEach {
            val className =  it.simpleName.getShortName()
            logger.info("$className generation started.")
            val (dataKsClass, entityKsClass) = getDataAndEntityClassForGenMapper(dataClasses, className, entityClasses)

            val dataToEntityPropNamePairs = createDataEntityPropertyPairs(dataKsClass, entityKsClass, className)

            val file = codeGenerator.createNewFile(
                Dependencies(false),
                OrmExtFuncGenerator.ORM_EXT_PACKAGE,
                "${it.simpleName.getShortName()}${OrmExtFuncGenerator.ORM_EXT_FILENAME}")
            OutputStreamWriter(file, StandardCharsets.UTF_8).use { writer ->
                writer.write(
                    generate(
                        it,
                        dataKsClass,
                        entityKsClass,
                        className,
                        dataToEntityPropNamePairs
                    )
                )
            }
            logger.info("$className generation ended.")
        }
        logger.info("Process ended.")
        return emptyList()
    }

    private fun generate(
        it: KSClassDeclaration,
        dataKsClass: KSClassDeclaration,
        entityKsClass: KSClassDeclaration,
        className: String,
        dataToEntityPropNamePairs: MutableList<Pair<String, String>>
    ): String{
        val list = mutableListOf<String>()
        logger.info("$className: DataEntityMapperExtensionFunction started.")
        list.add(OrmExtFuncGenerator.generateDataEntityMapperExtensionFunction(it, dataKsClass, entityKsClass, className, dataToEntityPropNamePairs))
        logger.info("$className: DataEntityMapperExtensionFunction ended.")
        return list.joinToString(separator = "\n") {
            it
        }
    }

    @OptIn(KspExperimental::class)
    private fun createDataEntityPropertyPairs(
        dataKsClass: KSClassDeclaration,
        entityKsClass: KSClassDeclaration,
        className: String
    ): MutableList<Pair<String, String>> {
        val dataProps = dataKsClass.getAllProperties()
        val entityProps = entityKsClass.getAllProperties()

        val dataToEntityPropNamePairs = mutableListOf<Pair<String, String>>()

        dataProps.forEach { dataProp ->
            val mapToAnnotation = dataProp.getAnnotationsByType(MapTo::class).firstOrNull()
            val dataPropNameTarget =
                mapToAnnotation?.targetName ?: dataProp.simpleName.getShortName()
            val dataPropNameReal = dataProp.simpleName.getShortName()
            val entityPropName = entityProps
                .map { entityProp -> entityProp.simpleName.getShortName() }
                .filter { entityPropName -> entityPropName == dataPropNameTarget }
                .firstOrNull() ?: throw IllegalStateException("In $className can't map $dataPropNameReal")
            dataToEntityPropNamePairs.add(dataPropNameReal to entityPropName)
        }
        return dataToEntityPropNamePairs
    }

    @OptIn(KspExperimental::class)
    private fun getDataAndEntityClassForGenMapper(
        dataClasses: Sequence<KSClassDeclaration>,
        className: String,
        entityClasses: Sequence<KSClassDeclaration>
    ): Pair<KSClassDeclaration, KSClassDeclaration> {
        val dataKsClass = dataClasses
            .filter { asd ->
                asd.getAnnotationsByType(MapperDataSide::class).first().daoClassName == className
            }
            .firstOrNull()
            ?: throw IllegalStateException("No MapperDataSide annotation for @GenMapper $className")
        val entityKsClass = entityClasses
            .filter { asd ->
                asd.getAnnotationsByType(MapperEntitySide::class).first().daoClassName == className
            }
            .firstOrNull()
            ?: throw IllegalStateException("No MapperEntitySide annotation for @MapperDataSide $className")
        return Pair(dataKsClass, entityKsClass)
    }
}