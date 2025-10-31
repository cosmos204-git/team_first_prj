package kr.co.sist.prof.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.prof.controller.ProfScoreMgrDesignEvt;

public class ProfScoreMgrDesign extends JDialog{
	
	private JComboBox<String> jcbProfCourse;
	private DefaultComboBoxModel<String> dcbmProfCourse;
	private JComboBox<String> jcbProfSub;
	private DefaultComboBoxModel<String> dcbmProfSub;
	private JTable jtProfScoreMgr;
	private DefaultTableModel dtmProfScoreMgr;
	private JTextField jtfStuNum;
	private JButton jbtnSearchStuNum;
	private JButton jbtnShowStuReportInfo;
	private Map<String,Integer> courseMap;
	
	
	
	
	public ProfScoreMgrDesign(ProfInfoDesign pid, boolean modal) {
		super(pid,"성적 관리",modal);
	
		dcbmProfCourse = new DefaultComboBoxModel<String>();
		dcbmProfSub = new DefaultComboBoxModel<String>();
		jbtnSearchStuNum = new JButton("검색");
		jbtnShowStuReportInfo = new JButton("시험지상세보기");
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		
		jtfStuNum = new JTextField(); 
		jcbProfCourse = new JComboBox<String>(dcbmProfCourse);
		jcbProfSub = new JComboBox<String>(dcbmProfSub);
		courseMap = new HashMap<>();
		
		//색변경
		jbtnSearchStuNum.setBackground(new Color(0xE6E6E6));
		jbtnShowStuReportInfo.setBackground(new Color(0xE6E6E6));
	    
		jcbProfCourse.setBackground(new Color(0xE6E6E6));
		jcbProfSub.setBackground(new Color(0xE6E6E6));
		
		//JTable			
		String[] columnNames = {"학번","학생명","과목코드","과목명","점수"};
		String[] rowData = {"","","","",""};
		//String[][] rowData = {{"202","이준원","101","Java", "40"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"}};
		dtmProfScoreMgr = new DefaultTableModel(null, columnNames);
		jtProfScoreMgr = new JTable(dtmProfScoreMgr);
		jtProfScoreMgr.setRowHeight(25);
		
		JScrollPane jspProfScoreMgr = new JScrollPane(jtProfScoreMgr);
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setOpaque(true);
		centerRenderer.setBackground(new Color(0xF8F9FA));
		jtProfScoreMgr.setDefaultRenderer(Object.class, centerRenderer);
		
		jspProfScoreMgr.getViewport().setBackground(new Color(0xF8F9FA)); 
		jspProfScoreMgr.setBorder(new LineBorder(new Color(0x000000), 2));
		
		jbtnSearchStuNum.setPreferredSize(new Dimension(70, 25)); 
	    
	    // 2. 시험지 상세 보기 버튼 크기 조정 (예: 130 x 25)
	    jbtnShowStuReportInfo.setPreferredSize(new Dimension(130, 25));
		
		
		JPanel jp = new JPanel();
		JPanel jpa = new JPanel();
		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		jp.setPreferredSize(new Dimension(20,100));
		jpa.setPreferredSize(new Dimension(20,100));
		
		jbtnSearchStuNum.setSize(200, 200);
		
		jbtnSearchStuNum.setFont(font);
		jbtnShowStuReportInfo.setFont(font);
		
		jbtnSearchStuNum.setBorder(BorderFactory.createEmptyBorder());
		jbtnSearchStuNum.setBackground(new Color(96,186,42));
		jbtnSearchStuNum.setForeground(Color.white);
		jbtnShowStuReportInfo.setBorder(BorderFactory.createEmptyBorder());
		jbtnShowStuReportInfo.setBackground(new Color(96,186,42));
		jbtnShowStuReportInfo.setForeground(Color.white);
		
		
		
		jtfStuNum.setPreferredSize(new Dimension(110,25));
		//North Panel
		jpNorth.add(jcbProfCourse);
		jpNorth.add(jcbProfSub);
		jpNorth.add(jtfStuNum);
		jpNorth.add(jbtnSearchStuNum);
		//Center Panel		
		jpCenter.setLayout(new BorderLayout());
		jpCenter.add(jspProfScoreMgr);
		//South Panel		
		jpSouth.add(jbtnShowStuReportInfo);
		
		add("North",jpNorth);
		add("Center",jpCenter);
		add("West",jpa);
		add("East",jp);
		add("South", jpSouth);
		
		
		ProfScoreMgrDesignEvt psmde = new ProfScoreMgrDesignEvt(this);
		
		CurrentProfData cpd = CurrentProfData.getInstance();
		dcbmProfCourse.addElement("---과정선택---");
		dcbmProfSub.addElement("---과목선택---");
		for(int i=0;i<psmde.showAllCourse().size();i++) {
			String msg = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			
			String[] strArr=cpd.getLogProfDTO().getCourseName().split(",");
			
			
			
		
			for(String str : strArr) {
				if(str.equals(psmde.showAllCourse().get(i).getCourseName())){
					msg="";
					break;
				}
			}
			
			if(msg==null) {
				msg="(종료됨)";
			}

		
			
			dcbmProfCourse.addElement(psmde.showAllCourse().get(i).getCourseName()+msg);
			
			courseMap.put(psmde.showAllCourse().get(i).getCourseName(), psmde.showAllCourse().get(i).getCourseCode());
		}
		

		
		jtProfScoreMgr.getSelectionModel().addListSelectionListener(psmde);
		jtProfScoreMgr.addMouseListener(psmde);
		jcbProfCourse.addActionListener(psmde);
		jcbProfSub.addActionListener(psmde);
		jbtnShowStuReportInfo.addActionListener(psmde);
		jbtnSearchStuNum.addActionListener(psmde);
		addWindowListener(psmde);
		
	
		setBounds(100,100,500,300);
		setVisible(true);
	}


	public Map<String, Integer> getCourseMap() {
		return courseMap;
	}


	public void setCourseMap(Map<String, Integer> courseMap) {
		this.courseMap = courseMap;
	}


	public JComboBox<String> getJcbProfCourse() {
		return jcbProfCourse;
	}


	public DefaultComboBoxModel<String> getDcbmProfCourse() {
		return dcbmProfCourse;
	}


	public JComboBox<String> getJcbProfSub() {
		return jcbProfSub;
	}


	public DefaultComboBoxModel<String> getDcbmProfSub() {
		return dcbmProfSub;
	}


	public void setJcbProfCourse(JComboBox<String> jcbProfCourse) {
		this.jcbProfCourse = jcbProfCourse;
	}


	public void setDcbmProfCourse(DefaultComboBoxModel<String> dcbmProfCourse) {
		this.dcbmProfCourse = dcbmProfCourse;
	}


	public void setJcbProfSub(JComboBox<String> jcbProfSub) {
		this.jcbProfSub = jcbProfSub;
	}


	public void setDcbmProfSub(DefaultComboBoxModel<String> dcbmProfSub) {
		this.dcbmProfSub = dcbmProfSub;
	}


	public void setJtProfScoreMgr(JTable jtProfScoreMgr) {
		this.jtProfScoreMgr = jtProfScoreMgr;
	}


	public void setDtmProfScoreMgr(DefaultTableModel dtmProfScoreMgr) {
		this.dtmProfScoreMgr = dtmProfScoreMgr;
	}


	public void setJtfStuNum(JTextField jtfStuNum) {
		this.jtfStuNum = jtfStuNum;
	}


	public void setJbtnSearchStuNum(JButton jbtnSearchStuNum) {
		this.jbtnSearchStuNum = jbtnSearchStuNum;
	}


	public void setJbtnShowStuReportInfo(JButton jbtnShowStuReportInfo) {
		this.jbtnShowStuReportInfo = jbtnShowStuReportInfo;
	}


	public JTable getJtProfScoreMgr() {
		return jtProfScoreMgr;
	}


	public DefaultTableModel getDtmProfScoreMgr() {
		return dtmProfScoreMgr;
	}


	public JTextField getJtfStuNum() {
		return jtfStuNum;
	}


	public JButton getJbtnSearchStuNum() {
		return jbtnSearchStuNum;
	}


	public JButton getJbtnShowStuReportInfo() {
		return jbtnShowStuReportInfo;
	}
	
	
	
}
