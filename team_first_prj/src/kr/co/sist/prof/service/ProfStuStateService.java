package kr.co.sist.prof.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.prof.dao.ProfStuStateDAO;
import kr.co.sist.prof.dto.ProfStuStateDTO;

public class ProfStuStateService {

	public List<ProfStuStateDTO> selectCourseStudent(int p_profNum) {
		List<ProfStuStateDTO> list = new ArrayList<ProfStuStateDTO>();
		ProfStuStateDAO aDAO = ProfStuStateDAO.getInstance();

		System.out.println("Service 진입"+aDAO);
		
		try {
			list=aDAO.selectCourseStudent();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//ProfStuStateService
	
}//class