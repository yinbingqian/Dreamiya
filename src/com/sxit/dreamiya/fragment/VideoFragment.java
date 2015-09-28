package com.sxit.dreamiya.fragment;

import com.easemob.chatuidemo.Constant;
import com.easemob.chatuidemo.activity.MainActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.activity.video.CameraList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 显示所有会话记录，比较简单的实现，更好的可能是把陌生人存入本地，这样取到的聊天记录是可控的
 * 
 */
public class VideoFragment extends Fragment implements View.OnClickListener {
		
    View front_layout_camera;
    View front_layout_record;
    View front_layout_snap;
    View front_layout_favor;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_video, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false)){
		    return;		    
		}
		
		viewInit();
		
	}
	
	private void viewInit(){
	    front_layout_camera = (View)getActivity().findViewById(R.id.front_layout_camera);
        front_layout_record = (View)getActivity().findViewById(R.id.front_layout_record);
        front_layout_snap = (View)getActivity().findViewById(R.id.front_layout_snap);
        front_layout_favor = (View)getActivity().findViewById(R.id.front_layout_favor);
        front_layout_camera.setOnClickListener(this);
        front_layout_record.setOnClickListener(this);
        front_layout_snap.setOnClickListener(this);
        front_layout_favor.setOnClickListener(this);
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
        case R.id.front_layout_camera://实时视频
            Intent intent_camera = new Intent();
            intent_camera.setClass(getActivity(), CameraList.class);
            getActivity().startActivity(intent_camera);
            break;
        case R.id.front_layout_record://录像回放
            
            break;
        case R.id.front_layout_snap://抓拍浏览
            
            break;
        case R.id.front_layout_favor://收藏夹
            
            break;

        default:
            break;
        }
    }

}
