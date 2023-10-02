package com.test.assignment.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.test.assignment.R

object SlidingOptions {
    fun slidRight(view: View, context: Context) {
        val slideInRightAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)

        slideInRightAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        view.startAnimation(slideInRightAnimation)

    }

    fun slideLeft(view: View, context: Context) {
        val slideInLeftAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)

        slideInLeftAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        view.startAnimation(slideInLeftAnimation)
    }

    fun slideDown(view: View, context: Context) {
        val slideInLeftAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_down)

        slideInLeftAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        view.startAnimation(slideInLeftAnimation)
    }

    fun slideUp(view: View, context: Context) {
        val slideInLeftAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_up)

        slideInLeftAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        view.startAnimation(slideInLeftAnimation)
    }

}