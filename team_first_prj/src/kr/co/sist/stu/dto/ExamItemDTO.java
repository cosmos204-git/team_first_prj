package kr.co.sist.stu.dto;

import java.sql.Date;

public class ExamItemDTO {
    private int examCode;
    private int testCode;
    private String examQuest;
    private String examChoice1;
    private String examChoice2;
    private String examChoice3;
    private String examChoice4;
    private int examCorrectTchoice;
    private String delFlag;
    private Date inputDate;

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
    public String getExamChoice4() { 
    	return examChoice4; 
    	
    }
    public void setExamChoice4(String examChoice4) { 
    	this.examChoice4 = examChoice4; 
    	
    }
    public int getExamCorrectTchoice() {
    	return examCorrectTchoice; 
    	
    }
    public void setExamCorrectTchoice(int examCorrectTchoice) {
    	this.examCorrectTchoice = examCorrectTchoice;

    }
    public String getDelFlag() { 
    	return delFlag;
    	
    }
    public void setDelFlag(String delFlag) {
    	this.delFlag = delFlag; 
    	
    }
    public Date getInputDate() {
    	return inputDate; 
    	
    }
    public void setInputDate(Date inputDate) {
    	this.inputDate = inputDate; 
    	
    }
}
