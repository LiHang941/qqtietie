package xyz.lihang.qqtietietie.app.navigation

import android.content.Context
import android.support.v4.app.Fragment

/**
 * @author HanikLZ
 * @since 2016/11/3
 */
object NativeNavigator : NavigationHandler {

    override fun handleNavigate(context: Context, protocol: String, url: String, params: Map<String, String>) {

    }

    override fun createNavigateFragment(protocol: String, url: String, params: Map<String, String>): Fragment? {
        return null
    }

}

