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
package com.sxit.dreamiya.activity.chat;

import java.util.ArrayList;
import java.util.List;

import com.easemob.chat.EMGroupManager;
import com.easemob.chatuidemo.activity.ChatActivity;
import com.easemob.chatuidemo.activity.GroupsActivity;
import com.easemob.chatuidemo.activity.SplashActivity;
import com.easemob.exceptions.EaseMobException;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.adapter.PinyinAdapter;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.entity.ContactInfo;
import com.sxit.dreamiya.entity.user.UserInfo;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.AssortView;
import com.sxit.dreamiya.utils.AssortView.OnTouchAssortListener;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.webservice.SoapRes;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 联系人列表页
 * 
 */
public class ContactActivity extends BaseActivity {
	Context context;
	
	private PinyinAdapter adapter;
	private ExpandableListView eListView;
	private AssortView assortView;
	private List<String> names;
	public static ArrayList<ContactInfo> contactList;

	public static final int CHATTYPE_SINGLE = 1;
	public static final int CHATTYPE_GROUP = 2;
	int userid = 0;
	Button finish_bt;
	
	public static List<String> contact_checked_arrayStrings;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.nim_fragment_contact_list);
		context = this;
		eListView = (ExpandableListView) findViewById(R.id.elist);
		
		assortView = (AssortView) findViewById(R.id.assort);
		getContactList();
		//字母按键回调
		assortView.setOnTouchAssortListener(new OnTouchAssortListener() {
			
			View layoutView=LayoutInflater.from(ContactActivity.this)
					.inflate(R.layout.alert_dialog_menu_layout, null);
			TextView text =(TextView) layoutView.findViewById(R.id.content);
			PopupWindow popupWindow ;
			
			public void onTouchAssortListener(String str) {
			   int index=adapter.getAssort().getHashList().indexOfKey(str);
			   if(index!=-1)
			   {
					eListView.setSelectedGroup(index);;
			   }
				if(popupWindow!=null){
				text.setText(str);
				}
				else
				{   
				      popupWindow = new PopupWindow(layoutView,
							80, 80,
							false);
					// 显示在Activity的根视图中心
					popupWindow.showAtLocation(getWindow().getDecorView(),
							Gravity.CENTER, 0, 0);
				}
				text.setText(str);
			}

			public void onTouchAssortUP() {
				if(popupWindow!=null)
				popupWindow.dismiss();
				popupWindow=null;
			}
		});
		
		finish_bt = (Button) this.findViewById(R.id.finish_bt);
		finish_bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String resString = "";
				int members_count = 0;
				int count_location = 0;
				for (int i = 0; i < contact_checked_arrayStrings.size(); i++) {
					if (!contact_checked_arrayStrings.get(i).equals("0")) {	
						String[] user_array = contact_checked_arrayStrings.get(i).split("%");
						if(resString.equals("")){
							resString = user_array[2];
						}else{							
							resString = resString + "%" + user_array[2];
						}
						members_count += 1;
						count_location = i;
					}
				}
				if(members_count == 0){
					Toast.makeText(context, "请选择联系人", Toast.LENGTH_SHORT).show();
				}else if(members_count == 1){
					String[] user_array = contact_checked_arrayStrings.get(count_location).toString().split("%");
					
					Intent intent = new Intent();
					intent.setClass(ContactActivity.this, ChatActivity.class);
					intent.putExtra("chatType", CHATTYPE_SINGLE);
					intent.putExtra("userId", user_array[2]);
					intent.putExtra("Realname", user_array[0]);
					
					UserInfo userinfo = SplashActivity.userinfo;
					
					intent.putExtra("name", userinfo.getRealName());
					intent.putExtra("toname", user_array[0]);
					intent.putExtra("pic", SOAP_UTILS.PIC_FILE + userinfo.getHeadPic());
					intent.putExtra("topic", SOAP_UTILS.PIC_FILE + contactList.get(count_location).getHeadPic());
					startActivity(intent);
					finish();
				}else{
					final String res = resString;
//				Toast.makeText(context, resString, Toast.LENGTH_SHORT).show();
					final EditText groupname_edit = new EditText(context);
					AlertDialog.Builder exitbuilder = new Builder(ContactActivity.this);
					exitbuilder.setMessage("请输入群名");
					exitbuilder.setTitle("提示");
					exitbuilder.setView(groupname_edit);
					exitbuilder.setPositiveButton("确定",
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
//									Toast.makeText(context, groupname_edit.getText().toString().trim(), Toast.LENGTH_SHORT).show();
									new Thread(new Runnable() {
										@Override
										public void run() {
											// 调用sdk创建群组方法
											Message msg = new Message();
											msg.arg1 = 3;
//											msg.obj = group_info_array;
											threadMessageHandler.sendMessage(msg);
											
											String groupName = groupname_edit.getText().toString().trim();
											String desc = "";//群简介
											String[] members = res.split("%");
											final String[] group_info_array = new String[2];
											group_info_array[0] = groupName;
											group_info_array[1] = res;
											try {
												EMGroupManager.getInstance().createPublicGroup(groupName, desc, members, true);
												runOnUiThread(new Runnable() {
													public void run() {
//														progressDialog.dismiss();
//														setResult(RESULT_OK);
														Message msg = new Message();
														msg.arg1 = 2;
														msg.obj = group_info_array;
														threadMessageHandler.sendMessage(msg);
														finish();
													}
												});
											} catch (final EaseMobException e) {
												runOnUiThread(new Runnable() {
													public void run() {
//														progressDialog.dismiss();
														Toast.makeText(ContactActivity.this, e.getLocalizedMessage(), 1).show();
													}
												});
											}
											
										}
									}).start();
								}	
							});
					exitbuilder.setNegativeButton("取消",
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();
								}
							});
					exitbuilder.show();
				}
			}
		});
		
	}
	
	// 刷新ui
	public void refresh() {
		try {
			// 可能会在子线程中调到这方法
			this.runOnUiThread(new Runnable() {
				public void run() {
					getContactList();
					adapter.notifyDataSetChanged();

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Handler threadMessageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.arg1 == 1) {
				
			}else if (msg.arg1 == 2) {
				String[] group_array = (String[]) msg.obj;
				//进入群聊
				Intent intent = new Intent(ContactActivity.this, GroupsActivity.class);
				// it is group chat
				intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
				intent.putExtra("userId", "");
				intent.putExtra("Realname", group_array[0]);
				
				SharedPreferences sjbSsharedPreferences = getSharedPreferences(
						"BBGJ_UserInfo", Context.MODE_PRIVATE); // 私有数据
				String name = sjbSsharedPreferences.getString("realName", "");
				String pic = sjbSsharedPreferences.getString("headpic", "");
				
				intent.putExtra("name", name);
				intent.putExtra("toname", group_array[0]);
				intent.putExtra("pic", SOAP_UTILS.PIC_FILE + pic);
				intent.putExtra("topic", "");

				startActivity(intent);
				
			} else if (msg.arg1 == 3) {
				ProgressDialog progressDialog = ProgressDialog.show(context, "正在创建群", "请稍等...", true, false);
			}
		}
	};

	/**
	 * 获取联系人列表，并过滤掉黑名单和排序
	 */
	private void getContactList() {
	    Object[] property_va = { SplashActivity.userinfo.getUserId(), 1000, 1 };
        soapService.getUserInfoByClass(property_va);
	}

    @Override
	public void onEvent(Object obj) {
	    super.onEvent(obj);
	    SoapRes res = (SoapRes) obj;
	    if (res.getCode().equals(SOAP_UTILS.METHOD.GETUSERINFOBYCLASS)) {
	        contactList = (ArrayList<ContactInfo>)res.getObj();
	        
	        contact_checked_arrayStrings = new ArrayList<String>();
	        names = new ArrayList<String>();
            for (int i = 0; i < contactList.size(); i++) {
                names.add(contactList.get(i).getPosition());//在SoapService中拼成的参数String
                contact_checked_arrayStrings.add("0");
            }
            
            adapter = new PinyinAdapter(context, names, eListView);
            eListView.setAdapter(adapter);
            
            eListView.setClickable(true);
            eListView.setOnChildClickListener(new OnChildClickListener() {
                
                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                        int groupPosition, int childPosition, long id) {
                    // TODO Auto-generated method stub

                    String[] user_array = adapter.getChild(groupPosition, childPosition).toString().split("%");
                    int position = Integer.valueOf(user_array[1]);
                    CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                        contact_checked_arrayStrings.set(position, "0");
                    }else{
                        checkBox.setChecked(true);
                        contact_checked_arrayStrings.set(position, adapter.getChild(groupPosition, childPosition).toString());
                    }
                    
                    return true;
                }
            });
           //展开所有
            for (int i = 0, length = adapter.getGroupCount(); i < length; i++) {
                eListView.expandGroup(i);
            }
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
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        
    }
	
}
