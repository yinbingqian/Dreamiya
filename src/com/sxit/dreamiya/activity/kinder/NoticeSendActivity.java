package com.sxit.dreamiya.activity.kinder;

import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.md5.MD5Plus;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.webservice.SoapRes;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class NoticeSendActivity extends BaseActivity implements OnClickListener{
    private Context context;
    
    private TextView send_tv;
    private AutoCompleteTextView send_content;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinder_notice_send);

        context = this;
        viewInit();
        setListeners();

    }

    private void viewInit(){
        send_tv = (TextView) findViewById(R.id.send_tv);
        send_content = (AutoCompleteTextView) findViewById(R.id.send_content);
        send_content.setEnabled(true);
       
    }
    
    private void setListeners() {
        send_tv.setClickable(true);
        send_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.send_tv:
            if (send_content.getText().toString().trim().equals("") ) {
                Toast.makeText(context, R.string.send_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            String[] property_va = new String[] { SplashActivity.userinfo.getComId(), send_content.getText().toString().trim(),SplashActivity.userinfo.getPhone()};
            soapService.tranfoNoticeInfoForPhone(property_va);
            break;
        default:
            break;
        }
    }
    /**
     * 返回
     * 
     * @param view
     */
    public void back(View view) {
        finish();
    }
    
    /**
     * http回调SoapObject
     * @param obj   
     */
    @Override
    public void onEvent(Object obj) {
        SoapRes res = (SoapRes) obj;
        //webservice result
        if (res.getCode().equals(SOAP_UTILS.METHOD.TRANFONOTICEINFOFORPHONE)) {
            if (res.getObj() != null) {
                if (res.getObj().toString().equals("ok")) {
                    Toast.makeText(context, R.string.send_success, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(context, R.string.send_failed, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, R.string.send_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }
   
    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
    }
    
}
