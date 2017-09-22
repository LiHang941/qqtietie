package xyz.lihang.qqtietietie.weex;

import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXException;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import xyz.lihang.qqtietietie.Constant;
import xyz.lihang.qqtietietie.model.Face;
import xyz.lihang.qqtietietie.model.ParesFactory;
import xyz.lihang.qqtietietie.model.ParseDto;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by LiHang on 2017/9/21.
 */

public class ConfigModule extends WXModule {
    public  static void register () throws WXException {
        WXSDKEngine.registerModule("configModule", ConfigModule.class);
    }
    @WXModuleAnno(runOnUIThread = true)
    public void save(String itemJsonStr,List<ParseDto> list, JSCallback jsCallback) {
        boolean flag = false;
        try{
            List<Face> faces = new ArrayList<>();
            for(ParseDto parseDto:list){
                Face face = ParesFactory.parseParseDto(parseDto);
                faces.add(face);
            }
            String facesJson = JSON.toJSONString(faces);
            Log.d("ConfigModule",facesJson);

            SharedPreferences.Editor facesEditor = mWXSDKInstance.getContext().getSharedPreferences(Constant.facesName, MODE_PRIVATE).edit();
            facesEditor.putString(Constant.facesName,facesJson);
            facesEditor.apply();

            facesEditor = mWXSDKInstance.getContext().getSharedPreferences(Constant.facesName, MODE_PRIVATE).edit();
            facesEditor.putString(Constant.tempName,itemJsonStr);
            facesEditor.apply();

            flag = true;
        }catch (Exception e){
            flag = false;
        }
        jsCallback.invoke(Collections.singletonMap("flag",(Object) Boolean.valueOf(flag)));
    }

    @WXModuleAnno(runOnUIThread = true)
    public void getTemplate(JSCallback jsCallback) {
        SharedPreferences sharedPreferences = mWXSDKInstance.getContext().getSharedPreferences(Constant.facesName, MODE_PRIVATE);
        String string = sharedPreferences.getString(Constant.tempName, null);
        jsCallback.invoke(Collections.singletonMap("str",(Object) string));
    }
}
