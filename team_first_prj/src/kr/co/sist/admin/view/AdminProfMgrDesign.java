package kr.co.sist.admin.view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.admin.controller.AdminProfMgrDesignEvt;

public class AdminProfMgrDesign extends JDialog {

	private JTextField jtfProfNum;
	private DefaultTableModel dtmProMgr;
	private JTable jtProfMgr ;
	private JButton jbtnSearch, jbtnModify, jbtnAdd, jbtnDelete, jbtnClose;
//	private StudentDTO sDTO;

	public AdminProfMgrDesign(AdminInfoDesign aid, boolean modal) {
		super(aid,"관리자 - 교수관리",modal);
		JPanel jpNouthSerch= new JPanel();
		JPanel jpSouthButton= new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
		String[] columnNames = { "교번", "이름", "휴대폰번호", "가입일" };
		jtfProfNum = new JTextField(15);
		dtmProMgr = new DefaultTableModel(columnNames, 0);
		jtProfMgr = new JTable(dtmProMgr);

		jbtnSearch = new JButton("검색");

		jbtnModify = new JButton("수정");
		jbtnAdd = new JButton("계정 추가");
		jbtnDelete = new JButton("삭제");
		jbtnClose = new JButton("닫기");

		// 글꼴 설정
		Font font = new Font("맑은 고딕", Font.BOLD, 15);
		jtfProfNum.setFont(font);
		jtProfMgr.setFont(font);
		jbtnSearch.setFont(font);
		jbtnModify.setFont(font);
		jbtnAdd.setFont(font);
		jbtnDelete.setFont(font);
		jbtnClose.setFont(font);
		
		
		//액션리스너, 윈도우 리스너 추가 
		AdminProfMgrDesignEvt asde= new AdminProfMgrDesignEvt(this);
		addWindowListener(asde);
		jbtnSearch.addActionListener(asde);
		jbtnModify.addActionListener(asde);
		jbtnAdd.addActionListener(asde);
		jbtnDelete.addActionListener(asde);
		jbtnClose.addActionListener(asde);
		jtProfMgr.addMouseListener(asde);
		

		// columns(열)의 넓이 설정
		TableColumnModel tcm = jtProfMgr.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(40);// 교번
		tcm.getColumn(1).setPreferredWidth(80);// 이름
		tcm.getColumn(2).setPreferredWidth(100);// 휴대폰 번호
		tcm.getColumn(3).setPreferredWidth(120);// 가입일

		// 행의 높이
		jtProfMgr.setRowHeight(20);

		// 스크롤바 설정
		JScrollPane jspStuMgr = new JScrollPane(jtProfMgr);

		setLayout(new BorderLayout());
		jpSouthButton.add(jbtnModify);
		jpSouthButton.add(jbtnAdd);
		jpSouthButton.add(jbtnDelete);
		jpSouthButton.add(jbtnClose);
		jpNouthSerch.add(jtfProfNum);
		jpNouthSerch.add(jbtnSearch);
		
		
		add("North",jpNouthSerch);
		add("Center",jspStuMgr);
		add("South",jpSouthButton);
		
		
		setSize(600, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}//AdminProfMgrDesign

	public JTextField getJtfProfNum() {
		return jtfProfNum;
	}

	public DefaultTableModel getDtmProMgr() {
		return dtmProMgr;
	}

	public JTable getJtProfMgr() {
		return jtProfMgr;
	}

	public JButton getJbtnSearch() {
		return jbtnSearch;
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
	

	
	
//	public static void main(String[] args) {
//		new AdminProfMgrDesign();
//	}//main
	


}//class
