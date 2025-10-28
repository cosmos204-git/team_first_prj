package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.stu.view.ShowSubjectDialog;

public class ShowSubjectDialogEvt extends WindowAdapter implements ActionListener{
	
	private ShowSubjectDialog ssd;
	
	public ShowSubjectDialogEvt(ShowSubjectDialog ssd){
		this.ssd = ssd;
	}

	
	@Override
	public void windowClosing(WindowEvent e) {
		ssd.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ssd.getJbtnClose()) {
			ssd.dispose();
		}
	}
	
	
	
}
