package ru.zoo.presentation.tables.rations.createEdit

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
import kotlinx.android.synthetic.main.activity_rations_edit.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.models.Animal
import ru.zoo.data.models.Ration
import ru.zoo.data.models.Feed
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.rationForSend
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.animal
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.feed
import ru.zoo.presentation.tables.rations.createEdit.RationsEditRepository.Companion.requestCode
import ru.zoo.presentation.tables.rations.listDirectory.RationsRepository

class RationsEditActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: RationsEditPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rations_edit)
        presenter = RationsEditPresenter(this,this,parent_rations_create)
        presenter.hideLoading()
        setToolbar()
        container_time.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                rationForSend.time = container_time.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
        container_mass.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                rationForSend.mass = container_mass.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
    }
    override fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        if (requestCode == REQUEST_CODE_CREATE){
            title_toolbar.text = getString(R.string.new_ration)
        } else if (requestCode == REQUEST_CODE_EDIT){
            title_toolbar.text = getString(R.string.edit_ration)
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
                    presenter.addRation()
                }else if (requestCode == REQUEST_CODE_EDIT) {
                    presenter.editRation()
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
        rationForSend = Ration()
        feed = Feed()
        animal = Animal()
        val intent = Intent()
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    fun onClick(view: View) {
        when (view) {
            container_feedID -> {
//                val arrayList = ArrayList<Feed>()
//                arrayList.add(feed)
//                PO.startForResultDirectory(
//                    this,
//                    REQUEST_CODE_DIRECTORY, arrayList
//                )
            }
            container_animalID -> {
//                val arrayList = ArrayList<Animal>()
//                arrayList.add(animal)
//                PO.startForResultDirectory(
//                    this,
//                    REQUEST_CODE_DIRECTORY, arrayList
//                )
            }
            button_delete_ration -> {
                presenter.deleteRation()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_DIRECTORY -> {
                    if (FeedsRepository.checkedFeed.isNotEmpty()) {
                        rationForSend.feedID = FeedsRepository.checkedFeed[0].id
                        presenter.getFeed()
                        presenter.setMode()
                    }
//                    if (AnimalsRepository.checkedAnimal.isNotEmpty()) {
//                        rationForSend.animalID = AnimalsRepository.checkedAnimal[0].id
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
            RationsEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, RationsEditActivity::class.java), requestCode
            )
        }

        fun startForResultEdit(
            activity: Activity,
            requestCode: Int,
            rationForEdit: Ration
        ) {
            rationForSend = rationForEdit
            RationsEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, RationsEditActivity::class.java), requestCode
            )
        }
    }
}