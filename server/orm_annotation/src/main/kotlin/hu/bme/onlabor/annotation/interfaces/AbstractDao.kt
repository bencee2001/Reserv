package hu.bme.onlabor.annotation.interfaces

interface AbstractDao<T>{
    suspend fun getAll(): List<T>
    suspend fun save(data: T): T?
}