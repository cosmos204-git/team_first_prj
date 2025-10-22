package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.admin.view.TestExamMgrDesign;

public class TestExamMgrDesignEvt extends WindowAdapter implements ActionListener,MouseListener{
	private TestExamMgrDesign temd;
//	private TestExamMgrService tems;	
	
	public TestExamMgrDesignEvt(TestExamMgrDesign temd) {
		this.temd=temd;
	}//TestExamMgrDesignEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==temd.getJbtnReset()) {
			System.out.println("시험지보기/추가 - 초기화");
			resetProcess();
		}//end if 
		if(ae.getSource()==temd.getJbtnAdd()) {
			System.out.println("시험지보기/추가 - 추가");
			addProcess();
		}//end if 
		if(ae.getSource()==temd.getJbtnDelete()) {
			System.out.println("시험지보기/추가 - 삭제");
			deleteProcess();
		}//end if 
	}//ActionEvent e
	
	@Override
	public void windowClosing(WindowEvent e) {
		temd.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent me) {
		
	}//mouseClicked(MouseEvent e

	
	
	public void resetProcess() {}//resetProcess
	public void addProcess() {}//addProcess
	public void deleteProcess() {}//deleteProcess
	public void searchEiListProcess() {}//searchEiListProcess
	
	
	
	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}//class
