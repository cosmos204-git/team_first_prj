package kr.co.sist.prof.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.login.dao.GetConnection;
import kr.co.sist.prof.dto.ExamListDTO;

public class ProfExamMgrDAO {

	private static ProfExamMgrDAO pemDAO;
	
	private ProfExamMgrDAO(){
		
	}//ProfSutStateDAO
	
	public static ProfExamMgrDAO getInstance() {
		if(pemDAO==null) {
			pemDAO=new ProfExamMgrDAO();
		}//end if 
		return pemDAO;
	}//getInstance
	
	
	/**
	 * 헤딩 교수의 과정과 해당학생의 정보 조회
	 * @return
	 * @throws SQLException
	 */
	public List<ExamListDTO> selectExamList() throws SQLException,IOException{
		ExamListDTO sDTO=null;
		
		List<ExamListDTO> list = new ArrayList<ExamListDTO>();
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int p_profNum;
				
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder strSelectExamList= new StringBuilder();
			
			// 교수 교번을 기준으로 과정, 과목, 시험오픈, 시작시간, 종료시간을 과정명 + 과목 순으로 리스트업 
			strSelectExamList
			.append(" SELECT c.course_code, c.course_name, s.sub_code, s.sub_name, e.exam_open, e.exam_start, e.exam_end " ) 
			.append(" FROM  course c JOIN  course_subject cs ON c.course_code = cs.course_code ")
			.append("                JOIN  subject s ON cs.sub_code = s.sub_code ")
			.append("                JOIN  exam e ON e.course_code = c.course_code AND e.sub_code = s.sub_code")
			.append(" Where c.prof_num=? ")                                      
			.append(" ORDER BY c.course_name, s.sub_name ");			
			
			// 3. 쿼리문 생성객체 얻기
			pstmt = con.prepareStatement(strSelectExamList.toString());
			
			//4. 바인드 변수에 값 설정
			p_profNum=CurrentProfData.getInstance().getLogProfDTO().getProfNum();
			
//			System.out.println(strSelectExamList + "  교번은 "+ p_profNum +" 입니다.");
			
			pstmt.setInt(1, p_profNum);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				sDTO = new ExamListDTO();
		        // Alias 이름을 넣으면 오류남...
				sDTO.setCourseName(rs.getString("course_name"));
				sDTO.setSubName(rs.getString("sub_name"));
				sDTO.setExamOpen(rs.getString("exam_open"));
				sDTO.setExamStart(rs.getString("exam_start"));
				sDTO.setExamEnd(rs.getString("exam_end"));
				
				list.add(sDTO);
			}//end if 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		return list;
		
	}//selectCourseStudent

	
	
	// 선정된 내용으로 업데이트 한다.
	public int updateExam(ExamListDTO uDTO) throws SQLException,IOException{
		int rowCnt=0;

		Connection con = null;
		PreparedStatement pstmt=null;
				
		GetConnection gc = GetConnection.getInstance();

		try {
			con=gc.getConn();
			StringBuilder updateExamState= new StringBuilder();
			updateExamState
			.append(" update (select course_name,sub_name,exam_open,exam_start,exam_end " ) 
			.append("         from exam e,COURSE c,subject s ")
			.append("         where e.course_code=c.course_code and s.sub_code=e.sub_code) ")
			.append(" set exam_open=? ,exam_start=?, exam_end=? ")
			.append(" where course_name=? and sub_name=? ");

			
			// 3. 쿼리문 생성객체 얻기
			pstmt = con.prepareStatement(updateExamState.toString());
			
			// 4. 바인드변수에 값 설정
			pstmt.setString(1, uDTO.getExamOpen().toString());
			pstmt.setString(2, uDTO.getExamStart().toString());
			pstmt.setString(3, uDTO.getExamEnd().toString());
			pstmt.setString(4, uDTO.getCourseName().toString());
			pstmt.setString(5, uDTO.getSubName().toString());
			
			rowCnt=pstmt.executeUpdate();

		} finally {
			// 5. 연결 끊기
			gc.dbClose(con, pstmt, null);
		} // end finally

		return rowCnt;
	}// updateMember
	
}//class

