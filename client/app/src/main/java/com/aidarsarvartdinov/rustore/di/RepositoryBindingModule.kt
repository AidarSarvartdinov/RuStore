package com.aidarsarvartdinov.rustore.di

import com.aidarsarvartdinov.rustore.data.repository.AppNetworkRepository
import com.aidarsarvartdinov.rustore.data.repository.AppRepository
import com.aidarsarvartdinov.rustore.data.repository.DownloadRepository
import com.aidarsarvartdinov.rustore.data.repository.DownloadRepositoryImpl
import com.aidarsarvartdinov.rustore.data.repository.DownloadStateRepository
import com.aidarsarvartdinov.rustore.data.repository.DownloadStateRepositoryImpl
import com.aidarsarvartdinov.rustore.data.repository.InstalledAppsRepository
import com.aidarsarvartdinov.rustore.data.repository.InstalledAppsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindingModule {
    @Binds
    @Singleton
    abstract fun bindDownloadRepository(impl: DownloadRepositoryImpl): DownloadRepository

    @Binds
    @Singleton
    abstract fun bindAppRepository(impl: AppNetworkRepository): AppRepository

    @Binds
    @Singleton
    abstract fun bindInstalledAppsRepository(
        impl: InstalledAppsRepositoryImpl
    ): InstalledAppsRepository

    @Binds
    @Singleton
    abstract fun bindDownloadStateRepository(
        impl: DownloadStateRepositoryImpl
    ): DownloadStateRepository
}