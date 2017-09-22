package xyz.lihang.qqtietietie.app

import android.os.Bundle
import com.taobao.weex.common.WXRenderStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import xyz.lihang.qqtietietie.weex.WeexFragment
import xyz.lihang.qqtietietie.weex.WeexPageHelper

/**
 * @author HanikLZ
 * @since 2016/10/13
 */
class ComponentFragment : WeexFragment(), com.taobao.weex.IWXRenderListener {

    private var mContentContainer: android.view.ViewGroup? = null

    override fun onWeexSdkCreated(sdk: com.taobao.weex.WXSDKInstance) {
        sdk.registerRenderListener(this)
    }

    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?, savedInstanceState: android.os.Bundle?) = android.widget.FrameLayout(context).apply {
        mContentContainer = this
    }

    override fun onViewCreated(view: android.view.View?, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments.string(WeexPageHelper.ARG_PAGE_PATH)
        WeexPageHelper.loadPageTemplate(context, url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mWeexSdkInstance.render(arguments.string(WeexPageHelper.ARG_PAGE_NAME)
                            , it
                            , mapOf(WeexPageHelper.ARG_BUNDLE_URL to url)
                            , arguments.string(WeexPageHelper.ARG_PAGE_PARAMS)
                            , WXRenderStrategy.APPEND_ASYNC)
                }, { it.printStackTrace() })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mContentContainer = null
    }

    override fun onViewCreated(instance: com.taobao.weex.WXSDKInstance?, view: android.view.View?) {
        mContentContainer?.addView(view)
    }

    override fun onRenderSuccess(instance: com.taobao.weex.WXSDKInstance?, width: Int, height: Int) = Unit
    override fun onException(instance: com.taobao.weex.WXSDKInstance?, errCode: String?, msg: String?) = Unit
    override fun onRefreshSuccess(instance: com.taobao.weex.WXSDKInstance?, width: Int, height: Int) = Unit

    private fun Bundle.string(key: String) = getCharSequence(key, "").toString()

}

