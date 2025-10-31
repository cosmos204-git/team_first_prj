package kr.co.sist.prof.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.co.sist.prof.controller.ProInfoModifyDesignEvt;



public class ProfInfoModifyDesign extends JDialog {
	
	private JLabel jlblProfImg;
	
	private JTextField jtfProfNumData, jtfProfNameData, jtfProfCourseData;
	private JTextField jtfProfEmailData, jtfProfTelData;
	
	private JFileChooser jfcAddProfPic;
	private JButton jbtnAddImg, jbtnModifyProfPW, jbtnModifyProfInfo, jbtnClose;

	private ProfInfoDesign pid;
	
	public ProfInfoModifyDesign(ProfInfoDesign pid, boolean modal) {
		super(pid,"교수 기본 화면",true);
		
		this.pid = pid;
		
		JLabel jlblProfNum = new JLabel("교번");
		JLabel jlblProfName = new JLabel("이름");
		JLabel jlblProfTel = new JLabel("전화번호");
		JLabel jlblProfCourse = new JLabel("담당과정");
		JLabel jlblProfEmail = new JLabel("메일주소");
		JLabel jlbTitle = new JLabel();
	      
		jlbTitle.setIcon(new ImageIcon("src/images/logo.png"));      
	      
		
		jlblProfImg = new JLabel();
		ImageIcon ii = new ImageIcon();
		jtfProfNumData = new JTextField();
		jtfProfNumData.setEditable(false);
		jtfProfNameData = new JTextField();
		jtfProfNameData.setEditable(false);
		jtfProfTelData = new JTextField();
		jtfProfTelData.setEditable(false);
		jtfProfCourseData = new JTextField();
		jtfProfCourseData.setEditable(false);
		jtfProfEmailData = new JTextField();
		
		//사진 추가를 위한 FileChooser
		jfcAddProfPic = new JFileChooser();
		
		jbtnModifyProfPW = new JButton("비밀번호 변경");
		jbtnModifyProfInfo = new JButton("개인정보 수정");
		jbtnClose = new JButton("닫기");
		jbtnAddImg = new JButton("사진첨부");
		
		Font font = new Font("맑은고딕",Font.BOLD,15);
		
		setLayout(null);
		
	
		
		jtfProfNumData.setSize(150,30);
		jtfProfNumData.setLocation(220,90);
		jtfProfNumData.setFont(font);
		add(jtfProfNumData);
		
		jtfProfNameData.setSize(150,30);
		jtfProfNameData.setLocation(220,125);
		jtfProfNameData.setFont(font);
		add(jtfProfNameData);
		
		jtfProfTelData.setSize(150,30);
		jtfProfTelData.setLocation(220,160);
		jtfProfTelData.setFont(font);
		add(jtfProfTelData);
		
		jtfProfCourseData.setSize(150,30);
		jtfProfCourseData.setLocation(220,195);
		jtfProfCourseData.setFont(font);
		add(jtfProfCourseData);
		
		jtfProfEmailData.setSize(150,30);
		jtfProfEmailData.setLocation(220,230);
		jtfProfEmailData.setFont(font);
		jtfProfEmailData.requestFocusInWindow();
		add(jtfProfEmailData);
		
		jlbTitle.setSize(350,50);
		jlbTitle.setLocation(150, 25);
		add(jlbTitle);
		
		jlblProfNum.setSize(200,50);
		jlblProfNum.setLocation(140,80);
		jlblProfNum.setFont(font);
		add(jlblProfNum);
		
		
		
		jlblProfName.setSize(200,50);
		jlblProfName.setLocation(140,115);
		jlblProfName.setFont(font);
		add(jlblProfName);
		
		jlblProfTel.setSize(200,50);
		jlblProfTel.setLocation(140,150);
		jlblProfTel.setFont(font);
		add(jlblProfTel);

		jlblProfCourse.setSize(200,50);
		jlblProfCourse.setLocation(140,185);
		jlblProfCourse.setFont(font);
		add(jlblProfCourse);
		
		jlblProfEmail.setSize(200,50);
		jlblProfEmail.setLocation(140,220);
		jlblProfEmail.setFont(font);
		add(jlblProfEmail);

				
		jlblProfImg.setSize(100,120);
		jlblProfImg.setLocation(20, 100);
		jlblProfImg.setFont(font);
		jlblProfImg.setBackground(Color.BLACK);
		jlblProfImg.setOpaque(true);
		jlblProfImg.setHorizontalAlignment(SwingConstants.CENTER);
		add(jlblProfImg);
		
		
		jbtnAddImg.setSize(100,30);
		jbtnAddImg.setLocation(20, 220);
		jbtnAddImg.setFont(font);
		add(jbtnAddImg);
		
		jbtnModifyProfPW.setSize(140,30);
		jbtnModifyProfPW.setLocation(380,90);
		jbtnModifyProfPW.setFont(font);		
		add(jbtnModifyProfPW);
		
		jbtnModifyProfInfo.setSize(140,30);
		jbtnModifyProfInfo.setLocation(380,125);
		jbtnModifyProfInfo.setFont(font);		
		add(jbtnModifyProfInfo);
		
		jbtnClose.setSize(140,30);
		jbtnClose.setLocation(380,160);
		jbtnClose.setFont(font);		
		add(jbtnClose);
		
		jbtnModifyProfInfo.setBorder(BorderFactory.createEmptyBorder());
		jbtnAddImg.setBorder(BorderFactory.createEmptyBorder());
		jbtnModifyProfPW.setBorder(BorderFactory.createEmptyBorder());
		jbtnClose.setBorder(BorderFactory.createEmptyBorder());
		
		jbtnModifyProfInfo.setBackground(new Color(96,186,42));
		jbtnAddImg.setBackground(new Color(96,186,42));
		jbtnModifyProfPW.setBackground(new Color(96,186,42));
		jbtnClose.setBackground(new Color(96,186,42));
		jbtnModifyProfInfo.setForeground(Color.white);
		jbtnAddImg.setForeground(Color.white);
		jbtnModifyProfPW.setForeground(Color.white);
		jbtnClose.setForeground(Color.white);
		
		
		ProInfoModifyDesignEvt pimde = new ProInfoModifyDesignEvt(this);
		
        jbtnAddImg.addActionListener(pimde);
        jbtnModifyProfPW.addActionListener(pimde);
        jbtnModifyProfInfo.addActionListener(pimde);
        jbtnClose.addActionListener(pimde);		
		
        setResizable(false);
        addWindowListener(pimde);
        getContentPane().setBackground(new Color(247, 247, 249));
		setBounds(pid.getX()+50,pid.getY()+50,560,350);
		setVisible(true);
		
	}

	public ProfInfoDesign getPid() {
		return pid;
	}

	public void setPid(ProfInfoDesign pid) {
		this.pid = pid;
	}

	public JLabel getJlblProfImg() {
		return jlblProfImg;
	}

	public JTextField getJtfProfNumData() {
		return jtfProfNumData;
	}

	public JTextField getJtfProfNameData() {
		return jtfProfNameData;
	}

	public JTextField getJtfProfCourseData() {
		return jtfProfCourseData;
	}

	public JTextField getJtfProfEmailData() {
		return jtfProfEmailData;
	}

	public JTextField getJtfProfTelData() {
		return jtfProfTelData;
	}

	public JFileChooser getJfcAddProfPic() {
		return jfcAddProfPic;
	}

	public JButton getJbtnAddImg() {
		return jbtnAddImg;
	}

	public JButton getJbtnModifyProfPW() {
		return jbtnModifyProfPW;
	}

	public JButton getJbtnModifyProfInfo() {
		return jbtnModifyProfInfo;
	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}
		

}// class