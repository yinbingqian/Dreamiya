package com.sxit.dreamiya.activity.setting;

import com.easemob.EMCallBack;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.easemob.chatuidemo.activity.MainActivity;
import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.db.DBHelper;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.webservice.SoapRes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class ResetPasswordActivity extends BaseActivity implements OnClickListener{
    private Context context;

    private String password;
    private TextView sure_tv;
    private AutoCompleteTextView actv_password,actv_password_enable,actv_password_enable_sure;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_resetpassword);

        context = this;
        intent = getIntent();
//        password = SplashActivity.userinfo.getPassword();
        initView();
        setListeners();

    }
    
    
    private void initView(){
        sure_tv = (TextView) findViewById(R.id.sure_tv);
        actv_password = (AutoCompleteTextView) findViewById(R.id.actv_password);
        actv_password_enable = (AutoCompleteTextView) findViewById(R.id.actv_password_enable);
        actv_password_enable_sure = (AutoCompleteTextView) findViewById(R.id.actv_password_enable_sure);
        actv_password.setEnabled(true);
        actv_password_enable.setEnabled(true);
        actv_password_enable_sure.setEnabled(true);
    } 
    private void setListeners() {
        sure_tv.setOnClickListener(this);
    }


    /**
     * 返回
     * 
     * @param view
     */
    public void back(View view) {
        finish();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.sure_tv:

            String oldpwd = actv_password.getText().toString().trim();
            String newpwd = actv_password_enable.getText().toString().trim();
            String newpwd_sure =actv_password_enable_sure.getText().toString().trim();
            if (actv_password.getText().toString().trim().equals("") || actv_password_enable.getText().toString().trim().equals("")
                    || actv_password_enable_sure.getText().toString().trim().equals("")) {

                Toast.makeText(context, R.string.password_empty, Toast.LENGTH_SHORT).show();
                break;
            }else if(newpwd.equals(newpwd_sure)){
                String[] property_va = new String[] { SplashActivity.userinfo.getPhone(), oldpwd, newpwd };
                soapService.changePWInfo(property_va);
            }else{
                Toast.makeText(context, R.string.resetpw_sure, Toast.LENGTH_SHORT).show();
            }
            
            break;
        default:
            break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
    void logout() {
        final ProgressDialog pd = new ProgressDialog(context);
        String st = getResources().getString(R.string.Are_logged_out);
        pd.setMessage(st);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        DemoApplication.getInstance().logout(new EMCallBack() {

            @Override
            public void onSuccess() {
                ResetPasswordActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        // 清空本地数据库登录信息
                        DBHelper dbh = new DBHelper(context);
                        int db_res_int = dbh.clearAllUserInfo();

                        pd.dismiss();
                        // 重新显示登陆页面
                        finish();
                        MainActivity.instance.finish();
                        startActivity(new Intent(context, LoginActivity.class));

                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }

    public void onEvent(SoapRes res) {
        if (res.getCode().equals(SOAP_UTILS.METHOD.CHANGEPWINFO)) {
            if (res.getObj() != null) {
                if (res.getObj().toString().equals("1")) {
//                    SplashActivity.userinfo.setPassword(actv_password_enable.getText().toString().trim());
                    logout();
                    Toast.makeText(context, R.string.resetpw_success, Toast.LENGTH_SHORT).show();
                } else if (res.getObj().toString().equals("2")) {
                    Toast.makeText(context, R.string.pw_false, Toast.LENGTH_SHORT).show();
                }
            } else if (res.getObj().toString().equals("3")){
                Toast.makeText(context, R.string.resetpw_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        
    }

}
