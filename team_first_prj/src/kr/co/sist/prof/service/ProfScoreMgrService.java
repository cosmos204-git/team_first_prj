package kr.co.sist.prof.service;

import java.util.List;

import kr.co.sist.prof.dao.ScoreMgrDAO;
import kr.co.sist.prof.dto.ProfCourseSubjectDTO;
import kr.co.sist.prof.dto.ProfStuScoreDTO;

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
	
	public List<ProfCourseSubjectDTO> searchAllSubject(int num, int courseCode){
		
		List<ProfCourseSubjectDTO> list = null;		
		try {
			ScoreMgrDAO smDAO = ScoreMgrDAO.getInstance();
			
			list = smDAO.selectAllSubject(num, courseCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		 
	}
	
	public List<ProfStuScoreDTO> searchSubStuCourseScore(int profNum, int courseCode, int subCode, int stuNum){
		List<ProfStuScoreDTO> list = null;	
		try {
			ScoreMgrDAO smDAO = ScoreMgrDAO.getInstance();
			
			list = smDAO.selectSubStuCourseScore(profNum, courseCode, subCode, stuNum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
}
