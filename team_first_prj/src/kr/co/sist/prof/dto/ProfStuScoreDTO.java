package kr.co.sist.prof.dto;

public class ProfStuScoreDTO {
	private int stuNum;
	private String stuName;
	private int subCode;
	private String subName;
	private int stuScore;
	
	public ProfStuScoreDTO() {
		super();
	}
	
	public ProfStuScoreDTO(int stuNum, String stuName, int subCode, String subName, int stuScore) {
		super();
		this.stuNum = stuNum;
		this.stuName = stuName;
		this.subCode = subCode;
		this.subName = subName;
		this.stuScore = stuScore;
	}
	@Override
	public String toString() {
		return "ProfStuScoreDTO [stuNum=" + stuNum + ", stuName=" + stuName + ", subCode=" + subCode + ", subName="
				+ subName + ", stuScore=" + stuScore + "]";
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
	public int getStuScore() {
		return stuScore;
	}
	public void setStuScore(int stuScore) {
		this.stuScore = stuScore;
	}
	
	

}
