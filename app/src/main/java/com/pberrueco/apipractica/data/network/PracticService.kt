package com.pberrueco.apipractica.data.network

import com.pberrueco.apipractica.data.network.model.PracticResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PracticService {

    @GET("homeWork/{practice}")
    suspend fun getHomeWork(@Header("Authorization") userName: String, @Path("practice")practice: String) : Response<List<PracticResponse>>
}