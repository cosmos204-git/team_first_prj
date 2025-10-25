package kr.co.sist.prof.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.prof.dto.ProfCourseSubjectDTO;
import kr.co.sist.prof.dto.ProfStuScoreDTO;

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
			.append("		SELECT PROF_NUM, COURSE_NAME, COURSE_CODE")
			.append("		FROM  COURSE")
			.append("		WHERE PROF_NUM = ? and course_del_flag='N' ");
			
			pstmt = con.prepareStatement(selectOneMember.toString());
			
			//4.바인드 변수 값 설정
			
			pstmt.setInt(1, memberNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //쿼리로 인한 조회 결과가 존재.
				csjDTO=new ProfCourseSubjectDTO();
				csjDTO.setCourseName(rs.getString("course_name"));
				csjDTO.setCourseCode(rs.getInt("course_code"));
				
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
	
	
	public List<ProfCourseSubjectDTO> selectAllSubject(int memberNum, int courseCode) throws SQLException, IOException {
		

		
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
			.append("		SELECT PROF_NUM, SUB_CODE, SUB_NAME")
			.append("		FROM  COURSE, SUBJECT")
			.append("		WHERE PROF_NUM = ?  and course_code = ? and course_del_flag='N' ");
			
			pstmt = con.prepareStatement(selectOneMember.toString());
			
			//4.바인드 변수 값 설정
			
			pstmt.setInt(1, memberNum);
			pstmt.setInt(2, courseCode);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) { //쿼리로 인한 조회 결과가 존재.
				csjDTO=new ProfCourseSubjectDTO();
				csjDTO.setSubCode(rs.getInt("sub_code"));
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
	
	public List<ProfStuScoreDTO> selectSubStuCourseScore(int profNum, int courseCode, int subCode, int stuNum) throws SQLException, IOException {

	
		
		
		List<ProfStuScoreDTO> list = new ArrayList<ProfStuScoreDTO>();
		
		ProfStuScoreDTO pssDTO = null;
		GetConnection gc = GetConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = gc.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectReport = new StringBuilder();
			selectReport
			.append("  		select stu_num,stu_name,sub_code,sub_name,stu_score from		(")
			.append("		SELECT S.stu_num,S.stu_name,SJ.sub_code, SJ.sub_name,  R.stu_score")
			.append("		FROM  student S")
			.append("		JOIN report R ON S.stu_num = R.stu_num")
			.append("		JOIN subject SJ ON R.sub_code = SJ.sub_code")
			.append("		JOIN course C ON R.course_code = C.course_code")
			.append("		JOIN professor P ON C.prof_num = P.prof_num")
			.append("		WHERE p.PROF_NUM = ?");
//					+ "  and c.course_code = ? and R.sub_code =? )")
//			.append("		WHERE stu_num = ?");
			    
			if(courseCode == -1) {
				selectReport.append("and c.course_code = c.course_code ");
			}else {
				selectReport.append("and c.course_code = " + courseCode + " ");
			}
			
			
			if(subCode == -1) {
				selectReport.append("and R.sub_code = R.sub_code ");
				
			}else {
				selectReport.append("and R.sub_code = " + subCode + " ");
			}
			
			
			if(stuNum == -1) {
				selectReport.append(") WHERE stu_num = stu_num");
			}else {
				selectReport.append(") WHERE stu_num =" +stuNum);
			}
				
			pstmt = con.prepareStatement(selectReport.toString());
			
			//4.바인드 변수 값 설정
			
			
			pstmt.setInt(1, profNum);
		
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) { //쿼리로 인한 조회 결과가 존재.
				pssDTO=new ProfStuScoreDTO();
				
				pssDTO.setStuNum(rs.getInt("stu_num"));
				pssDTO.setStuName(rs.getString("stu_name"));
				pssDTO.setStuScore(rs.getInt("stu_score"));
				pssDTO.setSubCode(rs.getInt("sub_code"));
				pssDTO.setSubName(rs.getString("sub_name"));
				

				//이미지는 스트림을 별도로 연결하여 읽어 들인다.
				Properties prop = new Properties();
				prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
				
				File dir = new File(prop.getProperty("savePath"));
				
				//디렉토리가 없다면 디렉토리를 생성
				if(!dir.exists()){
					dir.mkdirs();
				}//end if
				
				list.add(pssDTO);
				
				
			}//end while
		}finally {
			//5. 연결 끊기.
			gc.dbClose(con, pstmt, rs);
			
		}
		
		return list;
	}
	

}
