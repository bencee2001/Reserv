package hu.bme.onlabor.commondomain.countries

object CountryDao{

    private val countries = CountriesProvider.getCountries()
    private val countryById = countries.associateBy { it.id }

    fun getByIdentifier(id: String): Country {
        return countryById[id] ?: throw IllegalArgumentException("No such Country by Id: $id")
    }


}