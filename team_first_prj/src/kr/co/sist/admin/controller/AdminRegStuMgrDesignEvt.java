package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.dto.CourseDTO;
import kr.co.sist.admin.dto.RegStuMgrDTO;
import kr.co.sist.admin.service.AdminRegStuMgrService;
import kr.co.sist.admin.view.AdminRegStuMgrDesign;

public class AdminRegStuMgrDesignEvt extends WindowAdapter implements ActionListener {
	private  AdminRegStuMgrDesign aamd;
//	private AdminAllStuMgrServic asms;
	public AdminRegStuMgrDesignEvt (AdminRegStuMgrDesign aamd) {
		this.aamd=aamd;
	}//AdminAllStuMgrDesignEvt
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == aamd.getJbtnSearchStuNum()) {
			if(numbercheck(aamd.getJtfStuNum())) {
				return;
			}
			searchProcess();
		}//end if 
	}//actionPerformed


	@Override
	public void windowClosing(WindowEvent e) {
		aamd.dispose();
	}//windowClosing


	public void searchAllStuProcess() {
		AdminRegStuMgrService arsms = new AdminRegStuMgrService();
		DefaultTableModel dtm = aamd.getDtmStudent();
		List<RegStuMgrDTO> list = arsms.searchAllstu();
		String[] rowData = new String[5];
		for(RegStuMgrDTO rsmDTO : list) {
			rowData[0] = rsmDTO.getCourseName() ;
			rowData[1] = rsmDTO.getProfName();
			rowData[2] = rsmDTO.getStuName();
			rowData[3] = String.valueOf(rsmDTO.getStuNum());
			rowData[4] = rsmDTO.getStuRegInputDate();
			
			dtm.addRow(rowData);
		}//end for
		
	}//searchAllStu
	public void searchComboProcess() {
		AdminRegStuMgrService arsms = new AdminRegStuMgrService();
		DefaultComboBoxModel<String> dcbm = aamd.getDcbmCoure();
		dcbm.addElement("");
		List<CourseDTO> list = arsms.searchCourse();
		String courseName = "";
		LocalDate currentDate = LocalDate.now();
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now();
		String isIng = "";
		for( CourseDTO element : list) {
			startDate = LocalDate.parse(element.getCourseStartDate());
			endDate = LocalDate.parse(element.getCourseEndDate());
			if(!currentDate.isBefore(startDate) && !currentDate.isAfter(endDate)) {
				isIng = "(진행 중)";
			}else if(currentDate.isBefore(startDate)) {
				isIng = "(시작 전)";
			}else {
				isIng = "(종료)";
			}//end else
			courseName = element.getCourseName();
			
			dcbm.addElement(courseName+isIng);
		}
	}//searchAllCourseProcess\
	public void searchProcess(){
		AdminRegStuMgrService arsms = new AdminRegStuMgrService();
		DefaultTableModel dtm = aamd.getDtmStudent();
		dtm.setRowCount(0);
		List<RegStuMgrDTO> list =arsms.searchStu(aamd.getJcbCourse(), aamd.getJtfStuNum());
		if(list.size()==0) {
			JOptionPane.showMessageDialog(aamd, "검색 결과가 없습니다.");
			return;
		}
		String[] rowData = new String[5];

		for(RegStuMgrDTO rsmDTO : list) {
			rowData[0] = rsmDTO.getCourseName();
			rowData[1] = rsmDTO.getProfName();
			rowData[2] = rsmDTO.getStuName();
			rowData[3] = String.valueOf( rsmDTO.getStuNum());
			rowData[4] = rsmDTO.getStuRegInputDate();
			
			dtm.addRow(rowData);
		}//end for
		
		
	}//searchProcess
	public boolean numbercheck(JTextField jtf) {
		boolean flag = false;
		if(!jtf.getText().trim().isEmpty() && jtf.getText()!=null ) {
		try {
		int number = Integer.parseInt(aamd.getJtfStuNum().getText().trim());
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(aamd, "학번은 숫자로만 입력가능합니다.");
			flag=true;
		}//end catch
		}//end if
		
		return flag;
	
	}//class
}

