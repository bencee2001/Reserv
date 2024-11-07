package hu.bme.onlabor.commondomain.network.response

data class BasicResponse<T>(
    val status: Int,
    val bodyMessage: T
)