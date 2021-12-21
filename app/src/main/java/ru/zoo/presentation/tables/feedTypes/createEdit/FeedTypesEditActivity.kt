package ru.zoo.presentation.tables.feedTypes.createEdit

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
import kotlinx.android.synthetic.main.activity_feed_types_edit.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.models.Animal
import ru.zoo.data.models.FeedType
import ru.zoo.data.models.Feed
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository
import ru.zoo.presentation.tables.feedTypes.createEdit.FeedTypesEditRepository.Companion.feedTypeForSend
import ru.zoo.presentation.tables.feedTypes.createEdit.FeedTypesEditRepository.Companion.requestCode
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository

class FeedTypesEditActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: FeedTypesEditPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_types_edit)
        presenter = FeedTypesEditPresenter(this,this,parent_feed_types_create)
        presenter.hideLoading()
        setToolbar()
        container_title.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                feedTypeForSend.title = container_title.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
    }
    override fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        if (requestCode == REQUEST_CODE_CREATE){
            title_toolbar.text = getString(R.string.new_feed_type)
        } else if (requestCode == REQUEST_CODE_EDIT){
            title_toolbar.text = getString(R.string.edit_feed_type)
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
                    presenter.addFeedType()
                }else if (requestCode == REQUEST_CODE_EDIT) {
                    presenter.editFeedType()
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
        feedTypeForSend = FeedType()
        val intent = Intent()
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    fun onClick(view: View) {
        when (view) {
            button_delete_feed_type -> {
                presenter.deleteFeedType()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_DIRECTORY -> {
                }
            }
        }
    }
    companion object {
        fun startForResultCreate(
            activity: Activity,
            requestCode: Int
        ) {
            FeedTypesEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, FeedTypesEditActivity::class.java), requestCode
            )
        }

        fun startForResultEdit(
            activity: Activity,
            requestCode: Int,
            feedTypeForEdit: FeedType
        ) {
            feedTypeForSend = feedTypeForEdit
            FeedTypesEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, FeedTypesEditActivity::class.java), requestCode
            )
        }
    }
}