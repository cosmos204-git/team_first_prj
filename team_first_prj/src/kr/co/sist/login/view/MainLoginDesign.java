package kr.co.sist.login.view;

import java.awt.Font;
import java.awt.GridLayout;

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
		Font font = new Font("맑은 고딕",Font.BOLD,40);
		JLabel jlbTitle = new JLabel("권한을 선택하세요.");
		MainLoginDesignEvt mlde = new MainLoginDesignEvt(this);
		
		
		jlbTitle.setFont(font);
		jbtnStuLogin.setFont(font);
		jbtnProfLogin.setFont(font);
		jbtnAdminLogin.setFont(font);
		
		jpNorth.add(jlbTitle);
		
		jlblContext.setLayout(new GridLayout(1,3));
		jlblContext.add(jbtnStuLogin);
		jlblContext.add(jbtnProfLogin);
		jlblContext.add(jbtnAdminLogin);
		
		add("North", jpNorth);
		add("Center", jlblContext);
		
		
		jbtnStuLogin.addActionListener(mlde);
		jbtnProfLogin.addActionListener(mlde);
		jbtnAdminLogin.addActionListener(mlde);
		
		addWindowListener(mlde);
		
		
		setBounds(200,200, 500, 500);
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
