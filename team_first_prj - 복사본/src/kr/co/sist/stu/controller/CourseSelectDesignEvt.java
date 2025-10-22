package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import kr.co.sist.stu.view.CourseSelectDesign;
import kr.co.sist.stu.view.ShowSubjectDialog;

public class CourseSelectDesignEvt extends WindowAdapter implements ActionListener {
	
	private CourseSelectDesign csd;
	
	public CourseSelectDesignEvt(CourseSelectDesign csd) {
		this.csd = csd;
	}

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
