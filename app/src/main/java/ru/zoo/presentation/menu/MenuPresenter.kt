package ru.zoo.presentation.menu

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_menu.view.*
import kotlinx.android.synthetic.main.fragment_navigation_menu.view.*
import kotlinx.android.synthetic.main.icon_navigation_main.view.*
import kotlinx.android.synthetic.main.icon_navigation_profile.view.*
import kotlinx.android.synthetic.main.icon_navigation_settings.view.*
import ru.zoo.R
import ru.zoo.data.Constants.role
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

    init {
        setRolePreset()
    }

    fun setRolePreset() {
        if (role == "manager"){
            layout.frame_button_users.gone()
            layout.frame_button_employees.visible()
            layout.frame_button_positions.visible()
            layout.frame_button_feedings.gone()
            layout.frame_button_rations.gone()
            layout.frame_button_feeds.gone()
            layout.frame_button_feed_types.gone()
            layout.frame_button_feed_supplies.visible()
            layout.frame_button_suppliers.visible()
            layout.frame_button_animals.gone()
            layout.frame_button_checkins.visible()
            layout.frame_button_aviaries.visible()
            layout.frame_button_aviary_types.visible()
            layout.frame_button_offsprings.visible()
            layout.frame_button_animal_exchanges.visible()
            layout.frame_button_medical_examinations.gone()
            layout.frame_button_diagnosis.gone()
            layout.frame_button_species.gone()
            layout.frame_button_animal_types.gone()
            layout.frame_button_climate_zones.gone()
        } else if (role == "admin") {
            layout.frame_button_users.visible()
            layout.frame_button_employees.visible()
            layout.frame_button_positions.visible()
            layout.frame_button_feedings.visible()
            layout.frame_button_rations.visible()
            layout.frame_button_feeds.visible()
            layout.frame_button_feed_types.visible()
            layout.frame_button_feed_supplies.visible()
            layout.frame_button_suppliers.visible()
            layout.frame_button_animals.visible()
            layout.frame_button_checkins.visible()
            layout.frame_button_aviaries.visible()
            layout.frame_button_aviary_types.visible()
            layout.frame_button_offsprings.visible()
            layout.frame_button_animal_exchanges.visible()
            layout.frame_button_medical_examinations.visible()
            layout.frame_button_diagnosis.visible()
            layout.frame_button_species.visible()
            layout.frame_button_animal_types.visible()
            layout.frame_button_climate_zones.visible()
        }else if (role == "vet") {
            layout.frame_button_users.gone()
            layout.frame_button_employees.gone()
            layout.frame_button_positions.gone()
            layout.frame_button_feedings.visible()
            layout.frame_button_rations.visible()
            layout.frame_button_feeds.visible()
            layout.frame_button_feed_types.visible()
            layout.frame_button_feed_supplies.gone()
            layout.frame_button_suppliers.gone()
            layout.frame_button_animals.visible()
            layout.frame_button_checkins.gone()
            layout.frame_button_aviaries.gone()
            layout.frame_button_aviary_types.gone()
            layout.frame_button_offsprings.gone()
            layout.frame_button_animal_exchanges.gone()
            layout.frame_button_medical_examinations.visible()
            layout.frame_button_diagnosis.visible()
            layout.frame_button_species.visible()
            layout.frame_button_animal_types.gone()
            layout.frame_button_climate_zones.gone()
        }else if (role == "zoologist") {
            layout.frame_button_users.gone()
            layout.frame_button_employees.gone()
            layout.frame_button_positions.gone()
            layout.frame_button_feedings.visible()
            layout.frame_button_rations.visible()
            layout.frame_button_feeds.visible()
            layout.frame_button_feed_types.visible()
            layout.frame_button_feed_supplies.gone()
            layout.frame_button_suppliers.gone()
            layout.frame_button_animals.visible()
            layout.frame_button_checkins.visible()
            layout.frame_button_aviaries.visible()
            layout.frame_button_aviary_types.visible()
            layout.frame_button_offsprings.visible()
            layout.frame_button_animal_exchanges.gone()
            layout.frame_button_medical_examinations.gone()
            layout.frame_button_diagnosis.gone()
            layout.frame_button_species.gone()
            layout.frame_button_animal_types.gone()
            layout.frame_button_climate_zones.gone()
        }
    }

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