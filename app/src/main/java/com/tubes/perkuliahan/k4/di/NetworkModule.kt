package com.tubes.perkuliahan.k4.di

import android.content.Context
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.tubes.perkuliahan.k4.networks.DosenApiService
import com.tubes.perkuliahan.k4.networks.MahasiswaApiService
import com.tubes.perkuliahan.k4.networks.MataKuliahApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context:
                            Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(
                "https://ppm-api.gusdya.net/api/"
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun  provideDosenApiService(retrofit: Retrofit):
            DosenApiService {
        return  retrofit.create(DosenApiService::class.java)
    }

    @Provides
    @Singleton
    fun  provideMahasiswaApiService(retrofit: Retrofit):
            MahasiswaApiService {
        return  retrofit.create(MahasiswaApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMataKuliahApiService(retrofit: Retrofit):
            MataKuliahApiService {
        return retrofit.create(MataKuliahApiService::class.java)
    }
}