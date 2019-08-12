package com.techdining.www.doordashtakehome.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.techdining.www.doordashtakehome.models.RestaurantsNearBy
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NearByRestaurantsApiService {
    @GET("v2/restaurant/")
    fun nearByRestaurantSearchQuery(
        @Query("lat") lat: String,
        @Query("lng") lng: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Observable<List<RestaurantsNearBy>>

    companion object {
        fun create(): NearByRestaurantsApiService {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addNetworkInterceptor(StethoInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.doordash.com/")
                .build()

            return retrofit.create(NearByRestaurantsApiService::class.java)
        }
    }
}