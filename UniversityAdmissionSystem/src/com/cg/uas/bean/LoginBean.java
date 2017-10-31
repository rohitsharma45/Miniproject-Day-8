package com.cg.uas.bean;

public class LoginBean {

	private String loginId;
	private String password;
	
	
	public LoginBean() {
		
		
	}
	
	
	public LoginBean(String loginId, String password) {
		super();
		this.loginId = loginId;
		this.password = password;
	}


	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
