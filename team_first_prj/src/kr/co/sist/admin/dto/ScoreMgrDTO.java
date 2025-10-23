package kr.co.sist.admin.dto;

public class ScoreMgrDTO {
	private int stuNum,stuScore ;
	private String stuName,	subName;
	
	public ScoreMgrDTO() {
		super();
	}
	
	public ScoreMgrDTO(int stuNum, int stuScore, String stuName, String subName) {
		super();
		this.stuNum = stuNum;
		this.stuScore = stuScore;
		this.stuName = stuName;
		this.subName = subName;
		
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

	@Override
	public String toString() {
		return "ScoreMgrDTO [stuNum=" + stuNum + ", stuScore=" + stuScore + ", stuName=" + stuName + ", subName="
				+ subName + ", courseName=]";
	} 
	 
}
