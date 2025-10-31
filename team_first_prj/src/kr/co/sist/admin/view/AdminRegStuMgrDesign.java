package kr.co.sist.admin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import kr.co.sist.admin.controller.AdminRegStuMgrDesignEvt;

public class AdminRegStuMgrDesign extends JDialog {
	
	
	private DefaultComboBoxModel<String> dcbmCoure;
	private JComboBox<String> jcbCourse;
	private JTextField jtfStuNum;
	private JButton jbtnSearchStuNum;
	private DefaultTableModel dtmStudent;
	private JTable jtAdminSubjectMgr;
	
	public AdminRegStuMgrDesign(AdminInfoDesign aid, boolean modal) {
		super(aid,"관리자 - 입과관리",modal);
		
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		
		dcbmCoure = new DefaultComboBoxModel<String>();
		jcbCourse = new JComboBox<String>(dcbmCoure);
		jcbCourse.setPreferredSize(new Dimension(150,20));
		jtfStuNum = new JTextField();
		jtfStuNum.setPreferredSize(new Dimension(100,20));
		JLabel jlblStu = new JLabel("   학번 :  ");
		jlblStu.setFont(font);
		JLabel jlblCorse = new JLabel("과정 :  ");
		jlblCorse.setFont(font);
		jbtnSearchStuNum = new JButton("검색");
		jbtnSearchStuNum.setFont(font);
		
		
		JPanel jpNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jpNorth.add(jlblCorse);
		jpNorth.add(jcbCourse);
		jpNorth.add(jlblStu);
		
		jpNorth.add(jtfStuNum);
		jpNorth.add(jbtnSearchStuNum);
		jpNorth.setFont(font);
		
		
		jcbCourse.setBackground(new Color(0xF8F9FA));
		jbtnSearchStuNum.setBackground(new Color(0xE6E6E6));
		jcbCourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jbtnSearchStuNum.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		String[] columNames = {"과정명","교수명","학생명","학번","등록일"};
		dtmStudent = new DefaultTableModel(columNames,0);
		jtAdminSubjectMgr = new JTable(dtmStudent);
		JScrollPane jsp = new JScrollPane(jtAdminSubjectMgr);
//		jtAdminSubjectMgr.setFont(font);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setOpaque(true);
		centerRenderer.setBackground(new Color(0xF8F9FA));
		
		jtAdminSubjectMgr.setDefaultRenderer(Object.class, centerRenderer);
		jsp.getViewport().setBackground(new Color(0xF8F9FA)); 
		jsp.setBorder(new LineBorder(new Color(0x000000), 2));
		
		
		
		JPanel jpCenter = new JPanel(new BorderLayout());
		jpCenter.add(jsp);
		
		jpCenter.setBorder(new EmptyBorder(5, 10, 10, 10));// top, left, bottom, right 여백
		jpNorth.setBorder(new EmptyBorder(5, 10, 0, 7));// top, left, bottom, right 여백
		
		add("North",jpNorth);
		add("Center",jpCenter);
		
		//리스너 추가 
		AdminRegStuMgrDesignEvt aamd= new AdminRegStuMgrDesignEvt(this);
		jbtnSearchStuNum.addActionListener(aamd);
		aamd.searchAllStuProcess();
		aamd.searchComboProcess();
		addWindowListener(aamd);
		
		
		
		setSize(700,400);
		setLocationRelativeTo(null);
		jpNorth.setBackground(new Color(0xF8F9FA));
		jpCenter.setBackground(new Color(0xF8F9FA));
		getContentPane().setBackground(new Color(0xF8F9FA));
		
		setResizable(false);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}//AdminScoreMrgDesign

	public DefaultComboBoxModel<String> getDcbmCoure() {
		return dcbmCoure;
	}

	public JComboBox<String> getJcbCourse() {
		return jcbCourse;
	}

	public JTextField getJtfStuNum() {
		return jtfStuNum;
	}

	public JButton getJbtnSearchStuNum() {
		return jbtnSearchStuNum;
	}

	public DefaultTableModel getDtmStudent() {
		return dtmStudent;
	}

	public JTable getJtAdminSubjectMgr() {
		return jtAdminSubjectMgr;
	}
	
	
	
//	public static void main(String[] args) {
//		new AdminAllStuMgrDesign();
//	}
	
	
}//class
