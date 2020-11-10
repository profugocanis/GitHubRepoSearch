package com.ijk.githubreposearch.utils.collection_adapter

import android.view.View

interface CollectionDelegate<T> {
    fun onItemClick(item: T, view: View, position: Int)
}
