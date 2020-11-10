package com.ijk.githubreposearch.di

import com.ijk.data.repository.GitHubRepositoryImpl
import com.ijk.domain.repository.GitHubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {
    @Binds
    fun getGitHubRepository(p: GitHubRepositoryImpl): GitHubRepository
}