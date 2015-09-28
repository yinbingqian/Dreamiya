package com.sxit.dreamiya.activity.kinder;

import java.util.ArrayList;
import java.util.List;

import com.easemob.chatuidemo.activity.SplashActivity;
import com.easemob.chatuidemo.adapter.MessageAdapter.ViewHolder;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.adapter.kinder.NoticeManagement_Adapter;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.entity.notice.FinNoticeManagementList;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshListView;
import com.sxit.dreamiya.webservice.SoapRes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;

public class NoticeManagementActivity extends BaseActivity
        implements OnClickListener {
    private Context context;
    private List<FinNoticeManagementList> list;
    private NoticeManagement_Adapter adapter;

    private PullToRefreshListView notice_management_list;;
    private ListView listView;
    private ImageView delete_img;
    private ImageView send_img;

    public static List<String> notice_checked_arrayStrings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinder_notice_management);

        context = this;
        viewInit();
        setListeners();
        String[] property_va = new String[] {
                SplashActivity.userinfo.getPhone() };
        soapService.getNoticeInfoForSim(property_va);

    }

    private void viewInit() {
        delete_img = (ImageView) findViewById(R.id.delete_img);
        send_img = (ImageView) findViewById(R.id.send_img);
        notice_management_list = (PullToRefreshListView) findViewById(
                R.id.notice_management_list);
        listView = notice_management_list.getRefreshableView();

    }

    private void setListeners() {
        delete_img.setClickable(true);
        send_img.setClickable(true);

        send_img.setOnClickListener(this);
        delete_img.setOnClickListener(this);

        notice_management_list
                .setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
                        CheckBox checkbox = (CheckBox) view
                                .findViewById(R.id.checkbox);
                        if (checkbox.isChecked()) {
                            notice_checked_arrayStrings.set(position - 1, "0");
                            checkbox.setChecked(false);
                            checkbox.setTag("0");
                        } else {
                            notice_checked_arrayStrings.set(position - 1,
                                    list.get(position).getId());
                            checkbox.setChecked(true);
                            checkbox.setTag("1");
                        }
                    }
                });

        notice_management_list
                .setOnRefreshListener(new OnRefreshListener<ListView>() {

                    @Override
                    public void onRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        new GetDataTask().execute();
                    }
                });

    }

    private void deleteNotices() {
        String res = "";
        for (int i = 0; i < notice_checked_arrayStrings.size(); i++) {
            if (!notice_checked_arrayStrings.get(i).equals("0")) {
                if (res.equals("")) {
                    res = notice_checked_arrayStrings.get(i);
                } else {
                    res = res + "," + notice_checked_arrayStrings.get(i);
                }

            }
        }
        String[] property_va = new String[] { res };
        soapService.deleNoticeInfoForPhone(property_va);

//        Toast.makeText(context, res, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.send_img:
            Intent intent_send = new Intent();
            intent_send.setClass(this, NoticeSendActivity.class);
            startActivity(intent_send);
            break;
        case R.id.delete_img:
            deleteNotices();
            break;
        default:
            break;
        }
    }

    /**
     * 列表刷新
     * 
     * @author why
     * 
     */
    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            String[] property_va = new String[] {
                    SplashActivity.userinfo.getPhone() };
            soapService.getNoticeInfoForSim(property_va);
            super.onPostExecute(result);
        }
    }

    /**
     * 返回
     * 
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * http回调SoapObject
     * 
     * @param obj
     */
    @Override
    public void onEvent(Object obj) {
        SoapRes res = (SoapRes) obj;
        // webservice result
        if (res.getCode().equals(SOAP_UTILS.METHOD.GETNOTICEINFOFORSIM)) {
            Parcelable listState = listView.onSaveInstanceState();

            notice_checked_arrayStrings = new ArrayList<String>();

            if (res.isPage()) {
                for (FinNoticeManagementList bean : (List<FinNoticeManagementList>) res
                        .getObj()) {
                    list.add(bean);
                    notice_checked_arrayStrings.add("0");
                }
                adapter.notifyDataSetChanged();
            } else {
                list = (List<FinNoticeManagementList>) res.getObj();
                if (list != null) {
                    if (list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            notice_checked_arrayStrings.add("0");
                        }
                        adapter = new NoticeManagement_Adapter(this, list);
                        listView.setAdapter(adapter);
                    }
                }
            }
            notice_management_list.onRefreshComplete();
            listView.onRestoreInstanceState(listState);
        } else if (res.getCode()
                .equals(SOAP_UTILS.METHOD.DELENOTICEINFOFORPHONE)) {
            if (res.getObj() != null) {
                if (res.getObj().toString().equals("true")) {
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    Refresh();
                } else {
                    Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Refresh();
    }

    private void Refresh() {

        String[] property_va = new String[] {
                SplashActivity.userinfo.getPhone() };
        soapService.getNoticeInfoForSim(property_va);
    }

    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        if (http.equals(SOAP_UTILS.METHOD.GETNOTICEINFOFORSIM)) {
            String[] property_va = new String[] {
                    SplashActivity.userinfo.getPhone() };
            soapService.getNoticeInfoForSim(property_va);
        }
    }

}
