package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.admin.dao.AdminProfMgrDAO;
import kr.co.sist.admin.dao.AdminStuMgrDAO;
import kr.co.sist.admin.dto.ProfDTO;
import kr.co.sist.admin.dto.StudentDTO;

public class AdminProfMgrService {

	public List<ProfDTO> searchAllProfessor() {
		List<ProfDTO> list = new ArrayList<ProfDTO>();
		AdminProfMgrDAO apDAO = AdminProfMgrDAO.getInstance();

		
		try {
			list=apDAO.selectAllProfessor();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchAllProfessor
	
	
	public ProfDTO searchProfessor(int ProfNum) {
		ProfDTO pDTO = null;
		
		AdminProfMgrDAO apDAO = AdminProfMgrDAO.getInstance();
		try {
			
			pDTO=apDAO.selectProfessor(ProfNum);
			
		}catch (IOException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return pDTO;
		
	}//searchProfessor
	
	public int removeProfessor(int stunum) {
		int flag = 0;
		
		AdminProfMgrDAO apDAO = AdminProfMgrDAO.getInstance();
		try {
			flag=apDAO.deleteProfessor(stunum);//변경한 수의 개수 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	
		return flag;
	}//removeStudent 
}//class
