package kr.co.sist.login.dto;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

public class LoginProfDTO {
	private int profNum;
	private FileInputStream profImg;
	private File file;
	private String profName;
	private String profPass;
	private String profNewPass;
	private String profTel;
	private String profEmail;
	private Date profInputDate;
	private int courseNum;
	private String courseName;
	private String profDelFlag;
	private String ext;
	private Date courseEndDate;
	private Date courseStrDate;
	
	public LoginProfDTO() {
		
	}

	

	public LoginProfDTO(int profNum, FileInputStream profImg, File file, String profName, String profPass,
			String profNewPass, String profTel, String profEmail, Date profInputDate, int courseNum, String courseName,
			String profDelFlag, String ext, Date courseEndDate, Date courseStrDate) {
		super();
		this.profNum = profNum;
		this.profImg = profImg;
		this.file = file;
		this.profName = profName;
		this.profPass = profPass;
		this.profNewPass = profNewPass;
		this.profTel = profTel;
		this.profEmail = profEmail;
		this.profInputDate = profInputDate;
		this.courseNum = courseNum;
		this.courseName = courseName;
		this.profDelFlag = profDelFlag;
		this.ext = ext;
		this.courseEndDate = courseEndDate;
		this.courseStrDate = courseStrDate;
	}






	public int getCourseNum() {
		return courseNum;
	}






	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}






	public String getCourseName() {
		return courseName;
	}






	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}






	public Date getCourseEndDate() {
		return courseEndDate;
	}






	public void setCourseEndDate(Date courseEndDate) {
		this.courseEndDate = courseEndDate;
	}






	public Date getCourseStrDate() {
		return courseStrDate;
	}






	public void setCourseStrDate(Date courseStrDate) {
		this.courseStrDate = courseStrDate;
	}






	@Override
	public String toString() {
		return "LoginProfDTO [profNum=" + profNum + ", profImg=" + profImg + ", file=" + file + ", profName=" + profName
				+ ", profPass=" + profPass + ", profNewPass=" + profNewPass + ", profTel=" + profTel + ", profEmail="
				+ profEmail + ", profInputDate=" + profInputDate + ", courseNum=" + courseNum + ", courseName="
				+ courseName + ", profDelFlag=" + profDelFlag + ", ext=" + ext + ", courseEndDate=" + courseEndDate
				+ ", courseStrDate=" + courseStrDate + "]";
	}






	public String getExt() {
		return ext;
	}



	public void setExt(String ext) {
		this.ext = ext;
	}



	public int getProfNum() {
		return profNum;
	}

	public void setProfNum(int profNum) {
		this.profNum = profNum;
	}

	public FileInputStream getProfImg() {
		return profImg;
	}

	public void setProfImg(FileInputStream profImg) {
		this.profImg = profImg;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

	public String getProfNewPass() {
		return profNewPass;
	}

	public void setProfNewPass(String profNewPass) {
		this.profNewPass = profNewPass;
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

	public String getProfDelFlag() {
		return profDelFlag;
	}

	public void setProfDelFlag(String profDelFlag) {
		this.profDelFlag = profDelFlag;
	}

	
}
