package com.sxit.dreamiya.entity.course;

import java.io.Serializable;

/**
 * 课程详情
 * 
 * @author huanyu 类名称：NewsInfo 创建时间:2014-11-13 下午6:54:19
 */
public class FinCourseList implements Serializable{
	private String comId;
	private String className;
	private String classId ;
    public String getComId() {
        return comId;
    }
    public void setComId(String comId) {
        this.comId = comId;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    
}
