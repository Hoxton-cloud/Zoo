package ru.zoo.presentation.tables.feeds.createEdit

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_feeds_edit.view.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.include_text_parameter_without_delete.view.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditDB
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditRepository.Companion.feedForSend
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditRepository.Companion.feedType
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditRepository.Companion.requestCode

class FeedsEditPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    var toolbar = layout.include_toolbar
    var progressView = layout.progress_view
    var containerTitle = layout.container_title
    var containerID = layout.container_id
    var containerTypeID = layout.container_typeID
    var containerDelete = layout.frame_button_delete_feed

    val db = FeedsEditDB(context, this, activity)

    init {
        setMode()
    }

    fun setMode(){
        if (requestCode == REQUEST_CODE_CREATE){
            createPreset()
        } else if (requestCode == REQUEST_CODE_EDIT){
            db.getFeedType()
        }
    }

    fun editFeed() {
        db.editFeed()
    }

    fun addFeed() {
        db.addFeed()
    }

    fun getFeedType() {
        db.getFeedType()
    }

    fun deleteFeed() {
        db.deleteFeed()
    }

    override fun showLoading() {
        progressView.visible()
    }

    override fun hideLoading() {
        progressView.gone()
    }

    fun createPreset() {
        containerID.gone()
        containerDelete.gone()

        containerTitle.edit_text_title.text = context.getString(R.string.title)
        containerTitle.edit_text.hint = context.getString(R.string.enter_title)

        containerTypeID.label.text = context.getString(R.string.feed_type)
        containerTypeID.value.visible()
        if (feedType.title .isEmpty()){
            containerTypeID.value.gone()
        } else {
            containerTypeID.value.visible()
            containerTypeID.value.text = feedType.title.toString()
        }
    }

    fun editPreset() {
        containerID.label.text = context.getString(R.string.id)
        containerID.imageViewArrow.gone()
        containerID.value.text = feedForSend.id.toString()

        containerTitle.edit_text_title.text = context.getString(R.string.title)
        containerTitle.edit_text.hint = context.getString(R.string.enter_title)
        containerTitle.edit_text.setText(feedForSend.title)

        containerTypeID.label.text = context.getString(R.string.feed_type)
        containerTypeID.value.visible()
        if (feedType.title .isEmpty()){
            containerTypeID.value.gone()
        } else {
            containerTypeID.value.visible()
            containerTypeID.value.text = feedType.title.toString()
        }
    }



    fun readyToSend(): Boolean {
        return containerTitle.edit_text.text.isNotEmpty() &&
                containerTypeID.value.text.isNotEmpty()
    }
}