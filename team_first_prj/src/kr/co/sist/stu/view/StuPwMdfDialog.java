package kr.co.sist.stu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import kr.co.sist.stu.controller.StuPwMdfDialogEvt;

public class StuPwMdfDialog extends JDialog{
	
	private JLabel jlblCurrentStuPw, jlblNewStuPw, jlblConfirmnStuPw;
	private JTextField jtfCurrentStuPw, jtfNewStuPw, jtfConfirmnStuPw;
	private JButton jbtnModify, jbtnClose; 
	

	
	public StuPwMdfDialog(StuInfoModifyDesign simd, boolean modal) {
		super(simd,"학생 비밀번호 변경",modal);
		
		
		Border grayBorder = BorderFactory.createLineBorder(new Color(230, 230, 230), 2);
		JLabel jlbTitle = new JLabel();
		jlbTitle.setIcon(new ImageIcon("src/images/logo.png"));
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		setLayout(null);
		
		jlbTitle.setBounds(110, 25, 250, 50);
		add(jlbTitle);
		
		jlblCurrentStuPw = new JLabel("현재 비밀번호");
		jlblNewStuPw = new JLabel("새로운 비밀번호");
		jlblConfirmnStuPw = new JLabel("새로운 비밀번호 확인");
		

		jlblCurrentStuPw.setSize(150,45);
		jlblCurrentStuPw.setLocation(50,85);
		jlblCurrentStuPw.setFont(font);
		add(jlblCurrentStuPw);
		
		jlblNewStuPw.setSize(150,45);
		jlblNewStuPw.setLocation(50,145);
		jlblNewStuPw.setFont(font);
		add(jlblNewStuPw);

		jlblConfirmnStuPw.setSize(150,45);
		jlblConfirmnStuPw.setLocation(50,205);
		jlblConfirmnStuPw.setFont(font);
		add(jlblNewStuPw);
		
		
		jtfCurrentStuPw = new JPasswordField(20);
		jtfNewStuPw = new JPasswordField(20);
		jtfConfirmnStuPw = new JPasswordField(20);
		
		
		jtfCurrentStuPw.setSize(200,40);
		jtfCurrentStuPw.setLocation(210,90);
		jtfCurrentStuPw.setFont(font);
		add(jtfCurrentStuPw);
		
		jtfNewStuPw.setSize(200,40);
		jtfNewStuPw.setLocation(210,150);
		jtfNewStuPw.setFont(font);
		add(jtfNewStuPw);
		
		jtfConfirmnStuPw.setSize(200,40);
		jtfConfirmnStuPw.setLocation(210,210);
		jtfConfirmnStuPw.setFont(font);
		add(jtfConfirmnStuPw);
		
		
		
		add(jlblCurrentStuPw);
		add(jtfCurrentStuPw);
		add(jlblNewStuPw);
		add(jtfNewStuPw);
		add(jlblConfirmnStuPw);
		add(jtfConfirmnStuPw);
		
		
		
		jbtnModify = new JButton("변경");
		jbtnClose = new JButton("닫기");
		
		
		jtfCurrentStuPw.setBorder(grayBorder);
		jtfNewStuPw.setBorder(grayBorder);
		jtfConfirmnStuPw.setBorder(grayBorder);
		

		jbtnClose.setBorder(BorderFactory.createEmptyBorder());
		jbtnModify.setBorder(BorderFactory.createEmptyBorder());
		
		jbtnModify.setSize(100,40);
		jbtnModify.setLocation(100,280);
		jbtnModify.setFont(font);
		jbtnModify.setBackground(new Color(106,189,229));
		jbtnModify.setForeground(Color.white);
		add(jbtnModify);
		
		jbtnClose.setSize(100,40);
		jbtnClose.setLocation(240,280);
		jbtnClose.setFont(font);
		jbtnClose.setBackground(new Color(106,189,229));
		jbtnClose.setForeground(Color.white);
		add(jbtnClose);
		
	    
	    
		StuPwMdfDialogEvt spmde = new StuPwMdfDialogEvt(this);
		jbtnModify.addActionListener(spmde);
		jbtnClose.addActionListener(spmde);
		addWindowListener(spmde);
		
		getContentPane().setBackground(new Color(247, 247, 249));
		setResizable(false);
	    setBounds(simd.getX()+50,simd.getY()+50,480,400);
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
