package com.santriprogrammer.retrokotlin.retrofit

import com.santriprogrammer.retrokotlin.model.News
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?country=us&category=business&apiKey=980d8d512d494e2393504db4809c0d25")
    fun getNews() : Flowable<News.Result>

    companion object Factory{
        fun create() : ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://newsapi.org/v2/")
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}