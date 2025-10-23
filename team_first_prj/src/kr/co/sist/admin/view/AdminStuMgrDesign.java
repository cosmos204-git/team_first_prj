package kr.co.sist.admin.view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.admin.controller.AdminStuMgrDesignEvt;
import kr.co.sist.admin.dto.StudentDTO;

public class AdminStuMgrDesign extends JDialog {

	private JTextField jtfStuNum;
	private DefaultTableModel dtmStuMgr;
	private JTable jtStuMgr;
	private JButton jbtnSearch, jbtnModify, jbtnAdd, jbtnDelete, jbtnClose;
//	private StudentDTO sDTO;

	public AdminStuMgrDesign(AdminInfoDesign aid, boolean modal) {
		super(aid,"관리자 - 학생관리",modal);
		JPanel jpNouthSerch= new JPanel();
		JPanel jpSouthButton= new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
		String[] columnNames = { "학번", "이름", "휴대폰번호", "가입일" };
		jtfStuNum = new JTextField(15);
		dtmStuMgr = new DefaultTableModel(columnNames, 0);
		jtStuMgr = new JTable(dtmStuMgr);

		jbtnSearch = new JButton("검색");

		jbtnModify = new JButton("수정");
		jbtnAdd = new JButton("계정 추가");
		jbtnDelete = new JButton("삭제");
		jbtnClose = new JButton("닫기");

		// 글꼴 설정
		Font font = new Font("맑은 고딕", Font.BOLD, 15);
		jtfStuNum.setFont(font);
		jtStuMgr.setFont(font);
		
		jbtnSearch.setFont(font);
		jbtnModify.setFont(font);
		jbtnAdd.setFont(font);
		jbtnDelete.setFont(font);
		jbtnClose.setFont(font);
		
		//액션리스너, 윈도우 리스너 추가 
		AdminStuMgrDesignEvt asde= new AdminStuMgrDesignEvt(this);
		addWindowListener(asde);
		jbtnSearch.addActionListener(asde);
		jbtnModify.addActionListener(asde);
		jbtnAdd.addActionListener(asde);
		jbtnDelete.addActionListener(asde);
		jbtnClose.addActionListener(asde);
		jtStuMgr.addMouseListener(asde);
		
		
		
		
		

		// columns(열)의 넓이 설정
		TableColumnModel tcm = jtStuMgr.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(40);// 학번
		tcm.getColumn(1).setPreferredWidth(80);// 이름
		tcm.getColumn(2).setPreferredWidth(100);// 휴대폰 번호
		tcm.getColumn(3).setPreferredWidth(120);// 가입일

		// 행의 높이
		jtStuMgr.setRowHeight(20);

		// 스크롤바 설정
		JScrollPane jspStuMgr = new JScrollPane(jtStuMgr);

		setLayout(new BorderLayout());
		jpSouthButton.add(jbtnModify);
		jpSouthButton.add(jbtnAdd);
		jpSouthButton.add(jbtnDelete);
		jpSouthButton.add(jbtnClose);
		jpNouthSerch.add(jtfStuNum);
		jpNouthSerch.add(jbtnSearch);
		
		
		add("North",jpNouthSerch);
		add("Center",jspStuMgr);
		add("South",jpSouthButton);
		
		
		setSize(600, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}//AdminStuMgrDesign

	public JTextField getJtfStuNum() {
		return jtfStuNum;
	}

	public DefaultTableModel getDtmStuMgr() {
		return dtmStuMgr;
	}

	public JTable getJtStuMgr() {
		return jtStuMgr;
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

	

	




}//class
