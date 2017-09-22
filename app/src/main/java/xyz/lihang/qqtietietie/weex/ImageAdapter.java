package xyz.lihang.qqtietietie.weex;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * Created by LiHang on 2017/9/20.
 */
public class ImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }
}