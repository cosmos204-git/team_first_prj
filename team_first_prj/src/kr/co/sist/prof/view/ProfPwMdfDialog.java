package kr.co.sist.prof.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.co.sist.prof.controller.ProfPwMdfDialogEvt;

public class ProfPwMdfDialog extends JDialog{
	
	private JLabel jlblCurrentProfPw, jlblNewProfPw, jlblConfirmnProfPw;
	private JTextField jtfCurrentProfPw, jtfNewProfPw, jtfConfirmnProfPw;
	private JButton jbtnModify, jbtnClose; 
	
	public ProfPwMdfDialog(ProfInfoModifyDesign pimd, boolean modal) {
		super(pimd,"교수 비밀번호 변경",modal);
		setLayout(new BorderLayout(10,10));
		
		//비밀번호들 패널
		JPanel jpStuPwMdf = new JPanel(new GridLayout(6,1,5,5));
		jpStuPwMdf.setBorder(new EmptyBorder(15, 30, 15, 30)); 
		
		jlblCurrentProfPw = new JLabel("현재 비밀번호");
		jlblNewProfPw = new JLabel("새로운 비밀번호");
		jlblConfirmnProfPw = new JLabel("새로운 비밀번호 확인");
		
		jtfCurrentProfPw = new JPasswordField();
		jtfNewProfPw = new JPasswordField();
		jtfConfirmnProfPw = new JPasswordField();
		
		jpStuPwMdf.add(jlblCurrentProfPw);
		jpStuPwMdf.add(jtfCurrentProfPw);
		jpStuPwMdf.add(jlblNewProfPw);
		jpStuPwMdf.add(jtfNewProfPw);
		jpStuPwMdf.add(jlblConfirmnProfPw);
		jpStuPwMdf.add(jtfConfirmnProfPw);
		
		add(jpStuPwMdf, BorderLayout.CENTER);
		
		//버튼 패널
		JPanel jpButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		jpButton.setBorder(new EmptyBorder(15, 30, 15, 30));
		
		jbtnModify = new JButton("변경");
		jbtnClose = new JButton("닫기");
		
		jpButton.add(jbtnModify);
		jpButton.add(jbtnClose);
		
		add(jpButton, BorderLayout.SOUTH);
		
	    Font font = new Font("맑은고딕",Font.BOLD,15);
	    
	    jlblCurrentProfPw.setFont(font);
	    jlblNewProfPw.setFont(font);
	    jlblConfirmnProfPw.setFont(font);
		jtfCurrentProfPw.setFont(font);
		jtfNewProfPw.setFont(font);
		jtfConfirmnProfPw.setFont(font);
	    
		ProfPwMdfDialogEvt ppmde = new ProfPwMdfDialogEvt(this);
		jbtnModify.addActionListener(ppmde);
		jbtnClose.addActionListener(ppmde);
		addWindowListener(ppmde);
		
		
		
	    setBounds(600,300,600,350);
		setVisible(true);	     
		
	}//StuPwMdfDialog

	public JTextField getJtfCurrentProfPw() {
		return jtfCurrentProfPw;
	}

	public void setJtfCurrentProfPw(JTextField jtfCurrentProfPw) {
		this.jtfCurrentProfPw = jtfCurrentProfPw;
	}

	public JTextField getJtfNewProfPw() {
		return jtfNewProfPw;
	}

	public void setJtfNewProfPw(JTextField jtfNewProfPw) {
		this.jtfNewProfPw = jtfNewProfPw;
	}

	public JTextField getJtfConfirmnProfPw() {
		return jtfConfirmnProfPw;
	}

	public void setJtfConfirmnProfPw(JTextField jtfConfirmnProfPw) {
		this.jtfConfirmnProfPw = jtfConfirmnProfPw;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
	}

	public void setJbtnModify(JButton jbtnModify) {
		this.jbtnModify = jbtnModify;
	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}

	public void setJbtnClose(JButton jbtnClose) {
		this.jbtnClose = jbtnClose;
	}


}//class
