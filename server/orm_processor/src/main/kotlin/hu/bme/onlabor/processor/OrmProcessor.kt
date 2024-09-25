package hu.bme.onlabor.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import hu.bme.onlabor.annotation.GenMapper

class OrmProcessor(
    private val codeGenerator: CodeGenerator
): SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver
            .getSymbolsWithAnnotation(GenMapper::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .forEach {
                codeGenerator.createNewFile(Dependencies(false),"com.example","HelloWorld${it.simpleName.getShortName()}")
            }
        return emptyList()
    }
}