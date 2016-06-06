package com.syntel.isap.provisioning.bean;

import java.util.List;

public class VMDetails {
	
	private String status;
	private String instanceName;
	private String ipAddress;
	private String powerState;
	
	private List<InstanceEndPointDetails> instanceEndPointDetails;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPowerState() {
		return powerState;
	}

	public void setPowerState(String powerState) {
		this.powerState = powerState;
	}

	public List<InstanceEndPointDetails> getInstanceEndPointDetails() {
		return instanceEndPointDetails;
	}

	public void setInstanceEndPointDetails(
			List<InstanceEndPointDetails> instanceEndPointDetails) {
		this.instanceEndPointDetails = instanceEndPointDetails;
	}
	

}
