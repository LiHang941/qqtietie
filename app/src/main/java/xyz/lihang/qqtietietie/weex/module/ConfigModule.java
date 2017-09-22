package xyz.lihang.qqtietietie.weex.module;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXException;
import com.taobao.weex.common.WXModule;

import java.util.ArrayList;
import java.util.List;

import xyz.lihang.qqtietietie.Constant;
import xyz.lihang.qqtietietie.utils.AppUtils;
import xyz.lihang.qqtietietie.utils.model.Face;
import xyz.lihang.qqtietietie.utils.model.ParesFactory;
import xyz.lihang.qqtietietie.utils.model.ParseDto;

/**
 * Created by LiHang on 2017/9/21.
 */

public class ConfigModule extends WXModule {
    public  static void register () throws WXException {
        WXSDKEngine.registerModule("configModule", ConfigModule.class);
    }
    @JSMethod( uiThread = true)
    public void save(String itemJsonStr, List<ParseDto> list, JSCallback jsCallback) {
        boolean flag = false;
        try{
            List<Face> faces = new ArrayList<>();
            for(ParseDto parseDto:list){
                Face face = ParesFactory.parseParseDto(parseDto);
                faces.add(face);
            }
            String facesJson = JSON.toJSONString(faces);
            SharedPreferences.Editor facesEditor = mWXSDKInstance.getContext().getSharedPreferences(Constant.facesName, Activity.MODE_WORLD_READABLE).edit();
            facesEditor.putString(Constant.facesName,facesJson);
            facesEditor.apply();
            facesEditor = mWXSDKInstance.getContext().getSharedPreferences(Constant.facesName, Activity.MODE_WORLD_READABLE).edit();
            facesEditor.putString(Constant.tempName,itemJsonStr);
            facesEditor.apply();
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        jsCallback.invoke(flag);
    }

    @JSMethod( uiThread = true)
    public void getTemplate(JSCallback jsCallback) {
        SharedPreferences sharedPreferences = mWXSDKInstance.getContext().getSharedPreferences(Constant.facesName, Activity.MODE_WORLD_READABLE);
        String string = sharedPreferences.getString(Constant.tempName, null);
        jsCallback.invoke(string);
    }

    @JSMethod( uiThread = true)
    public void update() {
        AppUtils.update(mWXSDKInstance.getContext(),Constant.UPDATE_URL);
    }


    @JSMethod( uiThread = true)
    public void getversion(JSCallback jsCallback){
        jsCallback.invoke( AppUtils.getVersionName(mWXSDKInstance.getContext()));
    }
}
