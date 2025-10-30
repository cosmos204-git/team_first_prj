package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.regex.Pattern;

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
	
	public boolean duplicateName(String profName) {
		boolean profFlag=false;
		List<ProfDTO> list = pms.searchAllProfessor();
		for(ProfDTO pDTO:list) {
			String name= pDTO.getProfName();
			if(profName.equals(name)) {
				profFlag=true;
				break;
			}
		}
		
		return profFlag;
	}//duplicateName
	
	public void ModifyProcess() {
		//1.사용자가 변경한 값을 얻고 
		ProfDTO pDTO = new ProfDTO();
		
		pDTO.setProfNum(Integer.parseInt(pmd.getJtfProfNum().getText().trim()));
		String profName=pmd.getJtfProfName().getText().trim();
		String profPass=String.valueOf(pmd.getJpfProfPass().getPassword()).trim();
		String profTel=pmd.getJtfProfTel().getText().trim();
		
		String telRegex = "(^010-\\d{4}-\\d{4}$)";
		
		String msg="이름,비밀번호,전화번호를 모두 입력해주세요!";
		if(profName==null||profName.isEmpty()||profTel==null||profTel.isEmpty()
				||profPass==null||profPass.isEmpty()) {
			JOptionPane.showMessageDialog(pmd, msg);
			return;
		}else if(profName.length()<2||profName.length()>5) {
			msg="이름을 2~5자 사이로 입력해주세요.";
			JOptionPane.showMessageDialog(pmd, msg);
			return ;
		}else if(!Pattern.matches(telRegex, profTel)) {
			msg="전화번호 형식이 올바르지 않습니다.\n ex)010-xxxx-xxxx";
			JOptionPane.showMessageDialog(pmd, msg);
			return ;
		}else if(profPass.length()<4||profPass.length()>20) {
			msg="비밀번호 4~20자 사이로 설정해주세요.";
			JOptionPane.showMessageDialog(pmd, msg);
			return;
		}//end if 
		
		
		if(duplicateName(profName)) {
			profName=profName+"("+Integer.parseInt(pmd.getJtfProfNum().getText())+")";
		}
		
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
