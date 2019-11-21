package com.oranle.practices.share_element

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Slide
import android.transition.TransitionSet
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.oranle.practices.R
import com.oranle.practices.login.SHARE_IC
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ViewCompat.setTransitionName(detail_img, SHARE_IC)

        window.enterTransition = Slide(Gravity.START)
        window.exitTransition = Slide(Gravity.END)

        //设置ShareElementTransition, 指定的share Element会执行这个Transition动画
        val transitionSet = TransitionSet()
        transitionSet.addTransition(ChangeBounds())
        transitionSet.addTarget(detail_img)

        window.sharedElementEnterTransition = transitionSet
        window.sharedElementExitTransition = transitionSet
    }

}