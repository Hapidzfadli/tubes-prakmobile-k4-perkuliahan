package com.tubes.perkuliahan.k4.networks

import com.skydoves.sandwich.ApiResponse
import com.tubes.perkuliahan.k4.data.model.Dosen
import okhttp3.ResponseBody
import retrofit2.http.*

interface DosenApiService {
    @GET("dosen")
    suspend fun all(): ApiResponse<DosenGetResponse>

    @GET("dosen/{id}")
    suspend fun find(@Path("id") id: String): ApiResponse<DosenSingleGetResponse>

    @POST("dosen")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Dosen): ApiResponse<DosenSingleGetResponse>

    @PUT("dosen/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String, @Body item: Dosen): ApiResponse<DosenSingleGetResponse>

    @DELETE("dosen/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<DosenSingleGetResponse>
}
