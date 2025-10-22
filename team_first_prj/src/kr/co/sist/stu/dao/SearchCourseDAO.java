package kr.co.sist.stu.dao;

public class SearchCourseDAO {

	private static SearchCourseDAO scDAO;
	
	private SearchCourseDAO() {
		
	}//SearchCourseDAO
	
	
	public static SearchCourseDAO getInstance() {
		if(scDAO == null ) {
		   scDAO = new SearchCourseDAO();
		}//end if
		return scDAO;
	}//getInstance
	
}//SearchCourseDAO
