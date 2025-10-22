package kr.co.sist.login.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnection {
	
	private static GetConnection getCon;
	
	private GetConnection()
	{
	
	}//GetConnection
	
	public static GetConnection getInstance() {
		if(getCon == null) {
			getCon = new GetConnection();
		}//end if
		
		return getCon;
	}//getInstance
	
	
	public Connection getConn() throws SQLException, IOException {
		//1. 드라이버 로딩
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}//end catch
			//2. 로딩된 드라이버를 사용하여 커넥션 얻기 => properties 도입
			
			File file = new File("C:/dev/workspace/team_first_prj/src/properties/datebase.properties");
			if(!file.exists()) {
				throw new IOException("properties가 지정된 위치에 존재하지 않습니다.");
			}
			
			Properties prop = new Properties();
			prop.load(new FileInputStream(file));
			
			String url = prop.getProperty("url");
			String id = prop.getProperty("id");
			String pass = prop.getProperty("pass");
			
			PreparedStatement pstmt = null;
				//사용이 종료되면 자용사용객체를 끊어준다.
			Connection con = DriverManager.getConnection(url,id,pass);

			return con;
	}//getConn
	
	public void dbClose(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		
		try {
			if(rs!=null) { rs.close();}
			if(pstmt!=null) { pstmt.close();}
		} finally{
			if(con!=null) { con.close();}
		}//end finally
		
	}//dbClose			
				
}

