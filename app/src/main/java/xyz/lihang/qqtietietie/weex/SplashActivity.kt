package xyz.lihang.qqtietietie.weex

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle

import xyz.lihang.qqtietietie.R
import xyz.lihang.qqtietietie.utils.AppUtils
import xyz.lihang.qqtietietie.utils.DialogUtil

/**
 * Created by LiHang on 2017/9/20.
 */

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onResume() {
        super.onResume()
        showMoudleState()
    }

    /**
     * Xposed拦截
     *
     * @return
     */
    private val isModuleActive: Boolean
        get() = false

    private fun showMoudleState() {
        if (!isModuleActive) {
            DialogUtil.showAlertlr(this, getString(R.string.tips), getString(R.string.module_active_tips), getString(R.string.ignore), { dialogInterface, i -> toMain() }, getString(R.string.open_xposed)) { dialogInterface, i -> AppUtils.redirectToXposed(this@SplashActivity) }
        } else {
            toMain()
        }

    }

    private fun toMain() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)

        startActivity(intent)
    }

}
