package com.sxit.dreamiya.page.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.pay.PayDemoActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.config.MLog;
import com.sxit.dreamiya.entity.UserEntity;
import com.sxit.dreamiya.entity.http.request.RegisterEntity;
import com.sxit.dreamiya.eventbus.EventCode;
import com.sxit.dreamiya.eventbus.MEvent;
import com.sxit.dreamiya.http.BaseRdaHttp;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.mimplement.UserServiceImplement;
import com.sxit.dreamiya.mservice.UserService;

public class DemoActivity extends BaseActivity {

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dreamiya_activity_demo);
        Button goto_pay_button = (Button) findViewById(R.id.goto_pay_button);
        userService = new UserServiceImplement();
        userService.test("210726");
        goto_pay_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DemoActivity.this, PayDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onEventMainThread(RdaResultPack http) {
        if (http.Match(userService.This(), "test")) {
            if (http.Success()) {
                // TODO
            } else if (http.HttpFail()) {
                Toast.makeText(this, "服务器连接失败，请稍后再试！", Toast.LENGTH_SHORT)
                        .show();
                // Alert("");
            } else if (http.ServerError()) {
                if (http.Message().toString().trim().equals("E003")) {
                    Toast.makeText(this, "未查询到相关数据", Toast.LENGTH_SHORT).show();
                    // Alert("暂无车会活动！");
                } else {
                    Toast.makeText(this, "服务器请求失败，请稍后再试！", Toast.LENGTH_SHORT)
                            .show();
                }
            }

        }

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
}
