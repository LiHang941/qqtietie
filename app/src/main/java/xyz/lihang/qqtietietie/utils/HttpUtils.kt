package xyz.lihang.qqtietietie.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * @author HanikLZ
 * @since 2016/11/4
 */
object HttpUtils {

    private val httpClient by lazy { OkHttpClient() }

    fun getAsText(url: String) = httpClient.newCall(Request.Builder().url(url).build()).execute().use {
        (if (it.isSuccessful) it.body()?.string() else null) ?: throw IOException("can not load content from url.")
    }

    fun ensureUrlProtocol(url: String) = if (!url.contains("://")) "http://$url" else url
    fun ensureUrlEnd(url: String) = if (url.endsWith('/')) url.substring(0, url.length - 1) else url

    fun contactUrl(baseUrl: String, url: String) = if (url.contains("://")) url else ensureUrlProtocol("${ensureUrlEnd(baseUrl)}/$url")

}

