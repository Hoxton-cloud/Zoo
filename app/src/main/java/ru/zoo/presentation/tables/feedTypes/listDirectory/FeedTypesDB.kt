package ru.zoo.presentation.tables.feedTypes.listDirectory

import android.app.Activity
import android.content.Context
import ru.zoo.data.models.FeedType
import ru.zoo.db.queries.FeedTypesQueries
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository.Companion.feedTypes
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository.Companion.redFeedTypes

class FeedTypesDB (val context: Context, val presenter: FeedTypesPresenter, val activity: Activity) {
    init {
        getFeedTypes()
    }

    fun getFeedTypes() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            FeedTypesRepository.feedTypesTemp.clear()
        }
        val onFinish: (arrayList: ArrayList<FeedType>) -> Unit = {
            feedTypes = it.clone() as ArrayList<FeedType>
            redFeedTypes = it.clone() as ArrayList<FeedType>
            presenter.setListView()
            presenter.hideLoading()
        }
        FeedTypesQueries().getFeedTypesFromServer(onStart,onFinish,context)
    }
}