package com.sxit.dreamiya.base.component;

import com.sxit.dreamiya.customview.LoadingPage;
import com.sxit.dreamiya.customview.LoadingPage.ILoadingDo;
import com.sxit.dreamiya.eventbus.EBCache;
import com.sxit.dreamiya.http.RdaResultPack;
import com.sxit.dreamiya.utils.EventCache;
import com.sxit.dreamiya.webservice.ISoapService;
import com.sxit.dreamiya.webservice.SoapRes;
import com.sxit.dreamiya.webservice.SoapService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.jpush.android.api.JPushInterface;

public abstract class BaseActivity extends Activity {
    private boolean isEventBus_HTTP = true;// 是否注册EventBus
    /** soapService **/
    public ISoapService soapService = new SoapService();
    private LoadingPage loading;
    public Intent intent = new Intent();// 页面跳转
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // eventBus = EventBus.getDefault();
        // eventBus.register(this);
        if (isEventBus_HTTP) {
            EBCache.EB_HTTP.register(this);
        }
        
        EventCache.commandActivity.unregister(this);
        EventCache.commandActivity.register(this);
        EventCache.errorHttp.unregister(this);
        EventCache.errorHttp.register(this);
    }

    @Override
    protected void onDestroy() {
        if (isEventBus_HTTP) {
            EBCache.EB_HTTP.unregister(this);
        }

        EventCache.commandActivity.unregister(this);
        EventCache.errorHttp.unregister(this);
        super.onDestroy();

    }

    // 初始化HTTP结果EventBus方法
    protected abstract void onEventMainThread(RdaResultPack http);

    /**
     * 异常返回
     * 
     * @param className
     *            类名
     */
    protected void onEventMainThread(String className) {

    }
    
    /**
     * 添加loading
     */
    public void addLoading() {
        loading = new LoadingPage(this, new ILoadingDo() {

            @Override
            public void soapFail(String methodName) {
                EventCache.errorHttp.post(methodName);
            }

        });
        addContentView(loading, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }
    
    /**
     * 移除 loading
     */
    public void removeLoading() {
        if (loading != null) {
            ViewGroup parent = (ViewGroup) loading.ll_bg.getParent();
            parent.removeView(loading.ll_bg);
            loading = null;
        }
    }
    
    /**
     * http回调SoapObject
     * @param obj   
     */
    public void onEvent(Object obj) {
        SoapRes res = (SoapRes) obj;
        if (res.getObj() == null && loading != null) {
            loading.setState(1,res.getCode());
        }else{
            removeLoading();
        }        
    }

    @Override
    protected void onResume() {
        JPushInterface.onResume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        JPushInterface.onPause(this);
        super.onPause();
    }

}
