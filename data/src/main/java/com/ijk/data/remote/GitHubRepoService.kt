package com.ijk.data.remote

import com.ijk.domain.entity.ReadMy
import com.ijk.domain.entity.RepoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubRepoService {
    @GET("search/repositories")
    suspend fun getRepo(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Response<RepoList>

    @GET("repos/{owner}/{repo}/readme")
    suspend fun getReadMy(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<ReadMy>
}