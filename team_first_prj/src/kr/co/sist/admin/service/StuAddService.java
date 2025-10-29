package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;

import kr.co.sist.admin.dao.ProfAddDAO;
import kr.co.sist.admin.dao.StuAddDAO;
import kr.co.sist.admin.dto.StudentDTO;

public class StuAddService {
	public int AddStudnet(StudentDTO sDTO) {
		int flag=0;
		StuAddDAO saDAO = StuAddDAO.getInstance();
		
		try {
			flag=saDAO.insertStudent(sDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//AddStudnet
	
	public int AddStuNum(){
		StuAddDAO saDAO= StuAddDAO.getInstance();
		int stuNum=0;
		try {
			stuNum=saDAO.nextStuNum();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return stuNum;
	}//AddProfNum

}//class
