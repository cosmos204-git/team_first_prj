package kr.co.sist.prof.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.login.dao.GetConnection;


public class ProfInfoModifyDAO {
	
	private static ProfInfoModifyDAO pimDAO;
	
	
	private ProfInfoModifyDAO() {
		
	}
	
	public static ProfInfoModifyDAO getInstance() {
		if(pimDAO==null) {
			pimDAO = new ProfInfoModifyDAO();
		}//end if
		return pimDAO;

	}
	
	
	public int updateProfInfo(CurrentProfData cpDTO) throws SQLException, IOException {
		
		int flag = 0;
		
		GetConnection gc = GetConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			//1.드라이버 로딩
			//2.커넥션 얻기
			con=gc.getConn();
			//3. 쿼리문 생성 객체 얻기
			//회원번호를 사용하여 나이,전화번호를 변경
			StringBuilder updateMember = new StringBuilder();
			
			updateMember
			.append("	update	professor			")
			.append("	set		prof_email=?		")
			.append("	where	prof_num=?			");
			
			pstmt = con.prepareStatement(updateMember.toString());
			
			//4. 바인드변수에 값 설정
			pstmt.setString(1, cpDTO.getLogProfDTO().getProfEmail());
			pstmt.setInt(2, cpDTO.getLogProfDTO().getProfNum());
			
			//5. 쿼리문 수행 후 결과 얻기 
			flag=pstmt.executeUpdate(); //변경한 행의 수		

		}finally {			
			gc.dbClose(con, pstmt, null);			
		}//end finally
		
		return flag;		
	}
	
	public int updateProfImg(CurrentProfData cpDTO) throws SQLException, IOException{
		
		int flag = 0;
		
		//1. 드라이버로딩
		//2. 커넥션 얻기
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {				
			con=gc.getConn();
			//con.setAutoCommit(false); //연결이 안끊기면 commit 처리가 되지 않게 설정
			
			//3. 쿼리문 생성객체 얻기
			String updateImg = "update professor set prof_img = ? where prof_num = ?";
			
			pstmt = con.prepareStatement(updateImg);
			//4. 바인드변수에 값 설정
			pstmt.setBinaryStream(1, cpDTO.getLogProfDTO().getProfImg(), cpDTO.getLogProfDTO().getFile().length());
			pstmt.setInt(2, cpDTO.getLogProfDTO().getProfNum());
			
			
			//5. 쿼리문 수행 후 결과 얻기
			flag = pstmt.executeUpdate();
//			if(flag==1) {
//				con.commit();
//			}						
		}finally {
			//6. 연결끊기
			gc.dbClose(con, pstmt, null);
		}
		
		
		
		return flag;
	}
	

}
