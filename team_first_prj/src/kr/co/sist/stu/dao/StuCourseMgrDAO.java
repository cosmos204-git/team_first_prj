package kr.co.sist.stu.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.stu.dto.StuCourseMgrDTO;

public class StuCourseMgrDAO {

    private static StuCourseMgrDAO stucmDAO;

    public static StuCourseMgrDAO getInstance() {
        if (stucmDAO == null) {
            stucmDAO = new StuCourseMgrDAO();
        }
        return stucmDAO;
    }

    public List<StuCourseMgrDTO> selectAllCourse() throws SQLException, IOException {

        List<StuCourseMgrDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        GetConnection gc = GetConnection.getInstance();

        try {
            con = gc.getConn();

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT c.course_code, c.course_name, s.sub_code, s.sub_name, ")
              .append("NVL(e.exam_open, '시험불가') AS exam_open, ")
              .append("NVL(e.exam_start, '') AS exam_start, ")
              .append("NVL(e.exam_end, '') AS exam_end ")
              .append("FROM course c ")
              .append("JOIN course_subject cs ON cs.course_code = c.course_code ")
              .append("JOIN subject s ON s.sub_code = cs.sub_code ")
              .append("LEFT JOIN exam e ON e.course_code = c.course_code AND e.sub_code = s.sub_code ")
              .append("ORDER BY c.course_code, s.sub_code");

            pstmt = con.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StuCourseMgrDTO stucmDTO = new StuCourseMgrDTO();
                stucmDTO.setCourseCode(rs.getInt("course_code"));
                stucmDTO.setCourseName(rs.getString("course_name"));
                stucmDTO.setSubCode(rs.getInt("sub_code"));
                stucmDTO.setSubName(rs.getString("sub_name"));

                String open = rs.getString("exam_open");
                String start = rs.getString("exam_start");
                String end = rs.getString("exam_end");

                if ("시험가능".equals(open)) {
                	stucmDTO.setExamStart(start);
                	stucmDTO.setExamEnd(end);
                } else {
                	stucmDTO.setExamStart("시험불가");
                	stucmDTO.setExamEnd("");
                }

                list.add(stucmDTO);
            }

        } finally {
            gc.dbClose(con, pstmt, rs);
        }

        return list;
    }
}
