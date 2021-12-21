package ru.zoo.presentation.tables.feeds.listDirectory

import android.app.Activity
import android.content.Context
import ru.zoo.data.models.Feed
import ru.zoo.db.queries.FeedsQueries
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository.Companion.feeds
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository.Companion.redFeeds

class FeedsDB (val context: Context, val presenter: FeedsPresenter, val activity: Activity) {
    init {
        getFeeds()
    }

    fun getFeeds() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            FeedsRepository.feedsTemp.clear()
        }
        val onFinish: (arrayList: ArrayList<Feed>) -> Unit = {
            feeds = it.clone() as ArrayList<Feed>
            redFeeds = it.clone() as ArrayList<Feed>
            presenter.setListView()
            presenter.hideLoading()
        }
        FeedsQueries().getFeedsFromServer(onStart,onFinish,context)
    }
}