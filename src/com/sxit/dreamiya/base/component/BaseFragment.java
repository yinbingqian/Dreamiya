package com.sxit.dreamiya.base.component;

import com.sxit.dreamiya.eventbus.EBCache;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.webservice.ISoapService;
import com.sxit.dreamiya.webservice.SoapService;

import android.app.Fragment;
import android.os.Bundle;

public abstract class BaseFragment extends Fragment {
    private boolean isEventBus_HTTP = true;// 是否注册EventBus
    /** soapService **/
    public ISoapService soapService = new SoapService();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isEventBus_HTTP) {
            EBCache.EB_HTTP.register(this);
        }
    }

    @Override
    public void onDestroy() {
        if (isEventBus_HTTP) {
            EBCache.EB_HTTP.unregister(this);
        }
        super.onDestroy();
    }
    // 初始化HTTP结果EventBus方法
    protected  abstract void onEventMainThread(RdaResultPack http);
    /**
     * 异常返回
     * 
     * @param className
     *            类名
     */
    protected void onEventMainThread(String className) {

    }
}
