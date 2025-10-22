package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.StuAddDialog;

public class StuAddDialogEvt extends WindowAdapter implements ActionListener{
	private StuAddDialog sad;
	
	public StuAddDialogEvt(StuAddDialog sad) {
		this.sad=sad;
	}//StuAddDialogEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == sad.getJbtnAdd()) {
			System.out.println("추가 메소드");
			addProcess();
		}//end if 
		if(ae.getSource() == sad.getJbtnClose()) {
			closeProcess();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		sad.dispose();
	}//windowClosing
	
	public void addProcess() {
		
	}//addProcess
	
	public void closeProcess() {
		sad.dispose();
	}//closeProcess
	
}//class
