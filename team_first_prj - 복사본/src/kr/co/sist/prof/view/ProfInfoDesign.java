package kr.co.sist.prof.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.co.sist.prof.controller.ProfInfoDesignEvt;



public class ProfInfoDesign extends JFrame{
	private JLabel jlblProfImg;
	private JLabel jlblProfNum, jlibProfName, jlibProfCourse, jlibProfEmail;
	
	private JTextField jtfProfNumData, jtfProfNameData, jtfProfCourseData, jtfProfEmailData;

	private JButton jbtnModifyProfInfo, jbtnStuState, jbtnMgrExam, jbtnMgrProfScore;

	public JLabel getJlblProfImg() {
		return jlblProfImg;
	}
	
	public void setJlblProfImg(JLabel jlblProfImg) {
		this.jlblProfImg = jlblProfImg;
	}
	
	public JLabel getJlblProfNum() {
		return jlblProfNum;
	}
	
	public void setJlblProfNum(JLabel jlblProfNum) {
		this.jlblProfNum = jlblProfNum;
	}
	
	public JLabel getJlibProfName() {
		return jlibProfName;
	}
	
	public void setJlibProfName(JLabel jlibProfName) {
		this.jlibProfName = jlibProfName;
	}
	
	public JLabel getJlibProfCourse() {
		return jlibProfCourse;
	}
	
	public void setJlibProfCourse(JLabel jlibProfCourse) {
		this.jlibProfCourse = jlibProfCourse;
	}
	
	public JLabel getJlibProfEmail() {
		return jlibProfEmail;
	}
	
	public void setJlibProfEmail(JLabel jlibProfEmail) {
		this.jlibProfEmail = jlibProfEmail;
	}
	
	public JTextField getJtfProfNumData() {
		return jtfProfNumData;
	}
	
	public void setJtfProfNumData(JTextField jtfProfNumData) {
		this.jtfProfNumData = jtfProfNumData;
	}
	
	public JTextField getJtfProfNameData() {
		return jtfProfNameData;
	}
	
	public void setJtfProfNameData(JTextField jtfProfNameData) {
		this.jtfProfNameData = jtfProfNameData;
	}
	
	public JTextField getJtfProfCourseData() {
		return jtfProfCourseData;
	}
	
	public void setJtfProfCourseData(JTextField jtfProfCourseData) {
		this.jtfProfCourseData = jtfProfCourseData;
	}
	
	public JTextField getJtfProfEmailData() {
		return jtfProfEmailData;
	}
	
	public void setJtfProfEmailData(JTextField jtfProfEmailData) {
		this.jtfProfEmailData = jtfProfEmailData;
	}
	
	public JButton getJbtnModifyProfInfo() {
		return jbtnModifyProfInfo;
	}
	
	public void setJbtnModifyProfInfo(JButton jbtnModifyProfInfo) {
		this.jbtnModifyProfInfo = jbtnModifyProfInfo;
	}
	
	public JButton getJbtnStuState() {
		return jbtnStuState;
	}
	
	public void setJbtnStuState(JButton jbtnStuState) {
		this.jbtnStuState = jbtnStuState;
	}
	
	public JButton getJbtnMgrExam() {
		return jbtnMgrExam;
	}
	
	public void setJbtMgrExam(JButton jbtnMgrExam) {
		this.jbtnMgrExam = jbtnMgrExam;
	}
	
	public JButton getJbtnMgrProfScore() {
		return jbtnMgrProfScore;
	}
	
	public void setJbtnMgrProfScore(JButton jbtnMgrProfScore) {
		this.jbtnMgrProfScore = jbtnMgrProfScore;
	}
	
	public ProfInfoDesign() {
		super("교수 기본 화면");
		
		JLabel jlblProfNum = new JLabel("교번");
		JLabel jlblProfName = new JLabel("이름");
		JLabel jlblProfCourse = new JLabel("담당과정");
		JLabel jlblProfEmail = new JLabel("메일주소");
		
		jlblProfImg = new JLabel();
		ImageIcon ii = new ImageIcon();
		jtfProfNumData = new JTextField();
		jtfProfNumData.setEditable(false);
		jtfProfNameData = new JTextField();
		jtfProfNameData.setEditable(false);
		jtfProfCourseData = new JTextField();
		jtfProfCourseData.setEditable(false);
		jtfProfEmailData = new JTextField();
		jtfProfEmailData.setEditable(false);
		
		jbtnModifyProfInfo = new JButton("개인정보변경");
		jbtnStuState = new JButton("학생 현황");
		jbtnMgrExam = new JButton("시험 관리");
		jbtnMgrProfScore = new JButton("성적 관리");
		
		Font font = new Font("맑은고딕",Font.BOLD,15);
		
		setLayout(null);
		
		jlblProfNum.setSize(200,50);
		jlblProfNum.setLocation(140,20);
		jlblProfNum.setFont(font);
		add(jlblProfNum);
		
		
		jtfProfNumData.setSize(150,30);
		jtfProfNumData.setLocation(250,30);
		jtfProfNumData.setFont(font);
		add(jtfProfNumData);
		
		jtfProfNameData.setSize(150,30);
		jtfProfNameData.setLocation(250,70);
		jtfProfNameData.setFont(font);
		add(jtfProfNameData);
		
		jtfProfCourseData.setSize(150,30);
		jtfProfCourseData.setLocation(250,110);
		jtfProfCourseData.setFont(font);
		add(jtfProfCourseData);
		
		jtfProfEmailData.setSize(150,30);
		jtfProfEmailData.setLocation(250,150);
		jtfProfEmailData.setFont(font);
		add(jtfProfEmailData);
		
		
		
		jlblProfName.setSize(200,50);
		jlblProfName.setLocation(140,60);
		jlblProfName.setFont(font);
		add(jlblProfName);
		
		jlblProfCourse.setSize(200,50);
		jlblProfCourse.setLocation(140,100);
		jlblProfCourse.setFont(font);
		add(jlblProfCourse);

		jlblProfEmail.setSize(200,50);
		jlblProfEmail.setLocation(140,140);
		jlblProfEmail.setFont(font);
		add(jlblProfEmail);
		
		
		jlblProfImg.setSize(100,120);
		jlblProfImg.setLocation(20, 38);
		jlblProfImg.setFont(font);
		jlblProfImg.setBackground(Color.BLACK);
		jlblProfImg.setOpaque(true);
		jlblProfImg.setHorizontalAlignment(SwingConstants.CENTER);
		add(jlblProfImg);
		
		
		
		jbtnModifyProfInfo.setSize(150,30);
		jbtnModifyProfInfo.setLocation(420,30);
		jbtnModifyProfInfo.setFont(font);		
		add(jbtnModifyProfInfo);
		
		jbtnStuState.setSize(150,30);
		jbtnStuState.setLocation(420,70);
		jbtnStuState.setFont(font);		
		add(jbtnStuState);
		
		jbtnMgrExam.setSize(150,30);
		jbtnMgrExam.setLocation(420,110);
		jbtnMgrExam.setFont(font);		
		add(jbtnMgrExam);
		
		jbtnMgrProfScore.setSize(150,30);
		jbtnMgrProfScore.setLocation(420,150);
		jbtnMgrProfScore.setFont(font);		
		add(jbtnMgrProfScore);

		ProfInfoDesignEvt pide = new ProfInfoDesignEvt(this);
		
		jbtnModifyProfInfo.addActionListener(pide);
		jbtnStuState.addActionListener(pide);
		jbtnMgrProfScore.addActionListener(pide);
		jbtnMgrExam.addActionListener(pide);
		addWindowListener(pide);
		
		
		setBounds(600,300,600,350);
		setVisible(true);
		
		
		
	}



	
}// class


