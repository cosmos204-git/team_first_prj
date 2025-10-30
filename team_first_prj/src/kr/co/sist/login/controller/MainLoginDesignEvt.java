package kr.co.sist.login.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.login.view.LoginDesign;
import kr.co.sist.login.view.MainLoginDesign;

public class MainLoginDesignEvt extends WindowAdapter implements ActionListener{
	
	private MainLoginDesign mld;
	
	
	public MainLoginDesignEvt(MainLoginDesign mld){
		this.mld = mld;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource() == mld.getJbtnStuLogin()) {
			new LoginDesign(mld,0, 106,189,229);
			mld.dispose();
			
		}
		
		if (e.getSource() == mld.getJbtnProfLogin()) {
			new LoginDesign(mld,1,96,186,42);
			mld.dispose();
		}
		
		if (e.getSource() == mld.getJbtnAdminLogin()) {
			new LoginDesign(mld,2,55,101,163);
			mld.dispose();
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		mld.dispose();
	}
	
	
	

}
