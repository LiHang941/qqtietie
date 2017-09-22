package xyz.lihang.qqtietietie.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.allenliu.versionchecklib.core.http.HttpRequestMethod;

import xyz.lihang.qqtietietie.service.UpdateService;

/**
 * Created by LiHang on 2017/9/20.
 */
public class AppUtils {
    /**
     * 获取version
     * @param context
     * @return
     */
    public static String getVersionName (Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            String version = packInfo.versionName;
            return version;
        }catch (Exception e){
            return  null;
        }
    }

    /**
     * 更新
     * @param context
     */
    public static void update(Context context,String updateUrl){
        AllenChecker.init(true);
        VersionParams.Builder builder = new VersionParams.Builder()
                .setRequestMethod(HttpRequestMethod.GET)
                .setRequestUrl(updateUrl)
                .setService(UpdateService.class);
        AllenChecker.startVersionCheck(context, builder.build());
    }

    /**
     * 跳转xposed 模块列表
     * @param context
     */
    public static  void redirectToXposed(Context context) {
        ComponentName componentName = new ComponentName("de.robv.android.xposed.installer", "de.robv.android.xposed.installer.WelcomeActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        context.startActivity(intent);
    }


}
