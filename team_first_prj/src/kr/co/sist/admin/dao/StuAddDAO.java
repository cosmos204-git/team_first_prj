package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.login.dao.GetConnection;

public class StuAddDAO {

	private static StuAddDAO saDAO;
	
	private StuAddDAO() {
		
	}//stuAddDAO
	
	public static StuAddDAO getInstance() {
		if(saDAO==null) {
			saDAO=new StuAddDAO();
		}//end if 
		return saDAO;
	}//stuAddDAO
	

	public int nextStuNum() throws SQLException, IOException {
		int StuNum=0;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			String nextProfNum = "select NVL(max(stu_num),0)+1 max from student";
			pstmt = con.prepareStatement(nextProfNum);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				
				StuNum=rs.getInt("max");
			}//end if 
			 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		
		return StuNum;
	}//nextProfNum
	
	
	
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
