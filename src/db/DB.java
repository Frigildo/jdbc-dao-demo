package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	private static Connection conn= null;
	
	public static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}catch(IOException e) {
			throw new DBexception(e.getMessage());
		}
	}
	
	public static Connection getConnection() {
		try{
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
			return conn;
		}catch(SQLException e) {
			throw new DBexception(e.getMessage());
		}
	}
	
	public static void closeConnection() {
		try {
			conn.close();
		}catch(SQLException e) {
			throw new DBexception(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		 try {
			 if(st != null) {
				 st.close();
			 }
		 }catch(SQLException e) {
			 throw new DBexception(e.getMessage());
		 }
	}
}
