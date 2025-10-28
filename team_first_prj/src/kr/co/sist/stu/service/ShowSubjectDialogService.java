package kr.co.sist.stu.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.stu.dao.StuSubjectDialogDAO;
import kr.co.sist.stu.dto.StuSubjectDialogDTO;

public class ShowSubjectDialogService {

	private StuSubjectDialogDAO ssdDAO;

	public ShowSubjectDialogService() {
		ssdDAO = StuSubjectDialogDAO.getInstance();
	}//ShowSubjectDialogService

	/**
	 * 상세 과목 리스트 조회
	 */
	public List<StuSubjectDialogDTO> searchAllSubject(int courseCode) {
		List<StuSubjectDialogDTO> list = null;

		try {
			list = ssdDAO.selectAllSubject(courseCode);
		} catch (IOException ie) {
			ie.printStackTrace();
			
		} catch (SQLException se) {
			se.printStackTrace();
			
		} // end catch

		return list;
	}// searchAllSubject

}// class