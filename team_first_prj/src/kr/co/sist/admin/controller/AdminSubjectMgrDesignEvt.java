package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.dto.CourseDTO;
import kr.co.sist.admin.dto.SubjectDTO;
import kr.co.sist.admin.service.AdminSubjectMgrDesignService;
import kr.co.sist.admin.view.AdminSubjectMgrDesign;
import kr.co.sist.admin.view.TestExamMgrDesign;

public class AdminSubjectMgrDesignEvt extends WindowAdapter implements ActionListener,MouseListener,ItemListener {
	private AdminSubjectMgrDesign asmd;
	private List<SubjectDTO> sDTOList;
	private List<SubjectDTO> courseSubList;
	private List<CourseDTO> cDTOList;
	
	public AdminSubjectMgrDesignEvt(AdminSubjectMgrDesign asmd) {
		this.asmd=asmd;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == asmd.getJbtnMgrTestExam()) {
			mgrTestExamProcess();
			
		}//end if 
		if(e.getSource() == asmd.getJbtnAdd()) {
			addProcess();
			searchCourseSub();
		}//end if 
		if(e.getSource() == asmd.getJbtnDelete()) {
			deleteProcess();
			searchCourseSub();

		}//end if 
		if(e.getSource() == asmd.getJbtnClose()) {
			closeProcess();
		}//end if 
		if(e.getSource() == asmd.getJcbCourse()) {
			searchCourseSub();
		}//end if
		if(e.getSource() == asmd.getJcbSub()) {
			setSub();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		asmd.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// 사용할 메소드 
	}//mouseClicked
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		//사용할 메소드 
	}//itemStateChanged
	
	public void searchSubProcess() {
		AdminSubjectMgrDesignService asmds = new AdminSubjectMgrDesignService();
		DefaultComboBoxModel<String> dcbm = asmd.getDcbmSub();
		sDTOList = asmds.searchSub();
		dcbm.addElement("");
		
		for(SubjectDTO sub : sDTOList) {
			
			dcbm.addElement(sub.getSubName());
		}//end for
		
		
	}//searchSubProcess
	
	public void searchCourseProcess() {
		AdminSubjectMgrDesignService asmds = new AdminSubjectMgrDesignService();
		DefaultComboBoxModel<String> dcbm = asmd.getDcbmCourse();
		cDTOList = asmds.searchCourse();
		for( CourseDTO temp : cDTOList) {
			dcbm.addElement(temp.getCourseName());
		}//end for
	}//searchCoursProcess
	
	public void searchCourseSub() {
		AdminSubjectMgrDesignService asmds = new AdminSubjectMgrDesignService();
		DefaultTableModel dtm = asmd.getDtmAdminSubMgr();
//		courseSubList = new ArrayList<SubjectDTO>();
//		courseSubList.clear();
		courseSubList = asmds.searchCourseSub(asmd.getJcbCourse());
		
		dtm.setRowCount(0);
		
		String[] arr = new String[3];
		
		for(SubjectDTO sDTO : courseSubList) {
			arr[0] = String.valueOf( sDTO.getSubCode());
			arr[1] = sDTO.getSubName();
			arr[2] = sDTO.getSubInputdate();
			
			dtm.addRow(arr);
		}//end for
		
	}//searchCourseSub
	
	
	public void setSub() {
		int num = asmd.getJcbSub().getSelectedIndex();
		asmd.getJlblCourseInputdateData().setText("");
		asmd.getJlblSubNumdata().setText("");
		if(num>0) {
			asmd.getJlblSubNumdata().setText(String.valueOf( sDTOList.get(num-1).getSubCode()));
			asmd.getJlblCourseInputdateData().setText(sDTOList.get(num-1).getSubInputdate());
		}
	}
	public void addProcess() {
		AdminSubjectMgrDesignService asmds = new AdminSubjectMgrDesignService();
		int sub = asmd.getJcbSub().getSelectedIndex();
		if(sub==0) {
			JOptionPane.showMessageDialog(asmd, "추가할 과목을 선택하세요.");
			return;
		}//end if
		
		int courseNum = asmd.getJcbCourse().getSelectedIndex();
		int subNum = asmd.getJcbSub().getSelectedIndex()-1;
		int rowCnt = asmds.addCourseSub(cDTOList.get(courseNum).getCourseCode(), sDTOList.get(subNum).getSubCode());
		if(rowCnt==-1) {
			JOptionPane.showMessageDialog(asmd, "이미 추가된 과목입니다.");
		}
	}//addProcess
	public void deleteProcess() {
		AdminSubjectMgrDesignService asmds = new AdminSubjectMgrDesignService();
		
		int courseNum = asmd.getJcbCourse().getSelectedIndex();
		int subNum =  asmd.getJtAdminSubMgr().getSelectedRow();
		int rowCnt = asmds.removeCourseSub(cDTOList.get(courseNum).getCourseCode(),courseSubList.get(subNum).getSubCode());
	} //deleteProcess
	public void mgrTestExamProcess() {
		int row =asmd.getJtAdminSubMgr().getSelectedRow();
		if(row<0) {
			JOptionPane.showMessageDialog(asmd, "시험지를 열람할 과목을 선택해주세요.");
			return;
		}
		int subCode = courseSubList.get(row).getSubCode();
		String subName = courseSubList.get(row).getSubName();
		int crow = asmd.getJcbCourse().getSelectedIndex();
		int courseCode = cDTOList.get(crow).getCourseCode();
		new TestExamMgrDesign(asmd,false,subCode, courseCode, subName);
	}//mgrTestExamProcess
	public void closeProcess() {
		asmd.dispose();
	}//closeProcess
	
	
	
	
	
	
	
	
	
	
	
	
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
	
	

}
