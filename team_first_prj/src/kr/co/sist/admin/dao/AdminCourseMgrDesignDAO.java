package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.sist.admin.dto.CourseMgrDTO;
import kr.co.sist.login.dao.GetConnection;

public class AdminCourseMgrDesignDAO {
	private static AdminCourseMgrDesignDAO acmdDAO;
	
	public static AdminCourseMgrDesignDAO getInstance() {
		if(acmdDAO==null) {
			acmdDAO=new AdminCourseMgrDesignDAO();
		}//end if
		return acmdDAO;
	}//AdminCourseMgrDesignDAO
	
	public int nextCourseNum() throws SQLException, IOException {
		int CourseNum=0;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			String nextProfNum = "select NVL(max(COURSE_CODE),0)+1000 max from COURSE";
			pstmt = con.prepareStatement(nextProfNum);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				
				CourseNum=rs.getInt("max");
			}//end if 
			 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		
		return CourseNum;
	}//nextProfNum
	

	public List<CourseMgrDTO> selectAllCourse() throws SQLException,IOException{
		CourseMgrDTO cmDTO = null;
		
		List<CourseMgrDTO> list = new ArrayList<CourseMgrDTO>();
		
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			con=gc.getConn();
			StringBuilder selectCourse= new StringBuilder();
			selectCourse
			.append("SELECT c.COURSE_CODE, ")
			.append("       c.COURSE_NAME || CASE ")
			.append("           WHEN (c.COURSE_STARTDATE <= TRUNC(SYSDATE)) AND (c.COURSE_ENDDATE >= TRUNC(SYSDATE)) THEN '(진행 중)' ")
			.append("           WHEN (c.COURSE_STARTDATE > TRUNC(SYSDATE)) THEN '(진행 전)' ")
			.append("           ELSE '(종료)' END AS COURSE_NAME, p.PROF_NUM, p.PROF_NAME, ")
			.append("       TO_CHAR(c.COURSE_STARTDATE, 'yyyy-MM-dd') COURSE_STARTDATE, ")
			.append("       TO_CHAR(c.COURSE_ENDDATE, 'yyyy-MM-dd') COURSE_ENDDATE, ")
			.append("       TO_CHAR(c.COURSE_INPUTDATE, 'yyyy-MM-dd') COURSE_INPUTDATE ")
			.append("FROM COURSE c ")
			.append("LEFT JOIN PROFESSOR p ON c.PROF_NUM = p.PROF_NUM AND p.PROF_DEL_FLAG = 'N' ")
			.append("WHERE c.COURSE_DEL_FLAG = 'N' ")
			.append("ORDER BY c.COURSE_CODE ASC ");
	
			
			

			pstmt = con.prepareStatement(selectCourse.toString());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				
				cmDTO = new CourseMgrDTO();
				

				cmDTO.setCourseCode(rs.getInt("COURSE_CODE"));
				cmDTO.setCourseName(rs.getString("COURSE_NAME"));
				cmDTO.setProfNum(rs.getInt("PROF_NUM"));
				cmDTO.setProfName(rs.getString("PROF_NAME"));
				cmDTO.setCourseStartDate(String.valueOf(rs.getDate("COURSE_STARTDATE")));
				cmDTO.setCourseEndDate(String.valueOf(rs.getDate("COURSE_ENDDATE")));
				cmDTO.setCourseInputDate(String.valueOf(rs.getDate("COURSE_INPUTDATE")));
		
				list.add(cmDTO);
			}//end while
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
				
		return list;
	}//selectAllCourse
	
//	public List<CourseMgrDTO> selectAllCourse() throws SQLException,IOException{
//		CourseMgrDTO cmDTO = null;
//		
//		List<CourseMgrDTO> list = new ArrayList<CourseMgrDTO>();
//		
//		Connection con = null;
//		PreparedStatement pstmt= null;
//		ResultSet rs = null;
//		
//		GetConnection gc = GetConnection.getInstance();
//		try {
//			con=gc.getConn();
//			StringBuilder selectCourse= new StringBuilder();
//			selectCourse
//			.append("select c.COURSE_CODE,c.COURSE_NAME||CASE WHEN  c.COURSE_ENDDATE-c.COURSE_STARTDATE >0 THEN '(진행)' ELSE '(종료)'  END as COURSE_NAME")
//			.append(", p.PROF_NUM,p.PROF_NAME, c.COURSE_STARTDATE, c.COURSE_ENDDATE, c.COURSE_INPUTDATE")
//			.append("	from COURSE c,PROFESSOR p		")
//			.append("where (c.PROF_NUM=p.PROF_NUM )and COURSE_DEL_FLAG='N'")
//			.append("order by c.COURSE_CODE asc");
//
//			pstmt = con.prepareStatement(selectCourse.toString());
//			
//			rs=pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				
//				cmDTO = new CourseMgrDTO();
//				
//
//				cmDTO.setCourseCode(rs.getInt("COURSE_CODE"));
//				cmDTO.setCourseName(rs.getString("COURSE_NAME"));
//				cmDTO.setProfNum(rs.getInt("PROF_NUM"));
//				cmDTO.setProfName(rs.getString("PROF_NAME"));
//				cmDTO.setCourseStartDate(String.valueOf(rs.getDate("COURSE_STARTDATE")));
//				cmDTO.setCourseEndDate(String.valueOf(rs.getDate("COURSE_ENDDATE")));
//				cmDTO.setCourseInputDate(String.valueOf(rs.getDate("COURSE_INPUTDATE")));
//		
//				list.add(cmDTO);
//			}//end while
//		}finally {
//			gc.dbClose(con, pstmt, rs);
//		}//end finally
//				
//		return list;
//	}//selectAllCourse
	
//	public List<String> selectCombo() throws IOException, SQLException {
//		List<String> list = new ArrayList<String>();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		GetConnection gc = GetConnection.getInstance();
//
//		try {
//			con = gc.getConn();
//
//			StringBuilder selectCombo = new StringBuilder();
//			selectCombo.append("	select PROF_NUM,PROF_NAME	").append("	from PROFESSOR	")
//					.append("	where PROF_DEL_FLAG='N'	");
//
//			pstmt = con.prepareStatement(selectCombo.toString());
//
//			rs = pstmt.executeQuery();
//			String profName=null;
//			while (rs.next()) {
//				profName=rs.getString("PROF_NAME");
//
//				list.add(profName);
//			} // end while
//		} finally {
//			gc.dbClose(con, pstmt, rs);
//		} // end finally
//
//		return list;
//	}// selectCombo
//	
//	
	public Map<String, String> selectCombo() throws IOException, SQLException {
		Map<String, String> list = new HashMap<String, String>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();

			StringBuilder selectCombo = new StringBuilder();
			selectCombo.append("	select PROF_NUM,PROF_NAME	").append("	from PROFESSOR	")
					.append("	where PROF_DEL_FLAG='N'	");

			pstmt = con.prepareStatement(selectCombo.toString());

			rs = pstmt.executeQuery();
//			String combo=null;
			while (rs.next()) {
				String profName=rs.getString("PROF_NAME");
				String profNum=String.valueOf(rs.getInt("PROF_NUM"));
		
				list.put(profName,profNum);
			} // end while
		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally

		return list;
	}// selectCombo
	
//	public List<CourseMgrDTO> selectCourse(String courseName) throws SQLException, IOException {
//
//
//		CourseMgrDTO cmDTO=null;
//		
//		List<CourseMgrDTO> list = new ArrayList<CourseMgrDTO>();
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs= null;
//		
//		GetConnection gc = GetConnection.getInstance();
//		try {
//			con=gc.getConn();
//			StringBuilder selectCourse = new StringBuilder();
//			selectCourse
//			.append("	select c.COURSE_CODE,c.COURSE_NAME||CASE WHEN  c.COURSE_ENDDATE-c.COURSE_STARTDATE >0 THEN '(진행)' ELSE '(종료)'  END as COURSE_NAME	")
//			.append("	, p.PROF_NUM,p.PROF_NAME, to_char(c.COURSE_STARTDATE,'yyyy-MM-dd') COURSE_STARTDATE, 	")
//			.append("	to_char(c.COURSE_ENDDATE,'yyyy-MM-dd') COURSE_ENDDATE, to_char(c.COURSE_INPUTDATE,'yyyy-MM-dd') COURSE_INPUTDATE	")
//			.append("	from COURSE c,PROFESSOR p	")
//			.append("	where (c.PROF_NUM=p.PROF_NUM )and (PROF_DEL_FLAG='N'and COURSE_DEL_FLAG='N') and c.COURSE_NAME=?	")
//			.append("	order by c.COURSE_CODE asc	");
//			
//
//		
//			pstmt= con.prepareStatement(selectCourse.toString());
//			
//			pstmt.setString(1, courseName);
//			
//			rs=pstmt.executeQuery();
//			
//			while(rs.next()) {
//				cmDTO=new CourseMgrDTO();
//				cmDTO.setCourseCode(rs.getInt("COURSE_CODE"));
//				cmDTO.setCourseName(rs.getString("COURSE_NAME"));
//				cmDTO.setProfNum(rs.getInt("PROF_NUM"));
//				cmDTO.setProfName(rs.getString("PROF_NAME"));
//				cmDTO.setCourseStartDate(String.valueOf(rs.getDate("COURSE_STARTDATE")));
//				cmDTO.setCourseEndDate(String.valueOf(rs.getDate("COURSE_ENDDATE")));
//				cmDTO.setCourseInputDate(String.valueOf(rs.getDate("COURSE_INPUTDATE")));
//				
//				list.add(cmDTO);
//			}//while
//		}finally {
//			gc.dbClose(con, pstmt, rs);
//		}//end finally
//	
//		return list; 
//	}//selectCourse
	
	public List<CourseMgrDTO> selectCourse(String courseName) throws SQLException, IOException {


		CourseMgrDTO cmDTO=null;
		
		List<CourseMgrDTO> list = new ArrayList<CourseMgrDTO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			con=gc.getConn();
			StringBuilder selectCourse = new StringBuilder();
			selectCourse
			.append("	select c.COURSE_CODE,c.COURSE_NAME ||CASE	")
			.append("	WHEN  (c.COURSE_STARTDATE<=TRUNC(sysdate)) and (c.COURSE_ENDDATE>=TRUNC(sysdate)) and(c.COURSE_ENDDATE-c.COURSE_STARTDATE >=0) THEN '(진행 중)' 	")
			.append("	WHEN  (c.COURSE_STARTDATE>TRUNC(sysdate)) and(c.COURSE_ENDDATE-c.COURSE_STARTDATE >=0) THEN '(진행 전)'	")
			.append("	ELSE '(종료)'  END as COURSE_NAME	")
			.append("	, p.PROF_NUM,p.PROF_NAME, to_char(c.COURSE_STARTDATE,'yyyy-MM-dd') COURSE_STARTDATE,	")
			.append("	to_char(c.COURSE_ENDDATE,'yyyy-MM-dd') COURSE_ENDDATE, to_char(c.COURSE_INPUTDATE,'yyyy-MM-dd') COURSE_INPUTDATE	")
			.append("	from COURSE c,PROFESSOR p	")
			.append("	where (c.PROF_NUM=p.PROF_NUM )and (PROF_DEL_FLAG='N'and COURSE_DEL_FLAG='N') and c.COURSE_NAME like ?||'%'	")
			.append("	order by c.COURSE_CODE asc	");
			
			

		
			pstmt= con.prepareStatement(selectCourse.toString());
			
			pstmt.setString(1, courseName);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				cmDTO=new CourseMgrDTO();
				cmDTO.setCourseCode(rs.getInt("COURSE_CODE"));
				cmDTO.setCourseName(rs.getString("COURSE_NAME"));
				cmDTO.setProfNum(rs.getInt("PROF_NUM"));
				cmDTO.setProfName(rs.getString("PROF_NAME"));
				cmDTO.setCourseStartDate(String.valueOf(rs.getDate("COURSE_STARTDATE")));
				cmDTO.setCourseEndDate(String.valueOf(rs.getDate("COURSE_ENDDATE")));
				cmDTO.setCourseInputDate(String.valueOf(rs.getDate("COURSE_INPUTDATE")));
				
				list.add(cmDTO);
			}//while
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
	
		return list; 
	}//selectCourse
	
	public int updateCourse(CourseMgrDTO cDTO) throws SQLException, IOException {
		int flag=0;
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
		
			con=gc.getConn();
			
			StringBuilder updateCourse = new StringBuilder();
			updateCourse
			.append("   UPDATE COURSE				")
			.append("	SET COURSE_NAME = ?,COURSE_STARTDATE = ? ,COURSE_ENDDATE  = ?, COURSE_INPUTDATE = sysdate ")
			.append("	,PROF_NUM=(select PROF_NUM from  PROFESSOR where PROF_NAME=? and PROF_DEL_FLAG='N')")			
//			.append("	,PROF_NUM=?")			
			.append("	WHERE  COURSE_CODE=? AND COURSE_DEL_FLAG = 'N'			");
			
			pstmt=con.prepareStatement(updateCourse.toString());
			
			
//			pstmt.setString(1,cDTO.getCourseName());
//			pstmt.setString(2,cDTO.getCourseStartDate());
//			pstmt.setString(3,cDTO.getCourseEndDate());
//			pstmt.setString(4,cDTO.getProfName());
//			pstmt.setInt(5,cDTO.getCourseCode());
			pstmt.setString(1,cDTO.getCourseName());
			pstmt.setString(2,cDTO.getCourseStartDate());
			pstmt.setString(3,cDTO.getCourseEndDate());
			pstmt.setString(4,cDTO.getProfName());
			pstmt.setInt(5,cDTO.getCourseCode());
	
			
			
			flag=pstmt.executeUpdate();
		}finally {
			
			gc.dbClose(con, pstmt, rs);
		}//end finally

		return flag;
	}//updateCourse
	
	public int insertCourse(CourseMgrDTO cmDTO) throws SQLException, IOException {
		int flag =0;
		
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			
			StringBuilder insertCourse = new StringBuilder();
			insertCourse
			.append(" insert into COURSE(COURSE_CODE,COURSE_NAME, PROF_NUM, COURSE_STARTDATE, COURSE_ENDDATE)  ")
			.append(" values (sequence_course_code.nextval,?,(select PROF_NUM from PROFESSOR where PROF_NAME=?),")
			.append("	?,?)	");
			
			
			pstmt=con.prepareStatement(insertCourse.toString());
			
			pstmt.setString(1, cmDTO.getCourseName());
			pstmt.setString(2, cmDTO.getProfName());
			pstmt.setString(3, cmDTO.getCourseStartDate());
			pstmt.setString(4, cmDTO.getCourseEndDate());
			
			flag = pstmt.executeUpdate();
			
			
	
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		
		
		
		
		return flag;
	}//insertCourse
	
	
	public int  deleteCourse(int courseCode) throws SQLException, IOException {
		int flag =0;
		
		
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		
		try {
			con=gc.getConn();
			StringBuilder deleteCourse = new StringBuilder();
			deleteCourse
			.append("	update COURSE		")
			.append("	set COURSE_DEL_FLAG='Y'		")
			.append("	where COURSE_CODE=?		");
			
			pstmt=con.prepareStatement(deleteCourse.toString());
			
			pstmt.setInt(1, courseCode);
			
			flag=pstmt.executeUpdate();
			
		}finally {
			gc.dbClose(con, pstmt, rs);
			
		}//end finally
		return flag;
	}//deleteCourse
}//class


