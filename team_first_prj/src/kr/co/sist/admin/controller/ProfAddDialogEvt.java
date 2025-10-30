package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.regex.Pattern;

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
		pad.getJtfProNum().setText(String.valueOf(pas.AddProfNum()));
	}//StuAddDialogEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pad.getJbtnAdd()) {
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
	
	
	public boolean duplicateName(String profName) {
		boolean profFlag=false;
		List<ProfDTO> list = pas.searchAllProfessor();
		for(ProfDTO pDTO:list) {
			String name= pDTO.getProfName();
			if(profName.equals(name)) {
				profFlag=true;
				break;
			}
		}
		
		return profFlag;
	}//duplicateName
	
	public void addProcess() {
		ProfDTO pDTO = new ProfDTO();
		

		String profName= pad.getJtfProName().getText().trim();
		String profTel = pad.getJtfProTel().getText().trim();
		
		String telRegex = "^010-\\d{4}-\\d{4}$";
		
		String msg = "이름과 전화번호를 모두 입력해주세요!"; 
		
		//이름, 전화번호가 적혀져 있는 내용이 없다면 
		if(profName==null||profName.isEmpty()||profTel==null||profTel.isEmpty()) {
			JOptionPane.showMessageDialog(pad, msg);
			return;
		}
		if(profName.length()<2||profName.length()>5) {
			msg="이름을 2~5자 사이로 입력해주세요.";
			JOptionPane.showMessageDialog(pad, msg);
			return ;
		}
		if(!Pattern.matches(telRegex, profTel)) {
			msg= "전화번호 형식이 올바르지 않습니다.\n 010-xxxx-xxxx";
			JOptionPane.showMessageDialog(pad, msg);
			return;
		}//end if
		
		if(duplicateName(profName)) {
			profName=profName+"("+String.valueOf(pas.AddProfNum())+")";
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
