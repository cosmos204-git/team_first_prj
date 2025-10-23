package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.service.StuPwModifyService;
import kr.co.sist.stu.view.StuPwMdfDialog;

public class StuPwMdfDialogEvt extends WindowAdapter implements ActionListener{
	
	private StuPwMdfDialog spmd;
	private StuPwModifyService spms;
	
	public StuPwMdfDialogEvt(StuPwMdfDialog spmd) {
		this.spmd = spmd;
		spms = new StuPwModifyService();
		
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
		CurrentStuData csd = CurrentStuData.getInstance();

		if(!csd.getLogStuDTO().getStuPass().equals(spmd.getJtfCurrentStuPw().getText())) {
			JOptionPane.showMessageDialog(spmd, "기존 비밀번호가 틀립니다.");
			return;
		}
		
		if(spmd.getJtfNewStuPw().getText().length()>20) {
			JOptionPane.showMessageDialog(spmd, "비밀번호는 20자 내로 입력하세요.");
			return;
		}
		
		if(!spmd.getJtfNewStuPw().getText().equals(spmd.getJtfConfirmnStuPw().getText())) {
			JOptionPane.showMessageDialog(spmd, "새로운 비밀번호가 같지 않습니다.");
			return;
		}
		
		csd.getLogStuDTO().setStuPass(spmd.getJtfNewStuPw().getText());
		
		if(spms.modifyStuInfo(csd)==1) {
			JOptionPane.showMessageDialog(spmd, "비밀번호를 변경하였습니다.");
		}
		spmd.dispose();
		
		
	}
	

}
