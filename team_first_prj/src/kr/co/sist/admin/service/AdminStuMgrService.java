package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.admin.dao.AdminStuMgrDAO;
import kr.co.sist.admin.dto.StudentDTO;

public class AdminStuMgrService {

	public List<StudentDTO> searchAllStudent() {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		AdminStuMgrDAO aDAO = AdminStuMgrDAO.getInstance();

		
		try {
			list=aDAO.selectAllStudent();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchFriend
}//class
