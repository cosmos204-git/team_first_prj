package kr.co.sist.stu.view;

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

import kr.co.sist.stu.controller.StuPwMdfDialogEvt;

public class StuPwMdfDialog extends JDialog{
	
	private JLabel jlblCurrentStuPw, jlblNewStuPw, jlblConfirmnStuPw;
	private JTextField jtfCurrentStuPw, jtfNewStuPw, jtfConfirmnStuPw;
	private JButton jbtnModify, jbtnClose; 
	

	
	public StuPwMdfDialog(StuInfoModifyDesign simd, boolean modal) {
		super(simd,"학생 비밀번호 변경",modal);
		setLayout(new BorderLayout(10,10));
		
		//비밀번호들 패널
		JPanel jpStuPwMdf = new JPanel(new GridLayout(6,1,5,5));
		jpStuPwMdf.setBorder(new EmptyBorder(15, 30, 15, 30)); 
		
		jlblCurrentStuPw = new JLabel("현재 비밀번호");
		jlblNewStuPw = new JLabel("새로운 비밀번호");
		jlblConfirmnStuPw = new JLabel("새로운 비밀번호 확인");
		
		
		jtfCurrentStuPw = new JPasswordField(20);
		jtfNewStuPw = new JPasswordField(20);
		jtfConfirmnStuPw = new JPasswordField(20);

		
		jpStuPwMdf.add(jlblCurrentStuPw);
		jpStuPwMdf.add(jtfCurrentStuPw);
		jpStuPwMdf.add(jlblNewStuPw);
		jpStuPwMdf.add(jtfNewStuPw);
		jpStuPwMdf.add(jlblConfirmnStuPw);
		jpStuPwMdf.add(jtfConfirmnStuPw);
		
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
	    
	    jlblCurrentStuPw.setFont(font);
	    jlblNewStuPw.setFont(font);
	    jlblConfirmnStuPw.setFont(font);
		jtfCurrentStuPw.setFont(font);
		jtfNewStuPw.setFont(font);
		jtfConfirmnStuPw.setFont(font);
	    
		StuPwMdfDialogEvt spmde = new StuPwMdfDialogEvt(this);
		jbtnModify.addActionListener(spmde);
		jbtnClose.addActionListener(spmde);
		addWindowListener(spmde);
		
	    
	    setBounds(600,300,600,350);
		setVisible(true);	     
		
	}//StuPwMdfDialog

	

	public JTextField getJtfCurrentStuPw() {
		return jtfCurrentStuPw;
	}



	public JTextField getJtfNewStuPw() {
		return jtfNewStuPw;
	}



	public JTextField getJtfConfirmnStuPw() {
		return jtfConfirmnStuPw;
	}



	public JButton getJbtnModify() {
		return jbtnModify;
	}



	public JButton getJbtnClose() {
		return jbtnClose;
	}
	
	
}//class
