package xyz.lihang.qqtietietie.service;

import android.widget.Toast;

import com.allenliu.versionchecklib.core.AVersionService;

import xyz.lihang.qqtietietie.model.ApkUpdateDto;
import xyz.lihang.qqtietietie.utils.AppUtils;
import xyz.lihang.qqtietietie.weex.ManagerApplication;

/**
 * Created by LiHang on 2017/9/11.
 */

public class UpdateService extends AVersionService {
    @Override
    public void onResponses(final AVersionService aVersionService, String s) {
        try {
            ApkUpdateDto apkUpdateDto = null;
            if(apkUpdateDto!= null
                    && apkUpdateDto.getApkurl()!=null && apkUpdateDto.getApkurl().length() > 0
                    && (!apkUpdateDto.getVersion().equals(AppUtils.getVersionName(ManagerApplication.Companion.getApplication())))){

                aVersionService.showVersionDialog(apkUpdateDto.getApkurl(),"检测到新版本",apkUpdateDto.getContent());
            }else{
                Toast.makeText(ManagerApplication.Companion.getApplication(), "暂无新版本", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(ManagerApplication.Companion.getApplication(), "检查更新失败,请检查网络", Toast.LENGTH_SHORT).show();
        }
    }
}
