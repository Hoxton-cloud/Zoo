package ru.zoo.presentation.tables.rations.listDirectory

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_rations.view.*
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.Constants.REQUEST_CODE_LIST
import ru.zoo.data.models.Ration
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.rations.createEdit.RationsEditActivity
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository.Companion.addRation
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository.Companion.checkedRation
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository.Companion.rations
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository.Companion.redRations
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository.Companion.requestCode
import ru.zoo.presentation.tables.rations.listDirectory.view.RationsAdapter
import java.util.*

class RationsPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    val toolbar = layout.include_toolbar
    val progressView = layout.progress_view
    val recyclerView = layout.list_view_rations

    val db = RationsDB(context, this, activity)

    override fun hideLoading() {
        progressView.gone()
    }

    override fun showLoading() {
        progressView.visible()
    }

    fun getRations(){
        db.getRations()
    }

    fun search(s: String) {
        redRations.clear()
        rations.forEach {
            if (it.time .toLowerCase(Locale.getDefault()).contains(s)) {
                redRations.add(it)
            }
        }
        setListView()
    }

    fun setListView() {
        val onClick: (ration: Ration) -> Unit = { ration->
            if(requestCode == REQUEST_CODE_LIST){
                RationsEditActivity.startForResultEdit(
                    activity,
                    REQUEST_CODE_EDIT,
                    ration
                )
            }else if (requestCode == REQUEST_CODE_DIRECTORY) {
                if (!checkedRation.any { it.id == ration.id }) {
                    checkedRation.clear()
                }
                addRation(ration)
                recyclerView!!.adapter!!.notifyDataSetChanged()
                val intent = Intent()
                activity.setResult(AppCompatActivity.RESULT_OK, intent)
                activity.finish()
            }
        }
        recyclerView.adapter =
            RationsAdapter(redRations, checkedRation, context, onClick)
    }
}