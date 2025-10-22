package kr.co.sist.login.dao;

import kr.co.sist.login.dto.LoginStudentDTO;

public class CurrentStuData {
	
	private static CurrentStuData csd;
	
	private LoginStudentDTO logStuDTO;
	
	private CurrentStuData() {
		
	}
	
	public static CurrentStuData getInstance() {
		
		if(csd == null) {
			csd = new CurrentStuData();
		}
		
		return csd;
	}
	

	public LoginStudentDTO getLogStuDTO() {
		return logStuDTO;
	}

	public void setLogStuDTO(LoginStudentDTO logStuDTO) {
		this.logStuDTO = logStuDTO;
	}
	
	
	
}
