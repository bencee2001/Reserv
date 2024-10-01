package hu.bme.onlabor.annotation.annotations


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class MapperEntitySide(val daoClassName: String)
