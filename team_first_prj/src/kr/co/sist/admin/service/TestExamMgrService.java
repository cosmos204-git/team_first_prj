package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.admin.dao.TestExamMgrDAO;
import kr.co.sist.admin.dto.examItemDTO;

public class TestExamMgrService {

	
	public List<examItemDTO> searchSubEI(int subCode,int courseCode) {
		List<examItemDTO> eiList = new ArrayList<examItemDTO>();
		TestExamMgrDAO temDAO = TestExamMgrDAO.getinstance();
		
		try {
			eiList = temDAO.selectAllEI(subCode,courseCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return eiList;
	}//searchSubEI
	
	public int modifyEI(examItemDTO eiDTO , int eiCode) {
		TestExamMgrDAO temDAO = TestExamMgrDAO.getinstance();
		int cnt = 0 ; 
		try {
			cnt = temDAO.updateEI(eiDTO, eiCode);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt ;
	}//modifyEI 
}
