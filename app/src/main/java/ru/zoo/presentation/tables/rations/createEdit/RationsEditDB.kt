package ru.zoo.presentation.tables.rations.createEdit

import android.app.Activity
import android.content.Context
import android.content.Intent
import ru.zoo.data.Constants
import ru.zoo.data.models.Animal
import ru.zoo.data.models.Feed
import ru.zoo.data.models.Position
import ru.zoo.db.queries.*
import ru.zoo.presentation.tables.rations.createEdit.RationsEditPresenter
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.rationForSend
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.feed
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.animal
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository

class RationsEditDB (val context: Context, val presenter: RationsEditPresenter, val activity: Activity) {

    fun getFeed() {
        val onStart: () -> Unit = {
            presenter.showLoading()
        }
        val onFinish: (arrayList: ArrayList<Feed>) -> Unit = {
            feed = it[0]
            if (RationsEditRepository.requestCode == Constants.REQUEST_CODE_CREATE){
                presenter.createPreset()
            } else if (RationsEditRepository.requestCode == Constants.REQUEST_CODE_EDIT){
                presenter.editPreset()
            }
            presenter.hideLoading()
        }
        FeedsQueries().getFeedByIDFromServer(onStart,onFinish,context, rationForSend.feedID)
    }

    fun getAnimal() {
//        val onStart: () -> Unit = {
//            presenter.showLoading()
//        }
//        val onFinish: (arrayList: ArrayList<Animal>) -> Unit = {
//            animal = it[0]
//            if (RationsEditRepository.requestCode == Constants.REQUEST_CODE_CREATE){
//                presenter.createPreset()
//            } else if (RationsEditRepository.requestCode == Constants.REQUEST_CODE_EDIT){
//                presenter.editPreset()
//            }
//            presenter.hideLoading()
//        }
//        AnimalsQueries().getAnimalByIDFromServer(onStart,onFinish,context, rationForSend.animalID)
    }

    fun addRation() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            RationsRepository.rationsTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        RationsQueries().addRation(onStart,onFinish,context, rationForSend)
    }

    fun editRation() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            RationsRepository.rationsTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        RationsQueries().editRationByID(onStart,onFinish,context, rationForSend)
    }

    fun deleteRation() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            RationsRepository.rationsTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        deleteItem(onStart,onFinish,context, "Ration", rationForSend.id)
    }
}