package com.sxit.dreamiya.webservice;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.sxit.dreamiya.entity.ContactInfo;
import com.sxit.dreamiya.entity.course.FinCourseInfoList;
import com.sxit.dreamiya.entity.course.FinCourseList;
import com.sxit.dreamiya.entity.news.FinNewsList;
import com.sxit.dreamiya.entity.notice.FinNoticeList;
import com.sxit.dreamiya.entity.notice.FinNoticeManagementList;
import com.sxit.dreamiya.entity.photo.FinPhotoList;
import com.sxit.dreamiya.entity.photo.FinPhotoManagementList;
import com.sxit.dreamiya.entity.user.UserInfo;
import com.sxit.dreamiya.entity.video.FinVideoList;
import com.sxit.dreamiya.utils.EventCache;
import com.sxit.dreamiya.utils.SOAP_UTILS;
import com.sxit.dreamiya.webservice.AsyncTaskBase.SoapObjectResult;

public class SoapService implements ISoapService {
	private AsyncTaskBase asynTaskBase = new AsyncTaskBase();
	private SoapRes soapRes = new SoapRes();

	@Override
	public void userLogin(Object[] property_va) {
		String[] property_nm = { "userName", "passWord" };
		asynTaskBase.setMethod(SOAP_UTILS.METHOD.NEWLOGIN);
		asynTaskBase.setProperty_nm(property_nm);
		asynTaskBase.setProperty_va(property_va);
//		final String phone = (String) property_va[0];
		asynTaskBase.executeDo(new SoapObjectResult() {

			@Override
			public void soapResult(SoapObject obj) {
			    List<UserInfo> list = new ArrayList<UserInfo>();
				SoapObject soapchild = (SoapObject) obj.getProperty(0);
				int count = soapchild.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchild.getProperty(i);
                    UserInfo userinfo = new UserInfo();
                    userinfo.setHeadPic(soapchilds.getProperty("HeadPic").toString());
                    userinfo.setSport(soapchilds.getProperty("Sport").toString());
                    userinfo.setDeid(soapchilds.getProperty("Deid").toString());
                    userinfo.setStoragelistYN(soapchilds.getProperty("StoragelistYN").toString());
                    userinfo.setUsign(soapchilds.getProperty("Usign").toString());
                    userinfo.setId(soapchilds.getProperty("Id").toString());
                    userinfo.setUserId(soapchilds.getProperty("UserId").toString());
                    userinfo.setEmpId(soapchilds.getProperty("EmpId").toString());
                    userinfo.setComId(soapchilds.getProperty("ComId").toString());
                    userinfo.setRealName(soapchilds.getProperty("RealName").toString());
                    userinfo.setBbyy(soapchilds.getProperty("Bbyy").toString());
                    userinfo.setBbgj(soapchilds.getProperty("Bbgj").toString());
                    userinfo.setKqgl(soapchilds.getProperty("Kqgl").toString());
                    userinfo.setBbhy(soapchilds.getProperty("Bbhy").toString());
                    userinfo.setVideo(soapchilds.getProperty("Video").toString());
                    userinfo.setUserptz(soapchilds.getProperty("Userptz").toString());
                    userinfo.setRecord(soapchilds.getProperty("Record").toString());
                    userinfo.setSnap(soapchilds.getProperty("Snap").toString());
                    userinfo.setMap(soapchilds.getProperty("Map").toString());
                    userinfo.setFavor(soapchilds.getProperty("Favor").toString());
                    userinfo.setDistance(soapchilds.getProperty("Distance").toString());
                    userinfo.setKinder(soapchilds.getProperty("Kinder").toString());
                    userinfo.setUpload(soapchilds.getProperty("Upload").toString());
                    userinfo.setNews(soapchilds.getProperty("News").toString());
                    userinfo.setYys(soapchilds.getProperty("Yys").toString());
                    userinfo.setPay(soapchilds.getProperty("Pay").toString());
                    userinfo.setEnddate(soapchilds.getProperty("Enddate").toString());
                    userinfo.setPayStatus(soapchilds.getProperty("PayStatus").toString());
                    userinfo.setStatus(soapchilds.getProperty("Status").toString());
                    userinfo.setKinderType(soapchilds.getProperty("KinderType").toString());
                    userinfo.setKinderName(soapchilds.getProperty("KinderName").toString());
                    userinfo.setDid(soapchilds.getProperty("Did").toString());
                    userinfo.setDevName(soapchilds.getProperty("DevName").toString());
                    userinfo.setChName(soapchilds.getProperty("ChName").toString());
                    userinfo.setDevId(soapchilds.getProperty("DevId").toString());
                    userinfo.setIp(soapchilds.getProperty("Ip").toString());
                    userinfo.setPort(soapchilds.getProperty("Port").toString());
                    userinfo.setDevPort(soapchilds.getProperty("DevPort").toString());
                    userinfo.setType(soapchilds.getProperty("Type").toString());
                    userinfo.setUsername(soapchilds.getProperty("Username").toString());
                    userinfo.setPassword(soapchilds.getProperty("Password").toString());
                    userinfo.setChNo(soapchilds.getProperty("ChNo").toString());
                    userinfo.setListCount(soapchilds.getProperty("ListCount").toString());
                    userinfo.setListNo(soapchilds.getProperty("ListNo").toString());
                    userinfo.setWidth(soapchilds.getProperty("Width").toString());
                    userinfo.setHeight(soapchilds.getProperty("Height").toString());
                    userinfo.setLongitude(soapchilds.getProperty("Longitude").toString());
                    userinfo.setLatitude(soapchilds.getProperty("Latitude").toString());
                    userinfo.setAdapterId(soapchilds.getProperty("AdapterId").toString());
                    userinfo.setPtz(soapchilds.getProperty("Ptz").toString());
                    userinfo.setZoom(soapchilds.getProperty("Zoom").toString());
                    userinfo.setTalk(soapchilds.getProperty("Talk").toString());
                    userinfo.setRtsp(soapchilds.getProperty("Rtsp").toString());
                    userinfo.setStayline(soapchilds.getProperty("Stayline").toString());
//                    userinfo.setPhone(phone);
                    
                    list.add(userinfo);
                    
//                    MyApplication.getInstance().setA_heartcount(soapchild.getProperty("Heartcount").toString());
                }
				soapRes.setObj(list);
				soapRes.setCode(SOAP_UTILS.METHOD.NEWLOGIN);
				EventCache.commandActivity.post(soapRes);
			}

			@Override
			public void soapError() {
				soapRes.setObj(null);
				soapRes.setCode(SOAP_UTILS.METHOD.NEWLOGIN);
				EventCache.commandActivity.post(soapRes);
			}
		});
	}

    @Override
    public void getUserInfoByClass(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = { "userid", "pagesize", "pageindex" };
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETUSERINFOBYCLASS);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {

            @Override
            public void soapResult(SoapObject obj) {
                List<ContactInfo> list = new ArrayList<ContactInfo>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                int count = soapchild.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchild.getProperty(i);
                    ContactInfo contactinfo = new ContactInfo();
                    contactinfo.setId(soapchilds.getProperty("Id").toString());
                    contactinfo.setComId(soapchilds.getProperty("ComId").toString());
                    contactinfo.setCardNum(soapchilds.getProperty("CardNum").toString());
                    contactinfo.setHeadPic(soapchilds.getProperty("HeadPic").toString());
                    contactinfo.setType(soapchilds.getProperty("Type").toString());
                    contactinfo.setSim(soapchilds.getProperty("Sim").toString());
                    contactinfo.setName(soapchilds.getProperty("Name").toString());
                    contactinfo.setPassword(soapchilds.getProperty("Password").toString());
                    contactinfo.setRealName(soapchilds.getProperty("RealName").toString());
                    contactinfo.setSex(soapchilds.getProperty("Sex").toString());
                    contactinfo.setRemark(soapchilds.getProperty("Remark").toString());
                    contactinfo.setIslock(soapchilds.getProperty("Islock").toString());
                    contactinfo.setgroupId(soapchilds.getProperty("groupId").toString());
                    contactinfo.setkquid(soapchilds.getProperty("kquid").toString());
                    contactinfo.setuserType(soapchilds.getProperty("userType").toString());
                    contactinfo.setPosition(soapchilds.getProperty("RealName").toString() + "%" + i + "%" + 
                    soapchilds.getProperty("Sim").toString() + "%" + soapchilds.getProperty("HeadPic").toString()
                    +"%" + soapchilds.getProperty("Type").toString());
                    
                    list.add(contactinfo);
                }
                soapRes.setObj(list);
                soapRes.setCode(SOAP_UTILS.METHOD.GETUSERINFOBYCLASS);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETUSERINFOBYCLASS);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    @Override
    public void getLatestNews(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = { "comId" };
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETLATESTNEWS);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {

            @Override
            public void soapResult(SoapObject obj) {
                List<FinNewsList> list = new ArrayList<FinNewsList>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                SoapObject soapchildres = (SoapObject) soapchild.getProperty(1);
                SoapObject soapchildress = (SoapObject) soapchildres.getProperty(0);
                int count = soapchildress.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchildress.getProperty(i);
                    FinNewsList news = new FinNewsList();
                    news.setId(soapchilds.getProperty("Id").toString());
                    news.setCommentCount(soapchilds.getProperty("CommentCount").toString());
                    news.setIsRecommend(soapchilds.getProperty("IsRecommend").toString());
                    news.setCol(soapchilds.getProperty("Col").toString());
                    news.setOrders(soapchilds.getProperty("Orders").toString());
                    news.setTitle(soapchilds.getProperty("Title").toString());
                    news.setThumbnail(soapchilds.getProperty("Thumbnail").toString());
                    news.setSource(soapchilds.getProperty("Source").toString());
                    news.setAuthor(soapchilds.getProperty("Author").toString());
                    news.setPicture(soapchilds.getProperty("Picture").toString());
                    news.setContent(soapchilds.getProperty("Content").toString());
                    news.setAdminid(soapchilds.getProperty("Adminid").toString());
                    news.setCrtime(soapchilds.getProperty("Crtime").toString());
                    news.setImportant(soapchilds.getProperty("Important").toString());
                    news.setValue(soapchilds.getProperty("Value").toString());
                    news.setColTitle(soapchilds.getProperty("ColTitle").toString());
                    news.setComId(soapchilds.getProperty("ComId").toString());
                    
                    list.add(news);
                }
                soapRes.setObj(list);
                soapRes.setCode(SOAP_UTILS.METHOD.GETLATESTNEWS);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETLATESTNEWS);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    @Override
    public void getMagazineInfo(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = { "pagesize","pageindex","comId" };
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETMAGAZINEINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                List<FinPhotoList> list = new ArrayList<FinPhotoList>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
//                SoapObject soapchildres = (SoapObject) soapchild.getProperty(0);
                int count = soapchild.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchild.getProperty(i);
                    FinPhotoList photo = new FinPhotoList();
                    photo.setComId(soapchilds.getProperty("ComId").toString());
                    photo.setId(soapchilds.getProperty("Id").toString());
                    photo.setTitle(soapchilds.getProperty("Title").toString());
                    photo.setPic(soapchilds.getProperty("Pic").toString());
                    photo.setContent(soapchilds.getProperty("Content").toString());
                    String[] time_array = soapchilds.getProperty("Crtime").toString().split("T");
                    photo.setCrtime(time_array[0]);
                  
                    list.add(photo);
                }
                soapRes.setObj(list);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.GETMAGAZINEINFO);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.GETMAGAZINEINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    @Override
    public void getVideoInfo(Object[] property_va, final boolean isPage ) {
        // TODO Auto-generated method stub
        String[] property_nm = { "pagesize","pageindex","comId" };
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETVIDEOINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                List<FinVideoList> list = new ArrayList<FinVideoList>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
//                SoapObject soapchildres = (SoapObject) soapchild.getProperty(1);
//                SoapObject soapchildress = (SoapObject) soapchildres.getProperty(0);
                int count = soapchild.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchild.getProperty(i);
                    FinVideoList video = new FinVideoList();
                    video.setComId(soapchilds.getProperty("ComId").toString());
                    video.setId(soapchilds.getProperty("Id").toString());
                    video.setOrders(soapchilds.getProperty("Orders").toString());
                    video.setIstop(soapchilds.getProperty("Istop").toString());
                    video.setVideo(soapchilds.getProperty("Video").toString());
                    video.setExtension(soapchilds.getProperty("Extension").toString());
                    video.setSize(soapchilds.getProperty("Size").toString());
                    video.setTitle(soapchilds.getProperty("Title").toString());
                    video.setPic(soapchilds.getProperty("Pic").toString());
                    video.setContent(soapchilds.getProperty("Content").toString());
                    String[] time_array = soapchilds.getProperty("Crtime").toString().split("T");
                    video.setCrtime(time_array[0]);
                    
                    list.add(video);
                }
                soapRes.setObj(list);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.GETVIDEOINFO);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.GETVIDEOINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    @Override
    public void getSourseForClassAll(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = { "comId" };
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETSOURSEFORCLASSALL);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                List<FinCourseList> list = new ArrayList<FinCourseList>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                SoapObject soapchildres = (SoapObject) soapchild.getProperty(1);
                SoapObject soapchildress = (SoapObject) soapchildres.getProperty(0);
                int count = soapchildress.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchildress.getProperty(i);
                    FinCourseList course = new FinCourseList();
                    course.setComId(soapchilds.getProperty("comId").toString());
                    course.setClassName(soapchilds.getProperty("className").toString());
                    course.setClassId(soapchilds.getProperty("classId").toString());
                   
                    
                    list.add(course);
                }
                soapRes.setObj(list);
                soapRes.setCode(SOAP_UTILS.METHOD.GETSOURSEFORCLASSALL);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETSOURSEFORCLASSALL);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    @Override
    public void getSourseForClassSingle(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = { "comId","classId"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETSOURSEFORCLASSSINGLE);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                List<FinCourseInfoList> list = new ArrayList<FinCourseInfoList>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                SoapObject soapchildres = (SoapObject) soapchild.getProperty(1);
                if(soapchildres.getPropertyCount() > 0){
                    SoapObject soapchildress = (SoapObject) soapchildres.getProperty(0);
                    int count = soapchildress.getPropertyCount();
                    for (int i = 0; i < count; i++) {
                        SoapObject soapchilds = (SoapObject) soapchildress.getProperty(i);
                        FinCourseInfoList courseinfo = new FinCourseInfoList();
                        courseinfo.setComId(soapchilds.getProperty("comId").toString());
                        courseinfo.setClassName(soapchilds.getProperty("className").toString());
                        courseinfo.setClassId(soapchilds.getProperty("classId").toString());
                        courseinfo.setTmStart(soapchilds.getProperty("tmStart").toString());
                        courseinfo.setTmEnd(soapchilds.getProperty("tmEnd").toString());
                        courseinfo.setWeek(soapchilds.getProperty("week").toString());
                        courseinfo.setLesson(soapchilds.getProperty("lesson").toString());
                        courseinfo.setCourseName(soapchilds.getProperty("courseName").toString());
                        
                        
                        list.add(courseinfo);
                    }
                }
                soapRes.setObj(list);
                soapRes.setCode(SOAP_UTILS.METHOD.GETSOURSEFORCLASSSINGLE);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETSOURSEFORCLASSSINGLE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    @Override
    public void getSoursefirstshow(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = { "phonecall","comId"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETSOURSEFIRSTSHOW);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                List<FinCourseInfoList> list = new ArrayList<FinCourseInfoList>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                SoapObject soapchildres = (SoapObject) soapchild.getProperty(1);
                SoapObject soapchildress = (SoapObject) soapchildres.getProperty(0);
                int count = soapchildress.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchildress.getProperty(i);
                    FinCourseInfoList courseinfo = new FinCourseInfoList();
                    courseinfo.setComId(soapchilds.getProperty("comId").toString());
                    courseinfo.setClassName(soapchilds.getProperty("className").toString());
                    courseinfo.setClassId(soapchilds.getProperty("classId").toString());
                    courseinfo.setTmStart(soapchilds.getProperty("tmStart").toString());
                    courseinfo.setTmEnd(soapchilds.getProperty("tmEnd").toString());
                    courseinfo.setWeek(soapchilds.getProperty("week").toString());
                    courseinfo.setLesson(soapchilds.getProperty("lesson").toString());
                    courseinfo.setCourseName(soapchilds.getProperty("courseName").toString());
                    
                    
                    list.add(courseinfo);
                }
                soapRes.setObj(list);
                soapRes.setCode(SOAP_UTILS.METHOD.GETSOURSEFIRSTSHOW);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETSOURSEFIRSTSHOW);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    @Override
    public void getNoticeInfo(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = {"comId"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETNOTICEINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                List<FinNoticeList> list = new ArrayList<FinNoticeList>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                SoapObject soapchildres = (SoapObject) soapchild.getProperty(1);
                SoapObject soapchildress = (SoapObject) soapchildres.getProperty(0);
                int count = soapchildress.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchildress.getProperty(i);
                    FinNoticeList noticeinfo = new FinNoticeList();
                    noticeinfo.setNoticecontent(soapchilds.getProperty("Noticecontent").toString());
                    noticeinfo.setTrTime(soapchilds.getProperty("TrTime").toString());
                    
                    list.add(noticeinfo);
                }
                soapRes.setObj(list);
                soapRes.setCode(SOAP_UTILS.METHOD.GETNOTICEINFO);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETNOTICEINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    @Override
    public void getNoticeInfoForSim(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = {"sim"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETNOTICEINFOFORSIM);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                List<FinNoticeManagementList> list = new ArrayList<FinNoticeManagementList>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                SoapObject soapchildres = (SoapObject) soapchild.getProperty(1);
                SoapObject soapchildress = (SoapObject) soapchildres.getProperty(0);
                int count = soapchildress.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchildress.getProperty(i);
                    FinNoticeManagementList noticeinfo = new FinNoticeManagementList();
                    noticeinfo.setId(soapchilds.getProperty("Id").toString());
                    noticeinfo.setNoticecontent(soapchilds.getProperty("Noticecontent").toString());
                    noticeinfo.setSim(soapchilds.getProperty("Sim").toString());
                    noticeinfo.setTrTime(soapchilds.getProperty("TrTime").toString());
                    
                    list.add(noticeinfo);
                }
                soapRes.setObj(list);
                soapRes.setCode(SOAP_UTILS.METHOD.GETNOTICEINFOFORSIM);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETNOTICEINFOFORSIM);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    @Override
    public void tranfoNoticeInfoForPhone(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = {"comId","content","sim"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.TRANFONOTICEINFOFORPHONE);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                soapRes.setObj(obj.getProperty("TranfoNoticeInfoForPhoneResult"));
                soapRes.setCode(SOAP_UTILS.METHOD.TRANFONOTICEINFOFORPHONE);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.TRANFONOTICEINFOFORPHONE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
   
  
    @Override
    public void getMagazineInfoSim(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pagesize","pageindex","comId","sim"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETMAGAZINEINFOSIM);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                List<FinPhotoManagementList> list = new ArrayList<FinPhotoManagementList>();
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
//                SoapObject soapchildres = (SoapObject) soapchild.getProperty(0);
                int count = soapchild.getPropertyCount();
                for (int i = 0; i < count; i++) {
                    SoapObject soapchilds = (SoapObject) soapchild.getProperty(i);
                    FinPhotoManagementList photo = new FinPhotoManagementList();
                    photo.setId(soapchilds.getProperty("Id").toString());
                    photo.setComId(soapchilds.getProperty("ComId").toString());
                    photo.setSim(soapchilds.getProperty("Sim").toString());
                    photo.setTitle(soapchilds.getProperty("Title").toString());
                    photo.setPic(soapchilds.getProperty("Pic").toString());
                    photo.setContent(soapchilds.getProperty("Content").toString());
                    String[] time_array = soapchilds.getProperty("Crtime").toString().split("T");
                    photo.setCrtime(time_array[0]);
                  
                    list.add(photo);
                }
                soapRes.setObj(list);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.GETMAGAZINEINFOSIM);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.GETMAGAZINEINFOSIM);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    @Override
    public void deleInfoForPhone(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.DELEINFOFORPHONE);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                soapRes.setObj(obj.getProperty("DeleInfoForPhoneResult"));
                soapRes.setCode(SOAP_UTILS.METHOD.DELEINFOFORPHONE);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.DELEINFOFORPHONE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    @Override
    public void getHeadPicInfo(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id","images"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETHEADPICINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
                public void soapResult(SoapObject obj) {
                soapRes.setObj(obj.getProperty("GetHeadPicInfoResult"));
                soapRes.setCode(SOAP_UTILS.METHOD.GETHEADPICINFO);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETHEADPICINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    @Override
    public void changePWInfo(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = {"sim","password","newpassword"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.CHANGEPWINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                soapRes.setObj(obj.getProperty("ChangePWInfoResult"));
                soapRes.setCode(SOAP_UTILS.METHOD.CHANGEPWINFO);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.CHANGEPWINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    @Override
    public void deleNoticeInfoForPhone(Object[] property_va ) {
        // TODO Auto-generated method stub
        String[] property_nm = {"Id"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.DELENOTICEINFOFORPHONE);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                soapRes.setObj(obj.getProperty("DeleNoticeInfoForPhoneResult"));
                soapRes.setCode(SOAP_UTILS.METHOD.DELENOTICEINFOFORPHONE);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.DELENOTICEINFOFORPHONE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
}
