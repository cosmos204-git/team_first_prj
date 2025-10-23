package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;

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
			modifyStuInfoProcess();		
		}
		if(e.getSource()==sd.getJbtnSelectCourse()) {
			
			selectCourseProcess();
		}
		if(e.getSource()==sd.getJbtnMgrCourse()) {
			mgrCourseProcess();
			
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
		
		Properties prop = new Properties();
		String userHome = System.getProperty("user.home");
		try {
			prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
			ImageIcon ii = new ImageIcon(prop.getProperty("savePath")+csd.getLogStuDTO().getStuNum()+"s."+csd.getLogStuDTO().getExt());
			sd.getJlblStuImg().setIcon(ii);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void modifyStuInfoProcess() {
		new StuInfoModifyDesign(sd,true);
	}
	public void selectCourseProcess() {
		new CourseSelectDesign(sd,true);
	}
	public void mgrCourseProcess() {
		new StuCourseMgrDesign(sd,true);
	}
	

}
