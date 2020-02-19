package com.jiyoung.idiustest.model

abstract class ItemVO {
    abstract val type : Int
    companion object {
        val TYPE_HEADER = 0
        val TYPE_DATA = 1
    }
}

class HeaderItem : ItemVO() {
    override val type: Int
        get() = TYPE_HEADER
}

class DataItem(val item: WeatherItem, val state : WeatherState) : ItemVO() {
    override val type: Int
        get() = TYPE_DATA
}
