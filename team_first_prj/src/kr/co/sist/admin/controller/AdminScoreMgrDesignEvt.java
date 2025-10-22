package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.AdminScoreMgrDesign;

public class AdminScoreMgrDesignEvt extends WindowAdapter implements ActionListener{
	private  AdminScoreMgrDesign asmd;
//	private AdminScoreMgrService asms; 
	
	public AdminScoreMgrDesignEvt(AdminScoreMgrDesign asmd){
		this.asmd=asmd;
	}//AdminScoreMgrDesignEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()== asmd.getJbtnSearchStuNum()) {
			System.out.println("성적관리 - 검색");
			searchScoreProcess();
		}//end if 
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		asmd.dispose();
	}//windowClosing
	
	
	public void searchScoreProcess() {}//searchScoreProcess

}//class
