package kr.co.sist.admin.dto;

import java.util.Date;

public class StudentDTO {
	private int stuNum;
	private String stuName,stuTel,stuPw;
	private Date stuInputDate;
	public StudentDTO() {
		super();
	}
	public StudentDTO(int stuNum, String stuName, String stuTel, String stuPw, Date stuInputDate) {
		super();
		this.stuNum = stuNum;
		this.stuName = stuName;
		this.stuTel = stuTel;
		this.stuPw = stuPw;
		this.stuInputDate = stuInputDate;
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
	public String getStuPw() {
		return stuPw;
	}
	public void setStuPw(String stuPw) {
		this.stuPw = stuPw;
	}
	public Date getStuInputDate() {
		return stuInputDate;
	}
	public void setStuInputDate(Date stuInputDate) {
		this.stuInputDate = stuInputDate;
	}
	
	
	
	


}//class
