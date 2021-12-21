package ru.zoo.presentation.tables.rations.createEdit

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_rations_edit.view.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.include_text_parameter_without_delete.view.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.rations.createEdit.RationsEditDB
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.rationForSend
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.feed
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.animal
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.requestCode

class RationsEditPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    var toolbar = layout.include_toolbar
    var progressView = layout.progress_view
    var containerTime = layout.container_time
    var containerMass = layout.container_mass
    var containerID = layout.container_id
    var containerFeedID = layout.container_feedID
    var containerAnimalID = layout.container_animalID
    var containerDelete = layout.frame_button_delete_ration

    val db = RationsEditDB(context, this, activity)

    init {
        setMode()
    }

    fun setMode(){
        if (requestCode == REQUEST_CODE_CREATE){
            createPreset()
        } else if (requestCode == REQUEST_CODE_EDIT){
            db.getFeed()
            db.getAnimal()
        }
    }

    fun editRation() {
        db.editRation()
    }

    fun addRation() {
        db.addRation()
    }

    fun getFeed() {
        db.getFeed()
    }

    fun getAnimal() {
        db.getAnimal()
    }

    fun deleteRation() {
        db.deleteRation()
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

        containerTime.edit_text_title.text = context.getString(R.string.time)
        containerTime.edit_text.hint = context.getString(R.string.enter_time)

        containerMass.edit_text_title.text = context.getString(R.string.mass)
        containerMass.edit_text.hint = context.getString(R.string.enter_mass)

        containerFeedID.label.text = context.getString(R.string.feed)
        containerFeedID.value.visible()
        if (feed.title.isEmpty()){
            containerFeedID.value.gone()
        } else {
            containerFeedID.value.visible()
            containerFeedID.value.text = feed.title.toString()
        }

        containerAnimalID.label.text = context.getString(R.string.animal)
        containerAnimalID.value.visible()
        if (animal.name.isEmpty()){
            containerAnimalID.value.gone()
        } else {
            containerAnimalID.value.visible()
            containerAnimalID.value.text = animal.name.toString()
        }
    }

    fun editPreset() {
        containerID.label.text = context.getString(R.string.id)
        containerID.imageViewArrow.gone()
        containerID.value.text = rationForSend.id.toString()

        containerTime.edit_text_title.text = context.getString(R.string.time)
        containerTime.edit_text.hint = context.getString(R.string.enter_time)
        containerTime.edit_text.setText(rationForSend.time)

        containerMass.edit_text_title.text = context.getString(R.string.mass)
        containerMass.edit_text.hint = context.getString(R.string.enter_mass)
        containerMass.edit_text.setText(rationForSend.mass)

        containerFeedID.label.text = context.getString(R.string.feed)
        containerFeedID.value.visible()
        if (feed.title.isEmpty()){
            containerFeedID.value.gone()
        } else {
            containerFeedID.value.visible()
            containerFeedID.value.text = feed.title.toString()
        }

        containerAnimalID.label.text = context.getString(R.string.animal)
        containerAnimalID.value.visible()
        if (animal.name.isEmpty()){
            containerAnimalID.value.gone()
        } else {
            containerAnimalID.value.visible()
            containerAnimalID.value.text = animal.name.toString()
        }
    }



    fun readyToSend(): Boolean {
        return containerTime.edit_text.text.isNotEmpty() &&
                containerMass.edit_text.text.isNotEmpty() &&
                containerFeedID.value.text.isNotEmpty() &&
                containerAnimalID.value.text.isNotEmpty()
    }
}