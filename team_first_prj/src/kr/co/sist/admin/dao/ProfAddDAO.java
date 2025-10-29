package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public int nextProfNum() throws SQLException, IOException {
		int ProNum=0;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			String nextProfNum = "select NVL(max(PROF_NUM),0)+1 max from PROFESSOR";
			pstmt = con.prepareStatement(nextProfNum);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				
				ProNum=rs.getInt("max");
			}//end if 
			 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		
		return ProNum;
	}//nextProfNum
	
//	public int nextProfNum() throws SQLException, IOException {
//		int ProNum=0;
//		
//		Connection con = null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		GetConnection gc = GetConnection.getInstance();
//		
//		try {
//			con=gc.getConn();
//			try {
//				
//				
//				String nextProfNum = "select sequence_stu_num.CURRVAL AS max from dual";
//				pstmt = con.prepareStatement(nextProfNum);
//				rs=pstmt.executeQuery();
////				
////				if(rs.next()){
////					
////					ProNum=rs.getInt("max");
////				}//end if 
//			}catch (SQLException se) {
////				con=gc.getConn();
//				String nextProfNum = "select sequence_stu_num.nextval AS max from dual";
//				pstmt = con.prepareStatement(nextProfNum);
//				rs=pstmt.executeQuery();
////				
////				if(rs.next()){
////					
////					ProNum=rs.getInt("max");
////				}//end if 
//				
//			}//end catch
//			
//			
//			if(rs.next()){
//				
//				ProNum=rs.getInt("max");
//			}//end if
//			
//		}finally {
//			gc.dbClose(con, pstmt, rs);
//		}//end finally
//		
//		return ProNum;
//	}//nextProfNum
	

	
	
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
