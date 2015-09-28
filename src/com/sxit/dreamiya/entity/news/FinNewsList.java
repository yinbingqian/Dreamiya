package com.sxit.dreamiya.entity.news;

import java.io.Serializable;

/**
 * 新闻详情
 * 
 * @author huanyu 类名称：NewsInfo 创建时间:2014-11-13 下午6:54:19
 */
public class FinNewsList implements Serializable{
	private String Id;
	private String CommentCount;
	private String IsRecommend ;
	private String Col;
	private String Orders;
	private String Title;
	private String Thumbnail;
	private String Source;
	private String Author;
	private String Picture;
	private String Content;
	private String Adminid;
	private String Crtime;
	private String Important;
	private String Value;
	private String ColTitle;
	private String ComId;

	public String getComId() {
        return ComId;
    }

    public void setComId(String comId) {
        ComId = comId;
    }

    public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getCommentCount() {
		return CommentCount;
	}

	public void setCommentCount(String commentCount) {
		CommentCount = commentCount;
	}

	public String getIsRecommend() {
		return IsRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		IsRecommend = isRecommend;
	}

	public String getCol() {
		return Col;
	}

	public void setCol(String col) {
		Col = col;
	}

	public String getOrders() {
		return Orders;
	}

	public void setOrders(String orders) {
		Orders = orders;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getThumbnail() {
		return Thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		Thumbnail = thumbnail;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getAdminid() {
		return Adminid;
	}

	public void setAdminid(String adminid) {
		Adminid = adminid;
	}

	public String getCrtime() {
		return Crtime;
	}

	public void setCrtime(String crtime) {
		Crtime = crtime;
	}

	public String getImportant() {
		return Important;
	}

	public void setImportant(String important) {
		Important = important;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getColTitle() {
		return ColTitle;
	}

	public void setColTitle(String colTitle) {
		ColTitle = colTitle;
	}

}
