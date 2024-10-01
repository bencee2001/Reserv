package hu.bme.onlabor.annotation.interfaces

interface AttributeMapper<DataType, EntityType> {

    fun mapToEntityAttribute(dataAttr: DataType): EntityType
    fun mapToDataAttribute(entityAttr: EntityType): DataType

    companion object{
        const val TO_ENTITY_FUNC = "mapToEntityAttribute"
        const val TO_DATA_FUNC = "mapToDataAttribute"
    }
}