package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import kr.co.sist.admin.dao.ScoreMgrDAO;
import kr.co.sist.admin.dto.CourseDTO;
import kr.co.sist.admin.dto.ScoreMgrDTO;
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
	public List<ScoreMgrDTO> searchScore(JComboBox<String> corjc , JComboBox<String> subjc, JTextField stuNum) {
		List<ScoreMgrDTO> list = new ArrayList<ScoreMgrDTO>();
		ScoreMgrDAO smDAO = ScoreMgrDAO.getinstance();
		try {
		list =	smDAO.selectScore(corjc, subjc, stuNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return list;
	}//searchScore
	
	public List<ScoreMgrDTO> searchAllScore(){
		List<ScoreMgrDTO> list = new ArrayList<ScoreMgrDTO>();
		ScoreMgrDAO smDAO = ScoreMgrDAO.getinstance();
		try {
			list = smDAO.selelctAllScore();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
}//class
