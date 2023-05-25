package com.tubes.perkuliahan.k4.networks

import com.skydoves.sandwich.ApiResponse
import com.tubes.perkuliahan.k4.data.model.Mahasiswa
import okhttp3.ResponseBody
import retrofit2.http.*

interface MahasiswaApiService {
    @GET("mahasiswa")
    suspend fun getAllMahasiswa(): ApiResponse<MahasiswaGetResponse>

    @GET("mahasiswa/{npm}")
    suspend fun getMahasiswaByNpm(@Path("npm") npm: String): ApiResponse<MahasiswaSingleGetResponse>

    @POST("mahasiswa")
    @Headers("Content-Type: application/json")
    suspend fun addMahasiswa(@Body mahasiswa: Mahasiswa): ApiResponse<MahasiswaSingleGetResponse>

    @PUT("mahasiswa/{npm}")
    @Headers("Content-Type: application/json")
    suspend fun updateMahasiswa(@Path("npm") npm: String, @Body mahasiswa: Mahasiswa): ApiResponse<MahasiswaSingleGetResponse>

    @DELETE("mahasiswa/{npm}")
    suspend fun deleteMahasiswa(@Path("npm") npm: String): ApiResponse<MahasiswaSingleGetResponse>
}
