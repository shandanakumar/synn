/**********************************************************************************************************
 *  Copyright (C) 2015 Syntel and/or its affiliates. 
 *  All rights reserved.
 **********************************************************************************************************/	

package com.syntel.isap.provisioning.bean;

public class AzureEndPointDTO {

	private String endPointName;
	private String protocol;
	private int publicPort;
	private int privatePort;
	

	public void setPrivatePort(int privatePort) {
		this.privatePort = privatePort;
	}
	public String getEndPointName() {
		return endPointName;
	}
	public void setEndPointName(String endPointName) {
		this.endPointName = endPointName;
	}
	public int getPrivatePort() {
		return privatePort;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getPublicPort() {
		return publicPort;
	}
	public void setPublicPort(int publicPort) {
		this.publicPort = publicPort;
	}


	
	@Override
	public String toString() {
		return "AzureEndPointDTO [endPointName=" + endPointName + ", protocol="
				+ protocol + ", publicPort=" + publicPort + ", privatePort="
				+ privatePort + "]";
	}
	
}
