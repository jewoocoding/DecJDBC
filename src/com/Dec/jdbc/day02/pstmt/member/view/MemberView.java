package com.Dec.jdbc.day02.pstmt.member.view;

import java.util.*;

import com.Dec.jdbc.day02.pstmt.member.controller.MemberController;
import com.Dec.jdbc.day02.pstmt.member.model.vo.Member;

public class MemberView {
	
	private static final String INPUT_MISMATCH = "잘못 입력하셨습니다.";
	private static final String EXIT_PROGRAM = "프로그램이 종료되었습니다.";
	private static final String SUCCESS_MSG = "[서비스 성공]";
	private static final String FAIL_MSG = "[서비스 실패]";
	private static final String NO_DATA_FOUND = "데이터를 찾지 못했습니다.";
	private MemberController manage;
	private Scanner sc;
	
	public MemberView() {
		sc = new Scanner(System.in);
		manage = new MemberController();
	}
	
	public void startProgram() {
		end:
		while(true) {
			int menu = 0;
			String memberId = "";
			Member member = null;
			int result = 0;
			
			try {
				menu = this.mainMenu();
			}catch(InputMismatchException e) {
				System.out.println(INPUT_MISMATCH);
				sc.next();
				continue;
			}
			switch(menu) {
				case 1:	// 회원가입 
					member = this.inputMember();
					result = manage.insertMember(member);
					if(result > 0) {
						this.printMessage(SUCCESS_MSG);
					}else {
						this.printMessage(FAIL_MSG);						
					}
					break;
				case 2:	// 회원 정보 수정 
					memberId = this.inputMemberId();
					member = manage.selectOneById(memberId);
					if(member != null) {
						member = this.modifyMember(memberId);
						result = manage.updateMember(member);
						if(result > 0) {
							this.printMessage(SUCCESS_MSG);
						}else {
							this.printMessage(FAIL_MSG);
						}
					}else {
						this.printMessage(NO_DATA_FOUND);
					}
					break;
				case 3:	// 회원 탈퇴 
					memberId = this.inputMemberId();
					result = manage.deleteOneById(memberId);
					if(result > 0) {
						this.printMessage(SUCCESS_MSG);
					}else {
						this.printMessage(FAIL_MSG);
					}
					break;
				case 4: // 회원 전체 정보 출력
					List<Member> mList = manage.getMemberList();
					if(mList != null) {
						this.showMemberList(mList);
					}else {
						this.printMessage(NO_DATA_FOUND);
					}
					break;
				case 5: // 회원 검색
					memberId = this.inputMemberId();
					member = manage.selectOneById(memberId);
					if(member != null) {
						this.printOne(member);
					}else {
						this.printMessage(NO_DATA_FOUND);
					}
					break;
				case 0: // 프로그램 종료
					this.printMessage(EXIT_PROGRAM);
					break end;
				default: break;
			}
		}
		
	}

	private Member modifyMember(String memberId) {
		// 비번, 이름, 나이, 이메일, 전화번호, 주소, 취미
		System.out.println("==== 수정할 정보 입력 ====");
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		Member member = new Member(memberId, memberPwd, email, phone, address, hobby);
		return member;
	}

	private void showMemberList(List<Member> mList) {
		System.out.println("==== 회원 전체 정보 출력 ====");
		System.out.println("아이디\t이름\t성별\t나이\t이메일\t\t\t전화번호\t주소");
		for(Member member : mList) {
			System.out.println(member.getMemberId()+"\t"
					+member.getMemberName()+"\t"
					+member.getGender()+"\t"
					+member.getAge()+"\t"
					+member.getEmail()+"\t\t"
					+member.getPhone()+"\t"
					+member.getAddress());
		}
		
	}

	private void printOne(Member member) {
		System.out.println("==== 회원 정보 출력 ====");
		System.out.println("아이디\t이름\t성별\t나이\t이메일\t\t\t전화번호\t주소");
		System.out.println(member.getMemberId()+"\t"
				+member.getMemberName()+"\t"
				+member.getGender()+"\t"
				+member.getAge()+"\t"
				+member.getEmail()+"\t\t"
				+member.getPhone()+"\t"
				+member.getAddress());
	}

	private String inputMemberId() {
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}

	private Member inputMember() {
		System.out.println("==== 회원 가입 ====");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별(M,F) : ");
		String gender = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		Member member = new Member(memberId, memberPwd, memberName, gender, age);
		return member;
	}

	private void printMessage(String message) {
		System.out.println(message);
	}

	private int mainMenu() throws InputMismatchException{
		System.out.println("==== 회원 관리 프로그램 ====");
		System.out.println("1. 회원 가입");
		System.out.println("2. 회원 정보 수정");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 회원 전체 정보 출력");
		System.out.println("5. 회원 검색");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 >> ");
		int choice = sc.nextInt();
		return choice;
	}

}
