package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.ProfModifyDialog;

public class ProfModifyDialogEvt extends WindowAdapter implements ActionListener{
	private ProfModifyDialog pmd ;
	
	public ProfModifyDialogEvt(ProfModifyDialog pmd) {
		this.pmd=pmd;
	}//StuAddDialogEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pmd.getJbtnAdd()) {
			System.out.println("수정완료 메소드");
			ModifyProcess();
		}//end if 
		if(ae.getSource() == pmd.getJbtnClose()) {
			closeProcess();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		pmd.dispose();
	}//windowClosing
	
	public void ModifyProcess() {
		
	}//ModifyProcess

	
	public void closeProcess() {
		pmd.dispose();
	}//closeProcess
	
}//class
