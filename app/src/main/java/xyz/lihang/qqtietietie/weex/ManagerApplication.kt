package xyz.lihang.qqtietietie.weex

import android.app.Application
import com.taobao.weex.InitConfig
import com.taobao.weex.WXSDKEngine
import xyz.lihang.qqtietietie.Constant
import xyz.lihang.qqtietietie.utils.AppUtils

/**
 * Created by LiHang on 2017/9/11.
 */
class ManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        //检查更新
        AppUtils.update(this, Constant.UPDATE_URL)
    }

    private fun setUpWeex() {
        //weex注册
        try {
            val builder = InitConfig.Builder()
            builder.setImgAdapter(ImageAdapter())
            WXSDKEngine.initialize(this, builder.build())
            ConfigModule.register()

        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e)
        }

    }

    companion object {
        var application: Application? = null;
    }
}
