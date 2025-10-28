package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import kr.co.sist.stu.view.StuExamDesign;

public class StuExamDesignEvt extends WindowAdapter implements ActionListener {

    private final StuExamDesign view;

    public StuExamDesignEvt(StuExamDesign view) { this.view = view; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getJbtnSubmit()) {
            int stuNum = view.getStuNum();
            int testCode = view.getTestCode();

            Map<Integer,Integer> answers = collectAnswers();
            if (answers.size() < view.getExamCodes().size()) {
                JOptionPane.showMessageDialog(view, "모든 문항에 답을 선택해주세요.", "확인", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 과정 일치 검사
            try {
                Integer testCourseCode = kr.co.sist.stu.dao.StuExamDAO.getInstance().findCourseCodeByTestCode(testCode);
                int stuCourseCode = kr.co.sist.login.dao.CurrentStuData.getCourseCode();
                if (testCourseCode == null || testCourseCode != stuCourseCode) {
                    JOptionPane.showMessageDialog(view,
                        "이 시험은 본인의 과정과 다른 과정입니다.\n응시할 수 없습니다.",
                        "과정 불일치", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "시험 과정 확인 중 오류가 발생했습니다.");
                ex.printStackTrace();
                return;
            }

            kr.co.sist.stu.service.StuExamService svc = new kr.co.sist.stu.service.StuExamService();
            if (svc.alreadyTaken(stuNum, testCode)) {
                JOptionPane.showMessageDialog(view, "이미 응시한 시험입니다.");
                return;
            }

            int score = svc.submitAndScore(stuNum, testCode, answers);
            if (score < 0) {
                JOptionPane.showMessageDialog(view, "제출 처리 중 오류가 발생했습니다.");
                return;
            }

            // 시험창 닫기
            view.dispose();

            // 성적표 띄우기
            String stuName = view.getStuName();
            java.awt.Window owner = SwingUtilities.getWindowAncestor(view);
            kr.co.sist.stu.view.StuCourseMgrDesign parent =
                (owner instanceof kr.co.sist.stu.view.StuCourseMgrDesign)
                    ? (kr.co.sist.stu.view.StuCourseMgrDesign) owner : null;

            new kr.co.sist.stu.view.StuReportDesign(parent, true, stuNum, stuName);
        }
    }



    private Map<Integer,Integer> collectAnswers() {
        Map<Integer,Integer> map = new LinkedHashMap<>();
        java.util.List<ButtonGroup> groups = view.getGroups();
        java.util.List<Integer> codes = view.getExamCodes();
        for (int i = 0; i < groups.size(); i++) {
            int selected = toChoiceNumber(groups.get(i));
            if (selected > 0) map.put(codes.get(i), selected);
        }
        return map;
    }

    private int toChoiceNumber(ButtonGroup bg) {
        Enumeration<AbstractButton> en = bg.getElements();
        int idx = 1;
        while (en.hasMoreElements()) {
            if (en.nextElement().isSelected()) return idx;
            idx++;
        }
        return 0;
    }

    @Override public void windowClosing(WindowEvent e) { view.dispose(); }
}
