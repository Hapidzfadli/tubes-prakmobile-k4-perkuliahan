package com.tubes.perkuliahan.k4.networks

import com.tubes.perkuliahan.k4.model.MataKuliah
import okhttp3.ResponseBody
import retrofit2.http.*

interface MataKuliahApiService {
    @GET("matakuliah")
    suspend fun getMataKuliah(): List<MataKuliah>

    @GET("matakuliah/{kode}")
    suspend fun getMataKuliahByKode(@Path("kode") kode: String): MataKuliah

    @POST("matakuliah")
    suspend fun addMataKuliah(@Body mataKuliah: MataKuliah): ResponseBody

    @PUT("matakuliah/{kode}")
    suspend fun updateMataKuliah(@Path("kode") kode: String, @Body mataKuliah: MataKuliah): ResponseBody

    @DELETE("matakuliah/{kode}")
    suspend fun deleteMataKuliah(@Path("kode") kode: String): ResponseBody
}
