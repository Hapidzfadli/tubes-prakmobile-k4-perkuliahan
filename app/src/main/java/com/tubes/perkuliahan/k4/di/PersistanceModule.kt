package com.tubes.perkuliahan.k4.di

import android.app.Application
import androidx.room.Room
import com.tubes.perkuliahan.k4.persistance.AppDatabase
import com.tubes.perkuliahan.k4.persistance.PerkuliahanDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "perkuliahan"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun providePerkuliahanDao(appDatabase: AppDatabase): PerkuliahanDao {
        return appDatabase.perkuliahanDao()
    }
}