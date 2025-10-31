package kr.co.sist.prof.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import kr.co.sist.prof.controller.ProfPwMdfDialogEvt;

public class ProfPwMdfDialog extends JDialog{
	
	private JLabel jlblCurrentProfPw, jlblNewProfPw, jlblConfirmnProfPw;
	private JTextField jtfCurrentProfPw, jtfNewProfPw, jtfConfirmnProfPw;
	private JButton jbtnModify, jbtnClose; 
	
	public ProfPwMdfDialog(ProfInfoModifyDesign pimd, boolean modal) {
		super(pimd,"교수 비밀번호 변경",modal);
		Border grayBorder = BorderFactory.createLineBorder(new Color(230, 230, 230), 2);
		JLabel jlbTitle = new JLabel();
		jlbTitle.setIcon(new ImageIcon("src/images/logo.png"));
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		setLayout(null);
		
		jlbTitle.setBounds(110, 25, 250, 50);
		add(jlbTitle);
		
		jlblCurrentProfPw = new JLabel("현재 비밀번호");
		jlblNewProfPw = new JLabel("새로운 비밀번호");
		jlblConfirmnProfPw = new JLabel("새로운 비밀번호 확인");
		

		jlblCurrentProfPw.setSize(150,45);
		jlblCurrentProfPw.setLocation(50,85);
		jlblCurrentProfPw.setFont(font);
		add(jlblCurrentProfPw);
		
		jlblNewProfPw.setSize(150,45);
		jlblNewProfPw.setLocation(50,145);
		jlblNewProfPw.setFont(font);
		add(jlblNewProfPw);

		jlblConfirmnProfPw.setSize(150,45);
		jlblConfirmnProfPw.setLocation(50,205);
		jlblConfirmnProfPw.setFont(font);
		add(jlblNewProfPw);
		
		
		jtfCurrentProfPw = new JPasswordField(20);
		jtfNewProfPw = new JPasswordField(20);
		jtfConfirmnProfPw = new JPasswordField(20);
		
		
		jtfCurrentProfPw.setSize(200,40);
		jtfCurrentProfPw.setLocation(210,90);
		jtfCurrentProfPw.setFont(font);
		add(jtfCurrentProfPw);
		
		jtfNewProfPw.setSize(200,40);
		jtfNewProfPw.setLocation(210,150);
		jtfNewProfPw.setFont(font);
		add(jtfNewProfPw);
		
		jtfConfirmnProfPw.setSize(200,40);
		jtfConfirmnProfPw.setLocation(210,210);
		jtfConfirmnProfPw.setFont(font);
		add(jtfConfirmnProfPw);
		
		
		
		add(jlblCurrentProfPw);
		add(jtfCurrentProfPw);
		add(jlblNewProfPw);
		add(jtfNewProfPw);
		add(jlblConfirmnProfPw);
		add(jtfConfirmnProfPw);
		
		
		
		jbtnModify = new JButton("변경");
		jbtnClose = new JButton("닫기");
		
		
		jtfCurrentProfPw.setBorder(grayBorder);
		jtfNewProfPw.setBorder(grayBorder);
		jtfConfirmnProfPw.setBorder(grayBorder);
		

		jbtnClose.setBorder(BorderFactory.createEmptyBorder());
		jbtnModify.setBorder(BorderFactory.createEmptyBorder());
		
		jbtnModify.setSize(100,40);
		jbtnModify.setLocation(100,280);
		jbtnModify.setFont(font);
		jbtnModify.setBackground(new Color(96,186,42));
		jbtnModify.setForeground(Color.white);
		add(jbtnModify);
		
		jbtnClose.setSize(100,40);
		jbtnClose.setLocation(240,280);
		jbtnClose.setFont(font);
		jbtnClose.setBackground(new Color(96,186,42));
		jbtnClose.setForeground(Color.white);
		add(jbtnClose);
	    
		ProfPwMdfDialogEvt ppmde = new ProfPwMdfDialogEvt(this);
		jbtnModify.addActionListener(ppmde);
		jbtnClose.addActionListener(ppmde);
		addWindowListener(ppmde);
		
		getContentPane().setBackground(new Color(247, 247, 249));
		setResizable(false);
		
		setBounds(pimd.getX()+50,pimd.getY()+50,480,400);
		setVisible(true);	     
		
	}//ProfPwMdfDialog

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
