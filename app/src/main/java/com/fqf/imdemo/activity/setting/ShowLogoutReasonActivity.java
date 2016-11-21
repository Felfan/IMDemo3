package com.fqf.imdemo.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.fqf.imdemo.R;
import com.fqf.imdemo.RegisterAndLoginActivity;
import com.fqf.imdemo.activity.TypeActivity;


/**
 * Created by ${chenyn} on 16/4/17.
 *
 * @desc :
 */
public class ShowLogoutReasonActivity extends Activity{

    private TextView mTv_showLogoutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mTv_showLogoutInfo.append(intent.getStringExtra(TypeActivity.LOGOUT_REASON));
    }

    private void initView() {
        setContentView(R.layout.activity_show_logout_info);
        mTv_showLogoutInfo = (TextView) findViewById(R.id.tv_show_logout_info);
    }

    @Override
    protected void onPause() {
        Intent intent = new Intent(ShowLogoutReasonActivity.this, RegisterAndLoginActivity.class);
        startActivity(intent);
        super.onDestroy();
    }
}
