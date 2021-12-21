package ru.zoo.presentation.menu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_LIST
import ru.zoo.extensions.view.ISetToolbar
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.authorization.AuthorizationActivity
import ru.zoo.presentation.menu.MenuRepository.Companion.activeTab
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesActivity
import ru.zoo.presentation.tables.feedTypes.listDirectory.FeedTypesActivity
import ru.zoo.presentation.tables.feeds.listDirectory.FeedsActivity
import ru.zoo.presentation.tables.rations.listDirectory.RationsActivity
import ru.zoo.presentation.tables.users.listDirectory.UsersActivity

class MenuActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, ISetToolbar {
    lateinit var presenter: MenuPresenter
    lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        presenter = MenuPresenter(this, this, parent_menu)
        tabLayout = parent_menu.findViewById<TabLayout>(R.id.tab_navigation)
        fragment_navigation_menu.visible()
        setToolbar()
        tabLayout.getTabAt(activeTab)?.select()
        onTabSelected(tabLayout.getTabAt(activeTab))
        tabLayout.addOnTabSelectedListener(this)
        presenter.hideLoading()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        activeTab = tab!!.position
        invalidateOptionsMenu()
        when (activeTab) {
            0 -> {
                presenter.mainMenuPreset()
            }
            1 -> {
                presenter.profilePreset()
            }
            2 -> {
                presenter.settingsPreset()
            }
        }
    }

    override fun onPause() {
        hideSoftKeyboard(this)
        super.onPause()
    }

    override fun setToolbar() {

        val toolbar = findViewById<Toolbar>(R.id.include_toolbar)
        title_toolbar.text = ""
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        AuthorizationActivity.start(applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_exit, menu)
        when (activeTab) {
            0 -> {
                menuInflater.inflate(R.menu.menu_exit, menu)
            }
            1 -> {
                menuInflater.inflate(R.menu.empty_menu, menu)
            }
            2 -> {
                menuInflater.inflate(R.menu.empty_menu, menu)
            }
        }
        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_button_exit -> {
                AuthorizationActivity.start(applicationContext)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.button_users -> UsersActivity.startForResultList(this, REQUEST_CODE_LIST)
            R.id.button_employees -> EmployeesActivity.startForResultList(this, REQUEST_CODE_LIST)
            R.id.button_feeds -> FeedsActivity.startForResultList(this, REQUEST_CODE_LIST)
            R.id.button_feed_types -> FeedTypesActivity.startForResultList(this, REQUEST_CODE_LIST)
            R.id.button_rations -> RationsActivity.startForResultList(this, REQUEST_CODE_LIST)
//            R.id.frame_button_employees -> UsersActivity.start(this)
//            R.id.frame_button_roles -> UsersActivity.start(this)
        }
    }

    companion object {
        fun start(context: Context, activeTab: Int) {
            MenuRepository.activeTab = activeTab
            val i = Intent(context, MenuActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(i)
        }
    }
}