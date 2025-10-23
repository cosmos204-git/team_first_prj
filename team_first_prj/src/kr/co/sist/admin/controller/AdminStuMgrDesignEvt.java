package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private List<StudentDTO> listStuData;
	private int selectedNum =-1;
	
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
//				JOptionPane.showMessageDialog(asmd, "숫자만 입력해주세요.");
				asmd.getJtfStuNum().setText("");
				searchAllStu();
			
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(asmd, "존재하지 않는 회원입니다.");
				asmd.getJtfStuNum().setText("");
				searchAllStu();
			}//end catch
		
		}//end if 
		
		if(ae.getSource()==asmd.getJbtnModify()) {
				ModifyProcess();
				searchAllStu();
			
		}//end if 
		
		if(ae.getSource()==asmd.getJbtnAdd()) {
//			new StuAddDialog(asmd,true);
			addProcess();
			searchAllStu();
		}//end if 
		
		if(ae.getSource()==asmd.getJbtnDelete()) {
			deleteProcess();
			
		}//end if 
		
		if(ae.getSource()==asmd.getJbtnClose()) {
			asmd.dispose();
		}//end if 
	}//actionPerformed
	
	
	@Override
	public void mouseClicked(MouseEvent me) {
			
		if(me.getButton() == MouseEvent.BUTTON1){//왼쪽버튼 클릭 시 
			
			selectedNum = asmd.getJtStuMgr().getSelectedRow();
			
		}//end if 
		
		
			
	}//mouseClicked

	@Override
	public void windowClosing(WindowEvent we) {
		asmd.dispose();
	}//windowClosing
	
	public void ModifyProcess() throws IndexOutOfBoundsException{
		
		if(selectedNum == -1) {
			JOptionPane.showMessageDialog(asmd,"수정할 학생을 선택해주세요 ");
		}else {
			
			StudentDTO sDTO = asms.searchAllStudent().get(selectedNum);
			int stuNum =sDTO.getStuNum();
			
			new StuModifyDialog(asmd,true,stuNum);
		}//end else
	}//ModifyProcess
	
	public void searchAllStu() {
		List<StudentDTO> listStudentData=asms.searchAllStudent();
		
		this.listStuData = listStudentData;
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
	
	public void addProcess() {
		
		DefaultTableModel dtmStuMgr = asmd.getDtmStuMgr();
		
		int dtmStuMgrlastIndex= dtmStuMgr.getRowCount()-1;
		System.out.println(dtmStuMgrlastIndex);
//		StudentDTO sDTO= asms.searchStudent(dtmStuMgrlastIndex);
		
		
		int newStuNum=listStuData.get(dtmStuMgrlastIndex).getStuNum()+1;
		System.out.println(newStuNum);
		
		
		new StuAddDialog(asmd,true,newStuNum);
	}//end addProcess
	
	
	public void searchProcess() throws NumberFormatException,NullPointerException {
		int StuNum = Integer.parseInt(asmd.getJtfStuNum().getText().trim());
		
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
	
	public void deleteProcess(){
			DefaultTableModel dtm = asmd.getDtmStuMgr();
			
			if(selectedNum==-1) {
				JOptionPane.showMessageDialog(asmd, "삭제할 학생을 선택해주세요.");
				
			}//end if
			
			
			StudentDTO sDTO = listStuData.get(selectedNum);
			int deleteStuNum=sDTO.getStuNum();
//			System.out.println(deleteStuNum);
			
			switch (JOptionPane.showConfirmDialog(asmd,deleteStuNum+"번 학생 정보를 정말 삭제 하시겠습니까?")) {
			case JOptionPane.OK_OPTION: 
				break;
			case JOptionPane.NO_OPTION: 
			case JOptionPane.CANCEL_OPTION:
			default:
				return;
			}//end switch
			
			
			int flag= asms.removeStudent(deleteStuNum);
			
			String outputmsg="문제가 발생했습니다. 잠시후 다시 시도해주세요";
			
			if(flag==1) {
				outputmsg=deleteStuNum+"번 학생의 정보가 삭제되었습니다.";
			}//end if
			
			JOptionPane.showMessageDialog(asmd, outputmsg);
			
			//삭제된 것을 확인하기위해 새로 반영 후 selectedNum 초기화 
			searchAllStu();
			selectedNum=-1;
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
