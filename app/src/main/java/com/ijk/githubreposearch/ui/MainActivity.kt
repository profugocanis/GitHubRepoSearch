package com.ijk.githubreposearch.ui

import android.os.Bundle
import androidx.navigation.findNavController
import com.ijk.githubreposearch.R
import com.ijk.githubreposearch.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun obtainLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            findNavController(R.id.hostFragment).popBackStack()
        }
    }
}