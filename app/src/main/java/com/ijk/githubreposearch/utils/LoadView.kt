package com.ijk.githubreposearch.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.florent37.viewanimator.ViewAnimator
import com.ijk.githubreposearch.R

object LoadView {
    private var loadView: View? = null

    fun dismiss() {
        animHide()
    }

    fun show(view: View?) {
        if (view !is ViewGroup) {
            return
        }
        (loadView?.parent as? ViewGroup)?.removeView(loadView)
        if (loadView == null) {
            val inflater =
                view.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            loadView = inflater.inflate(R.layout.load_view, view, false)
        }
        loadView?.setOnClickListener { dismiss() }
        view.addView(loadView)
        animShow()
    }

    private fun animShow() {
        ViewAnimator
            .animate(loadView?.findViewById(R.id.loadContent))
            .waitForHeight()
            .duration(200)
            .fadeIn()
            .start()
    }

    private fun animHide(callback: (() -> Unit)? = null) {
        callback?.let { it() }
        (loadView?.parent as? ViewGroup)?.removeView(loadView)
    }
}
