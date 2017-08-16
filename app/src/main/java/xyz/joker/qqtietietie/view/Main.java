package xyz.joker.qqtietietie.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;
import java.util.regex.Pattern;
import joker.qqtietietie.R;
import xyz.joker.qqtietietie.utils.DialogUtil;


public class Main extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }




    private void initView() {
        showMoudleState();
        TextView mainContent = (TextView) findViewById(R.id.mainContent);
        int flags = Pattern.CASE_INSENSITIVE;
        Pattern p = Pattern.compile("http://www.lihang.xyz/", flags);
        Pattern p2 = Pattern.compile("https://github.com/wawa2222/qqtietie", flags);
        Linkify.addLinks(mainContent, p, "http://www.lihang.xyz/");
        Linkify.addLinks(mainContent, p2, "https://github.com/wawa2222/qqtietie");
    }

    private void showMoudleState() {
        if (!isModuleActive())
            DialogUtil.showAlertlr(this, getString(R.string.tips), getString(R.string.module_active_tips), getString(R.string.ignore), null, getString(R.string.open_xposed), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    redirectToXposed();
                }
            });
    }

    /**
     * Xposed拦截
     * @return
     */
    private boolean isModuleActive() {
        return false;
    }


    private void redirectToXposed() {
        ComponentName componentName = new ComponentName("de.robv.android.xposed.installer",
                "de.robv.android.xposed.installer.WelcomeActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
