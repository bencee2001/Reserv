package hu.bme.onlabor.common

import org.kotlincrypto.hash.md.MD5

object Hash {
    @OptIn(ExperimentalStdlibApi::class)
    fun crypt(data: String): String {
        val digest = MD5().digest(data.encodeToByteArray())
        return digest.toHexString()
    }
}