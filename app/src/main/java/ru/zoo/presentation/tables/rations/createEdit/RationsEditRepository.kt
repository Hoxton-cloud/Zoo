package ru.zoo.presentation.tables.rations.createEdit

import ru.zoo.data.models.Animal
import ru.zoo.data.models.Feed
import ru.zoo.data.models.Ration

class RationsEditRepository {
    companion object{
        var rationForSend = Ration()
        var requestCode = 0
        var rationID : Int? = null
        var feed = Feed()
        var animal = Animal()
    }
}