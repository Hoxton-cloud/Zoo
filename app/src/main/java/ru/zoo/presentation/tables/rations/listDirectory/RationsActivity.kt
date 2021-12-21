package ru.zoo.presentation.tables.rations.listDirectory

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
import kotlinx.android.synthetic.main.activity_rations.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar_search.*
import ru.zoo.R
import ru.zoo.data.Constants
import ru.zoo.data.models.Ration
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.rations.createEdit.RationsEditActivity
import ru.zoo.presentation.tables.rations.listDirectory.RationsActivity
import ru.zoo.presentation.tables.rations.listDirectory.RationsPresenter
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository

class RationsActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: RationsPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rations)
        presenter = RationsPresenter(this,this,parent_rations)
        setToolbar()
    }

    override fun setToolbar() {
        val toolbarSearch = findViewById<Toolbar>(R.id.include_toolbar_search)
        setSupportActionBar(toolbarSearch)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarSearch.gone()
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        title_toolbar.text = getString(R.string.rations)
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
        when (RationsRepository.requestCode) {
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
        RationsRepository.clear()
    }

    fun onClick(view: View) {
        when (view) {
            create_ration_btn -> {
                RationsEditActivity.startForResultCreate(
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
            RationsRepository.requestCode = requestCode
            context.startActivityForResult(
                Intent(context, RationsActivity::class.java), requestCode
            )
        }

        fun startForResultDirectory(context: Activity, requestCode: Int, checkedRation: ArrayList<Ration>) {
            RationsRepository.requestCode = requestCode
            RationsRepository.checkedRation = checkedRation
            context.startActivityForResult(
                Intent(context, RationsActivity::class.java), requestCode
            )
        }
    }
}