package xyz.lihang.qqtietietie.weex

import android.content.Context

/**
 * @author HanikLZ
 * @since 2016/11/17
 */
object ConfigurationProvider {
    var weexAppUrl = "assets://"
    //var weexAppUrl = "http://192.168.32.1:8888/qq/"
        set(value) { field = if (value.isEmpty()) field else value }


}

