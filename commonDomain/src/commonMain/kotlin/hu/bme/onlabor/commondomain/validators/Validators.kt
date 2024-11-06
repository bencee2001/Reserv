package hu.bme.onlabor.commondomain.validators


object Validators {
    /**
     * Email address pattern, same as [android.util.Patterns.EMAIL_ADDRESS]
     */
    val emailRegex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
}

fun String.isValidEmail(): Boolean {
    return this.matches(Validators.emailRegex)
}