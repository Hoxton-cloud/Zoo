package ru.zoo.presentation.tables.feedTypes.createEdit

import ru.zoo.data.models.Animal
import ru.zoo.data.models.Feed
import ru.zoo.data.models.FeedType

class FeedTypesEditRepository {
    companion object{
        var feedTypeForSend = FeedType()
        var requestCode = 0
        var feedTypeID : Int? = null
    }
}