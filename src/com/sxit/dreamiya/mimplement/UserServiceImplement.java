package com.sxit.dreamiya.mimplement;

import com.loopj.android.http.RequestParams;
import com.sxit.dreamiya.base.framework.BaseServiceImplement;
import com.sxit.dreamiya.common.Instance;
import com.sxit.dreamiya.config.MLog;
import com.sxit.dreamiya.entity.UserEntity;
import com.sxit.dreamiya.entity.http.request.RegisterEntity;
import com.sxit.dreamiya.http.BaseRdaHttp;
import com.sxit.dreamiya.http.MAsycnHttpHandler;
import com.sxit.dreamiya.http.MHttpClient;
import com.sxit.dreamiya.method.UserMethod;
import com.sxit.dreamiya.mservice.UserService;
import com.sxit.dreamiya.utils.JsonUtil;

public class UserServiceImplement extends BaseServiceImplement implements
        UserService {

    @Override
    public void userRegister(RegisterEntity registerEntity) {
        String json = Instance.gson.toJson(registerEntity);
        MHttpClient.post(UserMethod.METHOD_USER_REGISTER.getMethodUrl(), json, new MAsycnHttpHandler(this, Method("SetUserIdentfyInfo")) {
            @Override
            public void onSuccess(String json) {
                MLog.I(json);
                if (JsonUtil.isSuccess(json)) {
                    postSuccessData("");
                } else {
                    postServerError(JsonUtil.getErrorCode(json));
                }
            }
        });

    }

    @Override
    public void userLogin(String username, String password) {
        RequestParams req = new RequestParams();
        // TODO Auto-generated method stub

    }

    @Override
    public void getUserInfo(String userid) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getContacts(String userid) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateUserInfo(UserEntity userEntity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void test(String cityCode) {
//        String json = Instance.gson.toJson(registerEntity);
        RequestParams req = new RequestParams();
        req.put("cityCode", cityCode);
        
        
        MHttpClient.post(UserMethod.METHOD_USER_TEST.getMethodUrl(), req, new MAsycnHttpHandler(this, Method("test")) {
            @Override
            public void onSuccess(String json) {
                MLog.I(json);
                if (JsonUtil.isSuccess(json)) {
                    postSuccessData(json);
                } else {
                    postServerError(JsonUtil.getErrorCode(json));
                }
            }
        });
        
    }

    @Override
    public BaseRdaHttp This() {
        // TODO Auto-generated method stub
        return this;
    }

}
