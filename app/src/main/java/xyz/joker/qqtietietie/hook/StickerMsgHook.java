package xyz.joker.qqtietietie.hook;

import android.app.Activity;
import android.app.Application;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import xyz.joker.qqtietietie.utilities.BeanUtils;
import xyz.joker.qqtietietie.utilities.XSharedPreferencesUtil;

import static de.robv.android.xposed.XposedBridge.invokeOriginalMethod;
import static de.robv.android.xposed.XposedHelpers.assetAsByteArray;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by LiHang on 2017/8/14.
 */

public class StickerMsgHook {
    private final XC_LoadPackage.LoadPackageParam loadPackageParam;
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

    private final static Object [] StickerInfo = { null,null };

    private static Object baseChatPie = null;


    private static final String QQ_PACKAGE_NAME = "com.tencent.mobileqq";
    public StickerMsgHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        this.loadPackageParam = loadPackageParam;
    }

    public void initAndHook() {
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
                log("自带表情",methodHookParam);
//                String objString  = new Gson().toJson(methodHookParam.args[methodHookParam.args.length-1]);
//                XSharedPreferencesUtil.getXSharePreferences().edit().putString("modelString",objString);
//                XSharedPreferencesUtil.reload();
                Object object = methodHookParam.args[1].getClass().newInstance();
                BeanUtils.copyProperty(methodHookParam.args[1],object);
                StickerInfo[0] = object;
                StickerInfo[1] = methodHookParam.args[0];

                XposedBridge.log("StickerInfoString:"+methodHookParam.args[0].toString().length());

                sendTie(methodHookParam);
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
                    XposedBridge.log(t);
                }
                return null;
            }

        });

        final int[] sendType = {-1000};
        //发送消息表情
        findAndHookMethod("com.tencent.mobileqq.app.message.QQMessageFacade"
                , loadPackageParam.classLoader
                ,"a"
                ,"com.tencent.mobileqq.data.MessageRecord", "com.tencent.mobileqq.app.MessageObserver"
                ,  new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                        log("发送表情",methodHookParam);
                        Object arg = methodHookParam.args[0];
                        Field msgType = XposedHelpers.findClass("com.tencent.mobileqq.data.MessageRecord"
                                , loadPackageParam.classLoader).getDeclaredField("msgtype");

                        msgType.setAccessible(true);

                        int type = msgType.getInt(arg);

                        //String modelString = XSharedPreferencesUtil.getString("modelString", null);
                        if(StickerInfo[0] == null || baseChatPie == null){
                            return;
                        }
                        for(int stype :sendType){
                            if(stype == type){

                                Field msgUid = XposedHelpers.findClass("com.tencent.mobileqq.data.MessageRecord"
                                        , loadPackageParam.classLoader).getDeclaredField("msgUid");


                                msgUid.setAccessible(true);


                                Field msgseq = XposedHelpers.findClass("com.tencent.mobileqq.data.MessageRecord"
                                        , loadPackageParam.classLoader).getDeclaredField("msgseq");
                                msgseq.setAccessible(true);

                                Field time = XposedHelpers.findClass("com.tencent.mobileqq.data.MessageRecord"
                                        , loadPackageParam.classLoader).getDeclaredField("time");
                                time.setAccessible(true);


                                long uid = msgUid.getLong(arg);
                                long seq = msgseq.getLong(arg);
                                long times = time.getLong(arg);

                                XposedBridge.log("sendMsgType:"+type + "------- uid:" + uid);


                                //发送操作
                                final Object object = StickerInfo[0].getClass().newInstance();
                                BeanUtils.copyProperty(StickerInfo[0],object);
                                Field hostMsgUid = object.getClass().getField("hostMsgUid");
                                hostMsgUid.setAccessible(true);
                                hostMsgUid.setLong(object,uid);

                                Field hostMsgSeq = object.getClass().getField("hostMsgSeq");
                                hostMsgSeq.setAccessible(true);
                                hostMsgSeq.setLong(object,seq);

                                Field hostMsgTime = object.getClass().getField("hostMsgTime");
                                hostMsgTime.setAccessible(true);
                                hostMsgTime.setLong(object,times);

                                //Field hostMsgTime = object.getClass().getField("hostMsgTime");
                                //hostMsgTime.setAccessible(true);
                                //hostMsgTime.setLong(object,new Date().getTime());

                                XposedBridge.log("toString:"+ new Gson().toJson(object));

                                ExecutorService singleThread = Executors.newSingleThreadExecutor();
                                singleThread.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(2000);
                                            XposedHelpers.callMethod(
                                                    baseChatPie
                                                    , HookMethod_1
                                                    ,StickerInfo[1]
                                                    ,object
                                            );
                                        } catch (Throwable t) {
                                            XposedBridge.log(t);
                                        }
                                    }
                                });



                                return;

                            }
                        }


                    }
                });

        XposedBridge.hookAllConstructors(XposedHelpers.findClass(BaseChatPieClassName, loadPackageParam.classLoader), new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                baseChatPie = param.thisObject;
            }
        });



    }

    private void log (String str ,XC_MethodHook.MethodHookParam methodHookParam){
        XposedBridge.log(str + "-----methodHookParam3.method:"+methodHookParam.method.getName());
        XposedBridge.log(str + "-----methodHookParam3.thisObject:"+methodHookParam.thisObject);

        StringBuilder sb = new StringBuilder();
        sb.append(str + "-----length : "+ methodHookParam.args.length);
        for(int i=0;i<methodHookParam.args.length;i++){
            sb.append("\n--"+i+"---");
            if(methodHookParam.args[i]!=null){
                sb.append(methodHookParam.args[i].getClass()+"---" );
                try {
                    sb.append( new Gson().toJson(methodHookParam.args[i]));
                }catch (Exception e){
                    sb.append(methodHookParam.args[i].toString());
                }
            }
        }
        XposedBridge.log(str + "-----methodHookParam3.args:"+ sb.toString());


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
        final Object[] yuanobject  = {methodHookParam.args[methodHookParam.args.length-1]};
        ExecutorService singleThread = Executors.newSingleThreadExecutor();
        for (long i = 0; i < 5; i++) {
            singleThread.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Object object = execute(yuanobject[0]);
                        yuanobject[0] = object;
                        methodHookParam.args[methodHookParam.args.length-1] = object;
                        invokeOriginalMethod(methodHookParam.method, methodHookParam.thisObject, methodHookParam.args);
                    } catch (Throwable t) {
                        XposedBridge.log(t);
                    }
                }
            });
        }
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
        XposedBridge.log("methodHookParam2.obj1:"+object);
        Field x = object.getClass().getDeclaredField("x");
        Field height = object.getClass().getDeclaredField("height");
        Field width = object.getClass().getDeclaredField("width");
        Field y = object.getClass().getDeclaredField("y");
        x.setAccessible(true);
        y.setAccessible(true);
        x.setFloat(object,x.getFloat(object) + width.getFloat(object));
        y.setFloat(object,y.getFloat(object) + height.getFloat(object));
        XposedBridge.log("methodHookParam2.obj2:"+object);
        return object;
    }

}
