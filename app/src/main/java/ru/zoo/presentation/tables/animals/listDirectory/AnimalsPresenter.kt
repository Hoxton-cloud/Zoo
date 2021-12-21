package ru.zoo.presentation.tables.animals.listDirectory

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_animals.view.*
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.Constants.REQUEST_CODE_LIST
import ru.zoo.data.models.Animal
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditActivity
import ru.zoo.presentation.tables.animals.listDirectory.AnimalsRepository.Companion.addAnimal
import ru.zoo.presentation.tables.animals.listDirectory.AnimalsRepository.Companion.checkedAnimal
import ru.zoo.presentation.tables.animals.listDirectory.AnimalsRepository.Companion.animals
import ru.zoo.presentation.tables.animals.listDirectory.AnimalsRepository.Companion.redAnimals
import ru.zoo.presentation.tables.animals.listDirectory.AnimalsRepository.Companion.requestCode
import ru.zoo.presentation.tables.animals.listDirectory.view.AnimalsAdapter
import java.util.*

class AnimalsPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    val toolbar = layout.include_toolbar
    val progressView = layout.progress_view
    val recyclerView = layout.list_view_animals

    val db = AnimalsDB(context, this, activity)

    override fun hideLoading() {
        progressView.gone()
    }

    override fun showLoading() {
        progressView.visible()
    }

    fun getAnimals(){
        db.getAnimals()
    }

    fun search(s: String) {
        redAnimals.clear()
        animals.forEach {
            if (it.name.toLowerCase(Locale.getDefault()).contains(s)) {
                redAnimals.add(it)
            }
        }
        setListView()
    }

    fun setListView() {
        val onClick: (animal: Animal) -> Unit = { animal->
            if(requestCode == REQUEST_CODE_LIST){
                AnimalsEditActivity.startForResultEdit(
                    activity,
                    REQUEST_CODE_EDIT,
                    animal
                )
            }else if (requestCode == REQUEST_CODE_DIRECTORY) {
                if (!checkedAnimal.any { it.id == animal.id }) {
                    checkedAnimal.clear()
                }
                addAnimal(animal)
                recyclerView!!.adapter!!.notifyDataSetChanged()
                val intent = Intent()
                activity.setResult(AppCompatActivity.RESULT_OK, intent)
                activity.finish()
            }
        }
        recyclerView.adapter =
            AnimalsAdapter(redAnimals, checkedAnimal, context, onClick)
    }
}