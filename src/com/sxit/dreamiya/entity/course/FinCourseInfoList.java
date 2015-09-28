package com.sxit.dreamiya.entity.course;

import java.io.Serializable;

/**
 * 课程详情
 * 
 * @author huanyu 类名称：NewsInfo 创建时间:2014-11-13 下午6:54:19
 */
public class FinCourseInfoList implements Serializable{
	private String comId;
	private String className;
	private String classId ;
	private String tmStart ;
	private String tmEnd ;
	private String week ;
	private String lesson ;
	private String courseName ;
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
    public String getTmStart() {
        return tmStart;
    }
    public void setTmStart(String tmStart) {
        this.tmStart = tmStart;
    }
    public String getTmEnd() {
        return tmEnd;
    }
    public void setTmEnd(String tmEnd) {
        this.tmEnd = tmEnd;
    }
    public String getWeek() {
        return week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getLesson() {
        return lesson;
    }
    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
}
