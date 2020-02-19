package com.jiyoung.idiustest.model

import com.google.gson.annotations.SerializedName

class ConsolidatedWeather {
    @SerializedName("id")
    val id: Long = 0
    @SerializedName("weather_state_name")
    val weatherStateName: String = ""
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String = ""
    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String = ""
    @SerializedName("created")
    val created: String = ""
    @SerializedName("applicable_date")
    val applicableDate: String = ""
    @SerializedName("min_temp")
    val minTemp: Double = 0.0
    @SerializedName("max_temp")
    val maxTemp: Double = 0.0
    @SerializedName("the_temp")
    val theTemp: Double = 0.0
    @SerializedName("wind_speed")
    val windSpeed: Double = 0.0
    @SerializedName("wind_direction")
    val windDirection: Double = 0.0
    @SerializedName("air_pressure")
    val airPressure: Double = 0.0
    @SerializedName("humidity")
    val humidity: Int = 0
    @SerializedName("visibility")
    val visibility: Double = 0.0
    @SerializedName("predictability")
    val predictability: Int = 0
}