package kr.co.sist.stu.controller;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.service.StuInfoModifyService;
import kr.co.sist.stu.view.StuInfoDesign;
import kr.co.sist.stu.view.StuInfoModifyDesign;
import kr.co.sist.stu.view.StuPwMdfDialog;

public class StuInfoModifyDesignEvt extends WindowAdapter implements ActionListener{
	
	private StuInfoModifyDesign simd;
	private StuInfoModifyService sims;
	private ImageIcon resizedIcon;	
	
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
					sd.getJlblStuImg().setIcon(resizedIcon);
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
					jfcFlag=false;
					return;
				}//end if
				
				BufferedImage originalImage = ImageIO.read(file);
				Image scaledImage = originalImage.getScaledInstance(100, 180, Image.SCALE_SMOOTH);
				//ImageResize.resizeImage(file.getAbsolutePath(), 130, 130);
				resizedIcon = new ImageIcon(scaledImage);
				
				
				//ImageResize.resizeImage(file.getAbsolutePath(), 130, 130);
				
							
				//현재 스테이터스 창에 채워주고...
				selectedImg=file.getParent()+File.separator+file.getName();
				simd.getJlblStuImg().setIcon(resizedIcon);
				
				FileInputStream fisImg = null;
//	            File tempResizedFile = null; 

	            try {
	                // a) Image를 BufferedImage로 변환 (ImageIO.write를 위해 필요)
	                BufferedImage bufferedResizedImage = new BufferedImage(100, 180, BufferedImage.TYPE_INT_RGB);
	                bufferedResizedImage.getGraphics().drawImage(scaledImage, 0, 0, null);
	                
	                CurrentStuData csd = CurrentStuData.getInstance();
	                
	                int stuNumInt = csd.getLogStuDTO().getStuNum();
	                String stuNum = String.valueOf(stuNumInt); 
	                String extension = csd.getLogStuDTO().getExt();
	                
	                String saveDirPath = "c:/dev/images/";
	                String saveFileName = stuNum + "s." + extension;
	                
//	                tempResizedFile = File.createTempFile("resized_img_", extension);
//	                tempResizedFile.deleteOnExit(); 
	                
	                File targetDir = new File(saveDirPath);
	                if (!targetDir.exists()) {
	                    // 디렉토리가 없으면 생성
	                    targetDir.mkdirs(); 
	                }
	                File targetFile = new File(saveDirPath + saveFileName);
	                
	                ImageIO.write(bufferedResizedImage, ext, targetFile); 
	                
	                // c) 임시 파일을 setProfImg가 요구하는 FileInputStream으로 읽어옴
	                fisImg = new FileInputStream(targetFile); 

	                // -------------------------------------------------------------
	                // 3. [기존 로직 수정] DTO에 데이터 전달 및 DB 저장
	                // -------------------------------------------------------------
	                
	                
	                // 원본 파일 객체를 setFile에 전달 (경로 참조용)
	                csd.getLogStuDTO().setFile(targetFile);       
	                // 🚨 핵심: 리사이징된 이미지의 FileInputStream 전달
	                csd.getLogStuDTO().setStuImg(fisImg);   
	                // 확장자 전달
	                csd.getLogStuDTO().setExt(ext);         

	                
	                if(sims.modifyStuImg(csd) == 1) { // 메서드명은 sims.modifyStuImg 대신 sims.modifyProfImg로 가정
	                    // DB 저장 성공 시
	                    if(fisImg != null) {fisImg.close();}
	                    
	                    // ⭐️ 임시 파일 삭제
//	                    if (tempResizedFile != null && tempResizedFile.exists()) {
//	                        tempResizedFile.delete();
//	                    }
	                } else {
	                    // DB 저장 실패 시에도 임시 파일 및 스트림 정리
	                    if(fisImg != null) {fisImg.close();}
//	                    if (tempResizedFile != null && tempResizedFile.exists()) {
//	                        tempResizedFile.delete();
//	                    }
	                } //end if (modifyProfImg)
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	                if (fisImg != null) {
	                    try {
	                        fisImg.close();
	                    } catch (IOException closeE) {
	                        closeE.printStackTrace();
	                    }
	                }
//	                if (fisImg != null) fisImg.close();
//	                if (tempResizedFile != null && tempResizedFile.exists()) tempResizedFile.delete();
	            }
			}else {
				jfcFlag=false;
			}
			//end if		
			
			
		}
	}
		
	            // -------------------------------------------------------------
	            // -------------------------------------------------------------
				
				
				/*
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
					*/
	//				lDTO.getFile().delete();
	
	
	
	
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
		InputStream is = null;
		
		ImageIcon ii = null;

		try {
		    is = getClass().getClassLoader().getResourceAsStream("properties/datebase.properties");
		    if (is == null) {
		        throw new IOException("datebase.properties 파일을 클래스패스에서 찾을 수 없습니다.");
		    }
		    prop.load(is);

		    // 1. 이미지 파일의 전체 경로를 조합합니다.
		    String imagePath = prop.getProperty("savePath") + csd.getLogStuDTO().getStuNum() + "s.png";
		    File imageFile = new File(imagePath); // File 객체 생성

		    // 2. 파일이 존재하는지 확인합니다.
		    if (imageFile.exists()) {
		        // 🚨 핵심 수정: ImageIO.read()를 사용하여 강제로 최신 파일 내용을 읽어옵니다.
		        try {
		            BufferedImage bImg = ImageIO.read(imageFile);
		            
		            if (bImg != null) {
		                // 3. 읽어온 BufferedImage를 기반으로 ImageIcon을 생성합니다.
		                ii = new ImageIcon(bImg);
		            } else {
		                // ImageIO.read에 실패한 경우 (파일이 손상되었거나 포맷 문제)
		                System.err.println("경고: 이미지 파일(" + imagePath + ")을 읽을 수 없습니다. (포맷/손상 문제)");
		            }
		        } catch (IOException readE) {
		            System.err.println("파일 시스템 읽기 오류: " + imagePath);
		            readE.printStackTrace();
		        }
		    }

		    // 4. 이미지를 로드하지 못했거나 파일이 존재하지 않는 경우 기본 이미지를 로드합니다.
		    if (ii == null) {
		        try (InputStream defaultIs = getClass().getResourceAsStream("/images/default_img.png")) {
		            if (defaultIs != null) {
		                ii = new ImageIcon(defaultIs.readAllBytes());
		            } else {
		                System.err.println("경고: 기본 이미지 파일을 찾을 수 없습니다.");
		            }
		        }
		    }
		    
		    // 5. 최종 로드된 이미지를 컴포넌트에 설정
		    if (ii != null) {
		        simd.getJlblStuImg().setIcon(ii);
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		    // 스트림 정리 로직은 try-with-resources나 finally 블록을 사용하면 좋습니다.
		}
	}
}
