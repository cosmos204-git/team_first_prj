package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.prof.view.ProfExamMgrDesign;
import kr.co.sist.prof.view.ShowExamDetailDesignDialog;

public class ProfExamMgrDesignEvt extends WindowAdapter implements ActionListener {
	
	private ProfExamMgrDesign pemd;
	
	public ProfExamMgrDesignEvt(ProfExamMgrDesign pemd) {
		this.pemd = pemd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == pemd.getJbtnExamDetail()) {
			new ShowExamDetailDesignDialog(pemd,true);
		}
		
		if(e.getSource() == pemd.getJbtnModifyExam()) {
			
			JOptionPane.showMessageDialog(pemd, "기능 구현 필요");
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		pemd.dispose();
	}
	
	

}
