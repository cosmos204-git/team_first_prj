package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import kr.co.sist.stu.dto.StuSubjectDialogDTO;
import kr.co.sist.stu.service.ShowSubjectDialogService;
import kr.co.sist.stu.view.ShowSubjectDialog;

public class ShowSubjectDialogEvt extends WindowAdapter implements ActionListener {

	private ShowSubjectDialog ssd;
	private ShowSubjectDialogService ssds;
	private List<StuSubjectDialogDTO> listStuSubDiaData;

	public ShowSubjectDialogEvt(ShowSubjectDialog ssd) {
		this.ssd = ssd;
		this.ssds = new ShowSubjectDialogService();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ssd.getJbtnClose()) {
			ssd.dispose();
		}
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent we) {
		ssd.dispose();
	}// windowClosing

	/**
	 * 과목 전체 조회
	 */
	public void viewAllSubject(int courseCode) {
		List<StuSubjectDialogDTO> listStuSubjectDialogData = ssds.searchAllSubject(courseCode);

		this.listStuSubDiaData = listStuSubjectDialogData;
		DefaultTableModel dtm = ssd.getDtmShowSubDialog();
		
		dtm.setRowCount(0); 
		String[] rowData=null;
		
		
		for (StuSubjectDialogDTO dto : listStuSubjectDialogData) {
			rowData[0] = dto.getSubName();
			dtm.addRow(rowData);
		}//end for
	}// viewAllSubject

}// class