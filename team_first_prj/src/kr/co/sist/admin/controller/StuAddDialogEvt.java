package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.admin.service.stuAddService;
import kr.co.sist.admin.view.StuAddDialog;

public class StuAddDialogEvt extends WindowAdapter implements ActionListener{
	private StuAddDialog sad;
	private stuAddService sas;
	
	public StuAddDialogEvt(StuAddDialog sad) {
		this.sad=sad;
		sas= new stuAddService();

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
		

		String StuName= sad.getJtfStuName().getText().trim();
		String StuTel = sad.getJtfStuTel().getText().trim();
		
		//msg Default 
		String msg = "이름과 전화번호를 모두 입력해주세요!"; 
		
		//이름, 전화번호가 적혀져 있는 내용이 없다면 
		if(StuName==null||StuName.isEmpty()||StuTel==null||StuTel.isEmpty()) {
			JOptionPane.showMessageDialog(sad, msg);
			return;
		}
		sDTO.setStuName(StuName);
		sDTO.setStuTel(StuTel);
		
		boolean flag=sas.AddStudnet(sDTO)==1;//새로운 계정이 문제 없이 생성되었을 경우 true
		
		if(flag) {//flag가 true일 경우만
			msg=sDTO.getStuName()+"이름으로 새로운 계정이 추가되었습니다.";
		}//end if 
		
		
		//메시지 반영 후 바로 추가 Dialog 끄기 
		JOptionPane.showMessageDialog(sad, msg);
		sad.dispose();
		
		
	}//addProcess
	
	public void closeProcess() {
		sad.dispose();
	}//closeProcess
	
}//class
