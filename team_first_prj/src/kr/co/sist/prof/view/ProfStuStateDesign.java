package kr.co.sist.prof.view;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.prof.controller.ProfStuStateDesignEvt;


public class ProfStuStateDesign extends JDialog {

	private JLabel jlblProfName, jlblProfCourse;
	private JTextField jtfProfName, jtfProfStu;
	private DefaultTableModel dtmStudent;
	private JTable jtStudent;
	private JScrollPane jspStudent;
	private JButton jbtnSearch,jbtnClose;
	
	private DefaultComboBoxModel<String> dcbmStuState; 
	private JComboBox<String> jcbStuState;
	
	//   private StudentDTO sDTO;
	
	public ProfStuStateDesign(ProfInfoDesign pid, boolean modal) {
		super(pid, "학생관리", modal);
		String[] columnNames = {"학번","학생 이름","휴대폰번호"};
		jtfProfName = new JTextField();
		dtmStudent = new DefaultTableModel(columnNames,0);
		jtStudent= new JTable(dtmStudent);
		
		
		jlblProfName = new JLabel("교수명");
		jlblProfCourse = new JLabel("과정명");
		jbtnSearch = new JButton("검색");		      
		jbtnClose = new JButton("닫기");
		jtfProfName = new JTextField("홍길동");
		jtfProfStu = new JTextField("허준");
		
		//글꼴 설정
		Font font =new Font("맑은 고딕",Font.BOLD,15);
		jlblProfName.setFont(font);
		jtfProfName.setFont(font);
		jlblProfCourse.setFont(font);
		jtStudent.setFont(font);
		jtStudent.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 15));
		jbtnSearch.setFont(font);
		jbtnClose.setFont(font);
		
		dcbmStuState = new DefaultComboBoxModel<String>();
		dcbmStuState.addElement("FullStack 과정");
		dcbmStuState.addElement("DATABASE 과정");
		jcbStuState = new JComboBox<String>(dcbmStuState);
		
		
		
		//columns(열)의 넓이 설정
		TableColumnModel tcm= jtStudent.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(40);//학번
		tcm.getColumn(1).setPreferredWidth(80);//이름
		tcm.getColumn(2).setPreferredWidth(100);//휴대폰 번호
		
		//행의 높이
		jtStudent.setRowHeight(20);
		
		//스크롤바 설정
		jspStudent=new JScrollPane(jtStudent);
		
		//위치이동
		setLayout(null);
		
		jlblProfName.setBounds(60,65,100,30);
		jtfProfName.setBounds(120,65,80,30);
		jlblProfCourse.setBounds(320,65,80,30);
		jcbStuState.setBounds(390, 60, 140, 35);		      
		jspStudent.setBounds(60,110,465,350);//JTable
		
		jbtnClose.setBounds(420,480,100,35);//닫기
		add(jspStudent);
		
		//JFrame에 추가 
		add(jlblProfName);
		add(jtfProfName);
		add(jlblProfCourse);
		add(jcbStuState);
		add(jtfProfStu);
		add(jbtnClose);
		
		ProfStuStateDesignEvt pssde = new ProfStuStateDesignEvt(this);
		jbtnClose.addActionListener(pssde);
		addWindowListener(pssde);
		
		setLayout(null);
		setSize(600,600);
		setLocationRelativeTo(this);
		setVisible(true);
		
		
	}//ProfStuStateDesign
	
	
	
	public JTextField getJtfName() {
		return jtfProfName;
	}//getJtfName
	
	
	
	public DefaultTableModel getDtmStudent() {
		return dtmStudent;
	}//getDtmStuMgr
	
	
	public JTable getJtStudent() {
		return jtStudent;
	}//getJtStuMgr
	
	
	public JButton getJbtnSearch() {
		return jbtnSearch;
	}//getJbtnSearch
	
	
	public JButton getJbtnClose() {
		return jbtnClose;
	}//getJbtnClose

	
	
}// class