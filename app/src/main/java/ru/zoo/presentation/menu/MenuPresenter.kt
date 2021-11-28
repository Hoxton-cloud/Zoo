//package ru.zoo.presentation.menu
//
//import android.app.Activity
//import android.content.Context
//import androidx.constraintlayout.widget.ConstraintLayout
//import ru.zoo.R
//import ru.zoo.data.Constants.isOnline
//import ru.zoo.data.Preferences
//import ru.zoo.extensions.view.IProgressView
//
//class MenuPresenter (
//    val activity: Activity,
//    val context: Context,
//    val layout: ConstraintLayout,
//) : IProgressView {
//    val fragmentMainMenu = layout.fragment_navigation_menu
//    val fragmentProfile = layout.fragment_profile_menu
//    val fragmentSettings = layout.fragment_settings_menu
//    var toolbar = layout.include_toolbar
//    var tabNavigation = layout.tab_navigation
//    var navigationMainButton = layout.main_container
//    var profileButton = layout.profile_container
//    var settingsButton = layout.note_container
//    val progressView = layout.progress_view
//    var db = MenuDB(activity, context, this)
//
//    init {
//        setProject()
//    }
//
//    override fun hideLoading() {
//        progressView.gone()
//    }
//
//    override fun showLoading() {
//        progressView.visible()
//    }
//
//    fun mainMenuPreset() {
//        fragmentMainMenu.visible()
//        fragmentProfile.gone()
//        fragmentSettings.gone()
//
//        toolbar.gone()
//
////        navigationMainButton.setImageResource(R.drawable.tab_main_active)
////        profileButton.setImageResource(R.drawable.tab_profile_unactive)
////        settingsButton.setImageResource(R.drawable.tab_notification_unactive)
//
//        toolbar.title_toolbar.gone()
////        toolbar.title_toolbar.text = null
//    }
//
//    fun setProject() {
//        fragmentMainMenu.choice_project.toggleVisibility(Preferences.getProjectName(context) != "")
//        fragmentMainMenu.choice_project.text = Preferences.getProjectName(context)
//    }
//
//    fun profilePreset() {
//        fragmentMainMenu.gone()
//        fragmentProfile.visible()
//        fragmentSettings.gone()
//
//        toolbar.visible()
//        containerFullName.dr_full_name.text = user.fullName
//        containerPosition.title.text = context.getString(R.string.position)
//        containerPosition.value.text = user.position
//        containerCompany.title.text = context.getString(R.string.company)
//        containerCompany.value.text = user.company
//        containerLanguage.labelFromWithOpen.text = context.getString(R.string.system_language)
//        containerLanguage.labelFromWithOpen.margin(0f, 0f, 8f, 0f)
//        containerLanguage.openFromWithOpen.text = context.getString(R.string.russian)
//        containerLanguage.iv_emergency_from_text.gone()
//        containerOfflineDirectories.labelFromWithOpen.text =
//            context.getString(R.string.directories_for_offline_mode)
//        containerDefaultProject.labelFromWithOpen.text = context.getString(R.string.default_project)
//        profileButton.setImageResource(R.drawable.tab_profile_active)
//        navigationMainButton.setImageResource(R.drawable.tab_main_unactive)
//        notificationButton.setImageResource(R.drawable.tab_notification_unactive)
//
//        toolbar.title_toolbar.visible()
//        toolbar.title_toolbar.text = context.getString(R.string.profile)
//    }
//
//    fun notificationsUpdate() {
//        val onItemClickListener: ((notification: Notification) -> Unit) = {
//            it.wasRead = true
//            db.getInspectionById(it.inspection)
//        }
//        val adapter = NotificationsAdapter(
//            redNotifications,
//            context,
//            onItemClickListener
//        )
//        notificationView.adapter = adapter
//        checkEmptyState()
//    }
//    fun checkEmptyState() {
//        layout.empty_state_notifications.toggleVisibility(redNotifications.isEmpty())
//        layout.empty_state_notifications.empty_state_tv.text =
//            context.getString(R.string.empty_state_notifications_filtered)
//        layout.empty_state_drawable.setImageDrawable(context.getDrawable(R.drawable.ic_empty_inspections))
//    }
//    fun notificationPreset() {
//        fragmentMainMenu.gone()
//        fragmentProfile.gone()
//        fragmentSettings.visible()
//
//        toolbar.visible()
//        toolbar.title_toolbar.visible()
//        toolbar.title_toolbar.text = context.getString(R.string.notifications)
//
//        profileButton.setImageResource(R.drawable.tab_profile_unactive)
//        navigationMainButton.setImageResource(R.drawable.tab_main_unactive)
//        settingsButton.setImageResource(R.drawable.tab_notification_active)
//        notificationsUpdate()
//        db.getNotifications()
//    }
//    fun getUser(){
//        db.getUser()
//    }
//}