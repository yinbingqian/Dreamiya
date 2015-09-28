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
package com.sxit.dreamiya.activity.setting;

import com.easemob.EMCallBack;
import com.easemob.applib.controller.HXSDKHelper;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMChatOptions;
import com.easemob.chatuidemo.Constant;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.DemoHXSDKModel;
import com.easemob.chatuidemo.activity.BlacklistActivity;
import com.easemob.chatuidemo.activity.DiagnoseActivity;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.easemob.chatuidemo.activity.MainActivity;
import com.easemob.chatuidemo.activity.OfflinePushNickActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.db.DBHelper;
import com.sxit.dreamiya.http.RdaResultPack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 设置界面
 * 
 * @author Administrator
 * 
 */
public class SettingsActivity extends BaseActivity implements OnClickListener {

	/**
	 * 设置新消息通知布局
	 */
	private RelativeLayout rl_switch_notification;
	/**
	 * 设置声音布局
	 */
	private RelativeLayout rl_switch_sound;
	/**
	 * 设置震动布局
	 */
	private RelativeLayout rl_switch_vibrate;
	/**
	 * 设置扬声器布局
	 */
	private RelativeLayout rl_switch_speaker;

	/**
	 * 打开新消息通知imageView
	 */
	private ImageView iv_switch_open_notification;
	/**
	 * 关闭新消息通知imageview
	 */
	private ImageView iv_switch_close_notification;
	/**
	 * 打开声音提示imageview
	 */
	private ImageView iv_switch_open_sound;
	/**
	 * 关闭声音提示imageview
	 */
	private ImageView iv_switch_close_sound;
	/**
	 * 打开消息震动提示
	 */
	private ImageView iv_switch_open_vibrate;
	/**
	 * 关闭消息震动提示
	 */
	private ImageView iv_switch_close_vibrate;
	/**
	 * 打开扬声器播放语音
	 */
	private ImageView iv_switch_open_speaker;
	/**
	 * 关闭扬声器播放语音
	 */
	private ImageView iv_switch_close_speaker;

	/**
	 * 声音和震动中间的那条线
	 */
	private TextView textview1, textview2;

	private LinearLayout blacklistContainer;
	private EMChatOptions chatOptions;
 
	/**
	 * 诊断
	 */
	private LinearLayout llDiagnose;
	/**
	 * iOS离线推送昵称
	 */
	
	DemoHXSDKModel model;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_setting_settings);
	        initView();
	}
private void initView(){
    rl_switch_notification = (RelativeLayout)findViewById(R.id.rl_switch_notification);
    rl_switch_sound = (RelativeLayout) findViewById(R.id.rl_switch_sound);
    rl_switch_vibrate = (RelativeLayout) findViewById(R.id.rl_switch_vibrate);
    rl_switch_speaker = (RelativeLayout) findViewById(R.id.rl_switch_speaker);

    iv_switch_open_notification = (ImageView) findViewById(R.id.iv_switch_open_notification);
    iv_switch_close_notification = (ImageView) findViewById(R.id.iv_switch_close_notification);
    iv_switch_open_sound = (ImageView) findViewById(R.id.iv_switch_open_sound);
    iv_switch_close_sound = (ImageView)findViewById(R.id.iv_switch_close_sound);
    iv_switch_open_vibrate = (ImageView) findViewById(R.id.iv_switch_open_vibrate);
    iv_switch_close_vibrate = (ImageView) findViewById(R.id.iv_switch_close_vibrate);
    iv_switch_open_speaker = (ImageView)findViewById(R.id.iv_switch_open_speaker);
    iv_switch_close_speaker = (ImageView) findViewById(R.id.iv_switch_close_speaker);
    textview1 = (TextView) findViewById(R.id.textview1);
    textview2 = (TextView) findViewById(R.id.textview2);
    
    blacklistContainer = (LinearLayout) findViewById(R.id.ll_black_list);
    llDiagnose=(LinearLayout) findViewById(R.id.ll_diagnose);
    
    blacklistContainer.setOnClickListener(this);
    rl_switch_notification.setOnClickListener(this);
    rl_switch_sound.setOnClickListener(this);
    rl_switch_vibrate.setOnClickListener(this);
    rl_switch_speaker.setOnClickListener(this);
    llDiagnose.setOnClickListener(this);
    
    chatOptions = EMChatManager.getInstance().getChatOptions();
    
    model = (DemoHXSDKModel) HXSDKHelper.getInstance().getModel();
    
    // 震动和声音总开关，来消息时，是否允许此开关打开
    // the vibrate and sound notification are allowed or not?
    if (model.getSettingMsgNotification()) {
        iv_switch_open_notification.setVisibility(View.VISIBLE);
        iv_switch_close_notification.setVisibility(View.INVISIBLE);
    } else {
        iv_switch_open_notification.setVisibility(View.INVISIBLE);
        iv_switch_close_notification.setVisibility(View.VISIBLE);
    }
    
    // 是否打开声音
    // sound notification is switched on or not?
    if (model.getSettingMsgSound()) {
        iv_switch_open_sound.setVisibility(View.VISIBLE);
        iv_switch_close_sound.setVisibility(View.INVISIBLE);
    } else {
        iv_switch_open_sound.setVisibility(View.INVISIBLE);
        iv_switch_close_sound.setVisibility(View.VISIBLE);
    }
    
    // 是否打开震动
    // vibrate notification is switched on or not?
    if (model.getSettingMsgVibrate()) {
        iv_switch_open_vibrate.setVisibility(View.VISIBLE);
        iv_switch_close_vibrate.setVisibility(View.INVISIBLE);
    } else {
        iv_switch_open_vibrate.setVisibility(View.INVISIBLE);
        iv_switch_close_vibrate.setVisibility(View.VISIBLE);
    }

    // 是否打开扬声器
    // the speaker is switched on or not?
    if (model.getSettingMsgSpeaker()) {
        iv_switch_open_speaker.setVisibility(View.VISIBLE);
        iv_switch_close_speaker.setVisibility(View.INVISIBLE);
    } else {
        iv_switch_open_speaker.setVisibility(View.INVISIBLE);
        iv_switch_close_speaker.setVisibility(View.VISIBLE);
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
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_switch_notification:
			if (iv_switch_open_notification.getVisibility() == View.VISIBLE) {
				iv_switch_open_notification.setVisibility(View.INVISIBLE);
				iv_switch_close_notification.setVisibility(View.VISIBLE);
				rl_switch_sound.setVisibility(View.GONE);
				rl_switch_vibrate.setVisibility(View.GONE);
				textview1.setVisibility(View.GONE);
				textview2.setVisibility(View.GONE);
				chatOptions.setNotificationEnable(false);
				EMChatManager.getInstance().setChatOptions(chatOptions);

				HXSDKHelper.getInstance().getModel().setSettingMsgNotification(false);
			} else {
				iv_switch_open_notification.setVisibility(View.VISIBLE);
				iv_switch_close_notification.setVisibility(View.INVISIBLE);
				rl_switch_sound.setVisibility(View.VISIBLE);
				rl_switch_vibrate.setVisibility(View.VISIBLE);
				textview1.setVisibility(View.VISIBLE);
				textview2.setVisibility(View.VISIBLE);
				chatOptions.setNotificationEnable(true);
				EMChatManager.getInstance().setChatOptions(chatOptions);
				HXSDKHelper.getInstance().getModel().setSettingMsgNotification(true);
			}
			break;
		case R.id.rl_switch_sound:
			if (iv_switch_open_sound.getVisibility() == View.VISIBLE) {
				iv_switch_open_sound.setVisibility(View.INVISIBLE);
				iv_switch_close_sound.setVisibility(View.VISIBLE);
				chatOptions.setNoticeBySound(false);
				EMChatManager.getInstance().setChatOptions(chatOptions);
				HXSDKHelper.getInstance().getModel().setSettingMsgSound(false);
			} else {
				iv_switch_open_sound.setVisibility(View.VISIBLE);
				iv_switch_close_sound.setVisibility(View.INVISIBLE);
				chatOptions.setNoticeBySound(true);
				EMChatManager.getInstance().setChatOptions(chatOptions);
				HXSDKHelper.getInstance().getModel().setSettingMsgSound(true);
			}
			break;
		case R.id.rl_switch_vibrate:
			if (iv_switch_open_vibrate.getVisibility() == View.VISIBLE) {
				iv_switch_open_vibrate.setVisibility(View.INVISIBLE);
				iv_switch_close_vibrate.setVisibility(View.VISIBLE);
				chatOptions.setNoticedByVibrate(false);
				EMChatManager.getInstance().setChatOptions(chatOptions);
				HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(false);
			} else {
				iv_switch_open_vibrate.setVisibility(View.VISIBLE);
				iv_switch_close_vibrate.setVisibility(View.INVISIBLE);
				chatOptions.setNoticedByVibrate(true);
				EMChatManager.getInstance().setChatOptions(chatOptions);
				HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(true);
			}
			break;
		case R.id.rl_switch_speaker:
			if (iv_switch_open_speaker.getVisibility() == View.VISIBLE) {
				iv_switch_open_speaker.setVisibility(View.INVISIBLE);
				iv_switch_close_speaker.setVisibility(View.VISIBLE);
				chatOptions.setUseSpeaker(false);
				EMChatManager.getInstance().setChatOptions(chatOptions);
				HXSDKHelper.getInstance().getModel().setSettingMsgSpeaker(false);
			} else {
				iv_switch_open_speaker.setVisibility(View.VISIBLE);
				iv_switch_close_speaker.setVisibility(View.INVISIBLE);
				chatOptions.setUseSpeaker(true);
				EMChatManager.getInstance().setChatOptions(chatOptions);
				HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(true);
			}
			break;
		case R.id.ll_black_list:
			startActivity(new Intent(this, BlacklistActivity.class));
			break;
		case R.id.ll_diagnose:
			startActivity(new Intent(this, DiagnoseActivity.class));
			break;
		default:
			break;
		}
		
	}

    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        
    }

}
