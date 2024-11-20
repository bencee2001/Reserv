package hu.bme.onlabor.security

enum class AuthType(val _name: String) {
    AUTH_FORM("auth-form"),
    AUTH_JWT_ALL("auth-jwt-all"),
    AUTH_JWT_OWNER("auth-jwt_own"),
}