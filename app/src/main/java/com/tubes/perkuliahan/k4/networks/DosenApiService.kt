package com.tubes.perkuliahan.k4.networks

import com.skydoves.sandwich.ApiResponse
import com.tubes.perkuliahan.k4.data.model.Dosen
import okhttp3.ResponseBody
import retrofit2.http.*

interface DosenApiService {
    @GET("dosen")
    suspend fun all(): ApiResponse<DosenGetResponse>

    @GET("dosen/{nidn}")
    suspend fun find(@Path("nidn") id: String): ApiResponse<DosenSingleGetResponse>

    @POST("dosen")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Dosen): ApiResponse<DosenSingleGetResponse>

    @PUT("dosen/{nidn}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("nidn") pathId: String, @Body item: Dosen): ApiResponse<DosenSingleGetResponse>

    @DELETE("dosen/{nidn}")
    suspend fun delete(@Path("nidn") id: String): ApiResponse<DosenSingleGetResponse>
}
