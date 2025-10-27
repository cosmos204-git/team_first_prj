package kr.co.sist.stu.dto;

public class StuCourseMgrDTO {
	private int stuNum,courseCode,subCode;
	private String stuName, courseName, subName, examStart, examEnd;

	public StuCourseMgrDTO() {
		super();
	}

	public StuCourseMgrDTO(int stuNum, int courseCode, int subCode, String stuName, String courseName, String subName,
			String examStart, String examEnd) {
		super();
		this.stuNum = stuNum;
		this.courseCode = courseCode;
		this.subCode = subCode;
		this.stuName = stuName;
		this.courseName = courseName;
		this.subName = subName;
		this.examStart = examStart;
		this.examEnd = examEnd;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public void setExamStart(String examStart) {
		this.examStart = examStart;
	}

	public void setExamEnd(String examEnd) {
		this.examEnd = examEnd;
	}

	public int getStuNum() {
		return stuNum;
	}

	public int getCourseCode() {
		return courseCode;
	}

	public int getSubCode() {
		return subCode;
	}

	public String getStuName() {
		return stuName;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getSubName() {
		return subName;
	}

	public String getExamStart() {
		return examStart;
	}

	public String getExamEnd() {
		return examEnd;
	}

	@Override
	public String toString() {
		return "StuCourseMgrDTO [stuNum=" + stuNum + ", courseCode=" + courseCode + ", subCode=" + subCode
				+ ", stuName=" + stuName + ", courseName=" + courseName + ", subName=" + subName + ", examStart="
				+ examStart + ", examEnd=" + examEnd + "]";
	}

}
