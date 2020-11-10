package com.ijk.githubreposearch.utils.collection_adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

object CollectionAdapterFactory {
    fun <T, V : View> create(
        dataSource: List<T>,
        viewSource: CollectionViewSource<T, V>,
        delegate: CollectionDelegate<T>?
    ): CollectionAdapter<T, V> {
        val adapter: CollectionAdapter<T, V> =
            CollectionAdapter(delegate)
        adapter.dataSource.addAll(dataSource)
        adapter.viewSource = viewSource
        return adapter
    }

    fun <T, V : View> create(
        list: List<T>,
        @LayoutRes resView: Int,
        bindView: (v: View, data: T, position: Int) -> Unit,
        callback: (item: T, view: View, position: Int) -> Unit
    ): CollectionAdapter<T, V> {
        val delegate = object :
            CollectionDelegate<T> {
            override fun onItemClick(item: T, view: View, position: Int) {
                callback(item, view, position)
            }
        }
        val adapter: CollectionAdapter<T, V> =
            CollectionAdapter(delegate)
        val viewSource =
            CollectionViewSource<T, V>(
                resView
            )
        viewSource.bindView = bindView
        adapter.dataSource.addAll(list)
        adapter.viewSource = viewSource
        return adapter
    }

    class Builder<T> {
        private var resView: Int = 0
        private var list: List<T> = listOf()
        private lateinit var bindView: (v: View, data: T, position: Int) -> Unit
        private var callback: ((item: T, view: View, position: Int) -> Unit)? = null
        private var delegate: CollectionDelegate<T>? = null

        fun setRes(@LayoutRes resView: Int): Builder<T> {
            this.resView = resView
            return this
        }

        fun setList(list: List<T>): Builder<T> {
            this.list = list
            return this
        }

        fun setBindView(bindView: (v: View, data: T, position: Int) -> Unit): Builder<T> {
            this.bindView = bindView
            return this
        }

        fun setCallback(callback: (item: T, view: View, position: Int) -> Unit): Builder<T> {
            this.callback = callback
            return this
        }

        fun setDelegate(delegate: CollectionDelegate<T>?): Builder<T> {
            this.delegate = delegate
            return this
        }

        fun create(): CollectionAdapter<T, ViewGroup> {
            if (delegate == null) {
                delegate = object :
                    CollectionDelegate<T> {
                    override fun onItemClick(item: T, view: View, position: Int) {
                        callback?.invoke(item, view, position)
                    }
                }
            }
            val adapter: CollectionAdapter<T, ViewGroup> =
                CollectionAdapter(delegate)
            val viewSource =
                CollectionViewSource<T, ViewGroup>(resView)
            viewSource.bindView = bindView
            adapter.dataSource.addAll(list)
            adapter.viewSource = viewSource
            return adapter
        }
    }
}
