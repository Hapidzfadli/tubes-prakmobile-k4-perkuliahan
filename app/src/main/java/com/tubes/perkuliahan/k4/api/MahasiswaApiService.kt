package com.tubes.perkuliahan.k4.api

import com.tubes.perkuliahan.k4.data.model.Mahasiswa
import okhttp3.ResponseBody
import retrofit2.http.*

interface MahasiswaApiService {
    @GET("mahasiswa")
    suspend fun getMahasiswa(): List<Mahasiswa>

    @GET("mahasiswa/{npm}")
    suspend fun getMahasiswaByNpm(@Path("npm") npm: String): Mahasiswa

    @POST("mahasiswa")
    suspend fun addMahasiswa(@Body mahasiswa: Mahasiswa): ResponseBody

    @PUT("mahasiswa/{npm}")
    suspend fun updateMahasiswa(@Path("npm") npm: String, @Body mahasiswa: Mahasiswa): ResponseBody

    @DELETE("mahasiswa/{npm}")
    suspend fun deleteMahasiswa(@Path("npm") npm: String): ResponseBody
}
