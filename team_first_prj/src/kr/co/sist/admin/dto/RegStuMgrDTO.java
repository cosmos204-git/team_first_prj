package kr.co.sist.admin.dto;

public class RegStuMgrDTO {
private int stuNum;
private String courseName,profName,stuName,stuRegInputDate;
public RegStuMgrDTO(int stuNum, String courseName, String profName, String stuName, String stuRegInputDate) {
	super();
	this.stuNum = stuNum;
	this.courseName = courseName;
	this.profName = profName;
	this.stuName = stuName;
	this.stuRegInputDate = stuRegInputDate;
}
public int getStuNum() {
	return stuNum;
}
public void setStuNum(int stuNum) {
	this.stuNum = stuNum;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getProfName() {
	return profName;
}
public void setProfName(String profName) {
	this.profName = profName;
}
public String getStuName() {
	return stuName;
}
public void setStuName(String stuName) {
	this.stuName = stuName;
}
public String getStuRegInputDate() {
	return stuRegInputDate;
}
public void setStuRegInputDate(String stuRegInputDate) {
	this.stuRegInputDate = stuRegInputDate;
}
@Override
public String toString() {
	return "RegStuMgrDTO [stuNum=" + stuNum + ", courseName=" + courseName + ", profName=" + profName + ", stuName="
			+ stuName + ", stuRegInputDate=" + stuRegInputDate + "]";
}


}
