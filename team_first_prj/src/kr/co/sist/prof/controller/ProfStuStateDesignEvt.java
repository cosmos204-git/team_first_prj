package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.prof.view.ProfStuStateDesign;

public class ProfStuStateDesignEvt extends WindowAdapter implements ActionListener{

	private ProfStuStateDesign pssd;
	
	public ProfStuStateDesignEvt(ProfStuStateDesign pssd) {
		this.pssd = pssd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pssd.getJbtnClose()) {
			pssd.dispose();
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		pssd.dispose();
	}
	
	
	
	
	
}
