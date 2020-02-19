package com.jiyoung.idiustest.model

import com.google.gson.annotations.SerializedName

class WeatherItem {
    @SerializedName("title")
    var title: String = ""
    @SerializedName("location_type")
    var locationType: String = ""
    @SerializedName("woeid")
    var woeid: Int = 0
    @SerializedName("latt_long")
    var lattLong: String = ""
}