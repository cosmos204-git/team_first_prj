package kr.co.sist.admin.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class test {
	
@Test
void selectCourse() {
	AdminSubjectMgrDAO asmDAO = AdminSubjectMgrDAO.getinstance();
	
	try {
		assertNotNull(asmDAO.selectCourse());
	} catch (IOException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
