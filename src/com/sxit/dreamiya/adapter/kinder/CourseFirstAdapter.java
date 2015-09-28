package com.sxit.dreamiya.adapter.kinder;

import java.util.HashMap;
import java.util.List;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.entity.course.FinCourseInfoList;
import com.sxit.dreamiya.entity.news.FinNewsList;
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
public class CourseFirstAdapter extends BaseAdapter {
	private Context context;
	private List<FinCourseInfoList> list;

	public CourseFirstAdapter(Context context, List<FinCourseInfoList> list) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_in_course, null);
			viewHolder = new ViewHolder();
			viewHolder.week_tv = (TextView) convertView.findViewById(R.id.week_tv);
			viewHolder.course_tv = (TextView) convertView.findViewById(R.id.course_tv);
			viewHolder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

//		viewHolder.week_tv.setText(list.get(position).getWeek());
//		viewHolder.course_tv.setText(list.get(position).getCourseName());
//		viewHolder.time_tv.setText(list.get(position).getTmStart() + "-" + list.get(position).getTmEnd());
		
		FinCourseInfoList appInfo = list.get(position);
        if (appInfo != null) {
            String week_str = appInfo.getWeek();
            String course_str = appInfo.getCourseName();
            String time_str =  appInfo.getComId();
            // holder.textview.setText(year + "年" + month + "月");
            try{                
                String[] course_array = course_str.split("%");
                String[] time_array = time_str.split("%");
                
                String course = "";
                String time = "";
                
                for(int i=0; i<course_array.length;i++){
                    course = course + course_array[i] + "\n";
                }
                for(int i=0; i<time_array.length;i++){
                    time = time + time_array[i] + "\n";
                }
                
                viewHolder.week_tv.setText(week_str);
                viewHolder.course_tv.setText(course);
                viewHolder.time_tv.setText(time);
//                viewHolder.week_tv.setText(list.get(position).getWeek());
//                viewHolder.course_tv.setText(list.get(position).getCourseName());
//                viewHolder.time_tv.setText(list.get(position).getTmStart() + "-" + list.get(position).getTmEnd());
            }catch(Exception e){
                
            }
        }
        
		return convertView;
		
	}

	static class ViewHolder {
		public TextView week_tv;
		public TextView course_tv;
		public TextView time_tv;
	}
}
