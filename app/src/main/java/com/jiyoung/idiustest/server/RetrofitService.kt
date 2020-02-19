package com.jiyoung.idiustest.server

import com.jiyoung.idiustest.model.WeatherItem
import com.jiyoung.idiustest.model.WeatherState
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/api/location/search/")
    fun getWeather(@Query("query") q : String) : Call<List<WeatherItem>>

    @GET("/api/location/{woeid}/")
    fun getState(@Path("woeid") woeid: Int) : Call<WeatherState>
}