package ru.zoo.presentation.tables.specieses.listDirectory

import android.app.Activity
import android.content.Context
import ru.zoo.data.models.Species
import ru.zoo.db.queries.SpeciesesQueries
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository.Companion.specieses
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository.Companion.redSpecieses

class SpeciesesDB (val context: Context, val presenter: SpeciesesPresenter, val activity: Activity) {
    init {
        getSpecieses()
    }

    fun getSpecieses() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            SpeciesesRepository.speciesesTemp.clear()
        }
        val onFinish: (arrayList: ArrayList<Species>) -> Unit = {
            specieses = it.clone() as ArrayList<Species>
            redSpecieses = it.clone() as ArrayList<Species>
            presenter.setListView()
            presenter.hideLoading()
        }
        SpeciesesQueries().getSpeciesesFromServer(onStart,onFinish,context)
    }
}