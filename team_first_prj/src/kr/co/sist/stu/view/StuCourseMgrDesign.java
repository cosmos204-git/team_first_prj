package kr.co.sist.stu.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.stu.controller.StuCourseMgrDesignEvt;

public class StuCourseMgrDesign extends JDialog{
    private JButton jbtnclose, jbtnShowStuReport, jbtnShowExam;

    public StuCourseMgrDesign(StuInfoDesign sid, boolean modal) {
        super(sid,"학업관리",modal);

        JPanel plStuInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel jlblStuNum = new JLabel("학번 : ");
        JLabel jlblStuNumData = new JLabel("학번");
        JLabel jlblStuName = new JLabel("이름 : ");
        JLabel jlblStuNameData = new JLabel("이름");

        plStuInfo.add(jlblStuNum);
        plStuInfo.add(jlblStuNumData);
        plStuInfo.add(jlblStuName);
        plStuInfo.add(jlblStuNameData);

        add(plStuInfo, BorderLayout.NORTH);

        String[] columnNames = {"과정","과목","시험기간"};
        String[][] rowData = {
                {"Full stack","데이터베이스","2025-12-01~2025-12-10"},
                {"AI","머신러닝","응시불가"},
                {"AI","머신러닝","응시불가"},
                {"AI","머신러닝","응시불가"},
                {"AI","머신러닝","응시불가"},
                {"AI","머신러닝","응시불가"},
                {"AI","머신러닝","응시불가"},
                {"AI","머신러닝","응시불가"},
                {"Full stack","웹프로그래밍","2026-01-05~2026-01-15"}
        };
        
        DefaultTableModel dtmStuCourseMgr = new DefaultTableModel(rowData, columnNames);
        JTable jtStuCourseMgr = new JTable(dtmStuCourseMgr);
        jtStuCourseMgr.setRowHeight(25);

        JScrollPane jspStuCourseMgr = new JScrollPane(jtStuCourseMgr);
        add(jspStuCourseMgr, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        jbtnclose = new JButton("닫기");
        jbtnShowStuReport = new JButton("성적표");
        jbtnShowExam = new JButton("시험");

        buttonPanel.add(jbtnShowExam);
        buttonPanel.add(jbtnShowStuReport);
        buttonPanel.add(jbtnclose);

        add(buttonPanel, BorderLayout.SOUTH);
        
        Font font = new Font("맑은고딕", Font.BOLD, 15);
		
        jlblStuNum.setFont(font);
		jlblStuName.setFont(font);
		jlblStuNumData.setFont(font);
		jlblStuNameData.setFont(font);
		jtStuCourseMgr.setFont(font);
		jbtnclose.setFont(font);
		jbtnShowStuReport.setFont(font);
		jbtnShowExam.setFont(font);
        
		StuCourseMgrDesignEvt scmde = new StuCourseMgrDesignEvt(this);
		jbtnclose.addActionListener(scmde);
		jbtnShowExam.addActionListener(scmde);
		jbtnShowStuReport.addActionListener(scmde);
		addWindowListener(scmde);
		
		
        setBounds(600, 300, 600, 350);
        
        setVisible(true);
    }

	public JButton getJbtnclose() {
		return jbtnclose;
	}

	public JButton getJbtnShowStuReport() {
		return jbtnShowStuReport;
	}

	public JButton getJbtnShowExam() {
		return jbtnShowExam;
	}

}
