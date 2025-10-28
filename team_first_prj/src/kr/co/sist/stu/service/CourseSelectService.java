package kr.co.sist.stu.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import kr.co.sist.stu.dao.SearchCourseDAO;
import kr.co.sist.stu.dto.SearchCourseDTO;

public class CourseSelectService {

	private SearchCourseDAO scDAO;

	public CourseSelectService() {
		scDAO = SearchCourseDAO.getInstance();
	}//CourseSelectService

	public List<SearchCourseDTO> searchCourse(Date startDate, Date endDate, int stuNum) {
		
		List<SearchCourseDTO> list = null;
		
		try {
			list = scDAO.selectCourse(startDate, endDate, stuNum);
			
		} catch (IOException ie) {
			ie.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} // end catch
		
		return list;
	} // searchCourse

}//class