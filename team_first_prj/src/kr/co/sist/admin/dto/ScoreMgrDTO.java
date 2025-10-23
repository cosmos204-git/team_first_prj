package kr.co.sist.admin.dto;

public class ScoreMgrDTO {
	private int stuNum,stuScore ;
	private String stuName,	subName, courseName;
	
	public ScoreMgrDTO() {
		super();
	}
	
	public ScoreMgrDTO(int stuNum, int stuScore, String stuName, String subName, String courseName) {
		super();
		this.stuNum = stuNum;
		this.stuScore = stuScore;
		this.stuName = stuName;
		this.subName = subName;
		this.courseName = courseName;
	}//ScoreMgrDTO
	
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public int getStuScore() {
		return stuScore;
	}
	public void setStuScore(int stuScore) {
		this.stuScore = stuScore;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "ScoreMgrDTO [stuNum=" + stuNum + ", stuScore=" + stuScore + ", stuName=" + stuName + ", subName="
				+ subName + ", courseName=" + courseName + "]";
	} 
	 
}
