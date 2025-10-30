package kr.co.sist.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.sist.admin.dao.AdminCourseMgrDesignDAO;
import kr.co.sist.admin.dto.CourseMgrDTO;

public class AdminCourseMgrDesignService {

	public List<CourseMgrDTO> searchAllCourse( ){
		List <CourseMgrDTO> list = new ArrayList<CourseMgrDTO>();
		
		AdminCourseMgrDesignDAO acmdDAO= AdminCourseMgrDesignDAO.getInstance();
		
		try {
			list=acmdDAO.selectAllCourse();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchAllCourse
	
	

//	
	public Map<String,String> searchCombo(){
		 Map<String,String> list = new HashMap<String,String>();
		AdminCourseMgrDesignDAO acmdDAO =AdminCourseMgrDesignDAO.getInstance();
		try {
			list = acmdDAO.selectCombo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}//searchCombo
	
//	public List<String> searchCombo(){
//		 List<String> list = new ArrayList<String>();
//		AdminCourseMgrDesignDAO acmdDAO =AdminCourseMgrDesignDAO.getInstance();
//		try {
//			list = acmdDAO.selectCombo();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return list;
//	}//searchCombo
	
	public List<CourseMgrDTO> searchCourse(String courseName) {
		
		CourseMgrDTO cmDTO = null;
		List<CourseMgrDTO> list = new ArrayList<CourseMgrDTO>();
		AdminCourseMgrDesignDAO acmdDAO = AdminCourseMgrDesignDAO.getInstance();
		try {
			
			list=acmdDAO.selectCourse(courseName);
			
		}catch (IOException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
		
	}//searchProfessor
	
	public int modifyCourse(CourseMgrDTO cDTO) {
		int flag=0;
		AdminCourseMgrDesignDAO acmdDAO= AdminCourseMgrDesignDAO.getInstance();
		
		try {
			flag=acmdDAO.updateCourse(cDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//modifyCourse
	
	
	public int addCourse(CourseMgrDTO cDTO) {
		int flag =0;
		
		AdminCourseMgrDesignDAO acmdDao = AdminCourseMgrDesignDAO.getInstance();
		
		try {
			flag=acmdDao.insertCourse(cDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//CourseMgrDTO cDTO

	
	public int removeCourse(int courseCode ) {
		int flag =0;
		
		AdminCourseMgrDesignDAO acmd = AdminCourseMgrDesignDAO.getInstance();
		
		try {
			flag=acmd.deleteCourse(courseCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		
		return flag;
		
		
	}//removeCourse
	
	public int AddCourseNum(){
		int courseNum=0;
		AdminCourseMgrDesignDAO acmdDAO= AdminCourseMgrDesignDAO.getInstance();
		try {
			courseNum=acmdDAO.nextCourseNum();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return courseNum;
	}//AddCourseNum
	
}//class