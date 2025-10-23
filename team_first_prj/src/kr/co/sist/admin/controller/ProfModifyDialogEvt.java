package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.admin.dto.ProfDTO;
import kr.co.sist.admin.service.ProfModifyService;
import kr.co.sist.admin.view.ProfModifyDialog;

public class ProfModifyDialogEvt extends WindowAdapter implements ActionListener{
	private ProfModifyDialog pmd ;
	private ProfModifyService pms;
	
	public ProfModifyDialogEvt(ProfModifyDialog pmd) {
		this.pmd=pmd;
		pms= new ProfModifyService();
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
		//1.사용자가 변경한 값을 얻고 
		ProfDTO pDTO = new ProfDTO();
		
		pDTO.setProfNum(Integer.parseInt(pmd.getJtfProfNum().getText().trim()));
		String profName=pmd.getJtfProfName().getText().trim();
		String profPass=pmd.getJtfProfPass().getText().trim();
		String profTel=pmd.getJtfProfTel().getText().trim();
		
		
		String msg="이름,비밀번호,전화번호를 모두 입력해주세요!";
		if(profName==null||profName.isEmpty()||profTel==null||profTel.isEmpty()
				||profPass==null||profPass.isEmpty()) {
			JOptionPane.showMessageDialog(pmd, msg);
			return;
		}//end if 
		
		pDTO.setProfName(profName);
		pDTO.setProfPass(profPass);
		pDTO.setProfTel(profTel);
			
		boolean flag = pms.modifyProfessor(pDTO)==1;
		
		
		if(flag) {
			msg=pDTO.getProfName()+"의 교수 정보가 수정되었습니다.";
		}//end if
//		
//		switch(flag) {
//		case 1: msg=sDTO.getStuNum()+"번 학생의 정보를 ";
//		}//end switch
		
		JOptionPane.showMessageDialog(pmd, msg);
	
		pmd.dispose();
		
	}//ModifyProcess

	
	public void closeProcess() {
		pmd.dispose();
	}//closeProcess
	
}//class
