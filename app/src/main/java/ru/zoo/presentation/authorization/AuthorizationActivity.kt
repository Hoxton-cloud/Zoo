package ru.zoo.presentation.authorization

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_authorization.*
import ru.zoo.R
import ru.zoo.data.Constants.isOnline
import ru.zoo.data.Constants.url
import java.util.concurrent.Executors
import ru.zoo.data.Preferences
import ru.zoo.extensions.view.hideSoftKeyboard
import ru.zoo.presentation.authorization.AuthorizationRepository.Companion.login
import ru.zoo.presentation.authorization.AuthorizationRepository.Companion.password
import ru.zoo.presentation.authorization.AuthorizationRepository.Companion.seePassword

class AuthorizationActivity : AppCompatActivity() {
    lateinit var presenter: AuthorizationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        presenter = AuthorizationPresenter(this, this, parent_authorization)
        edit_text_password.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                password = edit_text_password.text.toString()
                presenter.loginButtonColor(login, password)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        edit_text_login.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                login = edit_text_login.text.toString()
                presenter.loginButtonColor(login, password)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        presenter.hideLoading()

        if (url == "") {
            url = "https://sid-t.nipigas.ru/"
            Preferences.setUserIpAddress(this,url!!)
        }

        edit_text_login.setText(login)
        edit_text_password.setText(password)
        requestPermissions()
        Executors.newSingleThreadExecutor().execute {
            //      DB.clearAllTables()
        }
    }
    fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
//                Manifest.permission.RECORD_AUDIO
            ),
            1
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onPause() {
        hideSoftKeyboard(this)
        super.onPause()
    }

    fun onClick(view: View) {
        when (view) {
            button_login -> {
                isOnline = true
                presenter.login(login, password)
            }
            see_password -> {
                seePassword = !seePassword
                presenter.watchPassword(seePassword)
            }
        }
    }

    companion object {

        fun start(context: Context) {
            val i = Intent(context, AuthorizationActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(i)
        }
    }
}