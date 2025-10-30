package kr.co.sist.admin.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import kr.co.sist.admin.controller.AdminInfoDesignEvt;

public class AdminInfoDesign extends JFrame {
	private JButton jbtnStuMgr, jbtnProfMgr, jbtnCourseMgr, jbtnAllStuMgr, jbtnSubjectMgr, jbtnScoreMgr;
//	private AdminDTO aDTO;

	public AdminInfoDesign() {
		super("OO 교육센터 - 관리자");
		
		
		
		
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/images/logo.png")));
//		jbtnStuMgr = new JButton("학생관리");
		jbtnStuMgr = new JButton();
		jbtnProfMgr = new JButton();
		jbtnCourseMgr = new JButton();
		jbtnSubjectMgr = new JButton();
		jbtnAllStuMgr = new JButton();
		jbtnScoreMgr = new JButton();
		
		ImageIcon iconStu = new ImageIcon(getClass().getResource("/images/stuMgr.png"));
		Image imgStu = iconStu.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		
		jbtnStuMgr.setIcon(new ImageIcon(imgStu));
		
		ImageIcon iconProf = new ImageIcon(getClass().getResource("/images/profMgr.png"));
		Image imgProf = iconProf.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		
		jbtnProfMgr.setIcon(new ImageIcon(imgProf));
//		
		ImageIcon iconCourse = new ImageIcon(getClass().getResource("/images/CourseMgr.png"));
		Image imgCourse= iconCourse.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		
		jbtnCourseMgr.setIcon(new ImageIcon(imgCourse));
		
		ImageIcon iconSub = new ImageIcon(getClass().getResource("/images/subMgr.png"));
		Image imgSub= iconSub.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		
		jbtnSubjectMgr.setIcon(new ImageIcon(imgSub));
//		
		ImageIcon iconAllStu = new ImageIcon(getClass().getResource("/images/allstu.png"));
		Image imgAllStu = iconAllStu.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		
		jbtnAllStuMgr.setIcon(new ImageIcon(imgAllStu));
		
		ImageIcon iconSrore = new ImageIcon(getClass().getResource("/images/scoreMgr.png"));
		Image imgSrore = iconSrore.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		
		jbtnScoreMgr.setIcon(new ImageIcon(imgSrore));
		
		jbtnStuMgr.setBorderPainted(false);
		jbtnStuMgr.setFocusPainted(false);
		jbtnStuMgr.setContentAreaFilled(false); // ← 버튼 배경 제거
		jbtnStuMgr.setOpaque(false); // ← 완전 투명 가능
		
		jbtnProfMgr.setBorderPainted(false);
		jbtnProfMgr.setFocusPainted(false);
		jbtnProfMgr.setContentAreaFilled(false); // ← 버튼 배경 제거
		jbtnProfMgr.setOpaque(false); // ← 완전 투명 가능
		
		jbtnCourseMgr.setBorderPainted(false);
		jbtnCourseMgr.setFocusPainted(false);
		jbtnCourseMgr.setContentAreaFilled(false); // ← 버튼 배경 제거
		jbtnCourseMgr.setOpaque(false); // ← 완전 투명 가능
		
		jbtnSubjectMgr.setBorderPainted(false);
		jbtnSubjectMgr.setFocusPainted(false);
		jbtnSubjectMgr.setContentAreaFilled(false); // ← 버튼 배경 제거
		jbtnSubjectMgr.setOpaque(false); // ← 완전 투명 가능
		
		jbtnAllStuMgr.setBorderPainted(false);
		jbtnAllStuMgr.setFocusPainted(false);
		jbtnAllStuMgr.setContentAreaFilled(false); // ← 버튼 배경 제거
		jbtnAllStuMgr.setOpaque(false); // ← 완전 투명 가능
		
		jbtnScoreMgr.setBorderPainted(false);
		jbtnScoreMgr.setFocusPainted(false);
		jbtnScoreMgr.setContentAreaFilled(false); // ← 버튼 배경 제거
		jbtnScoreMgr.setOpaque(false); // ← 완전 투명 가능
		
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
		
		jbtnAllStuMgr.addMouseListener(aide);
		jbtnScoreMgr.addMouseListener(aide);
		jbtnStuMgr.addMouseListener(aide);
		jbtnSubjectMgr.addMouseListener(aide);
		jbtnProfMgr.addMouseListener(aide);
		jbtnCourseMgr.addMouseListener(aide);
		addWindowListener(aide);
		
		
		
		logo.setBounds(220,-5,200, 200);
		jbtnStuMgr.setBounds(70, 180, 140, 140);
		jbtnProfMgr.setBounds(250, 180, jbtnStuMgr.getWidth(), jbtnStuMgr.getHeight());
		jbtnCourseMgr.setBounds(430, jbtnStuMgr.getY(), jbtnStuMgr.getWidth(), jbtnStuMgr.getHeight());
		
		jbtnSubjectMgr.setBounds(jbtnStuMgr.getX(), 335 , jbtnStuMgr.getWidth(), jbtnStuMgr.getHeight());
		jbtnAllStuMgr.setBounds(jbtnProfMgr.getX()-7, 330, 150 ,150);
		jbtnScoreMgr.setBounds(jbtnCourseMgr.getX()-7, 326, 150, 150);
//		
//		
		add(logo);
		add(jbtnStuMgr);
		add(jbtnProfMgr);
		add(jbtnCourseMgr);
		add(jbtnSubjectMgr);
		add(jbtnAllStuMgr);
		add(jbtnScoreMgr);

		
//		setSize(600,300);
		setBounds(370,140,650,550);
		getContentPane().setBackground(new Color(0xF9F8F7));
		setResizable(false);
//		setLocationRelativeTo(null);
		setVisible(true);

	}// AdminInfoDesign
	
	// 삭제 필요 main문
	public static void main(String[] args) {
		new AdminInfoDesign();
	}// main

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

	
}// class
