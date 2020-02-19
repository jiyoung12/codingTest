package com.jiyoung.idiustest.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.jiyoung.idiustest.BuildConfig
import com.jiyoung.idiustest.BuildConfig.BASE_URL
import com.jiyoung.idiustest.R
import com.jiyoung.idiustest.WeatherBinderAdapter
import com.jiyoung.idiustest.server.RetrofitFactory

object WeatherBindingUtil {

    @BindingAdapter("bind:setWeatherAdapter")
    @JvmStatic
    fun setWeatherAdapter(recyclerView: RecyclerView, adapter: WeatherBinderAdapter) {
        recyclerView.adapter = adapter
    }

    @BindingAdapter(value = ["bind:onRefreshListener", "bind:isRefreshing"], requireAll = false)
    @JvmStatic
    fun setOnRefreshListener(
        swipeRefreshLayout: SwipeRefreshLayout,
        onRefreshListener: SwipeRefreshLayout.OnRefreshListener,
        isRefresh: Boolean
    ) {
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener)
        swipeRefreshLayout.isRefreshing = isRefresh
    }

    @BindingAdapter("bind:setWeatherIcon")
    @JvmStatic
    fun setWeatherIcon(view: ImageView, icon: String) {
        Glide.with(view.context)
            .load(String.format("$BASE_URL/static/img/weather/png/64/$icon.png"))
            .into(view)
    }

    @BindingAdapter("bind:setTemperature")
    @JvmStatic
    fun setTemperature(view: TextView, temp: Double) {
        val fullText = "${Math.round(temp)}℃"
        val textColor = if (temp > 0) {
            R.color.C_BE0410
        } else {
            R.color.C_3B86FF
        }
        view.setText(
            TextUtil.getSectionOfTextBoldColor(
                view.context,
                textColor,
                fullText,
                String.format("${Math.round(temp)}")
            )
        )
    }

    @BindingAdapter("bind:setHumidity")
    @JvmStatic
    fun setHumidity(view: TextView, hmdt: Int) {
        val fullText = "$hmdt%"
        val textColor = R.color.C_121212
        view.setText(
            TextUtil.getSectionOfTextBoldColor(
                view.context,
                textColor,
                fullText,
                String.format("$hmdt")
            )
        )
    }
}
