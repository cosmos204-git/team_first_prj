package kr.co.sist.prof.dto;

public class ShowStuReportInfoDTO {

	private int testCode;
	private int examCode;
	private String examQuest;
	private String examChoice1;
	private String examChoice2;
	private String examChoice3;
	private String examChoice4;
	private int examCorrectChoice;
	private int stuAns;
	



	public ShowStuReportInfoDTO() {
		
	}



	public ShowStuReportInfoDTO(int testCode, int examCode, String examQuest, String examChoice1, String examChoice2,
			String examChoice3, String examChoice4, int examCorrectChoice, int stuAns) {
		super();
		this.testCode = testCode;
		this.examCode = examCode;
		this.examQuest = examQuest;
		this.examChoice1 = examChoice1;
		this.examChoice2 = examChoice2;
		this.examChoice3 = examChoice3;
		this.examChoice4 = examChoice4;
		this.examCorrectChoice = examCorrectChoice;
		this.stuAns = stuAns;
	}



	@Override
	public String toString() {
		return "ShowStuReportInfoDTO [testCode=" + testCode + ", examCode=" + examCode + ", examQuest=" + examQuest
				+ ", examChoice1=" + examChoice1 + ", examChoice2=" + examChoice2 + ", examChoice3=" + examChoice3
				+ ", examChoice4=" + examChoice4 + ", examCorrectChoice=" + examCorrectChoice + ", stuAns=" + stuAns
				+ "]";
	}


	public int getTestCode() {
		return testCode;
	}

	public int getExamCode() {
		return examCode;
	}

	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}

	public String getExamQuest() {
		return examQuest;
	}

	public void setExamQuest(String examQuest) {
		this.examQuest = examQuest;
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
	public void setTestCode(int testCode) {
		this.testCode = testCode;
	}

	public String getExamChoice4() {
		return examChoice4;
	}

	public void setExamChoice4(String examChoice4) {
		this.examChoice4 = examChoice4;
	}

	public int getExamCorrectChoice() {
		return examCorrectChoice;
	}

	public void setExamCorrectChoice(int examCorrectChoice) {
		this.examCorrectChoice = examCorrectChoice;
	}

	public int getStuAns() {
		return stuAns;
	}

	public void setStuAns(int stuAns) {
		this.stuAns = stuAns;
	}
	
	
	
}
