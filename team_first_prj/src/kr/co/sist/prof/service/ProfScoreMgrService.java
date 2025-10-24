package kr.co.sist.prof.service;

import java.util.List;

import kr.co.sist.prof.dao.ScoreMgrDAO;
import kr.co.sist.prof.dto.ProfCourseSubjectDTO;

public class ProfScoreMgrService {

	public ProfScoreMgrService() {
		
	}
	
	public List<ProfCourseSubjectDTO> searchAllCourse(int num){
		
		List<ProfCourseSubjectDTO> list = null;		
		try {
			ScoreMgrDAO smDAO = ScoreMgrDAO.getInstance();
			
			list = smDAO.selectAllCourse(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		 
	}
	
	public List<ProfCourseSubjectDTO> searchAllSubject(int num, String courseName){
		
		List<ProfCourseSubjectDTO> list = null;		
		try {
			ScoreMgrDAO smDAO = ScoreMgrDAO.getInstance();
			
			list = smDAO.selectAllSubject(num, courseName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		 
	}
	
}
