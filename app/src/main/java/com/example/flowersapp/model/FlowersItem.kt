package com.example.flowersapp.model


import com.google.gson.annotations.SerializedName

// This data class is our POJO (Plain java object)? parsed from JSON
data class FlowersItem(
    //Gson library serializing the values from JSON
    @SerializedName("category")
    val category: String,
    //serialized name or name of the property has to be the same as the key from json object
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("productId")
    val productId: Int
)