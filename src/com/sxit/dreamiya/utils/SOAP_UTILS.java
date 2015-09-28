package com.sxit.dreamiya.utils;

public class SOAP_UTILS {
	public class METHOD {
		public static final String NEWLOGIN = "NewLogin";
		public static final String GETUSERINFOBYCLASS = "GetUserInfoByClass";
		public static final String GETLATESTNEWS = "GetLatestNews";
		public static final String GETMAGAZINEINFO = "GetMagazineInfo";
		public static final String GETVIDEOINFO = "GetVideoInfo";
		public static final String GETSOURSEFORCLASSALL = "GetSourseForClassAll";
		public static final String GETSOURSEFORCLASSSINGLE = "GetSourseForClassSingle";
		public static final String GETSOURSEFIRSTSHOW = "GetSoursefirstshow";
		public static final String GETNOTICEINFO = "GetNoticeInfo";
		public static final String GETNOTICEINFOFORSIM = "GetNoticeInfoForSim";
		public static final String TRANFONOTICEINFOFORPHONE = "TranfoNoticeInfoForPhone";
		public static final String GETMAGAZINEINFOSIM = "GetMagazineInfoSim";
		public static final String DELEINFOFORPHONE = "DeleInfoForPhone";
		public static final String GETHEADPICINFO = "GetHeadPicInfo";
		public static final String CHANGEPWINFO = "ChangePWInfo";
		public static final String DELENOTICEINFOFORPHONE = "DeleNoticeInfoForPhone";
	}

	public class ERROR {
		public static final String ERR0000 = "ERR 000";
		public static final String ERR0001 = "ERR 001";
		public static final String ERR0002 = "ERR 002";
		public static final String ERR0003 = "ERR 003";
		public static final String ERR0004 = "ERR 004";
		public static final String ERR0005 = "ERR 005 XML解析错误";
		public static final String ERR0006 = "ERR 006 本地错误";

	}

	public static final String NAMESPACE = "MobileNewspaper";
	public static final String IP_SIMPLE = "221.180.149.201";
	public static final String IP = "http://221.180.149.201:8899";
	public static final String URL = IP + "/phoneinvoke.asmx?wsdl";
    public static final String URL_WITHOUT_WSDL = IP + "/phoneinvoke.asmx";
    public static final String PIC_FILE = IP + "/manage/pic/";
    public static final String PIC_JOURNAL = IP + "/manage/magpic/";
    public static final String PIC_PUSH = IP + "/upload/";
    public static final String URL_SERVER = IP + "/apksource/version.xml";
    public static final String VIDEO_PATH = IP + "/manage/videofile/";
    public static final String AUDIO_PATH = IP + "/audio/";
    public static final String COL_PATH = IP + "/columns.xml";
	// login type
	public static final int TEACHER = 0;// 教师
	public static final int STUDENT = 1;// 学生家长

}
