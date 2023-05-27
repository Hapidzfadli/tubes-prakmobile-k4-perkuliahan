package com.tubes.perkuliahan.k4.di

import com.tubes.perkuliahan.k4.networks.DosenApiService
import com.tubes.perkuliahan.k4.networks.MahasiswaApiService
import com.tubes.perkuliahan.k4.networks.MataKuliahApiService
import com.tubes.perkuliahan.k4.persistance.PerkuliahanDao
import com.tubes.perkuliahan.k4.repository.DosenRepository
import com.tubes.perkuliahan.k4.repository.MahasiswaRepository
import com.tubes.perkuliahan.k4.repository.MataKuliahRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideDosenRepository(
        api: DosenApiService,
        dao: PerkuliahanDao
    ): DosenRepository {
        return DosenRepository(api, dao)
    }

    @Provides
    @ViewModelScoped
    fun provideMahasiswaRepository(
        api: MahasiswaApiService,
        dao: PerkuliahanDao
    ): MahasiswaRepository {
        return MahasiswaRepository(api, dao)
    }

    @Provides
    @ViewModelScoped
    fun provideMataKuliahRepository(
        api: MataKuliahApiService,
        dao: PerkuliahanDao
    ): MataKuliahRepository {
        return MataKuliahRepository(api, dao)
    }
}