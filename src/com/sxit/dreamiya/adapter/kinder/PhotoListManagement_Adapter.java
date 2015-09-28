package com.sxit.dreamiya.adapter.kinder;

import java.util.List;

import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.entity.photo.FinPhotoList;
import com.sxit.dreamiya.entity.photo.FinPhotoManagementList;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.webservice.ISoapService;
import com.sxit.dreamiya.webservice.SoapService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * info
 * 
 * @author huanyu 类名称：Information_Adapter 创建时间:2014-10-27 上午12:00:38
 */
public class PhotoListManagement_Adapter extends BaseAdapter implements OnClickListener {
	private Context context;
	private List<FinPhotoManagementList> list;

    /** soapService **/
    public ISoapService soapService = new SoapService();
    
	public PhotoListManagement_Adapter(Context context, List<FinPhotoManagementList> list) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_in_photo_management, null);
			viewHolder = new ViewHolder();
			viewHolder.list_in_photo_icon = (ImageView) convertView.findViewById(R.id.list_in_photo_icon);
			viewHolder.list_in_photo_time = (TextView) convertView.findViewById(R.id.list_in_photo_time);
			viewHolder.list_in_photo_title = (TextView) convertView.findViewById(R.id.list_in_photo_title);
			viewHolder.delete_btn = (Button) convertView.findViewById(R.id.delete_btn);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Instance.imageLoader.displayImage(SOAP_UTILS.PIC_JOURNAL + list.get(position).getPic(), viewHolder.list_in_photo_icon, Instance.img_options);
		viewHolder.list_in_photo_time.setText(list.get(position).getCrtime());
		viewHolder.list_in_photo_title.setText(list.get(position).getTitle());
		
		viewHolder.list_in_photo_icon.setTag(position);
		viewHolder.list_in_photo_icon.setOnClickListener(this);
		viewHolder.delete_btn.setTag(position + "");
		viewHolder.delete_btn.setOnClickListener(this);
		return convertView;
		
	}

	static class ViewHolder {
		public TextView list_in_photo_time;
		public ImageView list_in_photo_icon;
		public TextView list_in_photo_title;
		public Button delete_btn;
		
	}

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.delete_btn:
            int position = Integer.valueOf(v.getTag().toString());
//            Toast.makeText(context, list.get(position).getId(), Toast.LENGTH_SHORT).show();
            String[] property_va = new String[] { list.get(position).getId()};
            soapService.deleInfoForPhone(property_va);
            break;
        case R.id.list_in_photo_icon:
            Toast.makeText(context, "图片", Toast.LENGTH_SHORT).show();
            break;

        default:
            break;
        }
    }
}
