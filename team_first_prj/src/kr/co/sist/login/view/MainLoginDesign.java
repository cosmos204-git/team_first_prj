package kr.co.sist.login.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.login.controller.MainLoginDesignEvt;

public class MainLoginDesign extends JFrame {

	private JButton jbtnStuLogin;
	private JButton jbtnProfLogin;
	private JButton jbtnAdminLogin;


	public MainLoginDesign(){
		super("로그인");
		
		jbtnStuLogin = new JButton("학생");
		jbtnProfLogin = new JButton("교수");
		jbtnAdminLogin = new JButton("관리자");
		
		JPanel jlblContext = new JPanel();
		JPanel jpNorth = new JPanel();
		Font font = new Font("맑은 고딕",Font.BOLD,20);
		Font titleFont = new Font("맑은 고딕", Font.BOLD,30);
		JLabel jlbTitle = new JLabel();
		MainLoginDesignEvt mlde = new MainLoginDesignEvt(this);
		
		jlbTitle.setFont(titleFont);
		jlbTitle.setIcon(new ImageIcon(getClass().getResource("/images/logo.png")));
		jbtnStuLogin.setFont(font);
		jbtnProfLogin.setFont(font);
		jbtnAdminLogin.setFont(font);
		
		
		jbtnStuLogin.setForeground(Color.white);
		jbtnProfLogin.setForeground(Color.white);
		jbtnAdminLogin.setForeground(Color.white);
		
		jbtnStuLogin.setBorder(BorderFactory.createEmptyBorder());
		jbtnProfLogin.setBorder(BorderFactory.createEmptyBorder());
		jbtnAdminLogin.setBorder(BorderFactory.createEmptyBorder());
		
		jlbTitle.setBounds(125, 25, 350, 50);
		jbtnStuLogin.setBounds(80, 100, 100, 100);
		jbtnProfLogin.setBounds(200, 100, 100, 100);
		jbtnAdminLogin.setBounds(320, 100, 100, 100);
		
		
		
		jbtnStuLogin.setBackground(new Color(106,189,229));
		jbtnProfLogin.setBackground(new Color(96,186,42));
		jbtnAdminLogin.setBackground(new Color(55,101,163));
		getContentPane().setBackground(new Color(247, 247, 249));
		
		setLayout(null);
		add(jlbTitle);
		add(jbtnStuLogin);
		add(jbtnProfLogin);
		add(jbtnAdminLogin);
		
		add("North", jpNorth);
		add("Center", jlblContext);
		
		
		jbtnStuLogin.addActionListener(mlde);
		jbtnProfLogin.addActionListener(mlde);
		jbtnAdminLogin.addActionListener(mlde);
		
		addWindowListener(mlde);
		
		setResizable(false);
		setBounds(100,100, 520, 300);
		setVisible(true);
		
	}


	public JButton getJbtnStuLogin() {
		return jbtnStuLogin;
	}


	public JButton getJbtnProfLogin() {
		return jbtnProfLogin;
	}


	public JButton getJbtnAdminLogin() {
		return jbtnAdminLogin;
	}
	
	
}
