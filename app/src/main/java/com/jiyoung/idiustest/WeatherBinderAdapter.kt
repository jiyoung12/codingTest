package com.jiyoung.idiustest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jiyoung.idiustest.databinding.ItemWeatherBinding
import com.jiyoung.idiustest.model.DataItem
import com.jiyoung.idiustest.model.ItemVO

class WeatherBinderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var weathers: ArrayList<ItemVO> = ArrayList()

    fun set(items : ArrayList<ItemVO>){
        weathers.clear()
        weathers.addAll(items)
        notifyDataSetChanged()
    }

    fun add(item : ItemVO){
        weathers.add(item)
    }

    fun clear(){
        weathers.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ItemVO.TYPE_HEADER) {
            val layoutInflater = LayoutInflater.from(parent.context)
            return HeaderViewHolder(layoutInflater.inflate(R.layout.header_weather, parent, false))
        } else {
            val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return WeatherViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = weathers[position]
        if(item.type == ItemVO.TYPE_DATA) {
            with(holder as WeatherViewHolder){
                binding.item = item as DataItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return weathers[position].type
    }

    class WeatherViewHolder(val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root)

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)
}