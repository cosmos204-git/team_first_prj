package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.prof.service.ProfInfoModifyService;
import kr.co.sist.prof.view.ProfInfoDesign;
import kr.co.sist.prof.view.ProfInfoModifyDesign;
import kr.co.sist.prof.view.ProfPwMdfDialog;

public class ProInfoModifyDesignEvt extends WindowAdapter implements ActionListener{

	private ProfInfoModifyDesign pimd;
	private ProfInfoModifyService pims;
	
	private String selectedImg;
	boolean jfcFlag =false;
	
	public ProInfoModifyDesignEvt(ProfInfoModifyDesign pimd) {
		this.pimd = pimd;
		pims = new ProfInfoModifyService();
		selectedImg = "";
		viewProfInfo();
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==pimd.getJbtnAddImg()) {
			try {
				modfiyProfPicProcess();
				if (jfcFlag==true) {
					JOptionPane.showMessageDialog(pimd, "사진이 변경되었습니다.");
					ProfInfoDesign pid = pimd.getPid();				
					pid.getJlblProfImg().setIcon(new ImageIcon(selectedImg));
					selectedImg="";
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==pimd.getJbtnModifyProfInfo()) {
			modifyProfInfoProcess();
			ProfInfoDesign pid = pimd.getPid();
			CurrentProfData cpd = CurrentProfData.getInstance();
			pid.getJtfProfEmailData().setText(cpd.getLogProfDTO().getProfEmail());
			selectedImg="";
			
		}
		if(e.getSource()==pimd.getJbtnModifyProfPW()) {
			modifyProfPwProcess();		
		}
		
		if(e.getSource()==pimd.getJbtnClose()) {			
			pimd.dispose();
		}
	}
	
	public void modfiyProfPicProcess() throws IOException {
		
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showOpenDialog(pimd);
		
		if(result == jfc.APPROVE_OPTION) {
			jfcFlag = true;
			File file = jfc.getSelectedFile();
			if(file != null) { //이미지인 경우
				//선택한 파일의 크기를 170(w) x 170(h)로 변경하고, 이미지 아이콘에 설정한다.
				String FileName = file.getName();
				String ext = FileName.substring(FileName.lastIndexOf(".")+1).toLowerCase();
				//이미지는 png, jpg, gif, bmp만 사용한다.
				//위의 확장자일때 true를 그렇지 않으면 false를 콘솔에 출력
				
				String allowedExt="png,jpg,gif,bmp";
				boolean flag = false;
				String[] tempExtArr = allowedExt.split(",");
				for(String tempExt : tempExtArr) {
					if(flag=tempExt.equals(ext)) {
						break;
					}
				}
//			System.out.println(ext);
//			System.out.println(flag);
				
				if(!flag) {
					JOptionPane.showMessageDialog(pimd, "이미지파일(png,jpg,gif,bmp)만 허용합니다.");
				}//end if
				
				//이미지 크기를 변경 : 170(w) x 170(h)
				
				
				
				//ImageResize.resizeImage(file.getAbsolutePath(), 130, 130);
				selectedImg=file.getParent()+File.separator+file.getName();
				
				//현재 스테이터스 창에 채워주고...
				pimd.getJlblProfImg().setIcon(new ImageIcon(selectedImg));
				
				file = new File(selectedImg);
				FileInputStream fisImg = new FileInputStream(file);
				
				ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
				
				CurrentProfData cpd = CurrentProfData.getInstance();
				cpd.getLogProfDTO().setFile(file);
				cpd.getLogProfDTO().setProfImg(fisImg);
				cpd.getLogProfDTO().setExt(ext);
				
				
				
				if(pims.modifyProfImg(cpd) == 1) {
					//변경된 크기의 이미지를 삭제. (참조 process가 존재하면 파일이 삭제되지 않는다.)
					if(fisImg != null) {fisImg.close();}
					
//					lDTO.getFile().delete();
				}//end if		
				
				
			}
		
		}
		
		
		
	}
	
	
	public void modifyProfPwProcess() {
		new ProfPwMdfDialog(pimd, true);
	}
	
	
	public void modifyProfInfoProcess() {
		CurrentProfData cpd = CurrentProfData.getInstance();
		
		JTextField emailField = pimd.getJtfProfEmailData();
		String profEmail = emailField.getText();

		final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	    final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);
	    
	    String trimmedEmail = profEmail.trim();

	    
	    // 빈 값 체크
	    if (trimmedEmail.isEmpty()) {
	        JOptionPane.showMessageDialog(pimd, 
	            "이메일 주소를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
	        
	        emailField.requestFocusInWindow();
	        return; 
	    }
	    
	    // Matcher.matches() 대신 Pattern.matcher(String).matches()를 바로 사용
	    if (!PATTERN.matcher(trimmedEmail).matches()) {
	        JOptionPane.showMessageDialog(pimd, 
	            "올바른 이메일 주소 형식이 아닙니다.", "형식 오류", JOptionPane.ERROR_MESSAGE);
	        
	        emailField.requestFocusInWindow();
	        emailField.selectAll(); 
	        return;
	    }
		
		if(pims.modifyProfInfo(cpd)==1) {
			JOptionPane.showMessageDialog(pimd, "개인정보를 변경하였습니다.");
		}
	}
	
	public void viewProfInfo() {
		
		CurrentProfData cpd = CurrentProfData.getInstance();
		
		pimd.getJtfProfNameData().setText(cpd.getLogProfDTO().getProfName());
		pimd.getJtfProfNumData().setText(String.valueOf(cpd.getLogProfDTO().getProfNum()));		
		pimd.getJtfProfTelData().setText(cpd.getLogProfDTO().getProfTel());
		pimd.getJtfProfEmailData().setText(cpd.getLogProfDTO().getProfEmail());
		pimd.getJtfProfCourseData().setText(cpd.getLogProfDTO().getCourseName());
	
		Properties prop = new Properties();
		String userHome = System.getProperty("user.home");
		try {
			prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
			ImageIcon ii = new ImageIcon(prop.getProperty("savePath")+cpd.getLogProfDTO().getProfNum()+"p."+cpd.getLogProfDTO().getExt());
			pimd.getJlblProfImg().setIcon(ii);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	
}
