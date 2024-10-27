package hu.bme.onlabor.response

data class BasicResponse<T>(
    val status: Int,
    val bodyMessage: T
)