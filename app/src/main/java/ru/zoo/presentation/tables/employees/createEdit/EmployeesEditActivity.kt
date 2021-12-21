package ru.zoo.presentation.tables.employees.createEdit

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
import kotlinx.android.synthetic.main.activity_employees_edit.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.models.Employee
import ru.zoo.data.models.Position
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.presentation.tables.employees.createEdit.EmployeesEditRepository.Companion.employeeForSend
import ru.zoo.presentation.tables.employees.createEdit.EmployeesEditRepository.Companion.position
import ru.zoo.presentation.tables.employees.createEdit.EmployeesEditRepository.Companion.requestCode
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository

class EmployeesEditActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: EmployeesEditPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_edit)
        presenter = EmployeesEditPresenter(this,this,parent_employees_create)
        presenter.hideLoading()
        setToolbar()
        container_firstname.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                employeeForSend.firstName = container_firstname.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
        container_lastname.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                employeeForSend.lastName = container_lastname.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
        container_patronymic.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                employeeForSend.patronymic = container_patronymic.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
        container_phoneNumber.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                employeeForSend.phoneNumber = container_phoneNumber.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
    }
    override fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        if (requestCode == REQUEST_CODE_CREATE){
            title_toolbar.text = getString(R.string.new_employee)
        } else if (requestCode == REQUEST_CODE_EDIT){
            title_toolbar.text = getString(R.string.edit_employee)
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
                    presenter.addEmployee()
                }else if (requestCode == REQUEST_CODE_EDIT) {
                    presenter.editEmployee()
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
        employeeForSend = Employee()
        position = Position()
        val intent = Intent()
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    fun onClick(view: View) {
        when (view) {
            container_positionID -> {
//                val arrayList = ArrayList<Position>()
//                arrayList.add(position)
//                PO.startForResultDirectory(
//                    this,
//                    REQUEST_CODE_DIRECTORY, arrayList
//                )
            }
            button_delete_employee -> {
                presenter.deleteEmployee()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_DIRECTORY -> {
//                    if (PositionsRepository.checkedPosition.isNotEmpty()) {
//                        employeeForSend.positionID = PositionsRepository.checkedPosition[0].id
//                        presenter.getPosition()
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
            EmployeesEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, EmployeesEditActivity::class.java), requestCode
            )
        }

        fun startForResultEdit(
            activity: Activity,
            requestCode: Int,
            employeeForEdit: Employee
        ) {
            employeeForSend = employeeForEdit
            EmployeesEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, EmployeesEditActivity::class.java), requestCode
            )
        }
    }
}