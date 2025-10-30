package kr.co.sist.admin.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import kr.co.sist.admin.view.AdminCourseMgrDesign;
import kr.co.sist.admin.view.AdminInfoDesign;
import kr.co.sist.admin.view.AdminProfMgrDesign;
import kr.co.sist.admin.view.AdminRegStuMgrDesign;
import kr.co.sist.admin.view.AdminScoreMgrDesign;
import kr.co.sist.admin.view.AdminStuMgrDesign;
import kr.co.sist.admin.view.AdminSubjectMgrDesign;

public class AdminInfoDesignEvt extends WindowAdapter implements ActionListener, MouseListener {

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
	
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton btn = (JButton) e.getSource();
		btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		btn.setBorderPainted(true);
	}//
	
	@Override
	public void mouseExited(MouseEvent e) {
		JButton btn = (JButton) e.getSource();
		btn.setBorderPainted(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}//class
