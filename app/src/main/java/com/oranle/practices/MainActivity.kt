package com.oranle.practices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.oranle.practices.databinding.ActivityMainBinding
import com.oranle.practices.koin.UserPresenter
import com.oranle.practices.koin.UserRepo
import com.oranle.practices.tasks.TaskViewModel
import com.oranle.practices.util.setupSnackBar
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var taskViewModel: TaskViewModel? = null

    val userRepo: UserRepo by inject {
        parametersOf("xcy")
    }

    val userPresenter: UserPresenter by inject {
        parametersOf("xcyxcy")
    }

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        activityMainBinding.let {
            it.vm = taskViewModel
            it.lifecycleOwner = this
        }

        setupSnackbar()

        val sayHi = userPresenter.sayHi()


        val repoName = userRepo.getName()

        Timber.v("----- $sayHi   $repoName")

        taskViewModel?.start(this)
    }

    private fun setupSnackbar() {
        activityMainBinding.root.setupSnackBar(
            this,
            taskViewModel!!.snackbarText,
            Snackbar.LENGTH_SHORT
        )

    }

}
