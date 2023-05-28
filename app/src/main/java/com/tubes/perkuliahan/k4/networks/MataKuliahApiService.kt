package com.tubes.perkuliahan.k4.networks

import com.skydoves.sandwich.ApiResponse
import com.tubes.perkuliahan.k4.model.MataKuliah
import okhttp3.ResponseBody
import retrofit2.http.*

interface MataKuliahApiService {
    @GET("matakuliah")
    suspend fun getAllMataKuliah(): ApiResponse<MataKuliahGetResponse>

    @GET("matakuliah/{id}")
    suspend fun getMataKuliahByKode(@Path("id") id: String): ApiResponse<MataKuliahSingleGetResponse>

    @POST("matakuliah")
    @Headers("Content-Type: application/json")
    suspend fun addMataKuliah(@Body mataKuliah: MataKuliah): ApiResponse<MataKuliahSingleGetResponse>

    @PUT("matakuliah/{id}")
    @Headers("Content-Type: application/json")
    suspend fun updateMataKuliah(@Path("id") id: String, @Body mataKuliah: MataKuliah): ApiResponse<MataKuliahSingleGetResponse>

    @DELETE("matakuliah/{id}")
    suspend fun deleteMataKuliah(@Path("id") kode: String): ApiResponse<MataKuliahSingleGetResponse>
}
