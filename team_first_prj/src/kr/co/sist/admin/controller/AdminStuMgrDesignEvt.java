package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.admin.service.AdminStuMgrService;
import kr.co.sist.admin.view.AdminStuMgrDesign;
import kr.co.sist.admin.view.StuAddDialog;
import kr.co.sist.admin.view.StuModifyDialog;

public class AdminStuMgrDesignEvt extends WindowAdapter implements ActionListener, MouseListener{
	
	private AdminStuMgrDesign asmd;
	private AdminStuMgrService asms;
	
	public AdminStuMgrDesignEvt(AdminStuMgrDesign asmd) {
		this.asmd=asmd;
		asms=new AdminStuMgrService();
	}//AdminStuMgrDesign

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==asmd.getJbtnModify()) {
			System.out.println("학생관리 - 수정");
			new StuModifyDialog(asmd,false);
		}//end if 
		
		if(ae.getSource()==asmd.getJbtnAdd()) {
			System.out.println("학생관리 - 추가");
			new StuAddDialog(asmd,false);
		}//end if 
		
		if(ae.getSource()==asmd.getJbtnDelete()) {
			System.out.println("학생관리 - 삭제");
			deleteProcess();
		}//end if 
		
		if(ae.getSource()==asmd.getJbtnClose()) {
			asmd.dispose();
		}//end if 
	}//actionPerformed
	
	
	@Override
	public void mouseClicked(MouseEvent me) {
			//실제로 사용할 리스너
	}//mouseClicked

	@Override
	public void windowClosing(WindowEvent we) {
		asmd.dispose();
	}//windowClosing
	
	public void searchAllStu() {
		List<StudentDTO> listStudentData=asms.searchAllStudent();
		
		DefaultTableModel dtm=asmd.getDtmStuMgr();
		
//		dtm.clear();//리스트모델을 초기화
		dtm.setRowCount(0);
		String[] rowData=null;
		
		for(StudentDTO aDTO : listStudentData) {
			rowData=new String[4];
			rowData[0]=String.valueOf(aDTO.getStuNum());
			rowData[1]=aDTO.getStuName();
			rowData[2]=aDTO.getStuTel();
			rowData[3]=String.valueOf(aDTO.getStuInputDate());
			
			dtm.addRow(rowData);
		}//end for 

		
	}//searchAllStu
	public void searchProcess() {
		
	}//searchProcess
	public void addProcess() {
		
	}//addProcess
	public void modifyProcess() {
		
	}//modifyProcess
	public void deleteProcess() {
		
	}//deleteProcess

	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	

	
	
}//class
