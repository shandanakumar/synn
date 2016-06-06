/**********************************************************************************************************
 *  Copyright (C) 2015 Syntel and/or its affiliates. 
 *  All rights reserved.
 **********************************************************************************************************/	

package com.syntel.isap.provisioning.bean;


public class AzureDetails {

	private String azureUserName;
	private String azurePassword;
	private String azureCnfPassword;
	private String azureImage;
	private String azureSize;
	private String azureCloudService;
	private String azureRegion;
	private String azureStorageAccount;
	private String azureEndPoint;
	private String azureVmName;
	private String azureStartDate;
	private String azureEndDate;
	private String azureServiceName;
	private String azureServiceDesc;
	private String azureVmId;
	
	

	public String getAzureUserName() {
		return azureUserName;
	}
	public void setAzureUserName(String azureUserName) {
		this.azureUserName = azureUserName;
	}
	public String getAzurePassword() {
		return azurePassword;
	}
	public void setAzurePassword(String azurePassword) {
		this.azurePassword = azurePassword;
	}
	public String getAzureCnfPassword() {
		return azureCnfPassword;
	}
	public void setAzureCnfPassword(String azureCnfPassword) {
		this.azureCnfPassword = azureCnfPassword;
	}
	public String getAzureImage() {
		return azureImage;
	}
	public void setAzureImage(String azureImage) {
		this.azureImage = azureImage;
	}
	public String getAzureSize() {
		return azureSize;
	}
	public void setAzureSize(String azureSize) {
		this.azureSize = azureSize;
	}
	public String getAzureCloudService() {
		return azureCloudService;
	}
	public void setAzureCloudService(String azureCloudService) {
		this.azureCloudService = azureCloudService;
	}
	public String getAzureRegion() {
		return azureRegion;
	}
	public void setAzureRegion(String azureRegion) {
		this.azureRegion = azureRegion;
	}
	public String getAzureStorageAccount() {
		return azureStorageAccount;
	}
	public void setAzureStorageAccount(String azureStorageAccount) {
		this.azureStorageAccount = azureStorageAccount;
	}
	
	public String getAzureEndPoint() {
		return azureEndPoint;
	}
	public void setAzureEndPoint(String azureEndPoint) {
		this.azureEndPoint = azureEndPoint;
	}
	public String getAzureVmName() {
		return azureVmName;
	}
	public void setAzureVmName(String azureVmName) {
		this.azureVmName = azureVmName;
	}
	public String getAzureStartDate() {
		return azureStartDate;
	}
	public void setAzureStartDate(String azureStartDate) {
		this.azureStartDate = azureStartDate;
	}
	public String getAzureEndDate() {
		return azureEndDate;
	}
	public void setAzureEndDate(String azureEndDate) {
		this.azureEndDate = azureEndDate;
	}
	public String getAzureServiceName() {
		return azureServiceName;
	}
	public void setAzureServiceName(String azureServiceName) {
		this.azureServiceName = azureServiceName;
	}
	public String getAzureServiceDesc() {
		return azureServiceDesc;
	}
	public void setAzureServiceDesc(String azureServiceDesc) {
		this.azureServiceDesc = azureServiceDesc;
	}
	public String getAzureVmId() {
		return azureVmId;
	}
	public void setAzureVmId(String azureVmId) {
		this.azureVmId = azureVmId;
	}
	@Override
	public String toString() {
		return "AzureDetails [azureUserName=" + azureUserName
				+ ", azurePassword=" + azurePassword + ", azureCnfPassword="
				+ azureCnfPassword + ", azureImage=" + azureImage
				+ ", azureSize=" + azureSize + ", azureCloudService="
				+ azureCloudService + ", azureRegion=" + azureRegion
				+ ", azureStorageAccount=" + azureStorageAccount
				+ ", azureEndPoint=" + azureEndPoint + ", azureVmName="
				+ azureVmName + ", azureStartDate=" + azureStartDate
				+ ", azureEndDate=" + azureEndDate + ", azureServiceName="
				+ azureServiceName + ", azureServiceDesc=" + azureServiceDesc
				+ ", azureVmId=" + azureVmId + "]";
	}
	


}