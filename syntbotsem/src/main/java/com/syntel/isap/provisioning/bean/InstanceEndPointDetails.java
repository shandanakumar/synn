package com.syntel.isap.provisioning.bean;

public class InstanceEndPointDetails {

	
	private String name;
	private String vip;
	private int publicPort;
	private int localPort;
	private String protocol;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public int getPublicPort() {
		return publicPort;
	}
	public void setPublicPort(int publicPort) {
		this.publicPort = publicPort;
	}
	public int getLocalPort() {
		return localPort;
	}
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	
	
	
}
