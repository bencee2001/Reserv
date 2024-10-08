package hu.bme.onlabor.expect

import kotlinx.browser.window

actual fun makeKtorCall(): String {
    val url = "http://10.0.2.2:8090/Hello"
    val response = window.fetch(url)
    return TODO()
}