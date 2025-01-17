package com.Dec.jdbc.day03.pstmt.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
	// DAO가 하는 역할이 많기 때문에 
	// Connection 객체를 생성하는 메소드를 담은 클래스를 따로 만듦
	// -> 단일 책임 원칙 : 하나의 클래스가 하나의 역할을 할 수 있도록 함.
	
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USERNAME = "KH";
	private static final String PASSWORD = "KH";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		return conn;
	}
}
