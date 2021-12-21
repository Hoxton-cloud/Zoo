package ru.zoo.presentation.tables.rations.listDirectory

import android.app.Activity
import android.content.Context
import ru.zoo.data.models.Ration
import ru.zoo.db.queries.RationsQueries
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository.Companion.rations
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository.Companion.redRations

class RationsDB (val context: Context, val presenter: RationsPresenter, val activity: Activity) {
    init {
        getRations()
    }

    fun getRations() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            RationsRepository.rationsTemp.clear()
        }
        val onFinish: (arrayList: ArrayList<Ration>) -> Unit = {
            rations = it.clone() as ArrayList<Ration>
            redRations = it.clone() as ArrayList<Ration>
            presenter.setListView()
            presenter.hideLoading()
        }
        RationsQueries().getRationsFromServer(onStart,onFinish,context)
    }
}