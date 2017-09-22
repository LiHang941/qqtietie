package xyz.lihang.qqtietietie.hook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 *
 */
public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        new MyPackageHook(loadPackageParam).initAndHook();
        new StickerMsgHook().initAndHook(loadPackageParam);
    }
}
