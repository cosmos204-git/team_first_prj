package kr.co.sist.prof.view;

import java.awt.Color;
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
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.prof.controller.ProfExamMgrDesignEvt;


public class ProfExamMgrDesign extends JDialog{
	
		private DefaultTableModel dtmStudent;
		private JTable jtCourseTable;
	    private JPanel jplProfExamMgr;
	    private JLabel jlbExamCourse, jlblExamSubject, jlblExamState, jlblExamStart, jlblExamEnd;
	    private JTextField jtfExamCourseData, jtfExamSubjectData;
	    
	    private DefaultComboBoxModel<String> dcbmExamState;
	    private JComboBox<String> jcbExamState;
	    private DefaultComboBoxModel<String> dcbmExamStart;
	    private JComboBox<String> jcbExamStart;
	    private DefaultComboBoxModel<String> dcbmExamEnd;
	    private JComboBox<String> jcbExamEnd;
	    
	    private JScrollPane jspStuReport;
	    private JButton jbtnModifyExam, jbtnExamDetail;
	    
	    public ProfExamMgrDesign(ProfInfoDesign pid, boolean modal) {
	        super(pid, "시험 관리", modal);

		      String[] columnNames = {"  강 의  과 정  "," 시험 과목 ", "시험 오픈", "시험 시작", "시험 종료" };
		      dtmStudent = new DefaultTableModel(columnNames,0);
		      jtCourseTable= new JTable(dtmStudent);

		      //글꼴 설정
		      Font font =new Font("맑은 고딕",Font.BOLD,15);
		        // 예시 데이터 추가
		      
		      jlbExamCourse = new JLabel(" 강의 과정 ");
		      jtfExamCourseData = new JTextField();
		      jtfExamCourseData.setEditable(false);

		      jlblExamSubject = new JLabel(" 시험 과목 ");
		      jtfExamSubjectData = new JTextField();
		      jtfExamSubjectData.setEditable(false);
		      
		      
		      jlblExamState = new JLabel(" 시험 오픈 ");
		      jlblExamStart = new JLabel(" 시험 시작 ");
		      jlblExamEnd = new JLabel(" 시험 종료 ");

		      
		      
		      dcbmExamState = new DefaultComboBoxModel<String>();
		      jcbExamState = new JComboBox<String>(dcbmExamState);
		      
		      jcbExamState.addItem("응시가능");
		      jcbExamState.addItem("응시불가");
		      
		      dcbmExamStart = new DefaultComboBoxModel<String>();
		      jcbExamStart = new JComboBox<String>(dcbmExamStart);
		      
		      jcbExamStart.addItem("08:00");
		      jcbExamStart.addItem("09:00");
		      jcbExamStart.addItem("10:00");
		      jcbExamStart.addItem("11:00");
		      jcbExamStart.addItem("12:00");
		      jcbExamStart.addItem("13:00");
		      jcbExamStart.addItem("14:00");
		      jcbExamStart.addItem("15:00");
		      jcbExamStart.addItem("16:00");
		      jcbExamStart.addItem("17:00");
		      jcbExamStart.addItem("18:00");

		      dcbmExamEnd = new DefaultComboBoxModel<String>();
		      jcbExamEnd = new JComboBox<String>(dcbmExamEnd);

		      jcbExamEnd.addItem("09:00");
		      jcbExamEnd.addItem("10:00");
		      jcbExamEnd.addItem("11:00");
		      jcbExamEnd.addItem("12:00");
		      jcbExamEnd.addItem("13:00");
		      jcbExamEnd.addItem("14:00");
		      jcbExamEnd.addItem("15:00");
		      jcbExamEnd.addItem("16:00");
		      jcbExamEnd.addItem("17:00");
		      jcbExamEnd.addItem("18:00");
		      jcbExamEnd.addItem("19:00");
		      
		      
		      
		      // 시험 관련 정보 라벨 
		      // 강의 과정
		      jlbExamCourse.setSize(200,50);
		      jlbExamCourse.setLocation(100,380);
		      jlbExamCourse.setFont(font);
		      // 시험 과목
		      jlblExamSubject.setSize(80,50);
		      jlblExamSubject.setLocation(300,380);
		      jlblExamSubject.setFont(font);
		      		      
		      //시험 오픈
		      jlblExamState.setSize(90,50);
		      jlblExamState.setLocation(430,380);
		      jlblExamState.setFont(font);
		      //시험 시작
		      jlblExamStart.setSize(100,50);
		      jlblExamStart.setLocation(540,380);
		      jlblExamStart.setFont(font);
		      //시험 종료
		      jlblExamEnd.setSize(100,50);
		      jlblExamEnd.setLocation(630,380);
		      jlblExamEnd.setFont(font);
		      
		      // 시험 과정과 시험응시 /일자 표시 콤보 박스 리스트
		      jtfExamCourseData.setSize(200,30);
		      jtfExamCourseData.setLocation(70,420);
		      jtfExamCourseData.setFont(font);
		      
		      jtfExamSubjectData.setSize(140,30);
		      jtfExamSubjectData.setLocation(280,420);
		      jtfExamSubjectData.setFont(font);
		      
		      jcbExamState.setSize(100,30);
		      jcbExamState.setLocation(430,420);
		      jcbExamState.setFont(font);

		      jcbExamStart.setSize(80,30);
		      jcbExamStart.setLocation(540,420);
		      jcbExamStart.setFont(font);
		      
		      jcbExamEnd.setSize(80,30);
		      jcbExamEnd.setLocation(630,420);
		      jcbExamEnd.setFont(font);

		      add(jcbExamState);
		      add(jcbExamStart);
		      add(jcbExamEnd);
		      
		      add(jlbExamCourse);
		      add(jtfExamCourseData);
		      add(jlblExamSubject);
		      add(jtfExamSubjectData);
		      add(jlblExamState);
		      add(jlblExamStart);
		      add(jlblExamEnd);
		      
		      // 아래쪽 버튼 
		      jbtnModifyExam = new JButton("수정 완료");		      
		      jbtnExamDetail = new JButton("시험지 상세 보기");
		      
		      //색 변경
		      jbtnExamDetail.setBackground(new Color(0xE6E6E6));
		      jbtnModifyExam.setBackground(new Color(0xE6E6E6));
		      jcbExamEnd.setBackground(new Color(0xE6E6E6));
		      jcbExamState.setBackground(new Color(0xE6E6E6));
		      jcbExamStart.setBackground(new Color(0xE6E6E6));
		      
		      jtCourseTable.setFont(font);
		      jtCourseTable.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 15));
		      jbtnModifyExam.setFont(font);
		      jbtnExamDetail.setFont(font);
		      
		      
		      //columns(열)의 넓이 설정
		      TableColumnModel tcm= jtCourseTable.getColumnModel();
		      tcm.getColumn(0).setPreferredWidth(120);//강의 과정
		      tcm.getColumn(1).setPreferredWidth(80);//시험 과목
		      tcm.getColumn(2).setPreferredWidth(10);//시험 오픈
		      tcm.getColumn(3).setPreferredWidth(10);//시험 시작
		      tcm.getColumn(4).setPreferredWidth(10);//시험 종료
		      
		      
		      //2~4 번 컬럼은 센터 align
		      DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		      centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		      tcm.getColumn(2).setCellRenderer(centerRenderer); 
		      tcm.getColumn(3).setCellRenderer(centerRenderer); 
		      tcm.getColumn(4).setCellRenderer(centerRenderer); 
		      
		      //행의 높이
		      jtCourseTable.setRowHeight(20);
		      
		      //스크롤바 설정
		      jspStuReport= new JScrollPane(jtCourseTable);
		      
				
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				centerRenderer.setOpaque(true);
				centerRenderer.setBackground(new Color(0xF8F9FA));
				jtCourseTable.setDefaultRenderer(Object.class, centerRenderer);
				
				jspStuReport.getViewport().setBackground(new Color(0xF8F9FA)); 
				jspStuReport.setBorder(new LineBorder(new Color(0x000000), 2));
		      
		      //위치이동
		      setLayout(null);
		      jspStuReport.setBounds(70,40,650,350);//JTable

		      jbtnModifyExam.setBounds(230,480,150,35);//수정 완료
		      jbtnExamDetail.setBounds(400,480,150,35);//시험지 상세 보기
		      add(jspStuReport);
		      
		      //JFrame에 추가 
		      add(jbtnModifyExam);
		      add(jbtnExamDetail);

		      ProfExamMgrDesignEvt pemde = new ProfExamMgrDesignEvt(this);
		      jbtnExamDetail.addActionListener(pemde);
		      jbtnModifyExam.addActionListener(pemde);
		      addWindowListener(pemde);
		      
		      //table 선택값을 전달
		      jtCourseTable.getSelectionModel().addListSelectionListener((ListSelectionListener) pemde);
			  
		      getContentPane().setBackground(new Color(0xF8F9FA));
		      
		      setLayout(null);
		      
		      setSize(800,600);
		      setLocationRelativeTo(this);
		      setVisible(true);
		      
	    }

		public DefaultTableModel getDtmStudent() {
			return dtmStudent;
		}

		public void setDtmStudent(DefaultTableModel dtmStudent) {
			this.dtmStudent = dtmStudent;
		}

		public JTable getJtCourseTable() {
			return jtCourseTable;
		}

		public void setJtCourseTable(JTable jtCourseTable) {
			this.jtCourseTable = jtCourseTable;
		}

		public JPanel getJplProfExamMgr() {
			return jplProfExamMgr;
		}

		public void setJplProfExamMgr(JPanel jplProfExamMgr) {
			this.jplProfExamMgr = jplProfExamMgr;
		}

		public JLabel getJlbExamCourse() {
			return jlbExamCourse;
		}

		public void setJlbExamCourse(JLabel jlbExamCourse) {
			this.jlbExamCourse = jlbExamCourse;
		}

		public JLabel getJlblExamSubject() {
			return jlblExamSubject;
		}

		public void setJlblExamSubject(JLabel jlblExamSubject) {
			this.jlblExamSubject = jlblExamSubject;
		}

		public JLabel getJlblExamState() {
			return jlblExamState;
		}

		public void setJlblExamState(JLabel jlblExamState) {
			this.jlblExamState = jlblExamState;
		}

		public JLabel getJlblExamStart() {
			return jlblExamStart;
		}

		public void setJlblExamStart(JLabel jlblExamStart) {
			this.jlblExamStart = jlblExamStart;
		}

		public JLabel getJlblExamEnd() {
			return jlblExamEnd;
		}

		public void setJlblExamEnd(JLabel jlblExamEnd) {
			this.jlblExamEnd = jlblExamEnd;
		}

		public JTextField getJtfExamCourseData() {
			return jtfExamCourseData;
		}

		public void setJtfExamCourseData(JTextField jtfExamCourseData) {
			this.jtfExamCourseData = jtfExamCourseData;
		}

		public JTextField getJtfExamSubjectData() {
			return jtfExamSubjectData;
		}

		public void setJtfExamSubjectData(JTextField jtfExamSubjectData) {
			this.jtfExamSubjectData = jtfExamSubjectData;
		}

		public DefaultComboBoxModel<String> getDcbmExamState() {
			return dcbmExamState;
		}

		public void setDcbmExamState(DefaultComboBoxModel<String> dcbmExamState) {
			this.dcbmExamState = dcbmExamState;
		}

		public JComboBox<String> getJcbExamState() {
			return jcbExamState;
		}

		public void setJcbExamState(JComboBox<String> jcbExamState) {
			this.jcbExamState = jcbExamState;
		}

		public DefaultComboBoxModel<String> getDcbmExamStart() {
			return dcbmExamStart;
		}

		public void setDcbmExamStart(DefaultComboBoxModel<String> dcbmExamStart) {
			this.dcbmExamStart = dcbmExamStart;
		}

		public JComboBox<String> getJcbExamStart() {
			return jcbExamStart;
		}

		public void setJcbExamStart(JComboBox<String> jcbExamStart) {
			this.jcbExamStart = jcbExamStart;
		}

		public DefaultComboBoxModel<String> getDcbmExamEnd() {
			return dcbmExamEnd;
		}

		public void setDcbmExamEnd(DefaultComboBoxModel<String> dcbmExamEnd) {
			this.dcbmExamEnd = dcbmExamEnd;
		}

		public JComboBox<String> getJcbExamEnd() {
			return jcbExamEnd;
		}

		public void setJcbExamEnd(JComboBox<String> jcbExamEnd) {
			this.jcbExamEnd = jcbExamEnd;
		}

		public JScrollPane getJspStuReport() {
			return jspStuReport;
		}

		public void setJspStuReport(JScrollPane jspStuReport) {
			this.jspStuReport = jspStuReport;
		}

		public JButton getJbtnModifyExam() {
			return jbtnModifyExam;
		}

		public void setJbtnModifyExam(JButton jbtnModifyExam) {
			this.jbtnModifyExam = jbtnModifyExam;
		}

		public JButton getJbtnExamDetail() {
			return jbtnExamDetail;
		}

		public void setJbtnExamDetail(JButton jbtnExamDetail) {
			this.jbtnExamDetail = jbtnExamDetail;
		}

	    

}//class
