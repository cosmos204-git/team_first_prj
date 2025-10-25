package kr.co.sist.prof.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.prof.dto.ShowStuReportInfoDTO;

public class ShowStuReportInfoDAO {

	private static ShowStuReportInfoDAO ssri;
	String userHome = System.getProperty("user.home");
	
	private ShowStuReportInfoDAO() {
		
	}
	
	public static ShowStuReportInfoDAO getInstance() {
		
		if(ssri==null) {
			ssri = new ShowStuReportInfoDAO();
		}
		
		return ssri;
	}
	
	

	public List<ShowStuReportInfoDTO> selectStuReport(int stuNum, int subCode) throws SQLException, IOException{
		
		
		
		List<ShowStuReportInfoDTO> list = new ArrayList<ShowStuReportInfoDTO>();
		
		ShowStuReportInfoDTO ssriDTO = null;
		GetConnection gc = GetConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = gc.getConn();
			//3.쿼리문 생성 객체 얻기
			StringBuilder selectReport = new StringBuilder();
			selectReport
			.append("		select  ei.exam_code, ei.test_code, ei.exam_quest , ei.exam_choice1, ei.exam_choice2, ei.exam_choice3, ei.exam_choice4, ei.exam_correct_tchoice, er.stu_ans		 ") 
			.append("		FROM exam_result er								")
			.append("		JOIN student st ON er.stu_num = st.stu_num						")
			.append("		JOIN exam_item ei ON er.exam_code = ei.exam_code						")
			.append("		JOIN exam e ON ei.test_code = e.test_code						")
			.append("		JOIN course c ON e.course_code = c.course_code						")
			.append("		JOIN subject s ON e.sub_code = s.sub_code							")
			.append("		WHERE st.stu_num = ? AND 						")
			.append("		(c.course_code = c.course_code) AND (s.sub_code =  ?) AND (ei.exam_code = ei.exam_code) AND (ei.test_code = ei.test_code) 		");

			pstmt = con.prepareStatement(selectReport.toString());
			
			//4.바인드 변수 값 설정
			
			
			pstmt.setInt(1, stuNum);
			pstmt.setInt(2, subCode);
		
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) { //쿼리로 인한 조회 결과가 존재.
				ssriDTO=new ShowStuReportInfoDTO();
				
				ssriDTO.setExamCode(rs.getInt("exam_code"));
				ssriDTO.setTestCode(rs.getInt("test_code"));
				ssriDTO.setExamQuest(rs.getString("exam_quest"));
				ssriDTO.setExamChoice1(rs.getString("exam_choice1"));
				ssriDTO.setExamChoice2(rs.getString("exam_choice2"));
				ssriDTO.setExamChoice3(rs.getString("exam_choice3"));
				ssriDTO.setExamChoice4(rs.getString("exam_choice4"));
				ssriDTO.setExamCorrectChoice(rs.getInt("exam_correct_tchoice"));
				ssriDTO.setStuAns(rs.getInt("stu_ans"));
				
				

				//이미지는 스트림을 별도로 연결하여 읽어 들인다.
				Properties prop = new Properties();
				prop.load(new FileInputStream(userHome+"/git/team_first_prj/team_first_prj/src/properties/datebase.properties"));
				
				File dir = new File(prop.getProperty("savePath"));
				
				//디렉토리가 없다면 디렉토리를 생성
				if(!dir.exists()){
					dir.mkdirs();
				}//end if
				
				list.add(ssriDTO);
				
				
			}//end while
		}finally {
			//5. 연결 끊기.
			gc.dbClose(con, pstmt, rs);
			
		}
		
		
		
		return list;
		
	}
	
}
