package com.jiyoung.idiustest.model

import com.google.gson.annotations.SerializedName


class WeatherState {

    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather> = mutableListOf()
    @SerializedName("time")
    val time: String = ""
    @SerializedName("sun_rise")
    val sunRise: String = ""
    @SerializedName("sun_set")
    val sunSet: String = ""
    @SerializedName("timezone_name")
    val timezoneName: String = ""
    @SerializedName("title")
    val title: String = ""
    @SerializedName("location_type")
    val locationType: String = ""
    @SerializedName("woeid")
    val woeid: Int = 0
    @SerializedName("latt_long")
    val lattLong: String = ""
    @SerializedName("timezone")
    val timezone: String = ""
}