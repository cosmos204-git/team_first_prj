package kr.co.sist.stu.dto;

public class StuReportDTO {
    private int stuScore;
    private int stuRank;
    private String stuName;
    private String subName;

    public StuReportDTO() {}

    public StuReportDTO(int stuScore, int stuRank, String stuName, String subName) {
        this.stuScore = stuScore;
        this.stuRank = stuRank;
        this.stuName = stuName;
        this.subName = subName;
    }

    public int getStuScore() { 
    	return stuScore; 
    	
    }
    public void setStuScore(int stuScore) {
    	this.stuScore = stuScore; 
    	
    }

    public int getStuRank() {
    	return stuRank; 
    	
    }
    public void setStuRank(int stuRank) {
    	this.stuRank = stuRank; 
    	
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
        return "StuReportDTO [stuScore=" + stuScore + ", stuRank=" + stuRank +
               ", stuName=" + stuName + ", subName=" + subName + "]";
    }
}
