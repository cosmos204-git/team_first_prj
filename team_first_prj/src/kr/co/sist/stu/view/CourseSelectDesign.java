package kr.co.sist.stu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.controller.CourseSelectDesignEvt;

public class CourseSelectDesign extends JDialog { 

    private JPanel jpNorth, jpCenter, jpBottom;
    
    private DefaultTableModel dtmSelectCourse;
    private JTable jtSelectCourse;
    private JScrollPane jspSelectCourse;
    private Date courseStartDate, courseEndDate;
    
    private JLabel jlblStuName, jlblStuNum;
    private JTextField jtfStuNameData, jtfStuNumData;
    private JButton jbtnShowSub, jbtnApplyCourse, jbtnClose;
    
    
    
    public CourseSelectDesign(StuInfoDesign sid, boolean modal) {
        super(sid,"수강 신청",modal); 
        setLayout(new BorderLayout());
        
        CurrentStuData csd = CurrentStuData.getInstance();
        
        int stuNum=csd.getStuNum();
    	String stuName =csd.getStuName();
        
        // North Panel
        jpNorth = new JPanel(new GridLayout(1, 4, 10, 10)); 
        jpNorth.setBorder(new EmptyBorder(15, 30, 15, 30)); 

        jlblStuNum = new JLabel("학번", JLabel.CENTER);
        jtfStuNumData = new JTextField(10);
        jtfStuNumData.setEditable(false);
        jtfStuNumData.setText(String.valueOf(stuNum));
        
        jlblStuName = new JLabel("이름", JLabel.CENTER);
        jtfStuNameData = new JTextField(10);
        jtfStuNameData.setEditable(false);
        jtfStuNameData.setText(stuName);
        
        jlblStuNum.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jlblStuName.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jpNorth.add(jlblStuNum);
        jpNorth.add(jtfStuNumData);
        jpNorth.add(jlblStuName); 
        jpNorth.add(jtfStuNameData);

        // Center Panel
        jpCenter = new JPanel(new BorderLayout()); 
        String[] columnsNames = {"신청 여부", "과정명"};
        dtmSelectCourse = new DefaultTableModel(columnsNames,0);
        jtSelectCourse = new JTable(dtmSelectCourse);

        TableColumnModel tcm = jtSelectCourse.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(40);
        tcm.getColumn(1).setPreferredWidth(120);

        jtSelectCourse.setRowHeight(35);
        jtSelectCourse.setFont(new Font("맑은고딕", Font.PLAIN, 15));

        jspSelectCourse = new JScrollPane(jtSelectCourse);
        jpCenter.add(jspSelectCourse, BorderLayout.CENTER);

        // Bottom Panel
        jpBottom = new JPanel(new GridLayout(1, 3, 10, 10)); 
        jpBottom.setBorder(new EmptyBorder(10, 60, 10, 60)); 

        jbtnShowSub = new JButton("과목보기"); 
        jbtnApplyCourse = new JButton("신청"); 
        jbtnClose = new JButton("닫기");

        jpBottom.add(jbtnShowSub);
        jpBottom.add(jbtnApplyCourse);
        jpBottom.add(jbtnClose);

        
        add(jpNorth, BorderLayout.NORTH);
        add(jpCenter, BorderLayout.CENTER);
        add(jpBottom, BorderLayout.SOUTH);
        
        CourseSelectDesignEvt csde = new CourseSelectDesignEvt(this);
        jbtnShowSub.addActionListener(csde);
        jbtnApplyCourse.addActionListener(csde);
        jbtnClose.addActionListener(csde);
        jtSelectCourse.addMouseListener(csde);
        addWindowListener(csde);
        
        setBounds(600, 300, 500, 350); 
        setResizable(false);
        setVisible(true);
        
    }//CourseSelectDesign



	public DefaultTableModel getDtmSelectCourse() {
		return dtmSelectCourse;
	}


	public JTable getJtSelectCourse() {
		return jtSelectCourse;
	}

	public JLabel getJlblStuName() {
		return jlblStuName;
	}


	public JLabel getJlblStuNum() {
		return jlblStuNum;
	}


	public JTextField getJtfStuNameData() {
		return jtfStuNameData;
	}


	public JTextField getJtfStuNumData() {
		return jtfStuNumData;
	}


	public JButton getJbtnShowSub() {
		return jbtnShowSub;
	}


	public JButton getJbtnApplyCourse() {
		return jbtnApplyCourse;
	}


	public JButton getJbtnClose() {
		return jbtnClose;
	}



	public Date getCourseStartDate() {
		return courseStartDate;
	}



	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}



	public Date getCourseEndDate() {
		return courseEndDate;
	}



	public void setCourseEndDate(Date courseEndDate) {
		this.courseEndDate = courseEndDate;
	}


	
	
	
}//class
