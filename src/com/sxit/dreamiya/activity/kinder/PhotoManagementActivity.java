package com.sxit.dreamiya.activity.kinder;

import java.util.List;

import com.easemob.chatuidemo.activity.SplashActivity;
import com.sxit.dreamiya.R;
import com.sxit.dreamiya.adapter.kinder.PhotoListManagement_Adapter;
import com.sxit.dreamiya.base.component.BaseActivity;
import com.sxit.dreamiya.entity.photo.FinPhotoManagementList;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PhotoManagementActivity extends BaseActivity{
	private Resources resources;
	private Context context;
    private int pageIndex = 1;
    private List<FinPhotoManagementList> list;
    private PhotoListManagement_Adapter adapter;
	
	private PullToRefreshListView listView_photomanagelist;
    private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kinder_photo_management);

		resources = this.getResources();
		context = this;

		viewInit();
		setListeners();
        String[] property_va = new String[] { "10", pageIndex + "", SplashActivity.userinfo.getComId(),SplashActivity.userinfo.getPhone() };
        soapService.getMagazineInfoSim(property_va, false);
	}
	
	private void viewInit(){
	    listView_photomanagelist = (PullToRefreshListView) findViewById(R.id.listView_photomanagelist);
        listView = listView_photomanagelist.getRefreshableView();
	}
	
	private void setListeners() {
	    listView_photomanagelist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("finphotolist", list.get(position - 1));
//                intent.putExtras(bundle);
//                intent.setClass(PhotoManagementActivity.this, PhotoManagementInfo_Activity.class);
//                startActivity(intent);
               
            }
        });
	    listView_photomanagelist.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });
        // end of list
	    listView_photomanagelist.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                String[] property_va = new String[] { "10", ++pageIndex + "", SplashActivity.userinfo.getComId(),SplashActivity.userinfo.getPhone() };
                soapService.getMagazineInfoSim(property_va, true);

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
            String[] property_va = new String[] { "10", pageIndex + "", SplashActivity.userinfo.getComId(),SplashActivity.userinfo.getPhone() };
            soapService.getMagazineInfoSim(property_va, false);
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
        if (res.getCode().equals(SOAP_UTILS.METHOD.GETMAGAZINEINFOSIM)) {
            Parcelable listState = listView.onSaveInstanceState();
            if (res.isPage()) {
                for (FinPhotoManagementList bean : (List<FinPhotoManagementList>) res.getObj()) {
                    list.add(bean);
                }
                adapter.notifyDataSetChanged();
            } else {
                list = (List<FinPhotoManagementList>) res.getObj();
                if (list != null) {
                    if (list.size() != 0) {
                        adapter = new PhotoListManagement_Adapter(this, list);
                        listView.setAdapter(adapter);
                    }
                }
            }
            listView_photomanagelist.onRefreshComplete();
            listView.onRestoreInstanceState(listState);
        }else if(res.getCode().equals(SOAP_UTILS.METHOD.DELEINFOFORPHONE)){
            if (res.getObj().toString().equals("ok")) {
                Toast.makeText(context, R.string.delete_success, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, R.string.delete_failed, Toast.LENGTH_SHORT).show();
            }
            pageIndex = 1;
            String[] property_va = new String[] { "10", pageIndex + "", SplashActivity.userinfo.getComId(),SplashActivity.userinfo.getPhone() };
            soapService.getMagazineInfoSim(property_va, false);
        }
    }

    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        if (http.equals(SOAP_UTILS.METHOD.GETMAGAZINEINFOSIM)) {
            String[] property_va = new String[] { "10", pageIndex + "", SplashActivity.userinfo.getComId(),SplashActivity.userinfo.getPhone() };
            soapService.getMagazineInfoSim(property_va, false);
        }
    }

}
