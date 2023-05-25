package com.tubes.perkuliahan.k4.networks

import com.skydoves.sandwich.ApiResponse
import com.tubes.perkuliahan.k4.data.model.Mahasiswa
import okhttp3.ResponseBody
import retrofit2.http.*

interface MahasiswaApiService {
    @GET("mahasiswa")
    suspend fun getAllMahasiswa(): ApiResponse<MahasiswaGetResponse>

    @GET("mahasiswa/{id}")
    suspend fun getMahasiswaByNpm(@Path("id") id: String): ApiResponse<MahasiswaSingleGetResponse>

    @POST("mahasiswa")
    @Headers("Content-Type: application/json")
    suspend fun addMahasiswa(@Body mahasiswa: Mahasiswa): ApiResponse<MahasiswaSingleGetResponse>

    @PUT("mahasiswa/{id}")
    @Headers("Content-Type: application/json")
    suspend fun updateMahasiswa(@Path("id") id: String, @Body mahasiswa: Mahasiswa): ApiResponse<MahasiswaSingleGetResponse>

    @DELETE("mahasiswa/{id}")
    suspend fun deleteMahasiswa(@Path("id") id: String): ApiResponse<MahasiswaSingleGetResponse>
}
