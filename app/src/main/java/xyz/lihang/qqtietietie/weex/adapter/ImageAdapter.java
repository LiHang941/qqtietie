package xyz.lihang.qqtietietie.weex.adapter;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

import xyz.lihang.qqtietietie.utils.LoadLocalImageUtil;
import xyz.lihang.qqtietietie.weex.ConfigurationProvider;

/**
 * Created by LiHang on 2017/9/22.
 */

public class ImageAdapter  implements IWXImgLoaderAdapter {
    @Override
    public void setImage(String url,ImageView view,WXImageQuality quality, WXImageStrategy strategy) {

        if (url.contains("assets/")) {
            String head = ConfigurationProvider.INSTANCE.getWeexAppUrl() + "assets/";
            url = url.replace(head,"");
            LoadLocalImageUtil.getInstance().dispalyFromAssets(url, view);
            return;
        }

        if(!TextUtils.isEmpty(strategy.placeHolder)){
            Picasso.Builder builder=new Picasso.Builder(WXEnvironment.getApplication());
            Picasso picasso=builder.build();
            picasso.load(Uri.parse(strategy.placeHolder)).into(view);
            view.setTag(strategy.placeHolder.hashCode(),picasso);
        }
    }
}
