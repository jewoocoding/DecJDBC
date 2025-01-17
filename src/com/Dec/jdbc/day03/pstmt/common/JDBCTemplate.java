package com.Dec.jdbc.day03.pstmt.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	// DAO가 하는 역할이 많기 때문에 
	// Connection 객체를 생성하는 메소드를 담은 클래스를 따로 만듦
	// -> 단일 책임 원칙 : 하나의 클래스가 하나의 역할을 할 수 있도록 함.
	
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//	private static final String USERNAME = "KH";
//	private static final String PASSWORD = "KH";
	
	
	// Connection에 싱글톤을 적용하지 않은 이유
	// Connection에 싱글톤을 적용하려는 이유는 Connection Pool(연결을 생성해서 Pool에 넣고
	// 재사용할 수 있는 기술)을 사용하려고 하는 것이지만 싱글톤을 적용하고 Connection Pool이
	// 동작하는 코드는 없기 때문에 적용하지 않음.
	// Connection을 만드는 메소드를 가지고 있는 JDBCTemplate에 싱글톤을 적용하여 사용함.

	private static JDBCTemplate instance; // 싱글톤 패턴으로 객체 하나만 생성할 수 있게 static으로 선언
	
	private static final String FILE_NAME = "resources/dev.properties";
	private Properties prop;
	
	// 다른 객체를 생성하지 못 하게 생성자를 private으로 함
	private JDBCTemplate() {
		try {
			Reader reader = new FileReader(FILE_NAME); // 스트림 열어서 파일 읽기
			prop = new Properties();			// 읽은 파일 사용하기
			prop.load(reader);							// 사용 준비 완료
			String driverName = prop.getProperty("driverName");
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} 
	
	// 객체가 생성되지 않았다면 만들고 객체가 이미 있다면 그 객체를 반환해줌.
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Reader reader = new FileReader(FILE_NAME);
			prop = new Properties();
			prop.load(reader);
			String url = prop.getProperty("url");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");		
			conn = DriverManager.getConnection(url,userName,password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
