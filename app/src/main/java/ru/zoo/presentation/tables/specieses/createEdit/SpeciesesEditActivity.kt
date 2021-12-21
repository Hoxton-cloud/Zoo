package ru.zoo.presentation.tables.specieses.createEdit

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
import kotlinx.android.synthetic.main.activity_specieses_edit.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.models.Animal
import ru.zoo.data.models.AnimalType
import ru.zoo.data.models.Species
import ru.zoo.data.models.ClimateZone
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.speciesForSend
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.animalType
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.climateZone
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditRepository.Companion.requestCode
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository

class SpeciesesEditActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: SpeciesesEditPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specieses_edit)
        presenter = SpeciesesEditPresenter(this,this,parent_specieses_create)
        presenter.hideLoading()
        setToolbar()
        container_title.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                speciesForSend.title = container_title.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
    }
    override fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        if (requestCode == REQUEST_CODE_CREATE){
            title_toolbar.text = getString(R.string.new_species)
        } else if (requestCode == REQUEST_CODE_EDIT){
            title_toolbar.text = getString(R.string.edit_species)
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
                    presenter.addSpecies()
                }else if (requestCode == REQUEST_CODE_EDIT) {
                    presenter.editSpecies()
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
        speciesForSend = Species()
        climateZone = ClimateZone()
        animalType = AnimalType()
        val intent = Intent()
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    fun onClick(view: View) {
        when (view) {
            container_climateZoneID -> {
//                val arrayList = ArrayList<ClimateZone>()
//                arrayList.add(climateZone)
//                PO.startForResultDirectory(
//                    this,
//                    REQUEST_CODE_DIRECTORY, arrayList
//                )
            }
            container_typeID -> {
//                val arrayList = ArrayList<Animal>()
//                arrayList.add(animal)
//                PO.startForResultDirectory(
//                    this,
//                    REQUEST_CODE_DIRECTORY, arrayList
//                )
            }
            button_delete_species -> {
                presenter.deleteSpecies()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_DIRECTORY -> {
//                    if (ClimateZonesRepository.checkedClimateZone.isNotEmpty()) {
//                        speciesForSend.climateZoneID = ClimateZonesRepository.checkedClimateZone[0].id
//                        presenter.getClimateZone()
//                        presenter.setMode()
//                    }
//                    if (AnimalTypesRepository.checkedAnimalTypes.isNotEmpty()) {
//                        speciesForSend.typeID = AnimalTypesRepository.checkedAnimalTypes[0].id
//                        presenter.getAnimal()
//                        presenter.setMode()
//                    }
                }
            }
        }
    }
    companion object {
        fun startForResultCreate(
            activity: Activity,
            requestCode: Int
        ) {
            SpeciesesEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, SpeciesesEditActivity::class.java), requestCode
            )
        }

        fun startForResultEdit(
            activity: Activity,
            requestCode: Int,
            speciesForEdit: Species
        ) {
            speciesForSend = speciesForEdit
            SpeciesesEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, SpeciesesEditActivity::class.java), requestCode
            )
        }
    }
}