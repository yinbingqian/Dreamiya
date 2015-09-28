package com.sxit.dreamiya.activity.kinder;

import java.util.List;

import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.adapter.kinder.NewsList_Adapter;
import com.sxit.dreamiya.adapter.kinder.PhotoList_Adapter;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.entity.photo.FinPhotoList;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase.OnLastItemVisibleListener;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.sxit.dreamiya.utils.pulltorefresh.PullToRefreshListView;
import com.sxit.dreamiya.webservice.SoapRes;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PhotoActivity extends BaseActivity {
	private Resources resources;
	private Context context;
    private int pageIndex = 1;
    private List<FinPhotoList> list;
    private PhotoList_Adapter adapter;
	
	private PullToRefreshListView listView_photolist;
    private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kinder_photo);

		resources = this.getResources();
		context = this;

		viewInit();
		setListeners();
        String[] property_va = new String[] { "10", pageIndex + "", SplashActivity.userinfo.getComId() };
        soapService.getMagazineInfo(property_va, false);
	}
	
	private void viewInit(){
	    listView_photolist = (PullToRefreshListView) findViewById(R.id.listView_photolist);
        listView = listView_photolist.getRefreshableView();
	}
	
	private void setListeners() {
	    listView_photolist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                Bundle bundle = new Bundle();
                bundle.putSerializable("finphotolist", list.get(position - 1));
                intent.putExtras(bundle);
                intent.setClass(PhotoActivity.this, PhotoInfo_Activity.class);
                startActivity(intent);
               
            }
        });
	    listView_photolist.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });
        // end of list
	    listView_photolist.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                String[] property_va = new String[] { "10", ++pageIndex + "", SplashActivity.userinfo.getComId() };
                soapService.getMagazineInfo(property_va, true);

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
            soapService.getMagazineInfo(property_va, false);
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
        if (res.getCode().equals(SOAP_UTILS.METHOD.GETMAGAZINEINFO)) {
            Parcelable listState = listView.onSaveInstanceState();
            if (res.isPage()) {
                for (FinPhotoList bean : (List<FinPhotoList>) res.getObj()) {
                    list.add(bean);
                }
                adapter.notifyDataSetChanged();
            } else {
                list = (List<FinPhotoList>) res.getObj();
                if (list != null) {
                    if (list.size() != 0) {
                        adapter = new PhotoList_Adapter(this, list);
                        listView.setAdapter(adapter);
                    }
                }
            }
            listView_photolist.onRefreshComplete();
            listView.onRestoreInstanceState(listState);
        }
    }

    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        if (http.equals(SOAP_UTILS.METHOD.GETMAGAZINEINFO)) {
            String[] property_va = new String[] { "10", pageIndex + "", SplashActivity.userinfo.getComId() };
            soapService.getMagazineInfo(property_va, false);
        }
    }

}
