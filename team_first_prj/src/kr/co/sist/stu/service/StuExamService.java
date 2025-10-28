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
            StuExamDAO dao = StuExamDAO.getInstance();
            dao.insertExamResult(stuNum, answers);
       
            int[] res = dao.calcScoreAndTotal(stuNum, testCode);
            int correct = res[0];
            int total = res[1];
            int score = (int)((correct * 100.0) / total);

            Integer subCode = dao.searchExamCode(testCode);
            if (subCode != null) {
                dao.updateReportScore(stuNum, subCode, score);
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
