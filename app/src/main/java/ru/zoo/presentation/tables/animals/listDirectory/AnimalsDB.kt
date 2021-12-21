package ru.zoo.presentation.tables.animals.listDirectory

import android.app.Activity
import android.content.Context
import ru.zoo.data.models.Animal
import ru.zoo.db.queries.AnimalsQueries
import ru.zoo.presentation.tables.animals.listDirectory.AnimalsRepository.Companion.animals
import ru.zoo.presentation.tables.animals.listDirectory.AnimalsRepository.Companion.redAnimals

class AnimalsDB (val context: Context, val presenter: AnimalsPresenter, val activity: Activity) {
    init {
        getAnimals()
    }

    fun getAnimals() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            AnimalsRepository.animalsTemp.clear()
        }
        val onFinish: (arrayList: ArrayList<Animal>) -> Unit = {
            animals = it.clone() as ArrayList<Animal>
            redAnimals = it.clone() as ArrayList<Animal>
            presenter.setListView()
            presenter.hideLoading()
        }
        AnimalsQueries().getAnimalsFromServer(onStart,onFinish,context)
    }
}