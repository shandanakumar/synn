/**********************************************************************************************************
 *  Copyright (C) 2015 Syntel and/or its affiliates. 
 *  All rights reserved.
 **********************************************************************************************************/	

package com.syntel.isap.provisioning.util;

import java.rmi.RemoteException;

import com.isap.core.IsapCoreWebservicesStub.GetIsapLaunchInstanceResp;
import com.syntel.isap.provisioning.soap.OpenStackServiceA2;




public class AsynchLaunchInstanceUtil extends Thread{
	private int openstackBespoke;
	private String openstackType;
	private String openstackKeyendpoint;
	private String openStackpassword;
	private String openstackUserTenantname;
	private String openstackUserid;
	private String openStackUsername;
	private String openstackInterface;
	private String openstackRegion;
	private String openstackZone;
	private String flavor;
	private String image;
	private String vm_name;
	private String keypair;
	private String network;
	private String security;
	private String puppetHostname;
	private String puppetHostip;
	private int puppetDisable;
	private int customId;
	
	
	private OpenStackServiceA2 openStackServices = new OpenStackServiceA2();
	
	public AsynchLaunchInstanceUtil(int openstackBespoke, String openstackType,
			String openstackKeyendpoint, String openStackpassword,
			String openstackUserTenantname, String openstackUserid,
			String openStackUsername, String openstackInterface,
			String openstackRegion, String openstackZone, String flavor,
			String image, String vm_name, String keypair, String network,
			String security, String puppetHostname, String puppetHostip,
			int puppetDisable, int customId) {
		super();
		this.openstackBespoke = openstackBespoke;
		this.openstackType = openstackType;
		this.openstackKeyendpoint = openstackKeyendpoint;
		this.openStackpassword = openStackpassword;
		this.openstackUserTenantname = openstackUserTenantname;
		this.openstackUserid = openstackUserid;
		this.openStackUsername = openStackUsername;
		this.openstackInterface = openstackInterface;
		this.openstackRegion = openstackRegion;
		this.openstackZone = openstackZone;
		this.flavor = flavor;
		this.image = image;
		this.vm_name = vm_name;
		this.keypair = keypair;
		this.network = network;
		this.security = security;
		this.puppetHostname = puppetHostname;
		this.puppetHostip = puppetHostip;
		this.puppetDisable = puppetDisable;
		this.customId = customId;
	}


	public void run(){  
		GetIsapLaunchInstanceResp lanch = new GetIsapLaunchInstanceResp();
		try {
			lanch = openStackServices.getOpenStackLaunchinstance(openstackBespoke, openstackType, openstackKeyendpoint, openStackpassword, openstackUserTenantname, openstackUserid, openStackUsername, openstackInterface, openstackRegion, openstackZone, flavor, image, vm_name, keypair, network, security, puppetHostname, puppetHostip, puppetDisable, customId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		}
}
