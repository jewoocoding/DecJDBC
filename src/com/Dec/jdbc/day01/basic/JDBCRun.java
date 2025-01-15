package com.Dec.jdbc.day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "student";
	private static final String PASSWORD = "student";

	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 연결
	 * 3. Statement 생성
	 * 4. SQL 전송
	 * 5. 결과받기
	 * 6. 자원해제
	 */
	public static void main(String[] args) {
		// DML JDBC
		String query = "INSERT INTO STUDENT_TBL VALUES('user02','pass02'"
				+ ", '이올라','F',23,'user02@ola.com',"
				+ "'01023456677','경기도 남양주시','수영,클라이밍',DEFAULT)";
		String query2 = "DELETE FROM STUDENT_TBL WHERE STUDENT_ID = 'user02'";
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3. Statement 생성
			Statement stmt = conn.createStatement();
			// 4. SQL 전송 및 5. 결과받기
			//int result2 = stmt.executeUpdate(query2);
			int result = stmt.executeUpdate(query);
			if(result > 0)
				System.out.println("데이터가 저장되었습니다.");
			else
				System.out.println("데이터가 저장되지 않았습니다.");
			
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void dqlJDBC() {
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","student","student");
			// 3. Statement 생성
			Statement stmt = conn.createStatement(); 
			// 4. SQL 전송 및 5. 결과받기
			String query = "SELECT * FROM STUDENT_TBL";
			ResultSet rset =  stmt.executeQuery(query);
			while(rset.next()) {
				System.out.println("ID : "+ rset.getString("STUDENT_ID"));
				System.out.println("NAME : "+ rset.getString("STUDENT_NAME"));
				System.out.println("AGE : "+ rset.getInt("AGE"));
				System.out.println("DATE : "+ rset.getDate("ENROLL_DATE"));
			}
			// 6. 자원해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
