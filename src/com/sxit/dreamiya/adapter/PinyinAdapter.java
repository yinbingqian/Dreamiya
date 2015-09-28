package com.sxit.dreamiya.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sxit.dreamiya.R;
import com.sxit.dreamiya.activity.chat.ContactActivity;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.entity.ContactInfo;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.utils.pinyin.AssortPinyinList;
import com.sxit.dreamiya.utils.pinyin.LanguageComparator_CN;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class PinyinAdapter extends BaseExpandableListAdapter {

	private List<String> strList;

	private AssortPinyinList assort = new AssortPinyinList();

	private Context context;
	ExpandableListView listview;
	private LayoutInflater inflater;

	private LanguageComparator_CN cnSort = new LanguageComparator_CN();

	public PinyinAdapter(Context context, List<String> strList, ExpandableListView _listview) {
		super();
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.strList = strList;
		this.listview = _listview;
		if (strList == null) {
			strList = new ArrayList<String>();
		}

		sort();

	}

	private void sort() {
		for (String str : strList) {
			assort.getHashList().add(str);
		}
		assort.getHashList().sortKeyComparator(cnSort);
		for (int i = 0, length = assort.getHashList().size(); i < length; i++) {
			Collections.sort((assort.getHashList().getValueListIndex(i)),
					cnSort);
		}

	}

	public Object getChild(int group, int child) {
		// TODO Auto-generated method stub
		return assort.getHashList().getValueIndex(group, child);
	}

	public long getChildId(int group, int child) {
		// TODO Auto-generated method stub
		return child;
	}

	public View getChildView(int group, int child, boolean arg2,
			View contentView, ViewGroup arg4) {
		// TODO Auto-generated method stub
		if (contentView == null) {
			contentView = inflater.inflate(R.layout.row_contact_with_checkbox, null);
		}
		CheckBox checkBox = (CheckBox) contentView.findViewById(R.id.checkbox);
		ImageView avatar = (ImageView) contentView.findViewById(R.id.avatar);
		TextView nameTextview = (TextView) contentView.findViewById(R.id.name);
		TextView typeTextview = (TextView) contentView.findViewById(R.id.type);

		String[] user_array = assort.getHashList().getValueIndex(group, child).toString().split("%");
		if (ContactActivity.contact_checked_arrayStrings.get(Integer.valueOf(user_array[1])).equals("0")) {
			checkBox.setChecked(false);
		}else{
			checkBox.setChecked(true);
		}
		nameTextview.setText(user_array[0]);
		typeTextview.setText(user_array[4].equals("1")?"家长":"老师");
        String pic = user_array[3].startsWith("any")?"":user_array[3];
		String headpic = SOAP_UTILS.PIC_FILE + pic;
		avatar.setTag(headpic);               
		Instance.imageLoader.displayImage(headpic, avatar, Instance.user_options);

		return contentView;
	}

	public int getChildrenCount(int group) {
		// TODO Auto-generated method stub
		return assort.getHashList().getValueListIndex(group).size();
	}

	public Object getGroup(int group) {
		// TODO Auto-generated method stub
		return assort.getHashList().getValueListIndex(group);
	}

	public int getGroupCount() {
		// TODO Auto-generated method stub
		return assort.getHashList().size();
	}

	public long getGroupId(int group) {
		// TODO Auto-generated method stub
		return group;
	}

	public View getGroupView(int group, boolean arg1, View contentView,
			ViewGroup arg3) {
		if (contentView == null) {
			contentView = inflater.inflate(R.layout.list_group_item, null);
			contentView.setClickable(true);
		}
		TextView textView = (TextView) contentView.findViewById(R.id.name);
		textView.setText(assort.getFirstChar(assort.getHashList()
				.getValueIndex(group, 0)));
		
		return contentView;
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	public AssortPinyinList getAssort() {
		return assort;
	}

}
