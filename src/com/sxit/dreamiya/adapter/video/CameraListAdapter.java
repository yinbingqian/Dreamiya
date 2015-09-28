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
package com.sxit.dreamiya.adapter.video;

import java.util.List;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.entity.user.UserInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CameraListAdapter extends ArrayAdapter<UserInfo> {

	private LayoutInflater inflater;
	private String str;
	List<UserInfo> userinfo;

	public CameraListAdapter(Context context, int res, List<UserInfo> _userinfo) {
		super(context, res, _userinfo);
		this.inflater = LayoutInflater.from(context);
		this.userinfo = _userinfo;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    
	    if (convertView == null) {
	        convertView = inflater.inflate(R.layout.camera_row, null);
	    }
	    final TextView ItemTitle = (TextView) convertView.findViewById(R.id.ItemTitle);
	    final ImageView ItemImage = (ImageView) convertView.findViewById(R.id.ItemImage);
	    
	    if(userinfo.get(position).getStayline().equals("1")){
            ItemTitle.setText(userinfo.get(position).getChName());
            ItemImage.setBackgroundResource(R.drawable.deviceicon);
	    }else{
	        ItemTitle.setText(userinfo.get(position).getChName()  + "(设备离线)");
	        ItemImage.setBackgroundResource(R.drawable.deviceicon_grey);
	    }
	    
		return convertView;
	}

}