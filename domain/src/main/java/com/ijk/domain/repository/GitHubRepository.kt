package com.ijk.domain.repository

import com.ijk.domain.entity.ReadMy
import com.ijk.domain.entity.RepoList
import com.ijk.domain.entity.RestResult

interface GitHubRepository {
    suspend fun getRepoList(text: String, limit: Int, page: Int): RestResult<RepoList>
    suspend fun getRepoReadMy(owner: String, repoName: String): RestResult<ReadMy>
}