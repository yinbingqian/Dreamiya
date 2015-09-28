package com.sxit.dreamiya.entity.video;

import java.io.Serializable;

/**
 * 新闻详情
 * 
 * @author huanyu 类名称：NewsInfo 创建时间:2014-11-13 下午6:54:19
 */
public class FinVideoList implements Serializable{
	private String Id;
	private String ComId;
	private String Orders ;
	private String Istop;
	private String Crtime;
	private String Title;
	private String Pic;
	private String Video;
	private String Extension;
	private String Size;
	private String Content;
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getComId() {
        return ComId;
    }
    public void setComId(String comId) {
        ComId = comId;
    }
    public String getOrders() {
        return Orders;
    }
    public void setOrders(String orders) {
        Orders = orders;
    }
    public String getIstop() {
        return Istop;
    }
    public void setIstop(String istop) {
        Istop = istop;
    }
    public String getCrtime() {
        return Crtime;
    }
    public void setCrtime(String crtime) {
        Crtime = crtime;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getPic() {
        return Pic;
    }
    public void setPic(String pic) {
        Pic = pic;
    }
    public String getVideo() {
        return Video;
    }
    public void setVideo(String video) {
        Video = video;
    }
    public String getExtension() {
        return Extension;
    }
    public void setExtension(String extension) {
        Extension = extension;
    }
    public String getSize() {
        return Size;
    }
    public void setSize(String size) {
        Size = size;
    }
    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }
	
	
}
