package ru.zoo.presentation.tables.feedTypes.listDirectory

import ru.zoo.data.models.FeedType

class FeedTypesRepository {
    companion object{
        var feedTypesTemp = ArrayList<FeedType>()
        fun addFeedType(feedType: FeedType) {
            if (checkedFeedType.any { it.id == feedType.id }) {
                checkedFeedType.removeAll { it.id == feedType.id }
            } else {
                checkedFeedType.add(feedType)
            }
        }
        var feedTypes = ArrayList<FeedType>()
        var redFeedTypes = ArrayList<FeedType>()
        var checkedFeedType = ArrayList<FeedType>()

        var requestCode = 0
        var isMultiple = false

        fun clear() {
            feedTypes.clear()
            redFeedTypes.clear()
            feedTypesTemp.clear()
        }
    }
}