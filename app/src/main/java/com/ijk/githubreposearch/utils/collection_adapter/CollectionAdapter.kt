package com.ijk.githubreposearch.utils.collection_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
class CollectionAdapter<T, V : View> constructor(
    private val delegate: CollectionDelegate<T>?
) : RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    lateinit var viewSource: CollectionViewSource<T, V>
    var dataSource: ArrayList<T> = arrayListOf()

    fun addItem(items: List<T>) {
        items.forEach {
            dataSource.add(it)
            this.notifyItemInserted(dataSource.size - 1)
        }
    }

    fun setItems(items: List<T>) {
        dataSource.clear()
        dataSource.addAll(items)
        this.notifyDataSetChanged()
    }

    fun clearData() {
        dataSource = arrayListOf()
        this.notifyDataSetChanged()
    }

    fun addItem(item: T) {
        dataSource.add(item)
        this.notifyItemInserted(dataSource.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return if (viewSource.res != null)
            createHolderByResource(parent)
        else
            createHolderByView(parent)
    }

    private fun createHolderByResource(parent: ViewGroup): CollectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CollectionViewHolder(
            inflater.inflate(viewSource.res ?: 0, parent, false)
        )
    }

    private fun createHolderByView(parent: ViewGroup): CollectionViewHolder {
        val constructor = viewSource.clazz?.constructors?.first()
        val view = constructor?.newInstance(parent.context) as V
        return CollectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val item = dataSource[position]
        viewSource.bindView(holder.itemView, item, position)
        if (delegate == null) return
        holder.itemView.setOnClickListener {
            delegate.onItemClick(item, holder.itemView, position)
        }
    }

    override fun getItemCount() = dataSource.size

    class CollectionViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
