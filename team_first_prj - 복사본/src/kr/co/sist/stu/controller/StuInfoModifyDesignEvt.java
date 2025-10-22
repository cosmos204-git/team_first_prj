package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.service.StuInfoModifyService;
import kr.co.sist.stu.view.StuInfoModifyDesign;
import kr.co.sist.stu.view.StuPwMdfDialog;

public class StuInfoModifyDesignEvt extends WindowAdapter implements ActionListener{
	private StuInfoModifyDesign simd;
	private StuInfoModifyService sims;
	
	
	
	public StuInfoModifyDesignEvt(StuInfoModifyDesign simd) {
		this.simd = simd;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==simd.getJbtnModifyStuInfo()) {
		//	modifyStuInfoProcess();
		}
		if(e.getSource()==simd.getJbtnModifyPw()) {
			modifyStuPwProcess();
		}
		if(e.getSource()==simd.getJbtnSelectImage()) {
			modfiyStuPicProcess();
		}
		if(e.getSource()==simd.getJbtnClose()) {
			simd.dispose();
		}
	}
	
	public void modifyStuInfoProcess() {
		CurrentStuData csd = CurrentStuData.getInstance();
		
		sims.modifyMember(csd,simd);
		
	}
	
	public void modfiyStuPicProcess() {
		JOptionPane.showMessageDialog(simd, "사진 바꾸는 기능 구현 필요");
	}
	
	public void modifyStuPwProcess() {
		new StuPwMdfDialog(simd,true);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		simd.dispose();
	}
	
	
	

}
