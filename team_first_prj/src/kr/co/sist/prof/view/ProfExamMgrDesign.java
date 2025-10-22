package kr.co.sist.prof.view;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.prof.controller.ProfExamMgrDesignEvt;


public class ProfExamMgrDesign extends JDialog{
	
		private DefaultTableModel dtmStudent;
		private JTable jtCourseTable;
	    private JPanel jplProfExamMgr;
	    private JLabel jlbProfExamList, jlblExamState, jlblExamStart, jlblExamEnd;
	    private JTextField jtfProfExamListData;
	    
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

		      String[] columnNames = {" 시험 목록 ","[과정 -> 과목]"," 시험 일정"};
		      dtmStudent = new DefaultTableModel(columnNames,0);
		      jtCourseTable= new JTable(dtmStudent);

		      //글꼴 설정
		      Font font =new Font("맑은 고딕",Font.BOLD,15);
		        // 예시 데이터 추가
		      dtmStudent.addRow(new Object[] { "FullStack과정",  "Java", "2025-10-01~2025-10-20" });
		      dtmStudent.addRow(new Object[] { "DATABASE과정", "PL/SQL", "2025-11-01~2025-11-20" });
		      
		      jlbProfExamList = new JLabel("수강 과정");
		      jtfProfExamListData = new JTextField();
		      jtfProfExamListData.setEditable(false);
		      jlblExamState = new JLabel(" 시험 일정 ");
		      jlblExamStart = new JLabel("  시 작  ");
		      jlblExamEnd = new JLabel("  종 료   ");

		      
		      
		      dcbmExamState = new DefaultComboBoxModel<String>();
		      dcbmExamState.addElement("응시 불가");
		      dcbmExamState.addElement("응시 가능");
		      jcbExamState = new JComboBox<String>(dcbmExamState);
		      
		      dcbmExamStart = new DefaultComboBoxModel<String>();
		      dcbmExamStart.addElement("11:00");
		      dcbmExamStart.addElement("12:00");
		      jcbExamStart = new JComboBox<String>(dcbmExamStart);
		      

		      dcbmExamEnd = new DefaultComboBoxModel<String>();
		      dcbmExamEnd.addElement("13:00");
		      dcbmExamEnd.addElement("14:00");
		      jcbExamEnd = new JComboBox<String>(dcbmExamEnd);

		      
		      // 시험 관련 정보 라벨 
		      jlbProfExamList.setSize(200,50);
		      jlbProfExamList.setLocation(130,380);
		      jlbProfExamList.setFont(font);

		      jlblExamState.setSize(200,50);
		      jlblExamState.setLocation(300,380);
		      jlblExamState.setFont(font);

		      jlblExamStart.setSize(200,50);
		      jlblExamStart.setLocation(440,380);
		      jlblExamStart.setFont(font);
		      
		      jlblExamEnd.setSize(200,50);
		      jlblExamEnd.setLocation(570,380);
		      jlblExamEnd.setFont(font);
		      
		      // 시험 과정과 시험응시 /일자 표시 콤보 박스 리스트
		      jtfProfExamListData.setSize(200,30);
		      jtfProfExamListData.setLocation(70,420);
		      jtfProfExamListData.setFont(font);
		      
		      jcbExamState.setSize(100,30);
		      jcbExamState.setLocation(300,420);
		      jcbExamState.setFont(font);

		      jcbExamStart.setSize(110,30);
		      jcbExamStart.setLocation(450,420);
		      jcbExamStart.setFont(font);
		      
		      jcbExamEnd.setSize(110,30);
		      jcbExamEnd.setLocation(580,420);
		      jcbExamEnd.setFont(font);

		      add(jtfProfExamListData);
		      add(jcbExamState);
		      add(jcbExamStart);
		      add(jcbExamEnd);
		      
		      add(jlbProfExamList);
		      add(jlblExamState);
		      add(jlblExamStart);
		      add(jlblExamEnd);
		      
		      // 아래쪽 버튼 
		      jbtnModifyExam = new JButton("수정 완료");		      
		      jbtnExamDetail = new JButton("시험지 상세 보기");
		      
		      jtCourseTable.setFont(font);
		      jtCourseTable.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 15));
		      jbtnModifyExam.setFont(font);
		      jbtnExamDetail.setFont(font);
		      
		      
		      //columns(열)의 넓이 설정
		      TableColumnModel tcm= jtCourseTable.getColumnModel();
		      tcm.getColumn(0).setPreferredWidth(100);//수강 과정
		      tcm.getColumn(1).setPreferredWidth(100);//시험 일정
		      
		      //행의 높이
		      jtCourseTable.setRowHeight(20);
		      
		      //스크롤바 설정
		      jspStuReport= new JScrollPane(jtCourseTable);
		      
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
		      
		      
		      
		      setLayout(null);
		      
		      setSize(800,600);
		      setLocationRelativeTo(this);
		      setVisible(true);
		      
	    }

		public DefaultTableModel getDtmStudent() {
			return dtmStudent;
		}

		public JTable getJtCourseTable() {
			return jtCourseTable;
		}

		public JTextField getJtfProfExamListData() {
			return jtfProfExamListData;
		}

		public DefaultComboBoxModel<String> getDcbmExamState() {
			return dcbmExamState;
		}

		public JComboBox<String> getJcbExamState() {
			return jcbExamState;
		}

		public DefaultComboBoxModel<String> getDcbmExamStart() {
			return dcbmExamStart;
		}

		public JComboBox<String> getJcbExamStart() {
			return jcbExamStart;
		}

		public DefaultComboBoxModel<String> getDcbmExamEnd() {
			return dcbmExamEnd;
		}

		public JComboBox<String> getJcbExamEnd() {
			return jcbExamEnd;
		}

		public JButton getJbtnModifyExam() {
			return jbtnModifyExam;
		}

		public JButton getJbtnExamDetail() {
			return jbtnExamDetail;
		}


	    
	    

}//class
