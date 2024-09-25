package hu.bme.onlabor.dal.model.user

import hu.bme.onlabor.annotation.GenMapper
import hu.bme.onlabor.dal.dbconnection.util.DataEntityMapper
import hu.bme.onlabor.dal.dbconnection.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

@GenMapper
class UserDaoImpl: UserDao {

    private val mapper = DataEntityMapper(User::class.java, Users::class.java, Users)

    private fun resultRowToUser(row: ResultRow): User{
        mapper.fromRowToData(row)
        return User(
            userId = row[Users.id],
            name = row[Users.name],
            email = row[Users.email],
            username = row[Users.username],
            password = row[Users.password],
            userLevel = row[Users.userLevel],
            role = row[Users.role],
            profilePictureUrl = row[Users.profilePictureUrl]
        )
    }

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