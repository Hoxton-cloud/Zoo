package ru.zoo.presentation.tables.animals.createEdit

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_animals_edit.view.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.include_text_parameter_without_delete.view.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditDB
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditRepository.Companion.animalForSend
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditRepository.Companion.species
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditRepository.Companion.requestCode

class AnimalsEditPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    var toolbar = layout.include_toolbar
    var progressView = layout.progress_view
    var containerName = layout.container_name
    var containerSex = layout.container_sex
    var containerID = layout.container_id
    var containerSpeciesID = layout.container_speciesID
    var containerDelete = layout.frame_button_delete_animal

    val db = AnimalsEditDB(context, this, activity)

    init {
        setMode()
    }

    fun setMode(){
        if (requestCode == REQUEST_CODE_CREATE){
            createPreset()
        } else if (requestCode == REQUEST_CODE_EDIT){
            db.getSpecies()
        }
    }

    fun editAnimal() {
        db.editAnimal()
    }

    fun addAnimal() {
        db.addAnimal()
    }

    fun getSpecies() {
        db.getSpecies()
    }

    fun deleteAnimal() {
        db.deleteAnimal()
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

        containerName.edit_text_title.text = context.getString(R.string.name)
        containerName.edit_text.hint = context.getString(R.string.enter_name)

        containerSex.edit_text_title.text = context.getString(R.string.sex)
        containerSex.edit_text.hint = context.getString(R.string.enter_sex)

        containerSpeciesID.label.text = context.getString(R.string.species)
        containerSpeciesID.value.visible()
        if (species.title.isEmpty()){
            containerSpeciesID.value.gone()
        } else {
            containerSpeciesID.value.visible()
            containerSpeciesID.value.text = species.title.toString()
        }
    }

    fun editPreset() {
        containerID.label.text = context.getString(R.string.id)
        containerID.imageViewArrow.gone()
        containerID.value.text = animalForSend.id.toString()

        containerName.edit_text_title.text = context.getString(R.string.name)
        containerName.edit_text.hint = context.getString(R.string.enter_name)
        containerName.edit_text.setText(animalForSend.name)

        containerSex.edit_text_title.text = context.getString(R.string.sex)
        containerSex.edit_text.hint = context.getString(R.string.enter_sex)
        containerSex.edit_text.setText(animalForSend.sex)

        containerSpeciesID.label.text = context.getString(R.string.species)
        containerSpeciesID.value.visible()
        if (species.title.isEmpty()){
            containerSpeciesID.value.gone()
        } else {
            containerSpeciesID.value.visible()
            containerSpeciesID.value.text = species.title.toString()
        }
    }



    fun readyToSend(): Boolean {
        return containerName.edit_text.text.isNotEmpty() &&
                containerSex.edit_text.text.isNotEmpty() &&
                containerSpeciesID.value.text.isNotEmpty()
    }
}