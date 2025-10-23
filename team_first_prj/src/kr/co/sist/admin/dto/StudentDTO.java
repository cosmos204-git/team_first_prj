package kr.co.sist.admin.dto;

import java.util.Date;

import javax.swing.ImageIcon;

public class StudentDTO {
	private int stuNum;
	private ImageIcon stuImg;
	private String stuName,stuTel,stuPass,stuEmail,stuAddr1,stuAddr2;
	private Date stuInputDate;
	
	public StudentDTO() {
		super();
	}

	
	
	public StudentDTO(int stuNum, String stuName, String stuTel) {
		super();
		this.stuNum = stuNum;
		this.stuName = stuName;
		this.stuTel = stuTel;
	}



	public StudentDTO(int stuNum, ImageIcon stuImg, String stuName, String stuTel, String stuPass, String stuEmail,
			String stuAddr1, String stuAddr2, Date stuInputDate) {
		super();
		this.stuNum = stuNum;
		this.stuImg = stuImg;
		this.stuName = stuName;
		this.stuTel = stuTel;
		this.stuPass = stuPass;
		this.stuEmail = stuEmail;
		this.stuAddr1 = stuAddr1;
		this.stuAddr2 = stuAddr2;
		this.stuInputDate = stuInputDate;
	}



	public int getStuNum() {
		return stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public ImageIcon getStuImg() {
		return stuImg;
	}

	public void setStuImg(ImageIcon stuImg) {
		this.stuImg = stuImg;
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

	public String getStuPass() {
		return stuPass;
	}

	public void setStuPass(String stuPass) {
		this.stuPass = stuPass;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public String getStuAddr1() {
		return stuAddr1;
	}

	public void setStuAddr1(String stuAddr1) {
		this.stuAddr1 = stuAddr1;
	}

	public String getStuAddr2() {
		return stuAddr2;
	}

	public void setStuAddr2(String stuAddr2) {
		this.stuAddr2 = stuAddr2;
	}

	public Date getStuInputDate() {
		return stuInputDate;
	}

	public void setStuInputDate(Date stuInputDate) {
		this.stuInputDate = stuInputDate;
	}

	
	
	
	


}//class
