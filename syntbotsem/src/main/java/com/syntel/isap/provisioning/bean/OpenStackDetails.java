package com.syntel.isap.provisioning.bean;

public class OpenStackDetails {
	
	String type;
	String username;
	String password;
	int vdc_id;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getVdc_id() {
		return vdc_id;
	}
	public void setVdc_id(int vdc_id) {
		this.vdc_id = vdc_id;
	}
	

}
