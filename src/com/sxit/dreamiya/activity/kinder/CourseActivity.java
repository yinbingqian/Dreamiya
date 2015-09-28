package com.sxit.dreamiya.activity.kinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.adapter.kinder.CourseAdapter;
import com.sxit.dreamiya.adapter.kinder.CourseFirstAdapter;
import com.sxit.dreamiya.adapter.kinder.CourseMonthAdapter;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.entity.course.FinCourseInfoList;
import com.sxit.dreamiya.entity.course.FinCourseList;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.RightMenuLinearLayout;
import com.sxit.dreamiya.utils.RightMenuLinearLayout.OnScrollListener;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshListView;
import com.sxit.dreamiya.webservice.SoapRes;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class CourseActivity extends BaseActivity implements OnTouchListener,
        GestureDetector.OnGestureListener, OnItemClickListener, OnClickListener {

    // private WebView web_course;
    Context context;
    private boolean hasMeasured = false;// �Ƿ�Measured.
    private LinearLayout layout_left;// ��߲���
    private LinearLayout layout_right;// �ұ߲���
    private ImageView iv_set;// ͼƬ
    private ListView lv_set;// ���ò˵�
    private ListView listView;
    private TextView dn_text;
    
    private List<FinCourseList> courselist;
    private List<FinCourseInfoList> list;

//    private ProgressDialog dialog;
//    public static final String BBGJ_USERINFO = "BBGJ_UserInfo";
    public static final String NAME = "name";

    /** 每次自动展开/收缩的范围 */
    private int MAX_WIDTH = 0;
    /** 每次自动展开/收缩的速度 */
    private final static int SPEED = 30;

    private final static int sleep_time = 5;

    private GestureDetector mGestureDetector;// ����
    private boolean isScrolling = false;
    private float mScrollX; // ���黬������
    private int window_width;// ��Ļ�Ŀ��

    private String TAG = "jj";

    private View view = null;// ����view
    String comId = "";

    private RightMenuLinearLayout mylaout;
    private RightMenuLinearLayout mywebviewlayout;
    ArrayList<String> data_array = new ArrayList<String>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kinder_course);
        context = this;

        InitView();
        getName();
//        setListeners();
        String[] property_va = new String[] { SplashActivity.userinfo.getComId() };
        soapService.getSourseForClassAll(property_va);
        
//        String[] property_gsf= new String[] {SplashActivity.userinfo.getPhone(), SplashActivity.userinfo.getComId()};
//        soapService.getSoursefirstshow(property_gsf);
    }

    void InitView() {
        layout_left = (LinearLayout) findViewById(R.id.layout_left);
        layout_right = (LinearLayout) findViewById(R.id.layout_right);
        lv_set = (ListView) findViewById(R.id.lv_set2);
        dn_text = (TextView) findViewById(R.id.dn_text);
        iv_set = (ImageView) findViewById(R.id.iv2_set);
        listView = (ListView) findViewById(R.id.course_list);
        mylaout = (RightMenuLinearLayout) findViewById(R.id.mylaout);
        mywebviewlayout = (RightMenuLinearLayout) findViewById(R.id.mywebviewlaout);

        mylaout.setOnScrollListener(new OnScrollListener() {
            @Override
            public void doScroll(float distanceX) {
                doScrolling(distanceX);
            }

            @Override
            public void doLoosen() {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                        .getLayoutParams();
                Log.e("jj", "layoutParams.leftMargin="
                        + layoutParams.leftMargin);
                // ���ȥ
                if (layoutParams.leftMargin < -window_width / 3) {
                    new AsynMove().execute(-SPEED);
                } else {
                    new AsynMove().execute(SPEED);
                }
            }
        });

        mywebviewlayout.setOnScrollListener(new OnScrollListener() {
            @Override
            public void doScroll(float distanceX) {
                doScrolling(distanceX);
            }

            @Override
            public void doLoosen() {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                        .getLayoutParams();
                Log.e("jj", "layoutParams.leftMargin="
                        + layoutParams.leftMargin);
                // ���ȥ
                if (layoutParams.leftMargin < -window_width / 3) {
                    new AsynMove().execute(-SPEED);
                } else {
                    new AsynMove().execute(SPEED);
                }
            }
        });

        // ������
        lv_set.setOnItemClickListener(this);
        iv_set.setOnTouchListener(this);
        layout_right.setOnTouchListener(this);
        layout_left.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this);
        // ���ó�������
        mGestureDetector.setIsLongpressEnabled(false);
        getMAX_WIDTH();
    }
    
    /**
     * 返回
     * 
     * @param view
     */
    public void back(View view) {
        finish();
    }
    
    void getName() {
        comId = SplashActivity.userinfo.getComId();
    }

    /***
     * listview ���ڻ���ʱִ��.
     */
    void doScrolling(float distanceX) {
        isScrolling = true;
        mScrollX += distanceX;// distanceX:����Ϊ����Ϊ��

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                .getLayoutParams();
        RelativeLayout.LayoutParams layoutParams_1 = (RelativeLayout.LayoutParams) layout_right
                .getLayoutParams();
        layoutParams.leftMargin -= mScrollX;
        layoutParams_1.leftMargin = window_width + layoutParams.leftMargin;
        if (layoutParams.leftMargin >= 0) {
            isScrolling = false;// �Ϲ�ͷ�˲���Ҫ��ִ��AsynMove��
            layoutParams.leftMargin = 0;
            layoutParams_1.leftMargin = window_width;

        } else if (layoutParams.leftMargin <= -MAX_WIDTH) {
            // �Ϲ�ͷ�˲���Ҫ��ִ��AsynMove��
            isScrolling = false;
            layoutParams.leftMargin = -MAX_WIDTH;
            layoutParams_1.leftMargin = window_width - MAX_WIDTH;
        }
        Log.v(TAG, "layoutParams.leftMargin=" + layoutParams.leftMargin
                + ",layoutParams_1.leftMargin =" + layoutParams_1.leftMargin);

        layout_left.setLayoutParams(layoutParams);
        layout_right.setLayoutParams(layoutParams_1);
    }

    void getMAX_WIDTH() {
        ViewTreeObserver viewTreeObserver = layout_left.getViewTreeObserver();
        // 获取控件宽度
        viewTreeObserver.addOnPreDrawListener(new OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (!hasMeasured) {
                    window_width = getWindowManager().getDefaultDisplay()
                            .getWidth();
                    MAX_WIDTH = layout_right.getWidth();
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                            .getLayoutParams();
                    RelativeLayout.LayoutParams layoutParams_1 = (RelativeLayout.LayoutParams) layout_right
                            .getLayoutParams();
                    ViewGroup.LayoutParams layoutParams_2 = mylaout
                            .getLayoutParams();
                    // 注意： 设置layout_left的宽度。防止被在移动的时候控件被挤压
                    layoutParams.width = window_width;
                    layout_left.setLayoutParams(layoutParams);

                    // 设置layout_right的初始位置.
                    layoutParams_1.leftMargin = window_width;
                    layout_right.setLayoutParams(layoutParams_1);
                    // 注意：设置lv_set的宽度防止被在移动的时候控件被挤压
                    layoutParams_2.width = MAX_WIDTH;
                    mylaout.setLayoutParams(layoutParams_2);

                    Log.v(TAG, "MAX_WIDTH=" + MAX_WIDTH + "width="
                            + window_width);
                    hasMeasured = true;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                    .getLayoutParams();
            if (layoutParams.leftMargin < 0) {
                new AsynMove().execute(SPEED);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        view = v;// 记录点击的控件

        // 松开的时候要判断，如果不到半屏幕位子则缩回去，
        if (MotionEvent.ACTION_UP == event.getAction() && isScrolling == true) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                    .getLayoutParams();
            // 缩回去
            if (layoutParams.leftMargin < -window_width / 3) {
                new AsynMove().execute(-SPEED);
            } else {
                new AsynMove().execute(SPEED);
            }
        }

        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {

        int position = lv_set.pointToPosition((int) e.getX(), (int) e.getY());
        if (position != ListView.INVALID_POSITION) {
            View child = lv_set.getChildAt(position
                    - lv_set.getFirstVisiblePosition());
            if (child != null)
                child.setPressed(true);
        }

        mScrollX = 0;
        isScrolling = false;
        // 将之改为true，才会传递给onSingleTapUp,不然事件不会向下传递.
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    /***
     * ����ɿ�ִ��
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // 点击的不是layout_left
        if (view != null && view == iv_set) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                    .getLayoutParams();
            // 左移动
            if (layoutParams.leftMargin >= 0) {
                new AsynMove().execute(-SPEED);
                lv_set.setSelection(0);// 设置为首位.
            } else {
                // 右移动
                new AsynMove().execute(SPEED);
            }
        } else if (view != null && view == layout_left) {
            RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) layout_left
                    .getLayoutParams();
            if (layoutParams.leftMargin < 0) {
                // 说明layout_left处于移动最左端状态，这个时候如果点击layout_left应该直接所以原有状态.(更人性化)
                // 右移动
                new AsynMove().execute(SPEED);
            }
        }

        return true;
    }

    /***
     * �������� ����һ����ƶ�������һ���. distanceX=�����x-ǰ���x��������0��˵��������ǰ�����ұ߼����һ���
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        // ִ�л���.
        doScrolling(distanceX);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
        return false;
    }

    class AsynMove extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            int times = 0;
            if (MAX_WIDTH % Math.abs(params[0]) == 0)// ���
                times = MAX_WIDTH / Math.abs(params[0]);
            else
                times = MAX_WIDTH / Math.abs(params[0]) + 1;// ������

            for (int i = 0; i < times; i++) {
                publishProgress(params[0]);
                try {
                    Thread.sleep(sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        /**
         * update UI
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                    .getLayoutParams();
            RelativeLayout.LayoutParams layoutParams_1 = (RelativeLayout.LayoutParams) layout_right
                    .getLayoutParams();
            // ���ƶ�
            if (values[0] > 0) {
                layoutParams.leftMargin = Math.min(layoutParams.leftMargin
                        + values[0], 0);
                layoutParams_1.leftMargin = Math.min(layoutParams_1.leftMargin
                        + values[0], window_width);
                Log.v(TAG, "layout_left��" + layoutParams.leftMargin
                        + ",layout_right��" + layoutParams_1.leftMargin);
            } else {
                // ���ƶ�
                layoutParams.leftMargin = Math.max(layoutParams.leftMargin
                        + values[0], -MAX_WIDTH);
                layoutParams_1.leftMargin = Math.max(layoutParams_1.leftMargin
                        + values[0], window_width - MAX_WIDTH);
                Log.v(TAG, "layout_left��" + layoutParams.leftMargin
                        + ",layout_right��" + layoutParams_1.leftMargin);
            }
            layout_right.setLayoutParams(layoutParams_1);
            layout_left.setLayoutParams(layoutParams);

        }

    }

    private void setListeners() {
        lv_set.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, "弹出web页", Toast.LENGTH_SHORT).show();
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                        .getLayoutParams();
                dn_text.setText(courselist.get(position).getClassName());
                String classId = courselist.get(position).getClassId();

                String[] property_va = new String[] { SplashActivity.userinfo.getComId(),classId };
                soapService.getSourseForClassSingle(property_va);

                if (layoutParams.leftMargin < 0) {
                    new AsynMove().execute(SPEED);
                }
               
            }
        });
//        lv_set.setOnRefreshListener(new OnRefreshListener<ListView>() {
//
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                new GetDataTask().execute();
//            }
//        });
//        // end of list
//        lv_set.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
//
//            @Override
//            public void onLastItemVisible() {
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
//            .getLayoutParams();
//                dn_text.setText(remoteWindowItem.get(position).get("className").toString());
//                String classId = remoteWindowItem.get(position).get("classId").toString();
//                String[] property_va = new String[] { SplashActivity.userinfo.getComId(),classId };
//                soapService.getSourseForClassSingle(property_va);
//
//            }
//        });
    }
        
    
    /**
     * 列表刷新
     * 
     * @author why
     * 
     */
    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            String[] property_va = new String[] { SplashActivity.userinfo.getComId()};
            soapService.getSourseForClassAll(property_va);
            super.onPostExecute(result);
        }
    }
    
    private void showCourseList(){
        List<FinCourseInfoList> list_res = new ArrayList<FinCourseInfoList>();
        String week_str = "";
        String course_str = "";
        String time_str = "";
        for (int i = 0; i < list.size(); i++) {
            String comId = list.get(i).getComId();
            String classId = list.get(i).getClassId();
            String className = list.get(i).getClassName();
            String tmStart = list.get(i).getTmStart();
            String tmEnd = list.get(i).getTmEnd();
            String week = list.get(i).getWeek();
            String lesson = list.get(i).getLesson();
            String courseName = list.get(i).getCourseName();
            
            if(!week.equals(week_str)){
                FinCourseInfoList course_info = new FinCourseInfoList();
                course_info.setWeek(week_str);
                course_info.setCourseName(course_str);
                course_info.setComId(time_str);
                if(!week_str.equals("")){                   
                    list_res.add(course_info);
                }
                
                week_str = week;
                course_str = lesson + ":" + courseName + "%";
                time_str = tmStart + "-" + tmEnd + "%";
                
            }else{
                course_str = course_str + lesson + ":" + courseName + "%";
                time_str = time_str + tmStart + "-" + tmEnd + "%";
            }
        }
        
        FinCourseInfoList course_res = new FinCourseInfoList();
        course_res.setWeek(week_str);
        course_res.setCourseName(course_str);
        course_res.setComId(time_str);
        
        if(!week_str.equals("")){                           
            list_res.add(course_res);
        }
        
        CourseFirstAdapter displayAdapter = new CourseFirstAdapter(context, list_res);
        listView.setAdapter(displayAdapter);
    }
    
    
    /**
     * http回调SoapObject
     * @param obj   
     */
    public void onEvent(Object obj) {
        SoapRes res = (SoapRes) obj;
//        webservice result
        if (res.getCode().equals(SOAP_UTILS.METHOD.GETSOURSEFORCLASSALL)) {
            Parcelable listState = lv_set.onSaveInstanceState();
            courselist = (List<FinCourseList>) res.getObj();
            if(courselist!=null){
                CourseMonthAdapter topicAdapter = new CourseMonthAdapter(
                        context, courselist,
                        R.layout.list_in_coursemonth, new String[] {
                                "comId", "className", "classId"}, new int[] { R.id.textview,
                                R.id.textview,
                                R.id.textview });
                lv_set.setAdapter(topicAdapter);
            }
            lv_set.onRestoreInstanceState(listState);
            setListeners();
            
            String[] property_gsf= new String[] {SplashActivity.userinfo.getPhone(), SplashActivity.userinfo.getComId()};
            soapService.getSoursefirstshow(property_gsf);
        }else if(res.getCode().equals(SOAP_UTILS.METHOD.GETSOURSEFIRSTSHOW)){
            list = (List<FinCourseInfoList>)res.getObj();

            if(list.size() > 0){                
                dn_text.setText(list.get(0).getClassName());
            }else{
                Toast.makeText(context, "无数据!", Toast.LENGTH_SHORT).show();
            }
            showCourseList();
            
        }else if(res.getCode().equals(SOAP_UTILS.METHOD.GETSOURSEFORCLASSSINGLE)){
            list = (List<FinCourseInfoList>)res.getObj();
            if(list.size() > 0){                
            }else{
                Toast.makeText(context, "无数据!", Toast.LENGTH_SHORT).show();
            }
            showCourseList();
        }
    }

    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        if (http.equals(SOAP_UTILS.METHOD.GETSOURSEFORCLASSALL)) {
//            String[] property_va = new String[] { SplashActivity.userinfo.getComId() };
//            soapService.getSourseForClassAll(property_va);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        // TODO Auto-generated method stub
        
    }

}
