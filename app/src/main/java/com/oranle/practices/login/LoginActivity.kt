package com.oranle.practices.login

import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.oranle.practices.R
import com.oranle.practices.databinding.LoginLayoutBinding
import com.oranle.practices.share_element.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

const val SHARE_IC = "ic-launcher"

class LoginActivity : AppCompatActivity() {

    private var binding: LoginLayoutBinding? = null

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        // 设置除了ShareElement外其他元素的退出方式
        window.exitTransition = Slide(Gravity.START)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_layout)

        binding = DataBindingUtil.setContentView(this, R.layout.login_layout)

        binding?.apply {
            viewmodel = loginViewModel
            lifecycleOwner = this@LoginActivity

            ViewCompat.setTransitionName(icLauncher, "$SHARE_IC icLauncher")
            ViewCompat.setTransitionName(desc, "$SHARE_IC text")
        }

        loginViewModel.start(this)
    }

    fun onLogin(view: View) {
        loginViewModel.onClick(view)
        startAct()
    }

    private fun startAct() {
        val intent = Intent(this, DetailActivity::class.java)

        val icLauncherView = binding?.icLauncher as View
        val textView = binding?.desc as View

        val transitionName = ViewCompat.getTransitionName(icLauncherView)
        val pair1 = Pair(icLauncherView, transitionName)

        val transitionNameText = ViewCompat.getTransitionName(textView)
        val pair2 = Pair(textView, transitionNameText)

        val activityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair1, pair2)
        ActivityCompat.startActivity(this, intent, activityOptionsCompat.toBundle())
    }

}