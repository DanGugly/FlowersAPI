package com.example.flowersapp

import com.example.flowersapp.model.Flowers
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkApi {
    //Here we do a get call to the server in order to get the Flowers.json
    @GET("feeds/flowers.json")
    fun fetchFlowers(): Single<Flowers>
    //Single refers to a single object of the type array getting from the API
}