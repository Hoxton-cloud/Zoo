package ru.zoo.presentation.tables.users.createEdit

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
import kotlinx.android.synthetic.main.activity_users_edit.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_EMPLOYEES_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_USERS_EDIT
import ru.zoo.data.Constants.REQUEST_CODE_USERS_CREATE
import ru.zoo.data.models.Employee
import ru.zoo.data.models.User
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesActivity
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository
import ru.zoo.presentation.tables.users.createEdit.UsersEditRepository.Companion.employee
import ru.zoo.presentation.tables.users.createEdit.UsersEditRepository.Companion.requestCode
import ru.zoo.presentation.tables.users.createEdit.UsersEditRepository.Companion.userForSend

class UsersEditActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: UsersEditPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_edit)
        presenter = UsersEditPresenter(this,this,parent_users_create)
        presenter.hideLoading()
        setToolbar()
        container_username.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userForSend.username = container_username.edit_text.text.toString()
            }

        })
        container_password.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userForSend.password = container_password.edit_text.text.toString()
            }

        })
        container_role.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userForSend.role = container_role.edit_text.text.toString()
            }

        })
    }
    override fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        if (requestCode == REQUEST_CODE_USERS_CREATE){
            title_toolbar.text = getString(R.string.new_user)
        } else if (requestCode == REQUEST_CODE_USERS_EDIT){
            title_toolbar.text = getString(R.string.edit_user)
        }
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close_filter)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.directory_menu_without_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                backPressed()
            }
            R.id.menu_button_ok -> {
                if(requestCode == REQUEST_CODE_USERS_CREATE){
                    presenter.addUser()
                }else if (requestCode == REQUEST_CODE_USERS_EDIT) {
                    presenter.editUser()
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
        userForSend = User()
        val intent = Intent()
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    fun onClick(view: View) {
        when (view) {
            container_employeeID -> {
                val arrayList = ArrayList<Employee>()
                arrayList.add(employee)
                EmployeesActivity.startForResultDirectory(
                    this,
                    REQUEST_CODE_EMPLOYEES_DIRECTORY, arrayList
                )
            }
            button_delete_user -> {
                presenter.deleteUser()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_EMPLOYEES_DIRECTORY -> {
                    if (EmployeesRepository.checkedEmployee.isNotEmpty()) {
                        userForSend.employeeID = EmployeesRepository.checkedEmployee[0].id
                        presenter.getEmployee()
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
            UsersEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, UsersEditActivity::class.java), requestCode
            )
        }

        fun startForResultEdit(
            activity: Activity,
            requestCode: Int,
            userForEdit: User
        ) {
            userForSend = userForEdit
            UsersEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, UsersEditActivity::class.java), requestCode
            )
        }
    }
}