package kr.co.sist.admin.dto;

public class examItemDTO {

	private int examCode, testCode, examCorrectTChoice;
	private String examChoice1, examChoice2, examChoice3, examChoice4, examInputDate, examQuest;
	
	
	
	
	public examItemDTO() {
		super();
	}




	public examItemDTO(int examCode, int testCode, int examCorrectTChoice, String examChoice1, String examChoice2,
			String examChoice3, String examChoice4, String examInputDate, String examQuest) {
		super();
		this.examCode = examCode;
		this.testCode = testCode;
		this.examCorrectTChoice = examCorrectTChoice;
		this.examChoice1 = examChoice1;
		this.examChoice2 = examChoice2;
		this.examChoice3 = examChoice3;
		this.examChoice4 = examChoice4;
		this.examInputDate = examInputDate;
		this.examQuest = examQuest;
	}




	public int getExamCode() {
		return examCode;
	}




	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}




	public int getTestCode() {
		return testCode;
	}




	public void setTestCode(int testCode) {
		this.testCode = testCode;
	}




	public int getExamCorrectTChoice() {
		return examCorrectTChoice;
	}




	public void setExamCorrectTChoice(int examCorrectTChoice) {
		this.examCorrectTChoice = examCorrectTChoice;
	}




	public String getExamChoice1() {
		return examChoice1;
	}




	public void setExamChoice1(String examChoice1) {
		this.examChoice1 = examChoice1;
	}




	public String getExamChoice2() {
		return examChoice2;
	}




	public void setExamChoice2(String examChoice2) {
		this.examChoice2 = examChoice2;
	}




	public String getExamChoice3() {
		return examChoice3;
	}




	public void setExamChoice3(String examChoice3) {
		this.examChoice3 = examChoice3;
	}




	public String getExamChoice4() {
		return examChoice4;
	}




	public void setExamChoice4(String examChoice4) {
		this.examChoice4 = examChoice4;
	}




	public String getExamInputDate() {
		return examInputDate;
	}




	public void setExamInputDate(String examInputDate) {
		this.examInputDate = examInputDate;
	}




	public String getExamQuest() {
		return examQuest;
	}




	public void setExamQuest(String examQuest) {
		this.examQuest = examQuest;
	}




	@Override
	public String toString() {
		return "examItemDTO [examCode=" + examCode + ", testCode=" + testCode + ", examCorrectTChoice="
				+ examCorrectTChoice + ", examChoice1=" + examChoice1 + ", examChoice2=" + examChoice2
				+ ", examChoice3=" + examChoice3 + ", examChoice4=" + examChoice4 + ", examInputDate=" + examInputDate
				+ ", examQuest=" + examQuest + "]";
	}
	
	
	
}
