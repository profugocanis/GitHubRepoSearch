package com.ijk.githubreposearch.utils.extensions

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.widget.AppCompatTextView

var View.isHidden: Boolean
    get() {
        return this.visibility == View.GONE
    }
    set(value) {
        if (value) {
            this.visibility = View.GONE
        } else {
            this.visibility = View.VISIBLE
        }
    }

fun View.showAnim() {
    ObjectAnimator.ofFloat(this, View.ALPHA, 1f)
        .setDuration(200)
        .start()
}

var AppCompatTextView.textAnim: String
    get() {
        return this.text.toString()
    }
    set(value) {
        val anim = AlphaAnimation(1.0f, 0.0f)
        anim.duration = 100
        anim.repeatCount = 1
        anim.repeatMode = Animation.REVERSE
        val t = this
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {
                t.text = value
            }
        })

        this.startAnimation(anim)
    }
