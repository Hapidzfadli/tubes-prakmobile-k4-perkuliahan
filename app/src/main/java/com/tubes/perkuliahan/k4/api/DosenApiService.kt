package com.tubes.perkuliahan.k4.api

import com.tubes.perkuliahan.k4.data.model.Dosen
import okhttp3.ResponseBody
import retrofit2.http.*

interface DosenApiService {
    @GET("dosen")
    suspend fun getDosen(): List<Dosen>

    @GET("dosen/{nidn}")
    suspend fun getDosenById(@Path("nidn") nidn: String): Dosen

    @POST("dosen")
    suspend fun addDosen(@Body dosen: Dosen): ResponseBody

    @PUT("dosen/{nidn}")
    suspend fun updateDosen(@Path("nidn") nidn: String, @Body dosen: Dosen): ResponseBody

    @DELETE("dosen/{nidn}")
    suspend fun deleteDosen(@Path("nidn") nidn: String): ResponseBody
}
