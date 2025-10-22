package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.StuModifyDialog;

public class StuModifyDialogEvt extends WindowAdapter implements ActionListener{
	private StuModifyDialog smd;
	
	public StuModifyDialogEvt(StuModifyDialog smd) {
		this.smd=smd;
	}//StuAddDialogEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == smd.getJbtnAdd()) {
			System.out.println("수정완료 메소드");
			ModifyProcess();
		}//end if 
		if(ae.getSource() == smd.getJbtnClose()) {
			closeProcess();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		smd.dispose();
	}//windowClosing
	
	public void ModifyProcess() {
		
	}//ModifyProcess

	
	public void closeProcess() {
		smd.dispose();
	}//closeProcess
	
}//class
