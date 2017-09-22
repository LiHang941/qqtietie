package xyz.lihang.qqtietietie.app;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.allenliu.versionchecklib.core.AVersionService;
import xyz.lihang.qqtietietie.utils.AppUtils;
import xyz.lihang.qqtietietie.utils.model.ApkUpdateDto;

/**
 * Created by LiHang on 2017/9/11.
 */

public class UpdateService extends AVersionService {
    @Override
    public void onResponses(final AVersionService aVersionService, String s) {
        try {
            ApkUpdateDto apkUpdateDto = JSON.parseObject(s,ApkUpdateDto.class);
            if(apkUpdateDto!= null
                    && apkUpdateDto.getApkurl()!=null && apkUpdateDto.getApkurl().length() > 0
                    && (!apkUpdateDto.getVersion().equals(AppUtils.getVersionName(ManagementApplication.Companion.getApplication())))){
                aVersionService.showVersionDialog(apkUpdateDto.getApkurl(),"检测到新版本",apkUpdateDto.getContent());
            }else{
                Toast.makeText(ManagementApplication.Companion.getApplication(), "暂无新版本", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(ManagementApplication.Companion.getApplication(), "检查更新失败,请检查网络", Toast.LENGTH_SHORT).show();
        }
    }
}
