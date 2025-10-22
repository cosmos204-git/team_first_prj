package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.admin.dto.StudentDTO;

public class AdminStuMgrDAO {

	private static AdminStuMgrDAO aDAO;
	
	private AdminStuMgrDAO(){
		
	}//AdminStuMgrDAO
	
	public static AdminStuMgrDAO getInstance() {
		if(aDAO==null) {
			aDAO=new AdminStuMgrDAO();
		}//end if 
		return aDAO;
	}//getInstance
	
	/**
	 * 모든 학생의 정보 조회
	 * @return
	 * @throws SQLException
	 */
	public List<StudentDTO> selectAllStudent() throws SQLException,IOException{
		StudentDTO sDTO=null;
		
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder selectStudent= new StringBuilder();
			selectStudent
			.append("	select stu_num,stu_name,stu_tel,stu_pass,stu_reg_inputdate	")
			.append("	from 	student												");
			
			pstmt = con.prepareStatement(selectStudent.toString());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				sDTO = new StudentDTO();
				sDTO.setStuNum(rs.getInt("stu_num"));
				sDTO.setStuName(rs.getString("stu_name"));
				sDTO.setStuTel(rs.getString("stu_tel"));
				sDTO.setStuInputDate(rs.getDate("stu_reg_inputdate"));
		
				list.add(sDTO);
			}//end if 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		return list;
	}//selectAllStudent
}//class
