package com.tubes.perkuliahan.k4.api
import com.tubes.perkuliahan.k4.data.model.Mahasiswa
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ResponseBody

object MahasiswaApiClient {
    private val retrofitService: MahasiswaApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://ppm-api.gusdya.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MahasiswaApiService::class.java)
    }

    suspend fun getMahasiswa(): List<Mahasiswa> {
        return retrofitService.getMahasiswa()
    }

    suspend fun getMahasiswaByNpm(npm: String): Mahasiswa {
        return retrofitService.getMahasiswaByNpm(npm)
    }

    suspend fun addMahasiswa(mahasiswa: Mahasiswa): ResponseBody {
        return retrofitService.addMahasiswa(mahasiswa)
    }

    suspend fun updateMahasiswa(npm: String, mahasiswa: Mahasiswa): ResponseBody {
        return retrofitService.updateMahasiswa(npm, mahasiswa)
    }

    suspend fun deleteMahasiswa(npm: String): ResponseBody {
        return retrofitService.deleteMahasiswa(npm)
    }
}
