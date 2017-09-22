package xyz.lihang.qqtietietie.utils;

import de.robv.android.xposed.XSharedPreferences;
import xyz.lihang.qqtietietie.Constant;

/**
 * 数据储存
 */
public class XSharedPreferencesUtil {

    public  static XSharedPreferences getXSharedPreferences(String name){
        XSharedPreferences xSharedPreferences = new XSharedPreferences(Constant.packName, name);
        xSharedPreferences.makeWorldReadable();
        return xSharedPreferences;
    }
}
