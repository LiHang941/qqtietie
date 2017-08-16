package xyz.joker.qqtietietie.hook;

import android.app.Activity;
import android.app.Application;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import xyz.joker.qqtietietie.utils.BeanUtils;

import static de.robv.android.xposed.XposedBridge.invokeOriginalMethod;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by LiHang on 2017/8/14.
 */

public class StickerMsgHook {
    private static XC_LoadPackage.LoadPackageParam loadPackageParam;
    private final String BaseChatPieClassName= "com.tencent.mobileqq.activity.BaseChatPie";
    private final String HookMethod_1= "a";
    private final String HookMethod_1_Param1= "java.lang.String";
    private final String HookMethod_1_Param2= "com.tencent.mobileqq.emoticon.EmojiStickerManager.StickerInfo";


    private final String BaseChatPieClassName_2= "com.tencent.mobileqq.activity.ChatActivityFacade";
    private final String HookMethod_2= "a";
    private final String HookMethod_2_Param1= "com.tencent.mobileqq.app.QQAppInterface";
    private final String HookMethod_2_Param2= "android.content.Context";
    private final String HookMethod_2_Param3= "com.tencent.mobileqq.activity.aio.SessionInfo";
    private final String HookMethod_2_Param4= "com.tencent.mobileqq.data.Emoticon";
    private final String HookMethod_2_Param5= "com.tencent.mobileqq.emoticon.EmojiStickerManager.StickerInfo";

    private final String HookMethod2= "com.tencent.mobileqq.activity.BaseChatPie";


    private static final String QQ_PACKAGE_NAME = "com.tencent.mobileqq";
    public StickerMsgHook() {

    }

    public void initAndHook(final XC_LoadPackage.LoadPackageParam loadPackageParam) {
        this.loadPackageParam = loadPackageParam;
        if (isQQPackage(loadPackageParam)) {
            findAndHookMethod(Application.class, "dispatchActivityResumed", Activity.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
                    XposedBridge.log("StickerMsgHook:"+loadPackageParam.toString());
                    sendStickerMsgHook(loadPackageParam);
                }
            });
        }
    }
    private void sendStickerMsgHook(final XC_LoadPackage.LoadPackageParam loadPackageParam){
        //hook自带表情
        findAndHookMethod(BaseChatPieClassName, loadPackageParam.classLoader, HookMethod_1, HookMethod_1_Param1,HookMethod_1_Param2,  new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(final MethodHookParam methodHookParam) throws Throwable {
                try {
                    log("自带表情", methodHookParam);
                    Object object = methodHookParam.args[1].getClass().newInstance();
                    BeanUtils.copyProperty(methodHookParam.args[1], object);
                    sendTie(methodHookParam);
                }catch (Exception e){
                    XposedBridge.log("ERROR:" + e);
                }
                return null;
            }
        });
        //hook其他表情
        findAndHookMethod(BaseChatPieClassName_2, loadPackageParam.classLoader, HookMethod_2, HookMethod_2_Param1,HookMethod_2_Param2,HookMethod_2_Param3,HookMethod_2_Param4,HookMethod_2_Param5,  new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(final MethodHookParam methodHookParam) throws Throwable {
                try {
                    log("其他表情",methodHookParam);
                    if(methodHookParam.args[methodHookParam.args.length-1] != null){
                        sendTie(methodHookParam);
                        return null;
                    }else{
                        invokeOriginalMethod(methodHookParam.method, methodHookParam.thisObject, methodHookParam.args);
                    }
                } catch (Throwable t) {
                    XposedBridge.log("ERROR:" + t);
                }
                return null;
            }
        });
    }
    private void log (String str ,XC_MethodHook.MethodHookParam methodHookParam){
//        XposedBridge.log(str + "Hook方法:"+methodHookParam.method.getName());
//        XposedBridge.log(str + "Hook拦截对象:"+methodHookParam.thisObject);
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(str + "Hook参数长度 : "+ methodHookParam.args.length);
//        for(int i=0;i<methodHookParam.args.length;i++){
//            sb.append("\n--"+i+"---");
//            if(methodHookParam.args[i]!=null){
//                sb.append(methodHookParam.args[i].getClass()+"---" );
//                try {
//                    sb.append( new Gson().toJson(methodHookParam.args[i]));
//                }catch (Exception e){
//                    sb.append(methodHookParam.args[i].toString());
//                }
//            }
//        }
//        XposedBridge.log(str + "Hook参数:"+ sb.toString());
    }
    /**
     * 判断是否是QQ的包
     * @param loadPackageParam
     * @return
     */
    private boolean isQQPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        return loadPackageParam.packageName.equals(QQ_PACKAGE_NAME);
    }

    /**
     * 发送帖子表情
     * @param methodHookParam
     */
    private void sendTie(final XC_MethodHook.MethodHookParam methodHookParam){
        ExecutorService singleThread = Executors.newSingleThreadExecutor();
            singleThread.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Object[] yuanobject  = {methodHookParam.args[methodHookParam.args.length-1]};
                        for (long i = 0; i < 5; i++) {
                            Object object = execute(yuanobject[0]);
                            yuanobject[0] = object;
                            methodHookParam.args[methodHookParam.args.length - 1] = object;
                            invokeOriginalMethod(methodHookParam.method, methodHookParam.thisObject, methodHookParam.args);
                            Field x = object.getClass().getDeclaredField("x");
                            Field height = object.getClass().getDeclaredField("height");
                            Field width = object.getClass().getDeclaredField("width");
                            Field y = object.getClass().getDeclaredField("y");
                            x.setAccessible(true);
                            y.setAccessible(true);
                            x.setFloat(object,x.getFloat(object));
                            y.setFloat(object,y.getFloat(object) + height.getFloat(object));
                           // XposedBridge.log("发送的表情:" + object);
                            Thread.sleep(100);
                        }
                    } catch (Throwable t) {
                        XposedBridge.log(t);
                    }
                }
            });
    }


    /**
     * 反射设置值
     * @param obj
     * @return
     * @throws Exception
     */
    private Object execute(Object obj) throws  Exception{
        Object object = obj.getClass().newInstance();
        BeanUtils.copyProperty(obj,object);
        return object;
    }

}
