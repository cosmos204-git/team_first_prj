package kr.co.sist.login.dto;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

public class LoginStudentDTO {
	private int stuNum;
	private FileInputStream stuImg;
	private File file;
	private String stuName;
	private String stuPass;
	private String stuNewPass;
	private String stuTel;
	private String stuEmail;
	private Date stuInputDate;
	private String stuAddr1;
	private String stuAddr2;
	private int stuCourseNum;
	private String stuCourseName;
	private String stuDelFlag;
	private String ext;
	
	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public LoginStudentDTO() {
		
	}

	public int getStuNum() {
		return stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public FileInputStream getStuImg() {
		return stuImg;
	}

	public void setStuImg(FileInputStream stuImg) {
		this.stuImg = stuImg;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuPass() {
		return stuPass;
	}

	public void setStuPass(String stuPass) {
		this.stuPass = stuPass;
	}

	public String getStuNewPass() {
		return stuNewPass;
	}

	public void setStuNewPass(String stuNewPass) {
		this.stuNewPass = stuNewPass;
	}

	public String getStuTel() {
		return stuTel;
	}

	public void setStuTel(String stuTel) {
		this.stuTel = stuTel;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public Date getStuInputDate() {
		return stuInputDate;
	}

	public void setStuInputDate(Date stuInputDate) {
		this.stuInputDate = stuInputDate;
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

	public int getStuCourseNum() {
		return stuCourseNum;
	}

	public void setStuCourseNum(int stuCourseNum) {
		this.stuCourseNum = stuCourseNum;
	}

	public String getStuCourseName() {
		return stuCourseName;
	}

	public void setStuCourseName(String stuCourseName) {
		this.stuCourseName = stuCourseName;
	}

	public String getStuDelFlag() {
		return stuDelFlag;
	}

	public void setStuDelFlag(String string) {
		this.stuDelFlag = string;
	}

	@Override
	public String toString() {
		return "LoginStudentDTO [stuNum=" + stuNum + ", stuImg=" + stuImg + ", file=" + file + ", stuName=" + stuName
				+ ", stuPass=" + stuPass + ", stuNewPass=" + stuNewPass + ", stuTel=" + stuTel + ", stuEmail="
				+ stuEmail + ", stuInputDate=" + stuInputDate + ", stuAddr1=" + stuAddr1 + ", stuAddr2=" + stuAddr2
				+ ", stuCourseNum=" + stuCourseNum + ", stuCourseName=" + stuCourseName + ", stuDelFlag=" + stuDelFlag
				+ ", ext=" + ext + "]";
	}

	public LoginStudentDTO(int stuNum, FileInputStream stuImg, File file, String stuName, String stuPass,
			String stuNewPass, String stuTel, String stuEmail, Date stuInputDate, String stuAddr1, String stuAddr2,
			int stuCourseNum, String stuCourseName, String stuDelFlag, String ext) {
		super();
		this.stuNum = stuNum;
		this.stuImg = stuImg;
		this.file = file;
		this.stuName = stuName;
		this.stuPass = stuPass;
		this.stuNewPass = stuNewPass;
		this.stuTel = stuTel;
		this.stuEmail = stuEmail;
		this.stuInputDate = stuInputDate;
		this.stuAddr1 = stuAddr1;
		this.stuAddr2 = stuAddr2;
		this.stuCourseNum = stuCourseNum;
		this.stuCourseName = stuCourseName;
		this.stuDelFlag = stuDelFlag;
		this.ext = ext;
	}

	

}
