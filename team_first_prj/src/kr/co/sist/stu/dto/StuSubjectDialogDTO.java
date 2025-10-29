package kr.co.sist.stu.dto;

public class StuSubjectDialogDTO {
	
	private int courseCode, subCode;
	private String subName;
	
	
	public StuSubjectDialogDTO() {
		
	}
	
	

	public StuSubjectDialogDTO(int courseCode, int subCode, String subName) {
		this.courseCode = courseCode;
		this.subCode = subCode;
		this.subName = subName;
	}



	public int getCourseCode() {
		return courseCode;
	}


	public int getSubCode() {
		return subCode;
	}


	public String getSubName() {
		return subName;
	}



	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}



	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}



	public void setSubName(String subName) {
		this.subName = subName;
	}



	@Override
	public String toString() {
		return "StuSubjectDialogDTO [courseCode=" + courseCode + ", subCode=" + subCode + ", subName=" + subName + "]";
	}
	
	
	
}//class