package com.sxit.dreamiya.adapter.kinder;

import java.util.List;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.entity.news.FinNewsList;
import com.sxit.dreamiya.entity.photo.FinPhotoList;
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
public class PhotoList_Adapter extends BaseAdapter {
	private Context context;
	private List<FinPhotoList> list;

	public PhotoList_Adapter(Context context, List<FinPhotoList> list) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_in_photo, null);
			viewHolder = new ViewHolder();
			viewHolder.list_in_photo_icon = (ImageView) convertView.findViewById(R.id.list_in_photo_icon);
			viewHolder.list_in_photo_time = (TextView) convertView.findViewById(R.id.list_in_photo_time);
			viewHolder.list_in_photo_content = (TextView) convertView.findViewById(R.id.list_in_photo_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Instance.imageLoader.displayImage(SOAP_UTILS.PIC_JOURNAL + list.get(position).getPic(), viewHolder.list_in_photo_icon, Instance.img_options);
		viewHolder.list_in_photo_time.setText(list.get(position).getCrtime());
		viewHolder.list_in_photo_content.setText(list.get(position).getTitle());
		return convertView;
		
	}

	static class ViewHolder {
		public TextView list_in_photo_time;
		public ImageView list_in_photo_icon;
		public TextView list_in_photo_content;
		
	}
}
