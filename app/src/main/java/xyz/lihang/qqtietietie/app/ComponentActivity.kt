package xyz.lihang.qqtietietie.app

import android.util.Log
import xyz.lihang.qqtietietie.R
import xyz.lihang.qqtietietie.utils.ScreenUtil
import xyz.lihang.qqtietietie.weex.WeexActivity
import xyz.lihang.qqtietietie.weex.WeexEvent
import xyz.lihang.qqtietietie.weex.WeexPageHelper


/**
 * @author HanikLZ
 * @since 2016/10/10
 */
class ComponentActivity : WeexActivity(), com.taobao.weex.IWXRenderListener {
    private var flag:Boolean = false;
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        startLoading()
        mWeexSdkInstance.startGlobalEventListener()
    }

    override fun onWeexSdkCreated(sdk: com.taobao.weex.WXSDKInstance) {
        sdk.registerRenderListener(this)
        loadPage(sdk)
    }
    override fun onViewCreated(instance: com.taobao.weex.WXSDKInstance?, view: android.view.View?) {
        view?.let {
            if (ManagementApplication.debug) {
                setContentViewWithDebug(it)
            } else {
                setContentView(it)
            }
        }
    }
    override fun onRefreshSuccess(instance: com.taobao.weex.WXSDKInstance?, width: Int, height: Int) = Unit
    override fun onRenderSuccess(instance: com.taobao.weex.WXSDKInstance?, width: Int, height: Int) = Unit
    override fun onException(instance: com.taobao.weex.WXSDKInstance?, errCode: String?, msg: String?) {
        android.util.Log.e("component", "errorCode:$errCode message:$msg")
    }

    override fun onPopFromStack() {
        WeexEvent.SHOW.fire(mWeexSdkInstance)
    }

    override fun onPushToStack() {
        WeexEvent.HIDE.fire(mWeexSdkInstance)
    }

    private fun setContentViewWithDebug(viewRes: Int) {
        setContentView(R.layout.activity_debug)
        getContentView().apply { addView(layoutInflater.inflate(viewRes, this, false), 0) }

    }

    private fun setContentViewWithDebug(view: android.view.View) {
        setContentView(R.layout.activity_debug)
        getContentView().addView(view, 0)

    }


    private fun startLoading() {
        setContentView(R.layout.activity_loading)
    }

    private fun reloadPage() {
        startLoading()
        loadPage(mWeexSdkInstance)
    }

    private fun errorPage() {
        if (ManagementApplication.debug) {
            setContentViewWithDebug(R.layout.activity_error)
        } else {
            setContentView(R.layout.activity_error)
        }
        findViewById(R.id.button_reload).setOnClickListener { reloadPage() }
    }

    private fun loadPage(sdk: com.taobao.weex.WXSDKInstance) {
        val url = intent.getStringExtra(WeexPageHelper.ARG_PAGE_PATH) ?: ""
        Log.d("URL",url);
        WeexPageHelper.loadPageTemplate(this, url)
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe({
                    sdk.render(intent.getStringExtra(WeexPageHelper.ARG_PAGE_NAME), it
                            , mapOf(WeexPageHelper.ARG_BUNDLE_URL to url, WeexPageHelper.ARG_DEVICE_TOKEN to ManagementApplication.deviceToken)
                            , intent.getStringExtra(WeexPageHelper.ARG_PAGE_PARAMS)
                            , -1, -1, com.taobao.weex.common.WXRenderStrategy.APPEND_ASYNC)
                }, {
                    errorPage()

        })
    }

    private fun getContentView() = (findViewById(R.id.frame_content) as android.view.ViewGroup)

}

