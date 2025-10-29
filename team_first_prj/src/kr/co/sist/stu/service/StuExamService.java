package kr.co.sist.stu.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.co.sist.stu.dao.StuExamDAO;
import kr.co.sist.stu.dto.ExamItemDTO;

public class StuExamService {
    private final StuExamDAO dao = StuExamDAO.getInstance();

    public boolean alreadyTaken(int stuNum, int testCode) {
        try {
            return dao.stuInsertExam(stuNum, testCode);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public List<ExamItemDTO> getExamItems(int testCode) {
        try {
            return dao.selectExamItem(testCode);
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    public int submitAndScore(int stuNum, int testCode, Map<Integer,Integer> answers) {
        try {
            // 1) 제출 저장
            dao.insertExamResult(stuNum, answers);

            // 2) 채점
            int[] res = dao.calcScoreAndTotal(stuNum, testCode);
            int correct = res[0];
            int total   = res[1];
            int score   = (total > 0) ? (int)((correct * 100.0) / total) : 0;

            // 3) testCode -> sub_code, course_code 조회
            Integer subCode    = dao.searchExamCode(testCode);
            Integer courseCode = dao.findCourseCodeByTestCode(testCode);

            // 4) report upsert (course_code 포함)
            if (subCode != null && courseCode != null) {
                dao.updateReportScore(stuNum, courseCode, subCode, score);
            }

            return score;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }



    public Integer findTestCode(String course, String subject) {
        try {
            return kr.co.sist.stu.dao.StuExamDAO.getInstance().searchTestCode(course, subject);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
