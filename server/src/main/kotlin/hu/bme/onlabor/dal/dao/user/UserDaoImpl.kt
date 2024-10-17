package hu.bme.onlabor.dal.dao.user

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.common.Hash
import hu.bme.onlabor.dal.dbQuery
import hu.bme.onlabor.dal.model.user.User
import hu.bme.onlabor.dal.model.user.Users
import hu.bme.onlabor.gen.insertStatement
import hu.bme.onlabor.gen.resultRowToUser
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
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
        val cryptData = data.copy(password = Hash.crypt(data.password))
        val insertStatement = insertStatement(cryptData)
        insertStatement.resultedValues?.singleOrNull()?.let { resultRowToUser(it) }
    }

    override suspend fun findByUsername(username: String): User? = dbQuery {
        val userEntity = Users
            .selectAll()
            .where { Users.username eq username }
            .singleOrNull()
        userEntity?.let { resultRowToUser(it) }
    }
}