package com.ijk.githubreposearch.ui.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.tiagohm.markdownview.css.styles.Github
import br.tiagohm.markdownview.js.JavaScript
import com.ijk.data.loget
import com.ijk.domain.entity.Items
import com.ijk.githubreposearch.R
import com.ijk.githubreposearch.base.BaseFragment
import com.ijk.githubreposearch.utils.LoadView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_repo.*
import kotlinx.android.synthetic.main.fragment_repo.view.*

@AndroidEntryPoint
class RepoFragment : BaseFragment() {
    override fun obtainLayoutResId() = R.layout.fragment_repo
    private val repoViewModel: RepoViewModel by viewModels()
    private var repo: Items? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initMarkDown()
        initToolBar()
        initLiveData()
    }

    private fun initLiveData() {
        LoadView.show(requireView())
        repoViewModel.setFragment(this)
        repoViewModel.loadReadMy(repo?.owner?.login ?: "", repo?.name ?: "").observe(
            viewLifecycleOwner
        ) {
            LoadView.dismiss()
            markdownView.loadMarkdownFromUrl(it.download_url)
            loget("Loadet")
        }
    }

    private fun initView() {
        repo = arguments?.getParcelable(itemsKey)
    }

    private fun initMarkDown() {
        markdownView.addStyleSheet(Github())
    }

    private fun initToolBar() {
        mainActivity.supportActionBar?.title = repo?.name
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        const val itemsKey = "ItemsKey"
    }
}