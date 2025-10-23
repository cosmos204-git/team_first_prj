package kr.co.sist.admin.view;


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.admin.controller.ProfAddDialogEvt;

public class ProfAddDialog extends JDialog {
	
	private JLabel jlbProfNum,jlbProfName,jlbProTel;
	private JTextField jtfProNum,jtfProName,jtfProTel;
	private JButton jbtnAdd,jbtnClose;
	


	public ProfAddDialog(AdminProfMgrDesign asmd, boolean modal,int ProfNum) {
		super(asmd,"관리자 - 교수관리(추가)",modal);
		

		jlbProfNum = new JLabel("교번");
		jlbProfName = new JLabel("이름");
		jlbProTel = new JLabel("휴대폰번호");
		
		jbtnAdd= new JButton("추가완료");
		jbtnClose= new JButton("닫기");
		
		jtfProNum = new JTextField(String.valueOf(ProfNum));
		jtfProName= new JTextField();
		jtfProTel= new JTextField();

		
		
		jtfProNum.setEditable(false);
		jtfProName.requestFocus();
		
		//font 지정
		
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		jlbProfNum.setFont(font);
		jlbProfName.setFont(font);
		jlbProTel.setFont(font);
		
		
		jtfProNum.setFont(font);
		jtfProName.setFont(font);
		jtfProTel.setFont(font);
	
		
		jbtnAdd.setFont(font);
		jbtnClose.setFont(font);
		
		
		//위치 선정
		setLayout(null);
		
		jlbProfNum.setBounds(155,13,100,100);
		jlbProfName.setBounds(jlbProfNum.getX(),jlbProfNum.getY()+50,100,100);
		jlbProTel.setBounds(jlbProfNum.getX(),jlbProfName.getY()+50,100,100);

		
		add(jlbProfNum);
		add(jlbProfName);
		add(jlbProTel);
		
		jtfProNum.setBounds(250,50,150,30);
		jtfProName.setBounds(250,jtfProNum.getY()+50,150,30);
		jtfProTel.setBounds(250,jtfProName.getY()+50,150,30);
		
		jbtnAdd.setBounds(155,215,100,25);
		jbtnClose.setBounds(300,215,100,25);

		
		//JFrame 추가 
		add(jbtnClose);
		add(jbtnAdd);
		
		add(jtfProNum);
		add(jtfProName);
		add(jtfProTel);
		
		//리스너 추가 
		ProfAddDialogEvt pade =new  ProfAddDialogEvt(this);
		jbtnAdd.addActionListener(pade); 
		jbtnClose.addActionListener(pade); 
		addWindowListener(pade);
		
		setSize(600,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}//StuModifyDialog
	
	
	
	public JLabel getJlbProfNum() {
		return jlbProfNum;
	}



	public JLabel getJlbProfName() {
		return jlbProfName;
	}



	public JLabel getJlbProTel() {
		return jlbProTel;
	}



	public JTextField getJtfProNum() {
		return jtfProNum;
	}



	public JTextField getJtfProName() {
		return jtfProName;
	}



	public JTextField getJtfProTel() {
		return jtfProTel;
	}



	public JButton getJbtnAdd() {
		return jbtnAdd;
	}



	public JButton getJbtnClose() {
		return jbtnClose;
	}



}//class
