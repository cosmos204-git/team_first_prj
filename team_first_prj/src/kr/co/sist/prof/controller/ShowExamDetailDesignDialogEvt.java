package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.prof.view.ProfExamMgrDesign;
import kr.co.sist.prof.view.ShowExamDetailDesignDialog;

// 사용하지 않음
public class ShowExamDetailDesignDialogEvt extends WindowAdapter implements ActionListener {
	
	private ShowExamDetailDesignDialog seddd;

	
	public ShowExamDetailDesignDialogEvt(ProfExamMgrDesign  pemd) {
//		this.pemd = pemd;
//        viewExamDetail(); // 초기 데이터 로딩
       
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
//		if(e.getSource() == seddd.getJbtnExamDetail()) {
//			ShowExamDetailDesignDialog(seddd,true);
//		}

//		if(e.getSource() == pemd.getJbtnModifyExam()) {
//			updateExamItem(pemd);
//		}
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		seddd.dispose();
	}


} // class
