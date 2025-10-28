package kr.co.sist.prof.controller;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
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
	private ImageIcon resizedIcon;
	
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
					pid.getJlblProfImg().setIcon(resizedIcon);
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
					jfcFlag=false;
					return;
				}//end if
				
				//이미지 크기를 변경 : 170(w) x 170(h)
				
				BufferedImage originalImage = ImageIO.read(file);
				Image scaledImage = originalImage.getScaledInstance(100, 180, Image.SCALE_SMOOTH);
				//ImageResize.resizeImage(file.getAbsolutePath(), 130, 130);
				resizedIcon = new ImageIcon(scaledImage);
				
				
				
				
				//현재 스테이터스 창에 채워주고...
				pimd.getJlblProfImg().setIcon(resizedIcon);
				//pimd.getJlblProfImg().setIcon(new ImageIcon(selectedImg));
				
				selectedImg=file.getParent()+File.separator+file.getName();

				file = new File(selectedImg);
				
				FileInputStream fisImg = null;
	            File tempResizedFile = null; 

	            try {
	                // a) Image를 BufferedImage로 변환 (ImageIO.write를 위해 필요)
	                BufferedImage bufferedResizedImage = new BufferedImage(100, 180, BufferedImage.TYPE_INT_RGB);
	                bufferedResizedImage.getGraphics().drawImage(scaledImage, 0, 0, null);

	                // b) 임시 파일 생성 및 저장 (FileInputStream을 사용하기 위해 필요)
	                String extension = "." + ext; 
	                tempResizedFile = File.createTempFile("resized_img_", extension);
	                tempResizedFile.deleteOnExit(); 
	                
	                ImageIO.write(bufferedResizedImage, ext, tempResizedFile); 
	                
	                // c) 임시 파일을 setProfImg가 요구하는 FileInputStream으로 읽어옴
	                fisImg = new FileInputStream(tempResizedFile); 

	                CurrentProfData cpd = CurrentProfData.getInstance();
	                
	                // 원본 파일 객체를 setFile에 전달 (경로 참조용)
	                cpd.getLogProfDTO().setFile(file);       
	                // 🚨 핵심: 리사이징된 이미지의 FileInputStream 전달
	                cpd.getLogProfDTO().setProfImg(fisImg);   
	                // 확장자 전달
	                cpd.getLogProfDTO().setExt(ext);         

	                
	                if(pims.modifyProfImg(cpd) == 1) { // 메서드명은 sims.modifyStuImg 대신 sims.modifyProfImg로 가정
	                    // DB 저장 성공 시
	                    if(fisImg != null) {fisImg.close();}
	                    
	                    // ⭐️ 임시 파일 삭제
	                    if (tempResizedFile != null && tempResizedFile.exists()) {
	                        tempResizedFile.delete();
	                    }
	                } else {
	                    // DB 저장 실패 시에도 임시 파일 및 스트림 정리
	                    if(fisImg != null) {fisImg.close();}
	                    if (tempResizedFile != null && tempResizedFile.exists()) {
	                        tempResizedFile.delete();
	                    }
	                } //end if (modifyProfImg)
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	                if (fisImg != null) fisImg.close();
	                if (tempResizedFile != null && tempResizedFile.exists()) tempResizedFile.delete();
	            }
				
				/*
				
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
				
					*/
				
				
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
		InputStream is = null;
		
		ImageIcon ii = null;
		
		try {
			is = getClass().getClassLoader().getResourceAsStream("properties/datebase.properties");
			if (is == null) {
	            throw new IOException("datebase.properties 파일을 클래스패스에서 찾을 수 없습니다.");
	        }
			prop.load(is);
			
			
			File imageFile = cpd.getLogProfDTO().getFile();
			
			boolean imageLoaded = false;
			
			if (imageFile != null && imageFile.exists()) {
		        String imagePath = imageFile.getAbsolutePath();
		        ii = new ImageIcon(imagePath);
		        
		        if (ii.getImageLoadStatus() == MediaTracker.COMPLETE) {
		            imageLoaded = true; // 이미지 로드 성공
		        }
		    }
			
			if (!imageLoaded) {
		        try (InputStream defaultIs = getClass().getResourceAsStream("/images/default_img.png")) {
		            if (defaultIs != null) {
		                // InputStream에서 바이트 배열을 읽어와 ImageIcon 생성 (URL 사용 안함)
		                byte[] imageBytes = defaultIs.readAllBytes();
		                ii = new ImageIcon(imageBytes);
		            } else {
		                // 기본 이미지마저 찾을 수 없는 경우 경고를 출력합니다.
		                System.err.println("경고: 기본 이미지 파일을 클래스패스에서 찾을 수 없습니다.");
		            }
		        } // try-with-resources에 의해 defaultIs는 자동으로 닫힙니다.
		    }
		
			// 3. 최종 로드된 이미지를 컴포넌트에 설정
		    if (ii != null) {
		        pimd.getJlblProfImg().setIcon(ii);
		    }
			
		    /*
			String saveDir = prop.getProperty("savePath");
	        int profNum = cpd.getLogProfDTO().getProfNum();
	        String ext = cpd.getLogProfDTO().getExt();
			
	        String imagePath = saveDir + File.separator + profNum + "p." + ext;
			
	        ii = new ImageIcon(imagePath);
			
			if (ii.getImageLoadStatus() != MediaTracker.COMPLETE || !(new File(imagePath).exists())) {
	             // 기본 이미지는 JAR 내부 리소스(/images/default_profile.png)에서 불러옵니다.
	             URL defaultImageUrl = getClass().getResource("/images/default_img.png"); 
	             if (defaultImageUrl != null) {
	                 ii = new ImageIcon(defaultImageUrl);
	             } else {
	                 // 기본 이미지도 없으면 콘솔 경고만 출력
	                 System.err.println("경고: 기본 이미지 파일도 클래스패스에서 찾을 수 없습니다.");
	             }
	        }
			
			pimd.getJlblProfImg().setIcon(ii);
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	
}
