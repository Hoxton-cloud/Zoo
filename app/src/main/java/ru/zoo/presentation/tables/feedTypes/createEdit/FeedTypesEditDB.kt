package ru.zoo.presentation.tables.feedTypes.createEdit

import android.app.Activity
import android.content.Context
import android.content.Intent
import ru.zoo.data.Constants
import ru.zoo.data.models.Animal
import ru.zoo.data.models.Feed
import ru.zoo.data.models.Position
import ru.zoo.db.queries.*
import ru.zoo.presentation.tables.feedTypes.createEdit.FeedTypesEditPresenter
import ru.zoo.presentation.tables.feedTypes.createEdit.FeedTypesEditRepository
import ru.zoo.presentation.tables.feedTypes.createEdit.FeedTypesEditRepository.Companion.feedTypeForSend
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository

class FeedTypesEditDB (val context: Context, val presenter: FeedTypesEditPresenter, val activity: Activity) {

    fun addFeedType() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            FeedTypesRepository.feedTypesTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        FeedTypesQueries().addFeedType(onStart,onFinish,context, feedTypeForSend)
    }

    fun editFeedType() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            FeedTypesRepository.feedTypesTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        FeedTypesQueries().editFeedTypeByID(onStart,onFinish,context, feedTypeForSend)
    }

    fun deleteFeedType() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            FeedTypesRepository.feedTypesTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        deleteItem(onStart,onFinish,context, "FeedType", feedTypeForSend.id)
    }
}