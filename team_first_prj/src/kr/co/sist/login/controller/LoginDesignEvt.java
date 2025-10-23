package kr.co.sist.login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.admin.view.AdminInfoDesign;
import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.login.dto.LoginAdminDTO;
import kr.co.sist.login.dto.LoginProfDTO;
import kr.co.sist.login.dto.LoginStudentDTO;
import kr.co.sist.login.service.LoginService;
import kr.co.sist.login.view.LoginDesign;
import kr.co.sist.prof.view.ProfInfoDesign;
import kr.co.sist.stu.view.StuInfoDesign;

public class LoginDesignEvt extends WindowAdapter implements ActionListener{

	private LoginDesign ld;
	private LoginService ls;
	private static final int STUDENT_FLAG = 0;
	private static final int PROF_FLAG = 1;
	private static final int ADMIN_FLAG = 2;
	
	public LoginDesignEvt(LoginDesign ld) {
		this.ld = ld;
		ls = new LoginService();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		ld.dispose();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==ld.getJbtnLogin()) {
			
			switch(ld.getLoginFlag()) {
			case STUDENT_FLAG:
	
				loginStuProcess();
				//로그인 성공시 학생 화면으로 넘어감.
				break;
			case PROF_FLAG:
				loginProfProcess();
				//로그인 성공시 교수 화면으로 넘어감.
				break;
			case ADMIN_FLAG:
				loginAdminProcess();
				//로그인 성공시 관리자 화면으로 넘어감.
				break;
			}
		
		}
	}
	
	
	
	public void loginStuProcess() {
		try {
			int stuNum = Integer.parseInt(ld.getJtfNum().getText());
			String stuPass = ld.getJtfPw().getText();
			
			
			LoginStudentDTO logDTO = ls.searchStuOneMember(stuNum);
			
			if(logDTO.getStuDelFlag().equals("Y"))
			{
				JOptionPane.showMessageDialog(ld, "삭제된 학생의 정보입니다.");
				return;
			}
			
			

			
			
			
			if(logDTO.getStuNum() == stuNum)
			{
				if(logDTO.getStuPass().length()>20) {
					JOptionPane.showMessageDialog(ld, "비밀번호는 20자 내로 입력하세요.");
					return;
				}
				if(logDTO.getStuPass().equals(stuPass)){
					CurrentStuData.getInstance().setLogStuDTO(logDTO);
					JOptionPane.showMessageDialog(ld, logDTO.getStuName() + "학생님 환영합니다.");
					new StuInfoDesign();
					ld.dispose();
					
				}else {
					JOptionPane.showMessageDialog(ld, "비밀번호를 확인해주세요");
				}
			}else{
				JOptionPane.showMessageDialog(ld, "아이디를 확인해주세요");
			}
			
		
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(ld, "숫자만 입력이 가능합니다");
			return;
		}
		
	}
	
	public void loginProfProcess() {
		
		try {
			int profNum = Integer.parseInt(ld.getJtfNum().getText());
			String profPass = ld.getJtfPw().getText();
			
			
			LoginProfDTO logDTO = ls.searchProfOneMember(profNum);
			
			if(logDTO.getProfDelFlag().equals("Y"))
			{
				JOptionPane.showMessageDialog(ld, "삭제된 교수의 정보입니다.");
				return;
			}
			
			
			if(logDTO.getProfNum() == profNum)
			{
				if(logDTO.getProfPass().equals(profPass)){
					CurrentProfData.getInstance().setLogProfDTO(logDTO);
					JOptionPane.showMessageDialog(ld, logDTO.getProfName() + "교수님 환영합니다.");
					new ProfInfoDesign();
					ld.dispose();
				}else {
					JOptionPane.showMessageDialog(ld, "비밀번호를 확인해주세요");
				}
			}else{
				JOptionPane.showMessageDialog(ld, "아이디를 확인해주세요");
			}
			
		
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(ld, "숫자만 입력이 가능합니다");
			return;
		}
		
	}
	
	
	
	public void loginAdminProcess() {

		String AdminId = ld.getJtfNum().getText();
		String AdminPass = ld.getJtfPw().getText();
		
		
		LoginAdminDTO logDTO = ls.searchAdminOneMember(AdminId);
		
		
		if(logDTO.getAdminId() == AdminId)
		{
			if(logDTO.getAdminPass().equals(AdminPass)){
				JOptionPane.showMessageDialog(ld, "관리자 계정으로 로그인 하였습니다.");	
				new AdminInfoDesign();
				ld.dispose();
			}else {
				JOptionPane.showMessageDialog(ld, "비밀번호를 확인해주세요");
			}
		}else{
			JOptionPane.showMessageDialog(ld, "아이디를 확인해주세요");
		}	
	}

}
