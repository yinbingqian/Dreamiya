package com.sxit.dreamiya.entity.photo;

import java.io.Serializable;

/**
 * 新闻详情
 * 
 * @author huanyu 类名称：NewsInfo 创建时间:2014-11-13 下午6:54:19
 */
public class FinPhotoManagementList implements Serializable {
    private String Id;
    private String ComId;
    private String Sim;

    public String getSim() {
        return Sim;
    }

    public void setSim(String sim) {
        Sim = sim;
    }

    private String Pic;
    private String Content;
    private String Crtime;
    private String Title;

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

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
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

}
