package kr.co.sist.admin.dto;

import java.util.Date;

public class CourseMgrDTO {
	private int courseCode,profNum;
	private String courseName,profName,courseStartDate,courseEndDate,courseInputDate;


	
	
	
	public CourseMgrDTO() {
		super();
	}



	

	public CourseMgrDTO(int courseCode, int profNum, String courseName, String profName, String courseStartDate,
			String courseEndDate, String courseInputDate) {
		super();
		this.courseCode = courseCode;
		this.profNum = profNum;
		this.courseName = courseName;
		this.profName = profName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.courseInputDate = courseInputDate;
	}


	


	@Override
	public String toString() {
		return "CourseMgrDTO [courseCode=" + courseCode + ", profNum=" + profNum + ", courseName=" + courseName
				+ ", profName=" + profName + ", courseStartDate=" + courseStartDate + ", courseEndDate=" + courseEndDate
				+ ", courseInputDate=" + courseInputDate + "]";
	}





	public int getCourseCode() {
		return courseCode;
	}





	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}





	public int getProfNum() {
		return profNum;
	}





	public void setProfNum(int profNum) {
		this.profNum = profNum;
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





	public String getCourseStartDate() {
		return courseStartDate;
	}





	public void setCourseStartDate(String string) {
		this.courseStartDate = string;
	}





	public String getCourseEndDate() {
		return courseEndDate;
	}





	public void setCourseEndDate(String courseEndDate) {
		this.courseEndDate = courseEndDate;
	}





	public String getCourseInputDate() {
		return courseInputDate;
	}





	public void setCourseInputDate(String courseInputDate) {
		this.courseInputDate = courseInputDate;
	}
	
	
	
}//class
