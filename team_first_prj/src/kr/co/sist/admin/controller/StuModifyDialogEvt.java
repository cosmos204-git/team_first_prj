package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

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
		String stuName=smd.getJtfStuName().getText().trim();
		String stuPass=String.valueOf(smd.getJpfStuPass().getPassword()).trim();
		String stuTel=smd.getJtfStuTel().getText().trim();
		
		String telRegex = "^010-\\d{4}-\\d{4}$";
		
		String msg="이름,비밀번호,전화번호를 모두 입력해주세요!";
		if(stuName==null||stuName.isEmpty()||stuTel==null||stuTel.isEmpty()
				||stuPass==null||stuPass.isEmpty()) {
			JOptionPane.showMessageDialog(smd, msg);
			return;
		}else if(stuName.length()<2||stuName.length()>5) {
			System.out.println();
			msg="이름을 2~5자 사이로 입력해주세요.";
			JOptionPane.showMessageDialog(smd, msg);
			return ;
		}else if(!Pattern.matches(telRegex, stuTel)) {
			JOptionPane.showMessageDialog(smd, "전화번호 형식이 올바르지 않습니다.\n 010-xxxx-xxxx");
			return ;
		}else if(stuPass.length()<4||stuPass.length()>20) {
			JOptionPane.showMessageDialog(smd, "비밀번호 4~20자 사이로 설정해주세요." );
			return;
		}//end if 
		
		sDTO.setStuName(stuName);
		sDTO.setStuPass(stuPass);
		sDTO.setStuTel(stuTel);
		
		
			
		boolean flag = smfs.modifyStudent(sDTO)==1;
		
		
		if(flag) {
			msg=sDTO.getStuName()+"의 학생 정보가 수정되었습니다.";
		}//end if

		
		JOptionPane.showMessageDialog(smd, msg);
	
		smd.dispose();
		
	}//ModifyProcess

	
	public void closeProcess() {
		smd.dispose();
	}//closeProcess
	
}//class
