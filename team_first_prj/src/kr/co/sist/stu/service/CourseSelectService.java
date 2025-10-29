package kr.co.sist.stu.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.login.dto.LoginStudentDTO;
import kr.co.sist.stu.dao.SearchCourseDAO;
import kr.co.sist.stu.dto.SearchCourseDTO;

public class CourseSelectService {

	private SearchCourseDAO scDAO;

	public CourseSelectService() {
		scDAO = SearchCourseDAO.getInstance();
	}//CourseSelectService

	
	
	public List<SearchCourseDTO> searchCourse(int stuNum) {
		
		List<SearchCourseDTO> list = null;
		
		try {
			list = scDAO.selectCourse(stuNum);
			
		} catch (IOException ie) {
			ie.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} // end catch
		
		return list;
	} // searchCourse

	
    // 과정 신청 service
    public boolean applyCourse(SearchCourseDTO scDTO) {
        boolean result = false;

        try {
            CurrentStuData csd = CurrentStuData.getInstance();
            LoginStudentDTO lsDTO = new LoginStudentDTO();

            lsDTO.setStuNum(csd.getStuNum());
            lsDTO.setStuCourseNum(scDTO.getCourseCode());

            int flag = scDAO.updateCourse(lsDTO);

            //success=>true 
            if (flag > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

	
	
}//class