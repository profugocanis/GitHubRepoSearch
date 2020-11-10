package com.ijk.data.remote

import com.google.gson.GsonBuilder
import com.ijk.domain.gitHubSearchUrl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GitHubRepoSource @Inject constructor() {
    private val client = OkHttpClient().newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private var retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())


    var gitHubRepoService: GitHubRepoService = retrofitBuilder
        .baseUrl(gitHubSearchUrl)
        .build()
        .create(GitHubRepoService::class.java)
}