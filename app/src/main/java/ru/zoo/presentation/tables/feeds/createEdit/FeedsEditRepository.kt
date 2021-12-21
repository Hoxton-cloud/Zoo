package ru.zoo.presentation.tables.feeds.createEdit

import ru.zoo.data.models.Animal
import ru.zoo.data.models.Feed
import ru.zoo.data.models.FeedType

class FeedsEditRepository {
    companion object{
        var feedForSend = Feed()
        var requestCode = 0
        var feedID : Int? = null
        var feedType = FeedType()
    }
}