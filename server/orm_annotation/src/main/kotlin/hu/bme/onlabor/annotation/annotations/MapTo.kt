package hu.bme.onlabor.annotation.annotations

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class MapTo(val targetName: String)