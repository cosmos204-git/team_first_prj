package kr.co.sist.prof.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.login.dto.LoginProfDTO;
import kr.co.sist.prof.dto.ProfCourseSubjectDTO;

public class ScoreMgrDAO {
	
	private static ScoreMgrDAO smd;
	String userHome = System.getProperty("user.home");
	
	private ScoreMgrDAO() {
		
	}
	
	public static ScoreMgrDAO getInstance() {
		
		if(smd==null) {
			smd = new ScoreMgrDAO();
		}
		
		return smd;
	}
	
	public List<ProfCourseSubjectDTO> selectAllCourse(int memberNum) throws SQLException, IOException {
	
		List<ProfCourseSubjectDTO> list = new ArrayList<ProfCourseSubjectDTO>();
		
		ProfCourseSubjectDTO csjDTO = null;
		GetConnection gc = GetConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = gc.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectOneMember = new StringBuilder();
			selectOneMember
			.append("		SELECT PROF_NUM, COURSE_NAME")
			.append("		FROM  COURSE")
			.append("		WHERE PROF_NUM = ? and course_del_flag='N' ");
			
			pstmt = con.prepareStatement(selectOneMember.toString());
			
			//4.바인드 변수 값 설정
			
			pstmt.setInt(1, memberNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //쿼리로 인한 조회 결과가 존재.
				csjDTO=new ProfCourseSubjectDTO();
				csjDTO.setCourseName(rs.getString("course_name"));
		
				
				//이미지는 스트림을 별도로 연결하여 읽어 들인다.
				Properties prop = new Properties();
				prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
				
				File dir = new File(prop.getProperty("savePath"));
				
				//디렉토리가 없다면 디렉토리를 생성
				if(!dir.exists()){
					dir.mkdirs();
				}//end if
				
				list.add(csjDTO);
				
			}//end while			
		}finally {
			//5. 연결 끊기.
			gc.dbClose(con, pstmt, rs);
			
		}
		
		
		return list;
	}
	
	
	public List<ProfCourseSubjectDTO> selectAllSubject(int memberNum, String courseName) throws SQLException, IOException {
		
		if(courseName == null) {
			courseName = "%";
		}
		
		List<ProfCourseSubjectDTO> list = new ArrayList<ProfCourseSubjectDTO>();
		
		ProfCourseSubjectDTO csjDTO = null;
		GetConnection gc = GetConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = gc.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectOneMember = new StringBuilder();
			selectOneMember
			.append("		SELECT PROF_NUM, SUB_NAME")
			.append("		FROM  COURSE, SUBJECT")
			.append("		WHERE PROF_NUM = ?  and course_name = ? and course_del_flag='N' ");
			
			pstmt = con.prepareStatement(selectOneMember.toString());
			
			//4.바인드 변수 값 설정
			
			pstmt.setInt(1, memberNum);
			pstmt.setString(2, courseName);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) { //쿼리로 인한 조회 결과가 존재.
				csjDTO=new ProfCourseSubjectDTO();
				
				csjDTO.setSubName(rs.getString("sub_name"));

				//이미지는 스트림을 별도로 연결하여 읽어 들인다.
				Properties prop = new Properties();
				prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
				
				File dir = new File(prop.getProperty("savePath"));
				
				//디렉토리가 없다면 디렉토리를 생성
				if(!dir.exists()){
					dir.mkdirs();
				}//end if
				
				list.add(csjDTO);
				
				
			}//end while
		}finally {
			//5. 연결 끊기.
			gc.dbClose(con, pstmt, rs);
			
		}
		
		return list;
	}
	

}
