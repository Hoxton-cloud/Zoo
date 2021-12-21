package ru.zoo.presentation.tables.feeds.createEdit

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
import kotlinx.android.synthetic.main.activity_feeds_edit.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.models.FeedType
import ru.zoo.data.models.Feed
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesActivity
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesRepository
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditRepository.Companion.feedForSend
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditRepository.Companion.feedType
import ru.zoo.presentation.tables.feeds.createEdit.FeedsEditRepository.Companion.requestCode
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsRepository

class FeedsEditActivity : AppCompatActivity(), ISetToolbar {
    lateinit var presenter: FeedsEditPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds_edit)
        presenter = FeedsEditPresenter(this,this,parent_feeds_create)
        presenter.hideLoading()
        setToolbar()
        container_title.edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                feedForSend.title = container_title.edit_text.text.toString()
                invalidateOptionsMenu()
            }

        })
    }
    override fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        if (requestCode == REQUEST_CODE_CREATE){
            title_toolbar.text = getString(R.string.new_feed)
        } else if (requestCode == REQUEST_CODE_EDIT){
            title_toolbar.text = getString(R.string.edit_feed)
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
                    presenter.addFeed()
                }else if (requestCode == REQUEST_CODE_EDIT) {
                    presenter.editFeed()
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
        feedForSend = Feed()
        feedType = FeedType()
        val intent = Intent()
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    fun onClick(view: View) {
        when (view) {
            container_typeID -> {
                val arrayList = ArrayList<FeedType>()
                arrayList.add(feedType)
                FeedTypesActivity.startForResultDirectory(
                    this,
                    REQUEST_CODE_DIRECTORY, arrayList
                )
            }
            button_delete_feed -> {
                presenter.deleteFeed()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_DIRECTORY -> {
                    if (FeedTypesRepository.checkedFeedType.isNotEmpty()) {
                        feedForSend.typeID = FeedTypesRepository.checkedFeedType[0].id
                        presenter.getFeedType()
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
            FeedsEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, FeedsEditActivity::class.java), requestCode
            )
        }

        fun startForResultEdit(
            activity: Activity,
            requestCode: Int,
            feedForEdit: Feed
        ) {
            feedForSend = feedForEdit
            FeedsEditRepository.requestCode = requestCode
            activity.startActivityForResult(
                Intent(activity, FeedsEditActivity::class.java), requestCode
            )
        }
    }
}