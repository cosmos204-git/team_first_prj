package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import kr.co.sist.admin.dto.RegStuMgrDTO;
import kr.co.sist.login.dao.GetConnection;
import oracle.jdbc.proxy.annotation.Pre;

public class AdminRegStuMgrDAO {

	static AdminRegStuMgrDAO aasmDAO;

	private AdminRegStuMgrDAO() {

	}// AdminAllStuMgrDAO

	public static AdminRegStuMgrDAO getinstance() {

		if (aasmDAO == null) {
			aasmDAO = new AdminRegStuMgrDAO();
		}
		return aasmDAO;
	}

	public List<RegStuMgrDTO> selectAllStu() throws IOException, SQLException {
		List<RegStuMgrDTO> rsmDTOList = new ArrayList<RegStuMgrDTO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();
		try {
			con = gc.getConn();

			StringBuilder selectAllStu = new StringBuilder();
			selectAllStu.append("	select course_name, prof_name, stu_num, stu_name, stu_reg_inputdate	")
					.append("	from student s, course c, professor p	")
					.append("	where s.course_code= c.course_code and c.prof_num=p.prof_num	")
					.append("	order by c.course_code	");

			pstmt = con.prepareStatement(selectAllStu.toString());

			rs = pstmt.executeQuery();

			String courseName, profName, stuName, sysdate;
			int stuNum;
			while (rs.next()) {
				courseName = rs.getString("course_name");
				profName = rs.getString("prof_name");
				stuNum = rs.getInt("stu_num");
				stuName = rs.getString("stu_name");
				sysdate = rs.getString("stu_reg_inputdate");
				RegStuMgrDTO rsmDTO = new RegStuMgrDTO(stuNum, courseName, profName, stuName, sysdate);

				rsmDTOList.add(rsmDTO);
			} // end while

		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally

		return rsmDTOList;
	}// selectAllStu

	public List<String> selectCombo() throws IOException, SQLException {
		List<String> cList = new ArrayList<String>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();

			StringBuilder selectCombo = new StringBuilder();
			selectCombo.append("	select course_name	").append("	from course	")
					.append("	where course_del_flag='N'	");

			pstmt = con.prepareStatement(selectCombo.toString());

			rs = pstmt.executeQuery();

			String combo = null;
			while (rs.next()) {
				combo = rs.getString("course_name");

				cList.add(combo);
			} // end while
		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally

		return cList;
	}// selectCombo

	public List<RegStuMgrDTO> selectStu(JComboBox<String> jc, JTextField jtfStuNum) throws SQLException,IOException{
		List<RegStuMgrDTO> cList = new ArrayList<RegStuMgrDTO>();

		//콤보 박스 값 얻기
		String jcCourseName = jc.getSelectedItem().toString();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();
			
			StringBuilder selectStu = new StringBuilder();
			if(jtfStuNum.getText()==null || jtfStuNum.getText().trim().isEmpty()&&jc.getSelectedItem().toString().isEmpty()) {
				selectStu
				.append("	select course_name, prof_name, stu_num, stu_name, stu_reg_inputdate	")
				.append("	from student s, course c, professor p	")
				.append("	where s.course_code= c.course_code and c.prof_num=p.prof_num	")
				.append("	order by c.course_code, stu_num	");
				
				pstmt = con.prepareStatement(selectStu.toString());
				
			}else if(jtfStuNum.getText()!=null && !jtfStuNum.getText().trim().isEmpty() && !jc.getSelectedItem().toString().isEmpty()) {
				int stuNum = Integer.parseInt(jtfStuNum.getText().trim());
				selectStu
				.append("	select course_name, prof_name, stu_num, stu_name, stu_reg_inputdate	")
				.append("	from student s, course c, professor p	")
				.append("	where s.course_code= c.course_code and c.prof_num=p.prof_num	")
				.append("	and c.course_code = (select course_code from course where course_name=?)	 ")
				.append("	and stu_num =?	 ")
				.append("	order by c.course_code, stu_num	 ");
						
				pstmt = con.prepareStatement(selectStu.toString());
				
				pstmt.setString(1, jcCourseName);
				pstmt.setInt(2, stuNum);
				
			}else if(jtfStuNum.getText()!=null && !jtfStuNum.getText().trim().isEmpty() && jc.getSelectedItem().toString().isEmpty()) {
				int stuNum = Integer.parseInt(jtfStuNum.getText().trim());
				selectStu
				.append("	select course_name, prof_name, stu_num, stu_name, stu_reg_inputdate	")
				.append("	from student s, course c, professor p	")
				.append("	where s.course_code= c.course_code and c.prof_num=p.prof_num	")
				.append("	and stu_num =?	 ")
				.append("	order by c.course_code, stu_num	 ");
				
				pstmt = con.prepareStatement(selectStu.toString());
				
				
				pstmt.setInt(1, stuNum);
			
			}else if(jtfStuNum.getText()==null || jtfStuNum.getText().trim().isEmpty() && !jc.getSelectedItem().toString().isEmpty()) {
				selectStu
				.append("	select course_name, prof_name, stu_num, stu_name, stu_reg_inputdate	")
				.append("	from student s, course c, professor p	")
				.append("	where s.course_code= c.course_code and c.prof_num=p.prof_num	")
				.append("	and c.course_code = (select course_code from course where course_name=?)	 ")
				.append("	order by c.course_code, stu_num	 ");
				
				pstmt = con.prepareStatement(selectStu.toString());
				
				pstmt.setString(1, jcCourseName);
			
			}//end if
					
			rs = pstmt.executeQuery();

			String courseName, profName, stuName, sysdate ;
			int stuNum =0;
			while (rs.next()) {
				courseName = rs.getString("course_name");
				stuNum = rs.getInt("stu_num");
				profName = rs.getString("prof_name");
				stuName = rs.getString("stu_name");
				sysdate = rs.getString("stu_reg_inputdate");
				
				RegStuMgrDTO rsmDTO = new RegStuMgrDTO(stuNum, courseName, profName, stuName, sysdate);

				cList.add(rsmDTO);
			} // end while
		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally

		return cList;
	}//selectStu

}// class
