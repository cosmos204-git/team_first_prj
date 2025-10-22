package kr.co.sist.stu.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.co.sist.stu.controller.StuInfoDesignEvt;

public class StuInfoDesign extends JFrame {
	private JLabel jlblStuImg;
	private JTextField jtfStuNumData,jtfStuNameData,jtfStuTelData,jtfStuCourseData;
	private JButton jbtnModifyStuInfo, jbtnSelectCourse, jbtnMgrCourse;

	public StuInfoDesign() {
		super("학생 기본 화면");
		
		JLabel jlblStuNum = new JLabel("학번");
		JLabel jlblStuName = new JLabel("이름");
		JLabel jlblStuTel = new JLabel("전화번호");
		JLabel jlblStuCourse = new JLabel("수강과정");
		
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
		jbtnSelectCourse = new JButton("수강신청"); 		jbtnMgrCourse = new JButton("학업관리");
		
		Font font = new Font("맑은고딕",Font.BOLD,15);
		
		setLayout(null);
		
		jlblStuNum.setSize(200,50);
		jlblStuNum.setLocation(140,20);
		jlblStuNum.setFont(font);
		add(jlblStuNum);
		
		
		jtfStuNumData.setSize(150,30);
		jtfStuNumData.setLocation(250,30);
		jtfStuNumData.setFont(font);
		add(jtfStuNumData);
		
		jtfStuNameData.setSize(150,30);
		jtfStuNameData.setLocation(250,70);
		jtfStuNameData.setFont(font);
		add(jtfStuNameData);
		
		jtfStuTelData.setSize(150,30);
		jtfStuTelData.setLocation(250,110);
		jtfStuTelData.setFont(font);
		add(jtfStuTelData);
		
		jtfStuCourseData.setSize(150,30);
		jtfStuCourseData.setLocation(250,150);
		jtfStuCourseData.setFont(font);
		add(jtfStuCourseData);
		
		
		jlblStuName.setSize(200,50);
		jlblStuName.setLocation(140,60);
		jlblStuName.setFont(font);
		add(jlblStuName);
		
		jlblStuTel.setSize(200,50);
		jlblStuTel.setLocation(140,100);
		jlblStuTel.setFont(font);
		add(jlblStuTel);
		
		jlblStuCourse.setSize(200,50);
		jlblStuCourse.setLocation(140,140);
		jlblStuCourse.setFont(font);
		add(jlblStuCourse);
		
		jlblStuImg.setSize(100,120);
		jlblStuImg.setLocation(20, 38);
		jlblStuImg.setFont(font);
		jlblStuImg.setBackground(Color.BLACK);
		jlblStuImg.setOpaque(true);
		jlblStuImg.setHorizontalAlignment(SwingConstants.CENTER);
		add(jlblStuImg);
		
		
		
		jbtnModifyStuInfo.setSize(150,30);
		jbtnModifyStuInfo.setLocation(420,30);
		jbtnModifyStuInfo.setFont(font);		
		add(jbtnModifyStuInfo);
		
		jbtnSelectCourse.setSize(150,30);
		jbtnSelectCourse.setLocation(420,70);
		jbtnSelectCourse.setFont(font);		
		add(jbtnSelectCourse);
		
		jbtnMgrCourse.setSize(150,30);
		jbtnMgrCourse.setLocation(420,110);
		jbtnMgrCourse.setFont(font);		
		add(jbtnMgrCourse);
		
		StuInfoDesignEvt side = new StuInfoDesignEvt(this);
		jbtnModifyStuInfo.addActionListener(side);
		jbtnSelectCourse.addActionListener(side);
		jbtnMgrCourse.addActionListener(side);
		
		
		addWindowListener(side);		
		
		
		setBounds(300,300,600,300);
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
