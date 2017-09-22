package xyz.lihang.qqtietietie.weex

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.taobao.weex.WXGlobalEventReceiver
import com.taobao.weex.WXSDKInstance

/**
 * @author HanikLZ
 * @since 2016/12/26
 */
class WeexInstance(context: Context) : WXSDKInstance(context) {

    private val mGlobalEventListener = object : WXGlobalEventReceiver(this) {
        override fun onReceive(c: Context?, intent: Intent?) {
            super.onReceive(c, intent)
            mNestedInstanceEventListeners.forEach { it.onReceive(c, intent) }
        }
    }

    private val mNestedInstanceEventListeners = mutableListOf<WXGlobalEventReceiver>()

    private var mGlobalEventRegistered = false

    init {
        setNestedInstanceInterceptor { wxsdkInstance, nestedContainer ->
            mNestedInstanceEventListeners.add(WXGlobalEventReceiver(wxsdkInstance))
        }
    }

    fun startGlobalEventListener() {
        if (!mGlobalEventRegistered) {
            mGlobalEventRegistered = true
            context.registerReceiver(mGlobalEventListener, IntentFilter(WXGlobalEventReceiver.EVENT_ACTION))
        }
    }

    fun stopGlobalEventListener() {
        if (mGlobalEventRegistered) {
            mGlobalEventRegistered = false
            context.unregisterReceiver(mGlobalEventListener)
        }
    }

    override fun destroy() {
        stopGlobalEventListener()
        mNestedInstanceEventListeners.clear()
        super.destroy()
    }

}
