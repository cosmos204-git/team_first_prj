package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.ProfAddDialog;

public class ProfAddDialogEvt extends WindowAdapter implements ActionListener{
	private ProfAddDialog pad;
	
	public ProfAddDialogEvt(ProfAddDialog pad) {
		this.pad=pad;
	}//StuAddDialogEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pad.getJbtnAdd()) {
			System.out.println("교수관리 - 추가");
			addProcess();
		}//end if 
		if(ae.getSource() == pad.getJbtnClose()) {
			closeProcess();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		pad.dispose();
	}//windowClosing
	
	public void addProcess() {
		
	}//addProcess
	
	public void closeProcess() {
		pad.dispose();
	}//closeProcess
	
}//class
