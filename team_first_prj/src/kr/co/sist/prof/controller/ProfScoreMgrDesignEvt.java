package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.prof.dto.ProfCourseSubjectDTO;
import kr.co.sist.prof.service.ProfScoreMgrService;
import kr.co.sist.prof.view.ProfScoreMgrDesign;
import kr.co.sist.prof.view.ShowStuReportInfoDesignDialog;

public class ProfScoreMgrDesignEvt extends WindowAdapter implements ActionListener{

	private ProfScoreMgrDesign psmd;
	private ProfScoreMgrService psms;
	int courseIdx = 0;
	String courseName;
	boolean comboFlag = false;
	
	public ProfScoreMgrDesignEvt(ProfScoreMgrDesign psmd) {
		this.psmd = psmd;
		psms = new ProfScoreMgrService();
		
		
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		
		courseIdx = psmd.getJcbProfCourse().getSelectedIndex();
		courseName = psmd.getJcbProfCourse().getItemAt(courseIdx);
		//과정 선택시 과정 콤보박스 불러오기	
//		psmd.getDcbmProfSub().addElement("---과목선택---");
		if(!comboFlag) {
			for(int i = 0; i<showAllSubject().size(); i++ ) {
				psmd.getDcbmProfSub().addElement(showAllSubject().get(i).getSubName());
			}
			comboFlag=true;
		}
		
		
	
		
		if(e.getSource()== psmd.getJbtnSearchStuNum()) {
			
			showReport();
			
		}
		
		if(e.getSource()== psmd.getJbtnShowStuReportInfo()) {
			new ShowStuReportInfoDesignDialog(psmd, true);
		}
	
	}

	@Override
	public void windowClosing(WindowEvent e) {
		psmd.dispose();
	}
	
	public List<ProfCourseSubjectDTO> showAllCourse() {
		CurrentProfData cpd = CurrentProfData.getInstance();
		
		List<ProfCourseSubjectDTO> list = psms.searchAllCourse(cpd.getLogProfDTO().getProfNum());
		
		return list;
		
	}
	
	public List<ProfCourseSubjectDTO> showAllSubject() {
		CurrentProfData cpd = CurrentProfData.getInstance();
		List<ProfCourseSubjectDTO> list = psms.searchAllSubject(cpd.getLogProfDTO().getProfNum(),courseName);
		
		return list;
		
	}
	
	public void showReport() {
		
		
		
		
	}

}
