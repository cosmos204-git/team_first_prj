package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import kr.co.sist.admin.dao.AdminSubjectMgrDAO;
import kr.co.sist.admin.dto.CourseDTO;
import kr.co.sist.admin.dto.SubjectDTO;

public class AdminSubjectMgrDesignService {
	public List<SubjectDTO> searchSub() {
		List<SubjectDTO> list = new ArrayList<SubjectDTO>();
		AdminSubjectMgrDAO asmDAO = AdminSubjectMgrDAO.getinstance();
		
		try {
			list = asmDAO.selectSub();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchSub
	
	public List<CourseDTO> searchCourse(){
		List<CourseDTO> list = new ArrayList<CourseDTO>();
		AdminSubjectMgrDAO asmDAO = AdminSubjectMgrDAO.getinstance();
		
		try {
			list = asmDAO.selectCourse();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}//searchCourse
	
	
	public List<SubjectDTO> searchCourseSub(JComboBox<String> jc){
		List<SubjectDTO> list = new ArrayList<SubjectDTO>();
		AdminSubjectMgrDAO asmDAO = AdminSubjectMgrDAO.getinstance();
		
		try {
			list = asmDAO.selectCourseSub(jc);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return list;
	}//searchCourseSub
	
	public int addCourseSub(int courseCode, int subCode) {
		int rowCnt =0;
		AdminSubjectMgrDAO asmDAO = AdminSubjectMgrDAO.getinstance();
		try {
			rowCnt = asmDAO.insertCourseSub(courseCode, subCode);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if(errorCode==1) {
				rowCnt=-1;
			}
			e.printStackTrace();
		}
		
		return rowCnt;
	}//addCourseSub
	
	
	public int removeCourseSub(int courseCode, int subCode) {
		int rowCnt =0;
		AdminSubjectMgrDAO asmDAO = AdminSubjectMgrDAO.getinstance();
		try {
			rowCnt = asmDAO.deleteCourseSub(courseCode, subCode);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowCnt;
	}//removeCourseSub
	
}//class
