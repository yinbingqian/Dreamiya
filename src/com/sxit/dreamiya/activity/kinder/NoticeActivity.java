package com.sxit.dreamiya.activity.kinder;

import java.util.List;

import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.adapter.kinder.Notice_Adapter;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.entity.notice.FinNoticeList;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshListView;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.sxit.dreamiya.webservice.SoapRes;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;

public class NoticeActivity extends BaseActivity {
    private Context context;
    private List<FinNoticeList> list;
    private Notice_Adapter adapter;
    
    private PullToRefreshListView listView_noticelist;;
    private ListView listView;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinder_notice);

        context = this;
        viewInit();

        String[] property_va = new String[] { SplashActivity.userinfo.getComId()};
        soapService.getNoticeInfo(property_va);

    }

    private void viewInit(){
        listView_noticelist = (PullToRefreshListView) findViewById(R.id.notice_list);
        listView = listView_noticelist.getRefreshableView();
        
        listView_noticelist.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });
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
            String[] property_va = new String[] { SplashActivity.userinfo.getComId()};
        soapService.getNoticeInfo(property_va);
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
     * @param obj   
     */
    public void onEvent(Object obj) {
        SoapRes res = (SoapRes) obj;
        //webservice result
        if (res.getCode().equals(SOAP_UTILS.METHOD.GETNOTICEINFO)) {
            Parcelable listState = listView.onSaveInstanceState();
            
            if (res.isPage()) {
                for (FinNoticeList bean : (List<FinNoticeList>) res.getObj()) {
                    list.add(bean);
                }
                adapter.notifyDataSetChanged();
            } else {
                list = (List<FinNoticeList>) res.getObj();
                if (list != null) {
                    if (list.size() != 0) {
                        adapter = new Notice_Adapter(this, list);
                        listView.setAdapter(adapter);
                    }
                }
            }
            listView_noticelist.onRefreshComplete();
            listView.onRestoreInstanceState(listState);
        }
    }

    
    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        if (http.equals(SOAP_UTILS.METHOD.GETNOTICEINFO)) {
            String[] property_va = new String[] { SplashActivity.userinfo.getComId() };
            soapService.getNoticeInfo(property_va);
        }
    }
    
}
