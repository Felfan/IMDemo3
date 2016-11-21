package com.fqf.imdemo.application;

import android.app.Application;
import android.util.Log;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by ${chenyn} on 16/3/22.
 *
 * @desc :
 */
public class IMDebugApplication extends Application {

    private static IMDebugApplication instance;
    public static IMDebugApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Log.i("IMDebugApplication", "init");
        JMessageClient.init(getApplicationContext());

        //用户登录只有一次，这里写死了
        JMessageClient.login("103258", "103258", null);

        //用户的退出
/**        mProgressDialog = ProgressDialog.show(SettingMainActivity.this, "提示：", "正在加载中。。。");
        UserInfo myInfo = JMessageClient.getMyInfo();
        if (myInfo != null) {
            JMessageClient.logout();
            mProgressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "退出成功", Toast.LENGTH_SHORT).show();
            intent.setClass(SettingMainActivity.this, RegisterAndLoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(SettingMainActivity.this, "退出失败", Toast.LENGTH_SHORT).show();
            mProgressDialog.dismiss();
        }
*/
    }
}

