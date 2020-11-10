package com.ijk.githubreposearch.ui.repo_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ijk.domain.entity.Items
import com.ijk.githubreposearch.R
import com.ijk.githubreposearch.base.BaseFragment
import com.ijk.githubreposearch.ui.repo.RepoFragment
import com.ijk.githubreposearch.utils.LoadView
import com.ijk.githubreposearch.utils.collection_adapter.CollectionDelegate
import com.ijk.githubreposearch.utils.extensions.setSubmitListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_git_hub_repo_list.*

@AndroidEntryPoint
class GitHubRepoListFragment : BaseFragment(), CollectionDelegate<Items> {
    override fun obtainLayoutResId() = R.layout.fragment_git_hub_repo_list
    private val gitHubRepoViewModel: GitHubRepoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initLiveData()
        initView()
    }

    private fun initView() {
        mainActivity.supportActionBar?.setTitle(R.string.app_name)
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        repoSearchView.setSubmitListener {
            LoadView.show(requireView())
            gitHubRepoViewModel.searchRepo(it)
        }
    }

    private fun initLiveData() {
        gitHubRepoViewModel.setFragment(this)
        gitHubRepoViewModel.isLoadLiveData.observe(viewLifecycleOwner) {
            LoadView.dismiss()
            imageLogo.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        repoRecyclerView.adapter = gitHubRepoViewModel.getRepoAdapter(this)
    }

    override fun onItemClick(item: Items, view: View, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable(RepoFragment.itemsKey, item)
        navGoTo(R.id.repoFragment, bundle)
    }
}