package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.stu.view.StuPwMdfDialog;

public class StuPwMdfDialogEvt extends WindowAdapter implements ActionListener{
	
	private StuPwMdfDialog spmd;
	
	public StuPwMdfDialogEvt(StuPwMdfDialog spmd) {
		this.spmd = spmd;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==spmd.getJbtnClose()) {
			spmd.dispose();
			
					
		}
		
		if(e.getSource()==spmd.getJbtnModify()) {
			 modifyProcess();
		}
		
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		spmd.dispose();
	}
	
	public void modifyProcess() {
		JOptionPane.showMessageDialog(spmd, "비밀번호 변경 기능 구현 필요");
	}
	

}
