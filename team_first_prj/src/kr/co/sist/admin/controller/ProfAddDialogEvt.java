package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.admin.dto.ProfDTO;
import kr.co.sist.admin.service.ProfAddService;
import kr.co.sist.admin.view.ProfAddDialog;

public class ProfAddDialogEvt extends WindowAdapter implements ActionListener{
	private ProfAddDialog pad;
	private ProfAddService pas;
	
	public ProfAddDialogEvt(ProfAddDialog pad) {
		this.pad=pad;
		pas= new ProfAddService();
	}//StuAddDialogEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pad.getJbtnAdd()) {
			System.out.println("교수관리 - 추가");
			addProcess();
		}//end if 
		if(ae.getSource() == pad.getJbtnClose()) {
			closeProcess();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		pad.dispose();
	}//windowClosing
	
	public void addProcess() {
		ProfDTO pDTO = new ProfDTO();
		

		String profName= pad.getJtfProName().getText().trim();
		String profTel = pad.getJtfProTel().getText().trim();
		
		//msg Default 
		String msg = "이름과 전화번호를 모두 입력해주세요!"; 
		
		//이름, 전화번호가 적혀져 있는 내용이 없다면 
		if(profName==null||profName.isEmpty()||profTel==null||profTel.isEmpty()) {
			JOptionPane.showMessageDialog(pad, msg);
			return;
		}
		pDTO.setProfName(profName);
		pDTO.setProfTel(profTel);
		
		boolean flag=pas.AddProfessor(pDTO)==1;//새로운 계정이 문제 없이 생성되었을 경우 true
		
		if(flag) {//flag가 true일 경우만
			msg=pDTO.getProfName()+"이름으로 새로운 계정이 추가되었습니다.";
		}//end if 
		
		
		//메시지 반영 후 바로 추가 Dialog 끄기 
		JOptionPane.showMessageDialog(pad, msg);
		pad.dispose();
		
		
	}//addProcess
	
	public void closeProcess() {
		pad.dispose();
	}//closeProcess
	
}//class
