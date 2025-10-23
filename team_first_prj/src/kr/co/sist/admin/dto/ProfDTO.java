package kr.co.sist.admin.dto;

import java.util.Date;

import javax.swing.ImageIcon;

public class ProfDTO {

	private int profNum;
	private ImageIcon profImg;
	private String profName,profPass,profTel,profEmail;
	private Date profInputDate;
	public ProfDTO() {
		super();
	}
	
	
	
	public ProfDTO(int profNum, String profName, String profTel) {
		super();
		this.profNum = profNum;
		this.profName = profName;
		this.profTel = profTel;
	}



	public ProfDTO(int profNum, ImageIcon profImg, String profName, String profPass, String profTel, String profEmail,
			Date profInputDate) {
		super();
		this.profNum = profNum;
		this.profImg = profImg;
		this.profName = profName;
		this.profPass = profPass;
		this.profTel = profTel;
		this.profEmail = profEmail;
		this.profInputDate = profInputDate;
	}
	
	public int getProfNum() {
		return profNum;
	}
	
	public void setProfNum(int profNum) {
		this.profNum = profNum;
	}
	
	public ImageIcon getProfImg() {
		return profImg;
	}
	
	public void setProfImg(ImageIcon profImg) {
		this.profImg = profImg;
	}
	
	public String getProfName() {
		return profName;
	}
	
	public void setProfName(String profName) {
		this.profName = profName;
	}
	
	public String getProfPass() {
		return profPass;
	}
	
	public void setProfPass(String profPass) {
		this.profPass = profPass;
	}
	
	public String getProfTel() {
		return profTel;
	}
	
	public void setProfTel(String profTel) {
		this.profTel = profTel;
	}
	
	public String getProfEmail() {
		return profEmail;
	}
	
	public void setProfEmail(String profEmail) {
		this.profEmail = profEmail;
	}
	
	public Date getProfInputDate() {
		return profInputDate;
	}
	
	public void setProfInputDate(Date profInputDate) {
		this.profInputDate = profInputDate;
	}
	
}//class 
