package kr.co.sist.stu.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import kr.co.sist.stu.controller.StuCourseMgrDesignEvt;
import kr.co.sist.stu.dto.StuCourseMgrDTO;
import kr.co.sist.stu.service.StuCourseMgrService;

public class StuCourseMgrDesign extends JDialog {

    private JButton jbtnclose, jbtnShowStuReport, jbtnShowExam;
    private JTable jtStuCourseMgr;
    private DefaultTableModel dtmStuCourseMgr;
    private JLabel jlblStuNumData, jlblStuNameData;

    public StuCourseMgrDesign(StuInfoDesign sid, boolean modal) {
        super(sid, "학업관리", modal);

        JPanel plStuInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel jlblStuNum = new JLabel("학번 ");
        jlblStuNumData = new JLabel();
        JLabel jlblStuName = new JLabel("이름 ");
        jlblStuNameData = new JLabel();
        plStuInfo.add(jlblStuNum);
        plStuInfo.add(jlblStuNumData);
        plStuInfo.add(jlblStuName);
        plStuInfo.add(jlblStuNameData);
        add(plStuInfo, BorderLayout.NORTH);

        String[] columnNames = {"과정", "과목", "시험시간"};
        dtmStuCourseMgr = new DefaultTableModel(columnNames, 0);
        jtStuCourseMgr = new JTable(dtmStuCourseMgr);
        jtStuCourseMgr.setRowHeight(25);
        add(new JScrollPane(jtStuCourseMgr), BorderLayout.CENTER);

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

        StuCourseMgrDesignEvt evt = new StuCourseMgrDesignEvt(this);
        jbtnclose.addActionListener(evt);
        jbtnShowExam.addActionListener(evt);
        jbtnShowStuReport.addActionListener(evt);
        jtStuCourseMgr.addMouseListener(evt);
        addWindowListener(evt);
        
        evt.viewStuInfo();
        loadTableData();

        setBounds(600, 300, 600, 350);
        setVisible(true);
    }

    private void loadTableData() {
        StuCourseMgrService service = new StuCourseMgrService();
        List<StuCourseMgrDTO> list = service.selectAllCourse();
        if (list != null) {
            for (StuCourseMgrDTO dto : list) {
                String examTime = "시험불가".equals(dto.getExamStart())
                        ? "시험불가"
                        : dto.getExamStart() + " ~ " + dto.getExamEnd();
                dtmStuCourseMgr.addRow(new Object[]{
                        dto.getCourseName(),
                        dto.getSubName(),
                        examTime
                });
            }
        }
    }

    public JTable getJtStuCourseMgr() {
    	return jtStuCourseMgr; 
    	
    }
    public JLabel getjlblStuNumData() {
    	return jlblStuNumData;
    }
    
    
    
    public JLabel getjlblStuNameData() {
    	return jlblStuNameData;
    }
    
    public void setjlblStuNameData(JLabel getjlblStuNameData) {
    	this.jlblStuNameData = getjlblStuNameData;
    }
    
    
    public DefaultTableModel getDtmStuCourseMgr() {
    	return dtmStuCourseMgr; 
    	
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
    public JLabel getJlblStuNumData() {
    	return jlblStuNumData; 
    	
    }
    public JLabel getJlblStuNameData() {
    	return jlblStuNameData; 
    	
    }
}
