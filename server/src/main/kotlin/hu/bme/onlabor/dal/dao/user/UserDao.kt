package hu.bme.onlabor.dal.dao.user

import hu.bme.onlabor.dal.model.user.User

interface UserDao {
    suspend fun getUsers(): List<User>
    suspend fun save(user: User): User?
}