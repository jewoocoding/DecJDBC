package com.Dec.jdbc.day03.pstmt.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.Dec.jdbc.day03.pstmt.common.JDBCTemplate;
import com.Dec.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.Dec.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberService {
	
	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
		jdbcTemplate = new JDBCTemplate();
		mDao = new MemberDAO();
	}
	
	public int insertMember(Member member) {
		
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.insertMember(conn, member);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Member selectOneById(String memberId) {
		Connection conn = null;
		Member member = null;
		try {
			conn = jdbcTemplate.getConnection();
			member = mDao.selectOneById(conn, memberId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}

	public List<Member> getMemberList() {
		Connection conn = null;
		List<Member> mList = null;
		try {
			conn = jdbcTemplate.getConnection();
			mList = mDao.getMemberList(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mList;
	}

	public int deleteOneById(String memberId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.deleteOneById(conn, memberId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int updateMember(Member member) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.updateMember(conn, member);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
