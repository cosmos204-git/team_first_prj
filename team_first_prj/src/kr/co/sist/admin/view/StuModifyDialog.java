package kr.co.sist.admin.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.admin.controller.StuModifyDialogEvt;

public class StuModifyDialog extends JDialog {
	
	private JLabel jlbStufNum,jlbStufName,jlbStuTel,jlblStuPass;
	private JTextField jtfStuNum,jtfStuName,jtfStuTel,jtfStuPass;
	private JButton jbtnAdd,jbtnClose;
//	private StudentDTO sDTO;
	private AdminStuMgrDesign asmd;
	

	public StuModifyDialog(AdminStuMgrDesign asmd, boolean modal,int StuNum) {
		super(asmd,"관리자 - 학생관리(수정)",modal);
		this.asmd=asmd;

		jlbStufNum = new JLabel("학번");
		jlbStufName = new JLabel("이름");
		jlbStuTel = new JLabel("휴대폰번호");
		jlblStuPass = new JLabel("비밀번호");
		
		jtfStuNum = new JTextField(String.valueOf(StuNum));
		jtfStuName= new JTextField();
		jtfStuTel= new JTextField();
		jtfStuPass= new JTextField();
		
		jbtnAdd= new JButton("변경");
		jbtnClose= new JButton("닫기");
		
		jtfStuNum.setEditable(false);
		jtfStuName.requestFocus();
		
		//font 지정
		
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		jlbStufNum.setFont(font);
		jlbStufName.setFont(font);
		jlbStuTel.setFont(font);
		jlblStuPass.setFont(font);
		
		jtfStuNum.setFont(font);
		jtfStuName.setFont(font);
		jtfStuTel.setFont(font);
		jtfStuPass.setFont(font);
		
		jbtnAdd.setFont(font);
		jbtnClose.setFont(font);
		
		setLayout(null);
		
		jlbStufNum.setBounds(155,3,100,100);
		jlbStufName.setBounds(jlbStufNum.getX(),jlbStufNum.getY()+40,100,100);
		jlbStuTel.setBounds(jlbStufNum.getX(),jlbStufName.getY()+40,100,100);
		jlblStuPass.setBounds(jlbStufNum.getX(),jlbStuTel.getY()+40,100,100);

		add(jlbStufNum);
		add(jlbStufName);
		add(jlbStuTel);
		add(jlblStuPass);
		
		jtfStuNum.setBounds(250,40,150,30);
		jtfStuName.setBounds(250,jtfStuNum.getY()+40,150,30);
		jtfStuTel.setBounds(250,jtfStuName.getY()+40,150,30);
		jtfStuPass.setBounds(250,jtfStuTel.getY()+40,150,30);
		
		
		jbtnAdd.setBounds(155,220,100,25);
		jbtnClose.setBounds(300,220,100,25);

		add(jtfStuNum);
		add(jtfStuName);
		add(jtfStuTel);
		
		add(jtfStuPass);
		
		add(jbtnAdd);
		add(jbtnClose);
		
		//리스너 추가 
		StuModifyDialogEvt smde = new StuModifyDialogEvt(this);
		jbtnAdd.addActionListener(smde);
		jbtnClose.addActionListener(smde);
		addWindowListener(smde);
		
		
		setSize(600,300);
		setLocationRelativeTo(null);
//        setBounds(600,300,600,350);
		setResizable(false);
		setVisible(true);
		
	}//StuModifyDialog
	
	
	public JLabel getJlbStufNum() {
		return jlbStufNum;
	}


	public JLabel getJlbStufName() {
		return jlbStufName;
	}


	public JLabel getJlbStuTel() {
		return jlbStuTel;
	}


	public JLabel getJlblStuPass() {
		return jlblStuPass;
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


	public JTextField getJtfStuPass() {
		return jtfStuPass;
	}


	public JButton getJbtnAdd() {
		return jbtnAdd;
	}


	public JButton getJbtnClose() {
		return jbtnClose;
	}



}//class
