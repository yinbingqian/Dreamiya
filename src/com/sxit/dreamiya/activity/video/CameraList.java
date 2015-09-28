package com.sxit.dreamiya.activity.video;

import java.util.List;

import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.adapter.video.CameraListAdapter;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.db.DBHelper;
import com.sxit.dreamiya.entity.user.UserInfo;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.utils.video.NetUtil;
import com.sxit.dreamiya.utils.video.libvlc.LibVLC;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class CameraList extends BaseActivity implements OnItemClickListener {

    protected static final int REQUEST_CODE = 0;
    private ListView cameralist;
    private List<UserInfo> cameraItem;
    private CameraListAdapter cameraAdapter;
    public NetUtil netUtil = new NetUtil();

    public int screenWidth = 0;
    public int screenHeight = 0;
    public static int clear = 0;

    private String userName = "";// 用户名
    private String passWord = "";// 密码
    private String serverIp = "";// 管理平台IP
    private String socketIp = "";// 转发IP
    private String userId;
    private String ifPtz;
    private String ifMap;
    private String ifRecord;
    private String ifSnap;
    private Handler handler = null;
    
    Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 禁止锁屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.cameralist);

        context = this;

        userId = SplashActivity.userinfo.getUserId();
        userName = SplashActivity.userinfo.getUsername();// 获取传过来的用户名
        passWord = SplashActivity.userinfo.getPassword();// 获取传过来的密码
        ifPtz = SplashActivity.userinfo.getIp();
        ifMap = SplashActivity.userinfo.getMap();
        ifRecord = SplashActivity.userinfo.getRecord();
        ifSnap = SplashActivity.userinfo.getSnap();

        // 创建属于主线程的handler
        handler = new Handler();

        initDevList();
    }

    private void initDevList() {

        cameralist = (ListView) findViewById(R.id.camera_list);
        DBHelper dbh = new DBHelper(context);
        cameraItem = dbh.queryUserInfo();

        serverIp = SOAP_UTILS.IP_SIMPLE;
        socketIp = SOAP_UTILS.IP_SIMPLE;

        int i = 0;
        UserInfo map = null;
        for (i = 0; i < cameraItem.size(); i++) {
            map = (UserInfo) cameraItem.get(i);
            if (!ifPtz.equals("1")){
                map.setPtz("0");// 是否支持云台，如果支持，传1，不支持，传0
                map.setZoom("0");// 是否支持云台，如果支持，传1，不支持，传0
                map.setTalk("0");// 是否支持云台，如果支持，传1，不支持，传0
            }
            map.setType("dev");// 传1
        }

        cameraAdapter = new CameraListAdapter(this, R.layout.camera_row, cameraItem);
        cameralist.setAdapter(cameraAdapter);
        cameralist.setOnItemClickListener(this);
    }

    public static final int UPDATE_ID = Menu.FIRST;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // menu.add(0, UPDATE_ID, 0, R.string.refreshvideo);
        return true;
    }
    /*
     * start to view
     */
    String port = "";
    String stayLine = "";
    Boolean ifCanBoolean = true;

    public final static String TAG = "VLC/VideoPlayerActivity";

    private LibVLC mLibVLC = null;

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long id) {
        if (R.id.camera_list == arg0.getId()) {

            final String selectName = cameraItem.get(arg2).getUserId();
            final String selectType = cameraItem.get(arg2).getType();
            final String rtsp = cameraItem.get(arg2).getRtsp();
            final String devType = cameraItem.get(arg2).getType();

            if (cameraItem.get(arg2).getStayline().equals("0")) {
                Toast toast = Toast.makeText(CameraList.this, "视频离线无法观看！",
                        Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            if (devType.equals("DHZL")) {
//                Bundle bundle = this.getIntent().getExtras();
//                Intent intent = new Intent(CameraList.this,
//                        DhPlayerActivity.class);
//                intent.putExtra("devName", selectName);
//                intent.putExtra("userId", userId);
//                intent.putExtra("chName", cameraItem.get(arg2).get("chName")
//                        .toString());
//                intent.putExtra("devId", cameraItem.get(arg2).get("devId")
//                        .toString());
//                intent.putExtra("ip", cameraItem.get(arg2).get("ip").toString());
//                intent.putExtra("port", cameraItem.get(arg2).get("port")
//                        .toString());
//                intent.putExtra("devUserName",
//                        cameraItem.get(arg2).get("devUserName").toString());
//                intent.putExtra("devPassWord",
//                        cameraItem.get(arg2).get("devPassWord").toString());
//                intent.putExtra("chNo", cameraItem.get(arg2).get("chNo")
//                        .toString());
//                intent.putExtra("listCount",
//                        cameraItem.get(arg2).get("listCount").toString());
//                intent.putExtra("listNo", cameraItem.get(arg2).get("listNo")
//                        .toString());
//                intent.putExtra("width", cameraItem.get(arg2).get("width")
//                        .toString());
//                intent.putExtra("height", cameraItem.get(arg2).get("height")
//                        .toString());
//                intent.putExtra("adapterId",
//                        cameraItem.get(arg2).get("adapterId").toString());
//                intent.putExtra("ptz", cameraItem.get(arg2).get("ptz")
//                        .toString());
//                intent.putExtra("zoom", cameraItem.get(arg2).get("zoom")
//                        .toString());
//                intent.putExtra("talk", cameraItem.get(arg2).get("talk")
//                        .toString());
//                intent.putExtra("socketIp", socketIp);
//                intent.putExtra("ifRecord", ifRecord);
//                intent.putExtra("ifSnap", ifSnap);
//                intent.putExtras(bundle);
//
//                startActivity(intent);
            } else {
                if (selectType.equals("dev")) {
                    if (rtsp.equals("0")) {

//                        Bundle bundle = this.getIntent().getExtras();
//
//                        Intent intent = new Intent(CameraList.this,
//                                ShowVideo.class);
//                        intent.putExtra("devName", selectName);
//                        intent.putExtra("userId", userId);
//                        intent.putExtra("chName",
//                                cameraItem.get(arg2).get("chName").toString());
//                        intent.putExtra("devId",
//                                cameraItem.get(arg2).get("devId").toString());
//                        intent.putExtra("ip", cameraItem.get(arg2).get("ip")
//                                .toString());
//                        intent.putExtra("port", cameraItem.get(arg2)
//                                .get("port").toString());
//                        intent.putExtra("chNo", cameraItem.get(arg2)
//                                .get("chNo").toString());
//                        intent.putExtra("listCount",
//                                cameraItem.get(arg2).get("listCount")
//                                        .toString());
//                        intent.putExtra("listNo",
//                                cameraItem.get(arg2).get("listNo").toString());
//                        intent.putExtra("width",
//                                cameraItem.get(arg2).get("width").toString());
//                        intent.putExtra("height",
//                                cameraItem.get(arg2).get("height").toString());
//                        intent.putExtra("adapterId",
//                                cameraItem.get(arg2).get("adapterId")
//                                        .toString());
//                        intent.putExtra("ptz", cameraItem.get(arg2).get("ptz")
//                                .toString());
//                        intent.putExtra("zoom", cameraItem.get(arg2)
//                                .get("zoom").toString());
//                        intent.putExtra("talk", cameraItem.get(arg2)
//                                .get("talk").toString());
//                        intent.putExtra("socketIp", socketIp);
//                        intent.putExtra("ifRecord", ifRecord);
//                        intent.putExtra("ifSnap", ifSnap);
//                        intent.putExtras(bundle);
//
//                        startActivity(intent);
                    } else if (rtsp.equals("1")) {

//                        try {
//                            mLibVLC = Util.getLibVlcInstance();
//                        } catch (LibVlcException e) {
//                            e.printStackTrace();
//                        }
//                        String chNo = cameraItem.get(arg2).get("chNo")
//                                .toString();
//                        int addOneNo = Integer.parseInt(chNo) + 1;
//                        String rtspUrl = "";
//                        if (socketIp.contains(".net:")
//                                || socketIp.contains(".com:")) {
//                            rtspUrl = "rtsp://"
//                                    + socketIp
//                                    + "/"
//                                    + cameraItem.get(arg2).get("devId")
//                                            .toString() + "_"
//                                    + Integer.toString(addOneNo);
//                        } else {
//                            rtspUrl = "rtsp://" + socketIp + ":554/" + cameraItem.get(arg2).get("devId")
//                                            .toString() + "_"
//                                    + Integer.toString(addOneNo);
//                        }
//                        System.out.println("rtspUrl:" + rtspUrl);
//                        Bundle bundle = this.getIntent().getExtras();
//                        Intent intent = new Intent(CameraList.this,
//                                VideoPlayerActivity.class);
//                        intent.putExtra("rtspUrl", rtspUrl);
//                        intent.putExtra("devName", selectName);
//                        intent.putExtra("userId", userId);
//                        intent.putExtra("chName",
//                                cameraItem.get(arg2).get("chName").toString());
//                        intent.putExtra("devId",
//                                cameraItem.get(arg2).get("devId").toString());
//                        intent.putExtra("ip", cameraItem.get(arg2).get("ip")
//                                .toString());
//                        intent.putExtra("port", cameraItem.get(arg2)
//                                .get("port").toString());
//                        intent.putExtra("chNo", cameraItem.get(arg2)
//                                .get("chNo").toString());
//                        intent.putExtra("listCount",
//                                cameraItem.get(arg2).get("listCount")
//                                        .toString());
//                        intent.putExtra("listNo",
//                                cameraItem.get(arg2).get("listNo").toString());
//                        intent.putExtra("width",
//                                cameraItem.get(arg2).get("width").toString());
//                        intent.putExtra("height",
//                                cameraItem.get(arg2).get("height").toString());
//                        intent.putExtra("adapterId",
//                                cameraItem.get(arg2).get("adapterId")
//                                        .toString());
//                        intent.putExtra("ptz", cameraItem.get(arg2).get("ptz")
//                                .toString());
//                        intent.putExtra("zoom", cameraItem.get(arg2)
//                                .get("zoom").toString());
//                        intent.putExtra("talk", cameraItem.get(arg2)
//                                .get("talk").toString());
//                        intent.putExtra("socketIp", socketIp);
//                        intent.putExtra("ifRecord", ifRecord);
//                        intent.putExtra("ifSnap", ifSnap);
//                        intent.putExtras(bundle);
//
//                        startActivity(intent);

                    } else if (rtsp.equals("2")) {

//                        Bundle bundle = this.getIntent().getExtras();
//                        Intent intent = new Intent(CameraList.this,
//                                DhPlayerActivity.class);
//                        intent.putExtra("devName", selectName);
//                        intent.putExtra("userId", userId);
//                        intent.putExtra("chName",
//                                cameraItem.get(arg2).get("chName").toString());
//                        intent.putExtra("devId",
//                                cameraItem.get(arg2).get("devId").toString());
//                        intent.putExtra("ip", cameraItem.get(arg2).get("ip")
//                                .toString());
//                        intent.putExtra("port", cameraItem.get(arg2)
//                                .get("port").toString());
//                        intent.putExtra("devUserName", cameraItem.get(arg2)
//                                .get("devUserName").toString());
//                        intent.putExtra("devPassWord", cameraItem.get(arg2)
//                                .get("devPassWord").toString());
//                        intent.putExtra("chNo", cameraItem.get(arg2)
//                                .get("chNo").toString());
//                        intent.putExtra("listCount",
//                                cameraItem.get(arg2).get("listCount")
//                                        .toString());
//                        intent.putExtra("listNo",
//                                cameraItem.get(arg2).get("listNo").toString());
//                        intent.putExtra("width",
//                                cameraItem.get(arg2).get("width").toString());
//                        intent.putExtra("height",
//                                cameraItem.get(arg2).get("height").toString());
//                        intent.putExtra("adapterId",
//                                cameraItem.get(arg2).get("adapterId")
//                                        .toString());
//                        intent.putExtra("ptz", cameraItem.get(arg2).get("ptz")
//                                .toString());
//                        intent.putExtra("zoom", cameraItem.get(arg2)
//                                .get("zoom").toString());
//                        intent.putExtra("talk", cameraItem.get(arg2)
//                                .get("talk").toString());
//                        intent.putExtra("socketIp", socketIp);
//                        intent.putExtra("ifRecord", ifRecord);
//                        intent.putExtra("ifSnap", ifSnap);
//                        intent.putExtras(bundle);
//
//                        startActivity(intent);
//
                    }
                } else if (selectType.equals("rtsp")) {

//                    try {
//                        mLibVLC = Util.getLibVlcInstance();
//                    } catch (LibVlcException e) {
//                        e.printStackTrace();
//                    }
//
//                    Intent intent = new Intent(CameraList.this,
//                            VideoPlayerActivity.class);
//                    intent.putExtra("rtspUrl",
//                            cameraItem.get(arg2).get("chName").toString());
//                    intent.putExtra("width", cameraItem.get(arg2).get("width")
//                            .toString());
//                    intent.putExtra("height", cameraItem.get(arg2)
//                            .get("height").toString());
//                    intent.putExtra("devName", selectName);
//                    intent.putExtra("userId", userId);
//                    intent.putExtra("devId", cameraItem.get(arg2).get("devId")
//                            .toString());
//                    intent.putExtra("chName", "0");
//                    intent.putExtra("ip", "0");
//                    intent.putExtra("port", "0");
//                    intent.putExtra("chNo", "0");
//                    intent.putExtra("listCount", "0");
//                    intent.putExtra("listNo", "0");
//                    intent.putExtra("adapterId", "1");
//                    intent.putExtra("ptz", cameraItem.get(arg2).get("ptz")
//                            .toString());
//                    intent.putExtra("zoom", cameraItem.get(arg2).get("zoom")
//                            .toString());
//                    intent.putExtra("talk", "0");
//                    intent.putExtra("socketIp", socketIp);
//                    intent.putExtra("ifRecord", ifRecord);
//                    intent.putExtra("ifSnap", ifSnap);
//
//                    startActivity(intent);
                }
            }
        }

    }

    Runnable runnableUi = new Runnable() {
        @Override
        public void run() {
            try {
                cameraAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
//            waitClose();
        }

    };

    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        // 退出，则设置退出属性为ture
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            this.finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
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
