package com.tubes.perkuliahan.k4.api
import com.tubes.perkuliahan.k4.data.model.MataKuliah
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ResponseBody

object MataKuliahApiClient {
    private val retrofitService: MataKuliahApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://ppm-api.gusdya.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MataKuliahApiService::class.java)
    }

    suspend fun getMataKuliah(): List<MataKuliah> {
        return retrofitService.getMataKuliah()
    }

    suspend fun getMataKuliahByKode(kode: String): MataKuliah {
        return retrofitService.getMataKuliahByKode(kode)
    }

    suspend fun addMataKuliah(mataKuliah: MataKuliah): ResponseBody {
        return retrofitService.addMataKuliah(mataKuliah)
    }

    suspend fun updateMataKuliah(kode: String, mataKuliah: MataKuliah): ResponseBody {
        return retrofitService.updateMataKuliah(kode, mataKuliah)
    }

    suspend fun deleteMataKuliah(kode: String): ResponseBody {
        return retrofitService.deleteMataKuliah(kode)
    }
}
