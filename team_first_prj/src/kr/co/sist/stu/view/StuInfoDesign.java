
package kr.co.sist.stu.view;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import kr.co.sist.stu.controller.StuInfoDesignEvt;

public class StuInfoDesign extends JFrame {
	private JLabel jlblStuImg;
	private JTextField jtfStuNumData,jtfStuNameData,jtfStuTelData,jtfStuCourseData;
	private JButton jbtnModifyStuInfo, jbtnSelectCourse, jbtnMgrCourse;

	public StuInfoDesign() throws IOException {
		super("학생 기본 화면");
		
		
		JLabel jlblStuNum = new JLabel("학번");
		JLabel jlblStuName = new JLabel("이름");
		JLabel jlblStuTel = new JLabel("전화번호");
		JLabel jlblStuCourse = new JLabel("수강과정");
		JLabel jlbTitle = new JLabel();
		Border grayBorder = BorderFactory.createLineBorder(new Color(230, 230, 230), 2);
		jlbTitle.setIcon(new ImageIcon(getClass().getResource("/images/logo.png")));
		
		
		jlblStuImg = new JLabel();
		ImageIcon ii = new ImageIcon();
		jtfStuNumData = new JTextField();
		jtfStuNumData.setEditable(false);
		jtfStuNameData = new JTextField();
		jtfStuNameData.setEditable(false);
		jtfStuTelData = new JTextField();
		jtfStuTelData.setEditable(false);
		jtfStuCourseData = new JTextField();
		jtfStuCourseData.setEditable(false);
		
		jbtnModifyStuInfo = new JButton("개인정보변경");
		jbtnSelectCourse = new JButton("수강신청"); 
		jbtnMgrCourse = new JButton("학업관리");
		
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		
		setLayout(null);
		
		jlbTitle.setSize(350,50);
		jlbTitle.setLocation(150, 25);
		add(jlbTitle);
		
		jlblStuNum.setSize(200,50);
		jlblStuNum.setLocation(140,80);
		jlblStuNum.setFont(font);
		add(jlblStuNum);
		
		jlblStuName.setSize(200,50);
		jlblStuName.setLocation(140,115);
		jlblStuName.setFont(font);
		add(jlblStuName);
		
		jlblStuTel.setSize(200,50);
		jlblStuTel.setLocation(140,150);
		jlblStuTel.setFont(font);
		add(jlblStuTel);
		
		jlblStuCourse.setSize(200,50);
		jlblStuCourse.setLocation(140,185);
		jlblStuCourse.setFont(font);
		add(jlblStuCourse);
		
		
		jtfStuNumData.setSize(150,30);
		jtfStuNumData.setLocation(220,90);
		jtfStuNumData.setFont(font);
		add(jtfStuNumData);
		
		jtfStuNameData.setSize(150,30);
		jtfStuNameData.setLocation(220,125);
		jtfStuNameData.setFont(font);
		add(jtfStuNameData);
		
		jtfStuTelData.setSize(150,30);
		jtfStuTelData.setLocation(220,160);
		jtfStuTelData.setFont(font);
		add(jtfStuTelData);
		
		jtfStuCourseData.setSize(150,30);
		jtfStuCourseData.setLocation(220,195);
		jtfStuCourseData.setFont(font);
		add(jtfStuCourseData);
		
		
		jlblStuImg.setSize(100,120);
		jlblStuImg.setLocation(20, 100);
		jlblStuImg.setFont(font);
		jlblStuImg.setBackground(Color.BLACK);
		jlblStuImg.setOpaque(true);
		jlblStuImg.setHorizontalAlignment(SwingConstants.CENTER);
		add(jlblStuImg);
		
		
		
		jbtnModifyStuInfo.setSize(140,30);
		jbtnModifyStuInfo.setLocation(380,90);
		jbtnModifyStuInfo.setFont(font);		
		add(jbtnModifyStuInfo);
		
		jbtnSelectCourse.setSize(140,30);
		jbtnSelectCourse.setLocation(380,125);
		jbtnSelectCourse.setFont(font);		
		add(jbtnSelectCourse);
		
		jbtnMgrCourse.setSize(140,30);
		jbtnMgrCourse.setLocation(380,160);
		jbtnMgrCourse.setFont(font);		
		add(jbtnMgrCourse);
		
		
		jbtnModifyStuInfo.setBorder(BorderFactory.createEmptyBorder());
		jbtnSelectCourse.setBorder(BorderFactory.createEmptyBorder());
		jbtnMgrCourse.setBorder(BorderFactory.createEmptyBorder());
		
		jbtnModifyStuInfo.setBackground(new Color(106,189,229));
		jbtnSelectCourse.setBackground(new Color(106,189,229));
		jbtnMgrCourse.setBackground(new Color(106,189,229));
		jbtnModifyStuInfo.setForeground(Color.white);
		jbtnSelectCourse.setForeground(Color.white);
		jbtnMgrCourse.setForeground(Color.white);
		

		jtfStuNameData.setBorder(grayBorder);
		jtfStuNumData.setBorder(grayBorder);
		jtfStuTelData.setBorder(grayBorder);
		jtfStuCourseData.setBorder(grayBorder);
		
		
		StuInfoDesignEvt side = new StuInfoDesignEvt(this);
		jbtnModifyStuInfo.addActionListener(side);
		jbtnSelectCourse.addActionListener(side);
		jbtnMgrCourse.addActionListener(side);
		getContentPane().setBackground(new Color(247, 247, 249));
		
		addWindowListener(side);		
		
		setResizable(false);
		setBounds(150,150,560,350);
		setVisible(true);
	}

	public JLabel getJlblStuImg() {
		return jlblStuImg;
	}

	public void setJlblStuImg(JLabel jlblStuImg) {
		this.jlblStuImg = jlblStuImg;
	}

	public JTextField getJtfStuNumData() {
		return jtfStuNumData;
	}

	public void setJtfStuNumData(JTextField jtfStuNumData) {
		this.jtfStuNumData = jtfStuNumData;
	}

	public JTextField getJtfStuNameData() {
		return jtfStuNameData;
	}

	public void setJtfStuNameData(JTextField jtfStuNameData) {
		this.jtfStuNameData = jtfStuNameData;
	}

	public JTextField getJtfStuTelData() {
		return jtfStuTelData;
	}

	public void setJtfStuTelData(JTextField jtfStuTelData) {
		this.jtfStuTelData = jtfStuTelData;
	}

	public JTextField getJtfStuCourseData() {
		return jtfStuCourseData;
	}

	public void setJtfStuCourseData(JTextField jtfStuCourseData) {
		this.jtfStuCourseData = jtfStuCourseData;
	}

	public JButton getJbtnModifyStuInfo() {
		return jbtnModifyStuInfo;
	}

	public void setJbtnModifyStuInfo(JButton jbtnModifyStuInfo) {
		this.jbtnModifyStuInfo = jbtnModifyStuInfo;
	}

	public JButton getJbtnSelectCourse() {
		return jbtnSelectCourse;
	}

	public void setJbtnSelectCourse(JButton jbtnSelectCourse) {
		this.jbtnSelectCourse = jbtnSelectCourse;
	}

	public JButton getJbtnMgrCourse() {
		return jbtnMgrCourse;
	}

	public void setJbtnMgrCourse(JButton jbtnMgrCourse) {
		this.jbtnMgrCourse = jbtnMgrCourse;
	}
		
	

}

