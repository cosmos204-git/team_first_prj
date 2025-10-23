package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.admin.dto.ProfDTO;
import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.login.dao.GetConnection;

public class AdminProfMgrDAO {

	private static AdminProfMgrDAO apDAO;
	
	private AdminProfMgrDAO(){
		
	}//AdminStuMgrDAO
	
	public static AdminProfMgrDAO getInstance() {
		if(apDAO==null) {
			apDAO=new AdminProfMgrDAO();
		}//end if 
		return apDAO;
	}//getInstance
	
	/**
	 * 모든 학생의 정보 조회
	 * @return
	 * @throws SQLException
	 */
	public List<ProfDTO> selectAllProfessor() throws SQLException,IOException{
		ProfDTO pDTO=null;
		
		List<ProfDTO> list = new ArrayList<ProfDTO>();
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder selectProfessor= new StringBuilder();
			selectProfessor
			.append("	select PROF_NUM, PROF_NAME, PROF_TEL, PROF_INPUTDATE,PROF_DEL_FLAG	")
			.append("	from PROFESSOR												")
			.append("	where PROF_DEL_FLAG='N' 								 	")
			.append("	order by PROF_NUM asc								 		");
			
			pstmt = con.prepareStatement(selectProfessor.toString());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				pDTO = new ProfDTO();
				pDTO.setProfNum(rs.getInt("PROF_NUM"));
				pDTO.setProfName(rs.getString("PROF_NAME"));
				pDTO.setProfTel(rs.getString("PROF_TEL"));
				pDTO.setProfInputDate (rs.getDate("PROF_INPUTDATE"));
		
				list.add(pDTO);
			}//end if 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		return list;
	}//selectAllStudent
	
	
	/**
	 * 선택된 학생의 데이터만 출력 
	 * @param stuNum
	 * @return StudentDTO
	 * @throws SQLException
	 * @throws IOException
	 */
	public ProfDTO selectProfessor(int ProfNum) throws SQLException,IOException{
		ProfDTO pDTO=null;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder selectOneProfessor= new StringBuilder();
			selectOneProfessor
			.append("	select PROF_NUM, PROF_NAME, PROF_TEL, PROF_INPUTDATE,PROF_DEL_FLAG	")
			.append("	from PROFESSOR												")
			.append("	where 	PROF_DEL_FLAG='N' and PROF_NUM=?						");
		
			
			pstmt = con.prepareStatement(selectOneProfessor.toString());
			
			
			pstmt.setInt(1, ProfNum);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				pDTO = new ProfDTO();
				pDTO.setProfNum(rs.getInt("PROF_NUM"));
				pDTO.setProfName(rs.getString("PROF_NAME"));
				pDTO.setProfTel(rs.getString("PROF_TEL"));
				pDTO.setProfInputDate(rs.getDate("PROF_INPUTDATE"));
		
			}//end if 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		return pDTO;
	}//selectStudent
	
	public int deleteProfessor(int ProfNum) throws SQLException, IOException {
		int flag = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			
			StringBuilder updateProfessor = new StringBuilder();
			updateProfessor
			.append("		update PROFESSOR		    		  ")
			.append("		set PROF_DEL_FLAG='Y'			 	  ")
			.append("		where PROF_NUM=?		    		  ");
			
			pstmt = con.prepareStatement(updateProfessor.toString());
			
			pstmt.setInt(1,ProfNum);
			
			flag= pstmt.executeUpdate();//변경한 행의 수
			
		}finally {
			gc.dbClose(con, pstmt, null);
		}//end finally	
		return flag;
	}//deleteProfessor
	
}//class
