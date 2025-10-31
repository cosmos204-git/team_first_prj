package kr.co.sist.stu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import kr.co.sist.stu.controller.ShowSubjectDialogEvt;

public class ShowSubjectDialog extends JDialog {
	
	private DefaultTableModel dtmShowSubDialog;
	private DefaultTableCellRenderer setFont_center;
	private JTable jtShowSubDialog;
	private JButton jbtnClose;
	
	private int courseCode;
	private String courseName;
	

	
    public ShowSubjectDialog(CourseSelectDesign csd, boolean modal, int courseCode, String courseName) {
        super(csd, courseName, modal);
        this.courseCode = courseCode;
        this.courseName = courseName;
        initLayout(csd);
    }
    
    
    
    private void initLayout(CourseSelectDesign csd) {
    
		setLayout(new BorderLayout(10,10));
		
		//JTable
		String[] columnNames = {"상세 과목"};
		dtmShowSubDialog = new DefaultTableModel(columnNames, 0);
		jtShowSubDialog = new JTable(dtmShowSubDialog);
		jtShowSubDialog.setRowHeight(25);
		jtShowSubDialog.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		
		JScrollPane jspTable = new JScrollPane(jtShowSubDialog);
		
        JTableHeader header = jtShowSubDialog.getTableHeader();
        header.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        header.setPreferredSize(new Dimension(0, 30));
        setFont_center = (DefaultTableCellRenderer) header.getDefaultRenderer();
        setFont_center.setHorizontalAlignment(SwingConstants.CENTER);
        
        
		setFont_center = new DefaultTableCellRenderer();
		setFont_center.setHorizontalAlignment(SwingConstants.CENTER);
        jtShowSubDialog.getColumnModel().getColumn(0).setCellRenderer(setFont_center);
        
		JPanel jpCenter = new JPanel(new BorderLayout());
		jpCenter.add(jspTable, BorderLayout.CENTER);
		jpCenter.setBorder(new EmptyBorder(15, 20, 0, 20)); 
		
		jbtnClose = new JButton("닫기");
		JPanel jpSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jpSouth.add(jbtnClose);
		jpSouth.setBorder(new EmptyBorder(0, 0, 10, 10));
		
		add(jpCenter, BorderLayout.CENTER);
		add(jpSouth, BorderLayout.SOUTH);

		ShowSubjectDialogEvt ssde = new ShowSubjectDialogEvt(this);
		jbtnClose.addActionListener(ssde);
		addWindowListener(ssde);
		
		//색변경
	    jbtnClose.setBackground(new Color(0xE6E6E6));

		
		
		
		setBounds(csd.getX()+50,csd.getY()+50,300,300);
		setResizable(false);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}//ShowSubjectDialog




	public DefaultTableModel getDtmShowSubDialog() {
		return dtmShowSubDialog;
	}


	public JTable getJtShowSubDialog() {
		return jtShowSubDialog;
	}


	public JButton getJbtnClose() {
		return jbtnClose;
	}



	public int getCourseCode() {
		return courseCode;
	}



	public String getCourseName() {
		return courseName;
	}
	
	

}//class