package xyz.lihang.qqtietietie.weex

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.taobao.weex.appfram.navigator.IActivityNavBarSetter
import xyz.lihang.qqtietietie.app.ComponentActivity
import xyz.lihang.qqtietietie.app.navigation.NavigationManager
import xyz.lihang.qqtietietie.app.navigation.WeexNavigator
import java.util.*

/**
 * @author haniklz
 * @since 16-12-15
 */
class ActivityNavBarSetter(val context: Context) : IActivityNavBarSetter, Application.ActivityLifecycleCallbacks {

    companion object {
        private const val KEY_ID = "qqteitietie_weex_navigator_setter"
        private fun Activity.isNav() = intent?.getBooleanExtra(KEY_ID, false)
    }

    private val mActivityStack = Stack<Activity>()

    override fun onActivityPaused(activity: Activity?) = Unit
    override fun onActivityResumed(activity: Activity?) = Unit
    override fun onActivityStarted(activity: Activity?) = Unit
    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) = Unit
    override fun onActivityStopped(activity: Activity?) = Unit

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        if (activity?.isNav() ?: false) {
            mActivityStack.apply {
                if (isNotEmpty()) { (peek() as? WeexActivity)?.onPushToStack() }
            }.push(activity)
        }
    }
    override fun onActivityDestroyed(activity: Activity?) {
        if (activity?.isNav() ?: false) {
            mActivityStack.apply {
                remove(activity)
                if (isNotEmpty()) { (peek() as? WeexActivity)?.onPopFromStack() }
            }
        }
    }

    override fun clearNavBarLeftItem(param: String?) = true

    override fun clearNavBarMoreItem(param: String?) = true

    override fun setNavBarLeftItem(param: String?) = true

    override fun setNavBarRightItem(param: String?) = true

    override fun clearNavBarRightItem(param: String?) = true

    override fun setNavBarTitle(param: String?) = true

    override fun setNavBarMoreItem(param: String?) = true

    override fun push(param: String?) = (JSON.parse(param) as? JSONObject)?.let {
        NavigationManager.navigateSchema(it.getString("url"), {
            _, protocol, url, params ->
                context.startActivity(Intent(context, ComponentActivity::class.java).apply {
                    putExtra(KEY_ID, true)
                    putExtra(WeexPageHelper.ARG_PAGE_PATH, WeexNavigator.parseUrl(protocol, url, params))
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            true
        })
    }?: false

    override fun pop(param: String?): Boolean {
        if (mActivityStack.isNotEmpty()) {
            ActivityCompat.finishAfterTransition(mActivityStack.peek())
        }
        return true
    }
}
