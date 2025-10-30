package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.admin.dao.AdminProfMgrDAO;
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

	
	public int AddProfNum(){
		ProfAddDAO paDAO= ProfAddDAO.getInstance();
		int profNum=0;
		try {
			profNum=paDAO.nextProfNum();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return profNum;
	}//AddProfNum
	
	public List<ProfDTO> searchAllProfessor() {
		List<ProfDTO> list = new ArrayList<ProfDTO>();
		AdminProfMgrDAO apDAO = AdminProfMgrDAO.getInstance();

		try {
			list=apDAO.selectAllProfessor();
			list.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchAllProfessor
	
	
}//class
