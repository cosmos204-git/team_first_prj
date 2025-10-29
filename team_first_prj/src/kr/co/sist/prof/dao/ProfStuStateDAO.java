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
import kr.co.sist.prof.dto.ProfStuStateDTO;

public class ProfStuStateDAO {

	private static ProfStuStateDAO pssDAO;
	
	private ProfStuStateDAO(){
		
	}//ProfSutStateDAO
	
	public static ProfStuStateDAO getInstance() {
		if(pssDAO==null) {
			pssDAO=new ProfStuStateDAO();
		}//end if 
		return pssDAO;
	}//getInstance
	
	
	/**
	 * 헤딩 교수의 과정과 해당학생의 정보 조회
	 * @return
	 * @throws SQLException
	 */
	public List<ProfStuStateDTO> selectCourseStudent() throws SQLException,IOException{
		ProfStuStateDTO sDTO=null;
		
		List<ProfStuStateDTO> list = new ArrayList<ProfStuStateDTO>();
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int p_profNum;
				
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder selectStuState= new StringBuilder();
			selectStuState

			.append(" SELECT      p.prof_num, p.prof_name,  c.course_name," ) 
			.append(" s.stu_name, s.stu_num, s.stu_tel " ) 
			.append(" FROM course c left JOIN professor p ON p.prof_num = c.prof_num ")
			.append("               left JOIN student_course sc ON c.course_code = sc.course_code ")
			.append("               left JOIN student s ON sc.stu_num = s.stu_num ")
			.append(" where c.course_del_flag='N' and s.stu_num is not null and p.prof_num=? ")                                      
			.append(" ORDER BY  p.prof_name, c.course_name, s.stu_num ");			
			
			
			// 3. 쿼리문 생성객체 얻기
			pstmt = con.prepareStatement(selectStuState.toString());
			
			//4. 바인드 변수에 값 설정
			p_profNum=CurrentProfData.getInstance().getLogProfDTO().getProfNum();
			
//			System.out.println(selectStuState + "  교번은 "+ p_profNum +" 입니다.");
			
			pstmt.setInt(1, p_profNum);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				sDTO = new ProfStuStateDTO();
		        // Alias 이름을 넣으면 오류남...
				sDTO.setprofName(rs.getString("prof_num"));
				sDTO.setCourseName(rs.getString("course_name"));
				sDTO.setStuNum(rs.getInt("stu_num"));
				sDTO.setStuName(rs.getString("stu_name"));
				sDTO.setStuTel(rs.getString("stu_tel"));
				
				list.add(sDTO);
			}//end if 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		return list;
		
	}//selectCourseStudent

	// 선정된 과정으로 다시 검색한다.
	public List<ProfStuStateDTO> selectStudentsByCourse(String courseName) throws SQLException,IOException{
		ProfStuStateDTO sDTO=null;
		
		List<ProfStuStateDTO> list = new ArrayList<ProfStuStateDTO>();
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
				
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder selectCourseState= new StringBuilder();
			selectCourseState

			.append(" SELECT      p.prof_num, p.prof_name,  c.course_name," ) 
			.append(" s.stu_name, s.stu_num, s.stu_tel " ) 
			.append(" FROM course c left JOIN professor p ON p.prof_num = c.prof_num ")
			.append("               left JOIN student_course sc ON c.course_code = sc.course_code ")
			.append("               left JOIN student s ON sc.stu_num = s.stu_num ")
			.append(" where c.course_del_flag='N' and s.stu_num is not null and c.course_name=? ")                                      
			.append(" ORDER BY  p.prof_name, c.course_name, s.stu_num ");		

			
			// 3. 쿼리문 생성객체 얻기
			pstmt = con.prepareStatement(selectCourseState.toString());
			
			//4. 바인드 변수 값은 파라미터로 받은 것
			pstmt.setString(1, courseName);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				sDTO = new ProfStuStateDTO();
		        // Alias 이름을 넣으면 오류남...
				sDTO.setprofName(rs.getString("prof_num"));
				sDTO.setCourseName(rs.getString("course_name"));
				sDTO.setStuNum(rs.getInt("stu_num"));
				sDTO.setStuName(rs.getString("stu_name"));
				sDTO.setStuTel(rs.getString("stu_tel"));
				
				list.add(sDTO);
			
			}//end if 
		}finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		return list;
		
	}//searchCourse

	
	
}//class

