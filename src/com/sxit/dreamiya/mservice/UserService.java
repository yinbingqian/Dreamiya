package com.sxit.dreamiya.mservice;

import com.sxit.dreamiya.base.framework.BaseService;
import com.sxit.dreamiya.entity.UserEntity;
import com.sxit.dreamiya.entity.http.request.RegisterEntity;

public interface UserService extends BaseService {
    
    void userRegister(RegisterEntity registerEntity);

    void userLogin(String username, String password);

    void getUserInfo(String userid);

    void getContacts(String userid);
    
    void updateUserInfo(UserEntity userEntity);
    
    void test(String cityCode);

}
