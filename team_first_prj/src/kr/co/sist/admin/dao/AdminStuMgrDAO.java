package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.admin.dto.StudentDTO;
import kr.co.sist.login.dao.GetConnection;

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
			.append("	from 	student												")
			.append("	where 	STU_DEL_FLAG='N' 								 	")
			.append("	order by stu_num asc								 		");
			
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
	
	
	/**
	 * 선택된 학생의 데이터만 출력 
	 * @param stuNum
	 * @return StudentDTO
	 * @throws SQLException
	 * @throws IOException
	 */
	public StudentDTO selectStudent(int stuNum) throws SQLException,IOException{
		StudentDTO sDTO=null;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder selectOneStudent= new StringBuilder();
			selectOneStudent
			.append("	select stu_num,stu_name,stu_tel,stu_pass,stu_reg_inputdate	")
			.append("	from 	student												")
			.append("	where 	STU_DEL_FLAG='N' and stu_num=?						");

			
			pstmt = con.prepareStatement(selectOneStudent.toString());
			
			
			pstmt.setInt(1, stuNum);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				sDTO = new StudentDTO();
				sDTO.setStuNum(rs.getInt("stu_num"));
				sDTO.setStuName(rs.getString("stu_name"));
				sDTO.setStuTel(rs.getString("stu_tel"));
				sDTO.setStuInputDate(rs.getDate("stu_reg_inputdate"));
		
			}//end if 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		return sDTO;
	}//selectStudent
	
	public int deleteStudent(int StuNum) throws SQLException, IOException {
		int flag = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			
			StringBuilder updateStudent = new StringBuilder();
			updateStudent
			.append("		update student		    ")
			.append("		set stu_del_flag='Y'	")
			.append("		where STU_NUM=?		     ");
			
			pstmt = con.prepareStatement(updateStudent.toString());
			
			pstmt.setInt(1,StuNum);
			
			flag= pstmt.executeUpdate();//변경한 행의 수
			
		}finally {
			gc.dbClose(con, pstmt, null);
		}//end finally	
		return flag;
	}//removeStudent
	
}//class
