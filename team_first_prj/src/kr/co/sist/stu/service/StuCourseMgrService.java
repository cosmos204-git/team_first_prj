package kr.co.sist.stu.service;

import java.util.List;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.dao.StuCourseMgrDAO;
import kr.co.sist.stu.dto.StuCourseMgrDTO;

public class StuCourseMgrService {

    public List<StuCourseMgrDTO> selectAllCourse() {
        StuCourseMgrDAO dao = StuCourseMgrDAO.getInstance();
        try {
        	
        	CurrentStuData csd = CurrentStuData.getInstance();
        	
            int courseCode = csd.getLogStuDTO().getStuCourseNum();

            return dao.selectAllCourse(courseCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
