package com.itwill.springboot5.domain;



public enum MemberRole {
	// Enum은 상수를 정의하는 클래스이며, MemberRole이라는 클래스에는 USER, ADMIN 2개의 객체만 존재한 것
	USER("ROLE_USER"), // public static final MemberRole USER = new MemberRole(""); 와 같은 코드 
	ADMIN("ROLE_ADMIN");
	
	private String authority;
	
	
	
	// 주의: enum의 생성자는 항상 private. private 수식어는 생략됨.
	MemberRole(String authoriry){
		this.authority = authoriry;
	}
	
	public String getAuthority() {
		return this.authority;
	}

}
