package ru.zoo.presentation.tables.feedTypes.listDirectory

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_feed_types.view.*
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.Constants.REQUEST_CODE_LIST
import ru.zoo.data.models.FeedType
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.feedTypes.createEdit.FeedTypesEditActivity
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository.Companion.addFeedType
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository.Companion.checkedFeedType
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository.Companion.feedTypes
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository.Companion.redFeedTypes
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository.Companion.requestCode
import ru.zoo.presentation.tables.feedTypes.listDirectory.view.FeedTypesAdapter
import java.util.*

class FeedTypesPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    val toolbar = layout.include_toolbar
    val progressView = layout.progress_view
    val recyclerView = layout.list_view_feed_types

    val db = FeedTypesDB(context, this, activity)

    override fun hideLoading() {
        progressView.gone()
    }

    override fun showLoading() {
        progressView.visible()
    }

    fun getFeedTypes(){
        db.getFeedTypes()
    }

    fun search(s: String) {
        redFeedTypes.clear()
        feedTypes.forEach {
            if (it.title.toLowerCase(Locale.getDefault()).contains(s)) {
                redFeedTypes.add(it)
            }
        }
        setListView()
    }

    fun setListView() {
        val onClick: (feedType: FeedType) -> Unit = { feedType->
            if(requestCode == REQUEST_CODE_LIST){
                FeedTypesEditActivity.startForResultEdit(
                    activity,
                    REQUEST_CODE_EDIT,
                    feedType
                )
            }else if (requestCode == REQUEST_CODE_DIRECTORY) {
                if (!checkedFeedType.any { it.id == feedType.id }) {
                    checkedFeedType.clear()
                }
                addFeedType(feedType)
                recyclerView!!.adapter!!.notifyDataSetChanged()
                val intent = Intent()
                activity.setResult(AppCompatActivity.RESULT_OK, intent)
                activity.finish()
            }
        }
        recyclerView.adapter =
            FeedTypesAdapter(redFeedTypes, checkedFeedType, context, onClick)
    }
}