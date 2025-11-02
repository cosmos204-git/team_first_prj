package kr.co.sist.admin.view;

import java.awt.BorderLayout;
import java.awt.Color;
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

import kr.co.sist.admin.controller.AdminScoreMgrDesignEvt;

public class AdminScoreMgrDesign extends JDialog {
	private DefaultTableModel dtmAdminScoreMgr ; 
	private JTable jtAdminScoreMgr;
	private DefaultComboBoxModel<String> dcbmSub , dcbmCourse;
	private JComboBox<String> jcbSub , jcbCourse;
	private JTextField jtfStuNum;
	private JButton jbtnSearchStuNum;
	private JScrollPane jsp ;
	
	public AdminScoreMgrDesign(AdminInfoDesign aid, boolean modal) {
		super(aid,"관리자 - 성적 관리",modal);
		
		Font font = new Font("맑은 고딕" , Font.BOLD,15);
		
		String[] columNames = {"학번", "학생명","과목명","점수","등수"};
		dtmAdminScoreMgr = new DefaultTableModel(columNames,0);
		jtAdminScoreMgr = new JTable(dtmAdminScoreMgr);
		jsp = new JScrollPane(jtAdminScoreMgr);
		
//		jtAdminScoreMgr.setFont(font);
		dcbmCourse = new DefaultComboBoxModel<String>();
		
		jcbCourse = new JComboBox<String>(dcbmCourse);
		jcbCourse.setPreferredSize(new Dimension(140,20));
		dcbmSub = new DefaultComboBoxModel<String>();
		jcbSub = new JComboBox<String>(dcbmSub);
		jcbSub.setPreferredSize(new Dimension(100,20));
		
		JLabel jlblstuCode = new JLabel("          학번 :");
		JLabel jlblCourseName = new JLabel("과정명 :");
		JLabel jlblSubName = new JLabel("  과목명 :");
		jlblstuCode.setFont(font);
		jlblCourseName.setFont(font);
		jlblSubName.setFont(font);
		jtfStuNum = new JTextField();
		jtfStuNum.setPreferredSize(new Dimension(120,20));
		jbtnSearchStuNum = new JButton("검색");
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setOpaque(true);
		centerRenderer.setBackground(new Color(0xF8F9FA));
		
		jtAdminScoreMgr.setDefaultRenderer(Object.class, centerRenderer);
		jsp.getViewport().setBackground(new Color(0xF8F9FA)); 
		jsp.setBorder(new LineBorder(new Color(0x000000), 2));
		
		
		jbtnSearchStuNum.setFont(font);
		jcbCourse.setBackground(new Color(0xF8F9FA));
		jbtnSearchStuNum.setBackground(new Color(0xE6E6E6));
		jcbSub.setBackground(new Color(0xF8F9FA));
		jcbCourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbSub.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jbtnSearchStuNum.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JPanel jpNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jpNorth.add(jlblCourseName);
		jpNorth.add(jcbCourse);
		jpNorth.add(jlblSubName);
		jpNorth.add(jcbSub);
		jpNorth.add(jlblstuCode);
		jpNorth.add(jtfStuNum);
		jpNorth.add(jbtnSearchStuNum);
		
		JPanel jpCenter = new JPanel(new BorderLayout());
		jpCenter.add(jsp);
		
		jpCenter.setBorder(new EmptyBorder(5, 10, 10, 10));// top, left, bottom, right 여백
		jpNorth.setBorder(new EmptyBorder(5, 10, 0, 7));// top, left, bottom, right 여백
		
		add("North",jpNorth);
		add("Center",jpCenter);
		
		//리스너 추가 
		AdminScoreMgrDesignEvt  asmde = new AdminScoreMgrDesignEvt(this);
		jbtnSearchStuNum.addActionListener(asmde);
		jcbCourse.addActionListener(asmde);
		addWindowListener(asmde);
		asmde.searchCourseProcess();
		asmde.seacrhAllScore();
		
		
		setSize(700,400);
		setLocationRelativeTo(null);
		jpNorth.setBackground(new Color(0xF8F9FA));
		jpCenter.setBackground(new Color(0xF8F9FA));
		getContentPane().setBackground(new Color(0xF8F9FA));
		
		setResizable(false);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//AdminScoreMrgDesign
	

	
	public DefaultTableModel getDtmAdminScoreMgr() {
		return dtmAdminScoreMgr;
	}



	public JTable getJtAdminScoreMgr() {
		return jtAdminScoreMgr;
	}



	public DefaultComboBoxModel<String> getDcbmSub() {
		return dcbmSub;
	}



	public DefaultComboBoxModel<String> getDcbmCourse() {
		return dcbmCourse;
	}



	public JComboBox<String> getJcbSub() {
		return jcbSub;
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



	public JScrollPane getJsp() {
		return jsp;
	}



	public static void main(String[] args) {
		
	}
	
}//class
