package com.sxit.dreamiya.fragment;

import org.ksoap2.serialization.SoapObject;

import com.easemob.chatuidemo.Constant;
import com.easemob.chatuidemo.activity.MainActivity;
import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.activity.kinder.CourseActivity;
import com.sxit.dreamiya.activity.kinder.NewsActivity;
import com.sxit.dreamiya.activity.kinder.NoticeActivity;
import com.sxit.dreamiya.activity.kinder.NoticeManagementActivity;
import com.sxit.dreamiya.activity.kinder.PhotoActivity;
import com.sxit.dreamiya.activity.kinder.PhotoManagementActivity;
import com.sxit.dreamiya.activity.kinder.VideoActivity;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.webservice.SoapWebService;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 显示所有会话记录，比较简单的实现，更好的可能是把陌生人存入本地，这样取到的聊天记录是可控的
 * 
 */
public class KinderFragment extends Fragment implements View.OnClickListener {
         TextView notive_tv;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    if(SplashActivity.userinfo.getKinderType().equals("教师")){	        
	        return inflater.inflate(R.layout.fragment_kinder_teacher, container, false);
	    }else{
	        return inflater.inflate(R.layout.fragment_kinder, container, false);
	    }
	}
	
	private void viewInit(){
	    if(SplashActivity.userinfo.getKinderType().equals("教师")){          
            ImageView photo_manage_img = (ImageView) getView().findViewById(R.id.front_img_06);
            ImageView notice_manage_img = (ImageView) getView().findViewById(R.id.front_img_09);
            photo_manage_img.setOnClickListener(this);
            notice_manage_img.setOnClickListener(this);
        }

        notive_tv = (TextView)getView().findViewById(R.id.notice_tv);
        View layout_notice = getView().findViewById(R.id.garden_layout_notice);
	    ImageView photo_img = (ImageView) getView().findViewById(R.id.front_img_02);
	    ImageView food_img = (ImageView) getView().findViewById(R.id.front_img_04);
	    ImageView course_img = (ImageView) getView().findViewById(R.id.front_img_08);
	    ImageView attend_img = (ImageView) getView().findViewById(R.id.front_img_05);
	    ImageView news_img = (ImageView) getView().findViewById(R.id.front_img_01);
	    ImageView video_img = (ImageView) getView().findViewById(R.id.front_img_03);
	    layout_notice.setClickable(true);
	    layout_notice.setOnClickListener(this);
	    photo_img.setOnClickListener(this);
	    food_img.setOnClickListener(this);
	    course_img.setOnClickListener(this);
	    attend_img.setOnClickListener(this);
	    news_img.setOnClickListener(this);
	    video_img.setOnClickListener(this);
	    
	}
	
	

	@Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        String[] property_nm = { "comId" };
        Object[] property_va = { SplashActivity.userinfo.getComId() };
        new GetNotice().execute(property_nm, property_va);
    }

    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

        viewInit();
		if(savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
		
	}

	@Override
    public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
        if(((MainActivity)getActivity()).isConflict){
        	outState.putBoolean("isConflict", true);
        }else if(((MainActivity)getActivity()).getCurrentAccountRemoved()){
        	outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.front_img_06://相册管理
            Intent intent_photomanagement = new Intent();
            intent_photomanagement.setClass(getActivity(), PhotoManagementActivity.class);
            getActivity().startActivity(intent_photomanagement);
            break;
        case R.id.garden_layout_notice://通知
            Intent intent_notice = new Intent();
            intent_notice.setClass(getActivity(), NoticeActivity.class);
            getActivity().startActivity(intent_notice);
            break;
        case R.id.front_img_02://相册
            Intent intent_photo = new Intent();
            intent_photo.setClass(getActivity(), PhotoActivity.class);
            getActivity().startActivity(intent_photo);
            break;
        case R.id.front_img_04://食谱
            Toast.makeText(getActivity(), "食谱", Toast.LENGTH_SHORT).show();
            break;
        case R.id.front_img_08://课程
            Intent intent_course = new Intent();
            intent_course.setClass(getActivity(), CourseActivity.class);
            getActivity().startActivity(intent_course);
            break;
        case R.id.front_img_05://出勤
            Toast.makeText(getActivity(), "出勤", Toast.LENGTH_SHORT).show();
            break;
        case R.id.front_img_01://资讯
            Intent intent_news = new Intent();
            intent_news.setClass(getActivity(), NewsActivity.class);
            getActivity().startActivity(intent_news);
            break;
        case R.id.front_img_03://视频
            Intent intent_video = new Intent();
            intent_video.setClass(getActivity(), VideoActivity.class);
            getActivity().startActivity(intent_video);
            break;
        case R.id.front_img_09://通知发布
            Intent intent_noticemanagement = new Intent();
            intent_noticemanagement.setClass(getActivity(), NoticeManagementActivity.class);
            getActivity().startActivity(intent_noticemanagement);
            break;
        default:
            break;
        }
    }
    
    class GetNotice extends AsyncTask<Object, Object, Object> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object... params) {
            System.out.println(">>>>>");
            SoapObject res_obj = (SoapObject) SoapWebService.data(SOAP_UTILS.METHOD.GETNOTICEINFO, (String[]) params[0],(Object[]) params[1]);
            
            String notice_show_str = "";
            try {                
                SoapObject soapchild = (SoapObject) res_obj.getProperty(0);
                SoapObject soapchildres = (SoapObject) soapchild.getProperty(1);
                SoapObject soapchildress = (SoapObject) soapchildres.getProperty(0);
                
                if(soapchildress.getPropertyCount()>0){                
                    SoapObject soapchilds = (SoapObject) soapchildress.getProperty(0);
                    notice_show_str = soapchilds.getProperty("Noticecontent").toString();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
                
            return notice_show_str;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            notive_tv.setText("通知：" + result.toString());
        }

    }
}
