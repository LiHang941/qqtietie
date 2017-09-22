package xyz.lihang.qqtietietie.weex

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.taobao.weex.WXSDKInstance
import java.util.*

/**
 * @author HanikLZ
 * @since 2016/10/13
 */
abstract class WeexActivity : FragmentActivity() {

    lateinit var mWeexSdkInstance: WeexInstance
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWeexSdkInstance = WeexInstance(this)
        onWeexSdkCreated(mWeexSdkInstance)
    }

    override fun onDestroy() {
        super.onDestroy()
        mWeexSdkInstance.destroy()
    }

    abstract fun onPushToStack()
    abstract fun onPopFromStack()
    abstract fun onWeexSdkCreated(sdk: WXSDKInstance)

}

