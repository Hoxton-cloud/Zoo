package ru.zoo.presentation.menu

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_menu.view.*
import kotlinx.android.synthetic.main.icon_navigation_main.view.*
import kotlinx.android.synthetic.main.icon_navigation_profile.view.*
import kotlinx.android.synthetic.main.icon_navigation_settings.view.*
import ru.zoo.R
import ru.zoo.data.Preferences
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible

class MenuPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout,
) : IProgressView {
    val fragmentMainMenu = layout.fragment_navigation_menu
    val fragmentProfile = layout.fragment_profile_menu
    val fragmentSettings = layout.fragment_settings_menu
    var toolbar = layout.include_toolbar
    var tabNavigation = layout.tab_navigation
    var navigationMainButton = layout.main_container
    var profileButton = layout.profile_container
    var settingsButton = layout.settings_container
    val progressView = layout.progress_view
//    var db = MenuDB(activity, context, this)

    override fun hideLoading() {
        progressView.gone()
    }

    override fun showLoading() {
        progressView.visible()
    }

    fun mainMenuPreset() {
        fragmentMainMenu.visible()
        fragmentProfile.gone()
        fragmentSettings.gone()

        toolbar.gone()

//        navigationMainButton.setImageResource(R.drawable.tab_main_active)
//        profileButton.setImageResource(R.drawable.tab_profile_unactive)
//        settingsButton.setImageResource(R.drawable.tab_notification_unactive)

//        toolbar.title_toolbar.text = null
    }

    fun profilePreset() {
        fragmentMainMenu.gone()
        fragmentProfile.visible()
        fragmentSettings.gone()

    }

    fun settingsPreset() {
        fragmentMainMenu.gone()
        fragmentProfile.gone()
        fragmentSettings.visible()


    }
//    fun getUser(){
//        db.getUser()
//    }
}