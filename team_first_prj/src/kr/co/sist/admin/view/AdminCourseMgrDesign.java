package kr.co.sist.admin.view;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.admin.controller.AdminCourseMgrDesignEvt;

public class AdminCourseMgrDesign extends JDialog {
	private DefaultTableModel dtmAdminCouresMgr;
	private JTable jtAdminCourseMgr;
	private JLabel jlblCourseName ,jlblCourseCode ,jlblCourseCodeData ,jlblProfName  , 
	jlblStartdate ,jlblEnddate ,jlblInputdata ,jlblInputdataData,jlblProfNum,jlblProfNumData;
	

	
	private JTextField jtfCourseName,jtfSearchCourse;
	private DefaultComboBoxModel<String> dcbmProfName, dcbmStartdateY,dcbmStartdateM,dcbmStartdateD, 
	dcbmEnddateY,dcbmEnddateM,dcbmEnddateD;
	private JComboBox<String> jcbProfName, jcbStartdateY,jcbStartdateM,jcbStartdateD, 
	jcbEnddateY,jcbEnddateM,jcbEnddateD;
	private JButton jbtnModify,jbtnAdd,jbtnDelete,jbtnClose,jbtnSearch;   
		
	public AdminCourseMgrDesign(AdminInfoDesign aid, boolean modal) {
		super(aid,"관리자 - 과정 관리",modal);

	Font font = new Font("맑은 고딕",Font.BOLD,15);
	UIManager.put("Label.font", font);
	
	JLabel jlblCourse = new JLabel("과정명 :");
	
	jlblCourseCode = new JLabel("과목코드 :");
	jlblCourseName = new JLabel("과정명 :");
	jlblProfName = new JLabel("교수 :");
	
	jlblProfNum = new JLabel("교번 :");
	jlblProfNumData = new JLabel("");
	
	jlblStartdate = new JLabel("시작일 :");
	jlblEnddate = new JLabel("종료일 :");
	jlblInputdata = new JLabel("등록일 :");
	
	LocalDate today = LocalDate.now();
	
	jlblInputdataData= new JLabel(today.toString());
	
	jlblCourseCodeData = new JLabel("");
	
	jtfCourseName = new JTextField("");
	jtfSearchCourse = new JTextField("");
		
	String[] years = "2025,2026,2027,2028,2029,2030".split(",");
	String[] months = "01,02,03,04,05,06,07,08,09,10,11,12".split(",");
	String[] days = new String[31];
	for(int i = 0; i<31 ; i++) {
		days[i]= i+1+"";
		if(days[i].length()==1) {
			days[i]= "0"+days[i];
		}//end if

	}//end for 
	dcbmProfName = new DefaultComboBoxModel<String>();
	
	
	dcbmStartdateY = new DefaultComboBoxModel<String>(years);
	dcbmStartdateM = new DefaultComboBoxModel<String>(months);
	dcbmStartdateD = new DefaultComboBoxModel<String>(days);
	
	dcbmEnddateY = new DefaultComboBoxModel<String>(years);
	dcbmEnddateM = new DefaultComboBoxModel<String>(months);
	dcbmEnddateD = new DefaultComboBoxModel<String>(days);
	
	
	jcbProfName = new JComboBox<String>(dcbmProfName);
	
	jcbStartdateY = new JComboBox<String>(dcbmStartdateY);
	jcbStartdateM = new JComboBox<String>(dcbmStartdateM);
	jcbStartdateD = new JComboBox<String>(dcbmStartdateD);

	jcbEnddateY = new JComboBox<String>(dcbmEnddateY);
	jcbEnddateM = new JComboBox<String>(dcbmEnddateM);
	jcbEnddateD = new JComboBox<String>(dcbmEnddateD);

	
	String[] columNames = "과정코드,과정명,교번,교수,시작일,종료일,등록일".split(",");
	dtmAdminCouresMgr = new DefaultTableModel(columNames,0);
	jtAdminCourseMgr = new JTable(dtmAdminCouresMgr);
	
	TableColumnModel tcm = jtAdminCourseMgr.getColumnModel();
	tcm.getColumn(0).setPreferredWidth(40);
	tcm.getColumn(1).setPreferredWidth(110);
	tcm.getColumn(2).setPreferredWidth(20);
	tcm.getColumn(3).setPreferredWidth(50);
	tcm.getColumn(4).setPreferredWidth(60);
	tcm.getColumn(5).setPreferredWidth(60);
	tcm.getColumn(6).setPreferredWidth(60);

	JScrollPane jsp = new JScrollPane(jtAdminCourseMgr);
	
	// 행의 높이
	jtAdminCourseMgr.setRowHeight(20);

	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	centerRenderer.setOpaque(true);
	centerRenderer.setBackground(new Color(0xF8F9FA));
	
	jtAdminCourseMgr.setDefaultRenderer(Object.class, centerRenderer);
	jsp.getViewport().setBackground(new Color(0xF8F9FA)); 
	jsp.setBorder(new LineBorder(new Color(0x000000), 2));
	
	

	
	jbtnModify = new JButton("수정");
	jbtnAdd = new JButton("과정추가");
	jbtnDelete = new JButton("삭제");
	jbtnClose = new JButton("닫기");
	
	jbtnSearch = new JButton("검색");
	
	jbtnModify.setFont(font);
	jbtnAdd.setFont(font);
	jbtnDelete.setFont(font);
	jbtnClose.setFont(font);
	jbtnSearch.setFont(font);
	
	jbtnModify.setBackground(new Color(0xE6E6E6));
	jbtnAdd.setBackground(new Color(0xE6E6E6));
	jbtnDelete.setBackground(new Color(0xE6E6E6));
	jbtnClose.setBackground(new Color(0xE6E6E6));
	jbtnSearch.setBackground(new Color(0xE6E6E6));
	
	
	jcbProfName.setBackground(new Color(0xF8F9FA));
	jcbStartdateM.setBackground(new Color(0xF8F9FA));
	jcbStartdateD.setBackground(new Color(0xF8F9FA));
	jcbEnddateY.setBackground(new Color(0xF8F9FA));
	jcbEnddateM.setBackground(new Color(0xF8F9FA));
	jcbEnddateD.setBackground(new Color(0xF8F9FA));
	
	
	
	jlblCourseCode.setBounds(30,90,70,20);
	jlblCourseName.setBounds(30,jlblCourseCode.getY()+40,70,20);
	
	jlblProfNum.setBounds(30,jlblCourseName.getY()+40,70,20);
	
	jlblProfName.setBounds(30,jlblProfNum.getY()+40,70,20);
	jlblStartdate.setBounds(30,jlblProfName.getY()+40,70,20);
	jlblEnddate.setBounds(30,jlblStartdate.getY()+40,70,20);
	jlblInputdata.setBounds(30,jlblEnddate.getY()+40,70,20);
	
	jlblCourseCodeData.setBounds(105,jlblCourseCode.getY(),70,20);
	jlblProfNumData.setBounds(105,jlblProfNum.getY(),70,20);
	jlblInputdataData.setBounds(105,331,100,20);

	
	
	jtfCourseName.setBounds(105,jlblCourseName.getY(),100,20);
	jtfSearchCourse.setBounds(552,19,150,30);
	
	jcbProfName.setBounds(105,jlblProfName.getY(),106,20);
	
	jcbStartdateY.setBounds(105,jlblStartdate.getY()+2,60,20);
	jcbStartdateM.setBounds(170,jlblStartdate.getY()+2,40,20);
	jcbStartdateD.setBounds(215,jlblStartdate.getY()+2,40,20);
	
	jcbEnddateY.setBounds(105,jlblEnddate.getY(),60,20);
	jcbEnddateM.setBounds(170,jlblEnddate.getY(),40,20);
	jcbEnddateD.setBounds(215,jlblEnddate.getY(),40,20);
	
	
	
	jsp.setBounds(297,60,500,320);
	jbtnClose.setBounds(660,390,80,30);
	jbtnDelete.setBounds(570,390,80,30);
	jbtnAdd.setBounds(455,390,105,30);
	jbtnModify.setBounds(360,390,85,30);
	
	jbtnSearch.setBounds(711,18,85,30);
	
	jlblCourse.setBounds(jbtnAdd.getX()+33,16,85,30);
	
	add(jlblCourse);
	add(jlblCourseCodeData);
	add(jtfCourseName);
	add(jtfSearchCourse);
	add(jlblInputdataData);
	add(jcbProfName);
	add(jlblCourseCode);
	add(jlblCourseName);
	add(jlblProfName);
	add(jlblProfNum);
	add(jlblProfNumData);
	
	add(jlblStartdate);
	add(jlblEnddate);
	add(jlblInputdata);
	add(jcbStartdateD);
	add(jcbStartdateM);
	add(jcbStartdateY);
	add(jcbEnddateD);
	add(jcbEnddateM);
	add(jcbEnddateY);
	add(jsp);
	add(jbtnClose);
	add(jbtnAdd);
	add(jbtnModify);
	add(jbtnDelete);
	add(jbtnSearch);
	setLayout(null);
	
	//리스너 추가 
	AdminCourseMgrDesignEvt acmde= new AdminCourseMgrDesignEvt(this);
	if(dcbmProfName.getSize()<2) {
		JOptionPane.showMessageDialog(this, "교수가 존재하지 않습니다\n교수를 먼저 추가해주세요.");
		return;
	}//end if 
	jbtnSearch.addActionListener(acmde);
	jbtnModify.addActionListener(acmde);
	jbtnAdd.addActionListener(acmde);
	jbtnDelete.addActionListener(acmde);
	jbtnClose.addActionListener(acmde);
	jtAdminCourseMgr.addMouseListener(acmde);
	jcbProfName.addItemListener(acmde);
	jcbStartdateM.addItemListener(acmde);
	jcbEnddateM.addItemListener(acmde);
	addWindowListener(acmde);
	
	setSize(820,500);
	getContentPane().setBackground(new Color(0xF8F9FA));
	setLocationRelativeTo(null);
	setResizable(false);
	setVisible(true);
//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}//AdminCourseMgrDesign


	public DefaultTableModel getDtmAdminCouresMgr() {
		return dtmAdminCouresMgr;
	}

	public JTable getJtAdminCourseMgr() {
		return jtAdminCourseMgr;
	}

	public JLabel getJlblCourseName() {
		return jlblCourseName;
	}

	public JLabel getJlblCourseCode() {
		return jlblCourseCode;
	}

	public JLabel getJlblCourseCodeData() {
		return jlblCourseCodeData;
	}

	public JLabel getJlblProfName() {
		return jlblProfName;
	}

	public JLabel getJlblStartdate() {
		return jlblStartdate;
	}

	public JLabel getJlblEnddate() {
		return jlblEnddate;
	}

	public JLabel getJlblInputdata() {
		return jlblInputdata;
	}

	public JLabel getJlblInputdataData() {
		return jlblInputdataData;
	}

	public JLabel getJlblProfNum() {
		return jlblProfNum;
	}

	public JLabel getJlblProfNumData() {
		return jlblProfNumData;
	}

	public JTextField getJtfCourseName() {
		return jtfCourseName;
	}

	public JTextField getJtfSearchCourse() {
		return jtfSearchCourse;
	}

	public DefaultComboBoxModel<String> getDcbmProfName() {
		return dcbmProfName;
	}

	public DefaultComboBoxModel<String> getDcbmStartdateY() {
		return dcbmStartdateY;
	}

	public DefaultComboBoxModel<String> getDcbmStartdateM() {
		return dcbmStartdateM;
	}

	public DefaultComboBoxModel<String> getDcbmStartdateD() {
		return dcbmStartdateD;
	}

	public DefaultComboBoxModel<String> getDcbmEnddateY() {
		return dcbmEnddateY;
	}

	public DefaultComboBoxModel<String> getDcbmEnddateM() {
		return dcbmEnddateM;
	}

	public DefaultComboBoxModel<String> getDcbmEnddateD() {
		return dcbmEnddateD;
	}

	public JComboBox<String> getJcbProfName() {
		return jcbProfName;
	}

	public JComboBox<String> getJcbStartdateY() {
		return jcbStartdateY;
	}

	public JComboBox<String> getJcbStartdateM() {
		return jcbStartdateM;
	}

	public JComboBox<String> getJcbStartdateD() {
		return jcbStartdateD;
	}

	public JComboBox<String> getJcbEnddateY() {
		return jcbEnddateY;
	}

	public JComboBox<String> getJcbEnddateM() {
		return jcbEnddateM;
	}

	public JComboBox<String> getJcbEnddateD() {
		return jcbEnddateD;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
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

	public JButton getJbtnSearch() {
		return jbtnSearch;
	}
	

	
	
	
}//class
