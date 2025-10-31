package kr.co.sist.stu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.stu.controller.StuCourseMgrDesignEvt;
import kr.co.sist.stu.dto.StuCourseMgrDTO;
import kr.co.sist.stu.service.StuCourseMgrService;

public class StuCourseMgrDesign extends JDialog {

    private JButton jbtnclose, jbtnShowStuReport, jbtnShowExam;
    private JTable jtStuCourseMgr;
    private DefaultTableModel dtmStuCourseMgr;
    private JTextField jtfStuNumData, jtfStuNameData;

    public StuCourseMgrDesign(StuInfoDesign sid, boolean modal) {
        super(sid, "학업관리", modal);
        setLayout(new BorderLayout());

        JPanel plStuInfo = new JPanel(new GridLayout(1, 4, 10, 10));
        plStuInfo.setBorder(new EmptyBorder(25, 40, 20, 40));

        Font font = new Font("맑은 고딕", Font.BOLD, 15);

        JLabel jlblStuNum = new JLabel("학번", JLabel.CENTER);
        jlblStuNum.setFont(font);
        jtfStuNumData = new JTextField(10);
        jtfStuNumData.setFont(font);
        jtfStuNumData.setEditable(false);
        jtfStuNumData.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel jlblStuName = new JLabel("이름", JLabel.CENTER);
        jlblStuName.setFont(font);
        jtfStuNameData = new JTextField(10);
        jtfStuNameData.setFont(font);
        jtfStuNameData.setEditable(false);
        jtfStuNameData.setHorizontalAlignment(SwingConstants.CENTER);

        jlblStuNum.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jlblStuName.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        plStuInfo.add(jlblStuNum);
        plStuInfo.add(jtfStuNumData);
        plStuInfo.add(jlblStuName);
        plStuInfo.add(jtfStuNameData);

        add(plStuInfo, BorderLayout.NORTH);
        JPanel jp = new JPanel();
        JPanel jpa = new JPanel();
        jp.setPreferredSize(new Dimension(20,100));
        jpa.setPreferredSize(new Dimension(20,100));
        add("West",jp);
        add("East",jpa);
        

        String[] columnNames = {"과정", "과목", "시험시간"};
        dtmStuCourseMgr = new DefaultTableModel(columnNames, 0);
        jtStuCourseMgr = new JTable(dtmStuCourseMgr);
        jtStuCourseMgr.setRowHeight(25);
        JScrollPane jspStuCourseMGR = new JScrollPane(jtStuCourseMgr);
        add(jspStuCourseMGR, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        jbtnclose = new JButton("닫기");
        jbtnShowStuReport = new JButton("성적표");
        jbtnShowExam = new JButton("시험");
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setOpaque(true);
        centerRenderer.setBackground(new Color(0xF8F9FA));
        jtStuCourseMgr.setDefaultRenderer(Object.class, centerRenderer);
        
        jspStuCourseMGR.getViewport().setBackground(new Color(0xF8F9FA)); 
        jspStuCourseMGR.setBorder(new LineBorder(new Color(0x000000), 2));
        
//        Color btnColor = new Color(0x6A, 0xBD, 0xE5);
////        jbtnclose.setBackground(btnColor);
////        jbtnShowStuReport.setBackground(btnColor);
////        jbtnShowExam.setBackground(btnColor);
////
////        jbtnclose.setForeground(Color.WHITE);
////        jbtnShowStuReport.setForeground(Color.WHITE);
////        jbtnShowExam.setForeground(Color.WHITE);
        
        buttonPanel.add(jbtnShowExam);
        buttonPanel.add(jbtnShowStuReport);
        buttonPanel.add(jbtnclose);
        add(buttonPanel, BorderLayout.SOUTH);

        jlblStuNum.setFont(font);
        jlblStuName.setFont(font);
        jtfStuNumData.setFont(font);
        jtfStuNameData.setFont(font);
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
        
        //색변경
        jbtnclose.setBackground(new Color(106,189,229));
        jbtnShowExam.setBackground(new Color(106,189,229));
        jbtnShowStuReport.setBackground(new Color(106,189,229));
        jbtnclose.setForeground(Color.white);
        jbtnShowExam.setForeground(Color.white);
        jbtnShowStuReport.setForeground(Color.white);
        
        evt.viewStuInfo();
        loadTableData();
        
        Color bg = new Color(247, 247, 249);
        getContentPane().setBackground(bg);
        plStuInfo.setBackground(bg);
        buttonPanel.setBackground(bg);
        
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < jtStuCourseMgr.getColumnCount(); i++) {
            jtStuCourseMgr.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) jtStuCourseMgr.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
        
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
    public JTextField getJtfStuNumData() {
    	return jtfStuNumData;
    }
    
    
    
    public JTextField getJtfStuNameData() {
    	return jtfStuNameData;
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

}
