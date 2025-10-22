package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.stu.view.StuReportDesign;

public class StuReportDesignEvt extends WindowAdapter implements ActionListener{
	private StuReportDesign srd;
	
	public StuReportDesignEvt(StuReportDesign srd) {
		this.srd = srd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==srd.getJbtnClose()) {
			srd.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		srd.dispose();
	}
	
	
	
	
}
