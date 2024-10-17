package hu.bme.onlabor.dal.model.accommodation

import hu.bme.onlabor.annotation.interfaces.AttributeMapper
import hu.bme.onlabor.common.countries.Country
import hu.bme.onlabor.common.countries.CountryDao


object CountryAttributeMapper: AttributeMapper<Country, String> {

    const val PATH = "hu.bme.onlabor.dal.model.accommodation.CountryAttributeMapper"

    override fun mapToEntityAttribute(dataAttr: Country): String {
        return dataAttr.id
    }

    override fun mapToDataAttribute(entityAttr: String): Country {
        return CountryDao.getByIdentifier(entityAttr)
    }
}