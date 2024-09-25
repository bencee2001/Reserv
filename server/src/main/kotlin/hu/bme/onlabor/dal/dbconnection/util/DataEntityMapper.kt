package hu.bme.onlabor.dal.dbconnection.util

import hu.bme.onlabor.dal.model.user.Users
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.InsertStatement
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

class DataEntityMapper<Data: Any, Entity: Table>(
    val dataClass: Class<Data>,
    val entityClass: Class<Entity>,
    val table: Entity
)
{
    val dataEntityMap: Map<KProperty1<Data, *>, KProperty1<Entity,*>>
    val entityDataMap: Map<KProperty1<Entity, *>, KProperty1<Data,*>>

    init {
        val dataProps = dataClass.kotlin.memberProperties
        val entityProps = entityClass.kotlin.memberProperties
        val propMap = mutableMapOf<KProperty1<Data, *>, KProperty1<Entity,*>>()
        for (dataProp in dataProps){
            val dataPropName =
                if(dataProp.findAnnotation<MapTo>() == null)
                    dataProp.name
                else
                    dataProp.findAnnotation<MapTo>()!!.targetName
            val entityProp = entityProps.filter { it.name == dataPropName }
            if(entityProp.size > 1) throw IllegalStateException("Can't be more then 1 matching names in data and entity classes.")
            if(entityProp.isEmpty()) throw IllegalStateException("There's need to be at least 1 data property that matches to 1  entity property.")
            propMap[dataProp] = entityProp.first()
        }
        dataEntityMap = propMap
        entityDataMap = propMap.map { (key, value) -> value to key }.toMap()
    }

    fun toData(entity: Entity): Data{
        val dataProps = dataClass.kotlin.memberProperties
        val dataConstructor = dataClass.kotlin.primaryConstructor ?: throw IllegalStateException("No Primary Constructor")
        val dataInstance = dataConstructor.callBy(emptyMap())
        for(dataProp in dataProps){
            if(dataProp is KMutableProperty1<*,*>){
                val entityProp = dataEntityMap[dataProp] ?: throw IllegalStateException("No Entity Property for Data Property ${dataProp.name}")
                val entityPropValue = entityProp.get(entity)
                dataProp.setter.call(dataInstance, entityPropValue)
            }
        }
        return dataInstance
    }

    fun toEntity(data: Data): Entity{
        val entityProps = entityClass.kotlin.memberProperties
        val entityConstructor = entityClass.kotlin.primaryConstructor ?: throw IllegalStateException("No Primary Constructor")
        val entityInstance = entityConstructor.callBy(emptyMap())
        for(entityProp in entityProps){
            if(entityProp is KMutableProperty1<*,*>){
                val dataProp = entityDataMap[entityProp] ?: throw IllegalStateException("No Entity Property for Data Property ${entityProp.name}")
                val dataPropValue = dataProp.get(data)
                entityProp.setter.call(entityInstance, dataPropValue)
            }
        }
        return entityInstance
    }

    fun fromRowToData(row: ResultRow): Data{
        val dataProps = dataClass.kotlin.memberProperties
        val dataConstructor = dataClass.kotlin.primaryConstructor ?: throw IllegalStateException("No Primary Constructor")
        val dataInstance = dataConstructor.callBy(emptyMap())
        for(dataProp in dataProps){
            dataProp as KMutableProperty1<*,*>
            val entityProp = dataEntityMap[dataProp] ?: throw IllegalStateException("No Entity Property for Data Property ${dataProp.name}")
            val realEntityProp = entityProp.getter.call(table) as Expression<*>
            val entityPropValue = row[realEntityProp]
            dataProp.setter.call(dataInstance, entityPropValue)
        }
        return dataInstance
    }

    fun fromDataToInsertStatement(data: Data): InsertStatement<Number>{
       return TODO() 
    }

}