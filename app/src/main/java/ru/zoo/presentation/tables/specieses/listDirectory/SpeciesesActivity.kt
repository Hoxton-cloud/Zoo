package ru.zoo.presentation.tables.specieses.listDirectory

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_specieses.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar_search.*
import ru.zoo.R
import ru.zoo.data.Constants
import ru.zoo.data.models.Species
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.specieses.createEdit.SpeciesesEditActivity
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesActivity
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesPresenter
import ru.zoo.presentation.tables.specieses.listDirectory.SpeciesesRepository

class SpeciesesActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: SpeciesesPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specieses)
        presenter = SpeciesesPresenter(this,this,parent_specieses)
        setToolbar()
    }

    override fun setToolbar() {
        val toolbarSearch = findViewById<Toolbar>(R.id.include_toolbar_search)
        setSupportActionBar(toolbarSearch)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarSearch.gone()
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        title_toolbar.text = getString(R.string.specieses)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        search_text.background.clearColorFilter()
        search_text.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search(search_text.text.toString().toLowerCase())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    fun search(s: String) {
        presenter.search(s)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (SpeciesesRepository.requestCode) {
            Constants.REQUEST_CODE_LIST -> {
                menuInflater.inflate(R.menu.search_menu, menu)
            }
            Constants.REQUEST_CODE_DIRECTORY -> {
                menuInflater.inflate(R.menu.empty_menu, menu)
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.onBackPressed()
            }
            R.id.menu_button_search -> {
                include_toolbar_search.visible()
                search_text.requestFocus()
                var imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        dropData()
        finish()
    }

    fun dropData() {
        SpeciesesRepository.clear()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            presenter.getSpecieses()
        }
    }

    fun onClick(view: View) {
        when (view) {
            create_species_btn -> {
                SpeciesesEditActivity.startForResultCreate(
                    this,
                    Constants.REQUEST_CODE_CREATE
                )
            }
            close_search -> {
                include_toolbar_search.gone()
                search("")
                search_text.setText("")
                hideSoftKeyboard(this)
            }
        }
    }

    companion object {
        fun startForResultList(context: Activity, requestCode: Int) {
            SpeciesesRepository.requestCode = requestCode
            context.startActivityForResult(
                Intent(context, SpeciesesActivity::class.java), requestCode
            )
        }

        fun startForResultDirectory(context: Activity, requestCode: Int, checkedSpecies: ArrayList<Species>) {
            SpeciesesRepository.requestCode = requestCode
            SpeciesesRepository.checkedSpecies = checkedSpecies
            context.startActivityForResult(
                Intent(context, SpeciesesActivity::class.java), requestCode
            )
        }
    }
}