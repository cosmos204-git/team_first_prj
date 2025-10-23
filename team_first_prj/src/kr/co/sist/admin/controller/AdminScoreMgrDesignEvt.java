package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.dto.CourseDTO;
import kr.co.sist.admin.dto.ScoreMgrDTO;
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
			if(numbercheck()) {return;}
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
		AdminScoreMgrService asms = new AdminScoreMgrService();
		DefaultTableModel dtm = asmd.getDtmAdminScoreMgr();
		dtm.setRowCount(0);
		List<ScoreMgrDTO> smDTOList = asms.searchScore(asmd.getJcbCourse(),asmd.getJcbSub(),asmd.getJtfStuNum());
		String stuNum, stuName, subName, score  = null;
		for(ScoreMgrDTO smDTO : smDTOList) {
			stuNum = String.valueOf(smDTO.getStuNum()) ;
			stuName = smDTO.getStuName();
			subName= smDTO.getSubName();
			score = String.valueOf( smDTO.getStuScore());
			String[] rowData = {stuNum,stuName,subName,score};
			dtm.addRow(rowData);
		}
		if(dtm.getRowCount() == 0) {JOptionPane.showMessageDialog(asmd, "검색 결과가 없습니다.");}
		
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
	
	public boolean numbercheck() {
		boolean flag =false;
		if(!asmd.getJtfStuNum().getText().trim().isEmpty()) {
		try {
		int n =Integer.parseInt( asmd.getJtfStuNum().getText());
		}catch(NumberFormatException nfe){
			nfe.getStackTrace();
			JOptionPane.showMessageDialog(asmd, "학번은 숫자로만 입력 가능합니다.");
			flag=true;
		}//end catch
		}//end if
		return flag;
	}
	
	public void seacrhAllScore() {
		AdminScoreMgrService asms = new AdminScoreMgrService();
		DefaultTableModel dtm = asmd.getDtmAdminScoreMgr();
		List<ScoreMgrDTO> list =  asms.searchAllScore();
		String stuNum, stuName, subName, score  = null;
		for( ScoreMgrDTO smDTO : list) {
			stuNum = String.valueOf(smDTO.getStuNum()) ;
			stuName = smDTO.getStuName();
			subName= smDTO.getSubName();
			score = String.valueOf( smDTO.getStuScore());
			String[] rowData = {stuNum,stuName,subName,score};
			dtm.addRow(rowData);
		}
	}//seacrhAllScore

}//class
