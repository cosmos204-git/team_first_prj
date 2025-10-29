package kr.co.sist.admin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.admin.dto.examItemDTO;
import kr.co.sist.login.dao.GetConnection;

public class TestExamMgrDAO {
	private static TestExamMgrDAO temDAO;
	
	private TestExamMgrDAO() {
		
	}//TestexamMgrDAO
	
	public static TestExamMgrDAO getinstance() {
		if(temDAO == null) {
			temDAO = new TestExamMgrDAO();
		}
		
		return temDAO;
	}
	
	public List<examItemDTO> selectAllEI(int subCode,int courseCode) throws SQLException, IOException {
		List<examItemDTO> examItemList = new ArrayList<examItemDTO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
//			int testCode= 0;
//			switch(subCode) {
//			case 101: testCode = 1;	break;
//			case 102: testCode = 2; break;
//			case 103: testCode = 3; break;
//			case 104: testCode = 4; break;
//			}
			con = gc.getConn();
			
			String selectAllEI = "select * from exam_item where test_code = (select test_code from exam where sub_code = ? and course_code = ?)";
//			String selectAllEI = "select * from exam_item where test_num = ?";
			
			pstmt = con.prepareStatement(selectAllEI);
			
			pstmt.setInt(1, subCode);
			pstmt.setInt(2, courseCode);
			rs = pstmt.executeQuery();
			
			examItemDTO eiDTO = null;
			int examCode =0;
			int examCorrectTChoice =0;
			String examInputdate = "";
			String examChoice1 = "";
			String examChoice2 = "";
			String examChoice3 = "";
			String examChoice4 = "";
			String examQuest = "";
			int testCode=0;
			
			while(rs.next()) {
			 	examCode =  rs.getInt("exam_code");
				examCorrectTChoice= rs.getInt("exam_correct_tchoice");
				examInputdate = rs.getString("exam_inputdate");
				examChoice1 = rs.getString("exam_choice1");
				examChoice2= rs.getString("exam_choice2");
				examChoice3 = rs.getString("exam_choice3");
				examChoice4 =  rs.getString("exam_choice4");
				examQuest = rs.getString("exam_quest");
				testCode = rs.getInt("test_code");
				
				eiDTO = new examItemDTO(examCode, testCode, examCorrectTChoice, examChoice1, examChoice2, examChoice3, examChoice4, examInputdate, examQuest);
				
				examItemList.add(eiDTO);
			}//end while
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally	
		
		return examItemList;
	}//selectAllEI
	
	public int updateEI(examItemDTO eiDTO, int eiCode) throws IOException, SQLException{
		int cnt=0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con = gc.getConn();
			
			StringBuilder updateEI = new StringBuilder();
			updateEI
			.append("	update  exam_item	")
			.append("	set exam_Quest = ?,exam_choice1 = ?,exam_choice2 = ?,exam_choice3 =? ,exam_choice4 =? ,exam_correct_tchoice = ?	")
			.append("	where exam_code = ?	");
			
			pstmt = con.prepareStatement(updateEI.toString());
			
			pstmt.setString(1, eiDTO.getExamQuest());
			pstmt.setString(2, eiDTO.getExamChoice1());
			pstmt.setString(3, eiDTO.getExamChoice2());
			pstmt.setString(4, eiDTO.getExamChoice3());
			pstmt.setString(5, eiDTO.getExamChoice4());
			pstmt.setInt(6, eiDTO.getExamCorrectTChoice());
			pstmt.setInt(7, eiCode);
			
			cnt = pstmt.executeUpdate();
			
			
			
		}finally {
			gc.dbClose(con, pstmt, rs);
		}
		
		return cnt;
	}
	
}//class
