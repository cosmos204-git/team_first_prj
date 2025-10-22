package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
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
		searchAllStu();
	}//AdminStuMgrDesign

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==asmd.getJbtnSearch()) {
			try {
			
				searchProcess();
			}catch (NumberFormatException ne) {
				JOptionPane.showMessageDialog(asmd, "숫자만 입력해주세요.");
				asmd.getJtfNum().setText("");
				searchAllStu();
			
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(asmd, "존재하지 않는 회원입니다.");
				asmd.getJtfNum().setText("");
				searchAllStu();
			}//end catch
		
		}//end if 
		
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
			
//		if(me.getButton() == MouseEvent.BUTTON1)//왼쪽버튼 클릭 시 
		 int selectedRowCount = asmd.getJtStuMgr().getSelectedRow();
		 System.out.println(selectedRowCount);
		
			
	}//mouseClicked

	@Override
	public void windowClosing(WindowEvent we) {
		asmd.dispose();
	}//windowClosing
	
	public void searchAllStu() {
		List<StudentDTO> listStudentData=asms.searchAllStudent();
		
		DefaultTableModel dtm=asmd.getDtmStuMgr();
		
		dtm.setRowCount(0);
		String[] rowData=null;
		
		for(StudentDTO sDTO : listStudentData) {
			rowData=new String[4];
			rowData[0]=String.valueOf(sDTO.getStuNum());
			rowData[1]=sDTO.getStuName();
			rowData[2]=sDTO.getStuTel();
			rowData[3]=String.valueOf(sDTO.getStuInputDate());
			
			dtm.addRow(rowData);
		}//end for 

		
	}//searchAllStu
	
	
	
	public void searchProcess() throws NumberFormatException,NullPointerException {
		int StuNum = Integer.parseInt(asmd.getJtfNum().getText().trim());
		
		DefaultTableModel dtmStuMgr =null;
		dtmStuMgr = asmd.getDtmStuMgr();
		StudentDTO sDTO= asms.searchStudent(StuNum);
		
		String[] rowData = rowData = new String[4];
		
		//테이블 초기화
		dtmStuMgr.setRowCount(0);
		
	
		rowData[0]=String.valueOf(sDTO.getStuNum());
		rowData[1]=sDTO.getStuName();
		rowData[2]=sDTO.getStuTel();
		rowData[3]=String.valueOf(sDTO.getStuInputDate());

		dtmStuMgr.addRow(rowData);
		
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
