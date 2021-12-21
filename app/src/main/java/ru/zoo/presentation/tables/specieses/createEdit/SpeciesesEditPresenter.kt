package ru.zoo.presentation.tables.specieses.createEdit

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_specieses_edit.view.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.include_text_parameter_without_delete.view.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditDB
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.speciesForSend
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.climateZone
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.animalType
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.requestCode

class SpeciesesEditPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    var toolbar = layout.include_toolbar
    var progressView = layout.progress_view
    var containerTitle = layout.container_title
    var containerID = layout.container_id
    var containerClimateZoneID = layout.container_climateZoneID
    var containerAnimalTypeID = layout.container_typeID
    var containerDelete = layout.frame_button_delete_species

    val db = SpeciesesEditDB(context, this, activity)

    init {
        setMode()
    }

    fun setMode(){
        if (requestCode == REQUEST_CODE_CREATE){
            createPreset()
        } else if (requestCode == REQUEST_CODE_EDIT){
            db.getClimateZone()
            db.getAnimal()
        }
    }

    fun editSpecies() {
        db.editSpecies()
    }

    fun addSpecies() {
        db.addSpecies()
    }

    fun getClimateZone() {
        db.getClimateZone()
    }

    fun getAnimal() {
        db.getAnimal()
    }

    fun deleteSpecies() {
        db.deleteSpecies()
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

        containerClimateZoneID.label.text = context.getString(R.string.climateZone)
        containerClimateZoneID.value.visible()
        if (climateZone.title.isEmpty()){
            containerClimateZoneID.value.gone()
        } else {
            containerClimateZoneID.value.visible()
            containerClimateZoneID.value.text = climateZone.title.toString()
        }

        containerAnimalTypeID.label.text = context.getString(R.string.animal)
        containerAnimalTypeID.value.visible()
        if (animalType.title.isEmpty()){
            containerAnimalTypeID.value.gone()
        } else {
            containerAnimalTypeID.value.visible()
            containerAnimalTypeID.value.text = animalType.title.toString()
        }
    }

    fun editPreset() {
        containerID.label.text = context.getString(R.string.id)
        containerID.imageViewArrow.gone()
        containerID.value.text = speciesForSend.id.toString()

        containerTitle.edit_text_title.text = context.getString(R.string.title)
        containerTitle.edit_text.hint = context.getString(R.string.enter_title)
        containerTitle.edit_text.setText(speciesForSend.title)

        containerClimateZoneID.label.text = context.getString(R.string.climateZone)
        containerClimateZoneID.value.visible()
        if (climateZone.title.isEmpty()){
            containerClimateZoneID.value.gone()
        } else {
            containerClimateZoneID.value.visible()
            containerClimateZoneID.value.text = climateZone.title.toString()
        }

        containerAnimalTypeID.label.text = context.getString(R.string.animal)
        containerAnimalTypeID.value.visible()
        if (animalType.title.isEmpty()){
            containerAnimalTypeID.value.gone()
        } else {
            containerAnimalTypeID.value.visible()
            containerAnimalTypeID.value.text = animalType.title.toString()
        }
    }



    fun readyToSend(): Boolean {
        return containerTitle.edit_text.text.isNotEmpty() &&
                containerClimateZoneID.value.text.isNotEmpty() &&
                containerAnimalTypeID.value.text.isNotEmpty()
    }
}