package ru.zoo.presentation.tables.animals.createEdit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_animals_edit.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.models.Animal
import ru.zoo.data.models.Species
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditRepository.Companion.animalForSend
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditRepository.Companion.species
import ru.zoo.presentation.tables.animals.createEdit.AnimalsEditRepository.Companion.requestCode
import ru.zoo.presentation.tables.animals.listDirectory.AnimalsRepository

class AnimalsEditActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: AnimalsEditPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animals_edit)
        presenter = AnimalsEditPresenter(this,this,parent_animals_create)
        presenter.hideLoading()
        setToolbar()
        container_name.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                animalForSend.name = container_name.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
        container_sex.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                animalForSend.sex = container_sex.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
    }
    override fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        if (requestCode == REQUEST_CODE_CREATE){
            title_toolbar.text = getString(R.string.new_animal)
        } else if (requestCode == REQUEST_CODE_EDIT){
            title_toolbar.text = getString(R.string.edit_animal)
        }
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close_filter)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (presenter.readyToSend()) {
            menuInflater.inflate(R.menu.menu_ok, menu)
        } else {
            menuInflater.inflate(R.menu.empty_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                backPressed()
            }
            R.id.menu_button_ok -> {
                if(requestCode == REQUEST_CODE_CREATE){
                    presenter.addAnimal()
                }else if (requestCode == REQUEST_CODE_EDIT) {
                    presenter.editAnimal()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        hideSoftKeyboard(this)
        super.onPause()
    }

    override fun onBackPressed() {
        backPressed()
    }

    fun backPressed() {
        animalForSend = Animal()
        species = Species()
        val intent = Intent()
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    fun onClick(view: View) {
        when (view) {
            container_speciesID -> {
//                val arrayList = ArrayList<Species>()
//                arrayList.add(species)
//                PO.startForResultDirectory(
//                    this,
//                    REQUEST_CODE_DIRECTORY, arrayList
//                )
            }
            button_delete_animal -> {
                presenter.deleteAnimal()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_DIRECTORY -> {
                    if (SpeciesesRepository.checkedSpecies.isNotEmpty()) {
                        animalForSend.speciesID = SpeciesesRepository.checkedSpecies[0].id
                        presenter.getSpecies()
                        presenter.setMode()
                    }
                }
            }
        }
    }
    companion object {
        fun startForResultCreate(
            activity: Activity,
            requestCode: Int
        ) {
            AnimalsEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, AnimalsEditActivity::class.java), requestCode
            )
        }

        fun startForResultEdit(
            activity: Activity,
            requestCode: Int,
            animalForEdit: Animal
        ) {
            animalForSend = animalForEdit
            AnimalsEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, AnimalsEditActivity::class.java), requestCode
            )
        }
    }
}