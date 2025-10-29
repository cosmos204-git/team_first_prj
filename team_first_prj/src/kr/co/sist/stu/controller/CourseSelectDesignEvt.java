package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.dto.SearchCourseDTO;
import kr.co.sist.stu.service.CourseSelectService;
import kr.co.sist.stu.view.CourseSelectDesign;
import kr.co.sist.stu.view.ShowSubjectDialog;

public class CourseSelectDesignEvt extends WindowAdapter implements ActionListener, MouseListener {

   private CourseSelectService css;
   private CourseSelectDesign csd;
   private List<SearchCourseDTO> listCourseData;
   private int selectedRow = -1;
   private int setflag;
   
   private int courseCode;
   private String courseName;
   private String courseState;
   private ShowSubjectDialog ssd;
   private ShowSubjectDialogEvt ssde;   
  
   
   public CourseSelectDesignEvt(CourseSelectDesign csd) {
      this.csd = csd;
      this.css = new CourseSelectService();
      showCourseProcess();
   }//CourseSelectDesignEvt

   @Override
   public void actionPerformed(ActionEvent ae) {

      if (ae.getSource() == csd.getJbtnShowSub()) {
         showSubProcess();
      }

      if (ae.getSource() == csd.getJbtnApplyCourse()) {
         applyProcess();
         if(setflag==1) {
        	 csd.getSid().getJtfStuCourseData().setText(courseState);
         }
      }

      if (ae.getSource() == csd.getJbtnClose()) {
         csd.dispose();
      }
   }// actionPerformed

   @Override
   public void mouseClicked(MouseEvent me) {
      if (me.getButton() == MouseEvent.BUTTON1) {
         selectedRow = csd.getJtSelectCourse().getSelectedRow();
      }
   }// mouseClicked

   @Override
   public void windowClosing(WindowEvent we) {
      csd.dispose();
   }// windowClosing

   /**
    * 기간과 학생번호 기반 과정 리스트를 보여주는 method
    */
   public void showCourseProcess() {
      try {
         CurrentStuData crsd = CurrentStuData.getInstance();
         
         int stuNum = crsd.getStuNum();

         
         listCourseData = css.searchCourse(stuNum);
         

         DefaultTableModel dtm = csd.getDtmSelectCourse();
         dtm.setRowCount(0);

         for(int i = 0; i< listCourseData.size();i++) {
            String[] data = {"",""};
            if(!listCourseData.get(i).isCheckCourse()) {
               data[0]="X";
            }else {
               data[0]="O";
            }
         
            
            data[1]=listCourseData.get(i).getCourseName();
            
            dtm.addRow(data);
         }//end for
         
         
//         for (SearchCourseDTO scDTO : listCourseData) {
//            System.out.println(scDTO);
////            row[0] = scDTO.isCourseFlag() ? "수강중" : "종료";
////            row[1] = scDTO.getCourseName();
//         }

      } catch (Exception e) {
         JOptionPane.showMessageDialog(csd, "조회 중 오류가 발생했습니다.");
         e.printStackTrace();
      }
   }// showSubProcess

   
   /**
    * 과정 클릭 후 상세 과목보기
    */
   public void showSubProcess() {
      
      if (selectedRow == -1) {
         JOptionPane.showMessageDialog(csd, "상세 과목 볼 과정을 선택해 주세요.");
         return;
      }//end if
      
      try {
         courseCode = listCourseData.get(selectedRow).getCourseCode();
         courseName = listCourseData.get(selectedRow).getCourseName();

         ssd = new ShowSubjectDialog(csd, true, courseCode, courseName);
         ssde = new ShowSubjectDialogEvt(ssd); //call viewAllSubject()

//         ssd.addWindowListener(ssde);
//         ssd.getJbtnClose().addActionListener(ssde);

         // 과목 리스트 조회
//         ssde.viewAllSubject(courseCode);

      } catch (Exception e) {
         JOptionPane.showMessageDialog(csd, "과목 조회 중 오류가 발생했습니다.");
         e.printStackTrace();
      }
   }// showSubProcess
      
      
   
   
   /**
    * 신청 버튼 클릭 시 호출되는 method
    */
   public void applyProcess() {
      if (selectedRow == -1) {
         JOptionPane.showMessageDialog(csd, "신청할 과정을 선택해주세요.");
         return;
      }//end if   
      
      SearchCourseDTO scDTO = listCourseData.get(selectedRow);

      if (scDTO.isCheckCourse()) {
          JOptionPane.showMessageDialog(csd, "이미 신청한 과정입니다.");
          return;
      }
      
      
      for (SearchCourseDTO scDTO1 : listCourseData ) {
    	  if (scDTO1.isCheckCourse()) {
    		  JOptionPane.showMessageDialog(csd, "이미 신청한 과정이 있습니다.\n과정을 변경하고 싶다면 따로 문의해주세요.");
    		  return;
    	  }
      }
      

      boolean result = css.applyCourse(scDTO);

      if (result) {
          scDTO.setCheckCourse(true);
         
          CurrentStuData crsd = CurrentStuData.getInstance();
          crsd.getLogStuDTO().setStuCourseNum(scDTO.getCourseCode());
          crsd.getLogStuDTO().setStuCourseName(scDTO.getCourseName());
          
          setflag=1;
          courseState= crsd.getLogStuDTO().getStuCourseName();
          csd.getSid().getJtfStuCourseData().setText(courseState);
          
          JOptionPane.showMessageDialog(csd, scDTO.getCourseName() + " 과정을 신청했습니다.");
      } else {
          JOptionPane.showMessageDialog(csd, "신청 중 오류가 발생했습니다.");
      }
      
      
      
   }// applyProcess

   
   @Override
   public void mousePressed(MouseEvent e) { 
      
   }
   
   @Override
   public void mouseReleased(MouseEvent e) {
      
   }
   
   @Override
   public void mouseEntered(MouseEvent e) {
      
   }
   
   @Override
   public void mouseExited(MouseEvent e) {
      
   }

}// class