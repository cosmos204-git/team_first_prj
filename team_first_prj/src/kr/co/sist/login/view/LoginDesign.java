package kr.co.sist.login.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.login.controller.LoginDesignEvt;

public class LoginDesign extends JFrame{
	
	
	private int loginFlag;
	
	
	
	public int getLoginFlag() {
		return loginFlag;
	}

	private JButton jbtnLogin;
	private JTextField jtfNum, jtfPw;
	
	public JButton getJbtnLogin() {
		return jbtnLogin;
	}

	public JTextField getJtfNum() {
		return jtfNum;
	}

	public JTextField getJtfPw() {
		return jtfPw;
	}

	public LoginDesign(int loginFlag) {
		super("로그인 화면");
		this.loginFlag = loginFlag;
		
		JLabel jlbNum = new JLabel("아이디");
		JLabel jlbPw = new JLabel("비밀번호");
		Font font = new Font("맑은고딕",Font.BOLD,24);
		
		jtfNum = new JTextField();
		jtfPw = new JPasswordField();
		jbtnLogin = new JButton("로그인");
		
		
		setLayout(null);
		
		
		jlbNum.setSize(200,50);
		jlbNum.setLocation(30,20);
		jlbNum.setFont(font);
		add(jlbNum);
		
		jtfNum.setSize(200,50);
		jtfNum.setLocation(150,20);
		jtfNum.setFont(font);
		add(jtfNum);
		
		jlbPw.setSize(200,50);
		jlbPw.setLocation(30,100);
		jlbPw.setFont(font);
		add(jlbPw);
		
		jtfPw.setSize(200,50);
		jtfPw.setLocation(150,100);
		jtfPw.setFont(font);
		add(jtfPw);
		
		jbtnLogin.setSize(130,130);
		jbtnLogin.setLocation(400,20);
		jbtnLogin.setFont(font);
		add(jbtnLogin);
		

		jtfNum.requestFocus();
		
		LoginDesignEvt lde = new LoginDesignEvt(this);
		
		jbtnLogin.addActionListener(lde);
		addWindowListener(lde);
		
		setBounds(100,100,580,230);
		setVisible(true);
		
	}
	
	
	
	
	
	

}
