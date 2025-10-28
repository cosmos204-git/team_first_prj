package kr.co.sist.stu.dto;

import java.sql.Date;

public class SearchCourseDTO {

	private int stuNum;
	private int courseCode;
	private String courseName;
	private Date courseStartDate;
	private Date courseEndDate;
	private Date courseInputDate;
	private boolean courseFlag;

	public SearchCourseDTO() {}

	public int getStuNum() {
		return stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public Date getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(Date courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public Date getCourseInputDate() {
		return courseInputDate;
	}

	public void setCourseInputDate(Date courseInputDate) {
		this.courseInputDate = courseInputDate;
	}

	public boolean isCourseFlag() {
		return courseFlag;
	}

	public void setCourseFlag(boolean courseFlag) {
		this.courseFlag = courseFlag;
	}

	@Override
	public String toString() {
		return "SearchCourseDTO [stuNum=" + stuNum + ", courseCode=" + courseCode + ", courseName=" + courseName
				+ ", courseStartDate=" + courseStartDate + ", courseEndDate=" + courseEndDate + ", courseInputDate="
				+ courseInputDate + ", courseFlag=" + courseFlag + "]";
	}

}