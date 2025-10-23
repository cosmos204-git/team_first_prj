package kr.co.sist.admin.view;


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.admin.controller.StuAddDialogEvt;

public class StuAddDialog extends JDialog {
	
	private JLabel jlbStuNum,jlbStuName,jlbStuTel;
	private JTextField jtfStuNum,jtfStuName,jtfStuTel;
	private JButton jbtnAdd,jbtnClose;
	



	public StuAddDialog(AdminStuMgrDesign asmd, boolean modal, int newStuNum) {
		super(asmd,"관리자 - 학생관리(추가)",modal);
		

		jlbStuNum = new JLabel("학번");
		jlbStuName = new JLabel("이름");
		jlbStuTel = new JLabel("휴대폰번호");
		
		jbtnAdd= new JButton("추가완료");
		jbtnClose= new JButton("닫기");
		
		jtfStuNum = new JTextField(String.valueOf(newStuNum));
		jtfStuName= new JTextField();
		jtfStuTel= new JTextField();

		
		
		jtfStuNum.setEditable(false);
		jtfStuName.requestFocus();
		
		//font 지정
		
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		

		jlbStuNum.setFont(font);
		jlbStuName.setFont(font);
		jlbStuTel.setFont(font);
		

		
		jtfStuNum.setFont(font);
		jtfStuName.setFont(font);
		jtfStuTel.setFont(font);
	
		
		jbtnAdd.setFont(font);
		jbtnClose.setFont(font);
		
		setLayout(null);
		
		

		
		jlbStuNum.setBounds(155,13,100,100);
		jlbStuName.setBounds(jlbStuNum.getX(),jlbStuNum.getY()+50,100,100);
		jlbStuTel.setBounds(jlbStuNum.getX(),jlbStuName.getY()+50,100,100);

		
		add(jlbStuNum);
		add(jlbStuName);
		add(jlbStuTel);
		
		jtfStuNum.setBounds(250,50,150,30);
		jtfStuName.setBounds(250,jtfStuNum.getY()+50,150,30);
		jtfStuTel.setBounds(250,jtfStuName.getY()+50,150,30);
		
		jbtnAdd.setBounds(155,215,100,25);
		jbtnClose.setBounds(300,215,100,25);

		
		
		add(jbtnClose);
		add(jbtnAdd);
		
		add(jtfStuNum);
		add(jtfStuName);
		add(jtfStuTel);
		
		//윈도우, 액션 리스너 추가 
		StuAddDialogEvt sade= new StuAddDialogEvt(this);
		jbtnAdd.addActionListener(sade);
		jbtnClose.addActionListener(sade);
		addWindowListener(sade);
		
		setSize(600,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}//StuModifyDialog
	
	
	public JLabel getJlbStuNum() {
		return jlbStuNum;
	}


	public JLabel getJlbStuName() {
		return jlbStuName;
	}


	public JLabel getJlbStuTel() {
		return jlbStuTel;
	}


	public JTextField getJtfStuNum() {
		return jtfStuNum;
	}


	public JTextField getJtfStuName() {
		return jtfStuName;
	}


	public JTextField getJtfStuTel() {
		return jtfStuTel;
	}


	public JButton getJbtnAdd() {
		return jbtnAdd;
	}


	public JButton getJbtnClose() {
		return jbtnClose;
	}


//	public static void main(String[] args) {
//		new StuAddDialog();
//	}//main

}//class
