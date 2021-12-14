package ru.zoo.presentation.tables.users.listDirectory

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_USERS_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_USERS_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_USERS_LIST
import ru.zoo.data.models.User
import ru.zoo.extensions.view.ISetToolbar
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
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        title_toolbar.text = getString(R.string.users)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (requestCode) {
            REQUEST_CODE_USERS_LIST -> {
                menuInflater.inflate(R.menu.search_menu, menu)
            }
            REQUEST_CODE_USERS_DIRECTORY -> {
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
                    REQUEST_CODE_USERS_CREATE
                )
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