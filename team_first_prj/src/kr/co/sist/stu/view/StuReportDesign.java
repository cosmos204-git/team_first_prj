package kr.co.sist.stu.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.stu.controller.StuReportDesignEvt;
import kr.co.sist.stu.service.StuReportService;

public class StuReportDesign extends JDialog {

    private DefaultTableModel dtmStuReport;
    private JTable jtStuReport;
    private JButton jbtnClose;

    private JTextField jtfStuNumData;
    private JTextField jtfStuNameData;

    private int stuNum;

    public StuReportDesign(StuCourseMgrDesign scmd, boolean modal, int stuNum, String stuName) {
        super(scmd, "학생 성적표", modal);
        this.stuNum = stuNum;
        getContentPane().setLayout(new BorderLayout());

        JPanel north = new JPanel(new GridLayout(1, 4, 10, 10));
        north.setBorder(new EmptyBorder(20, 40, 20, 40));

        Font infoFont = new Font("맑은 고딕", Font.BOLD, 15);

        JLabel jlblStuNum = new JLabel("학번", JLabel.CENTER);
        jlblStuNum.setFont(infoFont);
        jlblStuNum.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jtfStuNumData = new JTextField(10);
        jtfStuNumData.setFont(infoFont);
        jtfStuNumData.setEditable(false);
        jtfStuNumData.setHorizontalAlignment(SwingConstants.CENTER);
        jtfStuNumData.setText(String.valueOf(stuNum));

        JLabel jlblStuName = new JLabel("이름", JLabel.CENTER);
        jlblStuName.setFont(infoFont);
        jlblStuName.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jtfStuNameData = new JTextField(10);
        jtfStuNameData.setFont(infoFont);
        jtfStuNameData.setEditable(false);
        jtfStuNameData.setHorizontalAlignment(SwingConstants.CENTER);
        jtfStuNameData.setText(stuName);

        north.add(jlblStuNum);
        north.add(jtfStuNumData);
        north.add(jlblStuName);
        north.add(jtfStuNameData);
        


        String[] columns = {"이름", "과목", "점수", "등수"};
        dtmStuReport = new DefaultTableModel(columns, 0);
        jtStuReport = new JTable(dtmStuReport);
        jtStuReport.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        jtStuReport.setRowHeight(24);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < jtStuReport.getColumnCount(); i++) {
            jtStuReport.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) jtStuReport.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane center = new JScrollPane(jtStuReport);

        jbtnClose = new JButton("닫기");
        jbtnClose.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        //색변경
        jbtnClose.setBackground(new Color(0xE6E6E6));
//        Color btnColor = new Color(0x6A, 0xBD, 0xE5);
//        jbtnClose.setBackground(btnColor);

//        jbtnClose.setForeground(Color.WHITE);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        south.add(jbtnClose);

        StuReportDesignEvt evt = new StuReportDesignEvt(this);
        jbtnClose.addActionListener(evt);
        addWindowListener(evt);

        Color bg = new Color(247, 247, 249);
        getContentPane().setBackground(bg);
        north.setBackground(bg);
        south.setBackground(bg);
        center.getViewport().setBackground(bg);

        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);

        if (scmd != null) setBounds(scmd.getX() + 50, scmd.getY() + 50, 700, 360);
        else setSize(700, 360);
        setLocationRelativeTo(scmd);
        setVisible(true);
    }

    @SuppressWarnings("unused")
    private String fetchCourseName(int stuNum) {
        List<String> courses = new StuReportService().getCoursesByStuNum(stuNum);
        return (courses != null && !courses.isEmpty()) ? courses.get(0) : "과정 미등록";
    }

    public DefaultTableModel getDtmStuReport() { return dtmStuReport; }
    public JButton getJbtnClose() { return jbtnClose; }
    public int getStuNum() { return stuNum; }

    public JTextField getJtfStuNumData() { return jtfStuNumData; }
    public JTextField getJtfStuNameData() { return jtfStuNameData; }
}
