package com.Dec.jdbc.day01.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.Dec.jdbc.day01.stmt.member.controller.MemberController;
import com.Dec.jdbc.day01.stmt.member.model.vo.Member;

public class MemberView {
	// 선언만 한 상태
	private MemberController manage;
	private Scanner sc;
	
	public MemberView() {
		manage = new MemberController();
		sc = new Scanner(System.in);
	}
	
	private static final String BYE_MSG = "프로그램이 종료되었습니다.";
	private static final String FAIL_MSG = "[서비스 실패]";
	private static final String SUCCESS_MSG = "[서비스 성공]";
	private static final String SEARCH_CATEGORY = "검색";
	private static final String UPDATE_CATEGORY = "수정";
	private static final String DELETE_CATEGORY = "삭제";
	
	public void startProgram() {
		finish:
		while(true) {
			int menu = this.printMenu();
			Member member = null;
			String memberId = "";
			int result = 0;
			switch(menu) {
				case 1:
					// 회원 가입
					member = this.inputMember();
					result = manage.registerMember(member);
					if(result == 0) {
						this.printMessage(FAIL_MSG);
					}else {
						this.printMessage(SUCCESS_MSG);
					}
					break;
				case 2:
					// 회원 전체 조회
					List<Member> mList = manage.showMemberList();
					this.printAllMembers(mList);
					break;
				case 3:
					// 회원 검색(아이디)
					memberId = this.inputMemberId(SEARCH_CATEGORY);
					member = manage.findOneById(memberId);
					if(member == null) {
						this.printMessage(FAIL_MSG);
					}else {
						this.showMember(member);						
					}
					break;
				case 4:
					// 회원 수정
					memberId = this.inputMemberId(UPDATE_CATEGORY);
					member = manage.findOneById(memberId);
					if(member == null) {
						this.printMessage(FAIL_MSG);
					}else {
						this.showMember(member);
						Member changedMember = this.inputMember();
						result = manage.changeOneById(memberId, changedMember);
						if(result == 0) {
							this.printMessage(FAIL_MSG);
						}else {
							this.printMessage(SUCCESS_MSG);
						}
					}
					break;
				case 5: 
					// 회원 삭제
					memberId = this.inputMemberId(DELETE_CATEGORY);
					result = manage.deleteMemberById(memberId);
					if(result == 0) {
						this.printMessage(FAIL_MSG);
					}else {
						this.printMessage(SUCCESS_MSG);
					}
					break;
				case 0:
					// 프로그램 종료
					this.printMessage(BYE_MSG);
					break finish;
			}
		}
	}

	private void showMember(Member member) {
		System.out.println("==== 회원 정보 ====");
		System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"
				+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
	}

	private String inputMemberId(String category) {
		System.out.println("==== 회원 "+category+"(아이디) ====");
		System.out.print(category+"하실 회원아이디 : ");
		String memberId = sc.next();
		return memberId;
	}

	private Member inputMember() {
		System.out.println("==== 회원 정보 입력 =====");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		
		return new Member(memberId, memberPwd, memberName, gender
				, age, email, phone, address, hobby);
	}

	private void printAllMembers(List<Member> mList) {
		System.out.println("===== 회원 전체 정보 =====");
		System.out.println("아이디\t\t이름\t\t이메일\t\t\t전화번호\t\t주소");
		for(Member member : mList) {
			System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"
					+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
		}
	}

	private void printMessage(String message) {
		System.out.println(message);
		
	}

	private int printMenu() {
		System.out.println("===== 회원 관리 프로그램 =====");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 전체 조회");
		System.out.println("3. 회원 검색(아이디)");
		System.out.println("4. 회원 수정");
		System.out.println("5. 회원 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}
}
