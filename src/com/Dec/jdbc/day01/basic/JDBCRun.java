package com.Dec.jdbc.day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		query = "INSERT INTO STUDENT_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3. Statement 생성
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "user02");
			pstmt.setString(2, "pass02");
			pstmt.setString(3, "이올라");
			pstmt.setString(4, "F");
			pstmt.setInt(5, 23);
			pstmt.setString(6, "user02@ola.com");
			pstmt.setString(7, "01023455667");
			pstmt.setString(8, "경기도 남양주시");
			pstmt.setString(9, "수영,클라이밍");
			// 4. SQL 전송 및 5. 결과받기
			//int result2 = stmt.executeUpdate(query2);
			int result = stmt.executeUpdate(query);
			result = pstmt.executeUpdate();
			if(result > 0)
				System.out.println("데이터가 저장되었습니다.");
			else
				System.out.println("데이터가 저장되지 않았습니다.");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void dqlJDBC() {
		String query = "SELECT * FROM STUDENT_TBL";
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","student","student");
			// 3. Statement 생성, PreparedStatement 생성
			stmt = conn.createStatement(); 
			pstmt = conn.prepareStatement(query);
			// pstmt.setString(1, memberId); -> 위치홀더 있으면 생략불가능
			
			// 4. SQL 전송 및 5. 결과받기
			rset =  stmt.executeQuery(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				System.out.println("ID : "+ rset.getString("STUDENT_ID"));
				System.out.println("NAME : "+ rset.getString("STUDENT_NAME"));
				System.out.println("AGE : "+ rset.getInt("AGE"));
				System.out.println("DATE : "+ rset.getDate("ENROLL_DATE"));
			}
			// 6. 자원해제
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
