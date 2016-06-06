package com.syntel.isap.provisioning.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syntel.isap.provisioning.bean.Monitor;
import com.syntel.isap.provisioning.bean.SCM;
import com.syntel.isap.provisioning.bean.Vdc;
import com.syntel.isap.provisioning.bean.VdcExt;
import com.syntel.isap.provisioning.bean.VdcList;
import com.syntel.isap.provisioning.constants.OpenStackCredentials;
import com.syntel.isap.provisioning.dao.IVdcDao;
import com.syntel.isap.provisioning.service.IVdcService;
import com.syntel.isap.provisioning.soap.OpenStackServiceA2;

@Service("registerService")
public class VdcServiceImpl implements IVdcService {
	
	@Autowired
	private IVdcDao registerDao;
		
	private OpenStackServiceA2 openStackServices = new OpenStackServiceA2();

	@Transactional
	public List<Vdc> getUserOpenstack() {
		List<VdcExt> openstackVdcExtDetails = new ArrayList<VdcExt>();
		List<Vdc> vdcDetails=new ArrayList<Vdc>();
		List<Vdc> vdcNewList=new ArrayList<Vdc>();
		vdcDetails=registerDao.getVdcList();
		for(Vdc vdc:vdcDetails){
			
			VdcList list=new VdcList();
			
			int vdcId=vdc.getVdc_id();			
			openstackVdcExtDetails=registerDao.getVdcExtDetailsById(vdcId);
			for(VdcExt VdcExt:openstackVdcExtDetails){				
						if(VdcExt.getParam_name().equalsIgnoreCase("adminTenant")){							
							list.setAdminTenant(VdcExt.getParam_val());
						}
				        else if(VdcExt.getParam_name().equalsIgnoreCase("description")){				        					       				     
				        	list.setDescription(VdcExt.getParam_val());				      
				        }
						else if(VdcExt.getParam_name().equalsIgnoreCase("controllerEndpoint")){
							list.setControllerEndpoint(VdcExt.getParam_val());
						}
						else if(VdcExt.getParam_name().equalsIgnoreCase("keystoneEndpoint")){
							list.setKeystoneEndpoint(VdcExt.getParam_val());
						}
						else if(VdcExt.getParam_name().equalsIgnoreCase("subnetAddress")){
							list.setSubnetAddress(VdcExt.getParam_val());
						}
						else if(VdcExt.getParam_name().equalsIgnoreCase("version")){
							list.setVersion(VdcExt.getParam_val());
						}
			     }
			vdc.setVdcExt(openstackVdcExtDetails);
			vdc.setVdclist(list);
			System.out.println("openstack vdc.VdcExt.getParam_name()"+vdc.getDescription());
			vdcNewList.add(vdc);
		    }
		return vdcDetails;		
	}	

	@Transactional
	public List<Vdc> getAzureVdcList() {
		List<VdcExt> azureVdcExtDetails = new ArrayList<VdcExt>();
		List<Vdc> azureVdcDetails=new ArrayList<Vdc>();
		List<Vdc> vdcNewList=new ArrayList<Vdc>();
		azureVdcDetails=registerDao.getAzureVdcList();
			
		for(Vdc vdc:azureVdcDetails){
			
			VdcList list=new VdcList();
			
			int vdcId=vdc.getVdc_id();	
			System.out.println("In getAzureVdcList vdc Id"+vdcId);
			azureVdcExtDetails=registerDao.getVdcExtDetailsById(vdcId);
			for(VdcExt VdcExt:azureVdcExtDetails){		
				
				System.out.println("VdcExt.getParam_name()"+VdcExt.getParam_name());
						if(VdcExt.getParam_name().equalsIgnoreCase("adminTenant")){							
							list.setAdminTenant(VdcExt.getParam_val());
						}
				        else if(VdcExt.getParam_name().equalsIgnoreCase("description")){				        					       				     
				        	list.setDescription(VdcExt.getParam_val());	
				        	System.out.println("list.VdcExt.getParam_name()"+list.getDescription());
				        }
						else if(VdcExt.getParam_name().equalsIgnoreCase("controllerEndpoint")){
							list.setControllerEndpoint(VdcExt.getParam_val());
						}
						else if(VdcExt.getParam_name().equalsIgnoreCase("keystoneEndpoint")){
							list.setKeystoneEndpoint(VdcExt.getParam_val());
						}
						else if(VdcExt.getParam_name().equalsIgnoreCase("subnetAddress")){
							list.setSubnetAddress(VdcExt.getParam_val());
						}
						else if(VdcExt.getParam_name().equalsIgnoreCase("version")){
							list.setVersion(VdcExt.getParam_val());
						}
			     }
			vdc.setVdcExt(azureVdcExtDetails);
			vdc.setAzureVdcList(list);
		
			System.out.println("vdc.List.getParam_name()"+list.getDescription());
			System.out.println("vdc.VdcExt.getParam_name()"+vdc.getDescription());
			vdcNewList.add(vdc);
		    }
		
		return azureVdcDetails;		
	}	
	
	
	
	@Transactional
	public void setOpenstack(Vdc openstackVdc) {
		List<VdcExt> openStackVdcExtDetails = new ArrayList<VdcExt>();
		int vdcId;
		registerDao.setOpenstack(openstackVdc);	
	    vdcId=openstackVdc.getVdc_id();	    	  
	    VdcExt adminTenant = new VdcExt("adminTenant",openstackVdc.getTenant(),vdcId);
	    VdcExt controllerEndpoint= new VdcExt("controllerEndpoint",openstackVdc.getController_endpoint(),vdcId);		
		VdcExt description= new VdcExt("description",openstackVdc.getDescription(),vdcId);
		VdcExt keystoneEndpoint= new VdcExt("keystoneEndpoint",openstackVdc.getKeystone_endpoint(),vdcId);
		VdcExt subnetAddress= new VdcExt("subnetAddress",openstackVdc.getSubnet_address(),vdcId);
		VdcExt version= new VdcExt("version",openstackVdc.getVersion(),vdcId);
		/*VdcExt region= new VdcExt("region",openstackVdc.getRegion(),vdcId);*/
		openStackVdcExtDetails.add(adminTenant);
		openStackVdcExtDetails.add(controllerEndpoint);
		openStackVdcExtDetails.add(description);
		openStackVdcExtDetails.add(keystoneEndpoint);	
		openStackVdcExtDetails.add(subnetAddress);
		openStackVdcExtDetails.add(version);
		/*openStackVdcExtDetails.add(region);*/
		for(VdcExt vdc:openStackVdcExtDetails)
			registerDao.addOpenStackVdcExt(vdc);
	    registerDao.setCred(openstackVdc); 
	}

	@Transactional
	public int getKeystone(String keystone_endpoint) {
		List<String> list = new ArrayList<String>();
		list= registerDao.getKeystone(keystone_endpoint);
		if(list.size()==0){
			 return 0;
		}
		else{
			 return 1;
		}
	}
	
	@Transactional
	public int getControllerEndpoint(String controllerEndpoint) {
		List<String> list = new ArrayList<String>();
		list= registerDao.getControllerEndpoint(controllerEndpoint);
		if(list.size()==0){
			 return 0;
		}
		else{
			 return 1;
		}
	}

	@Transactional
	public Vdc getDetails(Integer vdcId) {
		Vdc vdc=new Vdc();
		List<VdcExt> openstackVdcExtDetails = new ArrayList<VdcExt>();
		List<Vdc> vdcDetails=new ArrayList<Vdc>();
		vdcDetails=registerDao.getVdcEditList(vdcId);	
		openstackVdcExtDetails=registerDao.getVdcExtDetailsById(vdcId);
		for(VdcExt VdcExt:openstackVdcExtDetails){				
						if(VdcExt.getParam_name().equalsIgnoreCase("adminTenant")){														
							vdc.setTenant(VdcExt.getParam_val());
						}
				        else if(VdcExt.getParam_name().equalsIgnoreCase("description")){				        					       				     
				        	vdc.setDescription(VdcExt.getParam_val());				      
				        }
						else if(VdcExt.getParam_name().equalsIgnoreCase("controllerEndpoint")){
							vdc.setController_endpoint(VdcExt.getParam_val());
						}
						else if(VdcExt.getParam_name().equalsIgnoreCase("keystoneEndpoint")){
							vdc.setKeystone_endpoint(VdcExt.getParam_val());
						}
						else if(VdcExt.getParam_name().equalsIgnoreCase("subnetAddress")){
							vdc.setSubnet_address(VdcExt.getParam_val());
						}
		}		
		for(Vdc vdcNew : vdcDetails){
			vdc.setVdc_name(vdcNew.getVdc_name());
			vdc.setVdc_location(vdcNew.getVdc_location());
			vdc.setVdc_status(vdcNew.getVdc_status());			
		}	
		return vdc;
	}
	

	@Transactional
	public void updateOpenstack(Vdc openstackVdc) {
		List<VdcExt> openStackVdcExtDetails = new ArrayList<VdcExt>();
		int vdcId;
		registerDao.updateOpenstack(openstackVdc);	
	    vdcId=openstackVdc.getVdc_id();	      
	    /*VdcExt adminTenant = new VdcExt("adminTenant",openstack.getTenant(),vdc_id);*/
		VdcExt description= new VdcExt("description",openstackVdc.getDescription(),vdcId);
		/*openStackVdcExtDetails.add(adminTenant);*/
		openStackVdcExtDetails.add(description);
		for(VdcExt vdc:openStackVdcExtDetails)
		registerDao.updateOpenStackVdcExt(vdc);
	}

	@Transactional
	public void deleteOpenstack(Integer vdcId) {		
		registerDao.deleteOpenstack(vdcId);
	}

	@Transactional
	public List<SCM> getScm() {
		List<SCM> scm=new ArrayList<SCM>();
		List<Vdc> vdc=new ArrayList<Vdc>();
	    scm=registerDao.getscm();		
    	for (SCM scmTemp : scm){
    		int scmId;
    		scmId=scmTemp.getScm_id();
		    vdc=registerDao.getvdc(scmId);
		    scmTemp.setVdc(vdc);			
		}
		return scm;
	}

	@Transactional
	public void setscmpuppet(SCM scm) {
		 registerDao.setscmpuppet(scm);
	}

	@Transactional
	public void setscmchef(SCM scm) {
		 registerDao.setscmchef(scm);
	}

	@Transactional
	public SCM editScm(Integer scmId) {
		return  registerDao.editSCm(scmId);
	}
	/*@Transactional
	public void updateScm(SCM scm) {
		// TODO Auto-generated method stub
		registerdao.updateScm(scm);
	}*/

	@Transactional
	public void update(SCM scm) {
		registerDao.update(scm);
	}

	@Transactional
	public void deleteProject(Integer scmId) {
	    registerDao.deleteProject(scmId);			
	}
	
	@Transactional
	public int getScmEndpoint(String scmEndpoint) {
		List<String> list=new ArrayList<String>();
		list=registerDao.getScmEndpoint(scmEndpoint);
		if(list.size()==0){
		   return 0;
		}
		else{
		   return 1;
		}
	}

	@Transactional
	public List<Monitor> getMonitorDetails() {
		 List<Monitor> monitor=new ArrayList<Monitor>();
		 List<Vdc> vdc=new ArrayList<Vdc>();	
	     monitor= registerDao.getMonitorDetails(); 
     	 for(Monitor monitorTemp : monitor){
     		int monId;
     		monId=monitorTemp.getMon_id();
			vdc= registerDao.getMonitorVdc(monId);
		    monitorTemp.setVdc(vdc);			
		 }
     	return monitor;	
	}
	
	@Transactional
	public void deleteMonitor(Integer monId) {		
		registerDao.deleteMonitor(monId);
	}

	@Transactional
	public void updateMonitor(Monitor monitor) {
		registerDao.updateMonitor(monitor);
	}

	@Transactional
	public Monitor editMon(Integer monId) {
		return  registerDao.editMon(monId);
	}

	@Transactional
	public void setMonNagio(Monitor monitor) {		
		 registerDao.setMonNagio( monitor);
	}

	@Transactional
	public void setMonZabbix(Monitor monitor) {		
		 registerDao.setMonZabbix( monitor);
	}
	
	@Transactional
	public int getMonEndpoint(String monEndpoint) {
		List<String> list=new ArrayList<String>();
		list=registerDao.getMonEndpoint(monEndpoint);
    	if(list.size()==0 ){
		   return 0;
    	}
		else {
			return 1;
		}
	}

	@Transactional
	public int getVdcName(String vdcName) {
		List<String> list=new ArrayList<String>();
		list= registerDao.getVdcName(vdcName);
		if(list.size()==0 ){
			return 0;
	    }
		else {
			return 1;
		}
	}

	@Transactional
	public int getScmName(String scmName) {
		List<String> list=new ArrayList<String>();
		list= registerDao.getScmName(scmName);
		if(list.size()==0 ){
			 return 0;
		}
		else {
			 return 1;
		}	
	}

	@Transactional
	public int getMonName(String monName) {
		List<String> list=new ArrayList<String>();
		list= registerDao.getMonName(monName);
		if(list.size()==0 ){
		    return 0;
		}
		else {
			return 1;
		}
	}
	
	@Transactional
	public int getScmId(String scmName) {
		return registerDao.getScmid(scmName);
	}

	@Transactional
	public void setMap(Vdc openstackVdc) {
		registerDao.setMap(openstackVdc);
	}
	
	@Transactional
	public int getMonId(String monName) {
		return registerDao.getMonid(monName);
	}

	@Transactional
	public int checkConnection(String keystone_endpoint,String tenant,
			String user, String password)  {
		int status=0;
		try {
			
			status = openStackServices.getIsapCloudAuthentication(
					OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
					OpenStackCredentials.OPENSTACK_TYPE, 
					keystone_endpoint, password, tenant, 
					OpenStackCredentials.OPENSTACK_USERID,
					user);
			/*status = openStackServices.getIsapCloudAuthentication(
					OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
					OpenStackCredentials.OPENSTACK_TYPE,
					OpenStackCredentials.OPENSTACK_KEYENDPOINT,
					OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD,
					OpenStackCredentials.OPENSTACK_USER_TENANTNAME,
					OpenStackCredentials.OPENSTACK_USERID,
					OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME);*/
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return status;


	}

	@Transactional
	public int getSubnetAddress(String subnetAddress) {
		List<String> list=new ArrayList<String>();
	    list= registerDao.getSubnetAddress(subnetAddress);
		if(list.size()==0 ){
			   return 0;
		}
		else {
			   return 1;
		}
	}

	@Transactional
	public void getUsage(int vdcId) {		
		Vdc vdc=registerDao.getVdcExt(vdcId);		
	}

		
	
}
