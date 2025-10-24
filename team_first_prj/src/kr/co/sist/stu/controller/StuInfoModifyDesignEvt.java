package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.service.StuInfoModifyService;
import kr.co.sist.stu.view.StuInfoDesign;
import kr.co.sist.stu.view.StuInfoModifyDesign;
import kr.co.sist.stu.view.StuPwMdfDialog;

public class StuInfoModifyDesignEvt extends WindowAdapter implements ActionListener{
	
	private StuInfoModifyDesign simd;
	private StuInfoModifyService sims;
	
	
	private String selectedImg;
	boolean jfcFlag = false;
	
	public StuInfoModifyDesignEvt(StuInfoModifyDesign simd) {
		this.simd = simd;
		sims = new StuInfoModifyService();
		selectedImg = "";
		viewStuInfo();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==simd.getJbtnModifyStuInfo()) {
			modifyStuInfoProcess();
		}
		if(e.getSource()==simd.getJbtnModifyPw()) {
			modifyStuPwProcess();
		}
		if(e.getSource()==simd.getJbtnSelectImage()) {
			try {
				modfiyStuPicProcess();
				if(jfcFlag==true) {
					JOptionPane.showMessageDialog(simd, "사진이 변경되었습니다.");
					StuInfoDesign sd = simd.getSid();				
					sd.getJlblStuImg().setIcon(new ImageIcon(selectedImg));
					selectedImg="";
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==simd.getJbtnClose()) {
			simd.dispose();
		}
	}
	
	public void modifyStuInfoProcess() {
		CurrentStuData csd = CurrentStuData.getInstance();
		
		JTextField emailField = simd.getJtfStuEmail();
		String stuEmail = emailField.getText();

		final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	    final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);
	    
	    String trimmedEmail = stuEmail.trim();

	    
	    // 5. 빈 값 체크
	    if (trimmedEmail.isEmpty()) {
	        JOptionPane.showMessageDialog(simd, 
	            "이메일 주소를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
	        
	        emailField.requestFocusInWindow();
	        return; 
	    }
	    
	    // Matcher.matches() 대신 Pattern.matcher(String).matches()를 바로 사용
	    if (!PATTERN.matcher(trimmedEmail).matches()) {
	        JOptionPane.showMessageDialog(simd, 
	            "올바른 이메일 주소 형식이 아닙니다.", "형식 오류", JOptionPane.ERROR_MESSAGE);
	        
	        emailField.requestFocusInWindow();
	        emailField.selectAll(); 
	        return;
	    }
		
		csd.getLogStuDTO().setStuEmail(simd.getJtfStuEmail().getText());
		csd.getLogStuDTO().setStuAddr1(simd.getJtfStuAddr().getText());
		csd.getLogStuDTO().setStuAddr2(simd.getJtfStuAddr2().getText());
		
		
		if(sims.modifyStuInfo(csd)==1) {
			JOptionPane.showMessageDialog(simd, "개인정보를 변경하였습니다.");
		}
		
		
	}
	
	public void modfiyStuPicProcess() throws IOException {
		
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showOpenDialog(simd);
		
		
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
					JOptionPane.showMessageDialog(simd, "이미지파일(png,jpg,gif,bmp)만 허용합니다.");
				}//end if
				
				//이미지 크기를 변경 : 170(w) x 170(h)
				//ImageResize.resizeImage(file.getAbsolutePath(), 130, 130);
				selectedImg=file.getParent()+File.separator+file.getName();
				
							
				//현재 스테이터스 창에 채워주고...
				simd.getJlblStuImg().setIcon(new ImageIcon(selectedImg));
				
				file = new File(selectedImg);
				FileInputStream fisImg = new FileInputStream(file);
				
				ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
				
				CurrentStuData csd = CurrentStuData.getInstance();
				csd.getLogStuDTO().setFile(file);
				csd.getLogStuDTO().setStuImg(fisImg);
				csd.getLogStuDTO().setExt(ext);
				
		
				
				if(sims.modifyStuImg(csd) == 1) {
					//변경된 크기의 이미지를 삭제. (참조 process가 존재하면 파일이 삭제되지 않는다.)
					if(fisImg != null) {fisImg.close();}
					
	//				lDTO.getFile().delete();
				}//end if		
				
				
			}
		}
		
	}
	
	
	public void modifyStuPwProcess() {
		new StuPwMdfDialog(simd,true);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		simd.dispose();
	}
	
	public void viewStuInfo() {
		CurrentStuData csd = CurrentStuData.getInstance();
		
		simd.getJtfStuNumData().setText(String.valueOf(csd.getLogStuDTO().getStuNum()));
		simd.getJtfStuNameData().setText(csd.getLogStuDTO().getStuName());
		simd.getJtfStuTelData().setText(csd.getLogStuDTO().getStuTel());
		simd.getJtfStuEmail().setText(csd.getLogStuDTO().getStuEmail());
		simd.getJtfStuAddr().setText(csd.getLogStuDTO().getStuAddr1());
		simd.getJtfStuAddr2().setText(csd.getLogStuDTO().getStuAddr2());
		
		Properties prop = new Properties();
		String userHome = System.getProperty("user.home");
		try {
			prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
			ImageIcon ii = new ImageIcon(prop.getProperty("savePath")+csd.getLogStuDTO().getStuNum()+"s."+csd.getLogStuDTO().getExt());
			simd.getJlblStuImg().setIcon(ii);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
