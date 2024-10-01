package hu.bme.onlabor.annotation.annotations

import hu.bme.onlabor.annotation.interfaces.AttributeMapper
import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class MapWith(val mapperPath: String)
