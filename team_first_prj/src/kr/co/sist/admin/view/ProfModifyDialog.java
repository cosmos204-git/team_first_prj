package kr.co.sist.admin.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.admin.controller.ProfModifyDialogEvt;

public class ProfModifyDialog extends JDialog {
	
	private JLabel jlblProfNum,jlblProfName,jlblProfTel,jlblProfPass;
	private JTextField jtfProfNum,jtfProfName,jtfProfTel,jtfProfPass;
	private JButton jbtnAdd,jbtnClose;
//	private StudentDTO sDTO;
	

	public ProfModifyDialog(AdminProfMgrDesign asmd, boolean modal,int ProfNum) {
		super(asmd,"관리자 - 교수관리(수정)",modal);
		

		jlblProfNum = new JLabel("교번");
		jlblProfName = new JLabel("이름");
		jlblProfTel = new JLabel("휴대폰번호");
		jlblProfPass = new JLabel("비밀번호");
		
		jtfProfNum = new JTextField(String.valueOf(ProfNum));
		jtfProfName= new JTextField();
		jtfProfTel= new JTextField();
		jtfProfPass= new JTextField();
		
		jbtnAdd= new JButton("변경");
		jbtnClose= new JButton("닫기");
		
		jtfProfNum.setEditable(false);
		jtfProfName.requestFocus();
		
		//font 지정
		
		Font font = new Font("맑은 고딕",Font.BOLD,15);

		jlblProfNum.setFont(font);
		jlblProfName.setFont(font);
		jlblProfTel.setFont(font);
		jlblProfPass.setFont(font);
		
		
	
		jtfProfNum.setFont(font);
		jtfProfName.setFont(font);
		jtfProfTel.setFont(font);
		jtfProfPass.setFont(font);
		
		jbtnAdd.setFont(font);
		jbtnClose.setFont(font);
		
		setLayout(null);
		

		jlblProfNum.setBounds(155,3,100,100);
		jlblProfName.setBounds(jlblProfNum.getX(),jlblProfNum.getY()+40,100,100);
		jlblProfTel.setBounds(jlblProfNum.getX(),jlblProfName.getY()+40,100,100);
		jlblProfPass.setBounds(jlblProfNum.getX(),jlblProfTel.getY()+40,100,100);
		
		jtfProfNum.setBounds(250,40,150,30);
		jtfProfName.setBounds(250,jtfProfNum.getY()+40,150,30);
		jtfProfTel.setBounds(250,jtfProfName.getY()+40,150,30);
		jtfProfPass.setBounds(250,jtfProfTel.getY()+40,150,30);
		
		
		jbtnAdd.setBounds(155,220,100,25);
		jbtnClose.setBounds(300,220,100,25);

		add(jbtnAdd);
		add(jbtnClose);
		
		add(jlblProfNum);
		add(jlblProfName);
		add(jlblProfTel);
		add(jlblProfPass);
		
		add(jtfProfNum);
		add(jtfProfName);
		add(jtfProfTel);
		add(jtfProfPass);
		
		//리스너 추가 
		ProfModifyDialogEvt pade =new ProfModifyDialogEvt(this);
		jbtnAdd.addActionListener(pade); 
		jbtnClose.addActionListener(pade); 
		addWindowListener(pade);
		
		
		setSize(600,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}//StuModifyDialog
	
	
	public JLabel getJlblProfNum() {
		return jlblProfNum;
	}


	public JLabel getJlblProfName() {
		return jlblProfName;
	}


	public JLabel getJlblProfTel() {
		return jlblProfTel;
	}


	public JLabel getJlblProfPass() {
		return jlblProfPass;
	}


	public JTextField getJtfProfNum() {
		return jtfProfNum;
	}


	public JTextField getJtfProfName() {
		return jtfProfName;
	}


	public JTextField getJtfProfTel() {
		return jtfProfTel;
	}


	public JTextField getJtfProfPass() {
		return jtfProfPass;
	}


	public JButton getJbtnAdd() {
		return jbtnAdd;
	}


	public JButton getJbtnClose() {
		return jbtnClose;
	}



}//class
