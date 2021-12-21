package ru.zoo.presentation.tables.animals.createEdit

import android.app.Activity
import android.content.Context
import android.content.Intent
import ru.zoo.data.Constants
import ru.zoo.data.models.Animal
import ru.zoo.data.models.Species
import ru.zoo.data.models.Position
import ru.zoo.db.queries.*
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditPresenter
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditRepository
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditRepository.Companion.animalForSend
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditRepository.Companion.species
import ru.zoo.presentation.tables.animals.listDirectory.AnimalsRepository

class AnimalsEditDB (val context: Context, val presenter: AnimalsEditPresenter, val activity: Activity) {

    fun getSpecies() {
        val onStart: () -> Unit = {
            presenter.showLoading()
        }
        val onFinish: (arrayList: ArrayList<Species>) -> Unit = {
            species = it[0]
            if (AnimalsEditRepository.requestCode == Constants.REQUEST_CODE_CREATE){
                presenter.createPreset()
            } else if (AnimalsEditRepository.requestCode == Constants.REQUEST_CODE_EDIT){
                presenter.editPreset()
            }
            presenter.hideLoading()
        }
        SpeciesesQueries().getSpeciesByIDFromServer(onStart,onFinish,context, animalForSend.speciesID)
    }
    

    fun addAnimal() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            AnimalsRepository.animalsTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        AnimalsQueries().addAnimal(onStart,onFinish,context, animalForSend)
    }

    fun editAnimal() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            AnimalsRepository.animalsTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        AnimalsQueries().editAnimalByID(onStart,onFinish,context, animalForSend)
    }

    fun deleteAnimal() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            AnimalsRepository.animalsTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        deleteItem(onStart,onFinish,context, "Animal", animalForSend.id)
    }
}