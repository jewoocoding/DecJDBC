package com.Dec.jdbc.day02.stmt.member.model.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.Dec.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDAO {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static final String USERNAME = "KH";
	private static final String PASSWORD = "KH";
	
	/*
	 * 여기서 JDBC 코딩 할거임
	 * 1. 드라이버 등록
	 * 2. DBMS 연결 생성
	 * 3. Statement 생성
	 * 4. SQL 전송
	 * 5. 결과받기
	 * 6. 자원해
	 */

	public int insertMember(Member member) {
		// 멤버 삽입
		int result = 0;
		String query = "INSERT INTO MEMBER_TBL(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, AGE) "
				+ "VALUES('"+member.getMemberId()+"','"+member.getMemberPwd()
				+"','"+member.getMemberName()+"',"+member.getAge()+")";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query); // 여기서 예외가 발생하면 close()코드는 실행되지 않음.
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 예외가 발생하든 안 하든 실행문을 동작시켜줌.
			try {
				// close()는 무조건 실행해야하기 때문에 finally안에 적어줌
				stmt.close();
				conn.close();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Member> selectList() {
		// 멤버 전체 정보 반환
		String query = "SELECT * FROM MEMBER_TBL ORDER BY 1";
		List<Member> mList = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			// rset에 테이블 형태로 데이터가 있으나 그대로 사용 못함
			// rset을 member에 담아주는 코드를 작성해주어야 함.
			// 그럴 때 사용하는 rset의 메소드가 next(), getString(),...이 있음.
			while(rset.next()) {
				Member member = this.rsetToMember(rset);
				// while문이 동작하면서 모든 레코드에 대한 정보를
				// mList에 담을 수 있도록 add 해줌
				mList.add(member);
			}
			
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 리턴을 null로 하면 NullPointerException 발생
		// mList로 리턴해주어야함.
		return mList;
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		// DBMS 드라이버 등록과 연결생성을 메소드로 만들어 반복 줄임
		Class.forName(DRIVER_NAME); // 예외 던지기
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		return conn;
	}

	public Member selectMember(String memberId) {
		// id를 받아와 SELECT 후 반환
		String query = "SELECT * FROM MEMBER_TBL WHERE "
				+ "LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		Member member = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				// rset은 member로 변환되어야 함(rsetToMember)
				member = this.rsetToMember(rset);
			}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		// ResultSet을 member로 변환 후 그 멤버를 반환
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

	public int deleteMember(String memberId) {
		String query = "DELETE FROM MEMBER_TBL "
				+ "WHERE LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
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
		// 성공여부를 알 수 있도록 result를 리턴해줌
		return result;
	}
}
