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

import kr.co.sist.admin.dto.ProfDTO;
import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.admin.service.AdminProfMgrService;
import kr.co.sist.admin.view.AdminProfMgrDesign;
import kr.co.sist.admin.view.ProfAddDialog;
import kr.co.sist.admin.view.ProfModifyDialog;
import kr.co.sist.admin.view.StuAddDialog;
import kr.co.sist.admin.view.StuModifyDialog;

public class AdminProfMgrDesignEvt extends WindowAdapter implements ActionListener, MouseListener{
	
	private AdminProfMgrDesign apmd ;
	private AdminProfMgrService apms;
	
	private List<ProfDTO> listProfData;
	private int selectedNum =-1;
	
	public AdminProfMgrDesignEvt(AdminProfMgrDesign apmd ) {
		this.apmd=apmd;
		apms = new AdminProfMgrService();
		ProfInfoProcess();
		
	}//AdminStuMgrDesign

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==apmd.getJbtnSearch()) {
			try {
				
				searchProcess();
			}catch (NumberFormatException ne) {
//				JOptionPane.showMessageDialog(apmd, "숫자만 입력해주세요.");
				apmd.getJtfProfNum().setText("");
				ProfInfoProcess();
			
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(apmd, "존재하지 않는 회원입니다.");
				apmd.getJtfProfNum().setText("");
				ProfInfoProcess();
			}//end catch
			
		}//end if
		if(ae.getSource()==apmd.getJbtnModify()) {
			System.out.println("교수관리 - 수정");
			modifyProcess();
			ProfInfoProcess();
			
			
		}//end if 
		
		if(ae.getSource()==apmd.getJbtnAdd()) {
			System.out.println("교수관리 - 추가");
			addProcess();
			ProfInfoProcess();
		}//end if 
		
		if(ae.getSource()==apmd.getJbtnDelete()) {
//			System.out.println("삭제");
			deleteProcess();
		}//end if 
		
		if(ae.getSource()==apmd.getJbtnClose()) {
			System.out.println("꺼질게~");
			apmd.dispose();
		}//end if 
		
	}//actionPerformed
	
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getButton() == MouseEvent.BUTTON1){//왼쪽버튼 클릭 시 
			
			selectedNum = apmd.getJtProfMgr().getSelectedRow();
			
		}//end if 
		
	}//mouseClicked

	@Override
	public void windowClosing(WindowEvent we) {
		apmd.dispose();
	}//windowClosing
	
	
	public void ProfInfoProcess()  {
		List<ProfDTO> listProfData=apms.searchAllProfessor();
		
		this.listProfData = listProfData;
		DefaultTableModel dtm=apmd.getDtmProMgr();
		
		dtm.setRowCount(0);
		String[] rowData=null;
		
		for(ProfDTO pDTO : listProfData) {
			rowData=new String[4];
			rowData[0]=String.valueOf(pDTO.getProfNum());
			rowData[1]=pDTO.getProfName();
			rowData[2]=pDTO.getProfTel();
			rowData[3]=String.valueOf(pDTO.getProfInputDate());
			
			dtm.addRow(rowData);
		}//end for 
	}//ProfInfoProcess
	public void searchProcess() throws NumberFormatException,NullPointerException{
		int ProfNum = Integer.parseInt(apmd.getJtfProfNum().getText().trim());
		
		DefaultTableModel dtmProMgr =null;
		dtmProMgr = apmd.getDtmProMgr();
		ProfDTO pDTO= apms.searchProfessor(ProfNum);
		
		String[] rowData = rowData = new String[4];
		
		//테이블 초기화
		dtmProMgr.setRowCount(0);
		
	
		rowData[0]=String.valueOf(pDTO.getProfNum());
		rowData[1]=pDTO.getProfName();
		rowData[2]=pDTO.getProfTel();
		rowData[3]=String.valueOf(pDTO.getProfInputDate());

		dtmProMgr.addRow(rowData);
	}//searchProcess
	public void addProcess() {
	DefaultTableModel dtmProMgr = apmd.getDtmProMgr();
		
		int dtmStuMgrlastIndex= dtmProMgr.getRowCount()-1;
//		StudentDTO sDTO= asms.searchStudent(dtmStuMgrlastIndex);
		
		
		int newProfNum=listProfData.get(dtmStuMgrlastIndex).getProfNum()+1;
		System.out.println(newProfNum);
		
		
		new ProfAddDialog(apmd,true,newProfNum);
		
	}//addProcess
	
	
	public void modifyProcess() throws IndexOutOfBoundsException {
		if(selectedNum == -1) {
			JOptionPane.showMessageDialog(apmd,"수정할 교수을 선택해주세요 ");
		}else {
			
			ProfDTO pDTO = apms.searchAllProfessor().get(selectedNum);
			int ProfNum =pDTO.getProfNum();
			System.out.println(ProfNum);
			
			new ProfModifyDialog(apmd,true,ProfNum);
		}//end else
	}//modifyProcess
	
	public void deleteProcess() {
		DefaultTableModel dtm = apmd.getDtmProMgr();
		
		if(selectedNum==-1) {
			JOptionPane.showMessageDialog(apmd, "삭제할 학생을 선택해주세요.");
			
		}//end if
		
		
		ProfDTO pDTO = listProfData.get(selectedNum);
		int deleteProfNum=pDTO.getProfNum();
		
		switch (JOptionPane.showConfirmDialog(apmd,deleteProfNum+"번 학생 정보를 정말 삭제 하시겠습니까?")) {
		case JOptionPane.OK_OPTION: 
			break;
		case JOptionPane.NO_OPTION: 
		case JOptionPane.CANCEL_OPTION:
		default:
			return;
		}//end switch
		
		
		int flag= apms.removeProfessor(deleteProfNum);
		
		String msg="문제가 발생했습니다. 잠시후 다시 시도해주세요";
		
		if(flag==1) {
			msg=deleteProfNum+"번 학생의 정보가 삭제되었습니다.";
		}//end if
		
		JOptionPane.showMessageDialog(apmd, msg);
		
		//삭제된 것을 확인하기위해 새로 반영 후 selectedNum 초기화 
		ProfInfoProcess();
		selectedNum=-1;
	}//deleteProcess
	public void closeProcess() {
		apmd.dispose();
	}//closeProcess
	
	
	
	
	
	
	
	
	
	
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

	

	
	
}//AdminStuMgrDesignEvt
