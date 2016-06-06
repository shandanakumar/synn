/*package com.syntel.isap.provisioning.soap;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

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
import com.isap.core.IsapCoreWebservicesStub.ArrayOfStringE;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackKeypair;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackKeypairReq;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackKeypairResponse;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackTenant;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackTenantResp;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackTenantResponse;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstackkeypairResp;
import com.isap.core.IsapCoreWebservicesStub.CreateOpenstacktenantReq;
import com.isap.core.IsapCoreWebservicesStub.DeallocateOpenstackFloatingIp;
import com.isap.core.IsapCoreWebservicesStub.DeallocateOpenstackFloatingIpReq;
import com.isap.core.IsapCoreWebservicesStub.DeallocateOpenstackFloatingIpResponse;
import com.isap.core.IsapCoreWebservicesStub.GetEndpointsRequest;
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
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackFloatingIpResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackHypervisorDetailsReq;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackHypervisorDetailsResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackImageDetails;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackImageDetailsReq;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackImageDetailsResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackImageDetailsResponse;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackProjectUsage;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackProjectUsageResponse;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackQuotaDetails;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackQuotaDetailsResponse;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackQuotaReq;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackQuotaResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackSecurityGroupDetails;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackSecurityGroupDetailsReq;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackSecurityGroupDetailsResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackSecurityGroupDetailsResponse;
import com.isap.core.IsapCoreWebservicesStub.GetServiceEndpoint;
import com.isap.core.IsapCoreWebservicesStub.GetServiceEndpointResponse;
import com.isap.core.IsapCoreWebservicesStub.ISAPTerminateOpensInstance;
import com.isap.core.IsapCoreWebservicesStub.ISAPTerminateOpensInstanceResponse;
import com.isap.core.IsapCoreWebservicesStub.ISAP_UpdateDatabase;
import com.isap.core.IsapCoreWebservicesStub.ISAP_UpdateDatabaseResponse;
import com.isap.core.IsapCoreWebservicesStub.ISAP_UpdateDatabase_BeSpoke;
import com.isap.core.IsapCoreWebservicesStub.ISAP_UpdateDatabase_BeSpokeResponse;
import com.isap.core.IsapCoreWebservicesStub.ISAP_UpdateInstStatusBespoke;
import com.isap.core.IsapCoreWebservicesStub.ISAP_UpdateInstStatusBespokeResponse;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFlavors;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFlavorsReq;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFlavorsResp;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFlavorsResponse;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFloatingIpPools;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFloatingIpPoolsResponse;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFloatingIps;
import com.isap.core.IsapCoreWebservicesStub.ListOpenstackFloatingIpsResponse;
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
import com.isap.core.IsapCoreWebservicesStub.OpenstackBasicReq;
import com.isap.core.IsapCoreWebservicesStub.OpenstackBasicResp;
import com.isap.core.IsapCoreWebservicesStub.RemoveOpenstackFloatingIpReq;
import com.isap.core.IsapCoreWebservicesStub.RemoveOpenstackFloatingIpResp;
import com.isap.core.IsapCoreWebservicesStub.RemoveOpenstackFloatingIpRespE;
import com.isap.core.IsapCoreWebservicesStub.RemoveOpenstackFloatingIpRespResponse;
import com.isap.core.IsapCoreWebservicesStub.Rules;
import com.isap.core.IsapCoreWebservicesStub.TerminateOpensInstanceReq;

public class OpenStackServiceA3 {

	

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
		System.out.println(keypairs.size());
		System.out.println("Array 1:"+tempkeypairsWrapperArray[0].getArray()[0]);
		
		return keypairs;
	}
	
	
	
	
public ArrayList<Map<String, String>> getFlavors(String opsusername,String opsKeyEndpoint,String ops_interface,String ops_region,String opspassword,String opstenantname) throws RemoteException{
	
		
		IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
		ListOpenstackFlavors flavors=new ListOpenstackFlavors();
		ListOpenstackFlavorsReq flavorsReq=new ListOpenstackFlavorsReq();
		flavorsReq.setOpesusername(opsusername);
		flavorsReq.setOps_interface(ops_interface);
		flavorsReq.setOps_region(ops_region);
		flavorsReq.setOpsKeyEndpoint(opsKeyEndpoint);
		flavorsReq.setOpspassword(opspassword);
		flavorsReq.setOpstenantname(opstenantname);
		flavors.setLof(flavorsReq);
	
		ListOpenstackFlavorsResponse flavorerespwrap=icwstub.listOpenstackFlavors(flavors);
		ListOpenstackFlavorsResp flavresp= flavorerespwrap.get_return();
		
       ArrayOfString[] tempFlavorWrapperArray=flavresp.getFlavors();
		
		Map<String, String> flavkeypair=new HashMap();
		ArrayList<Map<String, String>> flavlist=new ArrayList<Map<String,String>>();
		
		for(int i=0;i<tempFlavorWrapperArray.length;i++)
		{
			String keypairWrapperArray[]=tempFlavorWrapperArray[i].getArray();
			flavkeypair.put("keyname", keypairWrapperArray[0]);
			flavkeypair.put("value", keypairWrapperArray[1]);
			System.out.println(keypairWrapperArray[0]);
			System.out.println(keypairWrapperArray[1]);
			flavlist.add(flavkeypair);
		}
		System.out.println(flavlist.size());
		return flavlist;
		
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
	
	
	
	public LinkedHashMap<String, String> getSecurityGroups(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
		
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
		
       ArrayOfString[] tempSecgrpWrapperArray=secresp.getSecurityGroups();
		
       
   	System.out.println("temparray========="+tempSecgrpWrapperArray);
      LinkedHashMap<String, String> lhmnsecurity = new LinkedHashMap<String, String>();
   	
   	for(int i=0;i<tempSecgrpWrapperArray.length;i++)
   	{
   		String keypairWrapperArray[]=tempSecgrpWrapperArray[i].getArray();
   		lhmnsecurity.put("keyname", keypairWrapperArray[0]);
   		lhmnsecurity.put("value", keypairWrapperArray[1]);
   		System.out.println(keypairWrapperArray[0]);
   		System.out.println(keypairWrapperArray[1]);
   		
   	}
   	System.out.println("==============="+lhmnsecurity.size());
   	return lhmnsecurity;
		
	}
	
	///need to work===========================================================================================================
   public List<String> getSecurityGroupDetails(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname,String secGpId) throws RemoteException{
		
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
		
       
		
		Map<String, String> seckeypair=new HashMap();
		ArrayList<Map<String, String>> seclist=new ArrayList<Map<String,String>>();
		
		for(int i=0;i<tempSecgrpWrapperArray.length;i++)
		{
			String keypairWrapperArray[]=tempSecgrpWrapperArray[i].getArray().;
			seckeypair.put("keyname", keypairWrapperArray[0]);
			seckeypair.put("value", keypairWrapperArray[1]);
			System.out.println(keypairWrapperArray[0]);
			System.out.println(keypairWrapperArray[1]);
			seclist.add(seckeypair);
			
		}
		System.out.println("==============="+seclist.size());
		return null;
		
	}
	
//=====================================================================================================================//	
	
	
	

	
	public GetOpenstackSecurityGroupDetailsResp getSecurityGroupName(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname,String secgrpid) throws RemoteException{
		
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
		
		
		return secresponse;
		
	}
	
	
	
	
	
	
	
	

public ArrayList<Map<String, String>> getImages(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
	
	
	
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
	
   ArrayOfString[] tempimgWrapperArray=imgresp.getImages();
	
	Map<String, String> resimages=new HashMap();
	ArrayList<Map<String, String>> reslist=new ArrayList<Map<String,String>>();
	
	for(int i=0;i<tempimgWrapperArray.length;i++)
	{
		String keypairWrapperArray[]=tempimgWrapperArray[i].getArray();
		resimages.put("keyname", keypairWrapperArray[0]);
		resimages.put("value", keypairWrapperArray[1]);
		System.out.println(keypairWrapperArray[0]);
		System.out.println(keypairWrapperArray[1]);
		reslist.add(resimages);
	}
	System.out.println("==============="+reslist.size());
	return reslist;
	
}




public CreateOpenstackkeypairResp createOpenstackKeypair(String keypairName, String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
		
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
		
		CreateOpenstackkeypairResp response=keypairresp.get_return();
		
		System.out.println(keypairresp.get_return().getKey_pair_name());
		
		return response;
		
	
		
	}









public LinkedHashMap<String, String> getNetworks(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
	
	
	
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
	
   ArrayOfString[] tempNetWrapperArray=netresp.getNetworks();
	System.out.println("temparray========="+tempNetWrapperArray);
   LinkedHashMap<String, String> lhmnetworks = new LinkedHashMap<String, String>();
	
	for(int i=0;i<tempNetWrapperArray.length;i++)
	{
		String keypairWrapperArray[]=tempNetWrapperArray[i].getArray();
		lhmnetworks.put("keyname", keypairWrapperArray[0]);
		lhmnetworks.put("value", keypairWrapperArray[1]);
		System.out.println(keypairWrapperArray[0]);
		System.out.println(keypairWrapperArray[1]);
		
	}
	System.out.println("==============="+lhmnetworks.size());
	return lhmnetworks;
	
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









public GetOpenstackQuotaResp getOpenstackQuotaDetails(String opsusername,String opsKeyEndpoint,String opsInterface,String Opsregion,String opspassword,String opstenantname) throws RemoteException{
	
	IsapCoreWebservicesStub icwstub=new IsapCoreWebservicesStub();
	
	GetOpenstackQuotaDetails quotadet = new GetOpenstackQuotaDetails();
	GetOpenstackQuotaReq quoreq = new GetOpenstackQuotaReq();
	
	
	
	quoreq.setOpesusername(opsusername);
	quoreq.setOps_interface(opsInterface);
	quoreq.setOpsKeyEndpoint(opsKeyEndpoint);
	quoreq.setOps_region(Opsregion);
	quoreq.setOpspassword(opspassword);
	quoreq.setOpstenantname(opstenantname);
	
	
	quotadet.setGoqr(quoreq);
	
	
	GetOpenstackQuotaDetailsResponse quotadetresponse=icwstub.getOpenstackQuotaDetails(quotadet);
	GetOpenstackQuotaResp quotadetresp =quotadetresponse.get_return();
	System.out.println(quotadetresponse.get_return().getIsapMessage());
	
	return quotadetresp;
	
	
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



	public CreateOpenstackTenantResp createOpenstackTenant(
			String opsKeyEndpoint, String opspassword, String opstenantname,
			String opsusername, String tenant_name) throws RemoteException {

		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();

		CreateOpenstackTenant hypdet = new CreateOpenstackTenant();
		CreateOpenstacktenantReq hypdetreq = new CreateOpenstacktenantReq();

		hypdetreq.setOpsKeyEndpoint(opsKeyEndpoint);

		hypdetreq.setOpspassword(opspassword);
		hypdetreq.setOpstenantname(opstenantname);
		hypdetreq.setOpsusername(opsusername);
		hypdetreq.setTenant_name(tenant_name);

		hypdet.setCotr(hypdetreq);

		CreateOpenstackTenantResponse createopnsresponse = icwstub
				.createOpenstackTenant(hypdet);
		CreateOpenstackTenantResp crtopenstacktresp = createopnsresponse
				.get_return();
		System.out.println(createopnsresponse.get_return().getIsapMessage());

		return crtopenstacktresp;

	}

	inputs needed ---------------------------------------------------------
	
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

		return imgdetresp.getLongMessage();

	}
	
	
	
	public String getSecurityGroupName(String openStackUserName,
			String opsKeyEndpoint, String opsInterface, String opsRegion,
			String opspassword, String opstenantname, String secGpId){
		
		
		return null;
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
	
	


	//=====================================new service methods============================================//

	

	public GetServiceEndpointResponse getServiceEndpoint(String opsInterface, String opsKeyEndpoint,
			String opspassword, String Opsregion, String service,
			String opstenantname, String opesusername
			  
			) throws RemoteException {
		
		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();

		GetServiceEndpoint serv = new GetServiceEndpoint();

		GetEndpointsRequest servicereq = new GetEndpointsRequest();

		servicereq.setOpsInterface(opsInterface);
		servicereq.setOpsKeystoneURL(opsKeyEndpoint);
		servicereq.setOpsPassword(opspassword);
		servicereq.setOpsRegion(Opsregion);
		servicereq.setOpsService(service);
		;
		servicereq.setOpsTenantName(opstenantname);
		servicereq.setOpsUsername(opesusername);
		serv.setGsep(servicereq);

		GetServiceEndpointResponse imgedetresponse = icwstub
				.getServiceEndpoint(serv);

		System.out.println("-----------" + imgedetresponse.get_return());

		return imgedetresponse;

	}
	
	
	
	

	//  for below method need inputs to test============================================
	public GetOpenstackQuotaResp getProjectUsage(String opesusername,String opsKeyEndpoint,String opsInterface, 
			 String Opsregion,String opspassword, String opstenantname) throws RemoteException {
		
		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();

		GetOpenstackProjectUsage proj = new GetOpenstackProjectUsage();
		GetOpenstackQuotaReq prjreq = new GetOpenstackQuotaReq();
		
		prjreq.setOpesusername(opesusername);
		prjreq.setOpsKeyEndpoint(opsKeyEndpoint);
		prjreq.setOps_interface(opsInterface);
		prjreq.setOps_region(Opsregion);
		prjreq.setOpspassword(opspassword);
		prjreq.setOpstenantname(opstenantname);
		
		proj.setGoqr(prjreq);
		
		GetOpenstackProjectUsageResponse prjresponse = icwstub.getOpenstackProjectUsage(proj);
		GetOpenstackQuotaResp prjctresp = prjresponse.get_return();
		System.out.println("-----------" + prjresponse.get_return());

		return prjctresp;

	}
	
	
	//below method need to test with inputs===================================================
	public int IsapUpdateDatabase(int customid,String opensid,String ipaddress, 
			 String status) throws RemoteException {
		
		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();

		ISAP_UpdateDatabase updb = new ISAP_UpdateDatabase();
		
		updb.setVm_custom_id(customid);
		updb.setVm_opens_id(opensid);
		updb.setIp_address(ipaddress);
		updb.setStatus(status);
		
		ISAP_UpdateDatabaseResponse updbresp = icwstub.iSAP_UpdateDatabase(updb);
		
		
		
		
		System.out.println("-----------" + updbresp.get_return());

		return updbresp.get_return();

	}
	
	
	
	
	
	//below method need to test with inputs===================================================
	public int ISAPUpdateDatabaseBeSpoke(int customid,String opensid,String ipaddress, 
			 String status,String pubipaddress) throws RemoteException {

		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();

		ISAP_UpdateDatabase_BeSpoke updbspk = new ISAP_UpdateDatabase_BeSpoke();

		updbspk.setVm_custom_id(customid);
		updbspk.setVm_opens_id(opensid);
		updbspk.setIp_address(ipaddress);
		updbspk.setStatus(status);
		updbspk.setPublic_ip_addr(pubipaddress);

		ISAP_UpdateDatabase_BeSpokeResponse updbspkresp = icwstub
				.iSAP_UpdateDatabase_BeSpoke(updbspk);

		System.out.println("-----------" + updbspkresp.get_return());

		return updbspkresp.get_return();

	}
	
	
	
	//below method need to test with inputs===================================================
	public int ISAPUpdateInstStatusBespoke(String instancename, 
			 String status) throws RemoteException {

		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();

		ISAP_UpdateInstStatusBespoke updbspkinst = new ISAP_UpdateInstStatusBespoke();

		updbspkinst.setInst_name(instancename);
		updbspkinst.setStatus(status);

		ISAP_UpdateInstStatusBespokeResponse updbspkinstresp = icwstub
				.iSAP_UpdateInstStatusBespoke(updbspkinst);

		System.out.println("-----------" + updbspkinstresp.get_return());

		return updbspkinstresp.get_return();

	}
	
	
	
	
	
	
	
	
	public GetOpenstackFloatingIpResp listopsloatingIpPools(String opsKeyEndpoint,String opsInterface, 
			 String Opsregion,String opspassword, String opstenantname,String opesusername) throws RemoteException{
		
		
		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();
		
		ListOpenstackFloatingIpPools flotippols = new ListOpenstackFloatingIpPools();
		
		OpenstackBasicReq flotipreq	= new OpenstackBasicReq();
		
		flotipreq.setOpsKeyEndpoint(opsKeyEndpoint);
		flotipreq.setOps_interface(opsInterface);
		flotipreq.setOps_region(Opsregion);
		flotipreq.setOpspassword(opspassword);
		flotipreq.setOpstenantname(opstenantname);
		flotipreq.setOpsusername(opesusername);
		flotippols.setReq(flotipreq);
		
		ListOpenstackFloatingIpPoolsResponse listflotpool = icwstub.listOpenstackFloatingIpPools(flotippols);
		
		GetOpenstackFloatingIpResp listflotpoolresp = listflotpool.get_return();
		
		
		
		System.out.println("response issssssssssssss "+listflotpool.get_return().getFloatIP());
		
		
		return listflotpoolresp;
	}
	
	
	
	
	
	
	
	public GetOpenstackFloatingIpResp listOpstackFloatingIps(String opsKeyEndpoint,String opsInterface, 
			 String Opsregion,String opspassword, String opstenantname,String opesusername) throws RemoteException{
		
		
		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();
		
		ListOpenstackFloatingIps flotips = new ListOpenstackFloatingIps();
		
		OpenstackBasicReq flotipsreq	= new OpenstackBasicReq();
		
		flotipsreq.setOpsKeyEndpoint(opsKeyEndpoint);
		flotipsreq.setOps_interface(opsInterface);
		flotipsreq.setOps_region(Opsregion);
		flotipsreq.setOpspassword(opspassword);
		flotipsreq.setOpstenantname(opstenantname);
		flotipsreq.setOpsusername(opesusername);
		flotips.setReq(flotipsreq);
		
		ListOpenstackFloatingIpsResponse listflotips = icwstub.listOpenstackFloatingIps(flotips);
		
		GetOpenstackFloatingIpResp listflotipsresp = listflotips.get_return();
		
	
		
		System.out.println("response issssssssssssss "+listflotipsresp.getMessage());
		
		
		  tempNetWrapperArray = listflotipsresp.getFloatIP();
			System.out.println("temparray========="+tempNetWrapperArray);
		   LinkedHashMap<String, String> floatiplist = 
			
			for(int i=0;i<tempNetWrapperArray.length;i++)
			{
				String keypairWrapperArray[]=tempNetWrapperArray[i].getArray();
				floatiplist.put("keyname", keypairWrapperArray[0]);
				floatiplist.put("value", keypairWrapperArray[1]);
				System.out.println(keypairWrapperArray[0]);
				System.out.println(keypairWrapperArray[1]);
				
			}
			System.out.println("==============="+lhmnetworks.size());
			return lhmnetworks;
		
		
		
		return listflotipsresp;
	}
	
	
	
	
	public OpenstackBasicResp deallocateOpstackFloatingIp(String id,String opsInterface, 
			String opsKeyEndpoint,String Opsregion,String opspassword, String opstenantname,String opesusername) throws RemoteException{
		
		
		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();
		
		DeallocateOpenstackFloatingIp deallotip = new DeallocateOpenstackFloatingIp();
		
		DeallocateOpenstackFloatingIpReq deallotipreq	= new DeallocateOpenstackFloatingIpReq();
		
		deallotipreq.setId(id);
		deallotipreq.setOps_interface(opsInterface);
		deallotipreq.setOps_key_EP(opsKeyEndpoint);
		deallotipreq.setOps_region(Opsregion);
		deallotipreq.setOpspassword(opspassword);
		deallotipreq.setOpstenantname(opstenantname);
		deallotipreq.setOpsusername(opesusername);
		deallotip.setRequest(deallotipreq);
		
		DeallocateOpenstackFloatingIpResponse deallotipresponse = icwstub.deallocateOpenstackFloatingIp(deallotip);
		
		OpenstackBasicResp deallotipresp = deallotipresponse.get_return();
		
		
		System.out.println("response issssssssssssss "+deallotipresponse.get_return().getReturnCode());
		
		
		return deallotipresp;
	}
	

	
	public GetIsapLaunchInstanceResp getOpenStackLaunchinstance(int bspoke, String cloudtype,
			String opsKeyEndpoint, String opspassword, String opstenantname,
			String opesusrid, String opesusername, String opsInterface,
			String Opsregion, String opszone, String opsflavorid,
			String opsimgid, String opsinstname, String opeskeyname,
			String opsnetwrkid, String opssecgrp, String pptmasterhostname,
			String pptmasterip, int scm, int vmcustomid) throws RemoteException {
		
		IsapCoreWebservicesStub icwstub = new IsapCoreWebservicesStub();
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
		gilinstreq.setOpssec_group(opssecgrp);
		gilinstreq.setPuppet_Master_Hostname(pptmasterhostname);
		gilinstreq.setPuppet_Master_IP(pptmasterip);
		gilinstreq.setScm(scm);
		gilinstreq.setVm_custom_id(vmcustomid);
		
		gilinst.setGilir(gilinstreq);
		
		GetIsapLaunchinstanceResponse launchinstresponse = icwstub.getIsapLaunchinstance(gilinst);
		GetIsapLaunchInstanceResp launchinstresp = launchinstresponse.get_return();
		
		System.out.println("return code is "+launchinstresp.getIsapRetunCode());
		
		return launchinstresp;
	}
	
	
	
	
	
	
	
	public static void main(String args[]) throws RemoteException
	{
	    String opsusername="admin";
	    //String opesusername="admin";
		String opsKeyEndpoint="http://192.168.175.153:35357";
		String opsInterface="admin";
		String Opsregion="RegionPune";
		String opspassword="synlab123$";
		String opstenantname="ISAP-STGMA";
		String awsUsername="admin";
		String cloudType="openstack";
		String opsUserid="ravi";
		String flavorid="1";
		String secgrpid="796e5ade-a53d-46d7-a2e7-5c2abd0151d6";
		String keypairName = "test12309";
		
		OpenStackServiceA2 a2=new OpenStackServiceA2();
		
		//a2.getListOpenstackNetworks(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		
		//a2.getListOpenStackKeyPairs(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		//a2.getListOpenstackFlavors(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		//a2.getOpenstackFlavorDetails(flavorid,opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
	    //a2.getIsapCloudAuthentication(awsUsername,cloudType,opsKeyEndpoint,opspassword, opstenantname,opsUserid,opsusername);
       //a2.getListOpenstackSecurityGroups(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
	   //a2.getOpenstackSecurityGroupDetails(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname, secgrpid);
		//a2.getListOpenstackImages(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		//a2.createOpenstackKeypair(keypairName, opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		
		//String pool = "Pune_Public_Network";
		//a2.allocateOpenstackFloatingIp(opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname, opsusername, pool);
		
		//String floatingIpAdd = "192.168.102.153";
		//String serverId = "979d3a29-de15-4eb4-af53-c662b16c1fda";
		//a2.addOpenstackFloatingIptoInstance(floatingIpAdd, opsInterface, opsKeyEndpoint, Opsregion, opspassword, opstenantname, opsusername, pool, serverId);
		//a2.getOpenstackQuotaDetails(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		//a2.getHypervisorDetails(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		//String tenant_name="ravi";
		//a2.createOpenstackTenant(opsKeyEndpoint, opspassword, opstenantname, opsusername, tenant_name);
		
		//String instance_id ="979d3a29-de15-4eb4-af53-c662b16c1fda";
		//a2.terminateInstance(instance_id, opesusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		//String Opsregion="RegionOne";
		//String opsInterface="public";
		//a2.getNetworks(opsusername, opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname);
		
		//==========new methods=================//
		//String service="nova";
		//String opesusername = "admin";
		//a2.getServiceEndpoint(opsInterface, opsKeyEndpoint, opspassword, Opsregion, service, opstenantname, opesusername);
		//String opesusername = "admin";
		//a2.listopsloatingIpPools(opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname, opesusername);
		//a2.listOpstackFloatingIps(opsKeyEndpoint, opsInterface, Opsregion, opspassword, opstenantname, opesusername);
		//String id = "979d3a29-de15-4eb4-af53-c662b16c1fda";
		//a2.deallocateOpstackFloatingIp(id, opsInterface, opsKeyEndpoint, Opsregion, opspassword, opstenantname, opesusername);
		
		a2.getIsapLaunchinstance(bspoke, cloudtype, opsKeyEndpoint, opspassword, opstenantname, opesusrid, opesusername, opsInterface, Opsregion, opszone, opsflavorid, opsimgid, opsinstname, opeskeyname, opsnetwrkid, opssecgrp, pptmasterhostname, pptmasterip, scm, vmcustomid);
	}
}
*/