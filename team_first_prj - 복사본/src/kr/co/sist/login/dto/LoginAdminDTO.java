package kr.co.sist.login.dto;

import java.util.Date;

public class LoginAdminDTO {
	private String AdminId;
	private String AdminPass;
	private Date AdminInputDate;

	public LoginAdminDTO() {
		
	
	}

	public String getAdminId() {
		return AdminId;
	}

	public void setAdminId(String adminId) {
		AdminId = adminId;
	}

	public String getAdminPass() {
		return AdminPass;
	}

	public void setAdminPass(String adminPass) {
		AdminPass = adminPass;
	}

	public Date getAdminInputDate() {
		return AdminInputDate;
	}

	public void setAdminInputDate(Date adminInputDate) {
		AdminInputDate = adminInputDate;
	}

	@Override
	public String toString() {
		return "LoginAdminDTO [AdminId=" + AdminId + ", AdminPass=" + AdminPass + ", AdminInputDate=" + AdminInputDate
				+ "]";
	}

	

	
}