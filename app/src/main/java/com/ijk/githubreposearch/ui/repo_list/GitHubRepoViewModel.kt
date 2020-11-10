package com.ijk.githubreposearch.ui.repo_list

import android.view.View
import android.view.ViewGroup
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.ijk.domain.entity.Items
import com.ijk.domain.repository.GitHubRepository
import com.ijk.githubreposearch.R
import com.ijk.githubreposearch.base.BaseViewModel
import com.ijk.githubreposearch.utils.collection_adapter.CollectionAdapter
import com.ijk.githubreposearch.utils.collection_adapter.CollectionAdapterFactory
import com.ijk.githubreposearch.utils.collection_adapter.CollectionDelegate
import com.ijk.githubreposearch.utils.extensions.loadImage
import kotlinx.android.synthetic.main.item_repo.view.*

class GitHubRepoViewModel @ViewModelInject constructor(
    private val gitHubRepository: GitHubRepository
) : BaseViewModel() {

    val isLoadLiveData = MutableLiveData<Boolean>()
    private var repoAdapter: CollectionAdapter<Items, ViewGroup>? = null
    private var itemList: ArrayList<Items> = arrayListOf()

    fun searchRepo(text: String?) {
        itemList.clear()
        if (text.isNullOrEmpty()) return
        launch({ gitHubRepository.getRepoList(text, 15, 1) }, {
            addItem(it.items)
        })

        launch({ gitHubRepository.getRepoList(text, 15, 2) }, {
            addItem(it.items)
        })
    }

    private fun addItem(items: List<Items>) {
        if (itemList.isEmpty()) isLoadLiveData.value = true
        itemList.addAll(items)
        val sortedItems = itemList.sortedBy {
            return@sortedBy it.stargazers_count
        }.reversed()
        repoAdapter?.setItems(sortedItems)
    }

    fun getRepoAdapter(delegate: CollectionDelegate<Items>): CollectionAdapter<Items, ViewGroup> {
        if (repoAdapter == null) repoAdapter = createRepoAdapter(delegate)
        return repoAdapter!!
    }

    private fun createRepoAdapter(delegate: CollectionDelegate<Items>): CollectionAdapter<Items, ViewGroup> {
        return CollectionAdapterFactory.Builder<Items>()
            .setRes(R.layout.item_repo)
            .setBindView(::bindViewAdapter)
            .setDelegate(delegate)
            .create()
    }

    private fun bindViewAdapter(v: View, data: Items, index: Int) {
        try {
            v.txtRepoName.text = data.full_name
            v.txtDescription.text = data.description
            v.txtStars.text = data.stargazers_count.toString()
            v.txtLanguage.text = data.language
            v.imageRepo.loadImage(data.owner.avatar_url)
            v.txtLicense.text = data.license.name
        } catch (e: Exception) {
        }
    }
}