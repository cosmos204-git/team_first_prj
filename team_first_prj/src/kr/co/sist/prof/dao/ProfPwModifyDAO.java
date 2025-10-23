package kr.co.sist.prof.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.login.dao.GetConnection;

public class ProfPwModifyDAO {

	private static ProfPwModifyDAO ppmDAO;
	
	private ProfPwModifyDAO() {
		
	}
	
	public static ProfPwModifyDAO getInstance() {
		if(ppmDAO == null) {
			ppmDAO = new ProfPwModifyDAO();
		}
		
		return ppmDAO;
	}
	
	public int updateProfPw(CurrentProfData cpDTO) throws SQLException, IOException {
		
		
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
			.append("	update	professor				")
			.append("	set		prof_pass=?		 	")
			.append("	where	prof_num=?			");
			
			pstmt = con.prepareStatement(updateMember.toString());
			
			//4. 바인드변수에 값 설정
			pstmt.setString(1, cpDTO.getLogProfDTO().getProfPass());
			pstmt.setInt(2, cpDTO.getLogProfDTO().getProfNum());
			
			//5. 쿼리문 수행 후 결과 얻기 
			flag=pstmt.executeUpdate(); //변경한 행의 수		
			
		}finally {			
			gc.dbClose(con, pstmt, null);			
		}//end finally
		
		return flag;		
	}
	
}
