package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.AdminRegStuMgrDesign;
import kr.co.sist.admin.view.AdminCourseMgrDesign;
import kr.co.sist.admin.view.AdminInfoDesign;
import kr.co.sist.admin.view.AdminProfMgrDesign;
import kr.co.sist.admin.view.AdminScoreMgrDesign;
import kr.co.sist.admin.view.AdminStuMgrDesign;
import kr.co.sist.admin.view.AdminSubjectMgrDesign;

public class AdminInfoDesignEvt extends WindowAdapter implements ActionListener {

	private AdminInfoDesign aid;
	
	public AdminInfoDesignEvt(AdminInfoDesign aid) {
		this.aid = aid;
	}//AdminInfoDesignEvt
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == aid.getJbtnCourseMgr()) {
			new AdminCourseMgrDesign(aid,true);
		}//end if
		if(e.getSource() == aid.getJbtnScoreMgr()) {
			new AdminScoreMgrDesign(aid,true);
		}//end if
		if(e.getSource() == aid.getJbtnSubjectMgr()) {
			new AdminSubjectMgrDesign(aid,true);
		}//end if
		if(e.getSource() == aid.getJbtnAllStuMgr()) {
			new AdminRegStuMgrDesign(aid,true);
		}//end if
		
		if(e.getSource() == aid.getJbtnStuMgr()) {
			new AdminStuMgrDesign(aid,true);
		}//end if
		
		if(e.getSource() == aid.getJbtnProfMgr()) {
			new AdminProfMgrDesign(aid,true);
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		aid.dispose();
	}//windowClosing

}//class
