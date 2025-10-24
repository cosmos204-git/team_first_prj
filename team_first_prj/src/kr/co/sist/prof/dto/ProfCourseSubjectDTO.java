package kr.co.sist.prof.dto;

public class ProfCourseSubjectDTO {
	
	private int CourseCode;
	private String CourseName;
	private int SubCode;
	private String SubName;
	
	public ProfCourseSubjectDTO(){
		
	}

	public int getCourseCode() {
		return CourseCode;
	}

	public void setCourseCode(int courseCode) {
		CourseCode = courseCode;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public int getSubCode() {
		return SubCode;
	}

	public void setSubCode(int subCode) {
		SubCode = subCode;
	}

	public String getSubName() {
		return SubName;
	}

	public void setSubName(String subName) {
		SubName = subName;
	}

	@Override
	public String toString() {
		return "CourseSubjectDTO [CourseCode=" + CourseCode + ", CourseName=" + CourseName + ", SubCode=" + SubCode
				+ ", SubName=" + SubName + "]";
	}

	public ProfCourseSubjectDTO(int courseCode, String courseName, int subCode, String subName) {
		super();
		CourseCode = courseCode;
		CourseName = courseName;
		SubCode = subCode;
		SubName = subName;
	}
	
	

}
