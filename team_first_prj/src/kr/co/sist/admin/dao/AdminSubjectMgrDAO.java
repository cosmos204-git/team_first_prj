package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import kr.co.sist.admin.dto.CourseDTO;
import kr.co.sist.admin.dto.SubjectDTO;
import kr.co.sist.login.dao.GetConnection;

public class AdminSubjectMgrDAO {
	static AdminSubjectMgrDAO asmDAO;
	
	private AdminSubjectMgrDAO() {
		
	}
	
	public static AdminSubjectMgrDAO getinstance() {
		if(asmDAO == null ) {
			asmDAO = new AdminSubjectMgrDAO();
		}
		return asmDAO;
	}
	
	public List<SubjectDTO> selectSub() throws IOException, SQLException{
		List<SubjectDTO> subList = new ArrayList<SubjectDTO>();
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con = gc.getConn();
			
			StringBuilder selectSub = new StringBuilder();
			selectSub
			.append("	select  sub_code, sub_name, to_char(sub_inputdate,'yyyy-mm-dd') sub_inputdate	")
			.append("	from subject	");
			pstmt = con.prepareStatement(selectSub.toString());
			
			rs=pstmt.executeQuery();
			int subCode = 0;
			String subName = "";
			String inputdate = "";
			while(rs.next()) {
				subCode = rs.getInt("sub_code");
				subName = rs.getString("sub_name");
				inputdate = rs.getString("sub_inputdate");
				
				SubjectDTO sDTO = new SubjectDTO(subCode,subName,inputdate);
				subList.add(sDTO);
			}
		
		}finally {
			gc.dbClose(con, pstmt, rs);
		}
		 
		
		return subList;
	}//selectSub
	
	public List<CourseDTO> selectCourse() throws IOException, SQLException{
		List<CourseDTO> courseList = new ArrayList<CourseDTO>();
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con = gc.getConn();
			
			StringBuilder selectCourse = new StringBuilder();
			selectCourse
			.append("	select course_code, course_name	")
			.append("	from Course	")
			.append("	where course_del_flag='N'	");
			pstmt = con.prepareStatement(selectCourse.toString());
			
			rs=pstmt.executeQuery();
			
			int courseCode = 0 ;
			String courseName = "";
			
			while(rs.next()) {
				courseCode = rs.getInt("course_code");
				courseName = rs.getString("course_name");
				
				CourseDTO cDTO = new CourseDTO(courseCode, courseName);
				
				courseList.add(cDTO);
			}
		
		}finally {
			gc.dbClose(con, pstmt, rs);
		}
		 
		
		return courseList;
	}//selectCourse
	
	public List<SubjectDTO> selectCourseSub(JComboBox<String> jc) throws IOException, SQLException{
		List<SubjectDTO> sDTOList = new ArrayList<SubjectDTO>();
		
		String courseName = jc.getSelectedItem().toString();
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con = gc.getConn();
			
			StringBuilder selectCourse = new StringBuilder();
			selectCourse
			.append("	 select s.sub_code, sub_name, to_char(sub_inputdate,'yyyy-mm-dd') sub_inputdate	")
			.append("	 from subject s , course_subject cs	")
			.append("	 where s.sub_code = cs.sub_code	")
			.append("	and cs.course_code = (select course_code from course where course_name = ?)");
		
			
			pstmt = con.prepareStatement(selectCourse.toString());
			
			pstmt.setString(1, courseName);
			
			rs=pstmt.executeQuery();
			
			int subCode = 0;
			String subName = "";
			String inputdate ="";
			
			SubjectDTO sDTO = null;
			while(rs.next()) {
				subCode = rs.getInt("sub_code");
				subName = rs.getString("sub_name");
				inputdate = rs.getString("sub_inputdate");
				
				sDTO = new SubjectDTO(subCode, subName, inputdate);
				sDTOList.add(sDTO);
			}
		}finally {
			gc.dbClose(con, pstmt, rs);
		}
		 
		
		return sDTOList;
	}//selectCourseSub
	private Connection con;
	private GetConnection gc;
	public int insertCourseSub(int courseCode, int SubjectCode) throws IOException, SQLException {
		int rowCnt =0;
		int rowCnt2 =0;
		int totalCnt =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 =null;
		
		gc = GetConnection.getInstance();
		
			con = gc.getConn();
			
			String insertCourseSub = "	insert into course_subject values(?,?)	 ";
			
			pstmt = con.prepareStatement(insertCourseSub);
			
			pstmt.setInt(1, courseCode);
			pstmt.setInt(2, SubjectCode);
			
			rowCnt = pstmt.executeUpdate();
			
			String insertExam = "	insert into exam(test_code,exam_open,course_code, sub_code) values(sequence_test_code.nextval,'시험불가',?,?)	 ";
			
			pstmt2 = con.prepareStatement(insertExam);
			
			pstmt2.setInt(1, courseCode);
			pstmt2.setInt(2, SubjectCode);
			
			rowCnt = pstmt2.executeUpdate();
		
			totalCnt=rowCnt + rowCnt2;
		
		return totalCnt;
	}//insertCouseSub
	
	public int deleteCourseSub(int courseCode, int SubjectCode) throws IOException, SQLException {
		int rowCnt =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			con = gc.getConn();
			
			String deleteCourseSub = "	delete from course_subject where course_code= ? and sub_code = ?	 ";
			
			pstmt = con.prepareStatement(deleteCourseSub);
			
			pstmt.setInt(1, courseCode);
			pstmt.setInt(2, SubjectCode);

			rowCnt = pstmt.executeUpdate();

		}finally {
			gc.dbClose(con, pstmt, null);
		}//end finally
		
		return rowCnt;
	}//deleteCouseSub

	public Connection getCon() {
		return con;
	}

	public GetConnection getGc() {
		return gc;
	}
	
	
}//class
