package com.Dec.jdbc.day02.pstmt.member.model.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.Dec.jdbc.day02.pstmt.member.model.vo.Member;

public class MemberDAO {

	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USERNAME = "KH";
	private static final String PASSWORD = "KH";

	public int insertMember(Member member) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		String query = "INSERT INTO MEMBER_TBL(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, GENDER, AGE) "
				+ "VALUES('"+member.getMemberId()+"','"+member.getMemberPwd()
				+"','"+member.getMemberName()+"','"+member.getGender()+"',"+member.getAge()+")";
		
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public Member selectOneById(String memberId) {
		Connection conn = null;
		// #1. PreparedStatement 사용
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		// 실행 쿼리문
		String query = "SELECT * FROM MEMBER_TBL WHERE "
				+ "LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		// #2. 쿼리문 변경
		// - ?는 위치홀더라고 하며, 입력값이 들어가는 위치를 표시해줌.
		query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		ResultSet rset = null;
		Member member = null;
		try {
			conn = this.getConnection();
			// 쿼리문 실행
			// #3. 쿼리문 실행 부분 변경
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			
			//결과 받기
			// #4. 결과받기 부분 변경
			//rset = stmt.executeQuery(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				member = this.rsetToMember(rset);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				// 자원해제
				// 자원해제 변경
				
				rset.close();
				// stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}

	public List<Member> getMemberList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER_TBL";
		List<Member> mList = new ArrayList<Member>();
		
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member member = this.rsetToMember(rset);
				mList.add(member);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mList;
	}

	
	
	public int deleteOneById(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		String query = "DELETE FROM MEMBER_TBL "
				+ "WHERE LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = null;
		Statement stmt = null;
		String query = "UPDATE MEMBER_TBL SET "
				+ "MEMBER_PWD = '"+member.getMemberPwd()+"', "
				+ "EMAIL = '"+member.getEmail()+"', "
				+ "PHONE = '"+member.getPhone()+"', "
				+ "ADDRESS = '"+member.getAddress()+"', "
				+ "HOBBY = '"+member.getHobby()+"'"
				+ " WHERE MEMBER_ID = '"+member.getMemberId()+"'";
		int result = 0;
		
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		return conn;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		String memberId = rset.getString("MEMBER_ID");
		String memberPwd = rset.getString("MEMBER_PWD");
		String memberName = rset.getString("MEMBER_NAME");
		String gender = rset.getString("GENDER");
		int age = rset.getInt("AGE");
		String email = rset.getString("EMAIL");
		String phone = rset.getString("PHONE");
		String address = rset.getString("ADDRESS");
		String hobby = rset.getString("HOBBY");
		Date enrollDate =  rset.getDate("ENROLL_DATE");
		Member member = new Member(memberId, memberPwd, memberName
					, gender, age, email, phone, address, hobby, enrollDate);

		return member;
	}

}
