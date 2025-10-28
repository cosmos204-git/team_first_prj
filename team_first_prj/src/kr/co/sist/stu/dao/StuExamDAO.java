package kr.co.sist.stu.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.stu.dto.ExamItemDTO;

public class StuExamDAO {

    private static StuExamDAO instance;
    private StuExamDAO() {}
    public static StuExamDAO getInstance() {
        if (instance == null) instance = new StuExamDAO();
        return instance;
    }

    public Integer findTestCode(String courseName, String subName) throws SQLException, IOException {
        Integer testCode = null;
        GetConnection gc = GetConnection.getInstance();
        String sql =
            "SELECT e.test_code " +
            "FROM course c " +
            "JOIN course_subject cs ON cs.course_code = c.course_code " +
            "JOIN subject s ON s.sub_code = cs.sub_code " +
            "JOIN exam e ON e.course_code = c.course_code AND e.sub_code = s.sub_code " +
            "WHERE c.course_name = ? AND s.sub_name = ? AND e.exam_open = '시험가능' " +
            "FETCH FIRST 1 ROWS ONLY";
        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, courseName);
            ps.setString(2, subName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) testCode = rs.getInt(1);
            }
        }
        return testCode;
    }

    public List<ExamItemDTO> selectExamItems(int testCode) throws SQLException, IOException {
        List<ExamItemDTO> list = new ArrayList<>();
        GetConnection gc = GetConnection.getInstance();
        String sql =
            "SELECT exam_code, test_code, exam_quest, exam_choice1, exam_choice2, " +
            "       exam_choice3, exam_choice4, exam_correct_tchoice, ei_del_flag, exam_inputdate " +
            "FROM exam_item " +
            "WHERE test_code = ? AND NVL(ei_del_flag,'N') <> 'Y' " +
            "ORDER BY exam_code";
        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, testCode);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ExamItemDTO d = new ExamItemDTO();
                    d.setExamCode(rs.getInt("exam_code"));
                    d.setTestCode(rs.getInt("test_code"));
                    d.setExamQuest(rs.getString("exam_quest"));
                    d.setExamChoice1(rs.getString("exam_choice1"));
                    d.setExamChoice2(rs.getString("exam_choice2"));
                    d.setExamChoice3(rs.getString("exam_choice3"));
                    d.setExamChoice4(rs.getString("exam_choice4"));
                    d.setExamCorrectTchoice(rs.getInt("exam_correct_tchoice"));
                    d.setDelFlag(rs.getString("ei_del_flag"));
                    d.setInputDate(rs.getDate("exam_inputdate"));
                    list.add(d);
                }
            }
        }
        return list;
    }

    public boolean hasTakenTest(int stuNum, int testCode) throws SQLException, IOException {
        GetConnection gc = GetConnection.getInstance();
        String sql =
            "SELECT COUNT(*) " +
            "FROM exam_result er " +
            "JOIN exam_item ei ON ei.exam_code = er.exam_code " +
            "WHERE er.stu_num = ? AND ei.test_code = ?";
        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, stuNum);
            ps.setInt(2, testCode);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public int[] insertExamResults(int stuNum, Map<Integer,Integer> answerByExamCode)
            throws SQLException, IOException {
        if (answerByExamCode == null || answerByExamCode.isEmpty()) return new int[0];
        GetConnection gc = GetConnection.getInstance();
        String sql =
            "INSERT INTO exam_result (stu_ans, stu_num, stu_ans_inputdate, exam_code) " +
            "VALUES (?, ?, SYSDATE, ?)";
        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (Map.Entry<Integer,Integer> e : answerByExamCode.entrySet()) {
                ps.setInt(1, e.getValue());
                ps.setInt(2, stuNum);
                ps.setInt(3, e.getKey());
                ps.addBatch();
            }
            return ps.executeBatch();
        }
    }

    public int[] calcScoreAndTotal(int stuNum, int testCode) throws SQLException, IOException {
        GetConnection gc = GetConnection.getInstance();
        String sql =
            "SELECT SUM(CASE WHEN er.stu_ans = ei.exam_correct_tchoice THEN 1 ELSE 0 END) AS correct_cnt, " +
            "       COUNT(*) AS total_cnt " +
            "FROM exam_result er " +
            "JOIN exam_item ei ON ei.exam_code = er.exam_code " +
            "WHERE er.stu_num = ? AND ei.test_code = ?";
        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, stuNum);
            ps.setInt(2, testCode);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new int[]{ rs.getInt("correct_cnt"), rs.getInt("total_cnt") };
            }
        }
    }

    public Integer findSubCodeByTestCode(int testCode) throws SQLException, IOException {
        GetConnection gc = GetConnection.getInstance();
        String sql = "SELECT sub_code FROM exam WHERE test_code = ?";
        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, testCode);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }
    
    public Integer findCourseCodeByTestCode(int testCode) throws SQLException, IOException {
        GetConnection gc = GetConnection.getInstance();
        String sql = "SELECT course_code FROM exam WHERE test_code = ?";
        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, testCode);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }

    
    public int upsertReportScore(int stuNum, int subCode, int courseCode, int score) throws SQLException, IOException {
        GetConnection gc = GetConnection.getInstance();
        String sel = "SELECT COUNT(*) FROM report WHERE stu_num = ? AND sub_code = ? AND course_code = ?";
        String upd = "UPDATE report SET stu_score = ? WHERE stu_num = ? AND sub_code = ? AND course_code = ?";
        String ins = "INSERT INTO report (stu_num, sub_code, course_code, stu_score) VALUES (?, ?, ?, ?)";

        try (Connection con = gc.getConn();
             PreparedStatement ps = con.prepareStatement(sel)) {
            ps.setInt(1, stuNum);
            ps.setInt(2, subCode);
            ps.setInt(3, courseCode);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int cnt = rs.getInt(1);
                if (cnt > 0) {
                    try (PreparedStatement u = con.prepareStatement(upd)) {
                        u.setInt(1, score);
                        u.setInt(2, stuNum);
                        u.setInt(3, subCode);
                        u.setInt(4, courseCode);
                        return u.executeUpdate();
                    }
                } else {
                    try (PreparedStatement i = con.prepareStatement(ins)) {
                        i.setInt(1, stuNum);
                        i.setInt(2, subCode);
                        i.setInt(3, courseCode);
                        i.setInt(4, score);
                        return i.executeUpdate();
                    }
                }
            }
        }
    }

}
