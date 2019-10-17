package com.oranle.practices.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

fun View.setupSnackBar(lifecycleOwner: LifecycleOwner, msg: LiveData<String>, timeLength: Int) {
    msg.observe(lifecycleOwner, Observer {
        it?.let {
            showSnackbar(it, timeLength)
        }
    })
}

fun View.showSnackbar(msg: String, timeLength: Int) {
    Snackbar.make(this, msg, timeLength).run {
        addCallback(object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar?) {
                super.onShown(sb)
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
            }
        })
        show()
    }

}