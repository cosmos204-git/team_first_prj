package kr.co.sist.prof.view;

import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.prof.dao.ShowExamDetailDAO;
import kr.co.sist.prof.dto.ShowExamItemListDTO;

public class ShowExamDetailDesignDialog extends JDialog {

    private JLabel jlbSubject;
    private JTextArea jtaReport;

    public ShowExamDetailDesignDialog(ProfExamMgrDesign pemd, boolean modal) {
        super(pemd, "시험지 상세 보기", modal);

        jlbSubject = new JLabel("시험 상세 보기");
        jlbSubject.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        jtaReport = new JTextArea();
        jtaReport.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        jtaReport.setEditable(false);

        JScrollPane jspReport = new JScrollPane(jtaReport);

        add("North", jlbSubject);
        add("Center", jspReport);

        try {
            viewExamDetail(pemd);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "시험지 불러오기 중 오류가 발생했습니다.");
        }

        setBounds(pemd.getX() + 100, pemd.getY() + 50, 600, 700);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public void viewExamDetail(ProfExamMgrDesign pemd2) throws SQLException, IOException {

    		// 디자인에 선택과 과정과 과목을 기준으로 시험문제들을 불러온다.
    		String pCourseName = pemd2.getJtfExamCourseData().getText();
        String pSubName = pemd2.getJtfExamSubjectData().getText();

        ShowExamDetailDAO pDAO = ShowExamDetailDAO.getInstance();
        Collection<ShowExamItemListDTO> list = pDAO.getExamItemList(pCourseName, pSubName);

        jtaReport.setText(""); // 기존 내용 초기화

        if (list == null || list.isEmpty()) {
            jtaReport.append("등록된 시험 문제가 없습니다.\n");
            return;
        }

        int i = 1;
        for (ShowExamItemListDTO dto : list) {
            jtaReport.append(
                dto.getExamQuest() + "\n" +
                dto.getExamChoice1() + "\n" +
                dto.getExamChoice2() + "\n" +
                dto.getExamChoice3() + "\n" +
                dto.getExamChoice4() + "\n" +
                "정답: " + dto.getExamCorrectTChoice() + "\n\n"
            );
        }//end for
        
    }//viewExamDetail
    
    
}//class
