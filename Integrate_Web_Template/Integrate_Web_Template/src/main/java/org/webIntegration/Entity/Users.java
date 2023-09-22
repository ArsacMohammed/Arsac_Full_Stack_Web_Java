package org.webIntegration.Entity;

public class Users {
	private int user_id;
	private String username;
	private String email;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public Users(int user_id, String username, String email) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
