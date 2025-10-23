package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.admin.service.StuModifyService;
import kr.co.sist.admin.view.StuModifyDialog;

public class StuModifyDialogEvt extends WindowAdapter implements ActionListener{
	private StuModifyDialog smd;
	private StuModifyService smfs;

	
	public StuModifyDialogEvt(StuModifyDialog smd) {
		this.smd=smd;
		smfs=new StuModifyService();
	
	}//StuAddDialogEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == smd.getJbtnAdd()) {
			ModifyProcess();
		}//end if 
		if(ae.getSource() == smd.getJbtnClose()) {
			closeProcess();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		smd.dispose();
	}//windowClosing
	
	public void ModifyProcess() {
		//1.사용자가 변경한 값을 얻고 
		StudentDTO sDTO = new StudentDTO();
		
		sDTO.setStuNum(Integer.parseInt(smd.getJtfStuNum().getText().trim()));
		String StuName=smd.getJtfStuName().getText().trim();
		String StuPass=smd.getJtfStuPass().getText().trim();
		String StuTel=smd.getJtfStuTel().getText().trim();
		
		
		String msg="이름,비밀번호,전화번호를 모두 입력해주세요!";
		if(StuName==null||StuName.isEmpty()||StuTel==null||StuTel.isEmpty()
				||StuPass==null||StuPass.isEmpty()) {
			JOptionPane.showMessageDialog(smd, msg);
			return;
		}//end if 
		
		sDTO.setStuName(StuName);
		sDTO.setStuPass(StuPass);
		sDTO.setStuTel(StuTel);
			
		boolean flag = smfs.modifyStudent(sDTO)==1;
		
		
		if(flag) {
			msg=sDTO.getStuName()+"의 학생 정보가 수정되었습니다.";
		}//end if
//		
//		switch(flag) {
//		case 1: msg=sDTO.getStuNum()+"번 학생의 정보를 ";
//		}//end switch
		
		JOptionPane.showMessageDialog(smd, msg);
	
		smd.dispose();
		
	}//ModifyProcess

	
	public void closeProcess() {
		smd.dispose();
	}//closeProcess
	
}//class
