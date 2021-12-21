package ru.zoo.presentation.tables.feeds.createEdit

import android.app.Activity
import android.content.Context
import android.content.Intent
import ru.zoo.data.Constants
import ru.zoo.data.models.FeedType
import ru.zoo.data.models.Feed
import ru.zoo.db.queries.*
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditPresenter
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditRepository
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditRepository.Companion.feedForSend
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditRepository.Companion.feedType
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository

class FeedsEditDB (val context: Context, val presenter: FeedsEditPresenter, val activity: Activity) {

    fun getFeedType() {
        val onStart: () -> Unit = {
            presenter.showLoading()
        }
        val onFinish: (arrayList: ArrayList<FeedType>) -> Unit = {
            feedType = it[0]
            if (FeedsEditRepository.requestCode == Constants.REQUEST_CODE_CREATE){
                presenter.createPreset()
            } else if (FeedsEditRepository.requestCode == Constants.REQUEST_CODE_EDIT){
                presenter.editPreset()
            }
            presenter.hideLoading()
        }
        FeedTypesQueries().getFeedTypeByIDFromServer(onStart,onFinish,context, feedForSend.typeID)
    }

    fun addFeed() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            FeedsRepository.feedsTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        FeedsQueries().addFeed(onStart,onFinish,context, feedForSend)
    }

    fun editFeed() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            FeedsRepository.feedsTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        FeedsQueries().editFeedByID(onStart,onFinish,context, feedForSend)
    }

    fun deleteFeed() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            FeedsRepository.feedsTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        deleteItem(onStart,onFinish,context, "Feeds", feedForSend.id)
    }
}