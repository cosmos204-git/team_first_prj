package kr.co.sist.stu.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.stu.dto.StuReportDTO;

public class StuReportDAO {

    private static StuReportDAO srDAO;

    private StuReportDAO() {}

    public static StuReportDAO getInstance() {
        if (srDAO == null) {
            srDAO = new StuReportDAO();
        }
        return srDAO;
    }

    public List<StuReportDTO> selelctAllScore() throws IOException, SQLException {
        List<StuReportDTO> srDTOList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        GetConnection gc = GetConnection.getInstance();

        try {
            con = gc.getConn();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT st.stu_name, s.sub_name, r.stu_score, ")
               .append("DENSE_RANK() OVER (PARTITION BY s.sub_code ORDER BY r.stu_score DESC) AS stu_rank ")
               .append("FROM report r, subject s, student st ")
               .append("WHERE r.sub_code = s.sub_code AND r.stu_num = st.stu_num ")
               .append("ORDER BY s.sub_name, stu_rank");

            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                srDTOList.add(new StuReportDTO(
                        rs.getInt("stu_score"),
                        rs.getInt("stu_rank"),
                        rs.getString("stu_name"),
                        rs.getString("sub_name")
                ));
            }
        } finally {
            gc.dbClose(con, pstmt, rs);
        }

        return srDTOList;
    }

    public List<StuReportDTO> selelctScoreByStuNum(int stuNum) throws IOException, SQLException {
        List<StuReportDTO> list = new ArrayList<>();
        String sql =
            "SELECT * FROM (" +
            "  SELECT st.stu_num, st.stu_name, s.sub_name, r.stu_score, " +
            "         DENSE_RANK() OVER (PARTITION BY s.sub_code ORDER BY r.stu_score DESC) AS stu_rank " +
            "  FROM report r " +
            "  JOIN student st ON st.stu_num = r.stu_num " +
            "  JOIN subject s ON s.sub_code = r.sub_code" +
            ") t WHERE t.stu_num = ? " +
            "ORDER BY t.sub_name, t.stu_name";

        GetConnection gc = GetConnection.getInstance();
        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, stuNum);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new StuReportDTO(
                        rs.getInt("stu_score"),
                        rs.getInt("stu_rank"),
                        rs.getString("stu_name"),
                        rs.getString("sub_name")
                    ));
                }
            }
        }
        return list;
    }
 // StuReportDAO.java
    public List<String> selectCoursesByStuNum(int stuNum) throws SQLException, IOException {
        List<String> courses = new ArrayList<>();
        String sql =
            "SELECT c.course_name " +
            "FROM student st JOIN course c ON c.course_code = st.course_code " +
            "WHERE st.stu_num = ?";

        GetConnection gc = GetConnection.getInstance();
        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, stuNum);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    courses.add(rs.getString("course_name"));
                }
            }
        }
        return courses;
    }



}
