package xyz.lihang.qqtietietie.weex.adapter

import android.net.Uri
import com.taobao.weex.WXSDKInstance
import com.taobao.weex.adapter.URIAdapter
import xyz.lihang.qqtietietie.utils.HttpUtils
import xyz.lihang.qqtietietie.weex.ConfigurationProvider

/**
 * @author HanikLZ
 * @since 2016/12/24
 */
class MyURIAdapter : URIAdapter {

    override fun rewrite(instance: WXSDKInstance?, type: String?, uri: Uri) = if (uri.isRelative) {
        Uri.parse(HttpUtils.contactUrl(ConfigurationProvider.weexAppUrl, uri.toString())) ?: uri
    } else uri

}


