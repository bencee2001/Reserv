package hu.bme.onlabor.util

import hu.bme.onlabor.commondomain.model.Role

object AuthUtil {

    fun isNotSimpleUser(userRole: Role): Boolean {
        return userRole == Role.ADMIN || userRole == Role.OWNER
    }
}