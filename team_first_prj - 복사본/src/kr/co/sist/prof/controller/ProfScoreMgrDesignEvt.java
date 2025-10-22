package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.prof.view.ProfScoreMgrDesign;
import kr.co.sist.prof.view.ShowStuReportInfoDesignDialog;

public class ProfScoreMgrDesignEvt extends WindowAdapter implements ActionListener{

	ProfScoreMgrDesign psmd;
	
	public ProfScoreMgrDesignEvt(ProfScoreMgrDesign psmd) {
		this.psmd = psmd;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()== psmd.getJbtnSearchStuNum()) {
			
		}
		
		if(e.getSource()== psmd.getJbtnShowStuReportInfo()) {
			new ShowStuReportInfoDesignDialog(psmd, true);
		}
	
	}

	@Override
	public void windowClosing(WindowEvent e) {
		psmd.dispose();
	}

}
