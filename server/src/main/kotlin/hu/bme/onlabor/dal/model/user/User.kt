package hu.bme.onlabor.dal.model.user

import hu.bme.onlabor.annotation.MapTo
import hu.bme.onlabor.annotation.MapperDataSide
import hu.bme.onlabor.annotation.MapperEntitySide
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
@MapperDataSide(
    "UserDaoImpl",
    "hu.bme.onlabor.dal.model.user"
)
data class User(
    @MapTo("id")
    var userId: Int? = null,
    var name: String = "T",
    var email: String = "T",
    var username: String = "T",
    var password: String = "T",
    var userLevel: UserLevel = UserLevel.NORMAL,
    var role: Role = Role.SIMPLE_USER,
    var profilePictureUrl: String = "T"
)

@MapperEntitySide(
    "UserDaoImpl",
    "hu.bme.onlabor.dal.model.user"
)
object Users : Table() {
    val id = integer(User::userId.name).autoIncrement()
    val name = varchar(User::name.name, 100)
    val email = varchar(User::email.name, 100)
    val username = varchar(User::username.name, 100)
    val password = varchar(User::password.name, 255)
    val userLevel = enumerationByName<UserLevel>(User::userLevel.name, 50)
    val role = enumerationByName<Role>(User::role.name, 50)
    val profilePictureUrl = varchar(User::profilePictureUrl.name, 255)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}