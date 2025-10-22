package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.AdminSubjectMgrDesign;
import kr.co.sist.admin.view.TestExamMgrDesign;

public class AdminSubjectMgrDesignEvt extends WindowAdapter implements ActionListener,MouseListener,ItemListener {
	private AdminSubjectMgrDesign asmd;
	
	public AdminSubjectMgrDesignEvt(AdminSubjectMgrDesign asmd) {
		this.asmd=asmd;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == asmd.getJbtnMgrTestExam()) {
			new TestExamMgrDesign(asmd,false);
		}//end if 
		if(e.getSource() == asmd.getJbtnAdd()) {
			System.out.println("추가버튼");
			addProcess();
		}//end if 
		if(e.getSource() == asmd.getJbtnDelete()) {
			System.out.println("삭제버튼");
			deleteProcess();
		}//end if 
		if(e.getSource() == asmd.getJbtnClose()) {
			System.out.println("닫기");
			closeProcess();
		}//end if 
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		asmd.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// 사용할 메소드 
	}//mouseClicked
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		//사용할 메소드 
	}//itemStateChanged
	
	public void searchSubProcess() {
		
	}//searchSubProcess
	public void addProcess() {
		
	}//addProcess
	public void deleteProcess() {
		
	} //deleteProcess
	public void mgrTestExamProcess() {
		
	}//mgrTestExamProcess
	public void closeProcess() {
		asmd.dispose();
	}//closeProcess
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
