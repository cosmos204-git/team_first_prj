package kr.co.sist.stu.service;

import java.util.List;
import java.util.Map;

import kr.co.sist.stu.dao.StuExamDAO;
import kr.co.sist.stu.dto.ExamItemDTO;

public class StuExamService {
    private final StuExamDAO dao = StuExamDAO.getInstance();

    public boolean alreadyTaken(int stuNum, int testCode) {
        try {
            return dao.hasTakenTest(stuNum, testCode);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public List<ExamItemDTO> getExamItems(int testCode) {
        try {
            return dao.selectExamItems(testCode);
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    public int submitAndScore(int stuNum, int testCode, Map<Integer,Integer> answers) {
        try {
            StuExamDAO dao = StuExamDAO.getInstance();

            Integer courseCode = dao.findCourseCodeByTestCode(testCode);
            Integer subCode = dao.findSubCodeByTestCode(testCode);
            if (courseCode == null || subCode == null) return -1;

            dao.insertExamResults(stuNum, answers);

            int[] res = dao.calcScoreAndTotal(stuNum, testCode);
            int correct = res[0];
            int total = res[1];
            if (total <= 0) return -1;

            int score = (int)Math.round((correct * 100.0) / total);

            dao.upsertReportScore(stuNum, subCode, courseCode, score);

            return score;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }




    public Integer findTestCode(String course, String subject) {
        try {
            return kr.co.sist.stu.dao.StuExamDAO.getInstance().findTestCode(course, subject);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
