package kr.co.sist.admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.controller.AdminSubjectMgrDesignEvt;

public class AdminSubjectMgrDesign extends JDialog {
	private DefaultTableModel dtmAdminSubMgr;
	private JTable jtAdminSubMgr;
	private JLabel jlblCourseName,jlblSubNum,jlblSubNumdata,jlblSubName,jlblCourseInputdate
	,jlblCourseInputdateData;
	
	private JButton jbtnMgrTestExam,jbtnAdd,jbtnDelete,jbtnClose;
	private DefaultComboBoxModel<String> dcbmCourse,dcbmSub;
	private JComboBox<String> jcbCourse,jcbSub;
	
	public AdminSubjectMgrDesign() {
		
	}
	public AdminSubjectMgrDesign(AdminInfoDesign aid, boolean modal) {
		
		super(aid,"관리자 - 과목 관리",modal);
		
		Font font = new Font("맑은 고딕", Font.BOLD,15);
		UIManager.put("Label.font", font);
		
		jlblCourseName = new JLabel("과정명 : ");
		jlblSubName = new JLabel("과목명 : ");
		jlblSubNum = new JLabel("과목코드 : ");
		jlblCourseInputdate = new JLabel("등록일 : ");
		jlblCourseInputdateData = new JLabel("");
		jlblSubNumdata = new JLabel("");
		
		
		
		jbtnMgrTestExam = new JButton("시험지 보기/추가");
		jbtnClose = new JButton("닫기");
		jbtnDelete = new JButton("삭제");
		jbtnAdd = new JButton("추가");
		
		dcbmCourse = new DefaultComboBoxModel<String>();
		jcbCourse = new JComboBox<String>(dcbmCourse);
		jcbCourse.setPreferredSize(new Dimension(100,20));
		
		dcbmSub = new DefaultComboBoxModel<String>();
		jcbSub = new JComboBox<String>(dcbmSub);
		jcbSub.setPreferredSize(new Dimension(100,20));
		
		String[] columNames = {"과목코드","과목명","등록일"};
		dtmAdminSubMgr = new DefaultTableModel(columNames,0	);
		jtAdminSubMgr = new JTable(dtmAdminSubMgr);
		JScrollPane jsp = new JScrollPane(jtAdminSubMgr);

		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setOpaque(true);
		centerRenderer.setBackground(new Color(0xF8F9FA));
		
		jtAdminSubMgr.setDefaultRenderer(Object.class, centerRenderer);
		jsp.getViewport().setBackground(new Color(0xF8F9FA)); 
		jsp.setBorder(new LineBorder(new Color(0x000000), 2));
		
		
		//리스너 추가 
		AdminSubjectMgrDesignEvt asmde = new AdminSubjectMgrDesignEvt(this);
		asmde.searchCourseProcess();
		if(dcbmCourse.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(jsp, "과정이 존재하지 않습니다\n과정을 먼저 추가해주세요.");
			return;
		}
		asmde.searchSubProcess();
		asmde.searchCourseSub();
		jbtnMgrTestExam.addActionListener(asmde);
		jbtnClose.addActionListener(asmde);
		jbtnDelete.addActionListener(asmde);
		jbtnAdd.addActionListener(asmde);
		jcbCourse.addActionListener(asmde);
		jcbSub.addActionListener(asmde);
		
		addWindowListener(asmde);
		
		
		setLayout(null);
		jlblCourseName.setBounds(22,75,70,20);
		jlblSubName.setBounds(jlblCourseName.getX(),jlblCourseName.getY()+50,70,20);
		jlblSubNum.setBounds(jlblCourseName.getX(),jlblSubName.getY()+50,80,20);
		jlblCourseInputdate.setBounds(jlblCourseName.getX(),jlblSubNum.getY()+50,70,20);
		
		
//		
		jlblCourseInputdateData.setBounds(120,jlblCourseInputdate.getY()+3,140,20);
		jlblSubNumdata.setBounds(120,jlblSubNum.getY()+3,100,20);
		
		jcbCourse.setBounds(120,jlblCourseName.getY()+3,130,20);
		jcbSub.setBounds(120,jlblSubName.getY()+3,130,20);
//		
		
		jbtnMgrTestExam.setBounds(285,314,140,30);
		jbtnAdd.setBounds(jbtnMgrTestExam.getX()+150,jbtnMgrTestExam.getY(),60,30);
		jbtnDelete.setBounds(jbtnAdd.getX()+80,jbtnMgrTestExam.getY(),60,30);
		jbtnClose.setBounds(jbtnDelete.getX()+80,jbtnMgrTestExam.getY(),60,30);
		
		jsp.setBounds(280,40,380,260);
		
		add(jlblCourseName);
		add(jlblSubName);
		add(jlblSubNum);
		add(jlblCourseInputdate);
		add(jlblCourseInputdateData);
		add(jlblSubNumdata);
		add(jcbCourse);
		add(jcbSub);
		add(jsp);
		add(jbtnMgrTestExam);
		add(jbtnAdd);
		add(jbtnDelete);
		add(jbtnClose);
		
		
		setSize(700,400);//280.25
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD,13);
		jbtnMgrTestExam.setFont(jbtnFont);
		jbtnAdd.setFont(jbtnFont);
		jbtnDelete.setFont(jbtnFont);
		jbtnClose.setFont(jbtnFont);
		
		jbtnMgrTestExam.setBackground(new Color(0xE6E6E6));
		jbtnAdd.setBackground(new Color(0xE6E6E6));
		jbtnDelete.setBackground(new Color(0xE6E6E6));
		jbtnClose.setBackground(new Color(0xE6E6E6));
		jcbCourse.setBackground(new Color(0xF8F9FA));
		jcbSub.setBackground(new Color(0xF8F9FA));
		
		getContentPane().setBackground(new Color(0xF8F9FA));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}//AdminSubjectMgrDesign
	public DefaultTableModel getDtmAdminSubMgr() {
		return dtmAdminSubMgr;
	}
	public JTable getJtAdminSubMgr() {
		return jtAdminSubMgr;
	}
	public JLabel getJlblCourseName() {
		return jlblCourseName;
	}
	public JLabel getJlblSubNum() {
		return jlblSubNum;
	}
	public JLabel getJlblSubNumdata() {
		return jlblSubNumdata;
	}
	public JLabel getJlblSubName() {
		return jlblSubName;
	}
	public JLabel getJlblCourseInputdate() {
		return jlblCourseInputdate;
	}
	public JLabel getJlblCourseInputdateData() {
		return jlblCourseInputdateData;
	}
	public JButton getJbtnMgrTestExam() {
		return jbtnMgrTestExam;
	}
	public JButton getJbtnAdd() {
		return jbtnAdd;
	}
	public JButton getJbtnDelete() {
		return jbtnDelete;
	}
	public JButton getJbtnClose() {
		return jbtnClose;
	}
	public DefaultComboBoxModel<String> getDcbmCourse() {
		return dcbmCourse;
	}
	public DefaultComboBoxModel<String> getDcbmSub() {
		return dcbmSub;
	}
	public JComboBox<String> getJcbCourse() {
		return jcbCourse;
	}
	public JComboBox<String> getJcbSub() {
		return jcbSub;
	}
	

}//class
