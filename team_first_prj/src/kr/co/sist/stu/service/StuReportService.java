package kr.co.sist.stu.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import kr.co.sist.stu.dao.StuReportDAO;
import kr.co.sist.stu.dto.StuReportDTO;

public class StuReportService {

    public List<StuReportDTO> searchScoreByStuNum(int stuNum) {
        try {
            return StuReportDAO.getInstance().selelctScoreByStuNum(stuNum);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<String> getCoursesByStuNum(int stuNum) {
        try {
            return StuReportDAO.getInstance().selectSubsByStuNum(stuNum);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
