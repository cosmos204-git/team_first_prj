package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.view.CourseSelectDesign;
import kr.co.sist.stu.view.StuCourseMgrDesign;
import kr.co.sist.stu.view.StuInfoDesign;
import kr.co.sist.stu.view.StuInfoModifyDesign;

public class StuInfoDesignEvt extends WindowAdapter implements ActionListener{
	
	private StuInfoDesign sd;
	
	public StuInfoDesignEvt(StuInfoDesign sd) throws IOException {
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
	
	public void viewStuInfo() throws IOException {
		
		CurrentStuData csd = CurrentStuData.getInstance();
		
		sd.getJtfStuCourseData().setText(csd.getLogStuDTO().getStuCourseName());
		sd.getJtfStuNameData().setText(csd.getLogStuDTO().getStuName());
		sd.getJtfStuTelData().setText(csd.getLogStuDTO().getStuTel());
		sd.getJtfStuNumData().setText(String.valueOf(csd.getLogStuDTO().getStuNum()));
		
		Properties prop = new Properties();
		
		try (InputStream is = getClass().getClassLoader().getResourceAsStream("properties/datebase.properties")) {
		    if (is == null) {
		        throw new IOException("database.properties 파일을 클래스패스에서 찾을 수 없습니다.");
		    }
		    prop.load(is);
		}
		
		//String userHome = System.getProperty("user.home");
		//try {
			ImageIcon ii = new ImageIcon(prop.getProperty("savePath")+csd.getLogStuDTO().getStuNum()+"s."+csd.getLogStuDTO().getExt());
			sd.getJlblStuImg().setIcon(ii);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		
		
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
