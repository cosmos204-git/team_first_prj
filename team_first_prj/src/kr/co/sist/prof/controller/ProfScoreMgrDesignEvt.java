package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.prof.dto.ProfCourseSubjectDTO;
import kr.co.sist.prof.dto.ProfStuScoreDTO;
import kr.co.sist.prof.dto.ShowStuReportInfoDTO;
import kr.co.sist.prof.service.ProfScoreMgrService;
import kr.co.sist.prof.service.ShowStuReportInfoService;
import kr.co.sist.prof.view.ProfScoreMgrDesign;
import kr.co.sist.prof.view.ShowStuReportInfoDesignDialog;

public class ProfScoreMgrDesignEvt extends WindowAdapter implements ActionListener, MouseListener, ListSelectionListener{

	private ProfScoreMgrDesign psmd;
	private ProfScoreMgrService psms;
	private ShowStuReportInfoService ssris;
	int courseIdx, subIdx;
	int courseCode = -1, subCode = -1;
	String courseName, subName;
	StringBuilder report;
	int stuNum;
	
	List<ProfCourseSubjectDTO> courseList;
	Map<String,Integer> subMap;
	int comboFlag = 0;
	
	

	
	public ProfScoreMgrDesignEvt(ProfScoreMgrDesign psmd) {
		this.psmd = psmd;
		ssris = new ShowStuReportInfoService();
		psms = new ProfScoreMgrService();
		subMap = new HashMap<String, Integer>();

	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		
		if(e.getSource()==psmd.getJcbProfCourse()) {
			courseIdx = psmd.getJcbProfCourse().getSelectedIndex();
			courseName = psmd.getJcbProfCourse().getItemAt(courseIdx);
			
			
			if(courseName != null && !courseName.equals("---과정선택---")) {
				courseCode = psmd.getCourseMap().get(courseName);
			}else {
				courseCode = -1;
			}
			//과정 선택시 과정 콤보박스 불러오기	
			psmd.getDcbmProfSub().removeAllElements();
			
			psmd.getDcbmProfSub().addElement("---과목선택---");
	
			if(courseIdx!=0) {
				for(int i = 0; i<showAllSubject().size(); i++ ) {
					psmd.getDcbmProfSub().addElement(showAllSubject().get(i).getSubName());
					subMap.put(showAllSubject().get(i).getSubName(), showAllSubject().get(i).getSubCode());
				}
			}
			
			
		
		}
		
		
		if(e.getSource()==psmd.getJcbProfSub()) {
			subIdx = psmd.getJcbProfSub().getSelectedIndex();
			subName = psmd.getJcbProfSub().getItemAt(subIdx);
			
			if(subName != null && !subName.equals("---과목선택---")) {
				subCode = subMap.get(subName);
			}else
				subCode = -1;
		}
			
		
		
		if(e.getSource()== psmd.getJbtnSearchStuNum()) {
			
			showReport();
			
		}
		
		if(e.getSource()== psmd.getJbtnShowStuReportInfo()) {
			new ShowStuReportInfoDesignDialog(psmd, true, report);
		}
	
	}

	@Override
	public void windowClosing(WindowEvent e) {
		psmd.dispose();
	}
	
	public List<ProfCourseSubjectDTO> showAllCourse() {
		CurrentProfData cpd = CurrentProfData.getInstance();
		
		courseList = psms.searchAllCourse(cpd.getLogProfDTO().getProfNum());
		return courseList;
		
		
	}
	
	public List<ProfCourseSubjectDTO> showAllSubject() {
		
		CurrentProfData cpd = CurrentProfData.getInstance();
		List<ProfCourseSubjectDTO> list = psms.searchAllSubject(cpd.getLogProfDTO().getProfNum(),courseCode);
		
		return list;
		
	}
	
	public void showReport() {
		CurrentProfData cpd = CurrentProfData.getInstance();
		psmd.getDtmProfScoreMgr().setRowCount(0);
		List<ProfStuScoreDTO> list = null;
		try {
			if(psmd.getJtfStuNum().getText().equals(null) || psmd.getJtfStuNum().getText().equals("")) {
				stuNum = -1;
			}else {
				stuNum = Integer.parseInt(psmd.getJtfStuNum().getText());				
			}
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(psmd, "숫자만 입력 가능합니다.");
			return;
		}
			
		list = psms.searchSubStuCourseScore(cpd.getLogProfDTO().getProfNum(), courseCode, subCode, stuNum);

		for(int i = 0 ; i<list.size(); i++) {
			String[] score = {String.valueOf(list.get(i).getStuNum()),list.get(i).getStuName(),String.valueOf(list.get(i).getSubCode()),list.get(i).getSubName(),String.valueOf(list.get(i).getStuScore())};			
			psmd.getDtmProfScoreMgr().addRow(score);
		}
		
		return;
		
	}
	public void writeStuReport() {
		
	}
	
	public void sendStuRow() {
		int selRow = psmd.getJtProfScoreMgr().getSelectedRow();
		int selStuNum = Integer.parseInt((String)psmd.getJtProfScoreMgr().getModel().getValueAt(selRow, 0));
		int selSubCode = Integer.parseInt((String)psmd.getJtProfScoreMgr().getModel().getValueAt(selRow, 2));
		
		List<ShowStuReportInfoDTO> listExam = null;
		
		listExam = ssris.searchStuReport(selStuNum,selSubCode);
		
		
		writeStuReport();
		
		report = new StringBuilder();
		for(int i = 0;  i<listExam.size();i++) {
			report.append(listExam.get(i).getExamQuest())
			.append("\n\n")
			.append(listExam.get(i).getExamChoice1())
			.append("\n")
			.append(listExam.get(i).getExamChoice2())
			.append("\n")
			.append(listExam.get(i).getExamChoice3())
			.append("\n")
			.append(listExam.get(i).getExamChoice4())
			.append("\n\n 정답 : ")
			.append(listExam.get(i).getExamCorrectChoice())
			.append("\n 제출한 답 : ")
			.append(listExam.get(i).getStuAns())
			.append("\n\n");
			
		}
				
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

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


	
	@Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { 
            // psmd.getJTableProfScoreMgr()는 JTable 객체를 반환하는 메소드여야 함
            int selectedRow = psmd.getJtProfScoreMgr().getSelectedRow(); 
            
            if (selectedRow != -1) {
                sendStuRow(); 
            }
        }
    }

}
