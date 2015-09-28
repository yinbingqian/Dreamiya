package com.sxit.dreamiya.adapter.kinder;

import java.util.List;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.entity.notice.FinNoticeList;
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
public class Notice_Adapter extends BaseAdapter {
	private Context context;
	private List<FinNoticeList> list;

	public Notice_Adapter(Context context, List<FinNoticeList> list) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_in_notice, null);
			viewHolder = new ViewHolder();
			viewHolder.noticecontent_tv = (TextView) convertView.findViewById(R.id.noticecontent_tv);
			viewHolder.noticetime_tv = (TextView) convertView.findViewById(R.id.noticetime_tv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.noticecontent_tv.setText(list.get(position).getNoticecontent());
		viewHolder.noticetime_tv.setText(list.get(position).getTrTime());
		return convertView;
		
	}

	static class ViewHolder {
		public TextView noticecontent_tv;
		public TextView noticetime_tv;
		
	}
}
