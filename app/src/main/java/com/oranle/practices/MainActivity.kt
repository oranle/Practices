package com.oranle.practices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.oranle.practices.databinding.ActivityMainBinding
import com.oranle.practices.tasks.TaskViewModel
import com.oranle.practices.util.setupSnackBar

class MainActivity : AppCompatActivity() {

    private var taskViewModel : TaskViewModel? = null

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        activityMainBinding.let {
            it.vm = taskViewModel
            it.lifecycleOwner = this
        }

        setupSnackbar()

        taskViewModel?.start(this)
    }

    private fun setupSnackbar() {
        activityMainBinding.root.setupSnackBar(this, taskViewModel!!.snackbarText, Snackbar.LENGTH_SHORT)

    }

}
