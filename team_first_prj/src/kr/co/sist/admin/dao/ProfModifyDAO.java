package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.admin.dto.ProfDTO;
import kr.co.sist.login.dao.GetConnection;

public class ProfModifyDAO {

	
	private  static ProfModifyDAO pmDAO;
	
	private ProfModifyDAO() {
		
	}//StuModifyDAO
	
	public static ProfModifyDAO getInstance() {
		if(pmDAO==null) {
			pmDAO=new ProfModifyDAO();
		}//end if 
		return pmDAO;
		
	}//StuModifyDAO
	
	public int updateProfessor(ProfDTO pDTO) throws SQLException, IOException {
		int flag = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			
			StringBuilder updateProfessor = new StringBuilder();
			updateProfessor
			.append("		update PROFESSOR		    ")
			.append("		set PROF_NAME=?,PROF_PASS=?,PROF_TEL=?	")
			.append("		where PROF_NUM=?		     ");
			pstmt = con.prepareStatement(updateProfessor.toString());
			
			pstmt.setString(1,pDTO.getProfName());
			pstmt.setString(2,pDTO.getProfPass());
			pstmt.setString(3,pDTO.getProfTel());
			pstmt.setInt(4,pDTO.getProfNum());
			
			flag= pstmt.executeUpdate();//변경한 행의 수
			
		}finally {
			gc.dbClose(con, pstmt, null);
		}//end finally	
		return flag;
	}//updateStudent 
	

		
	
}//class
