package com.sxit.dreamiya.adapter.kinder;

import java.util.List;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.activity.kinder.NoticeManagementActivity;
import com.sxit.dreamiya.entity.notice.FinNoticeManagementList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * info
 * 
 * @author huanyu 类名称：Information_Adapter 创建时间:2014-10-27 上午12:00:38
 */
public class NoticeManagement_Adapter extends BaseAdapter {
	private Context context;
	private List<FinNoticeManagementList> list;

	public NoticeManagement_Adapter(Context context, List<FinNoticeManagementList> list) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_in_notice_management, null);
			viewHolder = new ViewHolder();
			viewHolder.noticecontent_tv = (TextView) convertView.findViewById(R.id.noticecontent_tv);
			viewHolder.noticetime_tv = (TextView) convertView.findViewById(R.id.noticetime_tv);
			viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if(NoticeManagementActivity.notice_checked_arrayStrings.get(position).equals("0")){
		    viewHolder.checkbox.setChecked(false);
		}else{
		    viewHolder.checkbox.setChecked(true);
		}
//		if(viewHolder.checkbox.getTag().equals("1")){
//		    viewHolder.checkbox.setChecked(true);
//		}else{
//		    viewHolder.checkbox.setChecked(false);
//		}
		viewHolder.noticecontent_tv.setText(list.get(position).getNoticecontent());
		viewHolder.noticetime_tv.setText(list.get(position).getTrTime());
		return convertView;
		
	}

	static class ViewHolder {
		public TextView noticecontent_tv;
		public TextView noticetime_tv;
		public CheckBox checkbox;
		
	}
}
