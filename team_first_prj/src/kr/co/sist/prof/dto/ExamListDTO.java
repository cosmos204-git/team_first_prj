package kr.co.sist.prof.dto;

public class ExamListDTO {
	private int courseCode;
	private String courseName;
	private int subCode;
	private String subName;
	private String examOpen;
	private String examStart;
	private String examEnd;

	public ExamListDTO() {
		super();
	}
	
	public ExamListDTO(int courseCode, String courseName, int subCode, String subName, String examOpen, String examStart, String examEnd) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.subCode = subCode;
		this.subName = subName;
		this.examOpen = examOpen;
		this.examStart = examStart;
		this.examEnd = examEnd;
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

	public int getSubCode() {
		return subCode;
	}

	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getExamOpen() {
		return examOpen;
	}

	public void setExamOpen(String examOpen) {
		this.examOpen = examOpen;
	}

	public String getExamStart() {
		return examStart;
	}

	public void setExamStart(String examStart) {
		this.examStart = examStart;
	}

	public String getExamEnd() {
		return examEnd;
	}

	public void setExamEnd(String examEnd) {
		this.examEnd = examEnd;
	}

	
	
}//class
	
	

