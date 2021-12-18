package ru.zoo.presentation.tables.users.listDirectory

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
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar_search.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_LIST
import ru.zoo.data.models.User
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.users.createEdit.UsersEditActivity
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.requestCode

class UsersActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: UsersPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        presenter = UsersPresenter(this,this,parent_users)
        setToolbar()
    }

    override fun setToolbar() {
        val toolbarSearch = findViewById<Toolbar>(R.id.include_toolbar_search)
        setSupportActionBar(toolbarSearch)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarSearch.gone()
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        title_toolbar.text = getString(R.string.users)
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
        when (requestCode) {
            REQUEST_CODE_LIST -> {
                menuInflater.inflate(R.menu.search_menu, menu)
            }
            REQUEST_CODE_DIRECTORY -> {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            presenter.getUsers()
        }
    }

    override fun onBackPressed() {
        dropData()
        finish()
    }

    fun dropData() {
        UsersRepository.clear()
    }

    fun onClick(view: View) {
        when (view) {
            create_user_btn -> {
                UsersEditActivity.startForResultCreate(
                    this,
                    REQUEST_CODE_CREATE
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
            UsersRepository.requestCode = requestCode
            context.startActivityForResult(
                Intent(context, UsersActivity::class.java), requestCode
            )
        }

        fun startForResultDirectory(context: Activity, requestCode: Int, checkedUser: ArrayList<User>) {
            UsersRepository.requestCode = requestCode
            UsersRepository.checkedUser = checkedUser
            context.startActivityForResult(
                Intent(context, UsersActivity::class.java), requestCode
            )
        }
    }
}