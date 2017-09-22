package xyz.lihang.qqtietietie.hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * MyPackageHook
 */
class MyPackageHook {
    private static final String METHOD_NAME_IS_MODULE_ACTIVE = "isModuleActive";
    private final XC_LoadPackage.LoadPackageParam loadPackageParam;
    private static final String PACKAGENAME = "xyz.lihang.qqtietietie";
    private static final String CLASSNAME_MAIN = "xyz.lihang.qqtietietie.weex.SplashActivity";

    public MyPackageHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        this.loadPackageParam = loadPackageParam;
    }

    public void initAndHook() {
        if (isQQSendPokePackage(loadPackageParam)) {
            findAndHookMethod(CLASSNAME_MAIN, loadPackageParam.classLoader, METHOD_NAME_IS_MODULE_ACTIVE, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });
        }
    }

    /**
     *
     * @param loadPackageParam
     * @return
     */
    private boolean isQQSendPokePackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        return loadPackageParam.packageName.equals(PACKAGENAME);
    }
}
