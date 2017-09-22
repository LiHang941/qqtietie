package xyz.lihang.qqtietietie.weex

import android.content.Context
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import xyz.lihang.qqtietietie.utils.HttpUtils
import java.io.File

/**
 * @author HanikLZ
 * @since 2016/10/13
 */
object WeexPageHelper {

    private const val PROTOCOL_HTTP = "http://"
    private const val PROTOCOL_FILE = "file://"
    private const val PROTOCOL_ASSETS = "assets://"

    const val ARG_PAGE_NAME = "PageName"
    const val ARG_PAGE_PATH = "PagePath"
    const val ARG_PAGE_PARAMS = "PageParams"
    const val ARG_BUNDLE_URL = "bundleUrl"
    const val ARG_DEVICE_TOKEN = "deviceToken"

    fun loadPageTemplate(context: Context, url: String) = Observable.fromCallable {
        url.toLowerCase().run {
            if (startsWith(PROTOCOL_HTTP)) {
                HttpUtils.getAsText(url)
            } else if (startsWith(PROTOCOL_ASSETS)) {
                if(url.indexOf("?")!=-1){
                    context.assets.open(url.substring(0,url.indexOf("?")).substring(PROTOCOL_ASSETS.length)).use {
                        it.bufferedReader().readText()
                    }
                }else{
                    context.assets.open(url.substring(PROTOCOL_ASSETS.length)).use {
                        it.bufferedReader().readText()
                    }
                }

            } else if (startsWith(PROTOCOL_FILE)) {
                File(url.substring(PROTOCOL_FILE.length)).readText()
            } else ""
        }
    }.subscribeOn(Schedulers.io())!!

}

