package hu.bme.onlabor.dal.dao.user

import hu.bme.onlabor.annotation.GenMapper
import hu.bme.onlabor.dal.xdbconnection.util.DataEntityMapper
import hu.bme.onlabor.dal.xdbconnection.dbQuery
import hu.bme.onlabor.dal.model.user.User
import hu.bme.onlabor.dal.model.user.Users
import hu.bme.onlabor.ext.resultRowToUser
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

@GenMapper("hu.bme.onlabor.dal.dao.user")
class UserDaoImpl: UserDao {

    private val mapper = DataEntityMapper(User::class.java, Users::class.java, Users)

    override suspend fun getUsers(): List<User> = dbQuery {
        Users.selectAll().map { resultRowToUser(it) }
    }

    override suspend fun save(user: User): User? = dbQuery{
        val insertStatement = Users.insert {
            it[name] = user.name
            it[email] = user.email
            it[username] = user.username
            it[password] = user.password
            it[userLevel] = user.userLevel
            it[role] = user.role
            it[profilePictureUrl] = user.profilePictureUrl
        }
        insertStatement.resultedValues?.singleOrNull()?.let { resultRowToUser(it) }
    }
}