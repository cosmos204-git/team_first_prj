package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;

import kr.co.sist.admin.dao.StuModifyDAO;
import kr.co.sist.admin.dto.StudentDTO;

public class StuModifyService {

	
	public int modifyStudent(StudentDTO sDTO) {
		int flag = 0;
		
		StuModifyDAO smDAO = StuModifyDAO.getInstance();
		try {
			flag=smDAO.updateStudent(sDTO);//변경한 수의 개수 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	
		return flag;
	}//removeStudent
}//class
