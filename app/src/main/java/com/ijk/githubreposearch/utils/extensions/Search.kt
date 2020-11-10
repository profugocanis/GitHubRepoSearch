package com.ijk.githubreposearch.utils.extensions

import android.widget.SearchView

fun SearchView.setSubmitListener(submit: (String?) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            submit(p0)
            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            return false
        }
    })
}