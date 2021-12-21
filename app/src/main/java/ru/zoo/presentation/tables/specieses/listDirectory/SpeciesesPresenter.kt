package ru.zoo.presentation.tables.specieses.listDirectory

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_specieses.view.*
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.Constants.REQUEST_CODE_LIST
import ru.zoo.data.models.Species
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditActivity
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository.Companion.addSpecies
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository.Companion.checkedSpecies
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository.Companion.specieses
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository.Companion.redSpecieses
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository.Companion.requestCode
import ru.zoo.presentation.tables.specieses.listDirectory.view.SpeciesesAdapter
import java.util.*

class SpeciesesPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    val toolbar = layout.include_toolbar
    val progressView = layout.progress_view
    val recyclerView = layout.list_view_specieses

    val db = SpeciesesDB(context, this, activity)

    override fun hideLoading() {
        progressView.gone()
    }

    override fun showLoading() {
        progressView.visible()
    }

    fun getSpecieses(){
        db.getSpecieses()
    }

    fun search(s: String) {
        redSpecieses.clear()
        specieses.forEach {
            if (it.title.toLowerCase(Locale.getDefault()).contains(s)) {
                redSpecieses.add(it)
            }
        }
        setListView()
    }

    fun setListView() {
        val onClick: (species: Species) -> Unit = { species->
            if(requestCode == REQUEST_CODE_LIST){
                SpeciesesEditActivity.startForResultEdit(
                    activity,
                    REQUEST_CODE_EDIT,
                    species
                )
            }else if (requestCode == REQUEST_CODE_DIRECTORY) {
                if (!checkedSpecies.any { it.id == species.id }) {
                    checkedSpecies.clear()
                }
                addSpecies(species)
                recyclerView!!.adapter!!.notifyDataSetChanged()
                val intent = Intent()
                activity.setResult(AppCompatActivity.RESULT_OK, intent)
                activity.finish()
            }
        }
        recyclerView.adapter =
            SpeciesesAdapter(redSpecieses, checkedSpecies, context, onClick)
    }
}