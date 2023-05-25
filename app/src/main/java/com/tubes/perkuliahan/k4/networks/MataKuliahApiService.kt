package com.tubes.perkuliahan.k4.networks

import com.skydoves.sandwich.ApiResponse
import com.tubes.perkuliahan.k4.model.MataKuliah
import okhttp3.ResponseBody
import retrofit2.http.*

interface MataKuliahApiService {
    @GET("matakuliah")
    suspend fun getAllMataKuliah(): ApiResponse<MataKuliahGetResponse>
    @GET("matakuliah/{kode}")
    suspend fun getMataKuliahByKode(@Path("kode") kode: String): ApiResponse<MataKuliahSingleGetResponse>

    @POST("matakuliah")
    @Headers("Content-Type: application/json")
    suspend fun addMataKuliah(@Body mataKuliah: MataKuliah): ApiResponse<MataKuliahSingleGetResponse>

    @PUT("matakuliah/{kode}")
    @Headers("Content-Type: application/json")
    suspend fun updateMataKuliah(@Path("kode") kode: String, @Body mataKuliah: MataKuliah): ApiResponse<MataKuliahSingleGetResponse>

    @DELETE("matakuliah/{kode}")
    suspend fun deleteMataKuliah(@Path("kode") kode: String): ApiResponse<MataKuliahSingleGetResponse>
}
