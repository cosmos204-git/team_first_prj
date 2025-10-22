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
		sims = new StuInfoModifyService();
		viewStuInfo();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==simd.getJbtnModifyStuInfo()) {
			modifyStuInfoProcess();
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
		csd.getLogStuDTO().setStuEmail(simd.getJtfStuEmail().getText());
		csd.getLogStuDTO().setStuAddr1(simd.getJtfStuAddr().getText());
		csd.getLogStuDTO().setStuAddr2(simd.getJtfStuAddr2().getText());
		
		if(sims.modifyMember(csd)==1) {
			JOptionPane.showMessageDialog(simd, "개인정보를 변경하였습니다.");
		}
		
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
	
	public void viewStuInfo() {
		CurrentStuData csd = CurrentStuData.getInstance();
		
		simd.getJtfStuNumData().setText(String.valueOf(csd.getLogStuDTO().getStuNum()));
		simd.getJtfStuNameData().setText(csd.getLogStuDTO().getStuName());
		simd.getJtfStuTelData().setText(csd.getLogStuDTO().getStuTel());
		simd.getJtfStuEmail().setText(csd.getLogStuDTO().getStuEmail());
		simd.getJtfStuAddr().setText(csd.getLogStuDTO().getStuAddr1());
		simd.getJtfStuAddr2().setText(csd.getLogStuDTO().getStuAddr2());
		
		
	}
	

}
