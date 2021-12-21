package ru.zoo.presentation.tables.specieses.createEdit

import android.app.Activity
import android.content.Context
import android.content.Intent
import ru.zoo.data.Constants
import ru.zoo.data.models.Animal
import ru.zoo.data.models.ClimateZone
import ru.zoo.data.models.Position
import ru.zoo.db.queries.*
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditPresenter
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.speciesForSend
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.climateZone
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository

class SpeciesesEditDB (val context: Context, val presenter: SpeciesesEditPresenter, val activity: Activity) {

    fun getClimateZone() {
//        val onStart: () -> Unit = {
//            presenter.showLoading()
//        }
//        val onFinish: (arrayList: ArrayList<ClimateZone>) -> Unit = {
//            climateZone = it[0]
//            if (SpeciesesEditRepository.requestCode == Constants.REQUEST_CODE_CREATE){
//                presenter.createPreset()
//            } else if (SpeciesesEditRepository.requestCode == Constants.REQUEST_CODE_EDIT){
//                presenter.editPreset()
//            }
//            presenter.hideLoading()
//        }
//        ClimateZonesQueries().getClimateZoneByIDFromServer(onStart,onFinish,context, speciesForSend.climateZoneID)
    }

    fun getAnimal() {
//        val onStart: () -> Unit = {
//            presenter.showLoading()
//        }
//        val onFinish: (arrayList: ArrayList<Animal>) -> Unit = {
//            animal = it[0]
//            if (SpeciesesEditRepository.requestCode == Constants.REQUEST_CODE_CREATE){
//                presenter.createPreset()
//            } else if (SpeciesesEditRepository.requestCode == Constants.REQUEST_CODE_EDIT){
//                presenter.editPreset()
//            }
//            presenter.hideLoading()
//        }
//        AnimalsQueries().getAnimalByIDFromServer(onStart,onFinish,context, speciesForSend.animalID)
    }

    fun addSpecies() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            SpeciesesRepository.speciesesTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        SpeciesesQueries().addSpecies(onStart,onFinish,context, speciesForSend)
    }

    fun editSpecies() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            SpeciesesRepository.speciesesTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        SpeciesesQueries().editSpeciesByID(onStart,onFinish,context, speciesForSend)
    }

    fun deleteSpecies() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            SpeciesesRepository.speciesesTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        deleteItem(onStart,onFinish,context, "Species", speciesForSend.id)
    }
}