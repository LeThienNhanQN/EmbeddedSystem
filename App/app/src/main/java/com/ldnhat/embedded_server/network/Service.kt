package com.ldnhat.embedded_server.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ldnhat.embedded_server.common.model.ApiResponse
import com.ldnhat.embedded_server.common.model.Data
import com.ldnhat.embedded_server.utils.constants.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .build()


interface DataApiService {
    @GET("user/data")
    fun userGetAllDataAsync(): Deferred<ApiResponse<List<Data>>>

    @GET("user/data/current")
    fun userGetDataCurrentAsync(): Deferred<ApiResponse<Data>>
}

object DataApi {
    val dataApiService: DataApiService by lazy {
        retrofit.create(DataApiService::class.java)
    }
}
