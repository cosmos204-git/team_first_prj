package kr.co.sist.stu.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import kr.co.sist.stu.controller.StuReportDesignEvt;
import kr.co.sist.stu.service.StuReportService;

public class StuReportDesign extends JDialog {

    private DefaultTableModel dtmStuReport;
    private JTable jtStuReport;
    private JButton jbtnClose;
    private JLabel jlblStuName;
    private JComboBox<String> jcbSub;
    private int stuNum;

    public StuReportDesign(StuCourseMgrDesign scmd, boolean modal, int stuNum, String stuName) {
        super(scmd, "학생 성적표", modal);
        this.stuNum = stuNum;

        JPanel north = new JPanel(new GridLayout(1, 4));
        north.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel jlblName = new JLabel("이름", JLabel.CENTER);
        jlblStuName = new JLabel(stuName, JLabel.CENTER);
        JLabel jlblCourse = new JLabel("과정", JLabel.CENTER);
        jcbSub = new JComboBox<>(new DefaultComboBoxModel<>(new String[]{"---과목선택---"}));

        List<String> subs = new StuReportService().getCoursesByStuNum(stuNum);
        if (subs != null) {
            for (String s : subs) jcbSub.addItem(s);
        }

        jlblName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jlblStuName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jlblCourse.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jcbSub.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        north.add(jlblName);
        north.add(jlblStuName);
        north.add(jlblCourse);
        north.add(jcbSub);

        String[] columns = {"이름", "과목", "점수", "등수"};
        dtmStuReport = new DefaultTableModel(columns, 0);
        jtStuReport = new JTable(dtmStuReport);
        JScrollPane center = new JScrollPane(jtStuReport);

        jbtnClose = new JButton("닫기");
        JPanel south = new JPanel();
        south.add(jbtnClose);

        Font font = new Font("맑은고딕", Font.BOLD, 15);
        jlblName.setFont(font);
        jlblStuName.setFont(font);
        jlblCourse.setFont(font);
        jcbSub.setFont(font);
        jbtnClose.setFont(font);
        jtStuReport.setFont(font);

        StuReportDesignEvt evt = new StuReportDesignEvt(this);
        jbtnClose.addActionListener(evt);
        addWindowListener(evt);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);

        if (scmd != null) setBounds(scmd.getX() + 50, scmd.getY() + 50, 700, 360);
        else setSize(700, 360);
        setLocationRelativeTo(scmd);
        setVisible(true);
    }

    public DefaultTableModel getDtmStuReport() {
    	return dtmStuReport; 
    	
    }
    public JButton getJbtnClose() {
    	return jbtnClose; 
    	
    }
    public JComboBox<String> getJcbStuSub() {
    	return jcbSub; 
    	
    }
    public int getStuNum() {
    	return stuNum; 
    	
    }
}
