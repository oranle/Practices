package com.oranle.practices.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oranle.practices.base.BaseViewModel
import com.oranle.practices.data.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    val userName = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val remember = MutableLiveData<Boolean>()

    val userInfo = MutableLiveData<UserInfo>()

    override fun start(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val users = getDB(context).getUserDao().getUsers()
            users[0]?.also {
                if (it.remember) {
                    userName.value = it.userName
                    password.value = it.password
                    remember.value = it.remember
                }
            }
        }



        if (remember.value == true) {


        } else {

        }
    }

    fun onLogin() {


    }

    fun saveUser() {

    }


}