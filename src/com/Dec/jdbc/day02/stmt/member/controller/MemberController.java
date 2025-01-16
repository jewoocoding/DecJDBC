package com.Dec.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.Dec.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.Dec.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {

	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public int insertMember(Member member) {
		// 멤버 정보 입력받고 반환
		int result = mDao.insertMember(member);
		// return result로 몇 행을 수정/삭제/삽입 성공했는지 반환
		return result;
	}

	public List<Member> selectList() {
		// 멤버 전체 정보 받아와서 반환
		List<Member> mList = mDao.selectList();
		// View에서 mList를 사용할 수 있도록 리턴해줌
		return mList;
	}

	public Member selectOneById(String memberId) {
		// id로 멤버 검색
		Member member = mDao.selectMember(memberId);
		return member;
	}

	public int deleteMember(String memberId) {
		// 몇개의 행을 삭제 했는지 result로 받아와서 반환해줌
		int result = mDao.deleteMember(memberId);
		return result;
	}

}
