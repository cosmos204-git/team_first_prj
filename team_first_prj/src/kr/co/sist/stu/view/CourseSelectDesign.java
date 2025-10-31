package kr.co.sist.stu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.controller.CourseSelectDesignEvt;

public class CourseSelectDesign extends JDialog { 

    private JPanel jpNorth, jpCenter, jpBottom;
    private DefaultTableCellRenderer setFont_center, setFont_Colnames;
    private DefaultTableModel dtmSelectCourse;
    private JTable jtSelectCourse;
    private JScrollPane jspSelectCourse;
    private Date courseStartDate, courseEndDate;
    
    private JLabel jlblStuName, jlblStuNum;
    private JTextField jtfStuNameData, jtfStuNumData;
    private JButton jbtnShowSub, jbtnApplyCourse, jbtnClose;
    private StuInfoDesign sid;
    
    
    public CourseSelectDesign(StuInfoDesign sid, boolean modal) {
        super(sid,"수강 신청",modal); 
        setLayout(new BorderLayout());
        
        this.sid=sid;
        
        CurrentStuData csd = CurrentStuData.getInstance();
        
        int stuNum=csd.getLogStuDTO().getStuNum();
    	String stuName =csd.getLogStuDTO().getStuName();
     
        // North Panel
        jpNorth = new JPanel(new GridLayout(1, 4, 5, 10)); 
        jpNorth.setBorder(new EmptyBorder(25, 40, 20, 40)); 
        
        Font northPfont = new Font("맑은 고딕", Font.BOLD, 15);
        
        jlblStuNum = new JLabel("학번", JLabel.CENTER);
        jtfStuNumData = new JTextField(10);
        jlblStuNum.setFont(northPfont);
        jtfStuNumData.setFont(northPfont);
        jtfStuNumData.setEditable(false);
        jtfStuNumData.setText(String.valueOf(stuNum));
        jtfStuNumData.setHorizontalAlignment(SwingConstants.CENTER);
        
        jlblStuName = new JLabel("이름", JLabel.CENTER);
        jtfStuNameData = new JTextField(10);
        jlblStuName.setFont(northPfont);
        jtfStuNameData.setFont(northPfont);
        jtfStuNameData.setEditable(false);
        jtfStuNameData.setText(stuName);
        jtfStuNameData.setHorizontalAlignment(SwingConstants.CENTER);
        
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
        jtSelectCourse.setFont(new Font("맑은 고딕", Font.BOLD, 18));


     	// 컬럼명 폰트
        setFont_Colnames = 
                (DefaultTableCellRenderer) jtSelectCourse.getTableHeader().getDefaultRenderer();
        setFont_Colnames.setHorizontalAlignment(SwingConstants.CENTER);

        jtSelectCourse.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 15));
        jtSelectCourse.getTableHeader().setPreferredSize(new Dimension(0, 35)); // 폭 0 = 자동

        
        setFont_center = new DefaultTableCellRenderer();
        setFont_center.setHorizontalAlignment(setFont_center.CENTER);
        jtSelectCourse.getColumnModel().getColumn(0).setCellRenderer(setFont_center);
        jtSelectCourse.getColumnModel().getColumn(1).setCellRenderer(setFont_center);
        
        
        jspSelectCourse = new JScrollPane(jtSelectCourse);
        jpCenter.add(jspSelectCourse, BorderLayout.CENTER);

        // Bottom Panel
        jpBottom = new JPanel(new GridLayout(1, 3, 15, 10)); 
        jpBottom.setBorder(new EmptyBorder(15, 80, 25, 80)); 

        
        jbtnShowSub = new JButton("과목보기"); 
        jbtnApplyCourse = new JButton("신청"); 
        jbtnClose = new JButton("닫기");
        //색변경
        jbtnApplyCourse.setBackground(new Color(0xE6E6E6));
        jbtnClose.setBackground(new Color(0xE6E6E6));
        jbtnShowSub.setBackground(new Color(0xE6E6E6));
        
		
	      DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			centerRenderer.setOpaque(true);
			centerRenderer.setBackground(new Color(0xF8F9FA));
			jtSelectCourse.setDefaultRenderer(Object.class, centerRenderer);
			
			jspSelectCourse.getViewport().setBackground(new Color(0xF8F9FA)); 
			jspSelectCourse.setBorder(new LineBorder(new Color(0x000000), 2));

        
        JPanel jp = new JPanel();
        JPanel jpa = new JPanel();
        jp.setPreferredSize(new Dimension(20,100));
        jpa.setPreferredSize(new Dimension(20,100));
        add("West",jp);
        add("East",jpa);

        Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 15);
        jbtnShowSub.setFont(jbtnFont);
        jbtnApplyCourse.setFont(jbtnFont);
        jbtnClose.setFont(jbtnFont);
        
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
        
        setBounds(600, 300, 600, 350); 
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



	public StuInfoDesign getSid() {
		return sid;
	}




	
	
	
}//class