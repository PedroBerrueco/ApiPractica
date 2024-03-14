package com.pberrueco.apipractica.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PracticApi {
    //Parsea los Json a data class
    private val converter = GsonConverterFactory.create()

    //Asigna el nivel de detalle que queremos por consola de las peticiones
    private val logginIntercerptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //Carga el Interceptor
    private val client = OkHttpClient.Builder().addInterceptor(logginIntercerptor).build()

    //Instancia de Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ud5-server.onrender.com/api/v1/") //TODO poner base_url Tiene que terminar siempre en /
        .client(client)
        .addConverterFactory(converter)
        .build()
    //Para Llamar a las peticiones de red del servicio
    val service: PracticService by lazy {
        retrofit.create(PracticService::class.java)
    }
}