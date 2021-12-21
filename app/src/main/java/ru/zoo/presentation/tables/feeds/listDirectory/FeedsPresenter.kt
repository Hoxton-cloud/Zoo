package ru.zoo.presentation.tables.feeds.listDirectory

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_feeds.view.*
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.Constants.REQUEST_CODE_LIST
import ru.zoo.data.models.Feed
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditActivity
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository.Companion.addFeed
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository.Companion.checkedFeed
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository.Companion.feeds
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository.Companion.redFeeds
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository.Companion.requestCode
import ru.zoo.presentation.tables.feeds.listDirectory.view.FeedsAdapter
import java.util.*

class FeedsPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    val toolbar = layout.include_toolbar
    val progressView = layout.progress_view
    val recyclerView = layout.list_view_feeds

    val db = FeedsDB(context, this, activity)

    override fun hideLoading() {
        progressView.gone()
    }

    override fun showLoading() {
        progressView.visible()
    }

    fun getFeeds(){
        db.getFeeds()
    }

    fun search(s: String) {
        redFeeds.clear()
        feeds.forEach {
            if (it.title.toLowerCase(Locale.getDefault()).contains(s)) {
                redFeeds.add(it)
            }
        }
        setListView()
    }

    fun setListView() {
        val onClick: (feed: Feed) -> Unit = { feed->
            if(requestCode == REQUEST_CODE_LIST){
                FeedsEditActivity.startForResultEdit(
                    activity,
                    REQUEST_CODE_EDIT,
                    feed
                )
            }else if (requestCode == REQUEST_CODE_DIRECTORY) {
                if (!checkedFeed.any { it.id == feed.id }) {
                    checkedFeed.clear()
                }
                addFeed(feed)
                recyclerView!!.adapter!!.notifyDataSetChanged()
                val intent = Intent()
                activity.setResult(AppCompatActivity.RESULT_OK, intent)
                activity.finish()
            }
        }
        recyclerView.adapter =
            FeedsAdapter(redFeeds, checkedFeed, context, onClick)
    }
}