package com.Dec.jdbc.day01.stmt.member.model.vo;

import java.sql.Date; 

public class Member {
	// rset에서 꺼내온 모든 값을 담아야하기 때문에
	// 필드의 개수는 컬럼의 개수와 같음
	// 필드의 자료형은 컬럼의 자료형과 매핑을 해주어야함.
	// String 	 - VARCHAR2, CHAR
	// int 		 - NUMBER
	// Date 	 - DATE
	// TimeStamp - TIMESTAMP
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	// SQL Date 자료형을 가져오기 위하여
	// java.sql.Date 클래스를 임포트해서 사용
	private Date enrollDate; 
	
	public Member() {}

	public Member(String memberId, String memberPwd, String memberName, char gender
			, int age, String email, String phone
			, String address, String hobby, Date enrollDate) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}
	
	public Member(String memberId, String memberPwd, String memberName, char gender, int age, String email,
			String phone, String address, String hobby) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public char getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getHobby() {
		return hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}
	
	// 데이터 확인용
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName + ", gender="
				+ gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}
	
	
	
}
