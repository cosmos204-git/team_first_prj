package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import kr.co.sist.admin.controller.AdminRegStuMgrDesignEvt;
import kr.co.sist.admin.dao.AdminRegStuMgrDAO;
import kr.co.sist.admin.dto.RegStuMgrDTO;
import kr.co.sist.admin.view.AdminProfMgrDesign;

public class AdminRegStuMgrService {
	public List<RegStuMgrDTO> searchAllstu(){
		List<RegStuMgrDTO> list = new ArrayList<RegStuMgrDTO>();
		AdminRegStuMgrDAO arsmDAO = AdminRegStuMgrDAO.getinstance();
		
		try {
			list = arsmDAO.selectAllStu();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}//searchAllstu
	
	public List<String> searchCombo(){
		List<String> list = new ArrayList<String>();
		AdminRegStuMgrDAO arsmDAO =AdminRegStuMgrDAO.getinstance();
		try {
			list = arsmDAO.selectCombo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}//searchCombo
	
	public List<RegStuMgrDTO> searchStu(JComboBox<String> jc , JTextField jtfStuNum){
		List<RegStuMgrDTO> rsmDTOList = new ArrayList<RegStuMgrDTO>();
		AdminRegStuMgrDAO arsmDAO = AdminRegStuMgrDAO.getinstance();
				
		try {
			rsmDTOList = arsmDAO.selectStu(jc, jtfStuNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return rsmDTOList;
	}//searchStu
}//class
