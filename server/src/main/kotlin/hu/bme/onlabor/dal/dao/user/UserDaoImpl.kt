package hu.bme.onlabor.dal.dao.user

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.commondomain.model.UserLevel
import hu.bme.onlabor.dal.dbQuery
import hu.bme.onlabor.dal.model.user.User
import hu.bme.onlabor.dal.model.user.Users
import hu.bme.onlabor.gen.resultRowToUser
import hu.bme.onlabor.gen.insertStatement
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
        val insertStatement = insertStatement(data)
        insertStatement.resultedValues?.singleOrNull()?.let { resultRowToUser(it) }
    }

    override suspend fun findByUsername(username: String): User? = dbQuery {
        val userEntity = Users
            .selectAll()
            .where { Users.username eq username }
            .singleOrNull()
        userEntity?.let { resultRowToUser(it) }
    }

    override suspend fun findByEmail(email: String): User? = dbQuery {
        val userEntity = Users
            .selectAll()
            .where { Users.email eq email }
            .singleOrNull()
        userEntity?.let { resultRowToUser(it) }
    }

    override suspend fun getUserLevelForUsername(username: String): UserLevel = dbQuery {
        Users
            .selectAll()
            .where { Users.username eq username }
            .map { it[Users.userLevel] }
            .single()
    }
}