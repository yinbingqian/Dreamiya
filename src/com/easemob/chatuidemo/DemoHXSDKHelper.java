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
package com.easemob.chatuidemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.EMChatRoomChangeListener;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.applib.controller.HXSDKHelper;
import com.easemob.applib.model.HXNotifier;
import com.easemob.applib.model.HXNotifier.HXNotificationInfoProvider;
import com.easemob.applib.model.HXSDKModel;
import com.easemob.chat.CmdMessageBody;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMChatOptions;
import com.easemob.chat.EMContact;
import com.easemob.chat.EMMessage;
import com.easemob.chat.EMMessage.ChatType;
import com.easemob.chat.EMMessage.Type;
import com.easemob.chatuidemo.activity.ChatActivity;
import com.easemob.chatuidemo.activity.MainActivity;
import com.easemob.chatuidemo.activity.VideoCallActivity;
import com.easemob.chatuidemo.activity.VoiceCallActivity;
import com.easemob.chatuidemo.domain.RobotUser;
import com.easemob.chatuidemo.domain.User;
import com.easemob.chatuidemo.receiver.CallReceiver;
import com.easemob.chatuidemo.utils.CommonUtils;
import com.easemob.exceptions.EaseMobException;
import com.easemob.util.EMLog;
import com.easemob.util.EasyUtils;
import com.sxit.dreamiya.R;

/**
 * Demo UI HX SDK helper class which subclass HXSDKHelper
 * @author easemob
 *
 */
public class DemoHXSDKHelper extends HXSDKHelper{

    private static final String TAG = "DemoHXSDKHelper";
    
    /**
     * EMEventListener
     */
    protected EMEventListener eventListener = null;

    /**
     * contact list in cache
     */
    private Map<String, User> contactList;
    
    /**
     * robot list in cache
     */
    private Map<String, RobotUser> robotList;
    private CallReceiver callReceiver;
    
    
    /**
     * 鐢ㄦ潵璁板綍foreground Activity
     */
    private List<Activity> activityList = new ArrayList<Activity>();
    
    public void pushActivity(Activity activity){
        if(!activityList.contains(activity)){
            activityList.add(0,activity); 
        }
    }
    
    public void popActivity(Activity activity){
        activityList.remove(activity);
    }
    
    @Override
    protected void initHXOptions(){
        super.initHXOptions();

        // you can also get EMChatOptions to set related SDK options
        EMChatOptions options = EMChatManager.getInstance().getChatOptions();
        options.allowChatroomOwnerLeave(getModel().isChatroomOwnerLeaveAllowed());  
    }

    @Override
    protected void initListener(){
        super.initListener();
        IntentFilter callFilter = new IntentFilter(EMChatManager.getInstance().getIncomingCallBroadcastAction());
        if(callReceiver == null){
            callReceiver = new CallReceiver();
        }

        //娉ㄥ唽閫氳瘽骞挎挱鎺ユ敹鑰�
        appContext.registerReceiver(callReceiver, callFilter);    
        //娉ㄥ唽娑堟伅浜嬩欢鐩戝惉
        initEventListener();
    }
    
    /**
     * 鍏ㄥ眬浜嬩欢鐩戝惉
     * 鍥犱负鍙兘浼氭湁UI椤甸潰鍏堝鐞嗗埌杩欎釜娑堟伅锛屾墍浠ヤ竴鑸鏋淯I椤甸潰宸茬粡澶勭悊锛岃繖閲屽氨涓嶉渶瑕佸啀娆″鐞�
     * activityList.size() <= 0 鎰忓懗鐫�鎵�鏈夐〉闈㈤兘宸茬粡鍦ㄥ悗鍙拌繍琛岋紝鎴栬�呭凡缁忕寮�Activity Stack
     */
    protected void initEventListener() {
        eventListener = new EMEventListener() {
            private BroadcastReceiver broadCastReceiver = null;
            
            @Override
            public void onEvent(EMNotifierEvent event) {
                EMMessage message = null;
                if(event.getData() instanceof EMMessage){
                    message = (EMMessage)event.getData();
                    EMLog.d(TAG, "receive the event : " + event.getEvent() + ",id : " + message.getMsgId());
                }
                
                switch (event.getEvent()) {
                case EventNewMessage:
                    //搴旂敤鍦ㄥ悗鍙帮紝涓嶉渶瑕佸埛鏂癠I,閫氱煡鏍忔彁绀烘柊娑堟伅
                    if(activityList.size() <= 0){
                        HXSDKHelper.getInstance().getNotifier().onNewMsg(message);
                    }
                    break;
                case EventOfflineMessage:
                    if(activityList.size() <= 0){
                        EMLog.d(TAG, "received offline messages");
                        List<EMMessage> messages = (List<EMMessage>) event.getData();
                        HXSDKHelper.getInstance().getNotifier().onNewMesg(messages);
                    }
                    break;
                // below is just giving a example to show a cmd toast, the app should not follow this
                // so be careful of this
                case EventNewCMDMessage:
                {
                    
                    EMLog.d(TAG, "鏀跺埌閫忎紶娑堟伅");
                    //鑾峰彇娑堟伅body
                    CmdMessageBody cmdMsgBody = (CmdMessageBody) message.getBody();
                    final String action = cmdMsgBody.action;//鑾峰彇鑷畾涔塧ction
                    
                    //鑾峰彇鎵╁睍灞炴�� 姝ゅ鐪佺暐
                    //message.getStringAttribute("");
                    EMLog.d(TAG, String.format("閫忎紶娑堟伅锛歛ction:%s,message:%s", action,message.toString()));
                    final String str = appContext.getString(R.string.receive_the_passthrough);
                    
                    final String CMD_TOAST_BROADCAST = "easemob.demo.cmd.toast";
                    IntentFilter cmdFilter = new IntentFilter(CMD_TOAST_BROADCAST);
                    
                    if(broadCastReceiver == null){
                        broadCastReceiver = new BroadcastReceiver(){

                            @Override
                            public void onReceive(Context context, Intent intent) {
                                // TODO Auto-generated method stub
                                Toast.makeText(appContext, intent.getStringExtra("cmd_value"), Toast.LENGTH_SHORT).show();
                            }
                        };
                        
                      //娉ㄥ唽骞挎挱鎺ユ敹鑰�
                        appContext.registerReceiver(broadCastReceiver,cmdFilter);
                    }

                    Intent broadcastIntent = new Intent(CMD_TOAST_BROADCAST);
                    broadcastIntent.putExtra("cmd_value", str+action);
                    appContext.sendBroadcast(broadcastIntent, null);
                    
                    break;
                }
                case EventDeliveryAck:
                    message.setDelivered(true);
                    break;
                case EventReadAck:
                    message.setAcked(true);
                    break;
                // add other events in case you are interested in
                default:
                    break;
                }
                
            }
        };
        
        EMChatManager.getInstance().registerEventListener(eventListener);
        
        EMChatManager.getInstance().addChatRoomChangeListener(new EMChatRoomChangeListener(){
            private final static String ROOM_CHANGE_BROADCAST = "easemob.demo.chatroom.changeevent.toast";
            private final IntentFilter filter = new IntentFilter(ROOM_CHANGE_BROADCAST);
            private boolean registered = false;
            
            private void showToast(String value){
                if(!registered){
                  //娉ㄥ唽骞挎挱鎺ユ敹鑰�
                    appContext.registerReceiver(new BroadcastReceiver(){

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            Toast.makeText(appContext, intent.getStringExtra("value"), Toast.LENGTH_SHORT).show();
                        }
                        
                    }, filter);
                    
                    registered = true;
                }
                
                Intent broadcastIntent = new Intent(ROOM_CHANGE_BROADCAST);
                broadcastIntent.putExtra("value", value);
                appContext.sendBroadcast(broadcastIntent, null);
            }
            
            @Override
            public void onChatRoomDestroyed(String roomId, String roomName) {
                showToast(" room : " + roomId + " with room name : " + roomName + " was destroyed");
                Log.i("info","onChatRoomDestroyed="+roomName);
            }

            @Override
            public void onMemberJoined(String roomId, String participant) {
                showToast("member : " + participant + " join the room : " + roomId);
                Log.i("info", "onmemberjoined="+participant);
                
            }

            @Override
            public void onMemberExited(String roomId, String roomName,
                    String participant) {
                showToast("member : " + participant + " leave the room : " + roomId + " room name : " + roomName);
                Log.i("info", "onMemberExited="+participant);
                
            }

            @Override
            public void onMemberKicked(String roomId, String roomName,
                    String participant) {
                showToast("member : " + participant + " was kicked from the room : " + roomId + " room name : " + roomName);
                Log.i("info", "onMemberKicked="+participant);
                
            }

        });
    }

    /**
     * 鑷畾涔夐�氱煡鏍忔彁绀哄唴瀹�
     * @return
     */
    @Override
    protected HXNotificationInfoProvider getNotificationListener() {
        //鍙互瑕嗙洊榛樿鐨勮缃�
        return new HXNotificationInfoProvider() {
            
            @Override
            public String getTitle(EMMessage message) {
              //淇敼鏍囬,杩欓噷浣跨敤榛樿
                return null;
            }
            
            @Override
            public int getSmallIcon(EMMessage message) {
              //璁剧疆灏忓浘鏍囷紝杩欓噷涓洪粯璁�
                return 0;
            }
            
            @Override
            public String getDisplayedText(EMMessage message) {
                // 璁剧疆鐘舵�佹爮鐨勬秷鎭彁绀猴紝鍙互鏍规嵁message鐨勭被鍨嬪仛鐩稿簲鎻愮ず
                String ticker = CommonUtils.getMessageDigest(message, appContext);
                if(message.getType() == Type.TXT){
                    ticker = ticker.replaceAll("\\[.{2,3}\\]", "[琛ㄦ儏]");
                }
                Map<String,RobotUser> robotMap=((DemoHXSDKHelper)HXSDKHelper.getInstance()).getRobotList();
    			if(robotMap!=null&&robotMap.containsKey(message.getFrom())){
    				String nick = robotMap.get(message.getFrom()).getNick();
    				if(!TextUtils.isEmpty(nick)){
    					return nick + ": " + ticker;
    				}else{
    					return message.getFrom() + ": " + ticker;
    				}
    			}else{
    				return message.getFrom() + ": " + ticker;
    			}
            }
            
            @Override
            public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
                return null;
                // return fromUsersNum + "涓熀鍙嬶紝鍙戞潵浜�" + messageNum + "鏉℃秷鎭�";
            }
            
            @Override
            public Intent getLaunchIntent(EMMessage message) {
                //璁剧疆鐐瑰嚮閫氱煡鏍忚烦杞簨浠�
                Intent intent = new Intent(appContext, ChatActivity.class);
                //鏈夌數璇濇椂浼樺厛璺宠浆鍒伴�氳瘽椤甸潰
                if(isVideoCalling){
                    intent = new Intent(appContext, VideoCallActivity.class);
                }else if(isVoiceCalling){
                    intent = new Intent(appContext, VoiceCallActivity.class);
                }else{
                    ChatType chatType = message.getChatType();
                    if (chatType == ChatType.Chat) { // 鍗曡亰淇℃伅
                        intent.putExtra("userId", message.getFrom());
                        intent.putExtra("chatType", ChatActivity.CHATTYPE_SINGLE);
                    } else { // 缇よ亰淇℃伅
                        // message.getTo()涓虹兢鑱奿d
                        intent.putExtra("groupId", message.getTo());
                        if(chatType == ChatType.GroupChat){
                            intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
                        }else{
                            intent.putExtra("chatType", ChatActivity.CHATTYPE_CHATROOM);
                        }
                        
                    }
                }
                return intent;
            }
        };
    }
    
    
    
    @Override
    protected void onConnectionConflict(){
        Intent intent = new Intent(appContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("conflict", true);
        appContext.startActivity(intent);
    }
    
    @Override
    protected void onCurrentAccountRemoved(){
    	Intent intent = new Intent(appContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.ACCOUNT_REMOVED, true);
        appContext.startActivity(intent);
    }
    

    @Override
    protected HXSDKModel createModel() {
        return new DemoHXSDKModel(appContext);
    }
    
    @Override
    public HXNotifier createNotifier(){
        return new HXNotifier(){
            public synchronized void onNewMsg(final EMMessage message) {
                if(EMChatManager.getInstance().isSlientMessage(message)){
                    return;
                }
                
                String chatUsename = null;
                List<String> notNotifyIds = null;
                // 鑾峰彇璁剧疆鐨勪笉鎻愮ず鏂版秷鎭殑鐢ㄦ埛鎴栬�呯兢缁刬ds
                if (message.getChatType() == ChatType.Chat) {
                    chatUsename = message.getFrom();
                    notNotifyIds = ((DemoHXSDKModel) hxModel).getDisabledGroups();
                } else {
                    chatUsename = message.getTo();
                    notNotifyIds = ((DemoHXSDKModel) hxModel).getDisabledIds();
                }

                if (notNotifyIds == null || !notNotifyIds.contains(chatUsename)) {
                    // 鍒ゆ柇app鏄惁鍦ㄥ悗鍙�
                    if (!EasyUtils.isAppRunningForeground(appContext)) {
                        EMLog.d(TAG, "app is running in backgroud");
                        sendNotification(message, false);
                    } else {
                        sendNotification(message, true);

                    }
                    
                    viberateAndPlayTone(message);
                }
            }
        };
    }
    
    /**
     * get demo HX SDK Model
     */
    public DemoHXSDKModel getModel(){
        return (DemoHXSDKModel) hxModel;
    }
    
    /**
     * 鑾峰彇鍐呭瓨涓ソ鍙媢ser list
     *
     * @return
     */
    public Map<String, User> getContactList() {
        if (getHXId() != null && contactList == null) {
            contactList = ((DemoHXSDKModel) getModel()).getContactList();
        }
        
        return contactList;
    }
    
	public Map<String, RobotUser> getRobotList() {
		if (getHXId() != null && robotList == null) {
			robotList = ((DemoHXSDKModel) getModel()).getRobotList();
		}
		return robotList;
	}
	
	
	public boolean isRobotMenuMessage(EMMessage message) {

		try {
			JSONObject jsonObj = message.getJSONObjectAttribute(Constant.MESSAGE_ATTR_ROBOT_MSGTYPE);
			if (jsonObj.has("choice")) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	public String getRobotMenuMessageDigest(EMMessage message) {
		String title = "";
		try {
			JSONObject jsonObj = message.getJSONObjectAttribute(Constant.MESSAGE_ATTR_ROBOT_MSGTYPE);
			if (jsonObj.has("choice")) {
				JSONObject jsonChoice = jsonObj.getJSONObject("choice");
				title = jsonChoice.getString("title");
			}
		} catch (Exception e) {
		}
		return title;
	}
	
	
	

    public void setRobotList(Map<String, RobotUser> robotList){
    	this.robotList = robotList;
    }
    
    /**
     * 璁剧疆濂藉弸user list鍒板唴瀛樹腑
     *
     * @param contactList
     */
    public void setContactList(Map<String, User> contactList) {
        this.contactList = contactList;
    }
    
    @Override
    public void logout(final EMCallBack callback){
        endCall();
        super.logout(new EMCallBack(){

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                setContactList(null);
                setRobotList(null);
                getModel().closeDB();
                if(callback != null){
                    callback.onSuccess();
                }
            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub
                if(callback != null){
                    callback.onProgress(progress, status);
                }
            }
            
        });
    }   
    
    void endCall(){
        try {
            EMChatManager.getInstance().endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
