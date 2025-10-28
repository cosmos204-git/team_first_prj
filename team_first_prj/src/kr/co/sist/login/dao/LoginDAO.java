package kr.co.sist.login.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kr.co.sist.login.dto.LoginAdminDTO;
import kr.co.sist.login.dto.LoginProfDTO;
import kr.co.sist.login.dto.LoginStudentDTO;


public class LoginDAO {
	
	private static LoginDAO logDAO;
	String userHome = System.getProperty("user.home");
	private LoginDAO() {
		
	}
	
	public static LoginDAO getInstance() {
		 
		if(logDAO==null) {
			logDAO = new LoginDAO();
		}
		return logDAO;
	
	}
	
	
	
	public LoginStudentDTO selectStuOneMember(int memberNum) throws SQLException, IOException {
		LoginStudentDTO logStuDTO = null;
		GetConnection gc = GetConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		FileOutputStream fos = null;
		try {
			con = gc.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectOneMember = new StringBuilder();
			selectOneMember
			.append("		SELECT STU_NUM,STU_IMG,STU_NAME,STU_PASS,STU_TEL,STU_EMAIL,STU_ADDR1,STU_ADDR2,STU_REG_INPUTDATE,STUDENT.COURSE_CODE,STU_DEL_FLAG, COURSE.COURSE_NAME")
			.append("		FROM  STUDENT")
			.append("		LEFT JOIN COURSE ON Student.course_code = COURSE.course_code")
			.append("		WHERE STU_NUM = ?");

			pstmt = con.prepareStatement(selectOneMember.toString());
			
			//4.바인드 변수 값 설정
			
			pstmt.setInt(1, memberNum);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) { //쿼리로 인한 조회 결과가 존재.
				logStuDTO=new LoginStudentDTO();
				logStuDTO.setStuNum(memberNum);
				logStuDTO.setStuName(rs.getString("stu_name"));
				logStuDTO.setStuPass(rs.getString("stu_pass"));
				logStuDTO.setStuTel(rs.getString("stu_tel"));
				logStuDTO.setStuEmail(rs.getString("stu_email"));
				logStuDTO.setStuAddr1(rs.getString("stu_addr1"));
				logStuDTO.setStuAddr2(rs.getString("stu_addr2"));
				logStuDTO.setStuInputDate(rs.getDate("stu_reg_inputdate"));
				logStuDTO.setStuCourseNum(rs.getInt("course_code"));
				logStuDTO.setStuCourseName(rs.getString("course_name"));
				logStuDTO.setStuDelFlag(rs.getString("stu_del_flag"));
				
				//이미지는 스트림을 별도로 연결하여 읽어 들인다.
				Properties prop = new Properties();

				try (InputStream is = getClass().getClassLoader().getResourceAsStream("properties/datebase.properties")) {
				    if (is == null) {
				        throw new IOException("database.properties 파일을 클래스패스에서 찾을 수 없습니다.");
				    }
				    prop.load(is);
				}
				
				//prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
				
				File dir = new File(prop.getProperty("savePath"));
				
				//디렉토리가 없다면 디렉토리를 생성
				if(!dir.exists()){
					dir.mkdirs();
				}//end if
				
				InputStream isl = rs.getBinaryStream("stu_img"); // 101행
				boolean isDefaultImage = false; // 기본 이미지를 사용했는지 여부 플래그

			
				
				if (isl == null) {
				    // ⭐️ BLOB 데이터가 null일 경우: 기본 이미지 파일 설정
				    isDefaultImage = true;		    
				    String resourcePath = "/images/default_img.png";
				    InputStream defaultIs = this.getClass().getResourceAsStream(resourcePath);
				    
				    
				    //String defaultImagePath = userHome + "/git/team_first_prj/team_first_prj/src/images/default_img.png"; 
				    //defaultImageFile = new File(defaultImagePath);
				    if (defaultIs != null) {
				        // 리소스가 존재하면, 해당 스트림을 isl에 할당
				        isl = defaultIs;
				        
				        // 확장자를 "png"로 고정 (리소스이므로 파일명 파싱이 불필요)
				        logStuDTO.setExt("png"); 
				    } else {
				        // c. 리소스를 찾을 수 없는 경우 (치명적 오류)
				        logStuDTO.setExt("png"); 
				        System.err.println("경고: DB에 프로필 이미지 없고, JAR 내부 기본 이미지 리소스 [" + resourcePath + "]도 찾을 수 없습니다.");
				        // isl은 null 상태로 유지되어 이후 복사 로직을 건너뜁니다.
				    }
				} else {
				    // DB에서 이미지를 불러온 경우, 확장자가 null이면 "png"로 설정 (기존 92~94행 로직)
				    if(logStuDTO.getExt()==null) {
				        logStuDTO.setExt("png");
				    }
				}
				File file = new File(dir.getAbsolutePath()+File.separator+logStuDTO.getStuNum()+"s."+logStuDTO.getExt()); 
				
				fos = new FileOutputStream(file); //파일이 존재하면 덮어쓰고, 존재하지 않으면 생성
						
    
				if (isl!=null) {
					try {
					int dataLength = 0;
					byte[] readData = new byte[512];
					
					while((dataLength = isl.read(readData)) != -1 ) { //읽어들인 내용이 존재한다면
						//읽어들인 내용의 길이까지 출력스트림으로 출력
						fos.write(readData,0,dataLength);
					}//end while
					if (isDefaultImage) {
						isl.close(); 
					}
					
					fos.flush(); 
					// ⭐️ DTO에 File 객체 설정
					logStuDTO.setFile(file);
					
					// ⭐️ DTO에 FileInputStream 설정 (저장된 파일을 읽기 위한 새 스트림)
					logStuDTO.setStuImg(new FileInputStream(file));
					
					} catch (IOException e) {
						System.err.println("파일 저장 또는 DTO 설정 중 오류 발생: " + e.getMessage());
						logStuDTO.setFile(null);
						logStuDTO.setStuImg(null);
					} finally {
						// ⭐️ fos (FileOutputStream) 안전하게 닫기
						if (fos != null) {
							try {
								fos.close();
							} catch (IOException e) {
								System.err.println("FileOutputStream 닫는 중 오류 발생: " + e.getMessage());
							}
						}
						
						// ⭐️ isl (InputStream) 닫기 (기존 로직 유지)
						if (isDefaultImage && isl != null) { // 기본 이미지 사용 시 isl을 닫음
							try {
								isl.close(); 
							} catch (IOException e) {
								System.err.println("InputStream(기본 이미지) 닫는 중 오류 발생: " + e.getMessage());
							}
						}
					}
				}//end if
					
			}				
				
		}finally {
			//5. 연결 끊기.
			gc.dbClose(con, pstmt, rs);
			
		}
		
		return logStuDTO;
		
		
	}//selectStuOneMember
	
	public List<LoginProfDTO> selectProfOneMember(int memberNum) throws SQLException, IOException {
		
		List<LoginProfDTO> list = new ArrayList<LoginProfDTO>();
		
		LoginProfDTO logProfDTO = null;
		GetConnection gc = GetConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		FileOutputStream fos = null;
		
		try {
			con = gc.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectOneMember = new StringBuilder();
			selectOneMember
			.append("		SELECT PROFESSOR.PROF_NUM,PROF_IMG,PROF_NAME,PROF_PASS,PROF_TEL,PROF_EMAIL,PROF_INPUTDATE,PROF_DEL_FLAG, COURSE.COURSE_NAME")
			.append("		FROM  PROFESSOR,COURSE")
			.append("		WHERE PROFESSOR.PROF_NUM = COURSE.PROF_NUM and PROFESSOR.PROF_NUM = ? and course_del_flag='N'");
			
			pstmt = con.prepareStatement(selectOneMember.toString());
			
			//4.바인드 변수 값 설정
			
			pstmt.setInt(1, memberNum);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) { //쿼리로 인한 조회 결과가 존재.
				logProfDTO=new LoginProfDTO();
				logProfDTO.setProfNum(memberNum);
				logProfDTO.setProfName(rs.getString("prof_name"));
				logProfDTO.setProfPass(rs.getString("prof_pass"));
				logProfDTO.setProfTel(rs.getString("prof_tel"));
				logProfDTO.setProfEmail(rs.getString("prof_email"));
				logProfDTO.setProfInputDate(rs.getDate("prof_inputdate"));
				logProfDTO.setProfDelFlag(rs.getString("prof_del_flag"));
				logProfDTO.setCourseName(rs.getString("course_name"));
				
				//이미지는 스트림을 별도로 연결하여 읽어 들인다.
				Properties prop = new Properties();
				try (InputStream is = getClass().getClassLoader().getResourceAsStream("properties/datebase.properties")) {
				    if (is == null) {
				        throw new IOException("database.properties 파일을 클래스패스에서 찾을 수 없습니다.");
				    }
				    prop.load(is);
				}
				//prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
				
				File dir = new File(prop.getProperty("savePath"));
				
				//디렉토리가 없다면 디렉토리를 생성
				if(!dir.exists()){
					dir.mkdirs();
				}//end if
				
				InputStream isl = rs.getBinaryStream("prof_img"); // 101행
				File defaultImageFile = null; // 기본 이미지 파일 객체
				boolean isDefaultImage = false; // 기본 이미지를 사용했는지 여부 플래그
				
				
				
				if (isl == null) {
				    // ⭐️ BLOB 데이터가 null일 경우: 기본 이미지 파일 설정
				    isDefaultImage = true;
				    
				    String resourcePath = "/images/default_img.png";

				    InputStream defaultIs = this.getClass().getResourceAsStream(resourcePath);
				    
				    
				    if (defaultIs != null) {
				        isl = defaultIs;
				        
				        logProfDTO.setExt("png"); 
				    } else {
				    	logProfDTO.setExt("png"); 
				        System.err.println("경고: DB에 프로필 이미지 없고, JAR 내부 기본 이미지 리소스 [" + resourcePath + "]도 찾을 수 없습니다.");
				    }
				} else {
				    if(logProfDTO.getExt()==null) {
				    	logProfDTO.setExt("png");
				    }
				}

				// -------------------------------------------------------------
				// 2. [기존 로직] 최종 파일 경로 생성
				// -------------------------------------------------------------
				// * 파일명은 "PK값 + 확장자"의 형식으로 다운로드됩니다.
				File file = new File(dir.getAbsolutePath()+File.separator+logProfDTO.getProfNum()+"p."+logProfDTO.getExt()); 

				// -------------------------------------------------------------
				// 3. [기존 로직] InputStream의 데이터를 디스크 파일로 쓰기 (104행 이후)
				// -------------------------------------------------------------
				
				//다운로드되는 파일명은 "PK값 + 확장자"의 형식
//				File file = new File(dir.getAbsolutePath()+File.separator+logStuDTO.getStuNum()+"."+logStuDTO.getExt());
				
				fos = new FileOutputStream(file); //파일이 존재하면 덮어쓰고, 존재하지 않으면 생성
						
				//입력스트림 얻기
				
				
				    
				if (isl!=null) {
					try {
					int dataLength = 0;
					byte[] readData = new byte[512];
					
					while((dataLength = isl.read(readData)) != -1 ) { //읽어들인 내용이 존재한다면
						//읽어들인 내용의 길이까지 출력스트림으로 출력
						fos.write(readData,0,dataLength);
					}//end while
						
					
					if (isDefaultImage) {
						isl.close(); 
					}
					
					fos.flush(); 
					// ⭐️ DTO에 File 객체 설정
					logProfDTO.setFile(file);
					
					// ⭐️ DTO에 FileInputStream 설정 (저장된 파일을 읽기 위한 새 스트림)
					logProfDTO.setProfImg(new FileInputStream(file));
					
					} catch (IOException e) {
						System.err.println("파일 저장 또는 DTO 설정 중 오류 발생: " + e.getMessage());
						logProfDTO.setFile(null);
						logProfDTO.setProfImg(null);
					} finally {
						// ⭐️ fos (FileOutputStream) 안전하게 닫기
						if (fos != null) {
							try {
								fos.close();
							} catch (IOException e) {
								System.err.println("FileOutputStream 닫는 중 오류 발생: " + e.getMessage());
							}
						}
						
						// ⭐️ isl (InputStream) 닫기 (기존 로직 유지)
						if (isDefaultImage && isl != null) { // 기본 이미지 사용 시 isl을 닫음
							try {
								isl.close(); 
							} catch (IOException e) {
								System.err.println("InputStream(기본 이미지) 닫는 중 오류 발생: " + e.getMessage());
							}
						}
					}
				}
				    //fos.close();
				//}//end if
				
//				//다운로드되는 파일명은 "PK값 + 확장자"의 형식
//				File file = new File(dir.getAbsolutePath()+File.separator+logProfDTO.getProfNum()+"p."+logProfDTO.getExt());
//				fos = new FileOutputStream(file); //파일이 존재하면 덮어쓰고, 존재하지 않으면 생성
//				
//				//입력스트림 얻기
//				is  = rs.getBinaryStream("prof_img");
//				if (is!=null) {
//					int dataLength = 0;
//					byte[] readData = new byte[512];
//					
//					while((dataLength = is.read(readData)) != -1 ) { //읽어들인 내용이 존재한다면
//						//읽어들인 내용의 길이까지 출력스트림으로 출력
//						fos.write(readData,0,dataLength);
//					}//end while
//					fos.flush();
//				}
//				
				list.add(logProfDTO);
				
			}//end while			
		}finally {
			//5. 연결 끊기.
			gc.dbClose(con, pstmt, rs);
			
		}
		
		
		return list;
		
	}//selectProfOneMember
	
	public LoginAdminDTO selectAdminOneMember(String id) throws SQLException, IOException {
		
		
		LoginAdminDTO logAdminDTO = null;
		GetConnection gc = GetConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		InputStream is = null;
		FileOutputStream fos = null;
		
		try {
			con = gc.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectOneMember = new StringBuilder();
			selectOneMember
			.append("		SELECT ADMIN_ID,ADMIN_PASS,ADMIN_INPUTDATE")
			.append("		FROM  ADMIN")
			.append("		WHERE ADMIN_ID = ?");
			
			pstmt = con.prepareStatement(selectOneMember.toString());
			
			//4.바인드 변수 값 설정
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) { //쿼리로 인한 조회 결과가 존재.
				logAdminDTO=new LoginAdminDTO();
				logAdminDTO.setAdminId(id);
				logAdminDTO.setAdminPass(rs.getString("admin_pass"));
				logAdminDTO.setAdminInputDate(rs.getDate("admin_inputdate"));

			}//end if
			
			
		}finally {
			//5. 연결 끊기.
			gc.dbClose(con, pstmt, rs);
			
		}
		
	
		return logAdminDTO;
		
	}//selectProfOneMember	
	
	
	
	
	
	
	
}
