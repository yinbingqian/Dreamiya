package com.sxit.dreamiya.activity.kinder;

import java.util.List;

import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.adapter.kinder.VideoList_Adapter;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.entity.photo.FinPhotoList;
import com.sxit.dreamiya.entity.video.FinVideoList;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase.OnLastItemVisibleListener;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshListView;
import com.sxit.dreamiya.webservice.SoapRes;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class VideoActivity extends BaseActivity {
	private Resources resources;
	private Context context;
    private int pageIndex = 1;
    private List<FinVideoList> list;
    private VideoList_Adapter adapter;
	
	private PullToRefreshListView listView_videolist;
    private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kinder_video);

		resources = this.getResources();
		context = this;

		viewInit();
		setListeners();
		String[] property_va = new String[] { "10", pageIndex + "", SplashActivity.userinfo.getComId() };
        soapService.getVideoInfo(property_va, false);
	}
	
	private void viewInit(){
	    listView_videolist = (PullToRefreshListView) findViewById(R.id.listView_videolist);
        listView = listView_videolist.getRefreshableView();
	}
	
	private void setListeners() {
	    listView_videolist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(SOAP_UTILS.VIDEO_PATH + list.get(position).getVideo());
                intent.setDataAndType(uri, "video/mp4");
                startActivity(intent);
               
            }
        });
	    listView_videolist.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });
        // end of list
	    listView_videolist.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                String[] property_va = new String[] { "10", ++pageIndex + "", SplashActivity.userinfo.getComId() };
                soapService.getVideoInfo(property_va, true);

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
            pageIndex = 1;
            String[] property_va = new String[] { "10", pageIndex + "", SplashActivity.userinfo.getComId() };
            soapService.getVideoInfo(property_va, false);
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
        if (res.getCode().equals(SOAP_UTILS.METHOD.GETVIDEOINFO)) {
            Parcelable listState = listView.onSaveInstanceState();
            if (res.isPage()) {
                for (FinVideoList bean : (List<FinVideoList>) res.getObj()) {
                    list.add(bean);
                }
                adapter.notifyDataSetChanged();
            } else {
                list = (List<FinVideoList>) res.getObj();
                if (list != null) {
                    if (list.size() != 0) {
                        adapter = new VideoList_Adapter(this, list);
                        listView.setAdapter(adapter);
                    }
                }
            }
            listView_videolist.onRefreshComplete();
            listView.onRestoreInstanceState(listState);
        }
    }

    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        if (http.equals(SOAP_UTILS.METHOD.GETVIDEOINFO)) {
            String[] property_va = new String[] { "10", pageIndex + "", SplashActivity.userinfo.getComId() };
            soapService.getVideoInfo(property_va, false);
        }
    }

}
