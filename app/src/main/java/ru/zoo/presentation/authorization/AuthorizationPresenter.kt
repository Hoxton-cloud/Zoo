package ru.zoo.presentation.authorization

import android.app.Activity
import android.content.Context
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_authorization.view.*
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.BuildConfig
import ru.zoo.R
import ru.zoo.data.Constants.url
import ru.zoo.data.Preferences

class AuthorizationPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) :
    IProgressView {
    var progressView = layout.progress_view
    var db = AuthorizationDB(context,this,activity)
    init {
        db.getPreferences()
        layout.version.text = "Версия ${BuildConfig.VERSION_NAME}"
    }

    override fun showLoading() {
        progressView.visible()
    }

    override fun hideLoading() {
        progressView.gone()
    }

    fun login(login: String, password: String){
        db.login(login, password)
    }

    fun setText(login: String, password: String) {
        layout.edit_text_login.setText(login)
        layout.edit_text_password.setText(password)
        loginButtonColor(login, password)
    }

    fun loginButtonColor(login: String, password: String) {
        if (login != "" && password != "") {
            layout.button_login.isClickable = true
            layout.frame_button.alpha = 1f
        } else {
            layout.button_login.isClickable = false
            layout.frame_button.alpha = 0.5f
        }
    }

    fun watchPassword(watch: Boolean) {
        if (!watch) {
            layout.edit_text_password.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            layout.see_password.setImageResource(R.drawable.ic_eye_off)
        } else {
            layout.edit_text_password.inputType = InputType.TYPE_CLASS_TEXT
            layout.see_password.setImageResource(R.drawable.ic_eye_on)
        }
    }
}