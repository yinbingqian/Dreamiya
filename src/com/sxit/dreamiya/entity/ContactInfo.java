package com.sxit.dreamiya.entity;

import java.io.Serializable;

public class ContactInfo implements Serializable{
	private String Id;
	private String ComId;
	private String CardNum;
	private String HeadPic;
	private String Type;
	private String Sim;
	private String Name;
	private String Password;
	private String RealName;
	private String Sex;
	private String Remark;
	private String Islock;
	private String groupId;
	private String kquid;
	private String userType;
	private String Position;
	
	public ContactInfo(){
		
	}
	public ContactInfo(String id, String comid, String cardnum, String headpic, String type, String sim, String name, 
	        String password, String realname, String sex, String remark, String islock, String groupid, String kquid, 
	        String usertype, String position){
	    
		this.Id = id;
		this.ComId = comid;
		this.CardNum = cardnum;
		this.HeadPic = headpic;
		this.Type = type;
		this.Sim = sim;
		this.Name = name;
		this.Password = password;
	    this.RealName = realname;
	    this.Sex = sex;
	    this.Remark = remark;
	    this.Islock = islock;
	    this.groupId = groupid;
	    this.kquid = kquid;
	    this.userType = usertype;
	    this.Position = position;
	}
	public String getId() {
		return Id;
	}
	public void setId(String Id) {
		this.Id = Id;
	}
	
	public String getComId() {
        return ComId;
    }
    public void setComId(String ComId) {
        this.ComId = ComId;
    }
    
    public String getCardNum() {
        return CardNum;
    }
    public void setCardNum(String CardNum) {
        this.CardNum = CardNum;
    }
    
    public String getHeadPic() {
        return HeadPic;
    }
    public void setHeadPic(String HeadPic) {
        this.HeadPic = HeadPic;
    }
    
    public String getType() {
        return Type;
    }
    public void setType(String Type) {
        this.Type = Type;
    }
    
    public String getSim() {
        return Sim;
    }
    public void setSim(String Sim) {
        this.Sim = Sim;
    }
	
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	
	public String getRealName() {
        return RealName;
    }
    public void setRealName(String RealName) {
        this.RealName = RealName;
    }
    
    public String getSex() {
        return Sex;
    }
    public void setSex(String Sex) {
        this.Sex = Sex;
    }
    
    public String getRemark() {
        return Remark;
    }
    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    
    public String getIslock() {
        return Islock;
    }
    public void setIslock(String Islock) {
        this.Islock = Islock;
    }
    
    public String getgroupId() {
        return groupId;
    }
    public void setgroupId(String groupId) {
        this.groupId = groupId;
    }
    
    public String getkquid() {
        return kquid;
    }
    public void setkquid(String kquid) {
        this.kquid = kquid;
    }
    
    public String getuserType() {
        return userType;
    }
    public void setuserType(String userType) {
        this.userType = userType;
    }
    
    public String getPosition() {
        return Position;
    }
    public void setPosition(String Position) {
        this.Position = Position;
    }
    
}
