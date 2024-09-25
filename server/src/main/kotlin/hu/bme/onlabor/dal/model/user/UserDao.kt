package hu.bme.onlabor.dal.model.user

interface UserDao {
    suspend fun getUsers(): List<User>
    suspend fun save(user: User):User?
}