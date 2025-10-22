package kr.co.sist.login.service;

import java.io.IOException;
import java.sql.SQLException;

import kr.co.sist.login.dao.LoginDAO;
import kr.co.sist.login.dto.LoginAdminDTO;
import kr.co.sist.login.dto.LoginProfDTO;
import kr.co.sist.login.dto.LoginStudentDTO;


public class LoginService {
	
	public LoginService() {
		
	}
	

	public LoginStudentDTO searchStuOneMember(int num) {
		
		LoginStudentDTO logStuDTO = null;
		
		try {
			LoginDAO logDAO = LoginDAO.getInstance();
			logStuDTO = logDAO.selectStuOneMember(num);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return logStuDTO;
	}
	
	public LoginProfDTO searchProfOneMember(int num) {
		
		LoginProfDTO logProfDTO = null;
		
		try {
			LoginDAO logDAO = LoginDAO.getInstance();
			logProfDTO = logDAO.selectProfOneMember(num);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return logProfDTO;
	}
	
	public LoginAdminDTO searchAdminOneMember(String id) {
		
		LoginAdminDTO logAdminDTO = null;
		
		try {
			LoginDAO logDAO = LoginDAO.getInstance();
			logAdminDTO = logDAO.selectAdminOneMember(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return logAdminDTO;
	}
				
	
	
}
