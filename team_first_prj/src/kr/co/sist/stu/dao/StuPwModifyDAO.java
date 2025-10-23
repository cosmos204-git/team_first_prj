package kr.co.sist.stu.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.login.dao.GetConnection;

public class StuPwModifyDAO {
	
	private static StuPwModifyDAO spmDAO;
	
	private StuPwModifyDAO() {
		
	}
	
	public static StuPwModifyDAO getInstance() {
		if(spmDAO == null) {
			spmDAO = new StuPwModifyDAO();
		}
		
		return spmDAO;
	}
	
public int updateStuPw(CurrentStuData csDTO) throws SQLException, IOException {
		
	
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
			.append("	update	student			")
			.append("	set		stu_pass, stu_addr1=?, stu_addr2=? 	")
			.append("	where	stu_num=?			");
			
			pstmt = con.prepareStatement(updateMember.toString());
			
			//4. 바인드변수에 값 설정
			pstmt.setString(1, csDTO.getLogStuDTO().getStuEmail());
			pstmt.setString(2, csDTO.getLogStuDTO().getStuAddr1());
			pstmt.setString(3, csDTO.getLogStuDTO().getStuAddr2());
			pstmt.setInt(4, csDTO.getLogStuDTO().getStuNum());
			
			//5. 쿼리문 수행 후 결과 얻기 
			flag=pstmt.executeUpdate(); //변경한 행의 수		

		}finally {			
			gc.dbClose(con, pstmt, null);			
		}//end finally
		
		return flag;		
	}
	

}
