package ru.zoo.presentation.tables.specieses.createEdit

import ru.zoo.data.models.Animal
import ru.zoo.data.models.AnimalType
import ru.zoo.data.models.ClimateZone
import ru.zoo.data.models.Species

class SpeciesesEditRepository {
    companion object{
        var speciesForSend = Species()
        var requestCode = 0
        var speciesID : Int? = null
        var climateZone = ClimateZone()
        var animalType = AnimalType()
    }
}