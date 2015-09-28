package com.sxit.dreamiya.webservice;

/**
 * webService请求接口
 * @author huanyu 类名称：ISoapService 创建时间:2014-11-4 下午7:08:50
 */
public interface ISoapService extends IASoapService{
	/**
	 * 用户登录--用户名|密码
	 * 
	 * @param property_va
	 */
	void userLogin(Object[] property_va);
	
	/**
     * 获取联系人列表--用户ID|每页条数|页数
     * 
     * @param property_va
     */
	void getUserInfoByClass(Object[] property_va);
	

    /**
     * 获取最新的资讯列表--幼儿园id
     * 
     * @param property_va
     */
    void getLatestNews(Object[] property_va);
    /**
     * 获取最新的相册列表--幼儿园id|每页条数|页数
     * 
     * @param property_va
     */
    void getMagazineInfo(Object[] property_va, final boolean isPage);
    
    /**
     * 获取最新的视频列表--幼儿园id|每页条数|页数
     * 
     * @param property_va
     */
    void getVideoInfo(Object[] property_va, final boolean isPage);
    
    /**
     * 获取最新的课程班级列表--幼儿园id
     * 
     * @param property_va
     */
    void getSourseForClassAll(Object[] property_va);
    
    /**
     * 获取最新的课程列表--幼儿园id
     * 
     * @param property_va
     */
    void getSourseForClassSingle(Object[] property_va);
    
    /**
     * 获取最新的首次课程列表--幼儿园id|手机号码
     * 
     * @param property_va
     */
    void getSoursefirstshow(Object[] property_va);
    
    /**
     * 获取最新的通知列表--幼儿园id
     * 
     * @param property_va
     */
    void getNoticeInfo(Object[] property_va);
    
    /**
     * 获取最新的通知发布列表--手机号码
     * 
     * @param property_va
     */
    void getNoticeInfoForSim(Object[] property_va);
    
    /**
     * 获取最新的通知发布列表--幼儿园id|发送內容|手机号码
     * 
     * @param property_va
     */
    void tranfoNoticeInfoForPhone(Object[] property_va);
    
    /**
     * 获取最新的相册列表--每页条数|页数|幼儿园id|手机号码
     * 
     * @param property_va
     */
    void getMagazineInfoSim(Object[] property_va, final boolean isPage);
    
    /**
     * 图集删除--id
     * 
     * @param property_va
     */
    void deleInfoForPhone(Object[] property_va);
    
    /**
     * 头像上传--id|images
     * 
     * @param property_va
     */
    void getHeadPicInfo(Object[] property_va);
    
    /**
     * 修改密码--手机号码|旧密码|新密码
     * 
     * @param property_va
     */
    void changePWInfo(Object[] property_va);
    
    /**
     * 幼儿园通知管理 删除通知--Id
     * 
     * @param property_va
     */
    void deleNoticeInfoForPhone(Object[] property_va);
    
}
