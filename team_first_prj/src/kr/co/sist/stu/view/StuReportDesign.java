package kr.co.sist.stu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.stu.controller.StuReportDesignEvt;

public class StuReportDesign extends JDialog {
    private JPanel jpStuReport;
    private DefaultComboBoxModel<String> dcbmCourse;
    private JComboBox<String> jcbCourse;
    private JButton jbtnClose;
    private StuCourseMgrDesign scmd;
    
    public StuReportDesign(StuCourseMgrDesign scmd, boolean modal) {
    	super(scmd, "학생 성적표", modal);
    	
    	
    	dcbmCourse = new DefaultComboBoxModel<>();
    	dcbmCourse.addElement("---과정선택---");
    	dcbmCourse.addElement("Full stack");
    	dcbmCourse.addElement("AI");
    	
    	jcbCourse = new JComboBox<>(dcbmCourse);
    	
    	jpStuReport = new JPanel(new GridLayout(1,4));
    	jpStuReport.setBorder(new EmptyBorder(20,40,20,40));
    	
    	JLabel jlblName = new JLabel("이름", JLabel.CENTER);
    	JLabel jlblStuName = new JLabel("", JLabel.CENTER);
    	JLabel jlblCourse = new JLabel("과정", JLabel.CENTER);
    	
    	
    	jlblName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    	jlblStuName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    	jlblCourse.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    	jcbCourse.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    	
    	jpStuReport.add(jlblName);
    	jpStuReport.add(jlblStuName);
    	jpStuReport.add(jlblCourse);
    	jpStuReport.add(jcbCourse);
    	
    	
    	String[] columnNames = {"과목","점수","등수"};
    	String[][] rowData = {
    			{"1","박상현","25"},
    			{"2","나경원","26"},
    			{"3","최승준","24"},
    			{"4","신승덕","25"},
    			{"5","신승덕","25"},
    			{"6","신승덕","25"},
    			{"7","신승덕","25"},
    			{"8","신승덕","25"},
    			{"9","신승덕","25"},
    	};
    	DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);
    	JTable jtStuReport = new JTable(dtm);
    	jtStuReport.setRowHeight(25);
    	
    	JScrollPane jspStuReport = new JScrollPane(jtStuReport);
    	
    	jbtnClose = new JButton("닫기");
    	    	
    	Font font = new Font("맑은고딕", Font.BOLD, 15);
    	
    	jlblName.setFont(font);
    	jlblStuName.setFont(font);
    	jlblCourse.setFont(font);
    	jcbCourse.setFont(font);
    	jbtnClose.setFont(font);
    	
    	JPanel buttonPannel =new JPanel();
    	buttonPannel.add(jbtnClose);
    	
    	add(jpStuReport, BorderLayout.NORTH);
    	add(jspStuReport, BorderLayout.CENTER);
    	add(buttonPannel,BorderLayout.SOUTH);
    	
    	StuReportDesignEvt srde = new StuReportDesignEvt(this);
    	jbtnClose.addActionListener(srde);
    	addWindowListener(srde);
    	
    	setBounds(scmd.getX()+50, scmd.getY()+50, 600, 300);
    	setVisible(true);
    	
    }

	public JButton getJbtnClose() {
		return jbtnClose;
	}


    

}
