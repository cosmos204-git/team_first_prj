package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.admin.dto.ProfDTO;
import kr.co.sist.login.dao.GetConnection;

public class ProfAddDAO {

	private static ProfAddDAO paDAO;
	
	private ProfAddDAO() {
		
	}//stuAddDAO
	
	public static ProfAddDAO getInstance() {
		if(paDAO==null) {
			paDAO=new ProfAddDAO();
		}//end if 
		return paDAO;
	}//stuAddDAO
	

	
	
	public int insertProfessor(ProfDTO pDTO) throws SQLException,IOException {
		int flag =0;
		ProfDTO pfDTO=pDTO;
	
		Connection con = null;
		PreparedStatement pstmt=null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			con=gc.getConn();
			String insertProf = "insert into PROFESSOR(PROF_NUM ,PROF_NAME ,PROF_TEL ) values(sequence_prof_num.nextval,?,?)";
			pstmt = con.prepareStatement(insertProf);
			pstmt.setString(1,pfDTO.getProfName());
			pstmt.setString(2,pfDTO.getProfTel());
			
			flag=pstmt.executeUpdate();
		}finally {
			gc.dbClose(con, pstmt, null);
		}//end finally

		
		return flag;
	}//insertStudent


}//class
