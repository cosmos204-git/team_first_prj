package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;

import kr.co.sist.admin.dao.ProfModifyDAO;
import kr.co.sist.admin.dto.ProfDTO;

public class ProfModifyService {

	
	public int modifyProfessor(ProfDTO pDTO) {
		int flag = 0;
		
		ProfModifyDAO pDAO = ProfModifyDAO.getInstance();
		try {
			flag=pDAO.updateProfessor(pDTO);//변경한 수의 개수 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	
		return flag;
	}//removeStudent
}//class
