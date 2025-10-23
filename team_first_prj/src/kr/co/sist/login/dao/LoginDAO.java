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
		
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			con = gc.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectOneMember = new StringBuilder();
			selectOneMember
			.append("		SELECT STU_NUM,STU_IMG,STU_NAME,STU_PASS,STU_TEL,STU_EMAIL,STU_ADDR1,STU_ADDR2,STU_REG_INPUTDATE,STUDENT.COURSE_CODE,STU_DEL_FLAG, COURSE.COURSE_NAME")
			.append("		FROM  STUDENT, COURSE")
			.append("		WHERE student.course_code = course.course_code and STU_NUM = ?");

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
				
				prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
				
				File dir = new File(prop.getProperty("savePath"));
				
				//디렉토리가 없다면 디렉토리를 생성
				if(!dir.exists()){
					dir.mkdirs();
				}//end if
				if(logStuDTO.getExt()==null) {
					logStuDTO.setExt("png");
				}
				//다운로드되는 파일명은 "PK값 + 확장자"의 형식
//				File file = new File(dir.getAbsolutePath()+File.separator+logStuDTO.getStuNum()+"."+logStuDTO.getExt());
				File file = new File(dir.getAbsolutePath()+File.separator+logStuDTO.getStuNum()+"s."+logStuDTO.getExt());
				
				fos = new FileOutputStream(file); //파일이 존재하면 덮어쓰고, 존재하지 않으면 생성
						
				//입력스트림 얻기
				is  = rs.getBinaryStream("stu_img");
				
				
				if (is!=null) {
					int dataLength = 0;
					byte[] readData = new byte[512];
					
					while((dataLength = is.read(readData)) != -1 ) { //읽어들인 내용이 존재한다면
						//읽어들인 내용의 길이까지 출력스트림으로 출력
						fos.write(readData,0,dataLength);
					}//end while
					fos.flush();
				}
			}//end if
			
			
		}finally {
			//5. 연결 끊기.
			gc.dbClose(con, pstmt, rs);
			
		}
		
		return logStuDTO;
		
		
	}//selectStuOneMember
	
	public LoginProfDTO selectProfOneMember(int memberNum) throws SQLException, IOException {
		
		LoginProfDTO logProfDTO = null;
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
			.append("		SELECT PROFESSOR.PROF_NUM,PROF_IMG,PROF_NAME,PROF_PASS,PROF_TEL,PROF_EMAIL,PROF_INPUTDATE,PROF_DEL_FLAG, COURSE.COURSE_NAME")
			.append("		FROM  PROFESSOR,COURSE")
			.append("		WHERE PROFESSOR.PROF_NUM = COURSE.PROF_NUM and PROFESSOR.PROF_NUM = ?");
			
			pstmt = con.prepareStatement(selectOneMember.toString());
			
			//4.바인드 변수 값 설정
			
			pstmt.setInt(1, memberNum);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) { //쿼리로 인한 조회 결과가 존재.
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
				prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
				
				File dir = new File(prop.getProperty("savePath"));
				
				//디렉토리가 없다면 디렉토리를 생성
				if(!dir.exists()){
					dir.mkdirs();
				}//end if
				
				if(logProfDTO.getExt()==null) {
					logProfDTO.setExt("png");
				}
				
				//다운로드되는 파일명은 "PK값 + 확장자"의 형식
				File file = new File(dir.getAbsolutePath()+File.separator+logProfDTO.getProfNum()+"p."+logProfDTO.getExt());
				fos = new FileOutputStream(file); //파일이 존재하면 덮어쓰고, 존재하지 않으면 생성
				
				//입력스트림 얻기
				is  = rs.getBinaryStream("prof_img");
				if (is!=null) {
					int dataLength = 0;
					byte[] readData = new byte[512];
					
					while((dataLength = is.read(readData)) != -1 ) { //읽어들인 내용이 존재한다면
						//읽어들인 내용의 길이까지 출력스트림으로 출력
						fos.write(readData,0,dataLength);
					}//end while
					fos.flush();
				}
			}//end if
			
			
		}finally {
			//5. 연결 끊기.
			gc.dbClose(con, pstmt, rs);
			
		}
		
		return logProfDTO;
		
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
