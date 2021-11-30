package ru.zoo.presentation.tables.employees.listDirectory

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_employees.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_EMPLOYEES_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EMPLOYEES_LIST
import ru.zoo.data.models.Employee
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository.Companion.requestCode

class EmployeesActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: EmployeesPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees)
        presenter = EmployeesPresenter(this,this,parent_employees)
        setToolbar()
    }

    override fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        title_toolbar.text = getString(R.string.employees)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (requestCode) {
            REQUEST_CODE_EMPLOYEES_LIST -> {
                menuInflater.inflate(R.menu.search_menu, menu)
            }
            REQUEST_CODE_EMPLOYEES_DIRECTORY -> {
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

    override fun onBackPressed() {
        dropData()
        finish()
    }

    fun dropData() {
        EmployeesRepository.clear()
    }

    fun onClick(view: View) {
        when (view) {
            create_employee_btn -> {
//                UsersEditActivity.startForResultCreate(
//                    this,
//                    REQUEST_CODE_USERS_CREATE
//                )
            }

        }
    }

    companion object {
        fun startForResultList(context: Activity, requestCode: Int) {
            EmployeesRepository.requestCode = requestCode
            context.startActivityForResult(
                Intent(context, EmployeesActivity::class.java), requestCode
            )
        }

        fun startForResultDirectory(context: Activity, requestCode: Int, checkedEmployee: ArrayList<Employee>) {
            EmployeesRepository.requestCode = requestCode
            EmployeesRepository.checkedEmployee = checkedEmployee
            context.startActivityForResult(
                Intent(context, EmployeesActivity::class.java), requestCode
            )
        }
    }
}