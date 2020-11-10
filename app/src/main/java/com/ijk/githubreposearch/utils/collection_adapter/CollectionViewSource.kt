package com.ijk.githubreposearch.utils.collection_adapter

import android.view.View
import androidx.annotation.LayoutRes

class CollectionViewSource<T, V : View> {

    var clazz: Class<V>? = null

    @LayoutRes
    var res: Int? = null

    constructor(clazz: Class<V>? = null) {
        this.clazz = clazz
    }

    constructor(@LayoutRes res: Int? = null) {
        this.res = res
    }

    var bindView: (v: View, data: T, position: Int) -> Unit = { _, _, _ -> }
}
