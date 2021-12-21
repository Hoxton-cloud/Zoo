package ru.zoo.presentation.tables.feeds.listDirectory

import ru.zoo.data.models.Feed

class FeedsRepository {
    companion object{
        var feedsTemp = ArrayList<Feed>()
        fun addFeed(feed: Feed) {
            if (checkedFeed.any { it.id == feed.id }) {
                checkedFeed.removeAll { it.id == feed.id }
            } else {
                checkedFeed.add(feed)
            }
        }
        var feeds = ArrayList<Feed>()
        var redFeeds = ArrayList<Feed>()
        var checkedFeed = ArrayList<Feed>()

        var requestCode = 0
        var isMultiple = false

        fun clear() {
            feeds.clear()
            redFeeds.clear()
            feedsTemp.clear()
        }
    }
}