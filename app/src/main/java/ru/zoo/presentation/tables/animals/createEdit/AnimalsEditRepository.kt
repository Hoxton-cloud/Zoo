package ru.zoo.presentation.tables.animals.createEdit

import ru.zoo.data.models.Animal
import ru.zoo.data.models.Species

class AnimalsEditRepository {
    companion object{
        var animalForSend = Animal()
        var requestCode = 0
        var animalID : Int? = null
        var species = Species()
    }
}