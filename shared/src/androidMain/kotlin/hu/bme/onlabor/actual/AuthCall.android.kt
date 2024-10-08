package hu.bme.onlabor.expect

import okhttp3.OkHttpClient
import okhttp3.Request

actual fun makeKtorCall(): String {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://10.0.2.2:8090/Hello").build()

    val response = client.newCall(request).execute()
    return response.body?.string() ?: "Okay"
}