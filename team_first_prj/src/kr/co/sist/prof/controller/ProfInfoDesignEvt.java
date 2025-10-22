package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.prof.view.ProfExamMgrDesign;
import kr.co.sist.prof.view.ProfInfoDesign;
import kr.co.sist.prof.view.ProfInfoModifyDesign;
import kr.co.sist.prof.view.ProfScoreMgrDesign;
import kr.co.sist.prof.view.ProfStuStateDesign;

public class ProfInfoDesignEvt extends WindowAdapter implements ActionListener{

	private ProfInfoDesign pid;
	
	public ProfInfoDesignEvt(ProfInfoDesign pid) {
		this.pid = pid;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		pid.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pid.getJbtnModifyProfInfo()) {
			new ProfInfoModifyDesign(pid,true);			
		}
		
		if(e.getSource()==pid.getJbtnMgrExam()) {
			new ProfExamMgrDesign(pid,true);
		}
		
		if(e.getSource()==pid.getJbtnStuState()) {
			new ProfStuStateDesign(pid,true);
		}
		
		if(e.getSource()==pid.getJbtnMgrProfScore()) {
			new ProfScoreMgrDesign();			
		}
	}

}
