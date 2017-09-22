package xyz.lihang.qqtietietie.app.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import xyz.lihang.qqtietietie.app.ComponentActivity
import xyz.lihang.qqtietietie.app.ComponentFragment
import xyz.lihang.qqtietietie.utils.HttpUtils
import xyz.lihang.qqtietietie.weex.ConfigurationProvider
import xyz.lihang.qqtietietie.weex.WeexPageHelper

/**
 * @author HanikLZ
 * @since 2016/11/3
 */
object WeexNavigator : NavigationHandler {

    private fun Map<String, String>.toQueryString() = StringBuilder().apply {
        entries.forEach { append("${it.key}=${it.value}").append('&') }
        if (length > 0) setLength(length - 1)
    }.toString()

    private fun String.lastSegment(splitChar: Char = '/') = split(splitChar).lastOrNull() ?: this

    fun parseUrl(protocol: String, url: String, query: Map<String, String>? = null) : String {
        val outUrl = if (protocol.isEmpty()) HttpUtils.contactUrl(ConfigurationProvider.weexAppUrl, url) else "$protocol://$url"
        return if (query != null && query.isNotEmpty()) "$outUrl?${query.toQueryString()}" else outUrl
    }

    override fun handleNavigate(context: Context, protocol: String, url: String, params: Map<String, String>) {
        context.startActivity(Intent(context, ComponentActivity::class.java).apply {
            putExtra(WeexPageHelper.ARG_PAGE_NAME, url.lastSegment())
            putExtra(WeexPageHelper.ARG_PAGE_PATH, parseUrl(protocol, url, params))
        })
    }

    override fun createNavigateFragment(protocol: String, url: String, params: Map<String, String>) = ComponentFragment().apply {
        arguments = Bundle().apply {
            putCharSequence(WeexPageHelper.ARG_PAGE_NAME, url.lastSegment())
            putCharSequence(WeexPageHelper.ARG_PAGE_PATH, parseUrl(protocol, url, params))
        }
    }
}

