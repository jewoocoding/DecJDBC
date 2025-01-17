package com.Dec.jdbc.day03.pstmt.common;

public class Singleton {
	/*
	 * - 디자인 패턴
	 * 
	 * 	싱클톤 패턴 - 클래스의 인스턴스가 하나만 생성되도록 보장하는 디자인 패턴
	 */
	// 데이터베이스 연결작업은 부하가 큰 작업이고
	// 반복될 수록 데이터베이스에 무리가 될 수 있음.
	// -> 데이터베이스 연결은 비용이 많이드는 작업
	// , 많은 메모리와 시스템 리소스가 필요함.
	// 그런데 이러한 작업을 싱글톤으로 관리하면 
	// 한번만 연결 객체를 생성해서 재사용할 수 있음.
	// -> 성능 향상, 새로운 연결을 생성할 때마다 발생하는
	// 비용 감소, DB 부하 감소
	// static이면서 Singleton 타입인 멤버변수 필요
	
	private static Singleton instance;
	
	private Singleton() {} 
	
	// static이면서 public이고 리턴타입이 Singleton인 메소드 필요
	public static Singleton getInstance() {
		// if문으로 멤버변수 null 체크 후 null이면 객체 생성
		// null이 아니면 그대로 리턴
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
