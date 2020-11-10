package com.ijk.githubreposearch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ijk.githubreposearch.R
import com.ijk.githubreposearch.ui.MainActivity

abstract class BaseFragment : Fragment() {

    @LayoutRes
    abstract fun obtainLayoutResId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(obtainLayoutResId(), container, false)
    }

    fun navGoTo(resId: Int, args: Bundle? = null, isAnimate: Boolean = true) {
        val navOptions = NavOptions.Builder()
        if (isAnimate) {
            navOptions
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
        }
        findNavController().navigate(resId, args, navOptions.build())
    }

    val mainActivity: MainActivity
        get() {
            return requireActivity() as MainActivity
        }
}