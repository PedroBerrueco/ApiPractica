package com.pberrueco.apipractica.data.network.model


import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("class")
    val classX: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("title")
    val title: String
)