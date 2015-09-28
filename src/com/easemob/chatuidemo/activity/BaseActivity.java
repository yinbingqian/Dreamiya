/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.easemob.chatuidemo.activity;

import com.easemob.applib.controller.HXSDKHelper;
import com.sxit.dreamiya.customview.LoadingPage;
import com.sxit.dreamiya.utils.EventCache;
import com.sxit.dreamiya.webservice.ISoapService;
import com.sxit.dreamiya.webservice.SoapRes;
import com.sxit.dreamiya.webservice.SoapService;
import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

public class BaseActivity extends FragmentActivity {
    /** soapService **/
    public ISoapService soapService = new SoapService();
    private LoadingPage loading;
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        EventCache.commandActivity.unregister(this);
        EventCache.commandActivity.register(this);
        EventCache.errorHttp.unregister(this);
        EventCache.errorHttp.register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // onresume时，取消notification显示
        HXSDKHelper.getInstance().getNotifier().reset();
        
        // umeng
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // umeng
        MobclickAgent.onPause(this);
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
    
    /**
     * http error回调
     * @param method    方法明名
     */
    public void onEventMainThread(String method) {
        
    }
}
