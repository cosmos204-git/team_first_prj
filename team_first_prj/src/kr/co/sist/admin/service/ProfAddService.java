package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;

import kr.co.sist.admin.dao.ProfAddDAO;
import kr.co.sist.admin.dto.ProfDTO;

public class ProfAddService {
	public int AddProfessor(ProfDTO pDTO) {
		int flag=0;
		ProfAddDAO paDAO = ProfAddDAO.getInstance();
		
		try {
			flag=paDAO.insertProfessor(pDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//AddStudnet
	
}//class
