package hu.bme.onlabor.plugin

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.application.Application
import io.ktor.server.application.install

fun Application.configureSwagger(){
    install(SwaggerUI){
        swagger {
            swaggerUrl = "swagger-ui"
            forwardRoot = true
        }
        info {
            title = "Reserv Swagger"
            version = "1.0"
            description = "Swagger Reserv"
        }
    }
}