package xyz.lihang.qqtietietie.app

import android.app.Activity
import android.app.Application
import android.graphics.drawable.Drawable
import android.support.multidex.MultiDexApplication
import cn.finalteam.galleryfinal.*
import cn.finalteam.galleryfinal.widget.GFImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.ImageViewTarget
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.taobao.weex.InitConfig
import com.taobao.weex.WXSDKEngine
import xyz.lihang.qqtietietie.Constant
import xyz.lihang.qqtietietie.R
import xyz.lihang.qqtietietie.app.navigation.NativeNavigator
import xyz.lihang.qqtietietie.app.navigation.NavigationManager
import xyz.lihang.qqtietietie.app.navigation.WeexNavigator
import xyz.lihang.qqtietietie.utils.AppUtils
import xyz.lihang.qqtietietie.weex.ActivityNavBarSetter
import xyz.lihang.qqtietietie.weex.adapter.ImageAdapter
import xyz.lihang.qqtietietie.weex.adapter.MyURIAdapter
import xyz.lihang.qqtietietie.weex.module.ConfigModule
import java.io.File


/**
 * @author HanikLZ
 * @since 2016/10/10
 */
class ManagementApplication : MultiDexApplication() {

    companion object {
        const val SCHEMA_PAGE_MAIN = ""
        val debug = BuildConfig.DEBUG
        var deviceToken = ""
        var application: Application? = null;
    }

    override fun onCreate() {
        super.onCreate()
        ManagementApplication.application = this

        setupWx()
        setupNavigator()
        setupPicker()
    }

    private fun setupNavigator() {
        NavigationManager.run {
            registerProtocolHandler("", WeexNavigator)
            registerProtocolHandler("management", NativeNavigator)
            registerProtocolHandler("assets", WeexNavigator)
            registerProtocolHandler("https", WeexNavigator)
            registerProtocolHandler("http", WeexNavigator)
            registerSchema(SCHEMA_PAGE_MAIN, "home.js")
        }
    }

    private fun setupWx() {
            com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this))
            var weexBuilder = InitConfig.Builder()
            weexBuilder.setImgAdapter(ImageAdapter())
            weexBuilder.setURIAdapter(MyURIAdapter())
            WXSDKEngine.initialize(this, weexBuilder.build())
            ConfigModule.register()
            WXSDKEngine.setActivityNavBarSetter(ActivityNavBarSetter(this).apply { registerActivityLifecycleCallbacks(this) })
    }




    private fun setupPicker() {
        val theme = ThemeConfig.Builder().build()
        val config = FunctionConfig.Builder()
                .setEnableEdit(false)
                .setCropSquare(false)
                .setEnablePreview(false)
                .setEnableCamera(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .build()

        val imageLoader = object : ImageLoader {
            override fun displayImage(activity: Activity?, path: String?, imageView: GFImageView, defaultDrawable: Drawable?, width: Int, height: Int) {
                Glide.with(activity)
                        .load("file://" + path)
                        .placeholder(defaultDrawable)
                        .error(defaultDrawable)
                        .override(width, height)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(object : ImageViewTarget<GlideDrawable>(imageView) {
                            override fun setResource(resource: GlideDrawable) = imageView.setImageDrawable(resource)
                            override fun setRequest(request: Request?) = imageView.setTag(R.id.qqtietietie_view_holder, request)
                            override fun getRequest() = imageView.getTag(R.id.qqtietietie_view_holder) as? Request
                        })
            }
            override fun clearMemoryCache() = Unit
        }

        GalleryFinal.init(CoreConfig.Builder(this, imageLoader, theme)
                .setTakePhotoFolder(File(externalCacheDir, "photo").apply { if (!parentFile.exists()) parentFile.mkdirs() })
                .setFunctionConfig(config)
                .build())
    }

}

