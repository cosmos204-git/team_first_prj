package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;

import kr.co.sist.admin.dao.stuAddDAO;
import kr.co.sist.admin.dto.StudentDTO;

public class stuAddService {
	public int AddStudnet(StudentDTO sDTO) {
		int flag=0;
		stuAddDAO saDAO = stuAddDAO.getInstance();
		
		try {
			flag=saDAO.insertStudent(sDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//AddStudnet
	
	public int NextStuNum() {
		int NewStuNum=-1;
		stuAddDAO saDAO = stuAddDAO.getInstance();
		
		try {
			NewStuNum=saDAO.selectNextStuNum();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return NewStuNum;
	}//NextStuNum
}//class
