package kr.co.sist.admin.dto;

public class SubjectDTO {
private int sub_code; 
private String subName,subInputdate;


public SubjectDTO() {
	super();
}

public SubjectDTO(int sub_code, String subName, String subInputdate) {
	super();
	this.sub_code = sub_code;
	this.subName = subName;
	this.subInputdate = subInputdate;
}//SubjectDTO



public SubjectDTO(int sub_code, String subName) {
	super();
	this.sub_code = sub_code;
	this.subName = subName;
}

public int getSubCode() {
	return sub_code;
}
public void setSubCode(int sub_code) {
	this.sub_code = sub_code;
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
	return "SubjectDTO [sub_code=" + sub_code + ", subName=" + subName + ", subInputdate=" + subInputdate + "]";
}


}

