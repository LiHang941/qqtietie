package xyz.lihang.qqtietietie.weex;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import java.util.HashMap;

import xyz.lihang.qqtietietie.R;
/**
 * Created by LiHang on 2017/9/20.
 */
public class MainActivity extends FragmentActivity implements IWXRenderListener {
    private WXSDKInstance mWXSDKInstance;
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWXSDKInstance = new WXSDKInstance(this);
        startLoad();
        loadPage();
    }
    private void loadPage (){
        Uri mUri = null;
        if(flag){
            Uri uri = getIntent().getData();
            Bundle bundle = getIntent().getExtras();

            if (uri != null) {
                mUri = uri;
            }

            if (bundle != null) {
                String bundleUrl = bundle.getString("bundleUrl");
                if (!TextUtils.isEmpty(bundleUrl)) {
                    mUri = Uri.parse(bundleUrl);
                }
            }

            if (mUri == null) {
                Toast.makeText(this, "the uri is empty! ", Toast.LENGTH_SHORT).show();
                mUri = Uri.parse("assets://home.js");
            }
            String path = mUri.toString();
            // 传来的url参数总会带上http:/ 应该是个bug 可以自己判断是否本地url再去跳转
            String jsPath = path.indexOf("weex/js/") > 0 ? path.replace("http:/", "") : path;
            HashMap<String, Object> options = new HashMap<String, Object>();
            options.put(WXSDKInstance.BUNDLE_URL, jsPath);
            mWXSDKInstance = new WXSDKInstance(this);
            mWXSDKInstance.registerRenderListener(this);
            mWXSDKInstance.render("weex",  WXFileUtils.loadFileContent( mUri.getPath() ,this), options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);

//            mWXSDKInstance.registerRenderListener(this);
//            String url = this.getIntent().getStringExtra("PagePath");
//            url = url == null?"home.js":url;
//            mWXSDKInstance.render("WXSample", WXFileUtils.loadFileContent(url,this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
            flag = false;
        }
    }


    private void startLoad(){
        setContentView(R.layout.activity_loading);
    }

    private void errorPage (){
        flag = true;
        setContentView(R.layout.activity_error);
        findViewById(R.id.button_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reloadPage();
            }
        });
    }
    private void reloadPage(){
        startLoad();
        loadPage();
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }
    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
    }
    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
    }
    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        Log.d("MainActivity","code:" + errCode + "---Msg:" + msg);
        errorPage ();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}
