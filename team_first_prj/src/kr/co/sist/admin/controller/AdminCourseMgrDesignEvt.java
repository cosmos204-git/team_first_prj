package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.AdminCourseMgrDesign;

public class AdminCourseMgrDesignEvt extends WindowAdapter implements ActionListener{
	
	private AdminCourseMgrDesign acmd;
//	private AdminCourseMgrDesignService acmds;
	
	public AdminCourseMgrDesignEvt(AdminCourseMgrDesign acmd) {
		this.acmd=acmd;
	}//AdminCourseMgrDesignEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == acmd.getJbtnSearch()) {
			System.out.println("과정 관리 - 검색");
			searchProcess();
		}//end if 
		if(ae.getSource() == acmd.getJbtnModify()) {
			System.out.println("과정 관리 - 수정");
			modifyProcess();
		}//end if 
		if(ae.getSource() == acmd.getJbtnAdd()) {
			System.out.println("과정 관리 - 과정추가");
			addProcess();
		}//end if 
		if(ae.getSource() == acmd.getJbtnDelete()) {
			System.out.println("과정 관리 - 삭제");
			deleteProcess();
		}//end if 
		if(ae.getSource() == acmd.getJbtnClose()) {
			System.out.println("과정 관리 - 닫기 ");
			closeProcess();
		}//end if 
	}//actionPerformed
	@Override
	public void windowClosing(WindowEvent we) {
		acmd.dispose();
	}//windowClosing
	
	public void searchAllProcess() {
		
	}//searchAllProcess
	
	public void searchProcess() {
		
	}//searchProcess
	
	public void addProcess() {
		
	}//addProcess
	
	public void modifyProcess(){
		
	}//modifyProcess
	
	public void deleteProcess(){
		
	}//deleteProcess
	
	public void closeProcess() {
		acmd.dispose();
	}//closeProcess
	
}//class
