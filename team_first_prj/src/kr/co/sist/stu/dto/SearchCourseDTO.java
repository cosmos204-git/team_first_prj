package kr.co.sist.stu.dto;

import java.sql.Date;

public class SearchCourseDTO {

   private int stuNum;
   private int courseCode;
   private String courseName;
   private Date courseStartDate;
   private Date courseEndDate;
   private Date courseInputDate;
   private boolean courseFlag;
   private boolean checkCourse=false;
   
   
   public SearchCourseDTO() {
      super();
   }
   public int getStuNum() {
      return stuNum;
   }
   public void setStuNum(int stuNum) {
      this.stuNum = stuNum;
   }
   public int getCourseCode() {
      return courseCode;
   }
   public void setCourseCode(int courseCode) {
      this.courseCode = courseCode;
   }
   public String getCourseName() {
      return courseName;
   }
   public void setCourseName(String courseName) {
      this.courseName = courseName;
   }
   public Date getCourseStartDate() {
      return courseStartDate;
   }
   public void setCourseStartDate(Date courseStartDate) {
      this.courseStartDate = courseStartDate;
   }
   public Date getCourseEndDate() {
      return courseEndDate;
   }
   public void setCourseEndDate(Date courseEndDate) {
      this.courseEndDate = courseEndDate;
   }
   public Date getCourseInputDate() {
      return courseInputDate;
   }
   public void setCourseInputDate(Date courseInputDate) {
      this.courseInputDate = courseInputDate;
   }
   public boolean isCourseFlag() {
      return courseFlag;
   }
   public void setCourseFlag(boolean courseFlag) {
      this.courseFlag = courseFlag;
   }
   public boolean isCheckCourse() {
      return checkCourse;
   }
   public void setCheckCourse(boolean checkCourse) {
      this.checkCourse = checkCourse;
   }
   public SearchCourseDTO(int stuNum, int courseCode, String courseName, Date courseStartDate, Date courseEndDate,
         Date courseInputDate, boolean courseFlag, boolean checkCourse) {
      super();
      this.stuNum = stuNum;
      this.courseCode = courseCode;
      this.courseName = courseName;
      this.courseStartDate = courseStartDate;
      this.courseEndDate = courseEndDate;
      this.courseInputDate = courseInputDate;
      this.courseFlag = courseFlag;
      this.checkCourse = checkCourse;
   }

   
   
   
}//class