package kr.co.sist.admin.dto;

public class SubjectDTO {
private int subCode; 
private String subName,subInputdate;


public SubjectDTO() {
	super();
}

public SubjectDTO(int sub_code, String subName, String subInputdate) {
	super();
	this.subCode = sub_code;
	this.subName = subName;
	this.subInputdate = subInputdate;
}//SubjectDTO



public SubjectDTO(int sub_code, String subName) {
	super();
	this.subCode = sub_code;
	this.subName = subName;
}

public int getSubCode() {
	return subCode;
}
public void setSubCode(int sub_code) {
	this.subCode = sub_code;
}
public String getSubName() {
	return subName;
}
public void setSubName(String subName) {
	this.subName = subName;
}
public String getSubInputdate() {
	return subInputdate;
}
public void setSubInputdate(String subInputdate) {
	this.subInputdate = subInputdate;
}
@Override
public String toString() {
	return "SubjectDTO [sub_code=" + subCode + ", subName=" + subName + ", subInputdate=" + subInputdate + "]";
}



}

