package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.stu.view.StuCourseMgrDesign;
import kr.co.sist.stu.view.StuExamDesign;
import kr.co.sist.stu.view.StuReportDesign;

public class StuCourseMgrDesignEvt extends WindowAdapter implements ActionListener{
	private StuCourseMgrDesign scmd;
	
	public StuCourseMgrDesignEvt(StuCourseMgrDesign scmd) {
		this.scmd = scmd;
		viewExamProcess();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==scmd.getJbtnShowStuReport()) {
			showReportProcess();
			
		}
		if(e.getSource()==scmd.getJbtnShowExam()) {
			showExamProcess();
		}
		if(e.getSource()==scmd.getJbtnclose()) {
			scmd.dispose();
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		scmd.dispose();
	}
	//현재 테이블에 데이터 띄우기
	public void viewExamProcess() {

	}
	
	//시험 클릭 했을 때
	public void showExamProcess() {
		new StuExamDesign(scmd,true);		
	}
	
	//성적표 클릭 했을 때 
	public void showReportProcess() {
		new StuReportDesign(scmd,true);
	}

	
	
	
	
	
}
