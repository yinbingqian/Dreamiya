package com.sxit.dreamiya.utils;

public class SOAP_UTILS {
	public class METHOD {
		// 分析师登陆信息--用户名|密码
		public static final String ADMINLOGIN = "AdminLogin";
		// 问答回复信息添加
		public static final String COMMUNREPLYADD = "CommunReplyAdd";
		// 问答信息添加
		public static final String COMMUNICATIONADD = "CommunicationAdd";
		// 分析师列表
		public static final String GETADMIN = "GetAdmin";
		// 返回栏目信息
		public static final String GETCOLUMNS = "GetColumns";
		// 问答回复列表
		public static final String GETCOMMUNREPLY = "GetCommunReply";
		// 分析师回复列表
		public static final String GETCOMMUNREPLYANA = "GetCommunReplyAna";
		// 根据回复ID返回详细信息
		public static final String GETCOMMUNREPLYBYID = "GetCommunReplyByID";
		// 问答分类显示
		public static final String GETCOMMUNICATION = "GetCommunication";
		// 用户个人全部问答列表
		public static final String GETCOMMUNICATIONALL = "GetCommunicationALL";
		// 用户全部问答列表
		public static final String GETCOMMUNICATIONALLUSER = "GetCommunicationAllUser";
		// 未解决问题
		public static final String GETCOMMUNICATIONANA = "GetCommunicationAna";
		// 返回新闻内容
		public static final String GETNEWSCONTENT = "GetNewsContent";
		// 返回最新新闻列表
		public static final String GETNEWSTITLE = "GetNewsTitle";
		// 接收消息首页信息 TOUSER：接收端用户ID
		public static final String INTERACTIONHOMEPAGE = "InteractionHomepage";
		// 接收用户获取推送信息 TOUSER：用户ID,HISTORYTIME：历史时间
		public static final String INTERACTIONMESSAGE = "InteractionMessage";
		// 接收用户获取推送信息 TOUSER：用户ID,HISTORYTIME：历史时间
		public static final String INTERACTIONMESSAGEANA = "InteractionMessageAna";
		// 接收用户获取推送信息 ID：消息ID,INFOTYPE:1消息/2研报，infoDirection:1用户-分析师/2分析师-用户
		public static final String INTERACTIONMESSAGEBYID = "InteractionMessageByID";
		// 互动内容提交，DEVTYPE:1Android/2iPhone，infoType:1消息/2研报，infoDirection:1用户-分析师/2分析师-用户
		public static final String INTERACTIONSUBMIT = "InteractionSubmit";
		// 根据回复ID选择最佳答案
		public static final String UPDATABESTANSWER = "UpdataBestAnswer";
		// 用户登陆信息--用户名|密码
		public static final String USERINFOLOGIN = "UserInfoLogin";
		// 用户充值
		public static final String USERPAYMENT = "UserPayment";
		// 用户注册
		public static final String USERREGISTERED = "UserRegistered";
		public static final String USERREGISTEREDV3 = "UserRegisteredV3";
		//密码重置
		public static final String USERPASSWORDRESET  = "UserPasswordReset ";
		//用户名校验：返回true可用|false不可用
		public static final String USERNAMECHECK = "UserNameCheck ";
		//编辑信息options:1昵称;2性别;3股龄;4投资风格;
		public static final String USEREDITOR = "UserEditor";
		//编辑城市信息
		public static final String USEREDITOR_CITY = "UserEditor_city";
		//编辑头像，其中images：图片的BASE64转码
		public static final String USEREDITOR_HEAD = "UserEditor_head";
		//研报列表
		public static final String GETREPORTLIST = "GetReportList";
		//研报提交，reportType:研报分类
		public static final String REPORTSUBMIT = "ReportSubmit";
		//返回研报内容
		public static final String GETREPORTCONTENT = "GetReportContent";
		//跟新红心数值
		public static final String HEARTCOUNTUPDATE  = "HeartCountUpdate";
		//获取订单信息
		public static final String GETORDERINFO  = "MobileAlipay";
		//获取验证码
		public static final String GETCODE  = "SmsVerify";
		//找回密码提交提交
		public static final String CODEVERIFY  = "CodeVerify";
		public static final String CODEVERIFYNEW  = "CodeVerifyNew";
		//用户反馈
		public static final String OPINION  = "Opinion";
		//重置密码
		public static final String USERPASSWORDFIND  = "UserPasswordFind";
		//分析师列表
		public static final String GETANALYSTJSON  = "GetAnalystJson";
		//分析师发送图片
		public static final String PICSUBMIT = "PicSubmit";
		//采纳的回答
		public static final String GETBESTANSWER="GetBestAnswer";
		//获取用户红心数
		public static final String GETHEART="GetHeart";
		//获取分析师用户红心数
		public static final String GETANALYSTMARK="GetAnalystMark";
		//手机号校验
		public static final String SIMVERIFY="SimVerify";
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
	public static final String IP = "http://guzhang.tvlicai.com";
	public static final String HEADER_URL = "http://guzhang.tvlicai.com/upload/headpic/";
	public static final String URL = IP + "/phoneinvoke.asmx?wsdl";
	public static final String URL_WITHOUT_WSDL = IP + "/phoneinvoke.asmx";
	public static final String PIC_FILE = IP + "/manage/pic/";
	public static final String PIC_JOURNAL = IP + "/manage/magpic/";
	public static final String PIC_PUSH = IP + "/upload/";
	public static final String PIC_YANBAO = IP + "/manage/pic/";
	public static final String URL_SERVER = IP + "/apksource/version.xml";
	public static final String VIDEO_PATH = IP + "/manage/videofile/";
	public static final String AUDIO_PATH = IP + "/audio/";
	public static final String COL_PATH = IP + "/columns.xml";
	public static final String HEAD_PIC_PATH = IP + "/upload/headpic/";
	public static final String COLUMN_PIC_PATH = IP + "/upload/column/";
	// login type
	public static final int ADMIN = 0;// 顾问
	public static final int USER = 1;// 投资者

}
