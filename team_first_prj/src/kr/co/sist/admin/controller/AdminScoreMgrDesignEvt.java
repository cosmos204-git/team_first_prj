package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.dto.CourseDTO;
import kr.co.sist.admin.dto.SubjectDTO;
import kr.co.sist.admin.service.AdminScoreMgrService;
import kr.co.sist.admin.view.AdminScoreMgrDesign;

public class AdminScoreMgrDesignEvt extends WindowAdapter implements ActionListener{
	private  AdminScoreMgrDesign asmd;
	private AdminScoreMgrService asms; 
	
	public AdminScoreMgrDesignEvt(AdminScoreMgrDesign asmd){
		this.asmd=asmd;
	}//AdminScoreMgrDesignEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()== asmd.getJbtnSearchStuNum()) {
			searchScoreProcess();
		}//end if
		if(ae.getSource() == asmd.getJcbCourse()) {
			searchsubjectProcess();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		asmd.dispose();
	}//windowClosing
	
	
	public void searchScoreProcess() {
	}//searchScoreProcess
	public void searchCourseProcess() {
		AdminScoreMgrService asms = new AdminScoreMgrService();
		DefaultComboBoxModel<String> dcbm = asmd.getDcbmCourse();
		dcbm.addElement("");
		List<CourseDTO> cDTOList =  asms.searchCourse();
		for(CourseDTO cDTO : cDTOList){
			String rowData= null;
			rowData = cDTO.getCourseName();
			dcbm.addElement(rowData);
		}//end for
	}//searchCourseProcess
	public void searchsubjectProcess() {
		AdminScoreMgrService asms = new AdminScoreMgrService();
		DefaultComboBoxModel<String> dcbm = asmd.getDcbmSub();
		dcbm.removeAllElements();
		dcbm.addElement("");
		List<SubjectDTO> sDTOList = asms.searchSubject(asmd.getJcbCourse());
		for(SubjectDTO sDTO : sDTOList) {
			dcbm.addElement(sDTO.getSubName());
		}//end for
	}//searchSubjectProcess

}//class
