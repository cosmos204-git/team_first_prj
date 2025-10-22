package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.prof.view.ProfPwMdfDialog;

public class ProfPwMdfDialogEvt extends WindowAdapter implements ActionListener{
	
	private ProfPwMdfDialog ppmd;
	
	public ProfPwMdfDialogEvt(ProfPwMdfDialog ppmd) {
		this.ppmd = ppmd;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ppmd.getJbtnClose()) {
			ppmd.dispose();
			
					
		}
		
		if(e.getSource()==ppmd.getJbtnModify()) {
			 modifyProcess();
		}
		
		
		
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ppmd.dispose();
	}
	
	public void modifyProcess() {
		JOptionPane.showMessageDialog(ppmd, "비밀번호 변경 기능 구현 필요");
	}
	

}
