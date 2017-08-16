package xyz.joker.qqtietietie.hook;

import java.io.File;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 *
 */
public class PokeMsgHook implements IXposedHookLoadPackage {
    File file = null;
    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        new QQTieTieTiePackageHook(loadPackageParam).initAndHook();
        new StickerMsgHook().initAndHook(loadPackageParam);
    }


}
