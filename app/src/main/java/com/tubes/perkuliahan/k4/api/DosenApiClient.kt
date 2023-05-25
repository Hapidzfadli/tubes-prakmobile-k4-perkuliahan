package com.tubes.perkuliahan.k4.api

import com.tubes.perkuliahan.k4.data.model.Dosen
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DosenApiClient {
    private val retrofitService: DosenApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://ppm-api.gusdya.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DosenApiService::class.java)
    }

    suspend fun getDosen():   List<Dosen> {
        return retrofitService.getDosen()
    }

    suspend fun getDosenById(nidn: String): Dosen {
        return retrofitService.getDosenById(nidn)
    }

    suspend fun addDosen(dosen: Dosen): ResponseBody {
        return retrofitService.addDosen(dosen)
    }

    suspend fun updateDosen(nidn: String, dosen: Dosen): ResponseBody {
        return retrofitService.updateDosen(nidn, dosen)
    }

    suspend fun deleteDosen(nidn: String): ResponseBody {
        return retrofitService.deleteDosen(nidn)
    }
}
