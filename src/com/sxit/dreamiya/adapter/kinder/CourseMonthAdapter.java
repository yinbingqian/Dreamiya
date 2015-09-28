package com.sxit.dreamiya.adapter.kinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.entity.course.FinCourseList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CourseMonthAdapter extends BaseAdapter {
	private class buttonViewHolder {
		TextView textview;
	}

	private List<FinCourseList> mAppList;
	private LayoutInflater mInflater;
	private Context mContext;
	private String[] keyString;
	private buttonViewHolder holder;

	public CourseMonthAdapter(Context c,
			List<FinCourseList> list_course, int resource,
			String[] from, int[] to) {
		mAppList = list_course;
		mContext = c;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		keyString = new String[from.length];
		System.arraycopy(from, 0, keyString, 0, from.length);
	}

	@Override
	public int getCount() {
		return mAppList.size();
	}

	@Override
	public Object getItem(int position) {
		return mAppList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void removeItem(int positon) {
		mAppList.remove(positon);
		this.notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView != null) {
			holder = (buttonViewHolder) convertView.getTag();
		} else {
			convertView = mInflater.inflate(R.layout.list_in_coursemonth, null);
			holder = new buttonViewHolder();
			holder.textview = (TextView) convertView.findViewById(R.id.txt1);
			convertView.setTag(holder);
		}
		FinCourseList appInfo = mAppList.get(position);
		if (appInfo != null) {
			String comId = appInfo.getComId();
			String className = appInfo.getClassName();
			String classId = appInfo.getClassId();
			holder.textview.setText(className);
			
//			convertView.setOnClickListener(new AdapterListener(position, year, month));
		}
		return convertView;
	}

	public void addItem(ArrayList<FinCourseList> item) {
		int count = item.size();
		for (int i = 0; i < count; i++) {
			mAppList.add(item.get(i));
		}
	}

	class AdapterListener implements OnClickListener {
		private int position;
		private String className;

		public AdapterListener(int pos,String _className) {
			// TODO Auto-generated constructor stub
			position = pos;
			className = _className;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		}
	}
}