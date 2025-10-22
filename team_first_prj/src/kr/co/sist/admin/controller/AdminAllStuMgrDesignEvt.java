package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.AdminAllStuMgrDesign;

public class AdminAllStuMgrDesignEvt extends WindowAdapter implements ActionListener {
	private  AdminAllStuMgrDesign aamd;
//	private AdminAllStuMgrServic asms;
	public AdminAllStuMgrDesignEvt (AdminAllStuMgrDesign aamd) {
		this.aamd=aamd;
	}//AdminAllStuMgrDesignEvt
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == aamd.getJbtnSearchStuNum()) {
				System.out.println("입과관리 - 검색");
		}//end if 
	}//actionPerformed


	@Override
	public void windowClosing(WindowEvent e) {
		aamd.dispose();
	}//windowClosing


	public void searchAllCourseProcess() {}//searchAllCourseProcess
	public void searchCourseProcess() {}//searchCourseProcess
	public void searchProcess() {}//searchProcess
}//class
