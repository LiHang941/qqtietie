package xyz.lihang.qqtietietie.weex

import android.os.Bundle
import android.support.v4.app.Fragment
import com.taobao.weex.WXSDKInstance

/**
 * @author HanikLZ
 * @since 2016/10/13
 */
abstract class WeexFragment : Fragment() {

    lateinit var mWeexSdkInstance: WeexInstance
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWeexSdkInstance = WeexInstance(context)
        onWeexSdkCreated(mWeexSdkInstance)
    }

    override fun onDestroy() {
        super.onDestroy()
        mWeexSdkInstance.destroy()
    }

    abstract fun onWeexSdkCreated(sdk: WXSDKInstance)

}

