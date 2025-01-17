package com.Dec.jdbc.day03.pstmt.member.controller;

import java.util.List;

import com.Dec.jdbc.day03.pstmt.member.model.service.MemberService;
import com.Dec.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberController {
	
	// private MemberDAO mDao;
	private MemberService mService;
	
	public MemberController() {
		// mDao = new MemberDAO();
		mService = new MemberService();
	}
	
	public int insertMember(Member member) {
		//int result = mDao.insertMember(member);
		int result = mService.insertMember(member);
		return result;
	}
	
	public Member selectOneById(String memberId) {
		Member member = mService.selectOneById(memberId);
		
		return member;
	}

	public List<Member> getMemberList() {
		List<Member> mList = mService.getMemberList();
		return mList;
	}

	public int deleteOneById(String memberId) {
		int result = mService.deleteOneById(memberId);
		return result;
	}

	public int updateMember(Member member) {
		int result = mService.updateMember(member);
		return result;
	}
}
