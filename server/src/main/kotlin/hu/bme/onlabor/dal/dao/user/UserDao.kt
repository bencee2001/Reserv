package hu.bme.onlabor.dal.dao.user

import hu.bme.onlabor.annotation.interfaces.AbstractDao
import hu.bme.onlabor.commondomain.model.UserLevel
import hu.bme.onlabor.dal.model.user.User


interface UserDao: AbstractDao<User> {
    suspend fun findByUsername(username: String): User?
    suspend fun findByEmail(email: String): User?
    suspend fun getUserLevelForUsername(username: String): UserLevel
}