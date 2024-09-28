package hu.bme.onlabor.annotation

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class MapTo(val targetName: String)