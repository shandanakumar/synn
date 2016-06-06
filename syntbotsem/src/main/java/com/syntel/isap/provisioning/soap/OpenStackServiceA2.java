package com.syntel.isap.provisioning.soap;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.isap.core.IsapCoreWebservicesStub;
import com.isap.core.IsapCoreWebservicesStub.AddOpenstackFloatingIp;
import com.isap.core.IsapCoreWebservicesStub.AddOpenstackFloatingIpReq;
import com.isap.core.IsapCoreWebservicesStub.AddOpenstackFloatingIpResp;
import com.isap.core.IsapCoreWebservicesStub.AddOpenstackFloatingIpResponse;
import com.isap.core.IsapCoreWebservicesStub.AllocateOpenstackFloatingIp;
import com.isap.core.IsapCoreWebservicesStub.AllocateOpenstackFloatingIpReq;
import com.isap.core.IsapCoreWebservicesStub.AllocateOpenstackFloatingIpResp;
import com.isap.core.IsapCoreWebservicesStub.AllocateOpenstackFloatingIpResponse;
import com.isap.core.IsapCoreWebservicesStub.ArrayOfString;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackKeypair;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackKeypairReq;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackKeypairResponse;
import com.isap.core.IsapCoreWebservicesStub.GetHypervisorDetails;
import com.isap.core.IsapCoreWebservicesStub.GetHypervisorDetailsResponse;
import com.isap.core.IsapCoreWebservicesStub.GetIsapCloudAuthentication;
import com.isap.core.IsapCoreWebservicesStub.GetIsapCloudAuthenticationRequest;
import com.isap.core.IsapCoreWebservicesStub.GetIsapCloudAuthenticationResponse;
import com.isap.core.IsapCoreWebservicesStub.GetIsapCloudAuthenticationResponseE;
import com.isap.core.IsapCoreWebservicesStub.GetIsapLaunchInstanceReq;
import com.isap.core.IsapCoreWebservicesStub.GetIsapLaunchInstanceResp;
import com.isap.core.IsapCoreWebservicesStub.GetIsapLaunchinstance;
import com.isap.core.IsapCoreWebservicesStub.GetIsapLaunchinstanceResponse;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackFlavorDetails;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackFlavorDetailsReq;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackFlavorDetailsResponse;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackFlavorResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackHypervisorDetailsReq;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackHypervisorDetailsResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackImageDetails;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackImageDetailsReq;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackImageDetailsResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackImageDetailsResponse;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackSecurityGroupDetails;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackSecurityGroupDetailsReq;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackSecurityGroupDetailsResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackSecurityGroupDetailsResponse;
import com.isap.core.IsapCoreWebservicesStub.ISAPTerminateOpensInstance;
import com.isap.core.IsapCoreWebservicesStub.ISAPTerminateOpensInstanceResponse;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFlavors;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFlavorsReq;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFlavorsResp;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFlavorsResponse;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackImages;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackImagesReq;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackImagesResp;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackImagesResponse;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackKeypairs;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackKeypairsReq;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackKeypairsResp;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackKeypairsResponse;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackNetworks;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackNetworksReq;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackNetworksResp;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackNetworksResponse;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackSecurityGroups;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackSecurityGroupsReq;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackSecurityGroupsResp;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackSecurityGroupsResponse;
import com.isap.core.IsapCoreWebservicesStub.RemoveOpenstackFloatingIpReq;
import com.isap.core.IsapCoreWebservicesStub.RemoveOpenstackFloatingIpResp;
import com.isap.core.IsapCoreWebservicesStub.RemoveOpenstackFloatingIpRespE;
import com.isap.core.IsapCoreWebservicesStub.RemoveOpenstackFloatingIpRespResponse;
import com.isap.core.IsapCoreWebservicesStub.Rules;
import com.isap.core.IsapCoreWebservicesStub.TerminateOpensInstanceReq;
import com.syntel.isap.provisioning.constants.OpenStackCredentials;
import com.syntel.isap.provisioning.controller.LaunchController;

public class OpenStackServiceA2 {

	

	public ArrayList<Map<String, String>> getListOpenStackKeyPairs(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
		
		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		
		ListOpenstackKeypairs lok=new ListOpenstackKeypairs();
		ListOpenstackKeypairsReq lokreq=new ListOpenstackKeypairsReq();
		lokreq.setOpesusername(opsusername);
		lokreq.setOps_interface(opsInterface);
		lokreq.setOps_region(Opsregion);
		lokreq.setOpsKeyEndpoint(opsKeyEndpoint);
		lokreq.setOpspassword(opspassword);
		lokreq.setOpstenantname(opstenantname);
		lok.setLokr(lokreq);
		
		ListOpenstackKeypairsResponse templokresp=icwstub.listOpenstackKeypairs(lok);
		ListOpenstackKeypairsResp lokresp=templokresp.get_return();
		System.out.println("Response :"+lokresp.getLongMessage());
		ArrayOfString[] tempkeypairsWrapperArray=lokresp.getKeypairs();
		
		Map<String, String> tempkeypair=new HashMap<String, String>();
		ArrayList<Map<String, String>> keypairs=new ArrayList<Map<String,String>>();
		
		for(int i=0;i<tempkeypairsWrapperArray.length;i++)
		{
			String keypairWrapperArray[]=tempkeypairsWrapperArray[i].getArray();
			tempkeypair.put("keyname", keypairWrapperArray[0]);
			tempkeypair.put("value", keypairWrapperArray[1]);
			keypairs.add(tempkeypair);
			
		}
		/*System.out.println(keypairs.size());
		System.out.println("Array 1:"+tempkeypairsWrapperArray[0].getArray()[0]);*/
		
		return keypairs;
	}
	
	
	
	
public LinkedHashMap<String, String> getListOpenstackFlavors(String opsusername,String opsKeyEndpoint,String ops_interface,String ops_region,String opspassword,String opstenantname) throws RemoteException{
	
		
		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		ListOpenstackFlavors flavorslist=new ListOpenstackFlavors();
		ListOpenstackFlavorsReq flavorsReq=new ListOpenstackFlavorsReq();
		flavorsReq.setOpesusername(opsusername);
		flavorsReq.setOps_interface(ops_interface);
		flavorsReq.setOps_region(ops_region);
		flavorsReq.setOpsKeyEndpoint(opsKeyEndpoint);
		flavorsReq.setOpspassword(opspassword);
		flavorsReq.setOpstenantname(opstenantname);
		flavorslist.setLof(flavorsReq);
	
		ListOpenstackFlavorsResponse flavorerespwrap=icwstub.listOpenstackFlavors(flavorslist);
		ListOpenstackFlavorsResp flavresp= flavorerespwrap.get_return();
		
		LinkedHashMap<String, String> flavourList = new LinkedHashMap<String, String>();
		
		  ArrayOfString[] tempFlavorWrapperArray=flavresp.getFlavors();
	      List<ArrayOfString> flavors = Arrays.asList(tempFlavorWrapperArray);
		
		for (ArrayOfString flavor : flavors) {
			String[] data = flavor.getArray();
			flavourList.put(data[0], data[1]);
			System.out.println(data[0]+"==="+data[1]);
		}
	
		return flavourList;
		
	}
	
	
	public GetOpenstackFlavorResp getOpenstackFlavorDetails(String flavorid,String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
		
		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		
		GetOpenstackFlavorDetailsReq flvreq= new GetOpenstackFlavorDetailsReq();
		
		GetOpenstackFlavorDetails flavdet = new GetOpenstackFlavorDetails();
		
		flvreq.setFlavor_id(flavorid);
		flvreq.setOpesusername(opsusername);
		flvreq.setOps_interface(opsInterface);
		flvreq.setOps_region(Opsregion);
		flvreq.setOpsKeyEndpoint(opsKeyEndpoint);
		flvreq.setOpspassword(opspassword);
		flvreq.setOpstenantname(opstenantname);
		flavdet.setGofr(flvreq);
	
		GetOpenstackFlavorDetailsResponse flvresponse=icwstub.getOpenstackFlavorDetails(flavdet);
		
		GetOpenstackFlavorResp response=flvresponse.get_return();
		System.out.println("Flavor ID:"+response.getId());
		
		return response;
		
	}
	
	
	
	public int getIsapCloudAuthentication(String awsUsername,String cloudType,String opsKeyEndpoint,String opspassword,String opstenantname,String userid,String opsusername) throws RemoteException{

		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		GetIsapCloudAuthentication getauth = new GetIsapCloudAuthentication();
		
		GetIsapCloudAuthenticationRequest getaut= new GetIsapCloudAuthenticationRequest();
		
		getaut.setAwsUsername(awsUsername);
		getaut.setCloudType(cloudType);
		getaut.setOpsKeyEndPoint(opsKeyEndpoint);
		getaut.setOpsPassword(opspassword);
		getaut.setOpsTenantName(opstenantname);
		getaut.setOpsUserid(userid);
		getaut.setOpsUsername(opsusername);
		getauth.setGicar(getaut);
		GetIsapCloudAuthenticationResponseE response = icwstub.getIsapCloudAuthentication(getauth);
		GetIsapCloudAuthenticationResponse resp = response.get_return();
		
		int status=resp.getIsapRetunCode();
		System.out.println("status"+status);
		return status;
		

	}
	

	
	public LinkedHashMap<String, String> getListOpenstackSecurityGroups(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
		
		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		
		ListOpenstackSecurityGroups secgrp = new ListOpenstackSecurityGroups();
		ListOpenstackSecurityGroupsReq secreq = new ListOpenstackSecurityGroupsReq();
		
		secreq.setOpesusername(opsusername);
		secreq.setOps_interface(opsInterface);
		secreq.setOps_region(Opsregion);
		secreq.setOpsKeyEndpoint(opsKeyEndpoint);
		secreq.setOpspassword(opspassword);
		secreq.setOpstenantname(opstenantname);
		secgrp.setLosgr(secreq);
	
		ListOpenstackSecurityGroupsResponse secerespwrap=icwstub.listOpenstackSecurityGroups(secgrp);
		ListOpenstackSecurityGroupsResp secresp= secerespwrap.get_return();

		LinkedHashMap<String, String> seckeypair = new LinkedHashMap<String, String>();
		
		ArrayOfString[] SecgrpWrapperArray=secresp.getSecurityGroups();
	      List<ArrayOfString> flavors = Arrays.asList(SecgrpWrapperArray);
		for (ArrayOfString flavor : flavors) {
			String[] data = flavor.getArray();
			seckeypair.put(data[0], data[1]);
			System.out.println(data[0]+"==="+data[1]);
		}
		
		System.out.println("======Size========="+seckeypair.size());
		return seckeypair;
		
	}
	
	
	
	
	
	 public List<SecurityRules> getSecurityGroupDetails(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname,String secGpId) throws RemoteException{
			
			IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
			
			GetOpenstackSecurityGroupDetails secgrp = new GetOpenstackSecurityGroupDetails();
			
			GetOpenstackSecurityGroupDetailsReq secreq = new GetOpenstackSecurityGroupDetailsReq();
			
			secreq.setOpesusername(opsusername);
			secreq.setOps_interface(opsInterface);
			secreq.setOps_region(Opsregion);
			secreq.setOpsKeyEndpoint(opsKeyEndpoint);
			secreq.setOpspassword(opspassword);
			secreq.setOpstenantname(opstenantname);
			secreq.setSec_gp_id(secGpId);
			secgrp.setGosgr(secreq);
			
			GetOpenstackSecurityGroupDetailsResponse secpwrap=icwstub.getOpenstackSecurityGroupDetails(secgrp);
			GetOpenstackSecurityGroupDetailsResp secgrplistresp= secpwrap.get_return();
		
		
			Rules[] rules1 = secgrplistresp.getSecGrpDetails();
			List<Rules> rules=Arrays.asList(rules1);
			
			List<SecurityRules> secRules = new ArrayList<SecurityRules>();
			for (Rules rule : rules) {
				if (rule != null) {
					SecurityRules secRule = new SecurityRules();
					secRule.setFromPort(rule.getFrom_port());
					secRule.setToPort(rule.getTo_port());
					secRule.setIpProtocol(rule.getIp_protocol());
					
					if(secRule!=null){
					secRules.add(secRule);
					}
					
					System.out.println(secRule.getFromPort());
				}
			}
			return secRules;
			
		}
	

	 public int removeFloatinIp(String instanceId, String opsInterface,
				String opsKeyEndpoint,  String opspassword,String Opsregion,
				String opstenantname, String opsusername) throws RemoteException {
		
		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		
		RemoveOpenstackFloatingIpReq remfpt = new RemoveOpenstackFloatingIpReq();
		
		RemoveOpenstackFloatingIpRespE reqq = new RemoveOpenstackFloatingIpRespE();
		
		remfpt.setInst_id(instanceId);
		remfpt.setOps_interface(opsInterface);
		remfpt.setOps_key_EP(opsKeyEndpoint);
		remfpt.setOps_password(opspassword);
		remfpt.setOps_region(Opsregion);
		remfpt.setOps_tenant_name(opstenantname);
		remfpt.setOps_username(opsusername);

		
		reqq.setRfip(remfpt);
		

		RemoveOpenstackFloatingIpRespResponse remvfloatipResponse = icwstub.removeOpenstackFloatingIpResp(reqq);
		RemoveOpenstackFloatingIpResp removefloattResp = remvfloatipResponse.get_return();
		System.out.println(removefloattResp.getLongMessage());
		
		return removefloattResp.getReturnCode();
		
	}

	 
	 
	 
public GetOpenstackSecurityGroupDetailsResp getOpenstackSecurityGroupDetails(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname,String secgrpid) throws RemoteException{
		
		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		
		GetOpenstackSecurityGroupDetailsReq secgrpdet = new GetOpenstackSecurityGroupDetailsReq();
		
		GetOpenstackSecurityGroupDetails secreq = new GetOpenstackSecurityGroupDetails();
		
		
		
		secgrpdet.setOpesusername(opsusername);
		secgrpdet.setOps_interface(opsInterface);
		secgrpdet.setOps_region(Opsregion);
		secgrpdet.setOpsKeyEndpoint(opsKeyEndpoint);
		secgrpdet.setOpspassword(opspassword);
		secgrpdet.setOpstenantname(opstenantname);
		secgrpdet.setSec_gp_id(secgrpid);
		secreq.setGosgr(secgrpdet);
	
		GetOpenstackSecurityGroupDetailsResponse secdetesponse=icwstub.getOpenstackSecurityGroupDetails(secreq);
		
		GetOpenstackSecurityGroupDetailsResp secresponse=secdetesponse.get_return();
		System.out.println(secresponse.getReturnCode());
		
		return secresponse;
		
	}
	
	

public LinkedHashMap<String, String> getListOpenstackImages(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
	
	IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
	
	ListOpenstackImages images=new ListOpenstackImages();
	ListOpenstackImagesReq imgreq= new ListOpenstackImagesReq();

	imgreq.setOpesusername(opsusername);
	imgreq.setOps_interface(opsInterface);
	imgreq.setOps_region(Opsregion);
	imgreq.setOpsKeyEndpoint(opsKeyEndpoint);
	imgreq.setOpspassword(opspassword);
	imgreq.setOpstenantname(opstenantname);
	images.setLoir(imgreq);

	ListOpenstackImagesResponse imgerespwrap=icwstub.listOpenstackImages(images);
	ListOpenstackImagesResp imgresp= imgerespwrap.get_return();
	LinkedHashMap<String, String> respimages = new LinkedHashMap<String, String>();
	
	ArrayOfString[] imgWrapperArray=imgresp.getImages();
	List<ArrayOfString> flavors = Arrays.asList(imgWrapperArray);
	
	for (ArrayOfString flavor : flavors) {
		String[] data = flavor.getArray();
		respimages.put(data[0], data[1]);
		System.out.println(data[0]+"==="+data[1]);
	}
	
	System.out.println("======Size========="+respimages.size());
	return respimages;
	

}




public String createOpenstackKeypair(String keypairName, String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
		
		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		CreateOpenstackKeypair createOpenstackKeypair=new CreateOpenstackKeypair();
		CreateOpenstackKeypairReq keypairreq= new CreateOpenstackKeypairReq();
		keypairreq.setKeypair_name(keypairName);
		keypairreq.setOpesusername(opsusername);
		keypairreq.setOps_interface(opsInterface);
		keypairreq.setOps_region(Opsregion);
		keypairreq.setOpsKeyEndpoint(opsKeyEndpoint);
		keypairreq.setOpspassword(opspassword);
		keypairreq.setOpstenantname(opstenantname);
		createOpenstackKeypair.setCokreq(keypairreq);
		CreateOpenstackKeypairResponse keypairresp = icwstub.createOpenstackKeypair(createOpenstackKeypair);
		
		String response=keypairresp.get_return().getPublic_key();
		System.out.println("========="+keypairresp.get_return().getPublic_key());
		
		return response;
		
	
		
	}









public LinkedHashMap<String, String> getListOpenstackNetworks(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
	
	IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
	
	ListOpenstackNetworks networks=new ListOpenstackNetworks();
	ListOpenstackNetworksReq networkreq= new ListOpenstackNetworksReq();

	networkreq.setOpesusername(opsusername);
	networkreq.setOps_interface(opsInterface);
	networkreq.setOps_region(Opsregion);
	networkreq.setOpsKeyEndpoint(opsKeyEndpoint);
	networkreq.setOpspassword(opspassword);
	networkreq.setOpstenantname(opstenantname);
	networks.setLonr(networkreq);

	ListOpenstackNetworksResponse networkrespwrap=icwstub.listOpenstackNetworks(networks);
	ListOpenstackNetworksResp netresp= networkrespwrap.get_return();
     ArrayOfString[] networkWrapperArray=netresp.getNetworks();
   
   LinkedHashMap<String, String> respnetworks = new LinkedHashMap<String, String>();
	
	List<ArrayOfString> networkList = Arrays.asList(networkWrapperArray);
	
	for (ArrayOfString network : networkList) {
		String[] data = network.getArray();
		if(data[0] !=null){
			respnetworks.put(data[0], data[1]);
		System.out.println(data[0]+"==="+data[1]);
		}
		
	}
	
	return respnetworks;
	
}




public AllocateOpenstackFloatingIpResp allocateOpenstackFloatingIp(String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname,String opsusername,String pool) throws RemoteException{
		
		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		
		AllocateOpenstackFloatingIp alctOSFloatIp=new AllocateOpenstackFloatingIp();
		AllocateOpenstackFloatingIpReq alctOSFloatIpReq=new AllocateOpenstackFloatingIpReq();
		
		alctOSFloatIpReq.setOpsKeyEndpoint(opsKeyEndpoint);
		alctOSFloatIpReq.setOps_interface(opsInterface);
		alctOSFloatIpReq.setOps_region(Opsregion);
		alctOSFloatIpReq.setOpspassword(opspassword);
		alctOSFloatIpReq.setOpstenantname(opstenantname);
		alctOSFloatIpReq.setOpsusername(opsusername);
		alctOSFloatIpReq.setPool(pool);
		alctOSFloatIp.setReq(alctOSFloatIpReq);
		AllocateOpenstackFloatingIpResponse allocatFloatIpResp=icwstub.allocateOpenstackFloatingIp(alctOSFloatIp);
		AllocateOpenstackFloatingIpResp allocatResponse =allocatFloatIpResp.get_return();
		System.out.println(allocatFloatIpResp.get_return().getFloatingIP());
		
		return allocatResponse;
		
		
	}
	


public AddOpenstackFloatingIpResp addOpenstackFloatingIptoInstance(String floatingIpAdd,String opsInterface,String opsKeyEndpoint,String Opsregion,String opspassword,String opstenantname,String opsusername,String pool,String serverId) throws RemoteException{
	
	IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
	
	AddOpenstackFloatingIp addFloatIp=new AddOpenstackFloatingIp();
	AddOpenstackFloatingIpReq addFloatIpReq=new AddOpenstackFloatingIpReq();
	
	
	addFloatIpReq.setFloatingIpAdd(floatingIpAdd);
	
	addFloatIpReq.setOps_interface(opsInterface);
	addFloatIpReq.setOps_key_EP(opsKeyEndpoint);
	addFloatIpReq.setOps_region(Opsregion);
	addFloatIpReq.setOpspassword(opspassword);
	addFloatIpReq.setOpstenantname(opstenantname);
	addFloatIpReq.setOpsusername(opsusername);
	addFloatIpReq.setPool(pool);
	addFloatIpReq.setServerId(serverId);
	addFloatIp.setAfip(addFloatIpReq);
	
	
	AddOpenstackFloatingIpResponse addFloatIpResp=icwstub.addOpenstackFloatingIp(addFloatIp);
	AddOpenstackFloatingIpResp addfloatResponse =addFloatIpResp.get_return();
	System.out.println(addFloatIpResp.get_return().getFloating_ip());
	
	return addfloatResponse;
	
	
}



public GetIsapLaunchInstanceResp getOpenStackLaunchinstance(int bspoke, String cloudtype,
		String opsKeyEndpoint, String opspassword, String opstenantname,
		String opesusrid, String opesusername, String opsInterface,
		String Opsregion, String opszone, String opsflavorid,
		String opsimgid, String opsinstname, String opeskeyname,
		String opsnetwrkid, String opssecgrp, String pptmasterhostname,
		String pptmasterip, int scm, int vmcustomid) throws RemoteException {
	
	IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();
	icwstub._getServiceClient().getOptions().setTimeOutInMilliSeconds(120000);
	GetIsapLaunchinstance gilinst = new GetIsapLaunchinstance();
	GetIsapLaunchInstanceReq gilinstreq = new GetIsapLaunchInstanceReq();

	gilinstreq.setBeSpoke(bspoke);
	gilinstreq.setCloudType(cloudtype);
	gilinstreq.setOpsKeyEndpoint(opsKeyEndpoint);
	gilinstreq.setOpsPassword(opspassword);
	gilinstreq.setOpsTenantName(opstenantname);
	gilinstreq.setOpsUserid(opesusrid);
	gilinstreq.setOpsUsername(opesusername);
	gilinstreq.setOps_interface(opsInterface);
	gilinstreq.setOps_region(Opsregion);
	gilinstreq.setOpsaz_zone(opszone);
	gilinstreq.setOpsflavor_id(opsflavorid);
	gilinstreq.setOpsimage_id(opsimgid);
	gilinstreq.setOpsinst_name(opsinstname);
	gilinstreq.setOpskey_name(opeskeyname);
	gilinstreq.setOpsnetwork_id(opsnetwrkid);
	gilinstreq.setOpssec_group("default");
	gilinstreq.setPuppet_Master_Hostname(pptmasterhostname);
	gilinstreq.setPuppet_Master_IP(pptmasterip);
	gilinstreq.setScm(scm);
	gilinstreq.setVm_custom_id(vmcustomid);
	
	gilinst.setGilir(gilinstreq);
	
	GetIsapLaunchinstanceResponse launchinstresponse = icwstub.getIsapLaunchinstance(gilinst);
	
	GetIsapLaunchInstanceResp launchinstresp = launchinstresponse.get_return();
	System.out.println("return code is "+launchinstresp.getOpsINstanceId());
	System.out.println("return code is "+launchinstresp.getIsapRetunCode());
	System.out.println("Message is "+launchinstresp.getIsapLongMessage());
	
	return launchinstresp;
}





public String getImageDetails(String imageid, String opsusername,
		String opsKeyEndpoint,String opsInterface, String Opsregion, String opspassword,
		String opstenantname) throws RemoteException

{
	
	
	IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();

	GetOpenstackImageDetailsReq imgdetreq = new GetOpenstackImageDetailsReq();
	GetOpenstackImageDetails imgdet=new GetOpenstackImageDetails();

	imgdetreq.setImageid(imageid);
	imgdetreq.setOpesusername(opsusername);
	imgdetreq.setOpsKeyEndpoint(opsKeyEndpoint);
	imgdetreq.setOps_interface(opsInterface);
	imgdetreq.setOps_region(Opsregion);
	imgdetreq.setOpspassword(opspassword);
	imgdetreq.setOpstenantname(opstenantname);
	
	imgdet.setGoidr(imgdetreq);

	GetOpenstackImageDetailsResponse imgedetresponse = icwstub.getOpenstackImageDetails(imgdet);
			
	GetOpenstackImageDetailsResp imgdetresp = imgedetresponse.get_return();
	System.out.println(imgedetresponse.get_return().getImagename());

	return imgdetresp.getImagename();

}




public GetOpenstackFlavorResp getFlavorDetails(String flavorid,String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
	
	IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
	
	GetOpenstackFlavorDetailsReq flvreq= new GetOpenstackFlavorDetailsReq();
	
	GetOpenstackFlavorDetails flavdet = new GetOpenstackFlavorDetails();
	
	flvreq.setFlavor_id(flavorid);
	flvreq.setOpesusername(opsusername);
	flvreq.setOps_interface(opsInterface);
	flvreq.setOps_region(Opsregion);
	flvreq.setOpsKeyEndpoint(opsKeyEndpoint);
	flvreq.setOpspassword(opspassword);
	flvreq.setOpstenantname(opstenantname);
	flavdet.setGofr(flvreq);

	GetOpenstackFlavorDetailsResponse flvresponse=icwstub.getOpenstackFlavorDetails(flavdet);
	
	GetOpenstackFlavorResp response=flvresponse.get_return();
	System.out.println(response.getReturnCode());
	
	return response;
	
}



public String getSecurityGroupName(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname,String secgrpid) throws RemoteException{
	
	IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
	
	GetOpenstackSecurityGroupDetailsReq secgrpdet = new GetOpenstackSecurityGroupDetailsReq();
	
	GetOpenstackSecurityGroupDetails secreq = new GetOpenstackSecurityGroupDetails();
	
	
	
	secgrpdet.setOpesusername(opsusername);
	secgrpdet.setOps_interface(opsInterface);
	secgrpdet.setOps_region(Opsregion);
	secgrpdet.setOpsKeyEndpoint(opsKeyEndpoint);
	secgrpdet.setOpspassword(opspassword);
	secgrpdet.setOpstenantname(opstenantname);
	secgrpdet.setSec_gp_id(secgrpid);
	secreq.setGosgr(secgrpdet);

	GetOpenstackSecurityGroupDetailsResponse secdetesponse=icwstub.getOpenstackSecurityGroupDetails(secreq);
	
	GetOpenstackSecurityGroupDetailsResp secresponse=secdetesponse.get_return();
	System.out.println(secresponse.getSecgrpname());
	
	
	return secresponse.getSecgrpname();
	
}




public int terminateInstance(String instance_id, String opesusername,
		String opsKeyEndpoint,String opsInterface, String Opsregion, String opspassword,
		String opstenantname) throws RemoteException {
	
	IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();
	TerminateOpensInstanceReq terinsreq = new TerminateOpensInstanceReq();
	ISAPTerminateOpensInstance terins=new ISAPTerminateOpensInstance();

	terinsreq.setInstance_id(instance_id);
	terinsreq.setOpesusername(opesusername);
	terinsreq.setOpsKeyEndpoint(opsKeyEndpoint);
	terinsreq.setOps_interface(opsInterface);
	terinsreq.setOps_region(Opsregion);
	terinsreq.setOpspassword(opspassword);
	terinsreq.setOpstenantname(opstenantname);
	terins.setTpir(terinsreq);

	ISAPTerminateOpensInstanceResponse imgedetresponse = icwstub.iSAPTerminateOpensInstance(terins);

	System.out.println("-----------"+imgedetresponse.get_return());
		return imgedetresponse.get_return();

}










	public static void main(String args[]) throws RemoteException
	{
		

		final  Logger LOGGER = Logger
				.getLogger(LaunchController.class.getName());

		
	    String opsusername="admin";
		String opsKeyEndpoint="http://192.168.175.153:35357";
		String opsInterface="public";
		String Opsregion="RegionOne";
		String opspassword="synlab123$";
		String opstenantname="ISAP-STGMA";
		String awsUsername="admin";
		String cloudType="openstack";
		String opsUserid="ravi";
		String flavorid="1";
		String secgrpid="796e5ade-a53d-46d7-a2e7-5c2abd0151d6";
		String keypairName = "test1230933"+new java.util.Date().getSeconds();
		String secGpId="77fbdc7c-57f8-47c8-9681-6013cfada19c";

        String imgid="5be78839-e753-410d-84ee-6d19d3b7f61d";
        String instance_id ="979d3a29-de15-4eb4-af53-c662b16c1fda";
		String userid="1";
        
       /* Only for networks
        * 
		String network_Opsregion="RegionOne";
     	String network_opsInterface="public";
     	*/
		
		
		OpenStackServiceA2 a2=new OpenStackServiceA2();
		//a2.removeFloatinIp("aec4a3bf-345a-420f-a21b-5c547230848f", opsInterface, opsKeyEndpoint, opspassword, Opsregion, opstenantname, opsusername);
		//a2.createOpenstackKeypair(keypairName, opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		
		
		/* ****************Completed********************/
	    //a2.getListOpenstackFlavors(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		//a2.getListOpenstackSecurityGroups(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		//a2.getListOpenstackImages(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
	    // a2.getListOpenstackNetworks(opsusername, opsKeyEndpoint, network_opsInterface, network_Opsregion, opspassword, opstenantname);
		//a2.getOpenstackFlavorDetails(flavorid,opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		
		/*SecurityGroup methods*/
		// a2.getSecurityGroupDetails(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname, secGpId);
	    //a2.getListOpenstackSecurityGroups(opsusername, opsKeyEndpoint, network_opsInterface, network_Opsregion, opspassword, opstenantname);
		//a2.getSecurityGroupName(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname, secGpId);
		
		//a2.getFlavorDetails(flavorid, opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		
		//a2.getImageDetails(imgid, opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
	
		//a2.terminateInstance(instance_id, opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		
		//a2.getIsapCloudAuthentication(awsUsername, cloudType, opsKeyEndpoint, opspassword, opstenantname, userid, opsusername);
		
		/* ****************Need to work********************/
		
		
		
		//a2.getListOpenStackKeyPairs(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);


   
	   //a2.getOpenstackSecurityGroupDetails(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname, secgrpid);
	
		
		
		
		//String pool = "Pune_Public_Network";
		//a2.allocateOpenstackFloatingIp(opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname, opsusername, pool);
		//a2.terminateInstance("ravi", "admin", "http://192.168.175.153:35357", "public","RegionOne", "synlab123$", "ISAP-STGMA");
		
		//String floatingIpAdd = "192.168.102.153";
		//String serverId = "979d3a29-de15-4eb4-af53-c662b16c1fda";
		//a2.addOpenstackFloatingIptoInstance(floatingIpAdd, opsInterface, opsKeyEndpoint, Opsregion, opspassword, opstenantname, opsusername, pool, serverId);
		//LOGGER.info("Before launching :");
	//GetIsapLaunchInstanceResp gis=a2.getOpenStackLaunchinstance(1, "openstack", "http://192.168.175.153:35357", "synlab123$", "ISAP-STGMA", "20", "admin", "public", "RegionOne", "STG-CHN-AZ01", "2", "5712f12c-d3da-4eac-a794-73ddce14a90e", "Mahi", "admin", "03d27216-3f30-46f6-b051-c91a0dc83ea0", "default", "puppetmst", "192.168.175.204", 1, 20);
		//LOGGER.info("After launching LongMessage :"+gis.getIsapLongMessage());5be78839-e753-410d-84ee-6d19d3b7f61d
		//LOGGER.info("After launching Message :"+gis.getIsapMessage());
		
		
	}
	
	
	public GetOpenstackHypervisorDetailsResp getHypervisorDetails(
			String opsusername, String opsKeyEndpoint, String opsInterface,
			String Opsregion, String opspassword, String opstenantname)
			throws RemoteException {

		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();

		GetHypervisorDetails hypdet = new GetHypervisorDetails();

		GetOpenstackHypervisorDetailsReq hypdetreq = new GetOpenstackHypervisorDetailsReq();

		hypdetreq.setOpesusername(opsusername);
		hypdetreq.setOps_interface(opsInterface);
		hypdetreq.setOpsKeyEndpoint(opsKeyEndpoint);
		hypdetreq.setOps_region(Opsregion);
		hypdetreq.setOpspassword(opspassword);
		hypdetreq.setOpstenantname(opstenantname);

		hypdet.setGhdr(hypdetreq);

		GetHypervisorDetailsResponse hypdetresponse = icwstub
				.getHypervisorDetails(hypdet);

		GetOpenstackHypervisorDetailsResp hypdetresp = hypdetresponse
				.get_return();
		System.out.println(hypdetresponse.get_return().getIsapMessage());

		return hypdetresp;

	}
	
	
	
}
