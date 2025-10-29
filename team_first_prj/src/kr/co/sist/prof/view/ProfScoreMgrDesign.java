package kr.co.sist.prof.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
		jtfStuNum = new JTextField(); 
		jcbProfCourse = new JComboBox<String>(dcbmProfCourse);
		jcbProfSub = new JComboBox<String>(dcbmProfSub);
		courseMap = new HashMap<>();
		
		//JTable			
		String[] columnNames = {"학번","학생명","과목코드","과목명","점수"};
		String[] rowData = {"","","","",""};
		//String[][] rowData = {{"202","이준원","101","Java", "40"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"},{"202","이준원","102","Oracle", "50"}};
		dtmProfScoreMgr = new DefaultTableModel(null, columnNames);
		jtProfScoreMgr = new JTable(dtmProfScoreMgr);
		jtProfScoreMgr.setRowHeight(25);
		
		JScrollPane jspProfScoreMgr = new JScrollPane(jtProfScoreMgr);
		
		
		
		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		
		
		
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
