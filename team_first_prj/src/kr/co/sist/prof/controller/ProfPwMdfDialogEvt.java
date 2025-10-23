package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.prof.service.ProfPwModifySerivce;
import kr.co.sist.prof.view.ProfPwMdfDialog;

public class ProfPwMdfDialogEvt extends WindowAdapter implements ActionListener{
	
	private ProfPwMdfDialog ppmd;
	private ProfPwModifySerivce ppms;
	
	public ProfPwMdfDialogEvt(ProfPwMdfDialog ppmd) {
		this.ppmd = ppmd;
		ppms = new ProfPwModifySerivce();
		
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
		CurrentProfData cpd = CurrentProfData.getInstance();

		if(!cpd.getLogProfDTO().getProfPass().equals(ppmd.getJtfCurrentProfPw().getText())) {
			JOptionPane.showMessageDialog(ppmd, "기존 비밀번호가 틀립니다.");
			return;
		}
		
		if(ppmd.getJtfNewProfPw().getText().length()>20) {
			JOptionPane.showMessageDialog(ppmd, "비밀번호는 20자 내로 입력하세요.");
			return;
		}
		
		if(!ppmd.getJtfNewProfPw().getText().equals(ppmd.getJtfConfirmnProfPw().getText())) {
			JOptionPane.showMessageDialog(ppmd, "새로운 비밀번호가 같지 않습니다.");
			return;
		}
		
		cpd.getLogProfDTO().setProfPass(ppmd.getJtfNewProfPw().getText());
		
		if(ppms.modifyStuInfo(cpd)==1) {
			
			JOptionPane.showMessageDialog(ppmd, "비밀번호를 변경하였습니다.");
		}
		ppmd.dispose();
		
	}
	

}
