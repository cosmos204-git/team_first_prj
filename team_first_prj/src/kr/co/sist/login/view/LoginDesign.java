package kr.co.sist.login.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import kr.co.sist.login.controller.LoginDesignEvt;

public class LoginDesign extends JFrame{
	
	
	private int loginFlag;
	
	private MainLoginDesign mld;
	
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

	public LoginDesign(MainLoginDesign mld, int loginFlag, int x, int y, int z) {
		super("로그인 화면");
		this.loginFlag = loginFlag;
		this.mld=mld;
		
		JLabel jlbNum = new JLabel();
		JLabel jlbPw = new JLabel();
		Font font = new Font("맑은 고딕",Font.BOLD,20);
		Border grayBorder = BorderFactory.createLineBorder(new Color(230, 230, 230), 2);
		
		jtfNum = new JTextField();
		jtfPw = new JPasswordField();
		jbtnLogin = new JButton("로그인");
		
		JLabel jlbTitle = new JLabel();
		
		jlbTitle.setIcon(new ImageIcon(getClass().getResource("/images/logo.png")));
		jlbNum.setIcon(new ImageIcon(getClass().getResource("/images/id_logo.png")));
		jlbPw.setIcon(new ImageIcon(getClass().getResource("/images/pw_logo.png")));
		
		setLayout(null);
		
		jlbTitle.setBounds(120, 25, 220, 50);
		add(jlbTitle);
		
		jlbNum.setSize(45,45);
		jlbNum.setLocation(50,85);
		jlbNum.setFont(font);
		add(jlbNum);
		
		jlbPw.setSize(45,45);
		jlbPw.setLocation(50,145);
		jlbPw.setFont(font);
		add(jlbPw);
		
		jtfNum.setSize(200,40);
		jtfNum.setLocation(100,90);
		jtfNum.setFont(font);
		add(jtfNum);
		
		jtfPw.setSize(200,40);
		jtfPw.setLocation(100,150);
		jtfPw.setFont(font);
		add(jtfPw);
		
		jbtnLogin.setSize(100,100);
		jbtnLogin.setLocation(315,90);
		jbtnLogin.setFont(font);
		jbtnLogin.setBackground(new Color(x,y,z));
		jbtnLogin.setForeground(Color.white);
		add(jbtnLogin);

		
		
		jtfNum.setBorder(grayBorder);
		jtfPw.setBorder(grayBorder);

		jbtnLogin.setBorder(BorderFactory.createEmptyBorder());
//		jtfPw.setBorder(BorderFactory.createEmptyBorder());
//		jtfNum.setBorder(BorderFactory.createEmptyBorder());
		jtfNum.requestFocus();
		
		LoginDesignEvt lde = new LoginDesignEvt(this);
		
		jbtnLogin.addActionListener(lde);
		addWindowListener(lde);
		
		
		getContentPane().setBackground(new Color(247, 247, 249));
		setResizable(false);
		setBounds(mld.getX()+50,mld.getY()+50,485,280);
		setVisible(true);
		

		
	}
	
	
	
	
	
	

}
