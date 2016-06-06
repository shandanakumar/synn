package com.syntel.isap.provisioning.soap;

import java.io.Serializable;

/**
 * @author KK5007843
 *
 */
public class TokenDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String token;

	private int status;
	/*
	 * private String tenant_id;
	 * 
	 * 
	 * public String getTenant_id() { return tenant_id; }
	 * 
	 * public void setTenant_id(String tenant_id) { this.tenant_id = tenant_id;
	 * }
	 */
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
