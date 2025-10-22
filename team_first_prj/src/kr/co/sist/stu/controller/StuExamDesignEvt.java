package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.stu.view.StuExamDesign;

public class StuExamDesignEvt extends WindowAdapter implements ActionListener {
	
	private StuExamDesign sed;
	
	public StuExamDesignEvt(StuExamDesign sed) {
		this.sed = sed;
		searchExItemProcess();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sed.getJbtnSubmit()) {
			submitExamProcess();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		sed.dispose();
	}
	
	public void searchExItemProcess() {
		
	}
	
	public void submitExamProcess() {
		JOptionPane.showMessageDialog(sed, "시험 제출 기능 구현 필요");
	}
	

}
