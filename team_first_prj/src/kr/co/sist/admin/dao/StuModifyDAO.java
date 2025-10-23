package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.login.dao.GetConnection;

public class StuModifyDAO {

	
	private  static StuModifyDAO smDAO;
	
	private StuModifyDAO() {
		
	}//StuModifyDAO
	
	public static StuModifyDAO getInstance() {
		if(smDAO==null) {
			smDAO=new StuModifyDAO();
		}//end if 
		return smDAO;
		
	}//StuModifyDAO
	
	public int updateStudent(StudentDTO sDTO) throws SQLException, IOException {
		int flag = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			
			StringBuilder updateStudent = new StringBuilder();
			updateStudent
			.append("		update student		    ")
			.append("		set STU_NAME=?,STU_PASS=?,STU_TEL=?	")
			.append("		where STU_NUM=?		     ");
			pstmt = con.prepareStatement(updateStudent.toString());
			
			pstmt.setString(1,sDTO.getStuName());
			pstmt.setString(2,sDTO.getStuPass());
			pstmt.setString(3,sDTO.getStuTel());
			pstmt.setInt(4,sDTO.getStuNum());
			
			flag= pstmt.executeUpdate();//변경한 행의 수
			
		}finally {
			gc.dbClose(con, pstmt, null);
		}//end finally	
		return flag;
	}//updateStudent 
	

		
	
}//class
