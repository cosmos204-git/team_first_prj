package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.prof.view.ProfExamMgrDesign;
import kr.co.sist.prof.view.ProfInfoDesign;
import kr.co.sist.prof.view.ProfInfoModifyDesign;
import kr.co.sist.prof.view.ProfScoreMgrDesign;
import kr.co.sist.prof.view.ProfStuStateDesign;

public class ProfInfoDesignEvt extends WindowAdapter implements ActionListener{

	private ProfInfoDesign pid;
	
	public ProfInfoDesignEvt(ProfInfoDesign pid) {
		this.pid = pid;
		viewProfInfo();
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
			new ProfScoreMgrDesign(pid,true);			
		}
	}
	
	public void viewProfInfo() {
		CurrentProfData cpd = CurrentProfData.getInstance();
		
		pid.getJtfProfNameData().setText(cpd.getLogProfDTO().getProfName());
		pid.getJtfProfNumData().setText(String.valueOf(cpd.getLogProfDTO().getProfNum()));
		pid.getJtfProfEmailData().setText(cpd.getLogProfDTO().getProfEmail());
		pid.getJtfProfCourseData().setText(cpd.getLogProfDTO().getCourseName());
		
	}

}
