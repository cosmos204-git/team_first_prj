package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.dto.SearchCourseDTO;
import kr.co.sist.stu.service.CourseSelectService;
import kr.co.sist.stu.view.CourseSelectDesign;

public class CourseSelectDesignEvt extends WindowAdapter implements ActionListener, MouseListener {

	private CourseSelectService css;
	private CourseSelectDesign csd;
	private List<SearchCourseDTO> listCourseData;
	private int selectedRow = -1;

	public CourseSelectDesignEvt(CourseSelectDesign csd) {
		this.csd = csd;
		this.css = new CourseSelectService();
		showCourseProcess();
	}//CourseSelectDesignEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == csd.getJbtnShowSub()) {
		}

		if (ae.getSource() == csd.getJbtnApplyCourse()) {
			applyProcess();
		}

		if (ae.getSource() == csd.getJbtnClose()) {
			csd.dispose();
		}
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1) {
			selectedRow = csd.getJtSelectCourse().getSelectedRow();
		}
	}// mouseClicked

	@Override
	public void windowClosing(WindowEvent we) {
		csd.dispose();
	}// windowClosing

	/**
	 * 선택된 기간과 학생번호를 기반 과정 리스트를 보여주는 method
	 */
	public void showCourseProcess() {
		try {
			CurrentStuData crsd = CurrentStuData.getInstance();
			
			int stuNum = crsd.getStuNum();

			
			listCourseData = css.searchCourse(stuNum);
			

			DefaultTableModel dtm = csd.getDtmSelectCourse();
			dtm.setRowCount(0);

			for(int i = 0; i< listCourseData.size();i++) {
				String[] data = {"",""};
				data[0]="X";
				data[1]=listCourseData.get(i).getCourseName();
				
				dtm.addRow(data);
			}
			
			
//			for (SearchCourseDTO scDTO : listCourseData) {
//				System.out.println(scDTO);
////				row[0] = scDTO.isCourseFlag() ? "수강중" : "종료";
////				row[1] = scDTO.getCourseName();
//			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(csd, "조회 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}// showSubProcess

	/**
	 * 신청 버튼 클릭 시 호출되는 method
	 */
	public void applyProcess() {
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(csd, "신청할 과정을 선택해주세요.");
			return;
		}

		SearchCourseDTO selectedDTO = listCourseData.get(selectedRow);
		JOptionPane.showMessageDialog(csd, selectedDTO.getCourseName() + " 과정을 신청했습니다.");
	}// applyProcess

	
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

}// class