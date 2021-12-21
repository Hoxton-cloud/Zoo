package ru.zoo.presentation.tables.feedTypes.createEdit

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_feed_types_edit.view.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.include_text_parameter_without_delete.view.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.feedTypes.createEdit.FeedTypesEditDB
import ru.zoo.presentation.tables.feedTypes.createEdit.FeedTypesEditRepository.Companion.feedTypeForSend
import ru.zoo.presentation.tables.feedTypes.createEdit.FeedTypesEditRepository.Companion.requestCode

class FeedTypesEditPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    var toolbar = layout.include_toolbar
    var progressView = layout.progress_view
    var containerTitle = layout.container_title
    var containerID = layout.container_id
    var containerDelete = layout.frame_button_delete_feed_type

    val db = FeedTypesEditDB(context, this, activity)

    init {
        setMode()
    }

    fun setMode(){
        if (requestCode == REQUEST_CODE_CREATE){
            createPreset()
        } else if (requestCode == REQUEST_CODE_EDIT){
        }
    }

    fun editFeedType() {
        db.editFeedType()
    }

    fun addFeedType() {
        db.addFeedType()
    }

    fun deleteFeedType() {
        db.deleteFeedType()
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
    }

    fun editPreset() {
        containerID.label.text = context.getString(R.string.id)
        containerID.imageViewArrow.gone()
        containerID.value.text = feedTypeForSend.id.toString()

        containerTitle.edit_text_title.text = context.getString(R.string.title)
        containerTitle.edit_text.hint = context.getString(R.string.enter_title)
        containerTitle.edit_text.setText(feedTypeForSend.title)
    }



    fun readyToSend(): Boolean {
        return containerTitle.edit_text.text.isNotEmpty()
    }
}