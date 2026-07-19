package com.aidarsarvartdinov.rustore.di

import android.content.Context
import com.aidarsarvartdinov.rustore.data.datastore.DataStoreManager
import com.aidarsarvartdinov.rustore.data.repository.AppRepository
import com.aidarsarvartdinov.rustore.data.repository.MockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppRepository(): AppRepository {
        return MockRepository()
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}
