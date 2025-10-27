package kr.co.sist.stu.service;

import java.util.List;
import kr.co.sist.stu.dao.StuCourseMgrDAO;
import kr.co.sist.stu.dto.StuCourseMgrDTO;

public class StuCourseMgrService {

    public List<StuCourseMgrDTO> selectAllCourse() {
        StuCourseMgrDAO stucmDTO = StuCourseMgrDAO.getInstance();
        try {
            return stucmDTO.selectAllCourse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
