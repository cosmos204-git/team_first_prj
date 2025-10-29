package kr.co.sist.stu.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.login.dto.LoginStudentDTO;
import kr.co.sist.stu.dto.SearchCourseDTO;

public class SearchCourseDAO {

	private static SearchCourseDAO scDAO;

	private SearchCourseDAO() {}

	public static SearchCourseDAO getInstance() {
		if (scDAO == null) {
			scDAO = new SearchCourseDAO();
		}
		return scDAO;
	}

	public List<SearchCourseDTO> selectCourse(int stuNum) throws SQLException, IOException {

		List<SearchCourseDTO> list = new ArrayList<>();
		
		GetConnection gc = GetConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = gc.getConn();
			StringBuilder selectCourse = new StringBuilder();
		    selectCourse
	        .append("SELECT c.course_code, c.course_name, c.course_startdate, ")
	        .append("c.course_enddate, c.course_inputdate, c.course_del_flag, ")
	        .append("CASE WHEN s.course_code = c.course_code THEN 1 ELSE 0 END AS check_course ")
	        .append("FROM course c ")
	        .append("LEFT JOIN student s ON s.stu_num = ? ")
	        .append("where c.course_del_flag='N'													")
	        .append("ORDER BY c.course_startdate DESC");
		    
		    
//	        .append("select c.course_code, c.course_name, c.course_startdate, ")
//	        .append("c.course_enddate, c.course_inputdate, c.course_del_flag ")
//	        .append("from course c inner join student s on c.course_code = s.course_code ")
//	        .append("where s.stu_num = ? ")
//	        .append("AND course_startdate < sysdate AND course_enddate > sysdate ")
//	        .append("order by c.course_startdate desc");

		  
			pstmt = con.prepareStatement(selectCourse.toString());
			pstmt.setInt(1, stuNum);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SearchCourseDTO scDTO = new SearchCourseDTO();
				scDTO.setCourseCode(rs.getInt("course_code"));
				scDTO.setCourseName(rs.getString("course_name"));
				scDTO.setCourseStartDate(rs.getDate("course_startdate"));
				scDTO.setCourseEndDate(rs.getDate("course_enddate"));
				scDTO.setCourseInputDate(rs.getDate("course_inputdate"));
				scDTO.setCourseFlag(rs.getBoolean("course_del_flag"));
				//save check_course data
	            scDTO.setCheckCourse(rs.getInt("check_course") == 1); 
	            
				list.add(scDTO);
			}//end while

		} finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally

		return list;
	}//selectCourse

	
	/**
	 * update the lsDTO when they apply the course
	 * @param lsDTO
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public int updateCourse(LoginStudentDTO lsDTO) throws SQLException, IOException {
		int flag = 0;
		GetConnection gc = GetConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = gc.getConn();
			String updateCourse = "update student set course_code = ? where stu_num = ?";
			pstmt = con.prepareStatement(updateCourse);
			pstmt.setInt(1, lsDTO.getStuCourseNum());
			pstmt.setInt(2, lsDTO.getStuNum());
			flag = pstmt.executeUpdate();
		} finally {
			gc.dbClose(con, pstmt, null);
		}//end finally

		return flag;
	}//updateCourse
	
}//class