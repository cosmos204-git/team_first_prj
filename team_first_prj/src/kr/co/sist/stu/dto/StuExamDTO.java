package kr.co.sist.stu.dto;

import java.sql.Date;

public class StuExamDTO {
	private int examCode, testCode, examCorrectTchoice ; 
	private String examQuest1,examQuest2,examQuest3,examQuest4;
	private Date examInputDate;
	
	public StuExamDTO() {
		super();
	}

	public StuExamDTO(int examCode, int testCode, int examCorrectTchoice, String examQuest1, String examQuest2,
			String examQuest3, String examQuest4, Date examInputDate) {
		super();
		this.examCode = examCode;
		this.testCode = testCode;
		this.examCorrectTchoice = examCorrectTchoice;
		this.examQuest1 = examQuest1;
		this.examQuest2 = examQuest2;
		this.examQuest3 = examQuest3;
		this.examQuest4 = examQuest4;
		this.examInputDate = examInputDate;
	}

	public int getExamCode() {
		return examCode;
	}

	public int getTestCode() {
		return testCode;
	}

	public int getExamCorrectTchoice() {
		return examCorrectTchoice;
	}

	public String getExamQuest1() {
		return examQuest1;
	}

	public String getExamQuest2() {
		return examQuest2;
	}

	public String getExamQuest3() {
		return examQuest3;
	}

	public String getExamQuest4() {
		return examQuest4;
	}

	public Date getExamInputDate() {
		return examInputDate;
	}

	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}

	public void setTestCode(int testCode) {
		this.testCode = testCode;
	}

	public void setExamCorrectTchoice(int examCorrectTchoice) {
		this.examCorrectTchoice = examCorrectTchoice;
	}

	public void setExamQuest1(String examQuest1) {
		this.examQuest1 = examQuest1;
	}

	public void setExamQuest2(String examQuest2) {
		this.examQuest2 = examQuest2;
	}

	public void setExamQuest3(String examQuest3) {
		this.examQuest3 = examQuest3;
	}

	public void setExamQuest4(String examQuest4) {
		this.examQuest4 = examQuest4;
	}

	public void setExamInputDate(Date examInputDate) {
		this.examInputDate = examInputDate;
	}

	@Override
	public String toString() {
		return "StuExamDTO [examCode=" + examCode + ", testCode=" + testCode + ", examCorrectTchoice="
				+ examCorrectTchoice + ", examQuest1=" + examQuest1 + ", examQuest2=" + examQuest2 + ", examQuest3="
				+ examQuest3 + ", examQuest4=" + examQuest4 + ", examInputDate=" + examInputDate + "]";
	}
	
}
