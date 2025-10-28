package kr.co.sist.prof.dto;

public class ProfStuStateDTO {
	private String profName;
	private String courseName;
	private int stuNum;
	private String stuName,stuTel;

	public ProfStuStateDTO() {
		super();
	}
	
	public ProfStuStateDTO(String profName, String courseName, int stuNum, String stuName, String stuTel) {
		super();
		this.profName = profName;
		this.courseName = courseName;
		this.stuNum = stuNum;
		this.stuName = stuName;
		this.stuTel = stuTel;
	}
	
	public String getprofName() {
		return profName;
	}

	public void setprofName(String profName) {
		this.profName = profName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuTel() {
		return stuTel;
	}
	public void setStuTel(String stuTel) {
		this.stuTel = stuTel;
	}
	
}//class
	
	

