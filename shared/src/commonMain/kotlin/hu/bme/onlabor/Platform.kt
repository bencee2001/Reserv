package hu.bme.onlabor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform