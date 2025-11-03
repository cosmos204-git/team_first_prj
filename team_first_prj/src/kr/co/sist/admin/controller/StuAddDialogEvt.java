package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.admin.service.StuAddService;
import kr.co.sist.admin.view.StuAddDialog;

public class StuAddDialogEvt extends WindowAdapter implements ActionListener{
	private StuAddDialog sad;
	private StuAddService sas;
	
	public StuAddDialogEvt(StuAddDialog sad) {
		this.sad=sad;
		sas= new StuAddService();
		sad.getJtfStuNum().setText(String.valueOf(sas.AddStuNum())); //

	}//StuAddDialogEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == sad.getJbtnAdd()) {//추가완료 버튼을 누를시 
			addProcess();
		}//end if 
		if(ae.getSource() == sad.getJbtnClose()) {//닫기 버튼을 누를시
			closeProcess();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		sad.dispose();
	}//windowClosing
	
	public void addProcess() {
		
		StudentDTO sDTO = new StudentDTO();
		

		String stuName= sad.getJtfStuName().getText().trim();
		String stuTel = sad.getJtfStuTel().getText().trim();
		
		
		//msg Default 
		String msg = "이름과 전화번호를 모두 입력해주세요!"; 
		
		String telRegex = "^010-\\d{4}-\\d{4}$";
		String nameRegex = "[a-zA-Zㄱ-힣]+";
		
		//이름, 전화번호가 적혀져 있는 내용이 없다면 
		if(stuName==null||stuName.isEmpty()||stuTel==null||stuTel.isEmpty()) {
			JOptionPane.showMessageDialog(sad, msg);
			return;
		}
		
		if(!Pattern.matches(nameRegex, stuName)){
			msg="이름은 숫자 및 특수문자 사용이 불가능합니다.";
			JOptionPane.showMessageDialog(sad, msg);
			return;
		}
		if(stuName.length()<2||stuName.length()>5) {
			msg="이름을 2~5자 사이로 입력해주세요.";
			JOptionPane.showMessageDialog(sad, msg);
			return ;
		}
		if(!Pattern.matches(telRegex, stuTel)) {
			msg= "전화번호 형식이 올바르지 않습니다.\n ex) 010-xxxx-xxxx";
			JOptionPane.showMessageDialog(sad, msg);
			return ;
		}//end if 
		
		
		
		sDTO.setStuName(stuName);
		sDTO.setStuTel(stuTel);
		
		boolean flag=sas.AddStudnet(sDTO)==1;//새로운 계정이 문제 없이 생성되었을 경우 true
		
		if(flag) {//flag가 true일 경우만
			msg=sDTO.getStuName()+" 이름으로 새로운 계정이 추가되었습니다.";
		}//end if 
		
		
		//메시지 반영 후 바로 추가 Dialog 끄기 
		JOptionPane.showMessageDialog(sad, msg);
		sad.dispose();
		
		
	}//addProcess
	
	public void closeProcess() {
		sad.dispose();
	}//closeProcess
	
}//class
