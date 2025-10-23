package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.admin.dto.CourseDTO;
import kr.co.sist.admin.dto.ScoreMgrDTO;
import kr.co.sist.admin.dto.SubjectDTO;
import kr.co.sist.admin.view.AdminScoreMgrDesign;
import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.login.dao.GetConnection;

public class ScoreMgrDAO {

	static ScoreMgrDAO smDAO;

	private ScoreMgrDAO() {

	}// AdminScoreMgrDAO

	public static ScoreMgrDAO getinstance() {
		if (smDAO == null) {
			smDAO = new ScoreMgrDAO();
		} // end if
		return smDAO;
	}

	public List<CourseDTO> selectCourse() throws IOException, SQLException {
		List<CourseDTO> smDTOL = new ArrayList<CourseDTO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();
		try {
			con = gc.getConn();

			String selectCourse = "select course_code,course_name from course where course_del_flag='N'";

			pstmt = con.prepareStatement(selectCourse);

			rs = pstmt.executeQuery();

			String courseName = null;
			int courseCode = 0;
			while (rs.next()) {
				courseName = rs.getString("course_name");
				courseCode = rs.getInt("course_code");
//			CourseDTO cDTO= new CourseDTO(courseCode, null, courseName, null, null, null);
				CourseDTO cDTO = new CourseDTO(courseCode, courseName);
				smDTOL.add(cDTO);
			} // end while

		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally
		return smDTOL;
	}// selectCourse

	public List<SubjectDTO> selectSubject(JComboBox<String> jc) throws SQLException, IOException {
		List<SubjectDTO> sDTOList = new ArrayList<SubjectDTO>();

		// 콤보 박스의 과정명 가져오기
		String scName = jc.getSelectedItem().toString();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();
			String selectSub = "select s.sub_name , s.sub_code from subject s, course_subject cs where s.sub_code=cs.sub_code and course_code=(select course_code from course where course_name = ?)";
			pstmt = con.prepareStatement(selectSub);

			pstmt.setString(1, scName);

			rs = pstmt.executeQuery();

			String sub = null;
			int code = 0;
			while (rs.next()) {
				sub = rs.getString("sub_name");
				code = rs.getInt("sub_code");
				SubjectDTO sDTO = new SubjectDTO(code, sub);
				sDTOList.add(sDTO);
			} // end while

		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally
		for (SubjectDTO s : sDTOList) {

			System.out.println(s);
		}
		return sDTOList;
	}// selectSubject

	public List<ScoreMgrDTO> selectScore(JComboBox<String> cjc, JComboBox<String> sjc, JTextField jtfstuNum)
			throws SQLException, IOException {
		List<ScoreMgrDTO> smDTOList = new ArrayList<ScoreMgrDTO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();

			String jcCourse = cjc.getSelectedItem().toString();
			String jcSub = sjc.getSelectedItem().toString();

			StringBuilder selectScore = new StringBuilder();
			if (jtfstuNum != null && !jtfstuNum.getText().trim().isEmpty() && !jcCourse.isEmpty() && !jcSub.isEmpty()) {
				// 과정,과목 콤보 박스 값 얻기
				int stuNum = Integer.parseInt(jtfstuNum.getText());
				selectScore.append("	select 	r.stu_num stu, stu_name, sub_name, stu_score	")
						.append("	from report r, subject s , student st	")
						.append("   where r.sub_code =s.sub_code and r.stu_num=st.stu_num    ")
						.append("   and r.stu_num = ?    ")
						.append("   and r.course_code = (select course_code from course where course_name=?)    ")
						.append("   and r.sub_code=(select sub_code from subject where sub_name=?)    ")
						.append("   and st.stu_del_flag='N'    ");

				pstmt = con.prepareStatement(selectScore.toString());

				pstmt.setInt(1, stuNum);
				pstmt.setString(2, jcCourse);
				pstmt.setString(3, jcSub);

			} else if (jtfstuNum == null
					|| jtfstuNum.getText().trim().isEmpty() && !jcCourse.isEmpty() && !jcSub.isEmpty()) {
				selectScore.append("	select 	r.stu_num stu, stu_name, sub_name, stu_score	")
						.append("	from report r, subject s , student st	")
						.append("   where r.sub_code =s.sub_code and r.stu_num=st.stu_num    ")
						.append("   and r.course_code = (select course_code from course where course_name=?)    ")
						.append("   and r.sub_code=(select sub_code from subject where sub_name=?)    ")
						.append("   and st.stu_del_flag='N'    ");

				pstmt = con.prepareStatement(selectScore.toString());

				pstmt.setString(1, jcCourse);
				pstmt.setString(2, jcSub);
			} else if (jtfstuNum == null
					|| jtfstuNum.getText().trim().isEmpty() && !jcCourse.isEmpty() && jcSub.isEmpty()) {
				selectScore.append("	select 	r.stu_num stu, stu_name, sub_name, stu_score	")
						.append("	from report r, subject s , student st	")
						.append("   where r.sub_code =s.sub_code and r.stu_num=st.stu_num    ")
						.append("   and r.course_code = (select course_code from course where course_name=?)    ")
						.append("   and st.stu_del_flag='N'    ");

				pstmt = con.prepareStatement(selectScore.toString());

				pstmt.setString(1, jcCourse);
			} else if (jtfstuNum == null
					|| jtfstuNum.getText().trim().isEmpty() && jcCourse.isEmpty() && jcSub.isEmpty()) {
				selectScore.append("	select 	r.stu_num stu, stu_name, sub_name, stu_score	")
						.append("	from report r, subject s , student st	")
						.append("   where r.sub_code =s.sub_code and r.stu_num=st.stu_num    ")
						.append("   and st.stu_del_flag='N'    ");

				pstmt = con.prepareStatement(selectScore.toString());

			} else if (jtfstuNum != null&& !jtfstuNum.getText().trim().isEmpty() && !jcCourse.isEmpty() && jcSub.isEmpty()) {
				int stuNum = Integer.parseInt(jtfstuNum.getText());
			selectScore.append("	select 	r.stu_num stu, stu_name, sub_name, stu_score	")
			.append("	from report r, subject s , student st	")
			.append("   where r.sub_code =s.sub_code and r.stu_num=st.stu_num    ")
			.append("   and r.stu_num = ?    ")
			.append("   and r.course_code = (select course_code from course where course_name=?)    ")
			.append("   and st.stu_del_flag='N'    ");
			
			pstmt = con.prepareStatement(selectScore.toString());
			
			pstmt.setInt(1, stuNum);
			pstmt.setString(2, jcCourse);

			} else if (jtfstuNum != null&& !jtfstuNum.getText().trim().isEmpty() && jcCourse.isEmpty() && jcSub.isEmpty()) {
				int stuNum = Integer.parseInt(jtfstuNum.getText());
				selectScore.append("	select 	r.stu_num stu, stu_name, sub_name, stu_score	")
				.append("	from report r, subject s , student st	")
				.append("   where r.sub_code =s.sub_code and r.stu_num=st.stu_num    ")
				.append("   and r.stu_num = ?    ")
				.append("   and st.stu_del_flag='N'    ");
			
			pstmt = con.prepareStatement(selectScore.toString());
			
			pstmt.setInt(1, stuNum);
			
		} // end if
			rs = pstmt.executeQuery();

			int stuNum = 0;
			String stuName = null;
			String subName = null;
			int score = 0;
			while (rs.next()) {
				stuNum = rs.getInt("stu");
				stuName = rs.getString("stu_name");
				subName = rs.getString("sub_name");
				score = rs.getInt("stu_score");
				ScoreMgrDTO smDTO = new ScoreMgrDTO(stuNum, score, stuName, subName);
				smDTOList.add(smDTO);
			} // end while

		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally

		return smDTOList;
	}// selectScore
	
	
	public List<ScoreMgrDTO> selelctAllScore() throws IOException, SQLException{
		List<ScoreMgrDTO> smDTOList = new ArrayList<ScoreMgrDTO>();
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con= gc.getConn();
			StringBuilder selectAllScore = new StringBuilder();
			selectAllScore
			.append("	select 	r.stu_num stu, stu_name, sub_name, stu_score	")
			.append("	from report r, subject s , student st	")
			.append("	where r.sub_code =s.sub_code and r.stu_num=st.stu_num	");
			
			pstmt = con.prepareStatement(selectAllScore.toString());
			
			rs=pstmt.executeQuery();
			
			int stuNum = 0;
			String stuName = null;
			String subName = null;
			int score = 0;
			while (rs.next()) {
				stuNum = rs.getInt("stu");
				stuName = rs.getString("stu_name");
				subName = rs.getString("sub_name");
				score = rs.getInt("stu_score");
				ScoreMgrDTO smDTO = new ScoreMgrDTO(stuNum, score, stuName, subName);
				smDTOList.add(smDTO);
			} // end while
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		
		return smDTOList;		
	}//selectAllScore

}// class
