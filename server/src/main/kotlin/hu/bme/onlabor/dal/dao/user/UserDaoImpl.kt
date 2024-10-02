package hu.bme.onlabor.dal.dao.user

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.dal.dbQuery
import hu.bme.onlabor.dal.model.user.User
import hu.bme.onlabor.dal.model.user.Users
import hu.bme.onlabor.gen.resultRowToUser
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

@GenMapper
class UserDaoImpl: UserDao {

    companion object{
        const val NAME = "UserDaoImpl"
    }

    override suspend fun getAll(): List<User> = dbQuery {
        Users.selectAll().map { resultRowToUser(it) }
    }

    override suspend fun save(data: User): User? = dbQuery{
        val insertStatement = Users.insert {
            it[name] = data.name
            it[email] = data.email
            it[username] = data.username
            it[password] = data.password
            it[userLevel] = data.userLevel
            it[role] = data.role
            it[profilePictureUrl] = data.profilePictureUrl
        }
        insertStatement.resultedValues?.singleOrNull()?.let { resultRowToUser(it) }
    }
}