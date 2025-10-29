package kr.co.sist.admin.dto;

public class CourseDTO {

	private int courseCode,profNum; 
	private String courseName; 
	private String courseStartDate,courseInputDate,courseEndDate;
			
	
	public CourseDTO() {
		super();
	}

	public CourseDTO(int courseCode, int profNum, String courseName, String courseStartDate, String courseInputDate,
			String courseEndDate) {
		super();
		this.courseCode = courseCode;
		this.profNum = profNum;
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseInputDate = courseInputDate;
		this.courseEndDate = courseEndDate;
	}

	
	public CourseDTO(int courseCode, String courseName, String courseStartDate, String courseEndDate) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
	}

	public CourseDTO(int courseCode, String courseName) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
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

	public String getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(String courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public String getCourseInputDate() {
		return courseInputDate;
	}

	public void setCourseInputDate(String courseInputDate) {
		this.courseInputDate = courseInputDate;
	}

	public String getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(String courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	@Override
	public String toString() {
		return "CourseDTO [courseCode=" + courseCode + ", profNum=" + profNum + ", courseName=" + courseName
				+ ", courseStartDate=" + courseStartDate + ", courseInputDate=" + courseInputDate + ", courseEndDate="
				+ courseEndDate + "]";
	} 
	  
	
}
