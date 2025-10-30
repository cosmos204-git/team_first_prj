package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.dto.CourseMgrDTO;
import kr.co.sist.admin.service.AdminCourseMgrDesignService;
import kr.co.sist.admin.view.AdminCourseMgrDesign;

public class AdminCourseMgrDesignEvt extends WindowAdapter implements ActionListener,MouseListener,ItemListener{
	
	private AdminCourseMgrDesign acmd;
	private AdminCourseMgrDesignService acmds;
	private List<CourseMgrDTO> listCourseData;
	private int selectedNum =-1;
	private int maxRow;


	
	
	
	public AdminCourseMgrDesignEvt(AdminCourseMgrDesign acmd) {
		this.acmd=acmd;
		acmds= new AdminCourseMgrDesignService();
		searchAllProcess();
		searchProfProcess();
		resetInputField();
//		acmd.getJlblCourseCodeData().setText(String.valueOf(acmds.AddCourseNum()));
	}//AdminCourseMgrDesignEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == acmd.getJbtnSearch()) {
				searchProcess();
			
		}//end if 
		if(ae.getSource() == acmd.getJbtnModify()) {
			modifyProcess();
		}//end if 
		if(ae.getSource() == acmd.getJbtnAdd()) {
			addProcess();
			
		}//end if 
		if(ae.getSource() == acmd.getJbtnDelete()) {
			deleteProcess();
		}//end if 
		if(ae.getSource() == acmd.getJbtnClose()) {
			closeProcess();
		}//end if 

	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent we) {
		acmd.dispose();
	}//windowClosing
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent me) {
		//1. 왼 2. 휠 3. 오른쪽
		switch(me.getButton()) {
		case MouseEvent.BUTTON1 : //왼쪽버튼 클릭
				clickedOneCourse();
				selectedNum=acmd.getJtAdminCourseMgr().getSelectedRow();
			}//end switch
		
	}//mouseClicked
	
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		if( ie.getStateChange() == ItemEvent.SELECTED) {
			
			if(ie.getSource() == acmd.getJcbProfName() ){
				String ProfName = (String)ie.getItem();
				Map<String, String> list = acmds.searchCombo();
				
				acmd.getJlblProfNumData().setText(list.get(ProfName));
				}//end if 
			if(ie.getSource()==acmd.getJcbStartdateM()) {
				int startY = Integer.parseInt((String)acmd.getJcbStartdateY().getSelectedItem());
				int startM = Integer.parseInt((String)acmd.getJcbStartdateM().getSelectedItem());
				LocalDate startDate = LocalDate.of(startY,startM,1);
				acmd.getDcbmStartdateD().removeAllElements();
				for(int i =0; i<startDate.lengthOfMonth();i++) {
					acmd.getDcbmStartdateD().addElement(String.format("%02d", i+1));
				}//end for 
			}//end if
			
			if(ie.getSource()==acmd.getJcbEnddateM()) {
				int endY = Integer.parseInt((String)acmd.getJcbEnddateY().getSelectedItem());
				int endM = Integer.parseInt((String)acmd.getJcbEnddateM().getSelectedItem());
				LocalDate endDate = LocalDate.of(endY,endM,1);
				acmd.getDcbmEnddateD().removeAllElements();
				for(int i =0; i<endDate.lengthOfMonth();i++) {
					acmd.getDcbmEnddateD().addElement(String.format("%02d", i+1));
				}//end for 
			}//end if

		}//end if
		
	}//itemStateChanged
	
	public void closeProcess() {
		acmd.dispose();
	}//closeProcess
	

	public void searchProfProcess() {
		DefaultComboBoxModel<String> dcbm = acmd.getDcbmProfName();
		dcbm.addElement("");
		Map<String, String> list = acmds.searchCombo();
		for( String profName : list.keySet()) {
			dcbm.addElement(profName);
		}//end for 
	}//searchProfProcess
	

	
	public void clickedOneCourse() {
		int selectedCourse = acmd.getJtAdminCourseMgr().getSelectedRow();
		
		CourseMgrDTO cmDTO = listCourseData.get(selectedCourse);
		
		JTable jtadimCourse = acmd.getJtAdminCourseMgr();
		
		String startDateSplit[]=String.valueOf(cmDTO.getCourseStartDate()).split("-");  
		String EndDateSplit[]=String.valueOf(cmDTO.getCourseEndDate()).split("-");  
		
		String CourseName =cmDTO.getCourseName().substring(0, cmDTO.getCourseName().indexOf("("));
		
		acmd.getJlblCourseCodeData().setText(String.valueOf(cmDTO.getCourseCode()));
		acmd.getJtfCourseName().setText(CourseName);
		acmd.getJlblProfNumData().setText(String.valueOf(cmDTO.getProfNum()));
		acmd.getJcbProfName().setSelectedItem(cmDTO.getProfName());

		acmd.getJcbStartdateY().setSelectedItem(startDateSplit[0]);
		acmd.getJcbStartdateM().setSelectedItem(startDateSplit[1]);
		acmd.getJcbStartdateD().setSelectedItem(startDateSplit[2]);
		
		acmd.getJcbEnddateY().setSelectedItem(EndDateSplit[0]);
		acmd.getJcbEnddateM().setSelectedItem(EndDateSplit[1]);
		acmd.getJcbEnddateD().setSelectedItem(EndDateSplit[2]);
		
		
		acmd.getJlblInputdataData().setText(String.valueOf(cmDTO.getCourseInputDate()));
		
		
	}//clickOneCourse
	
	
	public void searchAllProcess() {
		
		List<CourseMgrDTO> listCourseData = acmds.searchAllCourse();
		
		
		this.listCourseData = listCourseData;
		DefaultTableModel dtmAdminCouresMgr=acmd.getDtmAdminCouresMgr();
		
		dtmAdminCouresMgr.setRowCount(0);
		String[] rowData=null;
		
		String currentCoure="";
		
		
		for(CourseMgrDTO cmDTO : listCourseData) {
			rowData=new String[7];
			rowData[0]=String.valueOf(cmDTO.getCourseCode());
			rowData[1]=cmDTO.getCourseName();
			rowData[2]=String.valueOf(cmDTO.getProfNum());
			rowData[3]=cmDTO.getProfName();
			rowData[4]=String.valueOf(cmDTO.getCourseStartDate());
			rowData[5]=String.valueOf(cmDTO.getCourseEndDate());
			rowData[6]=String.valueOf(cmDTO.getCourseInputDate());
			
			dtmAdminCouresMgr.addRow(rowData);
		}//end for 
		maxRow=acmd.getJtAdminCourseMgr().getRowCount();
		selectedNum=-1;
		
	}//searchAllProcess
	
	public void searchProcess() {
		
		if("".equals(acmd.getJtfSearchCourse().getText())) {
			JOptionPane.showMessageDialog(acmd, "검색할 과정명을 입력해주세요!");	
			searchAllProcess();
			resetInputField();
//			acmd.getJlblCourseCodeData().setText(String.valueOf(acmds.AddCourseNum()));
			return;
		}if(!validCourseName(acmd.getJtfSearchCourse().getText())) {
		
				JOptionPane.showMessageDialog(acmd, "존재하지 않는 과정명입니다.");
				searchAllProcess();
				acmd.getJtfSearchCourse().setText("");
				resetInputField();
				acmd.getJlblCourseCodeData().setText(String.valueOf(acmds.AddCourseNum()));
				return ;
			}//end if
		String courseName = acmd.getJtfSearchCourse().getText().trim();
		
		DefaultTableModel dtm = acmd.getDtmAdminCouresMgr();
		JTable jt = acmd.getJtAdminCourseMgr();
		List<CourseMgrDTO> listCourseData=acmds.searchCourse(courseName);
		this.listCourseData=listCourseData;
		
		dtm.setRowCount(0);
		String[] rowData = null;
		for(CourseMgrDTO cmDTO:listCourseData) {
			rowData=new String[7];
			rowData[0]=String.valueOf(cmDTO.getCourseCode());
			rowData[1]=cmDTO.getCourseName();
			rowData[2]=String.valueOf(cmDTO.getProfNum());
			rowData[3]=cmDTO.getProfName();
			rowData[4]=String.valueOf(cmDTO.getCourseStartDate());
			rowData[5]=String.valueOf(cmDTO.getCourseEndDate());
			rowData[6]=String.valueOf(cmDTO.getCourseInputDate());
			
			dtm.addRow(rowData);
		}//end for 

	}//searchProcess
	
	
	
	public boolean validDate(String end, String start) {

		boolean flag= false;
		
		LocalDate startDate= LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		
		if(endDate.isBefore(startDate)) {
			flag=true;
		}//end if 
		
		return flag;
	
	}//validDate
	
	public boolean validCourseName(String CourseName) {
		boolean flag=false;
		
		List<CourseMgrDTO> cmDTO = acmds.searchAllCourse();
		
		for(CourseMgrDTO cDTO:cmDTO) {
			String dtoCourseName=cDTO.getCourseName().substring(0, cDTO.getCourseName().indexOf("("));
			
			if(dtoCourseName.equals(CourseName)||dtoCourseName.contains(CourseName)) {
				flag=true;
				break;
			}//end if 	
		}//end for 
		return flag;
	}//validCourseName
	
	public boolean modifiyCourseName(String CourseName, int courseCode) {
		boolean flag=false;
		
		List<CourseMgrDTO> cmDTO = acmds.searchAllCourse();
		
		for(CourseMgrDTO cDTO:cmDTO) {
			String dtoCourseName=cDTO.getCourseName().substring(0, cDTO.getCourseName().indexOf("("));
			
			if(dtoCourseName.equals(CourseName)&&cDTO.getCourseCode()!=courseCode) {
				flag=true;
				break;
			}//end if 	
		}//end for 
		return flag;
	}//validCourseName
	
	
	
	
	
	public void addProcess() {
		CourseMgrDTO cmDTO=new CourseMgrDTO();
		
		String startDate = (String)acmd.getJcbStartdateY().getSelectedItem()+"-"+
							(String)acmd.getJcbStartdateM().getSelectedItem()+"-"+
							(String)acmd.getJcbStartdateD().getSelectedItem();


		String endDate = (String)acmd.getJcbEnddateY().getSelectedItem()+"-"+
						 (String)acmd.getJcbEnddateM().getSelectedItem()+"-"+
						 (String)acmd.getJcbEnddateD().getSelectedItem();

		String courseName=acmd.getJtfCourseName().getText();
		String profName = (String)acmd.getJcbProfName().getSelectedItem();

		String courseRegex="[0-9a-zA-Zㄱ-힣_-]+"; 

		String msg = "과정명,교수, 시작일, 종료일이 알맞게 들어갔는지 확인해주세요";
		if(startDate==null||startDate.isEmpty()||endDate==null||endDate.isEmpty()
			||courseName==null||courseName.isEmpty()||profName==null||profName.isEmpty()) {
				JOptionPane.showMessageDialog(acmd, msg);
				return;
		}//end if
		
		if(courseName.length()<2||courseName.length()>10) {
			msg="과정명을 2~10자 사이로 입력해주세요.";
			JOptionPane.showMessageDialog(acmd, msg);
			return ;
		}//end if 
		if(validCourseName(courseName)) {
			msg="과정명이 이미 존재합니다.";
			JOptionPane.showMessageDialog(acmd, msg);
			return ;
		}
		if(validDate(endDate,startDate)) {
			msg="시작일이 종료일보다 작습니다. 다시 설정해주세요 ";
			JOptionPane.showMessageDialog(acmd, msg);
			return;
		}//end if
		if(!Pattern.matches(courseRegex, courseName)) {
			msg="과정명의 특수문자 사용 불가능합니다. \n _ -은 사용가능";
			JOptionPane.showMessageDialog(acmd, msg);
			return;
		}//end if 
		
		cmDTO.setCourseName(courseName);
		cmDTO.setProfName(profName);
		cmDTO.setCourseStartDate(startDate);
		cmDTO.setCourseEndDate(endDate);
		
		
		
		boolean flag= acmds.addCourse(cmDTO)==1;
		
		
		if(flag) {
			msg="과정이 성공적으로 추가되었습니다.";
			resetInputField();
		}//end if 
		JOptionPane.showMessageDialog(acmd, msg);
		acmd.getJlblCourseCodeData().setText(String.valueOf(acmds.AddCourseNum()));
		searchAllProcess();
		
	}//addProcess
	
	public void modifyProcess(){
		CourseMgrDTO cmDTO=new CourseMgrDTO();
		
		String startDate = (String)acmd.getJcbStartdateY().getSelectedItem()+"-"+
							(String)acmd.getJcbStartdateM().getSelectedItem()+"-"+
							(String)acmd.getJcbStartdateD().getSelectedItem();
		
		
		String endDate = (String)acmd.getJcbEnddateY().getSelectedItem()+"-"+
				(String)acmd.getJcbEnddateM().getSelectedItem()+"-"+
				(String)acmd.getJcbEnddateD().getSelectedItem();
		
		String courseName=acmd.getJtfCourseName().getText();
		String profName = (String)acmd.getJcbProfName().getSelectedItem();
		
		String courseRegex="[0-9a-zA-Zㄱ-힣_-]+"; 
		
		int courseCode =Integer.parseInt(acmd.getJlblCourseCodeData().getText()); 
		
		String msg = "과정명,교수, 시작일, 종료일 모두 입력되었는지 확인해주세요";
		if(selectedNum==-1) {
			msg="수정할 과정을 클릭해주세요";
			JOptionPane.showMessageDialog(acmd, msg);
			return;
		}//
		if(startDate==null||startDate.isEmpty()||endDate==null||endDate.isEmpty()
		||courseName==null||courseName.isEmpty()||profName==null||profName.isEmpty()) {
			JOptionPane.showMessageDialog(acmd, msg);
			return;
		}
		if(courseName.length()<2||courseName.length()>10) {
			msg="과정명을 2~10자 사이로 입력해주세요.";
			JOptionPane.showMessageDialog(acmd, msg);
			return ;
		}//end if 
		if(modifiyCourseName(courseName,courseCode)) {
			msg="과정명이 이미 존재합니다.";
			JOptionPane.showMessageDialog(acmd, msg);
			return ;
		}//end if
		if(validDate(endDate,startDate)) {
			msg="시작일이 종료일보다 작습니다. 다시 설정해주세요 ";
			JOptionPane.showMessageDialog(acmd, msg);
			return;
		}if(!Pattern.matches(courseRegex, courseName)) {
			msg="과정명의 특수문자 사용 불가능합니다. \n _ -은 사용가능";
			JOptionPane.showMessageDialog(acmd, msg);
			return;
		}//end if 
		
		cmDTO.setCourseCode(Integer.parseInt(acmd.getJlblCourseCodeData().getText()));
		cmDTO.setCourseName(courseName);
		cmDTO.setProfName(profName);
//		cmDTO.setProfNum(Integer.parseInt(acmd.getJlblProfNumData().getText()));
		cmDTO.setCourseStartDate(startDate);
		cmDTO.setCourseEndDate(endDate);
		
		
		boolean flag= acmds.modifyCourse(cmDTO)==1;
		
		if(flag) {
			msg="과정이 성공적으로 수정되었습니다.";
			resetInputField();
		}//end if 
		JOptionPane.showMessageDialog(acmd, msg);
//		acmd.getJlblCourseCodeData().setText(String.valueOf(acmds.AddCourseNum()));
		resetInputField();
		searchAllProcess();
		
	}//modifyProcess
	
	public void deleteProcess(){
		int selectedNum = acmd.getJtAdminCourseMgr().getSelectedRow();

		
		if(selectedNum==-1) {
			JOptionPane.showMessageDialog(acmd, "삭제할 과정을 선택해주세요.");
			return;
		}//end if
		
		CourseMgrDTO cmDTO = listCourseData.get(selectedNum);
		String CourseName =cmDTO.getCourseName().substring(0, cmDTO.getCourseName().indexOf("("));
		
		int deleteCourseCode=cmDTO.getCourseCode();
		
		switch (JOptionPane.showConfirmDialog(acmd,CourseName+" 과정을 정말 삭제 하시겠습니까?")) {
		case JOptionPane.OK_OPTION: 
			break;
		case JOptionPane.NO_OPTION: 
		case JOptionPane.CANCEL_OPTION:
		default:
			return;
		}//end switch
		
		
		int flag= acmds.removeCourse(deleteCourseCode);
		
		String msg="문제가 발생했습니다. 잠시후 다시 시도해주세요";
		
		if(flag==1) {
			msg=CourseName+" 과정이 삭제되었습니다.";
		}//end if
		
		JOptionPane.showMessageDialog(acmd, msg);
		
		//삭제된 것을 확인하기위해 새로 반영 후 selectedNum 초기화 
		searchAllProcess();
		selectedNum=-1;
		
	
	}//deleteProces
	
	private void resetInputField() {
		
		LocalDate today= LocalDate.now();
	
		//입력칸을 초기화
//		acmd.getJlblCourseCodeData().setText("");
		acmd.getJlblCourseCodeData().setText(String.valueOf(acmds.AddCourseNum()));
		acmd.getJtfCourseName().setText("");
		acmd.getJlblProfNumData().setText("");
		acmd.getJcbProfName().setSelectedIndex(0);
		acmd.getJcbProfName().setSelectedIndex(0);
		
		acmd.getJcbStartdateY().setSelectedItem(String.valueOf(today.getYear()) );
		acmd.getJcbStartdateM().setSelectedItem(String.format("%02d",today.getMonthValue()));
		acmd.getJcbStartdateD().setSelectedItem(String.format("%02d",today.getDayOfMonth()));

		acmd.getJcbEnddateY().setSelectedItem(String.valueOf(today.getYear()));
		acmd.getJcbEnddateM().setSelectedItem(String.format("%02d",today.getMonthValue()));
		acmd.getJcbEnddateD().setSelectedItem(String.format("%02d",today.getDayOfMonth()+1));

		
		
		
		acmd.getJlblInputdataData().setText(today.toString());
		
		
		
	}//resetInputField
	

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}//class
