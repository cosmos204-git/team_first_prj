package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.login.dao.GetConnection;

public class stuAddDAO {

	private static stuAddDAO saDAO;
	
	private stuAddDAO() {
		
	}//stuAddDAO
	
	public static stuAddDAO getInstance() {
		if(saDAO==null) {
			saDAO=new stuAddDAO();
		}//end if 
		return saDAO;
	}//stuAddDAO
	
	
	
	public int insertStudent(StudentDTO sDTO) throws SQLException,IOException {
		int flag =0;
		StudentDTO stDTO=sDTO;
	
		Connection con = null;
		PreparedStatement pstmt=null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			con=gc.getConn();
			String insertStu = "insert into Student(STU_NUM ,stu_Name ,STU_TEL ) values(sequence_stu_num.nextval,?,?)";
			pstmt = con.prepareStatement(insertStu);
			pstmt.setString(1,stDTO.getStuName());
			pstmt.setString(2,stDTO.getStuTel());
			
			flag=pstmt.executeUpdate();
		}finally {
			gc.dbClose(con, pstmt, null);
		}//end finally

		
		return flag;
	}//insertStudent


}//class
