package com.ijk.githubreposearch.ui.repo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ijk.domain.entity.ReadMy
import com.ijk.domain.repository.GitHubRepository
import com.ijk.githubreposearch.base.BaseViewModel

class RepoViewModel @ViewModelInject constructor(
    private val gitHubRepository: GitHubRepository
) : BaseViewModel() {

    fun loadReadMy(owner: String, repo: String): LiveData<ReadMy> {
        val res = MutableLiveData<ReadMy>()
        launch({ gitHubRepository.getRepoReadMy(owner, repo) }, {
            res.value = it
        })
        return res
    }
}