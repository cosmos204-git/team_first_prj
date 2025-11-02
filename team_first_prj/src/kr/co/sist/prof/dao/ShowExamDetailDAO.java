package kr.co.sist.prof.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.prof.dto.ShowExamItemListDTO;

public class ShowExamDetailDAO {

	private static ShowExamDetailDAO sedDAO;
	
	private ShowExamDetailDAO(){
		
	}//ProfSutStateDAO
	
	public static ShowExamDetailDAO getInstance() {
		if(sedDAO==null) {
			sedDAO=new ShowExamDetailDAO();
		}//end if 
		return sedDAO;
	}//getInstance
	
	
	/**
	 * 해당 과정과 과목을 기준으로 시험문제를 불러옴
	 * @return
	 * @throws SQLException
	 */
	public Collection<ShowExamItemListDTO> getExamItemList(String pCourseName,String pSubName) throws SQLException,IOException {
		ShowExamItemListDTO eiDTO=null;
		List<ShowExamItemListDTO> list = new ArrayList<ShowExamItemListDTO>();
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String returnStr;
				
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder strGetExamItemList= new StringBuilder();

			
			// 과정과 과목기준으로 시험 문제, 보기1~4, 정답을 불러온다.  종료 일자가 현재일자보다 클 경우 조건 추가 
			strGetExamItemList
			.append(" select cs.course_code,cs.sub_code,exam_quest,exam_choice1,exam_choice2,exam_choice3,exam_choice4,exam_correct_tchoice " ) 
			.append(" from exam_item ei, exam e , course_subject cs ")
			.append(" where (cs.course_code=e.course_code and e.test_code =ei.test_code and cs.sub_code=e.sub_code) ")
			.append("    and cs.course_code=(select course_code from course where course_enddate > sysdate and course_name=? ) ")
			.append(" and cs.sub_code=(select sub_code from subject where sub_name=? )");                                      
			
			// 3. 쿼리문 생성객체 얻기
			pstmt = con.prepareStatement(strGetExamItemList.toString());
			
			//4. 바인드 변수에 값 설정
			pstmt.setString(1, pCourseName);
			pstmt.setString(2, pSubName);

			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				eiDTO = new ShowExamItemListDTO();
		        // Alias 이름을 넣으면 오류남...
				eiDTO.setExamQuest(rs.getString("exam_quest"));
				eiDTO.setExamChoice1(rs.getString("exam_choice1"));
				eiDTO.setExamChoice2(rs.getString("exam_choice2"));
				eiDTO.setExamChoice3(rs.getString("exam_choice3"));
				eiDTO.setExamChoice4(rs.getString("exam_choice4"));
				eiDTO.setExamCorrectTChoice(rs.getInt("exam_correct_tchoice"));
				
				list.add(eiDTO);
			}//end while
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		
		
		return list;
		
	}//selectCourseStudent
	
	
}//class

