package com.ijk.data.repository

import com.ijk.data.remote.GitHubRepoSource
import com.ijk.data.remote.safeApiCall
import com.ijk.domain.entity.ReadMy
import com.ijk.domain.entity.RepoList
import com.ijk.domain.entity.RestResult
import com.ijk.domain.repository.GitHubRepository
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val gitHubRepoSource: GitHubRepoSource
) : GitHubRepository {

    override suspend fun getRepoList(text: String, limit: Int, page: Int): RestResult<RepoList> {
        return safeApiCall {
            gitHubRepoSource.gitHubRepoService.getRepo(text, "stars", limit, page)
        }
    }

    override suspend fun getRepoReadMy(owner: String, repoName: String): RestResult<ReadMy> {
        return safeApiCall {
            gitHubRepoSource.gitHubRepoService.getReadMy(owner, repoName)
        }
    }
}