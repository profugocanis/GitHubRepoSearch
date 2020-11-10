package com.ijk.githubreposearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun dispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}