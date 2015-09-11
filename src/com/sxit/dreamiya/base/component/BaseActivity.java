package com.sxit.dreamiya.base.component;

import cn.jpush.android.api.JPushInterface;

import com.sxit.dreamiya.eventbus.EBCache;
import com.sxit.dreamiya.eventbus.EventCode;
import com.sxit.dreamiya.eventbus.MEvent;
import com.sxit.dreamiya.http.RdaResultPack;

import de.greenrobot.event.EventBus;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
    private boolean isEventBus_HTTP = true;// 是否注册EventBus

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // eventBus = EventBus.getDefault();
        // eventBus.register(this);
        if (isEventBus_HTTP) {
            EBCache.EB_HTTP.register(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (isEventBus_HTTP) {
            EBCache.EB_HTTP.unregister(this);
        }
        super.onDestroy();

    }

    // public void onEventMainThread(MEvent mEvent) {
    // switch (mEvent.getEvent_code()) {
    // case 0:
    // System.out.println("HTTP");
    // System.out.println("http  " + mEvent.getObj());
    // break;
    // case 1:
    // System.out.println("ACTION");
    // break;
    // default:
    // break;
    // }
    //
    // }
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
