package kr.co.sist.login.dao;

import kr.co.sist.login.dto.LoginProfDTO;

public class CurrentProfData {
	
	private static CurrentProfData cpd;
	
	private LoginProfDTO logProfDTO;
	
	private CurrentProfData() {
		
	}
	
	public static CurrentProfData getInstance() {
		
		if(cpd == null) {
			cpd = new CurrentProfData();
		}
		
		return cpd;
	}
	

	public LoginProfDTO getLogProfDTO() {
		return logProfDTO;
	}

	public void setLogProfDTO(LoginProfDTO logProfDTO) {
		this.logProfDTO = logProfDTO;
	}
	
	
	
}
