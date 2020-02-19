package com.jiyoung.idiustest

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jiyoung.idiustest.model.*
import com.jiyoung.idiustest.server.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : BaseObservable() {
    val networkService = RetrofitFactory.create()
    val weatherList: ArrayList<ItemVO>
    val weatherBinderAdapter: WeatherBinderAdapter
    val resultLiveData: MutableLiveData<Boolean>
    var refresh = false
    var progress = true

    init {
        weatherList = ArrayList()
        weatherBinderAdapter = WeatherBinderAdapter()
        resultLiveData = MutableLiveData()
    }

    var isRefreshing
        @Bindable
        get() = refresh
        set(value) {
            refresh = value
            notifyPropertyChanged(BR.isRefreshing)
        }

    var isShowProgress
    @Bindable
    get() = progress
    set(value){
        progress = value
        notifyPropertyChanged(BR.isShowProgress)
    }

    @Bindable
    fun getIsRefreshing() : Boolean{
        return isRefreshing
    }

    @Bindable
    fun getIsShowProgress() :Boolean {
        return isShowProgress
    }

    @Bindable
    fun getWeatherAdapter(): WeatherBinderAdapter {
        return weatherBinderAdapter
    }

    fun setWeatherAdapter(weatherAdapter : WeatherBinderAdapter){
        weatherBinderAdapter.notifyDataSetChanged()
    }

    @Bindable
    fun getOnRefreshListener(): SwipeRefreshLayout.OnRefreshListener {
        return SwipeRefreshLayout.OnRefreshListener {
            weatherBinderAdapter.clear()
            weatherList.clear()
            getWeather()
        }
    }

    fun getWeather(): MutableLiveData<Boolean> {
        networkService.getWeather("se").enqueue(object : Callback<List<WeatherItem>> {
            override fun onFailure(call: Call<List<WeatherItem>>, t: Throwable) {
                resultLiveData.postValue(false)
            }

            override fun onResponse(
                call: Call<List<WeatherItem>>,
                response: Response<List<WeatherItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        getWeatherState(it)
                    }
                } else {
                    resultLiveData.postValue(false)
                }
            }

        })
        return resultLiveData
    }

    fun getWeatherState(weatherItems: List<WeatherItem>) {
        for (item in weatherItems) {
            networkService.getState(item.woeid).enqueue(object : Callback<WeatherState> {
                override fun onFailure(call: Call<WeatherState>, t: Throwable) {
                    weatherList.add(DataItem(item, WeatherState()))
                    Log.e("TAG", "ERROR")
                    if (weatherList.size == weatherItems.size) {
                        resultLiveData.postValue(true)
                        weatherList.add(0, HeaderItem())
                        weatherBinderAdapter.set(weatherList)
                        isRefreshing = false
                        isShowProgress = false
                    }
                }

                override fun onResponse(
                    call: Call<WeatherState>,
                    response: Response<WeatherState>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            weatherList.add(DataItem(item, it))
                        }
                    } else {
                        weatherList.add(DataItem(item, WeatherState()))
                    }

                    if (weatherList.size == weatherItems.size) {
                        resultLiveData.postValue(true)
                        weatherList.add(0, HeaderItem())
                        weatherBinderAdapter.set(weatherList)
                        isRefreshing = false
                        isShowProgress = false
                    }
                }
            })
        }
    }
}