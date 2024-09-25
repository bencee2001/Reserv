package hu.bme.onlabor.dal.dbconnection.util

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class MapTo(val targetName: String)