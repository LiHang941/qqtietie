package xyz.lihang.qqtietietie.hook;

import android.app.Activity;
import android.app.Application;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import xyz.lihang.qqtietietie.Constant;
import xyz.lihang.qqtietietie.model.Face;
import xyz.lihang.qqtietietie.model.ParesFactory;
import xyz.lihang.qqtietietie.utils.BeanUtils;
import xyz.lihang.qqtietietie.utils.XSharedPreferencesUtil;

import static de.robv.android.xposed.XposedBridge.invokeOriginalMethod;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by LiHang on 2017/8/14.
 */
public class StickerMsgHook {

    private static XC_LoadPackage.LoadPackageParam loadPackageParam;

    /*================    自带表情       =========================*/
    private final String BaseChatPieClassName= "com.tencent.mobileqq.activity.BaseChatPie";
    private final String HookMethod_1= "a";
    private final String HookMethod_1_Param1= "java.lang.String";
    private final String HookMethod_1_Param2= "com.tencent.mobileqq.emoticon.EmojiStickerManager.StickerInfo";

   /*======================  其他表情(非自定义表情)  ======================================*/
    private final String ChatActivityFacadeClassName = "com.tencent.mobileqq.activity.ChatActivityFacade";
    private final String HookMethod_2 = "a";
    private final String HookMethod_2_Param1 = "com.tencent.mobileqq.app.QQAppInterface";
    private final String HookMethod_2_Param2 = "android.content.Context";
    private final String HookMethod_2_Param3 = "com.tencent.mobileqq.activity.aio.SessionInfo";
    private final String HookMethod_2_Param4 = "com.tencent.mobileqq.data.Emoticon";
    private final String HookMethod_2_Param5 = "com.tencent.mobileqq.emoticon.EmojiStickerManager.StickerInfo";

    /*================ QQ 包名 ====================*/
    private static final String QQ_PACKAGE_NAME = "com.tencent.mobileqq";


    //HOOK
    public void initAndHook(final XC_LoadPackage.LoadPackageParam loadPackageParam) {
        this.loadPackageParam = loadPackageParam;
        if (isQQPackage(loadPackageParam)) {
            findAndHookMethod(Application.class, "dispatchActivityResumed", Activity.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
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
        findAndHookMethod(ChatActivityFacadeClassName, loadPackageParam.classLoader, HookMethod_2, HookMethod_2_Param1,HookMethod_2_Param2,HookMethod_2_Param3,HookMethod_2_Param4,HookMethod_2_Param5,  new XC_MethodReplacement() {
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
        XposedBridge.log(str + "Hook方法:"+methodHookParam.method.getName());
        XposedBridge.log(str + "Hook拦截对象:"+methodHookParam.thisObject);

        StringBuilder sb = new StringBuilder();
        sb.append(str + "Hook参数长度 : "+ methodHookParam.args.length);
        for(int i=0;i<methodHookParam.args.length;i++){
            sb.append("\n--"+i+"---");
            if(methodHookParam.args[i]!=null){
                sb.append(methodHookParam.args[i].getClass()+"---" );
                try {
                    sb.append(JSON.toJSONString(methodHookParam.args[i]));
                }catch (Exception e){
                    sb.append(methodHookParam.args[i].toString());
                }
            }
        }
        XposedBridge.log(str + "Hook参数:"+ sb.toString());
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
                        //List<Face> faceList = ParesFactory.getFaceList();
                        List<Face> faceList = ParesFactory.defaultFaces();
                        XposedBridge.log("face:" + JSON.toJSONString(faceList));
                        if(faceList.size() == 0){
                            invokeOriginalMethod(methodHookParam.method, methodHookParam.thisObject, methodHookParam.args);
                        }else{
                            Object yuanObject = methodHookParam.args[methodHookParam.args.length-1]; //qq表情信息对象
                            Stack<Object> objs = ParesFactory.getFaceList(yuanObject, faceList);
                            for(Object obj:objs){
                                XposedBridge.log("face:" + JSON.toJSONString(obj));
                                methodHookParam.args[methodHookParam.args.length - 1] = obj;
                                invokeOriginalMethod(methodHookParam.method, methodHookParam.thisObject, methodHookParam.args); //发送
                                Thread.sleep(100);
                            }
                        }
                    } catch (Throwable t) {
                        XposedBridge.log(t);
                    }
                }
            });
    }


    /**
     * 判断是否是QQ的包
     * @param loadPackageParam
     * @return
     */
    private boolean isQQPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        return loadPackageParam.packageName.equals(QQ_PACKAGE_NAME);
    }



}
