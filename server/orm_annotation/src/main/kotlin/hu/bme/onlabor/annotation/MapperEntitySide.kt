package hu.bme.onlabor.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class MapperEntitySide(val daoClassName: String, val filePackage: String)
