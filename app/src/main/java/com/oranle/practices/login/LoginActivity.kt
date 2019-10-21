package com.oranle.practices.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.oranle.practices.R
import com.oranle.practices.databinding.LoginLayoutBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_layout)

        val loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        val binding =
            DataBindingUtil.setContentView<LoginLayoutBinding>(this, R.layout.login_layout)

        binding.viewmodel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.start(this)
    }

}