/**********************************************************************************************************
 *  Copyright (C) 2015 Syntel and/or its affiliates. 
 *  All rights reserved.
 **********************************************************************************************************/	

package com.syntel.isap.provisioning.constants;

public interface AzureConstants {


	int AZURE_VDC_HARDDISK = 0;
	String AZURE_VDC_PROVIDER = "Azure";
	String AZURE_VDC_STATUS = "Requested";
	String AZURE_VDC_IPADDRESS = "-";
	String AZURE_VDC_PUBLIC_IPADDRESS = "-";
	int AZURE_VDC_ID = 41;
	int AZURE_DELETE_FLAG = 0;
	String AZURE_VDC_HOSTNAME = null;
	String AZURE_VDC_INSTANCE_ID = null;
	int SERVICE_REQ_FLOW_ID = 1;
	int RANDOM = 8888;
	
	
	// Azure params must be same value
	String AZURE_ROLE_SIZE = "Standard_D1";
	String AZURE_SERVICE_NAME = "";
	String AZURE_CONFIG_SET_TYPE = "WindowsProvisioningConfiguration";
	
	// Azure params should be any data
	String AZURE_DEPLOYMENT_LABEL = "label";
	String AZURE_AZURE_DEPLOYMENT_NAME = "deploymentSyntbots2";
	String AZURE_AZURE_DEPLOYMENT_NAME_FOR_DB_UPDATE = "Production";
		String AZURE_HOST_NAME = "syncgdc";
	String AZURE_HOST_OS_IMAGE = "windows";
	
	//custom Provision extension params 
	String AZURE_CUSTOM_IMAGE = "image";
	String AZURE_CUSTOM_FLAVOR = "flavor";
	String AZURE_BESPOKE_NETWORK = "network";
	String AZURE_BESPOKE_FLAVOR = "flavor";
	
}
