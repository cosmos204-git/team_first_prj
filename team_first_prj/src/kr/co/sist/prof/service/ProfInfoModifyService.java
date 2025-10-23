package kr.co.sist.prof.service;

import java.io.IOException;
import java.sql.SQLException;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.prof.dao.ProfInfoModifyDAO;

public class ProfInfoModifyService {

	public ProfInfoModifyService() {
		
	}
	
	public int modifyProfInfo(CurrentProfData cpd) {
		
		int flag=0;
		
		try {
			ProfInfoModifyDAO pimDAO = ProfInfoModifyDAO.getInstance();
			flag=pimDAO.updateProfInfo(cpd);
			//DB테이블에 회원번호가 PK가 아니라면 flag가 n개가 반환될 수 있고
			//그 경우에는 1로 재설정한다.
		}catch(IOException ie){
			ie.printStackTrace();
		} catch (SQLException e) {
			flag=2;
			e.printStackTrace();
		}//catch
		
		return flag;
	}//modifyMember
	
	
	public int modifyProfImg(CurrentProfData cpd) {
		
		int flag=0;
		
		try {
			ProfInfoModifyDAO pimDAO = ProfInfoModifyDAO.getInstance();
			flag=pimDAO.updateProfImg(cpd);
			//DB테이블에 회원번호가 PK가 아니라면 flag가 n개가 반환될 수 있고
			//그 경우에는 1로 재설정한다.
		}catch(IOException ie){
			ie.printStackTrace();
		} catch (SQLException e) {
			flag=2;
			e.printStackTrace();
		}//catch
				
		return flag;
	}//modifyMember
	
}
