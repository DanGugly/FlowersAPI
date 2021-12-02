package com.example.flowersapp.rest

import com.example.flowersapp.NetworkApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestApi {
    //Here we create our retrofit instance to build the networkAPI
    public val retrofit by lazy{
        Retrofit.Builder()
                //We provide baseurl
            .baseUrl(BASE_URL)
                //Provide gson converter to convert json to pojo class
            .addConverterFactory(GsonConverterFactory.create())
                //Provide rxjava2 call adapter to transform our response into reactive
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //build our retrofit object
            .build()
            .create(NetworkApi::class.java)
    }
    companion object{
        //Best practise to have baseurl(endpoint) provided in static variable
        private const val BASE_URL = "http://services.hanselandpetal.com/"
    }
}