package com.sxit.dreamiya.entity.notice;

import java.io.Serializable;

/**
 * 新闻详情
 * 
 * @author huanyu 类名称：NewsInfo 创建时间:2014-11-13 下午6:54:19
 */
public class FinNoticeList implements Serializable{
	private String Noticecontent;
	private String TrTime;
    public String getNoticecontent() {
        return Noticecontent;
    }
    public void setNoticecontent(String noticecontent) {
        Noticecontent = noticecontent;
    }
    public String getTrTime() {
        return TrTime;
    }
    public void setTrTime(String trTime) {
        TrTime = trTime;
    }
	
}
