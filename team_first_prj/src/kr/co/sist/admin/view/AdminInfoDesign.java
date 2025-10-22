package kr.co.sist.admin.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import kr.co.sist.admin.controller.AdminInfoDesignEvt;

public class AdminInfoDesign extends JFrame {
	private JButton jbtnStuMgr, jbtnProfMgr, jbtnCourseMgr, jbtnAllStuMgr, jbtnSubjectMgr, jbtnScoreMgr;
//	private AdminDTO aDTO;

	public AdminInfoDesign() {
		super("OO 교육센터 - 관리자");
		jbtnStuMgr = new JButton("학생관리");
		jbtnProfMgr = new JButton("교수관리");
		jbtnCourseMgr = new JButton("과정관리");
		jbtnSubjectMgr = new JButton("과목관리");
		jbtnAllStuMgr = new JButton("입과관리");
		jbtnScoreMgr = new JButton("성적관리");

		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		jbtnStuMgr.setFont(font);
		jbtnProfMgr.setFont(font);
		jbtnCourseMgr.setFont(font);
		jbtnAllStuMgr.setFont(font);
		jbtnSubjectMgr.setFont(font);
		jbtnScoreMgr.setFont(font);

		setLayout(null);

		AdminInfoDesignEvt aide = new AdminInfoDesignEvt(this);
		jbtnAllStuMgr.addActionListener(aide);
		jbtnScoreMgr.addActionListener(aide);
		jbtnStuMgr.addActionListener(aide);
		jbtnSubjectMgr.addActionListener(aide);
		jbtnProfMgr.addActionListener(aide);
		jbtnCourseMgr.addActionListener(aide);
		addWindowListener(aide);
		
		
		
		
		jbtnStuMgr.setBounds(120, 35, 140, 50);
		jbtnCourseMgr.setBounds(120, jbtnStuMgr.getY() + 70, jbtnStuMgr.getWidth(), jbtnStuMgr.getHeight());
		jbtnSubjectMgr.setBounds(120, jbtnCourseMgr.getY() +70 , jbtnStuMgr.getWidth(), jbtnStuMgr.getHeight());
		jbtnProfMgr.setBounds(345, 35, jbtnStuMgr.getWidth(), jbtnStuMgr.getHeight());
		
		jbtnAllStuMgr.setBounds(345, jbtnProfMgr.getY() +70, jbtnStuMgr.getWidth(), jbtnStuMgr.getHeight());
		jbtnScoreMgr.setBounds(345, jbtnAllStuMgr.getY() +70, jbtnStuMgr.getWidth(), jbtnStuMgr.getHeight());
		
		
		
		add(jbtnStuMgr);
		add(jbtnProfMgr);
		add(jbtnCourseMgr);
		add(jbtnSubjectMgr);
		add(jbtnAllStuMgr);
		add(jbtnScoreMgr);

		
		setSize(600,300);

		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}// AdminInfoDesign

	public JButton getJbtnStuMgr() {
		return jbtnStuMgr;
	}//getJbtnStuMgr

	public JButton getJbtnProfMgr() {
		return jbtnProfMgr;
	}//getJbtnProfMgr

	public JButton getJbtnCourseMgr() {
		return jbtnCourseMgr;
	}//getJbtnCourseMgr

	public JButton getJbtnAllStuMgr() {
		return jbtnAllStuMgr;
	}//getJbtnAllStuMgr

	public JButton getJbtnSubjectMgr() {
		return jbtnSubjectMgr;
	}//getJbtnSubjectMgr

	public JButton getJbtnScoreMgr() {
		return jbtnScoreMgr;
	}//getJbtnScoreMgr

	
	// 삭제 필요 main문
	public static void main(String[] args) {
		new AdminInfoDesign();
	}// main
}// class
