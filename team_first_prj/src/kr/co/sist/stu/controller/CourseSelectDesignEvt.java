package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import kr.co.sist.stu.service.CourseSelectService;
import kr.co.sist.stu.view.CourseSelectDesign;
import kr.co.sist.stu.view.ShowSubjectDialog;

public class CourseSelectDesignEvt extends WindowAdapter implements ActionListener {
	
	private CourseSelectDesign csd;
	private CourseSelectService css;
	
	public CourseSelectDesignEvt(CourseSelectDesign csd) {
		this.csd = csd;
		css=new CourseSelectService();
	}

	
//	/**
//	 * 선택된 과정 내 과목을 보여주는 method
//	 */
//	public void showSubProcess() {
//		
//	}//showSubProcess
//	
//	
//	
//	/**
//	 * 신청 선택 시 호출되는 method
//	 */
//	public void applyProcess() {
//		
//	}//applyProcess
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==csd.getJbtnApplyCourse()) {
			applyProcess();
		}
		if(e.getSource()==csd.getJbtnShowSub()){
			showSubProcess();
		}
		if(e.getSource()==csd.getJbtnClose()) {
			csd.dispose();
		}
	
	}
	public void showSubProcess() {
		new ShowSubjectDialog(csd, true);
	}
	
	public void applyProcess() {
		JOptionPane.showMessageDialog(csd, "과목 신청 구현 필요");
	}
	
	

}
