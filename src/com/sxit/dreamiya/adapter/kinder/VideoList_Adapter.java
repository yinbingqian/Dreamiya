package com.sxit.dreamiya.adapter.kinder;

import java.util.List;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.entity.photo.FinPhotoList;
import com.sxit.dreamiya.entity.video.FinVideoList;
import com.sxit.dreamiya.utils.SOAP_UTILS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * info
 * 
 * @author huanyu 类名称：Information_Adapter 创建时间:2014-10-27 上午12:00:38
 */
public class VideoList_Adapter extends BaseAdapter {
	private Context context;
	private List<FinVideoList> list;

	public VideoList_Adapter(Context context, List<FinVideoList> list) {
		this.context = context;
		this.list = list;
	}
	

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.list_in_video, null);
			viewHolder = new ViewHolder();
			viewHolder.list_in_video_icon = (ImageView) convertView.findViewById(R.id.list_in_video_icon);
			viewHolder.list_in_video_time = (TextView) convertView.findViewById(R.id.list_in_video_time);
			viewHolder.list_in_video_content = (TextView) convertView.findViewById(R.id.list_in_video_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Instance.imageLoader.displayImage(SOAP_UTILS.VIDEO_PATH + list.get(position).getPic(), viewHolder.list_in_video_icon, Instance.img_options);
		viewHolder.list_in_video_time.setText(list.get(position).getCrtime());
		viewHolder.list_in_video_content.setText(list.get(position).getTitle());
		return convertView;
		
	}

	static class ViewHolder {
		public TextView list_in_video_time;
		public ImageView list_in_video_icon;
		public TextView list_in_video_content;
		
	}
}
