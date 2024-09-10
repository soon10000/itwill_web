package com.itwill.lab05.repository;

import java.util.Objects;

// Model. 클래스 users 테이블.
public class User {
	private Integer id;
	private String userid;
	private String password;
	private String email;
	private Integer points;
	
	public User() {}

	public User(Integer id, String userid, String password, String email, Integer points) {
		this.id = id;
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.points = points;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userid=" + userid + ", password=" + password + ", email=" + email + ", points="
				+ points + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, points, userid);
	}

	// TODO : builder 패턴 생성
	public static UserBuilder userBulider() {
		return new UserBuilder();
	}
	
	
	// 보일러 플레이트 코드
	public static class UserBuilder{ // 메서드를 호출 할 때 User 객체가 생성되기 전에 메서드를 호출해야 하기 때문에 static으로 선언 (User 객체를 만들기 위한 메서드이므로)
		private Integer id;
		private String userid;
		private String password;
		private String email;
		private Integer points;
		
		private UserBuilder() {}
		
		public UserBuilder id(Integer id) {
			this.id = id;
			return this;
		}
		
		public UserBuilder userid(String userid) {
			this.userid = userid;
			return this;
		}
		
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public UserBuilder points(Integer points) {
			this.points = points;
			return this;
		}
		
		
		
		
		@Override
		public String toString() {
			return "UserBuilder [id=" + id + ", userid=" + userid + ", password=" + password + ", email=" + email
					+ ", points=" + points + "]";
		}

		public User userBuild() {
			return new User(id, userid, password, email, points);
		}
	}
}
