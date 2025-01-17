package com.Dec.jdbc.day03.pstmt.member.run;

import com.Dec.jdbc.day03.pstmt.common.Singleton;
import com.Dec.jdbc.day03.pstmt.member.view.MemberView;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberView view = new MemberView();
		view.startProgram();
		Singleton singleton = Singleton.getInstance(); // new Singleton();
		// singleton = Singleton.instance; -> static이더4라도 private 멤버변수에 접근 불가능 -> 메소드를 통해 접근
	}

}
