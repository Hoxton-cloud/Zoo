package ru.zoo.presentation.authorization

import android.app.Activity
import android.content.Context
import ru.adept.extensions.util.dialogError
import ru.zoo.R
import ru.zoo.data.Constants.isOnline
import ru.zoo.data.Constants.url
import ru.zoo.data.Preferences
import ru.zoo.presentation.authorization.AuthorizationRepository.Companion.password
import ru.zoo.presentation.authorization.AuthorizationRepository.Companion.login
import ru.zoo.presentation.menu.MenuActivity

class AuthorizationDB (
    val context: Context,
    val presenter: AuthorizationPresenter,
    val activity: Activity,
) {

    init {
        getPreferences()
    }

    fun getPreferences() {
        Preferences.initialize(context)
        login = Preferences.getUserLogin(context)!!
        password = Preferences.getUserPassword(context)!!
        url = Preferences.getIpAddress(context)!!
        presenter.setText(login, password)
    }

    fun login(login: String, password: String) {
        val onStart: () -> Unit = {
            presenter.showLoading()
        }
        val onFinish: (isCorrect: Boolean,isErrorCredentials:Boolean) -> Unit = {isCorrect,isErrorCredentials->
            setPreferences(login, password)
            if (isCorrect) {
                presenter.hideLoading()
                checkCorrectData()
            } else {
                if(isErrorCredentials){
                    presenter.hideLoading()
                    dialogError(activity,context,context.getString(R.string.error_credentials))
                }else{
                    isOnline = false
                    MenuActivity.start(context, 0)
                }
            }
        }
        ru.zoo.db.queries.loginToServer(onStart, onFinish, context, login, password)
        //MenuActivity.start(context, 0)

    }



    fun checkCorrectData() {
        val correct = (Preferences.getString(Preferences.USER_TOKEN, "")!!
            .isNotEmpty() && Preferences.getString(Preferences.USER_TOKEN, "") != "")
        MenuActivity.start(context, 0)
    }

    fun setPreferences(login: String, password: String) {
        Preferences.setUserLogin(context, login)
        Preferences.setUserPassword(context, password)
    }
}