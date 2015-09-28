package com.sxit.dreamiya.db;

import java.util.ArrayList;
import java.util.List;

import com.sxit.dreamiya.entity.user.UserInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DBNAME = "";
	private static final int VERSION = 1;
	private static final CursorFactory FACTORY = null;
	private static String DB_NAME = "babycare.db";
	private Context context;
	private static String DB_PATH = "/data/data/com.sxit.dreamiya/databases/";
	private static final int ASSETS_SUFFIX_BEGIN = 101;
	private static final int ASSETS_SUFFIX_END = 103;
	private SQLiteDatabase myDataBase = null;

	private String CHAT_DB = "";
	private String FRIE_DB = "";

	/**
	 * _id integer primary key autoincrement not null "byte_content blob "user_guid text "insert_date text "direction integer "type integer "user_clid bigint "target_type integer "child_clid text
	 * 
	 */
	private static final String CREATE_TABLE_T_BC_USER = "CREATE TABLE T_BC_USER (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,HEADPIC TEXT, SPORT TEXT,  DEID TEXT, STORAGELISTYN TEXT,USIGN TEXT,ID TEXT,USERID TEXT,EMPID TEXT, COMID TEXT,REALNAME TEXT,BBYY TEXT,BBGJ TEXT,KQGL TEXT,BBHY TEXT,VIDEO TEXT,USERPTZ TEXT,RECORD TEXT,SNAP TEXT,MAP TEXT,FAVOR TEXT,DISTANCE TEXT,KINDER TEXT,UPLOAD TEXT,NEWS TEXT,YYS TEXT,PAY TEXT,ENDDATE TEXT,PAYSTATUS TEXT,STATUS TEXT,KINDERTYPE TEXT,KINDERNAME TEXT,DID TEXT,DEVNAME TEXT,CHNAME TEXT,DEVID TEXT,IP TEXT,PORT TEXT,DEVPORT TEXT,TYPE TEXT,USERNAME TEXT,PASSWORD TEXT,CHNO TEXT,LISTCOUNT TEXT,LISTNO TEXT,WIDTH TEXT,HEIGHT TEXT,LONGITUDE TEXT,LATITUDE TEXT,ADAPTERID TEXT,PTZ TEXT,ZOOM TEXT,TALK TEXT,RTSP TEXT,STAYLINE TEXT,PHONE TEXT)";

	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, null, version);
		this.context = context;
	}

	public DBHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	public DBHelper(Context context, String name) {
		this(context, name, VERSION);
	}

	public DBHelper(Context context) {
		this(context, DB_PATH + DB_NAME);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("babycare.db ========== onCreate");
		db.execSQL(CREATE_TABLE_T_BC_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	/**
	 * 顾问模块列表
	 */

	/**
	 * 
	 * CREATE TABLE T_SU_ARI (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ADV_USER_ID TEXT, NAME TEXT, PASSWORD TEXT ,REALNAME TEXT, ISLOCK INTEGER, SEX INTEGER, MARK TEXT, REWARDMARK TEXT, PAIDMARK TEXT, HEADPIC TEXT, GROUP_ID TEXT, MOBILEPHONE TEXT, EMAIL TEXT, CRTIME TEXT, ORGID TEXT, SPECIALTY TEXT ,RESUME TEXT, LEVEL TEXT ,STATUS TEXT, PTITLE TEXT, ORGNAME TEXT)
	 * 
	 * @param data
	 */
	public void insUserInfo(UserInfo data) {
		System.out.println("#SU DB# insAdviserInfo");
		SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("HeadPic", data.getHeadPic());
			values.put("Sport", data.getSport());
			values.put("Deid", data.getDeid());
			values.put("StoragelistYN", data.getStoragelistYN());
			values.put("Usign", data.getUsign());
			values.put("Id", data.getId());
			values.put("UserId", data.getUserId());
			values.put("EmpId", data.getEmpId());
			values.put("ComId", data.getComId());
			values.put("RealName", data.getRealName());
			values.put("Bbyy", data.getBbyy());
			values.put("Bbgj", data.getBbgj());
			values.put("Kqgl", data.getKqgl());
			values.put("Bbhy", data.getBbhy());
			values.put("Video", data.getVideo());
			values.put("Userptz", data.getUserptz());
			values.put("Record", data.getRecord());
			values.put("Snap", data.getSnap());
			values.put("Map", data.getMap());
			values.put("Favor", data.getFavor());
			values.put("Distance", data.getDistance());
			values.put("Kinder", data.getKinder());
			values.put("Upload", data.getUpload());
			values.put("News", data.getNews());
			values.put("Yys", data.getYys());
			values.put("Pay", data.getPay());
			values.put("Enddate", data.getEnddate());
			values.put("PayStatus", data.getPayStatus());
			values.put("Status", data.getStatus());
			values.put("KinderType", data.getKinderType());
			values.put("KinderName", data.getKinderName());
			values.put("Did", data.getDid());
			values.put("DevName", data.getDevName());
			values.put("ChName", data.getChName());
			values.put("DevId", data.getDevId());
			values.put("Ip", data.getIp());
			values.put("Port", data.getPort());
			values.put("DevPort", data.getDevPort());
			values.put("Type", data.getType());
			values.put("Username", data.getUsername());
			values.put("Password", data.getPassword());
			values.put("ChNo", data.getChNo());
			values.put("ListCount", data.getListCount());//18640524618
			values.put("ListNo", data.getListNo());
			values.put("Width", data.getWidth());
			values.put("Height", data.getHeight());
			values.put("Longitude", data.getLongitude());
			values.put("Latitude", data.getLatitude());
			values.put("AdapterId", data.getAdapterId());
			values.put("Ptz", data.getPtz());
			values.put("Zoom", data.getZoom());
			values.put("Talk", data.getTalk());
			values.put("Rtsp", data.getRtsp());
			values.put("Stayline", data.getStayline());
			values.put("Phone", data.getPhone());
			db.insert("T_BC_USER", "", values);
		close();
	}

	public void setUserPic(String pic, String comid) {
      ContentValues cv = new ContentValues();
      cv.put("headpic", pic);
      SQLiteDatabase db = getWritableDatabase();
      db.update("T_BC_USER", cv, "comid=?", new String[] {comid});
      close();
  }
	
	public int clearAllUserInfo() {
		System.out.println("#SU DB# clearAllAdviser");
		SQLiteDatabase db = getWritableDatabase();
		return db.delete("T_BC_USER", null, null);
	}

	public List<UserInfo> queryUserInfo() {
		System.out.println("#SU DB# queryAdviserInfo");
		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo user = new UserInfo();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM T_BC_USER", null);
		while (c.moveToNext()) {
		    user = new UserInfo();
		    user.setHeadPic(c.getString(c.getColumnIndex("HEADPIC")));
		    user.setSport(c.getString(c.getColumnIndex("SPORT")));
		    user.setDeid(c.getString(c.getColumnIndex("DEID")));
		    user.setStoragelistYN(c.getString(c.getColumnIndex("STORAGELISTYN")));
		    user.setUsign(c.getString(c.getColumnIndex("USIGN")));
		    user.setId(c.getString(c.getColumnIndex("ID")));
		    user.setUserId(c.getString(c.getColumnIndex("USERID")));
		    user.setEmpId(c.getString(c.getColumnIndex("EMPID")));
		    user.setComId(c.getString(c.getColumnIndex("COMID")));
		    user.setRealName(c.getString(c.getColumnIndex("REALNAME")));
		    user.setBbyy(c.getString(c.getColumnIndex("BBYY")));
		    user.setBbgj(c.getString(c.getColumnIndex("BBGJ")));
		    user.setKqgl(c.getString(c.getColumnIndex("KQGL")));
		    user.setBbhy(c.getString(c.getColumnIndex("BBHY")));
		    user.setVideo(c.getString(c.getColumnIndex("VIDEO")));
		    user.setUserptz(c.getString(c.getColumnIndex("USERPTZ")));
		    user.setRecord(c.getString(c.getColumnIndex("RECORD")));
		    user.setSnap(c.getString(c.getColumnIndex("SNAP")));
		    user.setMap(c.getString(c.getColumnIndex("MAP")));
		    user.setFavor(c.getString(c.getColumnIndex("FAVOR")));
		    user.setDistance(c.getString(c.getColumnIndex("DISTANCE")));
		    user.setKinder(c.getString(c.getColumnIndex("KINDER")));
		    user.setUpload(c.getString(c.getColumnIndex("UPLOAD")));
		    user.setNews(c.getString(c.getColumnIndex("NEWS")));
		    user.setYys(c.getString(c.getColumnIndex("YYS")));
		    user.setPay(c.getString(c.getColumnIndex("PAY")));
		    user.setEnddate(c.getString(c.getColumnIndex("ENDDATE")));
		    user.setPayStatus(c.getString(c.getColumnIndex("PAYSTATUS")));
		    user.setStatus(c.getString(c.getColumnIndex("STATUS")));
		    user.setKinderType(c.getString(c.getColumnIndex("KINDERTYPE")));
		    user.setKinderName(c.getString(c.getColumnIndex("KINDERNAME")));
		    user.setDid(c.getString(c.getColumnIndex("DID")));
		    user.setDevName(c.getString(c.getColumnIndex("DEVNAME")));
		    user.setChName(c.getString(c.getColumnIndex("CHNAME")));
		    user.setDevId(c.getString(c.getColumnIndex("DEVID")));
		    user.setIp(c.getString(c.getColumnIndex("IP")));
		    user.setPort(c.getString(c.getColumnIndex("PORT")));
		    user.setDevPort(c.getString(c.getColumnIndex("DEVPORT")));
		    user.setType(c.getString(c.getColumnIndex("TYPE")));
		    user.setUsername(c.getString(c.getColumnIndex("USERNAME")));
		    user.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
		    user.setChNo(c.getString(c.getColumnIndex("CHNO")));
		    user.setListCount(c.getString(c.getColumnIndex("LISTCOUNT")));
		    user.setListNo(c.getString(c.getColumnIndex("LISTNO")));
		    user.setWidth(c.getString(c.getColumnIndex("WIDTH")));
		    user.setHeight(c.getString(c.getColumnIndex("HEIGHT")));
		    user.setLongitude(c.getString(c.getColumnIndex("LONGITUDE")));
		    user.setLatitude(c.getString(c.getColumnIndex("LATITUDE")));
		    user.setAdapterId(c.getString(c.getColumnIndex("ADAPTERID")));
		    user.setPtz(c.getString(c.getColumnIndex("PTZ")));
		    user.setZoom(c.getString(c.getColumnIndex("ZOOM")));
		    user.setTalk(c.getString(c.getColumnIndex("TALK")));
		    user.setRtsp(c.getString(c.getColumnIndex("RTSP")));
		    user.setStayline(c.getString(c.getColumnIndex("STAYLINE")));
            user.setPhone(c.getString(c.getColumnIndex("PHONE")));
			System.out.println(">>>??@@@" + user.getRealName());
			System.out.println(">>>??@@@" + user.getKinderName());
			list.add(user);
		}
		close();
		return list;
	}
	
	
	
//	public Adviser queryAdviserInfoById(String id) {
//		System.out.println("#SU DB# queryAdviserInfo");
//		List<Adviser> list = new ArrayList<Adviser>();
//		Adviser adviser = new Adviser();
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor c = db.rawQuery("SELECT * FROM T_SU_ARI WHERE ADV_USER_ID = "+id, null);
//		while (c.moveToNext()) {
//			adviser = new Adviser();
//			adviser.setAdv_user_id(c.getString(c.getColumnIndex("ADV_USER_ID")));
//			adviser.setCrtime(c.getString(c.getColumnIndex("CRTIME")));
//			adviser.setEmail(c.getString(c.getColumnIndex("EMAIL")));
//			adviser.setGroupid(c.getString(c.getColumnIndex("GROUP_ID")));
//			adviser.setHeadpic(c.getString(c.getColumnIndex("HEADPIC")));
//			adviser.setSex(c.getString(c.getColumnIndex("SEX")));
//			adviser.setIslock(c.getInt(c.getColumnIndex("ISLOCK")) == 0 ? true : false);
//			adviser.setLevel(c.getString(c.getColumnIndex("LEVEL")));
//			adviser.setMark(c.getString(c.getColumnIndex("MARK")));
//			adviser.setMobilephone(c.getString(c.getColumnIndex("MOBILEPHONE")));
//			adviser.setName(c.getString(c.getColumnIndex("NAME")));
//			adviser.setOrgid(c.getString(c.getColumnIndex("ORGID")));
//			adviser.setOrgname(c.getString(c.getColumnIndex("ORGNAME")));
//			adviser.setPaidmark(c.getString(c.getColumnIndex("PAIDMARK")));
//			adviser.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
//			adviser.setPtitle(c.getString(c.getColumnIndex("PTITLE")));
//			adviser.setRealname(c.getString(c.getColumnIndex("REALNAME")));
//			adviser.setRewardmark(c.getString(c.getColumnIndex("REWARDMARK")));
//			adviser.setResume(c.getString(c.getColumnIndex("RESUME")));
//			adviser.setSpecialty(c.getString(c.getColumnIndex("SPECIALTY")));
//			adviser.setStatus(c.getString(c.getColumnIndex("STATUS")));
//			adviser.setHeartcount(c.getString(c.getColumnIndex("HEARTCOUNT")));
//			System.out.println(">>>##@@@" + adviser.getName());
//			System.out.println(">>>##@@@" + adviser.getHeartcount());
//		}
//		close();
//		return adviser;
//	}
//	
//	
//
//	public String getAdviserHeader(String aid) {
//		System.out.println("#SU DB# getAdviserHeader");
//		String header = "";
//		List<Adviser> list = new ArrayList<Adviser>();
//		Adviser adviser = new Adviser();
//		SQLiteDatabase db = this.getReadableDatabase();
//		String[] str = { aid };
//		Cursor c = db.rawQuery("SELECT * FROM T_SU_ARI WHERE ADV_USER_ID = ?", str);
//		while (c.moveToNext()) {
//			adviser = new Adviser();
//			header = c.getString(c.getColumnIndex("HEADPIC"));
//
//			System.out.println("> pic >>??" + header);
//			list.add(adviser);
//		}
//		close();
//		return header;
//	}
//
//	/**
//	 * 聊天模块
//	 */
//
//	/**
//	 * 
//	 * CREATE TABLE T_SU_MSG (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, STATUS INTEGER, CONTENT1 TEXT, CONTENT2 BLOB, CONTENT3_TITLE TEXT, CONTENT3_CONTENT TEXT, CONTENT3_URL TEXT, CONTENT3_IMG TEXT, MSG_DIRECTION INTEGER, MSG_TYPE INTEGER, MSG_DATE TEXT, LOGIN_ID TEXT, SENDER_ID TEXT
//	 * 
//	 * @param cm
//	 */
//	public void insMsgData(ChatMessage cm) {
//		System.out.println("#SU DB# insMsgData");
//		SQLiteDatabase db = getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put("STATUS", cm.getStatus());
//		values.put("CONTENT1", cm.getContent1());
//		values.put("CONTENT2", cm.getContent2());
//		values.put("CONTENT3_TITLE", cm.getContent3_title());
//		values.put("CONTENT3_CONTENT", cm.getContent3_content());
//		values.put("CONTENT3_URL", cm.getContent3_url());
//		values.put("CONTENT3_IMG", cm.getContent3_img());
//		values.put("MSG_DIRECTION", cm.getMsg_direction());
//		values.put("MSG_TYPE", cm.getMsg_type());
//		values.put("MSG_DATE", cm.getMsg_send_date());
//		values.put("LOGIN_ID", cm.getLogin_id());
//		values.put("SENDER_ID", cm.getSender_id());
//		// values.put("HEADERPIC", cm.getUserinfo().getUserheader());
//		// values.put("SENDERNAME", cm.getUserinfo().getUsername());
//		db.insert("T_SU_MSG", "", values);
//		close();
//	}
//
//	public void insAMsgData(ChatMessage cm) {
//		System.out.println("#SU DB# insAMsgData" + "id : " + cm.getLogin_id());
//		SQLiteDatabase db = getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put("STATUS", cm.getStatus());
//		values.put("CONTENT1", cm.getContent1());
//		values.put("CONTENT2", cm.getContent2());
//		values.put("CONTENT3_TITLE", cm.getContent3_title());
//		values.put("CONTENT3_CONTENT", cm.getContent3_content());
//		values.put("CONTENT3_URL", cm.getContent3_url());
//		values.put("CONTENT3_IMG", cm.getContent3_img());
//		values.put("MSG_DIRECTION", cm.getMsg_direction());
//		values.put("MSG_TYPE", cm.getMsg_type());
//		values.put("MSG_DATE", cm.getMsg_send_date());
//		values.put("LOGIN_ID", cm.getLogin_id());
//		values.put("SENDER_ID", cm.getSender_id());
//		values.put("HEADERPIC", cm.getUserinfo().getUserheader());
//		values.put("SENDERNAME", cm.getUserinfo().getUsername());
//		db.insert("T_SU_A_MSG", "", values);
//		close();
//	}
//
//	public List<ChatMessage> queryMsgData(Adviser adviser, LoginUser loginuser) {
//		System.out.println("#SU DB# queryMsgData");
//		List<ChatMessage> list = new ArrayList<ChatMessage>();
//		ChatMessage cm = new ChatMessage();
//		String[] param = { loginuser.getUserid(), adviser.getAdv_user_id() };
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		ContactInfo userinfo_adv = new ContactInfo();
//		userinfo_adv.setUserheader(adviser.getHeadpic());
//		userinfo_adv.setUserid(adviser.getAdv_user_id());
//		userinfo_adv.setUsername(adviser.getRealname());
//
//		ContactInfo userinfo_cus = new ContactInfo();
//		userinfo_cus.setUserheader(loginuser.getHeadpic());
//		userinfo_cus.setUserid(loginuser.getUserid());
//		userinfo_cus.setUsername(adviser.getRealname());
//		Cursor c = db.rawQuery("SELECT * FROM T_SU_MSG WHERE LOGIN_ID  = ? AND SENDER_ID = ? ", param);
//		System.out.println(">>>>>" + c.getCount());
//		while (c.moveToNext()) {
//			cm = new ChatMessage();
//			switch (c.getInt(c.getColumnIndex("MSG_DIRECTION"))) {
//			case 1:
//				// 我发送
//				cm.setUserinfo(userinfo_cus);
//				break;
//			case 0:
//				// 我接受
//				cm.setUserinfo(userinfo_adv);
//				System.out.println("@@@ " + userinfo_adv.getUserheader());
//				// ContactInfo ui = new ContactInfo();
//				// ui.setUserheader(c.getString(c.getColumnIndex("HEADERPIC")));
//				// ui.setUsername(c.getString(c.getColumnIndex("SENDERNAME")));
//				// ui.setUserid(c.getString(c.getColumnIndex("SENDER_ID")));
//				// cm.setUserinfo(ui);
//				break;
//			}
//			switch (c.getInt(c.getColumnIndex("MSG_TYPE"))) {
//			case 0:
//				cm.setContent1(c.getString(c.getColumnIndex("CONTENT1")));
//				break;
//			case 1:
//				cm.setContent1(c.getString(c.getColumnIndex("CONTENT1")));
////				cm.setContent2(c.getBlob(c.getColumnIndex("CONTENT1")));
////				cm.setContent2(c.getBlob(c.getColumnIndex("CONTENT2")));
//				break;
//			case 2:
//				cm.setContent3_content(c.getString(c.getColumnIndex("CONTENT3_CONTENT")));
//				cm.setContent3_img(c.getString(c.getColumnIndex("CONTENT3_IMG")));
//				cm.setContent3_url(c.getString(c.getColumnIndex("CONTENT3_URL")));
//				cm.setContent3_title(c.getString(c.getColumnIndex("CONTENT3_TITLE")));
//				break;
//
//			default:
//				break;
//			}
//			cm.setMsg_type(c.getInt(c.getColumnIndex("MSG_TYPE")));
//			cm.setLogin_id(c.getString(c.getColumnIndex("LOGIN_ID")));
//			cm.setSender_id(c.getString(c.getColumnIndex("SENDER_ID")));
//			cm.setMsg_direction(c.getInt(c.getColumnIndex("MSG_DIRECTION")));
//			cm.setMsg_send_date(c.getString(c.getColumnIndex("MSG_DATE")));
//			cm.setStatus(c.getInt(c.getColumnIndex("STATUS")));
//
//			// System.out.println(">>!!" + cm.getContent1().toString() + "   pic = " + cm.getUserinfo().getUserheader());
//			list.add(cm);
//		}
//		close();
//		return list;
//	}
//
//	/**
//	 * 顾问团消息表
//	 * 
//	 * @param adviser
//	 * @param loginuser
//	 * @return
//	 */
//
//	public List<ChatMessage> queryAMsgData(ContactInfo ui, LoginUser loginuser) {
//		System.out.println("#SU DB# queryAMsgData");
//		List<ChatMessage> list = new ArrayList<ChatMessage>();
//		ChatMessage cm = new ChatMessage();
//		String[] param = { loginuser.getUserid(), ui.getUserid() };
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		ContactInfo userinfo_cus = new ContactInfo();
//		userinfo_cus.setUserheader(loginuser.getHeadpic());
//		userinfo_cus.setUserid(loginuser.getUserid());
//		userinfo_cus.setUsername(ui.getUsername());
//		Cursor c = db.rawQuery("SELECT * FROM T_SU_A_MSG WHERE LOGIN_ID  = ? AND SENDER_ID = ? ", param);
//		System.out.println(">>>>>" + c.getCount());
//		while (c.moveToNext()) {
//			cm = new ChatMessage();
//			switch (c.getInt(c.getColumnIndex("MSG_DIRECTION"))) {
//			case 1:
//				// 我发送
//				cm.setUserinfo(userinfo_cus);
//				break;
//			case 0:
//				// 我接受
//				ui.setUserheader(ui.getUserheader());
//				System.out.println("####" + ui.getUserheader());
//				cm.setUserinfo(ui);
//				break;
//			}
//			switch (c.getInt(c.getColumnIndex("MSG_TYPE"))) {
//			case 0:
//				cm.setContent1(c.getString(c.getColumnIndex("CONTENT1")));
//				break;
//			case 1:
//				cm.setContent1(c.getString(c.getColumnIndex("CONTENT1")));
////				cm.setContent2(c.getBlob(c.getColumnIndex("CONTENT2")));
//				break;
//			case 2:
//				cm.setContent3_content(c.getString(c.getColumnIndex("CONTENT3_CONTENT")));
//				cm.setContent3_img(c.getString(c.getColumnIndex("CONTENT3_IMG")));
//				cm.setContent3_url(c.getString(c.getColumnIndex("CONTENT3_URL")));
//				cm.setContent3_title(c.getString(c.getColumnIndex("CONTENT3_TITLE")));
//				break;
//
//			default:
//				break;
//			}
//			cm.setMsg_type(c.getInt(c.getColumnIndex("MSG_TYPE")));
//			cm.setLogin_id(c.getString(c.getColumnIndex("LOGIN_ID")));
//			cm.setSender_id(c.getString(c.getColumnIndex("SENDER_ID")));
//			cm.setMsg_direction(c.getInt(c.getColumnIndex("MSG_DIRECTION")));
//			cm.setMsg_send_date(c.getString(c.getColumnIndex("MSG_DATE")));
//			cm.setStatus(c.getInt(c.getColumnIndex("STATUS")));
//
//			System.out.println(">>!!" + cm.getContent1().toString() + "   pic = " + cm.getUserinfo().getUserheader());
//			list.add(cm);
//		}
//		close();
//		return list;
//	}
//
//	public List<Information_ListBean> getRecentMsgList(LoginUser loginUser) {
//		// SELECT MAX(_ID),STATUS,CONTENT1,CONTENT2,CONTENT3_TITLE,CONTENT3_CONTENT,CONTENT3_URL,CONTENT3_IMG,MSG_DIRECTION,MSG_TYPE,MSG_DATE,SENDER_ID FROM T_SU_MSG WHERE LOGIN_ID = ?
//		System.out.println("#SU DB# getRecentMsgList");
//		List<Information_ListBean> list = new ArrayList<Information_ListBean>();
//		Information_ListBean ib = new Information_ListBean();
//		List<Adviser> adv_list = new ArrayList<Adviser>();
//		adv_list = queryAdviserInfo();
//		SQLiteDatabase db = this.getReadableDatabase();
//		if (adv_list != null) {
//
//			for (int i = 0; i < adv_list.size(); i++) {
//				String[] param = { loginUser.getUserid(), adv_list.get(i).getAdv_user_id() };
//				Cursor c = db.rawQuery("SELECT MAX(_ID) AS _ID,STATUS,CONTENT1,CONTENT2,CONTENT3_TITLE,CONTENT3_CONTENT,CONTENT3_URL,CONTENT3_IMG,MSG_DIRECTION,MSG_TYPE,MSG_DATE,SENDER_ID FROM T_SU_MSG WHERE LOGIN_ID = ? and SENDER_ID = ?", param);
//				while (c.moveToNext()) {
//					ib = new Information_ListBean();
//					ib.setAnalyst(c.getString(c.getColumnIndex("SENDER_ID")));
//
//					switch (c.getInt(c.getColumnIndex("MSG_TYPE"))) {
//					case 0:
//						ib.setContent(c.getString(c.getColumnIndex("CONTENT1")));
//						break;
//					case 1:
//						ib.setContent("图片消息");
////						ib.setContent("语音消息");
//						break;
//					case 2:
//						ib.setContent(c.getString(c.getColumnIndex("CONTENT3_TITLE")));
//						break;
//
//					default:
//						break;
//					}
//					ib.set_id(c.getInt(c.getColumnIndex("_ID")));
//					ib.setDate(c.getString(c.getColumnIndex("MSG_DATE")));
//					ib.setImgUrl(adv_list.get(i).getHeadpic());
//					// TODO
//					ib.setNotifType(2);
//					ib.setAdviser(adv_list.get(i));
//					ib.setTime(getUnreadMsgCount(loginUser, adv_list.get(i)) + "");
//					ib.setTitle(adv_list.get(i).getRealname());
//					if (ib.getContent() == null || ib.getContent().equals("")) {
//
//					} else {
//						list.add(ib);
//					}
//				}
//			}
//		}
//		close();
//		// for (Information_ListBean p : list) {
//		// System.out.println(">!" + p.get_id());
//		// }
//		Collections.sort(list, new Comparator<Information_ListBean>() {
//			public int compare(Information_ListBean arg0, Information_ListBean arg1) {
//				int res = -2;
//				int num1 = arg0.get_id();
//				int num2 = arg1.get_id();
//				// System.out.println(">>>" + num1 + "  " + num2);
//				if (num1 > num2) {
//					res = 1;
//				} else if (num1 < num2) {
//					res = -1;
//				} else {
//					res = 0;
//				}
//
//				return res;
//			}
//		});
//
//		//
//		// list.add(queryAdviserGz());
//		// list.add(queryAdviserNs());
//		Collections.reverse(list);
//		// for (Information_ListBean p : list) {
//		// System.out.println(">?" + p.get_id());
//		// }
//
//		return list;
//	}
//
//	public List<Information_ListBean> getRecentMsgListSuper(LoginUser loginUser) {
//		// SELECT MAX(_ID),STATUS,CONTENT1,CONTENT2,CONTENT3_TITLE,CONTENT3_CONTENT,CONTENT3_URL,CONTENT3_IMG,MSG_DIRECTION,MSG_TYPE,MSG_DATE,SENDER_ID FROM T_SU_MSG WHERE LOGIN_ID = ?
//		List<Information_ListBean> tempdata = new ArrayList<Information_ListBean>();
//		List<Information_ListBean> tempdata2 = new ArrayList<Information_ListBean>();
//		System.out.println("#SU DB# getRecentMsgList");
//		List<Information_ListBean> list = new ArrayList<Information_ListBean>();
//		Information_ListBean ib = new Information_ListBean();
//		List<Adviser> adv_list = new ArrayList<Adviser>();
//		adv_list.add(queryAdviserGz());
//		adv_list.add(queryAdviserNs());
//		SQLiteDatabase db = this.getReadableDatabase();
//		if (adv_list != null) {
//
//			for (int i = 0; i < adv_list.size(); i++) {
//				String[] param = { loginUser.getUserid(), adv_list.get(i).getAdv_user_id() };
//				Cursor c = db.rawQuery("SELECT MAX(_ID) AS _ID,STATUS,CONTENT1,CONTENT2,CONTENT3_TITLE,CONTENT3_CONTENT,CONTENT3_URL,CONTENT3_IMG,MSG_DIRECTION,MSG_TYPE,MSG_DATE,SENDER_ID FROM T_SU_MSG WHERE LOGIN_ID = ? and SENDER_ID = ?", param);
//				while (c.moveToNext()) {
//					ib = new Information_ListBean();
//					ib.setAnalyst(c.getString(c.getColumnIndex("SENDER_ID")));
//
//					switch (c.getInt(c.getColumnIndex("MSG_TYPE"))) {
//					case 0:
//						ib.setContent(c.getString(c.getColumnIndex("CONTENT1")));
//						break;
//					case 1:
//						ib.setContent("图片消息");
//						ib.setContent("语音消息");
//						break;
//					case 2:
//						ib.setContent(c.getString(c.getColumnIndex("CONTENT3_TITLE")));
//						break;
//
//					default:
//						break;
//					}
//					ib.set_id(c.getInt(c.getColumnIndex("_ID")));
//					ib.setDate(c.getString(c.getColumnIndex("MSG_DATE")));
//					ib.setImgUrl(adv_list.get(i).getHeadpic());
//					// TODO
//					if (adv_list.get(i).getAdv_user_id().toString().equals("-1")) {
//						ib.setNotifType(1);
//					} else if (adv_list.get(i).getAdv_user_id().toString().equals("1")) {
//						ib.setNotifType(0);
//					} else {
//						ib.setNotifType(2);
//					}
//
//					ib.setAdviser(adv_list.get(i));
//					ib.setTime(getUnreadMsgCount(loginUser, adv_list.get(i)) + "");
//					ib.setTitle(adv_list.get(i).getRealname());
//					if (ib.getContent() == null || ib.getContent().equals("")) {
//
//					} else {
//						list.add(ib);
//					}
//				}
//			}
//		}
//		close();
//
//		tempdata.add(list.get(0));
//		tempdata.add(list.get(1));
//		tempdata2 = getRecentMsgList(loginUser);
//		for (int i = 0; i < tempdata2.size(); i++) {
//			tempdata.add(tempdata2.get(i));
//		}
//		// tempdata = getRecentMsgList(loginUser);
//
//		return tempdata;
//	}
//
//	public List<Information_ListBean> getRecentAMsgList(LoginUser loginUser) {
//		System.out.println("#SU DB# getaARecentMsgList  id : " + loginUser.getUserid());
//		List<Information_ListBean> list = new ArrayList<Information_ListBean>();
//		Information_ListBean ib = new Information_ListBean();
//		List<String> cus_list = new ArrayList<String>();
//		cus_list = getCustomIds(loginUser.getUserid() + "");
//		SQLiteDatabase db = this.getReadableDatabase();
//		
//		if (cus_list != null) {
//
//			for (int i = 0; i < cus_list.size(); i++) {
//				String[] param = { loginUser.getUserid(), cus_list.get(i) };
//				Cursor c = db.rawQuery("SELECT MAX(_ID) _ID,STATUS,CONTENT1,CONTENT2,CONTENT3_TITLE,CONTENT3_CONTENT,CONTENT3_URL,CONTENT3_IMG,MSG_DIRECTION,MSG_TYPE,MSG_DATE,SENDER_ID,HEADERPIC,SENDERNAME FROM T_SU_A_MSG WHERE LOGIN_ID = ? AND SENDER_ID  = ?", param);
//				while (c.moveToNext()) {
//
//					ib = new Information_ListBean();
//					ib.setAnalyst(c.getString(c.getColumnIndex("SENDER_ID")));
//					switch (c.getInt(c.getColumnIndex("MSG_TYPE"))) {
//					case 0:
//						ib.setContent(c.getString(c.getColumnIndex("CONTENT1")));
//						break;
//					case 1:
//						ib.setContent("图片消息");
////						ib.setContent("语音消息");
//						break;
//					case 2:
//						ib.setContent(c.getString(c.getColumnIndex("CONTENT3_TITLE")));
//						break;
//
//					default:
//						break;
//					}
//					ib.set_id(c.getInt(c.getColumnIndex("_ID")));
//					ib.setDate(c.getString(c.getColumnIndex("MSG_DATE")));
//					ib.setImgUrl(c.getString(c.getColumnIndex("HEADERPIC")));
//					// TODO
//					ib.setNotifType(2);
//					ContactInfo userinfo = new ContactInfo();
//					userinfo.setUserid(c.getString(c.getColumnIndex("SENDER_ID")));
//					userinfo.setUsername(c.getString(c.getColumnIndex("SENDERNAME")));
//					userinfo.setUserheader(c.getString(c.getColumnIndex("HEADERPIC")));
//					ib.setUserinfo(userinfo);
//					ib.setTime(getUnreadAMsgCount(loginUser, cus_list.get(i)) + "");
//					ib.setTitle(c.getString(c.getColumnIndex("SENDERNAME")));
//					if (ib.getContent() == null || ib.getContent().equals("")) {
//
//					} else {
//						list.add(ib);
//					}
//
//				}
//			}
//		}
//		close();
//		Collections.sort(list, new Comparator<Information_ListBean>() {
//			public int compare(Information_ListBean arg0, Information_ListBean arg1) {
//				int res = -2;
//				int num1 = arg0.get_id();
//				int num2 = arg1.get_id();
//				// System.out.println(">>>" + num1 + "  " + num2);
//				if (num1 > num2) {
//					res = 1;
//				} else if (num1 < num2) {
//					res = -1;
//				} else {
//					res = 0;
//				}
//
//				return res;
//			}
//		});
//		Collections.reverse(list);
//
//		System.out.println(">>>>>> list : " + list.size());
//
//		return list;
//	}
//
//	public List<String> getCustomIds(String loginId) {
//		List<String> list = new ArrayList<String>();
//		SQLiteDatabase db = this.getReadableDatabase();
//		String[] param = { loginId };
//		Cursor c = db.rawQuery("SELECT DISTINCT SENDER_ID FROM T_SU_A_MSG WHERE LOGIN_ID = ?", param);
//		while (c.moveToNext()) {
//			list.add(c.getString(c.getColumnIndex("SENDER_ID")));
//		}
//		System.out.println("#SU DB# getCustomIds   size : " + list.size());
//		return list;
//	}
//
//	public int getUnreadMsgCount(LoginUser loginUser, Adviser adviser) {
//		int count = -1;
//		SQLiteDatabase db = this.getReadableDatabase();
//		String[] param = { loginUser.getUserid(), adviser.getAdv_user_id() };
//		Cursor c = db.rawQuery("SELECT COUNT(_ID) AS COUNT FROM T_SU_MSG WHERE STATUS = 0 AND LOGIN_ID = ? AND SENDER_ID = ?", param);
//		while (c.moveToNext()) {
//			count = c.getInt(c.getColumnIndex("COUNT"));
//		}
//		return count;
//	}
//
//	public int getUnreadAMsgCount(LoginUser loginUser, String senderid) {
//		int count = -1;
//		SQLiteDatabase db = this.getReadableDatabase();
//		String[] param = { loginUser.getUserid(), senderid };
//		Cursor c = db.rawQuery("SELECT COUNT(_ID) AS COUNT FROM T_SU_A_MSG WHERE STATUS = 0 AND LOGIN_ID = ? AND SENDER_ID = ?", param);
//		while (c.moveToNext()) {
//			count = c.getInt(c.getColumnIndex("COUNT"));
//		}
//		return count;
//	}
//
//	public void setMsgReaded(String loginUserId, String senderId) {
//		ContentValues cv = new ContentValues();
//		cv.put("STATUS", "1");
//		SQLiteDatabase db = getWritableDatabase();
//		db.update("T_SU_MSG", cv, "LOGIN_ID=? AND SENDER_ID=?", new String[] { loginUserId, senderId });
//		close();
//	}
//
//	public void setAMsgReaded(String loginUserId, String senderId) {
//		ContentValues cv = new ContentValues();
//		cv.put("STATUS", "1");
//		SQLiteDatabase db = getWritableDatabase();
//		db.update("T_SU_A_MSG", cv, "LOGIN_ID=? AND SENDER_ID=?", new String[] { loginUserId, senderId });
//		close();
//	}
//
//	/**
//	 * 登陆者
//	 * 
//	 * @param loginUser
//	 */
//
//	public void insLoginUserInfo(LoginUser loginUser) {
//		System.out.println("#SU DB# insLoginUserInfo");
//		SQLiteDatabase db = getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put("AUTOLOGIN", loginUser.getAutologin());
//		values.put("CRTIME", loginUser.getCrtime());
//		values.put("CITY", loginUser.getCity());
//		values.put("EMAIL", loginUser.getEmail());
//		values.put("GROUP_ID", loginUser.getGroupid());
//		values.put("HEADPIC", loginUser.getHeadpic());
//		values.put("IMEI", loginUser.getImei());
//		values.put("ISLOCK", loginUser.getIslock());
//		values.put("LEVEL", loginUser.getLevel());
//		values.put("LOGIN_TYPE", loginUser.getLoginType());
//		values.put("MARK", loginUser.getMark());
//		values.put("MOBILEPHONE", loginUser.getMobilephone());
//		values.put("NAME", loginUser.getName());
//		values.put("ORGID", loginUser.getOrgid());
//		values.put("ORGNAME", loginUser.getOrgname());
//		values.put("PAIDMARK", loginUser.getPaidmark());
//		values.put("PTITLE", loginUser.getPtitle());
//		values.put("PASSWORD", loginUser.getPassword());
//		values.put("PROVINCE", loginUser.getProvince());
//		values.put("REALNAME", loginUser.getRealname());
//		values.put("REMARK", loginUser.getRemark());
//		values.put("RESUME", loginUser.getResume());
//		values.put("REWARDMARK", loginUser.getRewardmark());
//		values.put("SEX", loginUser.getSex());
//		values.put("SIM", loginUser.getSim());
//		values.put("SPECIALTY", loginUser.getSpecialty());
//		values.put("STATUS", loginUser.getStatus());
//		values.put("STOCK_AGE", loginUser.getStock_age());
//		values.put("STOCK_STYLE", loginUser.getStock_style());
//		values.put("TYPE", loginUser.getType());
//		values.put("USER_ID", loginUser.getUserid());
//		db.insert("T_SU_LUI", "", values);
//		close();
//	}
//
//	public void cheanLoginUserData() {
//		System.out.println("#SU DB# cheanLoginUserData");
//		SQLiteDatabase db = getWritableDatabase();
//		db.delete("T_SU_LUI", null, null);
//	}
//
//	public LoginUser queryLoginUserInfo() {
//		System.out.println("#SU DB# queryLoginUserInfo");
//		LoginUser loginUser = new LoginUser();
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor c = db.rawQuery("SELECT MAX(_ID) , L.* FROM T_SU_LUI L", null);
//		while (c.moveToNext()) {
//			loginUser = new LoginUser();
//			loginUser.setUserid(c.getString(c.getColumnIndex("USER_ID")));
//			loginUser.setAutologin(c.getString(c.getColumnIndex("AUTOLOGIN")));
//			loginUser.setCity(c.getString(c.getColumnIndex("CITY")));
//			loginUser.setCrtime(c.getString(c.getColumnIndex("CRTIME")));
//			loginUser.setEmail(c.getString(c.getColumnIndex("EMAIL")));
//			loginUser.setGroupid(c.getString(c.getColumnIndex("GROUP_ID")));
//			loginUser.setHeadpic(c.getString(c.getColumnIndex("HEADPIC")));
//			loginUser.setImei(c.getString(c.getColumnIndex("IMEI")));
//			loginUser.setIslock(c.getString(c.getColumnIndex("ISLOCK")));
//			loginUser.setLevel(c.getString(c.getColumnIndex("LEVEL")));
//			loginUser.setLoginType(c.getInt(c.getColumnIndex("LOGIN_TYPE")));
//			loginUser.setMark(c.getString(c.getColumnIndex("MARK")));
//			loginUser.setMobilephone(c.getString(c.getColumnIndex("MOBILEPHONE")));
//			loginUser.setName(c.getString(c.getColumnIndex("NAME")));
//			loginUser.setOrgid(c.getString(c.getColumnIndex("ORGID")));
//			loginUser.setOrgname(c.getString(c.getColumnIndex("ORGNAME")));
//			loginUser.setPaidmark(c.getString(c.getColumnIndex("PAIDMARK")));
//			loginUser.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
//			loginUser.setProvince(c.getString(c.getColumnIndex("PROVINCE")));
//			loginUser.setPtitle(c.getString(c.getColumnIndex("PTITLE")));
//			loginUser.setRealname(c.getString(c.getColumnIndex("REALNAME")));
//			loginUser.setRemark(c.getString(c.getColumnIndex("REMARK")));
//			loginUser.setResume(c.getString(c.getColumnIndex("RESUME")));
//			loginUser.setRewardmark(c.getString(c.getColumnIndex("REWARDMARK")));
//			loginUser.setSex(c.getString(c.getColumnIndex("SEX")));
//			loginUser.setSim(c.getString(c.getColumnIndex("SIM")));
//			loginUser.setSpecialty(c.getString(c.getColumnIndex("SPECIALTY")));
//			loginUser.setStatus(c.getString(c.getColumnIndex("STATUS")));
//			loginUser.setStock_age(c.getString(c.getColumnIndex("STOCK_AGE")));
//			loginUser.setStock_style(c.getString(c.getColumnIndex("STOCK_STYLE")));
//			loginUser.setType(c.getString(c.getColumnIndex("TYPE")));
//		}
//		System.out.println("###   ###  " + loginUser.getUserid() + ":" + loginUser.getName() + ":" + loginUser.getPassword() + ":" + loginUser.getRealname() + ":" + loginUser.getLoginType());
//		close();
//		return loginUser;
//	}
//
//	public void insAdviserGz(Adviser data) {
//
//		if (queryAdviserGz() == null) {
//			System.out.println("#SU DB# insAdviserGz");
//			SQLiteDatabase db = getWritableDatabase();
//			ContentValues values = new ContentValues();
//			values.put("ADV_USER_ID", data.getAdv_user_id());
//			values.put("NAME", data.getName());
//			values.put("PASSWORD", data.getPassword());
//			values.put("REALNAME", data.getRealname());
//			values.put("ISLOCK", data.isIslock() == true ? 0 : 1);
//			values.put("SEX", data.getSex());
//			values.put("MARK", data.getMark());
//			values.put("REWARDMARK", data.getRewardmark());
//			values.put("PAIDMARK", data.getPaidmark());
//			values.put("HEADPIC", "guwentuan.png");
////			values.put("HEADPIC", data.getHeadpic());
//			values.put("GROUP_ID", data.getGroupid());
//			values.put("MOBILEPHONE", data.getMobilephone());
//			values.put("EMAIL", data.getEmail());
//			values.put("CRTIME", data.getCrtime());
//			values.put("ORGID", data.getOrgid());
//			values.put("SPECIALTY", data.getSpecialty());
//			values.put("RESUME", data.getResume());
//			values.put("LEVEL", data.getLevel());
//			values.put("STATUS", data.getStatus());
//			values.put("PTITLE", data.getPtitle());
//			values.put("ORGNAME", data.getOrgname());
//			values.put("HEARTCOUNT", data.getHeartcount());
//			System.out.println("@@@rqrqrq !!! "+data.getHeartcount() );
//			db.insert("T_SU_ARI_GZ", "", values);
//		}
//		close();
//	}
//
//	public void insAdviserNs() {
//
//		if (queryAdviserNs() == null) {
//			System.out.println("#SU DB# insAdviserGz");
//			SQLiteDatabase db = getWritableDatabase();
//			ContentValues values = new ContentValues();
//			values.put("ADV_USER_ID", -1);
//			values.put("NAME", "");
//			values.put("PASSWORD", "");
//			values.put("REALNAME", "财经要闻");
//			values.put("ISLOCK", 1);
//			values.put("SEX", "");
//			values.put("MARK", "");
//			values.put("REWARDMARK", "");
//			values.put("PAIDMARK", "");
//			values.put("HEADPIC", "caijing.png");
//			values.put("GROUP_ID", "");
//			values.put("MOBILEPHONE", "");
//			values.put("EMAIL", "");
//			values.put("CRTIME", "");
//			values.put("ORGID", "");
//			values.put("SPECIALTY", "");
//			values.put("RESUME", "");
//			values.put("LEVEL", "");
//			values.put("STATUS", "");
//			values.put("PTITLE", "");
//			values.put("ORGNAME", "");
//			db.insert("T_SU_ARI_GZ", "", values);
//		}
//		close();
//	}
//
//	public Adviser queryAdviserGz() {
//		System.out.println("#SU DB# queryAdviserGz");
//		Adviser adviser = null;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor c = db.rawQuery("SELECT * FROM T_SU_ARI_GZ WHERE ADV_USER_ID = 1", null);
//		System.out.println(">>>??" + c.getCount());
//		while (c.moveToNext()) {
//			adviser = new Adviser();
//			adviser.setAdv_user_id(c.getString(c.getColumnIndex("ADV_USER_ID")));
//			adviser.setCrtime(c.getString(c.getColumnIndex("CRTIME")));
//			adviser.setEmail(c.getString(c.getColumnIndex("EMAIL")));
//			adviser.setGroupid(c.getString(c.getColumnIndex("GROUP_ID")));
//			adviser.setHeadpic(c.getString(c.getColumnIndex("HEADPIC")));
//			adviser.setSex(c.getString(c.getColumnIndex("SEX")));
//			adviser.setIslock(c.getInt(c.getColumnIndex("ISLOCK")) == 0 ? true : false);
//			adviser.setLevel(c.getString(c.getColumnIndex("LEVEL")));
//			adviser.setMark(c.getString(c.getColumnIndex("MARK")));
//			adviser.setMobilephone(c.getString(c.getColumnIndex("MOBILEPHONE")));
//			adviser.setName(c.getString(c.getColumnIndex("NAME")));
//			adviser.setOrgid(c.getString(c.getColumnIndex("ORGID")));
//			adviser.setOrgname(c.getString(c.getColumnIndex("ORGNAME")));
//			adviser.setPaidmark(c.getString(c.getColumnIndex("PAIDMARK")));
//			adviser.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
//			adviser.setPtitle(c.getString(c.getColumnIndex("PTITLE")));
//			adviser.setRealname(c.getString(c.getColumnIndex("REALNAME")));
//			adviser.setRewardmark(c.getString(c.getColumnIndex("REWARDMARK")));
//			adviser.setResume(c.getString(c.getColumnIndex("RESUME")));
//			adviser.setSpecialty(c.getString(c.getColumnIndex("SPECIALTY")));
//			adviser.setStatus(c.getString(c.getColumnIndex("STATUS")));
//			adviser.setHeartcount(c.getString(c.getColumnIndex("HEARTCOUNT")));
//			System.out.println(">>>?@@@?" + adviser.getName());
//			System.out.println(">>>?@@@?" + adviser.getHeartcount());
//		}
//		close();
//		return adviser;
//	}
//
//	public Adviser queryAdviserNs() {
//		System.out.println("#SU DB# queryAdviserNs");
//		Adviser adviser = null;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor c = db.rawQuery("SELECT * FROM T_SU_ARI_GZ WHERE ADV_USER_ID = -1", null);
//		System.out.println(">>>??" + c.getCount());
//		while (c.moveToNext()) {
//			adviser = new Adviser();
//			adviser.setAdv_user_id(c.getString(c.getColumnIndex("ADV_USER_ID")));
//			adviser.setCrtime(c.getString(c.getColumnIndex("CRTIME")));
//			adviser.setEmail(c.getString(c.getColumnIndex("EMAIL")));
//			adviser.setGroupid(c.getString(c.getColumnIndex("GROUP_ID")));
//			adviser.setHeadpic(c.getString(c.getColumnIndex("HEADPIC")));
//			adviser.setSex(c.getString(c.getColumnIndex("SEX")));
//			adviser.setIslock(c.getInt(c.getColumnIndex("ISLOCK")) == 0 ? true : false);
//			adviser.setLevel(c.getString(c.getColumnIndex("LEVEL")));
//			adviser.setMark(c.getString(c.getColumnIndex("MARK")));
//			adviser.setMobilephone(c.getString(c.getColumnIndex("MOBILEPHONE")));
//			adviser.setName(c.getString(c.getColumnIndex("NAME")));
//			adviser.setOrgid(c.getString(c.getColumnIndex("ORGID")));
//			adviser.setOrgname(c.getString(c.getColumnIndex("ORGNAME")));
//			adviser.setPaidmark(c.getString(c.getColumnIndex("PAIDMARK")));
//			adviser.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
//			adviser.setPtitle(c.getString(c.getColumnIndex("PTITLE")));
//			adviser.setRealname(c.getString(c.getColumnIndex("REALNAME")));
//			adviser.setRewardmark(c.getString(c.getColumnIndex("REWARDMARK")));
//			adviser.setResume(c.getString(c.getColumnIndex("RESUME")));
//			adviser.setSpecialty(c.getString(c.getColumnIndex("SPECIALTY")));
//			adviser.setStatus(c.getString(c.getColumnIndex("STATUS")));
//			System.out.println(">>>??" + adviser.getName());
//		}
//		close();
//		return adviser;
//	}
//
//	public int queryGzAdminMsg() {
//
//		return 0;
//
//	}
//
//	public int queryGzAdminMsg(String loginid) {
//		System.out.println("#SU DB# queryMsgData");
//		List<ChatMessage> list = new ArrayList<ChatMessage>();
//		String[] param = { loginid };
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor c = db.rawQuery("SELECT * FROM T_SU_MSG WHERE LOGIN_ID  = ? AND SENDER_ID = 1", param);
//		System.out.println(">>>>>" + c.getCount());
//		return c.getCount();
//	}
//
//	public void initSysMsgData(String loginid, String[] wStr) {
//		System.out.println(">>>　初始化消息数据...");
//		System.out.println("#SU DB# initAMsgData" + "id : " + loginid);
//		SQLiteDatabase db = getWritableDatabase();
//		ContentValues values1 = new ContentValues();
//		values1.put("STATUS", 0);
//		values1.put("CONTENT1", wStr[0]);
//		values1.put("CONTENT2", "");
//		values1.put("CONTENT3_TITLE", "");
//		values1.put("CONTENT3_CONTENT", "");
//		values1.put("CONTENT3_URL", "");
//		values1.put("CONTENT3_IMG", "");
//		values1.put("MSG_DIRECTION", 0);
//		values1.put("MSG_TYPE", 0);
//		values1.put("MSG_DATE", Utils.getSystemDate());
//		values1.put("LOGIN_ID", loginid);
//		values1.put("SENDER_ID", 1);
//		db.insert("T_SU_MSG", "", values1);
//		ContentValues values2 = new ContentValues();
//		values2.put("STATUS", 0);
//		values2.put("CONTENT1", wStr[1]);
//		values2.put("CONTENT2", "");
//		values2.put("CONTENT3_TITLE", "");
//		values2.put("CONTENT3_CONTENT", "");
//		values2.put("CONTENT3_URL", "");
//		values2.put("CONTENT3_IMG", "");
//		values2.put("MSG_DIRECTION", 0);
//		values2.put("MSG_TYPE", 0);
//		values2.put("MSG_DATE", Utils.getSystemDate());
//		values2.put("LOGIN_ID", loginid);
//		values2.put("SENDER_ID", -1);
//		db.insert("T_SU_MSG", "", values2);
//		close();
//	}

}
