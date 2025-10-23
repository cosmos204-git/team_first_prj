package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import kr.co.sist.admin.dao.ScoreMgrDAO;
import kr.co.sist.admin.dto.CourseDTO;
import kr.co.sist.admin.dto.SubjectDTO;

public class AdminScoreMgrService {
	
	
	public List<CourseDTO> searchCourse() {
		List<CourseDTO> list = new ArrayList<CourseDTO>();
		ScoreMgrDAO smDAO = ScoreMgrDAO.getinstance();
		try {
			list = smDAO.selectCourse();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}//searchCourse
	
	
	
	public List<SubjectDTO> searchSubject(JComboBox<String> jc) {
		List<SubjectDTO> list = new ArrayList<SubjectDTO>();
		ScoreMgrDAO smDAO=ScoreMgrDAO.getinstance();
		try {
			list = smDAO.selectSubject(jc);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list; 
	}//searchSubject
	public void searchScore() {
	
	}//searchScore
	
}//class
