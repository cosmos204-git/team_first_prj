package kr.co.sist.stu.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.stu.dto.StuSubjectDialogDTO;

public class StuSubjectDialogDAO {

	private static StuSubjectDialogDAO ssdDAO;

	private StuSubjectDialogDAO() {}

	public static StuSubjectDialogDAO getInstance() {
		if (ssdDAO == null) {
			ssdDAO = new StuSubjectDialogDAO();
		}
		return ssdDAO;
	}// getInstance

	/**
	 * 상세 과목 조회
	 */
	public List<StuSubjectDialogDTO> selectAllSubject(int courseCode) throws SQLException, IOException {

		List<StuSubjectDialogDTO> list = new ArrayList<>();
		
		GetConnection gc=GetConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = gc.getConn();
			
			StringBuilder selectAllSubject = new StringBuilder();
			selectAllSubject
            .append("select s.sub_code, s.sub_name, cs.course_code ")
            .append("from subject s ")
            .append("inner join course_subject cs on s.sub_code = cs.sub_code ")
            .append("where cs.course_code = ? ")
            .append("order by s.sub_code asc");

			pstmt = con.prepareStatement(selectAllSubject.toString());
			pstmt.setInt(1, courseCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				StuSubjectDialogDTO ssdDTO = new StuSubjectDialogDTO();
				ssdDTO.setSubCode(rs.getInt("sub_code"));
				ssdDTO.setSubName(rs.getString("sub_name"));
				ssdDTO.setCourseCode(rs.getInt("course_code"));
				list.add(ssdDTO);
			}

		} finally {
			gc.dbClose(con, pstmt, rs);
		}

		return list;
	}// selectAllSubject

}// class