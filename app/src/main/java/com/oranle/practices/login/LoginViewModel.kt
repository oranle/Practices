package com.oranle.practices.login

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oranle.practices.MainActivity
import com.oranle.practices.R
import com.oranle.practices.base.BaseViewModel
import com.oranle.practices.data.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LoginViewModel : BaseViewModel() {

    val userName = MutableLiveData<String>("zhangsan")

    val password = MutableLiveData<String>("123")

    val remember = MutableLiveData<Boolean>(true)

    val isShowProgress = MutableLiveData<Boolean>(false)

    override fun start(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val users = getDB(context).getUserDao().getUsers()
            if (users.isNotEmpty()) {
                withContext(UI) {
                    kotlinx.coroutines.delay(2000)
                    users[users.size - 1]?.also {
                        if (it.remember) {
                            userName.value = it.userName
                            password.value = it.password
                            remember.value = it.remember
                        }
                    }
                }
            }
        }
    }

    override fun onClick(view: View) {
        super.onClick(view)

        Timber.v("onclick ${view.id}")

        when (view.id) {
            R.id.login -> {
                Timber.v("on click login")

                viewModelScope.launch(UI) {
                    isShowProgress.value = true

                    kotlinx.coroutines.delay(1000)

                    if (userName.value != null && password.value != null && remember.value != null) {
                        withContext(IO) {
                            getDB(view.context).getUserDao().insertUser(
                                UserInfo(
                                    userName = userName.value!! + "xcy",
                                    password = password.value!!,
                                    headUrl = "http://www.baidu.com",
                                    phoneNum = userName.value!!,
                                    remember = remember.value!!
                                )
                            )
                        }
                        isShowProgress.value = false

                        Toast.makeText(view.context, "登录成功", Toast.LENGTH_SHORT).show()

                        kotlinx.coroutines.delay(1000)

                        view.context.startActivity(Intent(view.context, MainActivity::class.java))

                        start(view.context)
                    }
                }
            }
            R.id.register -> {
                Timber.v("on click register")
            }
        }
    }

    fun onLogin() {


    }

    fun saveUser() {

    }


}