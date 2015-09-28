package com.sxit.dreamiya.activity.setting;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.webservice.SoapRes;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OpinionActivity extends BaseActivity implements OnClickListener{
    private Context context;
    
    private TextView send_tv;
    private AutoCompleteTextView send_content;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_opinion);

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
            Toast.makeText(context, R.string.send_success, Toast.LENGTH_SHORT).show();
            finish();;
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
           
//                    Toast.makeText(context, R.string.send_success, Toast.LENGTH_SHORT).show();
//                    finish();
    }
   
    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
    }
    
}
