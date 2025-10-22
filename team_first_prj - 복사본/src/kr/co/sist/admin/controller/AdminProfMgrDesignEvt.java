package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.AdminProfMgrDesign;
import kr.co.sist.admin.view.AdminStuMgrDesign;
import kr.co.sist.admin.view.ProfAddDialog;
import kr.co.sist.admin.view.ProfModifyDialog;

public class AdminProfMgrDesignEvt extends WindowAdapter implements ActionListener, MouseListener{
	
	private AdminProfMgrDesign apmd ;
//	private AdminProfMgrService apms;
	
	public AdminProfMgrDesignEvt(AdminProfMgrDesign apmd ) {
		this.apmd=apmd;
	}//AdminStuMgrDesign

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==apmd.getJbtnModify()) {
			System.out.println("교수관리 - 수정");
			new ProfModifyDialog(apmd,false);
		}//end if 
		
		if(ae.getSource()==apmd.getJbtnAdd()) {
			System.out.println("교수관리 - 추가");
			new ProfAddDialog(apmd,false);
		}//end if 
		
		if(ae.getSource()==apmd.getJbtnDelete()) {
			System.out.println("삭제");
		}//end if 
		
		if(ae.getSource()==apmd.getJbtnClose()) {
			System.out.println("꺼질게~");
			apmd.dispose();
		}//end if 
		
	}//actionPerformed
	
	
	@Override
	public void mouseClicked(MouseEvent me) {
			//실제로 사용할 리스너
	}//mouseClicked

	@Override
	public void windowClosing(WindowEvent we) {
		apmd.dispose();
	}//windowClosing
	
	
	public void ProfInfoProcess() {
	}//ProfInfoProcess
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

	

	
	
}//AdminStuMgrDesignEvt
