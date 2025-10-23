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
import kr.co.sist.admin.dto.ScoreMgrDTO;
import kr.co.sist.admin.dto.SubjectDTO;
import kr.co.sist.admin.view.AdminScoreMgrDesign;
import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.login.dao.GetConnection;

public class ScoreMgrDAO {
	
	static ScoreMgrDAO smDAO;
	
	private ScoreMgrDAO() {
		
	}//AdminScoreMgrDAO
	
	public static ScoreMgrDAO getinstance() {
		if(smDAO==null) {
			smDAO = new ScoreMgrDAO();
		}//end if
		return smDAO;
	}
	
	public List<CourseDTO> selectCourse() throws IOException,SQLException{
		List<CourseDTO> smDTOL = new ArrayList<CourseDTO>();
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
		con = gc.getConn();
		
		String selectCourse= "select course_code,course_name from course where course_del_flag='N'";
		
		pstmt = con.prepareStatement(selectCourse);
		
		rs = pstmt.executeQuery();
		
		String courseName = null;
		int courseCode = 0;
		while(rs.next()) {
			courseName = rs.getString("course_name");
			courseCode = rs.getInt("course_code");
//			CourseDTO cDTO= new CourseDTO(courseCode, null, courseName, null, null, null);
			CourseDTO cDTO = new CourseDTO(courseCode,courseName);
			smDTOL.add(cDTO);
		}//end while
			
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally		
		return smDTOL;
	}//selectCourse
	
	
	public List<SubjectDTO> selectSubject(JComboBox<String> jc) throws SQLException,IOException{
		List<SubjectDTO> sDTOList = new ArrayList<SubjectDTO>();
		
		//콤보 박스의 과정명 가져오기
		String scName = jc.getSelectedItem().toString();
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			String selectSub ="select s.sub_name , s.sub_code from subject s, course_subject cs where s.sub_code=cs.sub_code and course_code=(select course_code from course where course_name = ?)";
			pstmt = con.prepareStatement(selectSub);
			
			pstmt.setString(1,scName);
			
			rs = pstmt.executeQuery();
		
			
			String sub = null;
			int code = 0;
			while(rs.next()) {
			sub = rs.getString("sub_name");
			code = rs.getInt("sub_code");
			SubjectDTO sDTO = new SubjectDTO(code,sub);
			sDTOList.add(sDTO);
			}//end while
			
			
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		for(SubjectDTO s : sDTOList) {
			
			System.out.println(s);
		}
		return sDTOList;
	}//selectSubject
	
}//class
