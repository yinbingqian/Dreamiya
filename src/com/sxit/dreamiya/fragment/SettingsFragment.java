/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sxit.dreamiya.fragment;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.ksoap2.serialization.SoapObject;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chatuidemo.Constant;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.easemob.chatuidemo.activity.MainActivity;
import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.activity.setting.OpinionActivity;
import com.sxit.dreamiya.activity.setting.ResetPasswordActivity;
import com.sxit.dreamiya.activity.setting.SettingsActivity;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.db.DBHelper;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.utils.settingimg.CircularImage;
import com.sxit.dreamiya.utils.settingimg.SelectPicActivity;
import com.sxit.dreamiya.webservice.ISoapService;
import com.sxit.dreamiya.webservice.SoapService;
import com.sxit.dreamiya.webservice.SoapWebService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 设置界面
 * 
 * @author Administrator
 * 
 */
public class SettingsFragment extends Fragment implements OnClickListener {

    /** soapService **/
    public ISoapService soapService = new SoapService();
    /**
     * 退出按钮
     */
    private Button logoutBtn;
    private TextView txtRealName;
    private TextView txtGardenName;

    private RelativeLayout mime_layout_04;
    private RelativeLayout mime_layout_05;
    private RelativeLayout mime_layout_06;
    private Button exitBtn;
    private CircularImage mime_img_01;
    // TextView txt;
    String uploadBuffer = "";
    String picPath = "";
    Bitmap bitmap;

    public static String picName;

    public static final String SERIP = "serverIp";
    public static final String NAME = "name";
    public static final String PASS = "pass";
    public static final String REMEMBER = "remember";
    public static final String AUTOLOG = "autolog";

    // Bundle loginBundle;
    private static String userId = "";
    private static String userName = "";
    private static String realName = "";
    private static String ipString = "";
    private static String gardenName = "";
    private static final String TAG = "uploadImage";

    public static final int TO_SELECT_PHOTO = 3;

    int user_state;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversation_settings,
                container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null
                && savedInstanceState.getBoolean("isConflict", false))
            return;
        context = getActivity();
        initView();

        userId = SplashActivity.userinfo.getUserId();
        
        String url = SOAP_UTILS.PIC_FILE + SplashActivity.userinfo.getHeadPic();
        Instance.imageLoader.displayImage(url, mime_img_01, Instance.user_options);
    }

    private void initView() {
        ipString = SplashActivity.userinfo.getIp();
        userName = SplashActivity.userinfo.getUsername();
        realName = SplashActivity.userinfo.getRealName();
        gardenName = SplashActivity.userinfo.getKinderName();

        txtRealName = (TextView) getView().findViewById(R.id.realname);
        txtGardenName = (TextView) getView().findViewById(R.id.garden_text);

        txtRealName.setText(realName);
        txtGardenName.setText(gardenName);

        mime_layout_04 = (RelativeLayout) getView().findViewById(R.id.mime_layout_04);
        mime_layout_05 = (RelativeLayout) getView().findViewById(R.id.mime_layout_05);
        mime_layout_06 = (RelativeLayout) getView().findViewById(R.id.mime_layout_06);
        mime_img_01 = (CircularImage) getView().findViewById(R.id.mime_img_01);
        exitBtn = (Button) getView().findViewById(R.id.btn_logout);
        logoutBtn = (Button) getView().findViewById(R.id.btn_logout);
        mime_layout_04.setClickable(true);
        mime_layout_05.setClickable(true);
        mime_layout_06.setClickable(true);
        mime_layout_04.setOnClickListener(this);
        mime_layout_05.setOnClickListener(this);
        mime_layout_06.setOnClickListener(this);

        exitBtn.setOnClickListener(this);
        mime_img_01.isClickable();
        mime_img_01.setOnClickListener(this);
        mime_img_01.setImageResource(R.drawable.default_avatar);

        if (!TextUtils.isEmpty(EMChatManager.getInstance().getCurrentUser())) {
            logoutBtn.setText(getString(R.string.button_logout) );
        }

        logoutBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        case R.id.btn_logout: // 退出登陆
            logout();
            break;

        case R.id.mime_img_01:
            Intent intent1 = new Intent(getActivity(), SelectPicActivity.class);
            startActivityForResult(intent1, TO_SELECT_PHOTO);
            break;
        case R.id.mime_layout_04:
             Intent intent_setting = new Intent();
             intent_setting.setClass(getActivity(),
                     SettingsActivity.class);
             startActivity(intent_setting);
             break;

        case R.id.mime_layout_05:
            // 用户反馈
            Intent intent5 = new Intent(getActivity(), OpinionActivity.class);
            startActivity(intent5);
            break;

        case R.id.mime_layout_06:

            Intent intent_settings = new Intent(getActivity(), ResetPasswordActivity.class);
//            getActivity().startActivityForResult(intent_settings, "1");
             startActivity(intent_settings);
             break;
        default:
            break;
        }

    }

    private void sendImage() {
        uploadBuffer = null;
        try {
            FileInputStream fis = new FileInputStream(picPath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = fis.read(buffer)) >= 0) {
                baos.write(buffer, 0, count);
            }
            uploadBuffer = Base64.encodeToString(baos.toByteArray(),
                    Base64.DEFAULT);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String[] property_nm = { "id", "images" };
        String[] property_va = new String[] {SplashActivity.userinfo.getUserId(), uploadBuffer };
        
        new SendHeadPic().execute(property_nm, property_va);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK
                && requestCode == TO_SELECT_PHOTO) {
            // imageView不设null, 第一次上传成功后，第二次在选择上传的时候会报错。
            mime_img_01.setImageBitmap(null);
            picPath = data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH);
            Log.i(TAG, "最终选择的图片=" + picPath);

            sendImage();

            // txt.setText("文件路径" + picPath);
            String[] str = picPath.split("/");
            String strr = str[str.length - 1];
            // title_edit.setText(picPath);
            picName = strr;
            Options ops = new Options();
            // ops.in
            ops.inPreferredConfig = Bitmap.Config.RGB_565;

            ops.inPurgeable = true;

            ops.inInputShareable = true;
            Bitmap bm = BitmapFactory.decodeFile(picPath, ops);

            mime_img_01.setImageBitmap(bm);// 将图片显示在ImageView里
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    void logout() {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        String st = getResources().getString(R.string.Are_logged_out);
        pd.setMessage(st);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        DemoApplication.getInstance().logout(new EMCallBack() {

            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        // 清空本地数据库登录信息
                        DBHelper dbh = new DBHelper(getActivity());
                        int db_res_int = dbh.clearAllUserInfo();

                        pd.dismiss();
                        // 重新显示登陆页面
                        ((MainActivity) getActivity()).finish();
                        startActivity(new Intent(getActivity(), LoginActivity.class));

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (((MainActivity) getActivity()).isConflict) {
            outState.putBoolean("isConflict", true);
        } else if (((MainActivity) getActivity()).getCurrentAccountRemoved()) {
            outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }
    class SendHeadPic extends AsyncTask<Object, Object, Object> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object... params) {
            System.out.println(">>>>>");
            SoapObject res_obj = (SoapObject) SoapWebService.data(SOAP_UTILS.METHOD.GETHEADPICINFO, (String[]) params[0],(Object[]) params[1]);
            
            String headpic_str = res_obj.getProperty("GetHeadPicInfoResult").toString();
                
            return headpic_str;
        }
        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
//            notive_tv.setText("通知：" + result.toString());
            if (result.toString().startsWith("no")) {
                Toast.makeText(getActivity(), "修改失败", Toast.LENGTH_SHORT).show();
            }else {
                DBHelper dbh = new DBHelper(getActivity()) ;
                dbh.setUserPic(result.toString(), SplashActivity.userinfo.getComId());
                SplashActivity.userinfo.setHeadPic(result.toString());
                
                String url = SOAP_UTILS.PIC_FILE + SplashActivity.userinfo.getHeadPic();
                Instance.imageLoader.displayImage(url, mime_img_01, Instance.user_options);

                Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
            }
        }

    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        String url = SOAP_UTILS.PIC_FILE + SplashActivity.userinfo.getHeadPic();
        Instance.imageLoader.displayImage(url, mime_img_01, Instance.user_options);
    }

}
