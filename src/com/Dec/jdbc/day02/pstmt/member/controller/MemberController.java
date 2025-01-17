package com.Dec.jdbc.day02.pstmt.member.controller;

import java.util.*;

import com.Dec.jdbc.day02.pstmt.member.model.dao.MemberDAO;
import com.Dec.jdbc.day02.pstmt.member.model.vo.Member;

public class MemberController {

	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public int insertMember(Member member) {
		int result = mDao.insertMember(member);
		return result;
	}
	
	public Member selectOneById(String memberId) {
		Member member = mDao.selectOneById(memberId);
		return member;
	}

	public List<Member> getMemberList() {
		List<Member> mList = mDao.getMemberList();
		return mList;
	}

	public int deleteOneById(String memberId) {
		int result = mDao.deleteOneById(memberId);
		return result;
	}

	public int updateMember(Member member) {
		int result = mDao.updateMember(member);
		return result;
	}

}
