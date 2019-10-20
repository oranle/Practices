package com.oranle.practices.login

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.oranle.practices.R
import com.oranle.practices.databinding.LoginLayoutBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        DataBindingUtil.setContentView<LoginLayoutBinding>(this, R.layout.login_layout)
    }

}