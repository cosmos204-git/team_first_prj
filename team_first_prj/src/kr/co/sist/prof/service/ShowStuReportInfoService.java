package kr.co.sist.prof.service;

import java.util.List;

import kr.co.sist.prof.dao.ShowStuReportInfoDAO;
import kr.co.sist.prof.dto.ShowStuReportInfoDTO;

public class ShowStuReportInfoService {

	
	public ShowStuReportInfoService() {
	
	}
	
	public List<ShowStuReportInfoDTO> searchStuReport(int stuNum, int subCode){
		List<ShowStuReportInfoDTO> list = null;	
		try {
			ShowStuReportInfoDAO ssriDAO = ShowStuReportInfoDAO.getInstance();
			
			list = ssriDAO.selectStuReport(stuNum, subCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
}
