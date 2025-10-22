package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.view.CourseSelectDesign;
import kr.co.sist.stu.view.StuCourseMgrDesign;
import kr.co.sist.stu.view.StuInfoDesign;
import kr.co.sist.stu.view.StuInfoModifyDesign;

public class StuInfoDesignEvt extends WindowAdapter implements ActionListener{
	
	private StuInfoDesign sd;
	
	public StuInfoDesignEvt(StuInfoDesign sd) {
		this.sd = sd;
		viewStuInfo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==sd.getJbtnModifyStuInfo()) {
			new StuInfoModifyDesign(sd,true);
		}
		if(e.getSource()==sd.getJbtnSelectCourse()) {
			new CourseSelectDesign(sd,true);
		}
		if(e.getSource()==sd.getJbtnMgrCourse()) {
			new StuCourseMgrDesign(sd,true);
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		sd.dispose();
	}
	
	public void viewStuInfo() {
		
		CurrentStuData csd = CurrentStuData.getInstance();
		
		sd.getJtfStuCourseData().setText(csd.getLogStuDTO().getStuCourseName());
		sd.getJtfStuNameData().setText(csd.getLogStuDTO().getStuName());
		sd.getJtfStuTelData().setText(csd.getLogStuDTO().getStuTel());
		sd.getJtfStuNumData().setText(String.valueOf(csd.getLogStuDTO().getStuNum()));
		
	}
	
	

}
