package com.sxit.dreamiya.adapter.kinder;

import java.util.List;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.common.Instance;
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
public class NewsList_Adapter extends BaseAdapter {
	private Context context;
	private List<FinNewsList> list;

	public NewsList_Adapter(Context context, List<FinNewsList> list) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_in_news, null);
			viewHolder = new ViewHolder();
			viewHolder.list_in_news_icon = (ImageView) convertView.findViewById(R.id.list_in_news_icon);
			viewHolder.list_in_news_time = (TextView) convertView.findViewById(R.id.list_in_news_time);
			viewHolder.replay_count_tv = (TextView) convertView.findViewById(R.id.replay_count_tv);
			viewHolder.list_in_news_content = (TextView) convertView.findViewById(R.id.list_in_news_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Instance.imageLoader.displayImage(SOAP_UTILS.PIC_FILE + list.get(position).getPicture(), viewHolder.list_in_news_icon, Instance.img_options);
		viewHolder.list_in_news_time.setText(list.get(position).getCrtime());
		viewHolder.list_in_news_content.setText(list.get(position).getTitle());
		viewHolder.replay_count_tv.setText(list.get(position).getCommentCount());
		return convertView;
		
	}

	static class ViewHolder {
		public TextView list_in_news_time;
		public TextView replay_count_tv;
		public ImageView list_in_news_icon;
		public TextView list_in_news_content;
		
	}
}
