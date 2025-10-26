package kr.co.sist.admin.view;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
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
		jlblCourseInputdateData = new JLabel();
		jlblSubNumdata = new JLabel();
		
		
		
		jbtnMgrTestExam = new JButton("시험지 보기/추가");
		jbtnClose = new JButton("닫기");
		jbtnDelete = new JButton("삭제");
		jbtnAdd = new JButton("추가");
		
		dcbmCourse = new DefaultComboBoxModel<String>();
		jcbCourse = new JComboBox<String>(dcbmCourse);
		
		dcbmSub = new DefaultComboBoxModel<String>();
		jcbSub = new JComboBox<String>(dcbmSub);
		
		String[] columNames = {"과목코드","과목명","등록일"};
		dtmAdminSubMgr = new DefaultTableModel(columNames,0	);
		jtAdminSubMgr = new JTable(dtmAdminSubMgr);
		JScrollPane jsp = new JScrollPane(jtAdminSubMgr);

		//리스너 추가 
		AdminSubjectMgrDesignEvt asmde = new AdminSubjectMgrDesignEvt(this);
		asmde.searchCourseProcess();
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
		jlblCourseName.setBounds(30,50,70,20);
		jlblSubName.setBounds(30,85,70,20);
		jlblSubNum.setBounds(30,120,80,20);
		jlblCourseInputdate.setBounds(30,155,70,20);
		jlblCourseInputdateData.setBounds(90,155,140,20);
		jlblSubNumdata.setBounds(100,120,100,20);
		
		jcbCourse.setBounds(90,50,100,20);
		jcbSub.setBounds(90,85,100,20);
		
		
		jbtnMgrTestExam.setBounds(180,210,130,30);
		jbtnAdd.setBounds(320,210,60,30);
		jbtnDelete.setBounds(390,210,60,30);
		jbtnClose.setBounds(460,210,60,30);
		
		jsp.setBounds(230,30,300,170);
		
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
		
		
		setSize(600,300);
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
