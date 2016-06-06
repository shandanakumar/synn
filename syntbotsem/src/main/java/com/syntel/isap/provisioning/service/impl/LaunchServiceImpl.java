package com.syntel.isap.provisioning.service.impl;

import java.io.File;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.net.ntp.TimeStamp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.isap.core.IsapCoreWebservicesStub.AddOpenstackFloatingIpResp;
import com.isap.core.IsapCoreWebservicesStub.GetIsapLaunchInstanceResp;
import com.isap.core.IsapCoreWebservicesStub.GetOpenstackFlavorResp;
import com.syntbots.azure.SyntBotsAzureWebServicesStub.CreateVMEP;
import com.syntel.isap.provisioning.bean.AzureDetails;
import com.syntel.isap.provisioning.bean.BespokePackages;
import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.CustomVMExt;
import com.syntel.isap.provisioning.bean.EnvironmentVDC;
import com.syntel.isap.provisioning.bean.EnvironmentVM;
import com.syntel.isap.provisioning.bean.EnvironmentVMExt;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.InstanceEndPointDetails;
import com.syntel.isap.provisioning.bean.OpenStackDetails;
import com.syntel.isap.provisioning.bean.PackageAttr;
import com.syntel.isap.provisioning.bean.PackageAttributes;
import com.syntel.isap.provisioning.bean.ServiceReqDts;
import com.syntel.isap.provisioning.bean.ServiceReqMst;
import com.syntel.isap.provisioning.bean.StackList;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VMDetails;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;
import com.syntel.isap.provisioning.bean.VmProvision;
import com.syntel.isap.provisioning.bean.VmProvisionPackages;
import com.syntel.isap.provisioning.constants.AzureConstants;
import com.syntel.isap.provisioning.constants.OpenStackCredentials;
import com.syntel.isap.provisioning.constants.ScmConstants;
import com.syntel.isap.provisioning.dao.ICorporateGroupDAO;
import com.syntel.isap.provisioning.dao.ILaunchDao;
import com.syntel.isap.provisioning.dao.impl.LaunchDaoImpl;
import com.syntel.isap.provisioning.mapper.LaunchMapper;
import com.syntel.isap.provisioning.service.ILaunchService;
import com.syntel.isap.provisioning.soap.AzureServicesA2;
import com.syntbots.azure.SyntBotsAzureWebServicesStub.CreateVMBSpoke;
import com.syntel.isap.provisioning.soap.OpenStackServiceA2;
import com.syntel.isap.provisioning.util.AsynchLaunchInstanceAzureUtil;
import com.syntel.isap.provisioning.util.AsynchLaunchInstanceUtil;

/**
 * @author KK5007843
 *
 */

@Service("launchService")
public class LaunchServiceImpl implements ILaunchService {

	@Autowired
	private ILaunchDao launchDao;

	@Autowired
	private ICorporateGroupDAO corporateGroupDao;
	private OpenStackServiceA2 openStackServices = new OpenStackServiceA2();
	private AzureServicesA2 azureServices = new AzureServicesA2();

	private static final Logger LOGGER = Logger
			.getLogger(LaunchServiceImpl.class.getName());

	private javax.jms.Connection jmsConnection = null;
	private Session session = null;

	@Transactional
	public void customLaunch(String keypair, String image, String network,
			String security, String flavor, CustomVM customVM,
			ServiceReqMst serviceReqMst,int vdcId) throws JMSException, RemoteException  {
		
		OpenStackDetails credential = launchDao.getNamByVDCId(vdcId);	
		ServiceReqDts reqDts = new ServiceReqDts();
		List<CustomVMExt> customVMExtDetails = new ArrayList<CustomVMExt>();
		serviceReqMst.setService_req_flow_id(1);
		serviceReqMst.setStatus(OpenStackCredentials.OPENSTACK_CUSTOM_STATUS);
		launchDao.addServiceRequest(serviceReqMst);
		LOGGER.info("Generated RequestId" + serviceReqMst.getService_req_id());
		reqDts.setService_req_id(serviceReqMst.getService_req_id());
		reqDts.setReq_user_id(1);
		launchDao.addServiceRequestDts(reqDts);
		LOGGER.info("Generated RequestDetailsId"
				+ reqDts.getService_req_dts_id());
		customVM.setService_req_id(serviceReqMst.getService_req_id());
		customVM.setService_req_dts_id(reqDts.getService_req_dts_id());
		customVM.setIsap_env_vdc_master_vdc_id(vdcId);
		customVM.setStatus(OpenStackCredentials.OPENSTACK_CUSTOM_STATUS);
		customVM.setIp_addr("-");
		customVM.setPublic_ip_addr("-");
		customVM.setInstance_id("-");
		launchDao.addCustomVMDetails(customVM);
		int customId = customVM.getVm_custom_id();
		LOGGER.info("Generated CustomId" + customId);
		
		CustomVMExt networks = new CustomVMExt(
				OpenStackCredentials.OPENSTACK_CUSTOM_NETWORK, network,
				customId);
		
		
		
		CustomVMExt images = new CustomVMExt(
				OpenStackCredentials.OPENSTACK_CUSTOM_IMAGE,
				openStackServices.getImageDetails(image,
						OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
						OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME),
				customId);
		LOGGER.info("Suucessfully got OpenStackimageName ====" + images);
		
		
		GetOpenstackFlavorResp flavResponse = openStackServices
				.getFlavorDetails(flavor,OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
						OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
		
		LOGGER.info("Suucessfully got OpenStackFlavId ====" + flavResponse.getId());
		CustomVMExt flavors = new CustomVMExt(OpenStackCredentials.OPENSTACK_CUSTOM_FLAVOR,
				flavResponse.getRam() + " MB  RAM | " + flavResponse.getVcpus()
						+ "  VCPU | " + flavResponse.getDisk() + " GB Disk",
				customId);
		
		CustomVMExt securities = new CustomVMExt(
				OpenStackCredentials.OPENSTACK_CUSTOM_SECURITY,
				openStackServices.getSecurityGroupName(
						OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
						OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME,
						security), customId);
		
		LOGGER.info("Suucessfully got OpenStackSecurityGroupName ====" + securities);
		
		CustomVMExt key = new CustomVMExt(
				OpenStackCredentials.OPENSTACK_CUSTOM_KEYPAIR, keypair,
				customId);
		
		
		customVMExtDetails.add(networks);
		customVMExtDetails.add(images);
		customVMExtDetails.add(flavors);
		customVMExtDetails.add(securities);
		customVMExtDetails.add(key);

		for (CustomVMExt cust : customVMExtDetails){
			launchDao.addCustomVMExt(cust);
		}
		String OpenStackUsername = credential.getUsername();
		String OpenStackpassword = credential.getPassword();
		String vdctype=credential.getType();
	GetIsapLaunchInstanceResp lanch = new GetIsapLaunchInstanceResp();
		
	
	
/*	
	a2.getOpenStackLaunchinstance(0, "openstack", "http://192.168.175.153:35357", "synlab123$", "ISAP-STGMA", "20", 
			"admin", "public", "RegionOne", "STG-CHN-AZ01", "2", "5712f12c-d3da-4eac-a794-73ddce14a90e", 
			"shandaanTesting-3-21-2015",
			"admin", "03d27216-3f30-46f6-b051-c91a0dc83ea0", "default", "puppetmst", "192.168.175.204", 0, 20);*/


	LOGGER.info("OpenStackCredentials.OPENSTACK_BESPOKE :"+OpenStackCredentials.OPENSTACK_BESPOKE);
	LOGGER.info("OpenStackCredentials.OPENSTACK_TYPE :"+OpenStackCredentials.OPENSTACK_TYPE);
	LOGGER.info("OpenStackCredentials.OPENSTACK_KEYENDPOINT :"+OpenStackCredentials.OPENSTACK_KEYENDPOINT);
	LOGGER.info("OpenStackCredentials.OPENSTACK_USER_TENANTNAME :"+OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
	LOGGER.info("OpenStackCredentials.OPENSTACK_USERID :"+OpenStackCredentials.OPENSTACK_USERID);
	LOGGER.info("OpenStackUsername :"+OpenStackUsername);
	LOGGER.info("OpenStackCredentials.OPENSTACK_INTERFACE :"+OpenStackCredentials.OPENSTACK_INTERFACE);
	LOGGER.info("OpenStackCredentials.OPENSTACK_REGION :"+OpenStackCredentials.OPENSTACK_REGION);
	LOGGER.info("OpenStackCredentials.OPENSTACK_ZONE :"+OpenStackCredentials.OPENSTACK_ZONE);
	LOGGER.info("flavor :"+flavor);
	LOGGER.info("image :"+image);
	LOGGER.info("customVM.getVm_name():"+customVM.getVm_name());
	LOGGER.info("keypair :"+keypair);
	LOGGER.info("network :"+network);
	LOGGER.info("security :"+security);
	LOGGER.info("ScmConstants.PUPPET_HOSTNAME :"+ScmConstants.PUPPET_HOSTNAME);
	LOGGER.info("ScmConstants.PUPPET_HOSTIP :"+ScmConstants.PUPPET_HOSTIP);
	LOGGER.info("ScmConstants.PUPPET_DISABLE :"+ScmConstants.PUPPET_DISABLE);
	LOGGER.info("customId :"+customId);
	
	
	//Launching instance Using thread
	AsynchLaunchInstanceUtil asynchLaunchInstanceUtil= new AsynchLaunchInstanceUtil(OpenStackCredentials.OPENSTACK_BESPOKE, OpenStackCredentials.OPENSTACK_TYPE, 
			OpenStackCredentials.OPENSTACK_KEYENDPOINT, 
			OpenStackpassword, 
			OpenStackCredentials.OPENSTACK_USER_TENANTNAME, 
			OpenStackCredentials.OPENSTACK_USERID, 
			OpenStackUsername,
			OpenStackCredentials.OPENSTACK_INTERFACE,
			OpenStackCredentials.OPENSTACK_REGION, 
			OpenStackCredentials.OPENSTACK_ZONE, 
			flavor, 
			image, 
			customVM.getVm_name(),
			keypair, 
			network, 
			security,
			ScmConstants.PUPPET_HOSTNAME, 
			ScmConstants.PUPPET_HOSTIP, 
			ScmConstants.PUPPET_DISABLE, 
			customId);
	
	asynchLaunchInstanceUtil.start();
	
	/*lanch = openStackServices.getOpenStackLaunchinstance(OpenStackCredentials.OPENSTACK_BESPOKE, OpenStackCredentials.OPENSTACK_TYPE, 
				OpenStackCredentials.OPENSTACK_KEYENDPOINT, 
				OpenStackpassword, 
				OpenStackCredentials.OPENSTACK_USER_TENANTNAME, 
				OpenStackCredentials.OPENSTACK_USERID, 
				OpenStackUsername,
				OpenStackCredentials.OPENSTACK_INTERFACE,
				OpenStackCredentials.OPENSTACK_REGION, 
				OpenStackCredentials.OPENSTACK_ZONE, 
				flavor, 
				image, 
				customVM.getVm_name(),
				keypair, 
				network, 
				security,
				ScmConstants.PUPPET_HOSTNAME, 
				ScmConstants.PUPPET_HOSTIP, 
				ScmConstants.PUPPET_DISABLE, 
				customId);*/
	
	
		
	LOGGER.info("long message============"+lanch.getIsapLongMessage());
	LOGGER.info("message=========="+lanch.getIsapMessage());

	//lanch = openStackServices.getOpenStackLaunchinstance(0, "openstack", "http://192.168.175.153:35357", "synlab123$", "ISAP-STGMA", "20", "admin", "public", "RegionOne", "STG-CHN-AZ01", "2", "5712f12c-d3da-4eac-a794-73ddce14a90e", "shandaan_tbalaji_21/4/2015", "admin", "03d27216-3f30-46f6-b051-c91a0dc83ea0", "default", "puppetmst", "192.168.175.204", 0, 20);
		
		LOGGER.info("Lanched Sucessfully -------------"+lanch.getIsapRetunCode());
		LOGGER.info("=== ActiveMQ Code Started====");
	/*
	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQCredentials.MQ_URL);
		jmsConnection = connectionFactory.createConnection();
		jmsConnection.start();
		// JMS messages are sent and received using a Session. We will
		// create here a non-transactional session object. If you want
		// to use transactions you should set the first parameter to 'true'
		session = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// Destination represents here our queue 'TESTQUEUE' on the
		// JMS server. You don't have to do anything special on the
		// server to create it, it will be created automatically.
		Destination destination = session
				.createQueue(ActiveMQCredentials.QUEUE_NAME);
		MessageProducer producer = session.createProducer(destination);
		
		String inputReq = "<?xml version='1.0' ?>"
				+ "\n"
				+ "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:a=\"http://schemas.xmlsoap.org/soap/http\">\n"
				+ "<env:Header><a:soapAction>urn:getToken</a:soapAction></env:Header>\n"
				+ "<env:Body>\n"
				+ "<p:server_launch_inputs xmlns:p=\"http://inputs.server.isap.com\">\n"
				+ "<p:beSpoke>"
				+ 0
				+ "</p:beSpoke>\n"
				+ "<p:cloudType>"
				+ vdctype
				+ "</p:cloudType>\n"
				+ "<p:opsKeyEndpoint>"
				+ OpenStackCredentials.OPENSTACK_KEYENDPOINT
				+ "</p:opsKeyEndpoint>\n"
				+ "<p:opsPassword>"
				+ OpenStackpassword
				+ "</p:opsPassword>\n"
				+ "<p:opsTenantName>"
				+ OpenStackCredentials.OPENSTACK_USER_TENANTNAME
				+ "</p:opsTenantName>\n"
				+ "<p:opsUserid>"
				+ OpenStackCredentials.OPENSTACK_USERID
				+ "</p:opsUserid>\n"
				+ "<p:opsUsername>"
				+ OpenStackUsername
				+ "</p:opsUsername>\n"
				+ "<p:ops_interface>"
				+ OpenStackCredentials.OPENSTACK_INTERFACE
				+ "</p:ops_interface>\n"
				+ "<p:ops_region>"
				+ OpenStackCredentials.OPENSTACK_REGION
				+ "</p:ops_region>\n"
				+ "<p:opsaz_zone>"
				+ OpenStackCredentials.OPENSTACK_ZONE
				+ "</p:opsaz_zone>\n"
				+ "<p:opsflavor_id>"
				+ flavor
				+ "</p:opsflavor_id>\n"
				+ "<p:opsimage_id>"
				+ image
				+ "</p:opsimage_id>\n"
				+ "<p:opsinst_name>"
				+ customVM.getVm_name()
				+ "</p:opsinst_name>\n"
				+ "<p:opskey_name>"
				+ keypair
				+ "</p:opskey_name>\n"
				+ "<p:opsnetwork_id>"
				+ network
				+ "</p:opsnetwork_id>\n"
				+ "<p:opssec_group>"
				+ security
				+ "</p:opssec_group>\n"
				+ "<p:puppet_Master_Hostname>"
				+ ScmConstants.PUPPET_HOSTNAME
				+ "</p:puppet_Master_Hostname>\n"
				+ "<p:puppet_Master_IP>"
				+ ScmConstants.PUPPET_HOSTIP
				+ "</p:puppet_Master_IP>\n"
				+ "<p:scm>"
				+ ScmConstants.PUPPET_DISABLE
				+ "</p:scm>\n"
				+ "<p:vm_custom_id>"
				+ customVM.getVm_custom_id()
				+ "</p:vm_custom_id>\n"
				+ "</p:server_launch_inputs>\n"
				+ "</env:Body>\n" + "</env:Envelope>";
		

		LOGGER.info(lanch.getOpsINstanceId());
		String instance_Id_details=lanch.getOpsINstanceId();
		launchDao.updateInstanceIdDetails(instance_Id_details,customId);
		String inputReq = "<?xml version='1.0' ?>"
				+ "\n"
				+ "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:a=\"http://schemas.xmlsoap.org/soap/http\">\n"
				+ "<env:Header><a:soapAction>urn:getToken</a:soapAction></env:Header>\n"
				+ "<env:Body>\n"
				+ "<p:server_launch_inputs xmlns:p=\"http://inputs.server.isap.com\">\n"
				+ "<p:vm_custom_id>"
				+ customId
				+ "</p:vm_custom_id>\n"
				+ "<p:cloudType>"
				+ OpenStackCredentials.OPENSTACK_TYPE
				+ "</p:cloudType>\n"
				+ "<p:opsKeyEndpoint>"
				+ OpenStackCredentials.OPENSTACK_KEYENDPOINT 
				+ "</p:opsKeyEndpoint>\n"
				+ "<p:ops_region>"
				+ OpenStackCredentials.OPENSTACK_REGION
				+ "</p:ops_region>\n"
				+ "<p:ops_interface>"
				+ OpenStackCredentials.OPENSTACK_INTERFACE
				+ "</p:ops_interface>\n"
				+ "<p:userid>"
				+ OpenStackCredentials.OPENSTACK_USERID
				+ "</p:userid>\n"
				+ "<p:image_id>"
				+ image
				+ "</p:image_id>\n"
				+ "<p:username>"
				+ OpenStackUsername
				+ "</p:username>\n"
				+ "<p:password>"
				+ OpenStackpassword
				+ "</p:password>\n"
				+ "<p:tenantname>"
				+ OpenStackCredentials.OPENSTACK_USER_TENANTNAME
				+ "</p:tenantname>\n"
				+ "<p:inst_name>"
				+ customVM.getVm_name()
				+ "</p:inst_name>\n"
				+ "<p:flavour>"
				+ flavor
				+ "</p:flavour>\n"
				+ "<p:az_zone>"
				+ OpenStackCredentials.OPENSTACK_ZONE
				+ "</p:az_zone>\n"
				+ "<p:network_id>"
				+ network
				+ "</p:network_id>\n"
				+ "<p:sec_group>"
				+ security
				+ "</p:sec_group>\n"
				+ "<p:keypair>"
				+ keypair
				+ "</p:keypair>\n"
				+ "<p:puppet_Master_IP>"
				+ ScmConstants.PUPPET_HOSTIP
				+ "</p:puppet_Master_IP>\n"
				+ "<p:puppet_Master_Hostname>"
				+ ScmConstants.PUPPET_HOSTNAME
				+ "</p:puppet_Master_Hostname>\n"
				+ "<p:scm>"
				+ ScmConstants.PUPPET_DISABLE
				+ "</p:scm>\n"
				+ "</p:server_launch_inputs>\n"
				+ "</env:Body>\n" + "</env:Envelope>";	
		LOGGER.info("ActiveMQ Input Request:" + inputReq);
		TextMessage message = session.createTextMessage(inputReq);
		// Here we are sending the message!
		//producer.send(message);
		LOGGER.info("Sent message to ActiveMQ '" + message.getText() + "'");*/
		
		
		int vmRam = flavResponse.getRam();
		int vmVcpu = flavResponse.getVcpus();
		int vmDisk = flavResponse.getDisk();
	/*	VdcProjQuotaMap vdcProjQuotaMapBean = new VdcProjQuotaMap();
		VdcUserQuotaMap vdcUserQuotaMapbean = new VdcUserQuotaMap();
		
		vdcProjQuotaMapBean = launchDao.getProjectFreeQuotaDetails(vdcId,projId);
		vdcUserQuotaMapbean = launchDao.retrieveUserQuotaBean(vdcId,projAdminUserId);
		
		LOGGER.info("==Got proj_quota_map Details from isap_env_vdc_proj_quota_map==");
		int projFreeRam = 0; 
		int projFreeVcpu = 0;
		int projFreeDisk = 0;
		int newProjFreeRam = 0;
		int newProjFreeVcpu = 0;
		int newProjFreeDisk = 0;
		projFreeRam = vdcProjQuotaMapBean.getFree_mem();
		projFreeVcpu = vdcProjQuotaMapBean.getFree_vcpu();
		projFreeDisk = vdcProjQuotaMapBean.getFree_disk_storage();
		newProjFreeRam = projFreeRam - vmRam;
		newProjFreeVcpu = projFreeVcpu - vmVcpu;
		newProjFreeDisk = projFreeDisk - vmDisk;
		vdcProjQuotaMapBean.setFree_mem(newProjFreeRam);
		vdcProjQuotaMapBean.setFree_vcpu(newProjFreeVcpu);
		vdcProjQuotaMapBean.setFree_disk_storage(newProjFreeDisk);
		LOGGER.info("Retrive UserQuotaBean by passing ==vdcId--"+vdcId+"=userId=="+projAdminUserId);
		
		int userFreeRam = 0; 
		int userFreeVcpu = 0;
		int userFreeDisk = 0;
		int newUserFreeRam = 0;
		int newUserFreeVcpu = 0;
		int newUserFreeDisk = 0;
		userFreeRam = vdcUserQuotaMapbean.getFree_mem();
		userFreeVcpu = vdcUserQuotaMapbean.getFree_vcpu();
		userFreeDisk = vdcUserQuotaMapbean.getFree_disk_storage();
		newUserFreeRam = userFreeRam - vmRam;
		newUserFreeVcpu = userFreeVcpu - vmVcpu;
		newUserFreeDisk = userFreeDisk - vmDisk;
		vdcUserQuotaMapbean.setFree_mem(newUserFreeRam);
		vdcUserQuotaMapbean.setFree_vcpu(newUserFreeVcpu);
		vdcUserQuotaMapbean.setFree_disk_storage(newUserFreeDisk);
		LOGGER.info("===Updating Project and user Quota data in DB===");
		launchDao.updateReducedProjFreeQouta(vdcProjQuotaMapBean);
		launchDao.updateReducedUserFreeQuota(vdcUserQuotaMapbean);*/
		launchDao.updateFlavourValueInCustomProvById(vmRam,vmVcpu,vmDisk,customId);
		
	}
	
	
	
	
	
	
	
	/*
	@Transactional
	public void reduceFreeQuotas(String flavor, int vdcId, int userId,
			int cgId, int dptId, int projId) throws RemoteException {
	  
		LOGGER.info("vdcId="+vdcId+"=====projId="+projId);
		GetOpenstackFlavorResp flavResponse = openStackServices.getFlavorDetails(
						flavor,
						OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
						OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_USER_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
						
		int vmRam = flavResponse.getRam();
		int vmVcpu = flavResponse.getVcpus();
		int vmDisk = flavResponse.getDisk();
		VdcProjQuotaMap vdcProjQuotaMapBean = new VdcProjQuotaMap();
		VdcUserQuotaMap vdcUserQuotaMapbean = new VdcUserQuotaMap();
		
		vdcProjQuotaMapBean = launchDao.getProjectFreeQuotaDetails(vdcId,projId);
		LOGGER.info("==Got proj_quota_map Details from isap_env_vdc_proj_quota_map==");
		int projFreeRam = 0; 
		int projFreeVcpu = 0;
		int projFreeDisk = 0;
		int newProjFreeRam = 0;
		int newProjFreeVcpu = 0;
		int newProjFreeDisk = 0;
		projFreeRam = vdcProjQuotaMapBean.getFree_mem();
		projFreeVcpu = vdcProjQuotaMapBean.getFree_vcpu();
		projFreeDisk = vdcProjQuotaMapBean.getFree_disk_storage();
		newProjFreeRam = projFreeRam - vmRam;
		newProjFreeVcpu = projFreeVcpu - vmVcpu;
		newProjFreeDisk = projFreeDisk - vmDisk;
		vdcProjQuotaMapBean.setFree_mem(newProjFreeRam);
		vdcProjQuotaMapBean.setFree_vcpu(newProjFreeVcpu);
		vdcProjQuotaMapBean.setFree_disk_storage(newProjFreeDisk);
		LOGGER.info("Retrive UserQuotaBean by passing ==vdcId--"+vdcId+"=userId=="+userId);
		vdcUserQuotaMapbean = launchDao.retrieveUserQuotaBean(vdcId,userId);
		int userFreeRam = 0; 
		int userFreeVcpu = 0;
		int userFreeDisk = 0;
		int newUserFreeRam = 0;
		int newUserFreeVcpu = 0;
		int newUserFreeDisk = 0;
		userFreeRam = vdcUserQuotaMapbean.getFree_mem();
		userFreeVcpu = vdcUserQuotaMapbean.getFree_vcpu();
		userFreeDisk = vdcUserQuotaMapbean.getFree_disk_storage();
		newUserFreeRam = userFreeRam - vmRam;
		newUserFreeVcpu = userFreeVcpu - vmVcpu;
		newUserFreeDisk = userFreeDisk - vmDisk;
		vdcUserQuotaMapbean.setFree_mem(newUserFreeRam);
		vdcUserQuotaMapbean.setFree_vcpu(newUserFreeVcpu);
		vdcUserQuotaMapbean.setFree_disk_storage(newUserFreeDisk);
		LOGGER.info("===Updating Project and user Quota data in DB===");
		launchDao.updateReducedProjFreeQouta(vdcProjQuotaMapBean);
		launchDao.updateReducedUserFreeQuota(vdcUserQuotaMapbean);
		
	}*/
	
	@Transactional
	public List<CustomVM> getCustomList(int userId) throws Exception {
		List<CustomVMExt> customVMExtDetails = new ArrayList<CustomVMExt>();
		List<CustomVM> customList = new ArrayList<CustomVM>();
		List<CustomVM> customNewList = new ArrayList<CustomVM>();
		customList = launchDao.getCustomList(userId);
		LOGGER.info("Inside getCustomList" + customList);
		for (CustomVM customVM : customList) {
			StackList list = new StackList();
			int customId = customVM.getVm_custom_id();
			String status = customVM.getStatus();
			int vmRam = customVM.getMem();
			int vmVcpu = customVM.getCpu() ;
			int vmDisk = customVM.getHdd();
			LOGGER.info("CustomId" + customId);
			customVMExtDetails = launchDao.getCustomVMExtDetailsById(customId);
			for (CustomVMExt customVMExt : customVMExtDetails) {
				LOGGER.info("CustomVMExt Details:" + customVMExt.getParam_val()
						+ ":" + customVMExt.getParam_val());
				if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_IMAGE)) {
					list.setImage(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_FLAVOR)) {
					list.setFlavor(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_SECURITY)) {
					list.setSecurity(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_NETWORK)) {
					list.setNetwork(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_KEYPAIR)) {
					list.setKeypair(customVMExt.getParam_val());
				}
			}
			customVM.setCustomVMExts(customVMExtDetails);
			customVM.setStackList(list);
			customNewList.add(customVM);
			LOGGER.info("===Creating Instance in OpenStack Started===");
			
		}
		return customList;
	}
	

	@Transactional
	public List<CustomVM> getCustomListAndQuotaManage(int userId,int vdcId, int adminUserId, int cgId,
			int dptId, int projId) throws Exception {
		List<CustomVMExt> customVMExtDetails = new ArrayList<CustomVMExt>();
		List<CustomVM> customList = new ArrayList<CustomVM>();
		List<CustomVM> customNewList = new ArrayList<CustomVM>();
		customList = launchDao.getCustomList(userId);
		LOGGER.info("Inside getCustomList" + customList);
		for (CustomVM customVM : customList) {
			StackList list = new StackList();
			int customId = customVM.getVm_custom_id();
			String status = customVM.getStatus();
			int vmRam = customVM.getMem();
			int vmVcpu = customVM.getCpu() ;
			int vmDisk = customVM.getHdd();
			LOGGER.info("CustomId" + customId);
			customVMExtDetails = launchDao.getCustomVMExtDetailsById(customId);
			for (CustomVMExt customVMExt : customVMExtDetails) {
				LOGGER.info("CustomVMExt Details:" + customVMExt.getParam_val()
						+ ":" + customVMExt.getParam_val());
				if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_IMAGE)) {
					list.setImage(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_FLAVOR)) {
					list.setFlavor(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_SECURITY)) {
					list.setSecurity(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_NETWORK)) {
					list.setNetwork(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_KEYPAIR)) {
					list.setKeypair(customVMExt.getParam_val());
				}
			}
			customVM.setCustomVMExts(customVMExtDetails);
			customVM.setStackList(list);
			customNewList.add(customVM);
			LOGGER.info("===Creating Instance in OpenStack Started===");
			if(!status.equalsIgnoreCase("error")){
				VdcProjQuotaMap vdcProjQuotaMapBean = new VdcProjQuotaMap();
				VdcUserQuotaMap vdcUserQuotaMapbean = new VdcUserQuotaMap();
				
				vdcProjQuotaMapBean = launchDao.getProjectFreeQuotaDetails(vdcId,projId);
				vdcUserQuotaMapbean = launchDao.retrieveUserQuotaBean(vdcId,adminUserId);
				
				LOGGER.info("==Got proj_quota_map Details from isap_env_vdc_proj_quota_map==");
				int projFreeRam = 0; 
				int projFreeVcpu = 0;
				int projFreeDisk = 0;
				int newProjFreeRam = 0;
				int newProjFreeVcpu = 0;
				int newProjFreeDisk = 0;
				projFreeRam = vdcProjQuotaMapBean.getFree_mem();
				projFreeVcpu = vdcProjQuotaMapBean.getFree_vcpu();
				projFreeDisk = vdcProjQuotaMapBean.getFree_disk_storage();
				newProjFreeRam = projFreeRam - vmRam;
				newProjFreeVcpu = projFreeVcpu - vmVcpu;
				newProjFreeDisk = projFreeDisk - vmDisk;
				vdcProjQuotaMapBean.setFree_mem(newProjFreeRam);
				vdcProjQuotaMapBean.setFree_vcpu(newProjFreeVcpu);
				vdcProjQuotaMapBean.setFree_disk_storage(newProjFreeDisk);
				LOGGER.info("Retrive UserQuotaBean by passing ==vdcId--"+vdcId+"=userId=="+adminUserId);
				
				int userFreeRam = 0; 
				int userFreeVcpu = 0;
				int userFreeDisk = 0;
				int newUserFreeRam = 0;
				int newUserFreeVcpu = 0;
				int newUserFreeDisk = 0;
				userFreeRam = vdcUserQuotaMapbean.getFree_mem();
				userFreeVcpu = vdcUserQuotaMapbean.getFree_vcpu();
				userFreeDisk = vdcUserQuotaMapbean.getFree_disk_storage();
				newUserFreeRam = userFreeRam - vmRam;
				newUserFreeVcpu = userFreeVcpu - vmVcpu;
				newUserFreeDisk = userFreeDisk - vmDisk;
				vdcUserQuotaMapbean.setFree_mem(newUserFreeRam);
				vdcUserQuotaMapbean.setFree_vcpu(newUserFreeVcpu);
				vdcUserQuotaMapbean.setFree_disk_storage(newUserFreeDisk);
				LOGGER.info("===Updating Project and user Quota data in DB===");
				launchDao.updateReducedProjFreeQouta(vdcProjQuotaMapBean);
				launchDao.updateReducedUserFreeQuota(vdcUserQuotaMapbean);
			}
		}
		return customList;
	}

	
	
	
	
	
	@Transactional
	public void bespokeLaunch(String keypair, VmProvision vmProvision,
			User user, HttpServletRequest req, String vmId) throws JMSException, RemoteException {
		String flavor = null;
		String network = null, security = null, image = null;
		List<PackageAttr> attrList = new ArrayList<PackageAttr>();
		ServiceReqMst serviceReqMst = new ServiceReqMst();
		ServiceReqDts reqDts = new ServiceReqDts();
		serviceReqMst.setService_req_flow_id(1);
		serviceReqMst.setStatus(OpenStackCredentials.OPENSTACK_CUSTOM_STATUS);
		launchDao.addServiceRequest(serviceReqMst);
		LOGGER.info("Generated RequestId" + serviceReqMst.getService_req_id());
		reqDts.setService_req_id(serviceReqMst.getService_req_id());
		reqDts.setReq_user_id(user.getUsr_id());
		launchDao.addServiceRequestDts(reqDts);
		LOGGER.info("Generated RequestDetailsId"
				+ reqDts.getService_req_dts_id());
		List<PackageAttr> packageAttrList = launchDao
				.getPackageAttrListByVmId(vmId);
		for (PackageAttr packageAttr : packageAttrList) {
			if (packageAttr.getParam_name().equalsIgnoreCase(
					OpenStackCredentials.OPENSTACK_CUSTOM_IMAGE)) {
				image = packageAttr.getParam_val();
			} else if (packageAttr.getParam_name().equalsIgnoreCase(
					OpenStackCredentials.OPENSTACK_CUSTOM_FLAVOR)) {
				flavor = packageAttr.getParam_val();
			} else if (packageAttr.getParam_name().equalsIgnoreCase(
					OpenStackCredentials.OPENSTACK_CUSTOM_SECURITY)) {
				security = packageAttr.getParam_val();
			} else if (packageAttr.getParam_name().equalsIgnoreCase(
					OpenStackCredentials.OPENSTACK_CUSTOM_NETWORK)) {
				network = packageAttr.getParam_val();
			}
		}
		int random = (int) Math.round(Math.random() * OpenStackCredentials.RANDOM + 1);
		vmProvision.setVm_name("service-" + user.getCg_id() + ""
				+ user.getDpt_id() + "" + user.getProj_id() + ""
				+ user.getUsr_id() + "" + random);
		vmProvision.setVm_master_id(Integer.parseInt(vmId));
		vmProvision.setVdc_id(40);
		vmProvision.setStatus(OpenStackCredentials.OPENSTACK_CUSTOM_STATUS);
		vmProvision.setFirewall_master_id(1);
		vmProvision.setUser_id(user.getUsr_id());
		vmProvision.setReq_id(reqDts.getService_req_dts_id());
		vmProvision.setProj_id(user.getProj_id());
		vmProvision.setCg_id(user.getCg_id());
		vmProvision.setDpt_id(user.getDpt_id());
		vmProvision.setPublic_ip_addr("-");
		vmProvision.setIp_addr("-");
		vmProvision.setInstance_id("-");
		launchDao.saveBespokevmProvision(vmProvision);
		LOGGER.info("Generated Vm ID:" + vmProvision.getVm_id());
		int customId = vmProvision.getVm_id();
		
		
		
		
		
	/*	PackageAttr networks = new PackageAttr(
				OpenStackCredentials.OPENSTACK_CUSTOM_NETWORK, network,
				customId);
		PackageAttr images = new PackageAttr(
				OpenStackCredentials.OPENSTACK_CUSTOM_IMAGE,
				openStackServices.getImageDetails(
						OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_USER_NAME,
						OpenStackCredentials.OPENSTACK_USER_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME, image),
				customId);
				
				
		GetOpenstackFlavorResp flavResponse = openStackServices
				.getFlavorDetails(OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_USER_NAME,
						OpenStackCredentials.OPENSTACK_USER_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME, flavor);
		PackageAttr flavors = new PackageAttr(
				OpenStackCredentials.OPENSTACK_CUSTOM_FLAVOR,
				flavResponse.getRam() + " MB  RAM | " + flavResponse.getVcpus()
						+ "  VCPU | " + flavResponse.getDisk() + " GB Disk",
				customId);
		*/
		
		
		
		PackageAttr networks = new PackageAttr(
				OpenStackCredentials.OPENSTACK_CUSTOM_NETWORK, network,
				customId);
		
		/*	image1 val hard coded*/
		String image1="5be78839-e753-410d-84ee-6d19d3b7f61d";
		
		LOGGER.info(image1);
		PackageAttr images = new PackageAttr(
				OpenStackCredentials.OPENSTACK_CUSTOM_IMAGE,
				openStackServices.getImageDetails(image1,
						OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
						OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME),
				customId);
		
		LOGGER.info("Suucessfully got OpenStackimageName ====" + images);
		

		
		GetOpenstackFlavorResp flavResponse = openStackServices
				.getFlavorDetails(flavor,OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
						OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
		
		LOGGER.info("Suucessfully got OpenStackFlavId ====" + flavResponse.getId());
		PackageAttr flavors = new PackageAttr(
				
				OpenStackCredentials.OPENSTACK_CUSTOM_FLAVOR,
				flavResponse.getRam() + " MB  RAM | " + flavResponse.getVcpus()
						+ "  VCPU | " + flavResponse.getDisk() + " GB Disk",
				customId);
		
	
		
		PackageAttr securities = new PackageAttr(
				OpenStackCredentials.OPENSTACK_CUSTOM_SECURITY, security,
				customId);
		PackageAttr key = new PackageAttr(
				OpenStackCredentials.OPENSTACK_CUSTOM_KEYPAIR, keypair,
				customId);
		attrList.add(networks);
		attrList.add(images);
		attrList.add(flavors);
		attrList.add(securities);
		attrList.add(key);
		for (PackageAttr attr : attrList) {
			launchDao.saveBespokeExtParams(attr);
		}
		List<String> packageIds = launchDao.getPackagesByVmId(Integer
				.parseInt(vmId));
		LOGGER.info("packageIds" + packageIds);
		if (!packageIds.isEmpty()) {
			for (String component : packageIds) {
				int packageId = Integer.parseInt(component);
				List<PackageAttributes> packageAttributes = launchDao
						.getAttributesByPackageId(packageId);
				VmProvisionPackages vmProvisionPackage = new VmProvisionPackages();
				vmProvisionPackage.setVm_id(vmProvision.getVm_id());
				vmProvisionPackage.setPkg_id(packageId);
				vmProvisionPackage.setRequired(0);
				vmProvisionPackage.setParam_name(packageId+"");
				vmProvisionPackage.setParam_value(packageId+"");
				
			
		 /*NOt addding DB	*/
			
				launchDao
					.saveBespokevmProvisionPackages(vmProvisionPackage);
			
				
				for (PackageAttributes packages : packageAttributes) {
					String reqName = packages.getAttr_name();
					String paramValue = req.getParameter(reqName);
					vmProvisionPackage.setVm_id(vmProvision.getVm_id());
					vmProvisionPackage.setPkg_id(packageId);
					vmProvisionPackage.setRequired(0);
					vmProvisionPackage.setParam_name(reqName);
					vmProvisionPackage.setParam_value(paramValue);
					launchDao
							.saveBespokevmProvisionPackages(vmProvisionPackage);
				}
	
			}
		}
		/*ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQCredentials.MQ_URL);
		jmsConnection = connectionFactory.createConnection();
		jmsConnection.start();
		// JMS messages are sent and received using a Session. We will
		// create here a non-transactional session object. If you want
		// to use transactions you should set the first parameter to 'true'
		session = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// Destination represents here our queue 'TESTQUEUE' on the
		// JMS server. You don't have to do anything special on the
		// server to create it, it will be created automatically.
		
		
		<xs:element minOccurs="0" name="beSpoke" type="xs:int"/>
		<xs:element minOccurs="0" name="cloudType" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsKeyEndpoint" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsPassword" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsTenantName" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsUserid" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsUsername" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="ops_interface" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="ops_region" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsaz_zone" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsflavor_id" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsimage_id" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsinst_name" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opskey_name" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opsnetwork_id" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="opssec_group" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="puppet_Master_Hostname" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="puppet_Master_IP" nillable="true" type="xs:string"/>
		<xs:element minOccurs="0" name="scm" type="xs:int"/>
		<xs:element minOccurs="0" name="vm_custom_id" type="xs:int"/>
		
		
		
		
		Destination destination = session
				.createQueue(ActiveMQCredentials.QUEUE_NAME);
		MessageProducer producer = session.createProducer(destination);
		
		
		
		String inputReq = "<?xml version='1.0' ?>"
				+ "\n"
				+ "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:a=\"http://schemas.xmlsoap.org/soap/http\">\n"
				+ "<env:Header><a:soapAction>urn:getToken</a:soapAction></env:Header>\n"
				+ "<env:Body>\n"
				+ "<p:server_launch_inputs xmlns:p=\"http://inputs.server.isap.com\">\n"
				+ "<p:vm_custom_id>"
				+ vmProvision.getVm_id()
				+ "</p:vm_custom_id>\n"
				+ "<p:cloudType>"
				+ OpenStackCredentials.OPENSTACK_TYPE
				+ "</p:cloudType>\n"
				+ "<p:ip>"
				+ OpenStackCredentials.OPENSTACK_IP
				+ "</p:ip>\n"
				+ "<p:userid>"
				+ OpenStackCredentials.OPENSTACK_USERID
				+ "</p:userid>\n"
				+ "<p:image_id>"
				+ image
				+ "</p:image_id>\n"
				+ "<p:username>"
				+ OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME
				+ "</p:username>\n"
				+ "<p:password>"
				+ OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD
				+ "</p:password>\n"
				+ "<p:tenantname>"
				+ OpenStackCredentials.OPENSTACK_ADMIN_TENANTNAME
				+ "</p:tenantname>\n"
				+ "<p:inst_name>"
				+ vmProvision.getVm_name()
				+ "</p:inst_name>\n"
				+ "<p:flavour>"
				+ flavor
				+ "</p:flavour>\n"
				+ "<p:az_zone>"
				+ OpenStackCredentials.OPENSTACK_ZONE
				+ "</p:az_zone>\n"
				+ "<p:network_id>"
				+ network
				+ "</p:network_id>\n"
				+ "<p:sec_group>"
				+ security
				+ "</p:sec_group>\n"
				+ "<p:keypair>"
				+ keypair
				+ "</p:keypair>\n"
				+ "<p:puppet_Master_IP>"
				+ ScmConstants.PUPPET_HOSTIP
				+ "</p:puppet_Master_IP>\n"
				+ "<p:puppet_Master_Hostname>"
				+ ScmConstants.PUPPET_HOSTNAME
				+ "</p:puppet_Master_Hostname>\n"
				+ "<p:scm>"
				+ ScmConstants.PUPPET_ENABLE
				+ "</p:scm>\n"
				+ "<p:beSpoke>"
				+ 1
				+ "</p:beSpoke>\n"
				+ "</p:server_launch_inputs>\n"
				+ "</env:Body>\n" + "</env:Envelope>";
		String inputReq = "<?xml version='1.0' ?>"
				+ "\n"
				+ "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:a=\"http://schemas.xmlsoap.org/soap/http\">\n"
				+ "<env:Header><a:soapAction>urn:getToken</a:soapAction></env:Header>\n"
				+ "<env:Body>\n"
				+ "<p:server_launch_inputs xmlns:p=\"http://inputs.server.isap.com\">\n"
				+ "<p:beSpoke>"
				+ 0
				+ "</p:beSpoke>\n"
				+ "<p:cloudType>"
				+ OpenStackCredentials.OPENSTACK_TYPE
				+ "</p:cloudType>\n"
				+ "<p:opsKeyEndpoint>"
				+ OpenStackCredentials.OPENSTACK_KEYENDPOINT
				+ "</p:opsKeyEndpoint>\n"
				+ "<p:opsPassword>"
				+ OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD
				+ "</p:opsPassword>\n"
				+ "<p:opsTenantName>"
				+ OpenStackCredentials.OPENSTACK_USER_TENANTNAME
				+ "</p:opsTenantName>\n"
				+ "<p:opsUserid>"
				+ OpenStackCredentials.OPENSTACK_USERID
				+ "</p:opsUserid>\n"
				+ "<p:opsUsername>"
				+ OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME
				+ "</p:opsUsername>\n"
				+ "<p:ops_interface>"
				+ OpenStackCredentials.OPENSTACK_INTERFACE
				+ "</p:ops_interface>\n"
				+ "<p:ops_region>"
				+ OpenStackCredentials.OPENSTACK_REGION
				+ "</p:ops_region>\n"
				+ "<p:opsaz_zone>"
				+ OpenStackCredentials.OPENSTACK_ZONE
				+ "</p:opsaz_zone>\n"
				+ "<p:opsflavor_id>"
				+ flavor
				+ "</p:opsflavor_id>\n"
				+ "<p:opsimage_id>"
				+ image
				+ "</p:opsimage_id>\n"
				+ "<p:opsinst_name>"
				+ vmProvision.getVm_name()
				+ "</p:opsinst_name>\n"
				+ "<p:opskey_name>"
				+ keypair
				+ "</p:opskey_name>\n"
				+ "<p:opsnetwork_id>"
				+ network
				+ "</p:opsnetwork_id>\n"
				+ "<p:opssec_group>"
				+ security
				+ "</p:opssec_group>\n"
				+ "<p:puppet_Master_Hostname>"
				+ ScmConstants.PUPPET_HOSTNAME
				+ "</p:puppet_Master_Hostname>\n"
				+ "<p:puppet_Master_IP>"
				+ ScmConstants.PUPPET_HOSTIP
				+ "</p:puppet_Master_IP>\n"
				+ "<p:scm>"
				+ ScmConstants.PUPPET_DISABLE
				+ "</p:scm>\n"
				+ "<p:vm_custom_id>"
				+ vmProvision.getVm_id()
				+ "</p:vm_custom_id>\n"
				+ "</p:server_launch_inputs>\n"
				+ "</env:Body>\n" + "</env:Envelope>";
		LOGGER.info("result" + inputReq);
		TextMessage message = session.createTextMessage(inputReq);
		// Here we are sending the message!
		producer.send(message);
		LOGGER.info("Sent message '" + message.getText() + "'");*/
		//Launching instance Using thread
		
		List<BespokePackages> bespokePackageList = new ArrayList<BespokePackages>();
		int integerVmId = Integer.parseInt(vmId);
		List<String> packageIdList = launchDao.getPackagesByVmId(integerVmId);
		LOGGER.info("packageIds" + packageIdList);
		if (!packageIdList.isEmpty()) {
			for (String component : packageIdList) {
				BespokePackages bespokePackage = new BespokePackages();
				int packageId = Integer.parseInt(component);
				String packageName = launchDao.getPackageNameById(packageId);
				List<PackageAttributes> packageAttributes = launchDao
						.getAttributesByPackageId(packageId);
				bespokePackage.setPackage_name(packageName);
				bespokePackage.setPackageAttributes(packageAttributes);
				bespokePackageList.add(bespokePackage);
			}
		}
    
		AsynchLaunchInstanceUtil asynchLaunchInstanceUtil= new AsynchLaunchInstanceUtil(OpenStackCredentials.OPENSTACK_BESPOKE_ONE, OpenStackCredentials.OPENSTACK_TYPE, 
				OpenStackCredentials.OPENSTACK_KEYENDPOINT, 
				OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD, 
				OpenStackCredentials.OPENSTACK_USER_TENANTNAME, 
				OpenStackCredentials.OPENSTACK_USERID, 
				OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
				OpenStackCredentials.OPENSTACK_INTERFACE,
				OpenStackCredentials.OPENSTACK_REGION, 
				OpenStackCredentials.OPENSTACK_ZONE, 
				flavor, 
				image, 
				vmProvision.getVm_name(),
				keypair, 
				network, 
				security,
				ScmConstants.PUPPET_HOSTNAME, 
				ScmConstants.PUPPET_HOSTIP, 
				ScmConstants.PUPPET_ENABLE, 
				customId);
		
		asynchLaunchInstanceUtil.start();
		
	}

	@Transactional
	public void bespokeAzureLaunch( User userSession,
			AzureDetails azureDetails) {
		
		
		String azureUserName = azureDetails.getAzureUserName();
		String azurePassword = azureDetails.getAzurePassword();
		String azureCnfPassword = azureDetails.getAzureCnfPassword();
		String azureImage = azureDetails.getAzureImage() ;
		//String azureEndPoint = azureDetails.getAzureEndPoint();
		String azureStartDate = azureDetails.getAzureStartDate();
		String azureEndDate = azureDetails.getAzureEndDate();
		String azureServiceName = azureDetails.getAzureServiceName();
		String azureServiceDesc = azureDetails.getAzureServiceDesc();
		String azureVmId = azureDetails.getAzureVmId();
		VmProvision vmBespokeProvision = new VmProvision();
		int userId = userSession.getUsr_id();
		
		
		ServiceReqMst serviceReqMst = new ServiceReqMst();
		ServiceReqDts reqDts = new ServiceReqDts();
		
		serviceReqMst.setService_req_flow_id(1);
		serviceReqMst.setStatus(OpenStackCredentials.OPENSTACK_CUSTOM_STATUS);
		launchDao.addServiceRequest(serviceReqMst);
		
		LOGGER.info("Generated RequestId" + serviceReqMst.getService_req_id());
		
		reqDts.setService_req_id(serviceReqMst.getService_req_id());
		reqDts.setReq_user_id(userSession.getUsr_id());
		launchDao.addServiceRequestDts(reqDts);
		
		LOGGER.info("Generated RequestDetailsId"
				+ reqDts.getService_req_dts_id());
		
		
				//will be used later not now START
				
		int random = (int) Math.round(Math.random() + 1);
		/*String vmRoleName = "service-" + userSession.getCg_id() + ""
				+ userSession.getDpt_id() + "" + userSession.getProj_id() + ""
				+ userSession.getUsr_id() + "" + random;*/
		vmBespokeProvision.setVm_name("bservice"+new Date().getMonth()+""+new Date().getTime());
		vmBespokeProvision.setVm_master_id(Integer.parseInt(azureVmId));
		vmBespokeProvision.setVdc_id(AzureConstants.AZURE_VDC_ID);
		vmBespokeProvision.setStatus(AzureConstants.AZURE_VDC_STATUS);
		vmBespokeProvision.setFirewall_master_id(1);
		vmBespokeProvision.setUser_id(userId);
		vmBespokeProvision.setReq_id(reqDts.getService_req_dts_id());
		vmBespokeProvision.setProj_id(userSession.getProj_id());
		vmBespokeProvision.setCg_id(userSession.getCg_id());
		vmBespokeProvision.setDpt_id(userSession.getDpt_id());
		vmBespokeProvision.setPublic_ip_addr(AzureConstants.AZURE_VDC_PUBLIC_IPADDRESS);
		vmBespokeProvision.setIp_addr(AzureConstants.AZURE_VDC_IPADDRESS);
		vmBespokeProvision.setInstance_id(AzureConstants.AZURE_VDC_INSTANCE_ID);
		vmBespokeProvision.setStartDate(azureStartDate);
		vmBespokeProvision.setEndDate(azureEndDate);
		
		try{
		launchDao.saveBespokevmProvision(vmBespokeProvision);
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.info("Generated Vm ID:" + vmBespokeProvision.getVm_id());
		int bespokeVMId = vmBespokeProvision.getVm_id();
		
		String flavor = null, flavorValue = "1 VCPU| 3.5 GB RAM";
		String network = null , ntwrkValue = "Private-net";
		String security = null, image = null;
		List<PackageAttr> attrList = new ArrayList<PackageAttr>();
		List<PackageAttr> packageAttrList = launchDao
				.getPackageAttrListByVmId("1");
		for (PackageAttr packageAttr : packageAttrList) {
			if (packageAttr.getParam_name().equalsIgnoreCase(
					OpenStackCredentials.OPENSTACK_CUSTOM_IMAGE)) {
				image = packageAttr.getParam_val();
			} else if (packageAttr.getParam_name().equalsIgnoreCase(
					OpenStackCredentials.OPENSTACK_CUSTOM_FLAVOR)) {
				flavor = packageAttr.getParam_val();
			} else if (packageAttr.getParam_name().equalsIgnoreCase(
					OpenStackCredentials.OPENSTACK_CUSTOM_SECURITY)) {
				security = packageAttr.getParam_val();
			} else if (packageAttr.getParam_name().equalsIgnoreCase(
					OpenStackCredentials.OPENSTACK_CUSTOM_NETWORK)) {
				network = packageAttr.getParam_val();
			}
		}
		
		
		PackageAttr networks = new PackageAttr(AzureConstants.AZURE_BESPOKE_NETWORK, ntwrkValue, bespokeVMId); 
		PackageAttr flavors = new PackageAttr(AzureConstants.AZURE_BESPOKE_FLAVOR,flavorValue ,bespokeVMId ); 
		attrList.add(networks);
		attrList.add(flavors);
		
		
		for (PackageAttr attr : attrList) {
			launchDao.saveBespokeExtParams(attr);
		}
		
		
		List<String> packageIds = launchDao.getPackagesByVmId(Integer
				.parseInt("1"));
		LOGGER.info("packageIds" + packageIds);
		if (!packageIds.isEmpty()) {
			for (String component : packageIds) {
				int packageId = Integer.parseInt(component);
				List<PackageAttributes> packageAttributes = launchDao
						.getAttributesByPackageId(packageId);
				VmProvisionPackages vmProvisionPackage = new VmProvisionPackages();
				vmProvisionPackage.setVm_id(bespokeVMId);
				vmProvisionPackage.setPkg_id(packageId);
				vmProvisionPackage.setRequired(0);
				vmProvisionPackage.setParam_name(packageId+"");
				vmProvisionPackage.setParam_value(packageId+"");
				
				launchDao.saveBespokevmProvisionPackages(vmProvisionPackage);
				
				for (PackageAttributes packages : packageAttributes) {
					String reqName = packages.getAttr_name();
					//String paramValue = req.getParameter(reqName);
					String paramValue = packages.getDefault_val();
					vmProvisionPackage.setVm_id(bespokeVMId); // for first bespoke tomcat vm_id is 1
					vmProvisionPackage.setPkg_id(packageId);
					vmProvisionPackage.setRequired(0);
					vmProvisionPackage.setParam_name(reqName);
					vmProvisionPackage.setParam_value(paramValue);
					launchDao
							.saveBespokevmProvisionPackages(vmProvisionPackage);
				}
	
			}
		}
		
		LOGGER.info("just before instantiation azureServices ::");
		String roleSize = "Standard_D1";
		String serviceName = "syntbots-chn";
		String configSetType = "WindowsProvisioningConfiguration";
		String deploymentLabel = "label";
		String deploymentName = "deploymentSyntbots";
		String hostname = "syncgdc";
		

		CreateVMBSpoke vmDetails = new CreateVMBSpoke();
		//need to be same values
		vmDetails.setRoleSize(AzureConstants.AZURE_ROLE_SIZE);
		vmDetails.setServiceName(AzureConstants.AZURE_SERVICE_NAME);
		vmDetails.setConfigSetType(AzureConstants.AZURE_CONFIG_SET_TYPE);
		
		//Values from UI
		vmDetails.setAdminUser(azureUserName);
		vmDetails.setAdminPassword(azurePassword);
		vmDetails.setImageName(AzureConstants.AZURE_HOST_OS_IMAGE);
		vmDetails.setRoleName("bservice-430");
		
		//Need to discuss
		vmDetails.setDeploymentLabel(AzureConstants.AZURE_DEPLOYMENT_LABEL);
		vmDetails.setDeploymentName(AzureConstants.AZURE_AZURE_DEPLOYMENT_NAME);
		vmDetails.setHostname(AzureConstants.AZURE_HOST_NAME);
		
		try{
			azureServices.launchBespokeAzureVM(vmDetails);
		}catch(Exception e){
			e.printStackTrace();
		}

		// can give random values for these below fields
		/*String adminPassword = "Syntel123$";
		String adminUser = "Syntel123";

		String imageName = "windows";
		String roleName = "syntbots-vmTest";
		*/
		
		
		
		/*AzureServicesA2 azureClient = new AzureServicesA2();
		CreateVMEP cVmep = new CreateVMEP();*/
		//attrList.add();
		
		// will be used later not now START . It provides tomcat, sql, details. These details should be sent from here to scripts via service
		List<BespokePackages> bespokePackageList = new ArrayList<BespokePackages>();
		int integerVmId = Integer.parseInt(azureVmId);
		List<String> packageIdList = launchDao.getPackagesByVmId(integerVmId);
		LOGGER.info("packageIds" + packageIdList);
		if (!packageIdList.isEmpty()) {
			for (String component : packageIdList) {
				BespokePackages bespokePackage = new BespokePackages();
				int packageId = Integer.parseInt(component);
				String packageName = launchDao.getPackageNameById(packageId);
				List<PackageAttributes> packageAttributes = launchDao
						.getAttributesByPackageId(packageId);
				bespokePackage.setPackage_name(packageName);
				bespokePackage.setPackageAttributes(packageAttributes);
				bespokePackageList.add(bespokePackage);
			}
		}
		// will be used later not now END
		/*		
		cVmep.setAdminPassword(adminPassword);
		cVmep.setAdminUser(adminUser);
		cVmep.setConfigSetType(configSetType);
		cVmep.setDeploymentLabel(deploymentLabel);
		cVmep.setDeploymentName(deploymentName);
		cVmep.setHostname(hostname);
		cVmep.setImageName(imageName);
		cVmep.setRoleName(roleName);
		cVmep.setRoleSize(roleSize);
		cVmep.setServiceName(serviceName);
		try{
			azureClient.launchAzureVM(cVmep);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		/* below two line of code are for Launch with Thread Concept
		 * AsynchLaunchInstanceAzureUtil asynchLaunchInstanceAzureUtil= new AsynchLaunchInstanceAzureUtil(cVmep);
		 * asynchLaunchInstanceAzureUtil.start();
		 * 
		 */
		
	}

	
	
	@Transactional
	public List<VmProvision> getBespokeList(int userId) throws Exception {
		List<PackageAttr> packageAttributes = new ArrayList<PackageAttr>();
		List<VmProvision> customList = new ArrayList<VmProvision>();
		List<VmProvision> customNewList = new ArrayList<VmProvision>();
		customList = launchDao.getBespokeList(userId);
		LOGGER.info("Inside getBespokeList" + customList);
		for (VmProvision customVM : customList) {
			StackList list = new StackList();
			int customId = customVM.getVm_id();
			customVM.setComponents(launchDao.getComponentsByVmId(customId));
			LOGGER.info("Custom BespokeId" + customId);
			packageAttributes = launchDao.getBespokeVMExtDetailsById(customId);
			for (PackageAttr customVMExt : packageAttributes) {
				LOGGER.info("CustomVMExt Details:" + customVMExt.getParam_val()
						+ ":" + customVMExt.getParam_val());
				if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_IMAGE)) {
					list.setImage(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_FLAVOR)) {
					list.setFlavor(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_SECURITY)) {
					list.setSecurity(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_NETWORK)) {
					list.setNetwork(customVMExt.getParam_val());
				} else if (customVMExt.getParam_name().equalsIgnoreCase(
						OpenStackCredentials.OPENSTACK_CUSTOM_KEYPAIR)) {
					list.setKeypair(customVMExt.getParam_val());
				}
			}
			int vdcId = customVM.getVdc_id();
			int vmMasterId = customVM.getVm_master_id();
			String VmMasterName = null;
			EnvironmentVM envVm = launchDao.getEnvVmDetailsByID(vmMasterId);
			VmMasterName = envVm.getVm_name();
			String vdcProvider = null;
			vdcProvider = launchDao.getVdcNameById(vdcId);
			customVM.setBspoke_mst_vm_name(VmMasterName);
			customVM.setVdc_provider(vdcProvider);
			customVM.setStackList(list);
			customNewList.add(customVM);
		}
		return customList;
	}

	@Transactional
	public List<Identity> getKeyPairList(int usrId) {
		return launchDao.getKeyPairList(usrId);
	}

	@Transactional
	public void addKeyPair(String name, String keyPairPrivate, int usrId) {
		Identity keypair = new Identity();
		keypair.setContent(keyPairPrivate);
		keypair.setUsr_id(usrId);
		keypair.setName(name);
		launchDao.addKeyPair(keypair);
	}

	@Transactional
	public Identity getIdentity(int keyId) {
		return launchDao.getIdentity(keyId);
	}

	@Transactional
	public int checkKeyPair(String name) {
		Identity identity = launchDao.checkKeyPair(name);
		LOGGER.info("CustomId" + identity);
		if (identity != null) {
			return 1;
		}
	  return 0;
	}

	@Transactional
	public List<String> getKeyPairs(int usrId) {
		return launchDao.getKeyPairs(usrId);
	}

	@Transactional
	public void terminateInstance(String instId,int projId,int vdcId,int adminUserId) {
		for (String retval : instId.split(",")) {
			String opensInstId = launchDao.getOpenStackInstanceId(Integer
					.parseInt(retval));
			launchDao.terminateInstance(Integer.parseInt(retval));
			try {
				openStackServices.terminateInstance(opensInstId,
								OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
								OpenStackCredentials.OPENSTACK_KEYENDPOINT,
								OpenStackCredentials.OPENSTACK_INTERFACE,
								OpenStackCredentials.OPENSTACK_REGION,
								OpenStackCredentials.OPENSTACK_USER_PASSWORD,
								OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
				
				
				CustomVM customVm = new CustomVM();
				int customVmId =Integer.parseInt(retval);
				customVm = launchDao.retrieveCustomVmProvById(customVmId);
				String status = customVm.getStatus();
				if(!status.equalsIgnoreCase("error")){
					int vmRam = customVm.getMem();
					int vmVcpu = customVm.getCpu();
					int vmDisk = customVm.getHdd();
					VdcProjQuotaMap vdcProjQuotaMapBean = new VdcProjQuotaMap();
					VdcUserQuotaMap vdcUserQuotaMapbean = new VdcUserQuotaMap();
					vdcProjQuotaMapBean = launchDao.getProjectFreeQuotaDetails(vdcId,projId);
					LOGGER.info("==Got proj_quota_map Details from isap_env_vdc_proj_quota_map==");
					int projFreeRam = 0; 
					int projFreeVcpu = 0;
					int projFreeDisk = 0;
					int newProjFreeRam = 0;
					int newProjFreeVcpu = 0;
					int newProjFreeDisk = 0;
					projFreeRam = vdcProjQuotaMapBean.getFree_mem();
					projFreeVcpu = vdcProjQuotaMapBean.getFree_vcpu();
					projFreeDisk = vdcProjQuotaMapBean.getFree_disk_storage();
					newProjFreeRam = projFreeRam + vmRam;
					newProjFreeVcpu = projFreeVcpu + vmVcpu;
					newProjFreeDisk = projFreeDisk + vmDisk;
					vdcProjQuotaMapBean.setFree_mem(newProjFreeRam);
					vdcProjQuotaMapBean.setFree_vcpu(newProjFreeVcpu);
					vdcProjQuotaMapBean.setFree_disk_storage(newProjFreeDisk);
					LOGGER.info("Retrive UserQuotaBean by passing ==vdcId--"+vdcId+"=userId=="+adminUserId);
					vdcUserQuotaMapbean = launchDao.retrieveUserQuotaBean(vdcId,adminUserId);
					int userFreeRam = 0; 
					int userFreeVcpu = 0;
					int userFreeDisk = 0;
					int newUserFreeRam = 0;
					int newUserFreeVcpu = 0;
					int newUserFreeDisk = 0;
					userFreeRam = vdcUserQuotaMapbean.getFree_mem();
					userFreeVcpu = vdcUserQuotaMapbean.getFree_vcpu();
					userFreeDisk = vdcUserQuotaMapbean.getFree_disk_storage();
					newUserFreeRam = userFreeRam + vmRam;
					newUserFreeVcpu = userFreeVcpu + vmVcpu;
					newUserFreeDisk = userFreeDisk + vmDisk;
					vdcUserQuotaMapbean.setFree_mem(newUserFreeRam);
					vdcUserQuotaMapbean.setFree_vcpu(newUserFreeVcpu);
					vdcUserQuotaMapbean.setFree_disk_storage(newUserFreeDisk);
					LOGGER.info("===Updating Project and user Quota data in DB===");
					launchDao.updateReducedProjFreeQouta(vdcProjQuotaMapBean);
					launchDao.updateReducedUserFreeQuota(vdcUserQuotaMapbean);
				}
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	@Transactional
	public void addFloatingIP(String instId) throws RemoteException {
		String opensInstId = launchDao.getOpenStackInstanceId(Integer
				.parseInt(instId));
		AddOpenstackFloatingIpResp flaotingIpResponse = openStackServices.addOpenstackFloatingIptoInstance(OpenStackCredentials.OPENSTACK_IP,
				OpenStackCredentials.OPENSTACK_INTERFACE, 
				OpenStackCredentials.OPENSTACK_KEYENDPOINT,
				OpenStackCredentials.OPENSTACK_REGION, 
				OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD,
				OpenStackCredentials.OPENSTACK_USER_TENANTNAME,
				OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
				OpenStackCredentials.OPENSTACK_POOL,
				opensInstId);
				/*.addFloatinIp(OpenStackCredentials.OPENSTACK_IP,
						OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_USER_NAME,
						OpenStackCredentials.OPENSTACK_USER_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME,
						OpenStackCredentials.OPENSTACK_POOL, opensInstId);*/
		launchDao.updatePublicIP(Integer.parseInt(instId), flaotingIpResponse.getFloating_ip());
}

	@Transactional
	public void removeFloatingIP(String instId) throws RemoteException {
		String opensInstId = launchDao.getOpenStackInstanceId(Integer
				.parseInt(instId));
		int returnCode = openStackServices.removeFloatinIp(opensInstId,
				OpenStackCredentials.OPENSTACK_INTERFACE,
				OpenStackCredentials.OPENSTACK_KEYENDPOINT, 
				OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD,
				OpenStackCredentials.OPENSTACK_REGION, OpenStackCredentials.OPENSTACK_USER_TENANTNAME, OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME);
		
		/*(
				OpenStackCredentials.OPENSTACK_KEYENDPOINT,
				OpenStackCredentials.OPENSTACK_INTERFACE,
				OpenStackCredentials.OPENSTACK_REGION,
				OpenStackCredentials.OPENSTACK_USER_NAME,
				OpenStackCredentials.OPENSTACK_USER_PASSWORD,
				OpenStackCredentials.OPENSTACK_USER_TENANTNAME,
				OpenStackCredentials.OPENSTACK_POOL, opensInstId);*/
		if (returnCode == 1){
			launchDao.updatePublicIPToNull(Integer.parseInt(instId));
		}
	}

	@Transactional
	public EnvironmentVM getEnvVmDetailsByID(Integer envVMId) {
		return launchDao.getEnvVmDetailsByID(envVMId);
	}

	@Transactional
	public List<EnvironmentVMExt> getVMExtParamsByID(Integer envVMId) {
		return launchDao
				.getVMExtParamsByID(envVMId);
	}

	@Transactional
	public List<EnvironmentVDC> getEnvVdcList() {
		return launchDao.getEnvVdcList();
	}

	@Transactional
	public List<EnvironmentVDC> getEnvVdcListById(int userId) {
		return launchDao.getEnvVdcListById(userId);
	}

	@Transactional
	public List<EnvironmentVM> getEnvVmDetails() {
		return launchDao.getEnvVmDetails();
	}

	@Transactional
	public List<EnvironmentVMExt> getVMExtParams(){
		return launchDao.getVMExtParams();
	}

	@Transactional
	public List<BespokePackages> getBespokeVMAttributes(Integer vmId) {
			List<BespokePackages> bespokePackageList = new ArrayList<BespokePackages>();
			List<String> packageIds = launchDao.getPackagesByVmId(vmId);
			LOGGER.info("packageIds" + packageIds);
			if (!packageIds.isEmpty()) {
				for (String component : packageIds) {
					BespokePackages bespokePackage = new BespokePackages();
					int packageId = Integer.parseInt(component);
					String packageName = launchDao.getPackageNameById(packageId);
					String packageVersion = launchDao.getPackageVersionById(packageId);
					
					List<PackageAttributes> packageAttributes = launchDao
							.getAttributesByPackageId(packageId);
					bespokePackage.setPackage_name(packageName);
					bespokePackage.setPackage_version(packageVersion);
					bespokePackage.setPackageAttributes(packageAttributes);
					bespokePackageList.add(bespokePackage);
				}
			}
	     return bespokePackageList;
	}

	@Transactional
	public VdcProjQuotaMap getProjectFreeQuotaDetails(int vdcId, int projId) {
		 return  launchDao.getProjectFreeQuotaDetails(vdcId,projId);
	}




	@Transactional
	public int getProjAdminId(int projId) {
		String projAdminName = launchDao.getProjOwnerName(projId);
		return corporateGroupDao.getUserIdByuserNameInUsertable(projAdminName);
	}



	@Transactional
	public int getVdcByUser(int adminUserId) {
		return launchDao.getVdcByUser(adminUserId);
	}

	
	
	@Transactional
	public void azureCustomLaunch(AzureDetails azureDetails,HttpSession session) {
		LOGGER.info("Entered Inside LaunchServiceImpl:azureCustomLaunch()");
		
		float fltMemoryValue = 0;
		float cpu = 0;
		CustomVM azureCustomVM = new CustomVM();
		String azureUserName = azureDetails.getAzureUserName();
		String azurePassword = azureDetails.getAzurePassword();
		String azureCnfPassword = azureDetails.getAzureCnfPassword();
		String azureImage = azureDetails.getAzureImage();
		String azureSize = azureDetails.getAzureSize();
		String azureCloudService = azureDetails.getAzureCloudService();
		String azureRegion = azureDetails.getAzureRegion();
		String azureStorageAccount = azureDetails.getAzureStorageAccount();
		String azureEndPoint = azureDetails.getAzureEndPoint();
		String azureVmName = azureDetails.getAzureVmName();
		String azureStartDate = azureDetails.getAzureStartDate();
		String azureEndDate = azureDetails.getAzureEndDate();
		String azureServiceName = azureDetails.getAzureServiceName();
		String azureServiceDesc = azureDetails.getAzureServiceDesc();
		
		float[] fltArry = getAzureSizeDetails(azureSize);
		fltMemoryValue = fltArry[0];
		cpu = fltArry[1];
		LOGGER.info("fltMemoryValue:"+fltMemoryValue+":cpu:"+cpu+":");
	
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		User userSession = (User) session.getAttribute("userValue");
		int userId = userSession.getUsr_id();
		String userName = userSession.getUsr_name();
		
		ServiceReqDts reqDts = new ServiceReqDts();
		ServiceReqMst serviceReqMst = new ServiceReqMst();
		serviceReqMst.setService_req_flow_id(AzureConstants.SERVICE_REQ_FLOW_ID);
		serviceReqMst.setStatus(AzureConstants.AZURE_VDC_STATUS);
		launchDao.addServiceRequest(serviceReqMst);
		
		LOGGER.info("Generated RequestId" + serviceReqMst.getService_req_id());
		reqDts.setService_req_id(serviceReqMst.getService_req_id());
		reqDts.setReq_user_id(userId);
		launchDao.addServiceRequestDts(reqDts);
		
		azureCustomVM.setVm_name(azureVmName);
		azureCustomVM.setHostname(AzureConstants.AZURE_VDC_HOSTNAME);
		azureCustomVM.setMem((int)fltMemoryValue);
		azureCustomVM.setCpu((int)cpu);
		azureCustomVM.setHdd(AzureConstants.AZURE_VDC_HARDDISK);
		azureCustomVM.setVdc_provider(AzureConstants.AZURE_VDC_PROVIDER);
		azureCustomVM.setStatus(AzureConstants.AZURE_VDC_STATUS);
		azureCustomVM.setIp_addr(AzureConstants.AZURE_VDC_IPADDRESS);
		azureCustomVM.setPublic_ip_addr(AzureConstants.AZURE_VDC_PUBLIC_IPADDRESS);
		azureCustomVM.setCreatedAt(df.format(new Date()));
		azureCustomVM.setCreatedBy(userName);
		azureCustomVM.setService_req_dts_id(reqDts.getService_req_dts_id());
		azureCustomVM.setService_req_id(reqDts.getService_req_id());
		azureCustomVM.setIsap_env_vdc_master_vdc_id(AzureConstants.AZURE_VDC_ID);
		azureCustomVM.setVdcId(AzureConstants.AZURE_VDC_ID);
		azureCustomVM.setUsr_id(userId);
		azureCustomVM.setDelete_flag(AzureConstants.AZURE_DELETE_FLAG);
		azureCustomVM.setInstance_id(AzureConstants.AZURE_VDC_INSTANCE_ID);
		azureCustomVM.setStartDate(azureStartDate);
		azureCustomVM.setEndDate(azureEndDate);

		try{
		launchDao.addAzureCustomVMDetails(azureCustomVM );	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	
		int customId = launchDao.getVmCustomIdByVmName(azureCustomVM);
		
		LOGGER.info("fltMemoryValue:"+azureCustomVM.getMem()+":cpu:"+azureCustomVM.getCpu()+":");
		CustomVMExt image = new CustomVMExt(
				AzureConstants.AZURE_CUSTOM_IMAGE,azureImage,customId);
	
		List<CustomVMExt> customVMExtDetails = new ArrayList<CustomVMExt>();
		CustomVMExt flavors = new CustomVMExt(AzureConstants.AZURE_CUSTOM_FLAVOR,
				azureCustomVM.getMem() + " MB  RAM | " + azureCustomVM.getCpu()
						+ "  VCPU ",
				customId);
		//customVMExtDetails.add(networks);
		customVMExtDetails.add(image);
		customVMExtDetails.add(flavors);
		//customVMExtDetails.add(securities);
		//customVMExtDetails.add(key);
		

		for (CustomVMExt cust : customVMExtDetails){
			launchDao.addCustomVMExt(cust);
		}
		
		LOGGER.info("Just before instanciation azureServicesA2 ::");
	/*	String roleSize = "Standard_D1";
		String serviceName = "syntbots-chn";
		String configSetType = "WindowsProvisioningConfiguration";*/

	// can give random values for these below fields
		/*String adminPassword = "Syntel123$";
		String adminUser = "Syntel123";
		String imageName = "windows";
		String roleName = "syntbots-vmTest2";*/
		
		/*String deploymentLabel = "label";
		String deploymentName = "deploymentSyntbots";
		String hostname = "syncgdc";*/
		
		
		CreateVMEP vmDetails = new CreateVMEP();
		//need to be same values
		vmDetails.setRoleSize(AzureConstants.AZURE_ROLE_SIZE);
		vmDetails.setServiceName(AzureConstants.AZURE_SERVICE_NAME);
		vmDetails.setConfigSetType(AzureConstants.AZURE_CONFIG_SET_TYPE);
		
		//Values from UI
		vmDetails.setAdminUser(azureUserName);
		vmDetails.setAdminPassword(azurePassword);
		vmDetails.setImageName(azureImage);
		vmDetails.setRoleName(azureVmName);
		
		//Need to discuss
		vmDetails.setDeploymentLabel(AzureConstants.AZURE_DEPLOYMENT_LABEL);
		vmDetails.setDeploymentName(AzureConstants.AZURE_AZURE_DEPLOYMENT_NAME);
		vmDetails.setHostname(AzureConstants.AZURE_HOST_NAME);
		
		try{
			azureServices.launchAzureVM(vmDetails);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		LOGGER.info("Just before leaving LaunchServiceImpl:azureCustomLaunch()");
		
	}
	
	

public float[] getAzureSizeDetails(String strAureSize){
	
	String subString = "";
	if(strAureSize != "" && strAureSize.length()!=0){
		subString=(String) strAureSize.subSequence(((strAureSize.indexOf("("))+1), strAureSize.indexOf(")"));
	}
	LOGGER.info("subString is:"+subString+":");
	StringTokenizer tokenizer = new StringTokenizer(subString, ",");
	LOGGER.info("tokenizer is:"+tokenizer+":");
	String[] strArry=subString.split(" ");
	String coreValue = "";
	String memoryValue = "";
	float cpu = 0;
	float fltMemoryValue = 0;
	int tokenizerElement = 0;
	while (tokenizer.hasMoreElements()) {
		if(tokenizerElement == 0)
		{
			coreValue = (String) tokenizer.nextElement();
		}
		if(tokenizerElement == 1)
		{
			memoryValue = (String) tokenizer.nextElement();
		}
		LOGGER.info("coreValue is:"+coreValue+":memoryValue is:"+memoryValue+":");
		tokenizerElement++;
	}
	
	if( (coreValue != null) && (coreValue.length() > 0))
	{
		coreValue = coreValue.trim();
		if(coreValue.indexOf("shared") >= 0)
		{
			cpu = 0.5f;
		}
		else
		{
			coreValue = coreValue.replaceAll("shared", "");
			coreValue = coreValue.replaceAll("cores", "");
			coreValue = coreValue.replaceAll("core", "");
			coreValue = coreValue.trim();
			LOGGER.info("coreValue is:"+coreValue+":");
			if(coreValue.length() > 0)
			{
				try{
				cpu = Float.parseFloat(coreValue);
				}catch(NumberFormatException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	if( (memoryValue != null) && (memoryValue.length() > 0))
	{
		memoryValue = memoryValue.trim();
		memoryValue = memoryValue.replaceAll("memory", "");		
		memoryValue = memoryValue.trim();
		LOGGER.info("memoryValue is:"+memoryValue+":");
		if(memoryValue.length() > 0)
		{
			if(memoryValue.indexOf("MB") > 0)
			{
				memoryValue = memoryValue.replaceAll("MB", "");
				memoryValue.trim();
				try{
					fltMemoryValue = Float.parseFloat(memoryValue);
				}catch(NumberFormatException e)
				{
					e.printStackTrace();
				}
			}
			if(memoryValue.indexOf("GB") > 0)
			{
				memoryValue = memoryValue.replaceAll("GB", "");
				memoryValue.trim();
				try{
					fltMemoryValue = Float.parseFloat(memoryValue);
					fltMemoryValue = fltMemoryValue * 1024;
				}catch(NumberFormatException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	float[] fltArry = new float[2] ;
	
	fltArry[0] = fltMemoryValue;
	fltArry[1] = cpu;
	
	LOGGER.info("fltMemoryValue:"+fltMemoryValue+":cpu:"+cpu+":");
	return fltArry;
}

@Transactional
public VMDetails getVmDetailsFromService(Integer varVmId) {
	
	LOGGER.info("Inside serviceImpl getVmDetailsFromServiceInJSON()- GET"+varVmId);
	VMDetails vmDetails = new VMDetails();
	
	String instanceName = null;
	String ipAddress = null;
	String powerState = null;
	String status=null;
	InstanceEndPointDetails instanceEndPointDetails=null;
	List<InstanceEndPointDetails> instanceEndPointDetailsList= new ArrayList();
	
	try {
		
		File fXmlFile = new File("D:\\Roles.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		String rootElement= doc.getDocumentElement().getNodeName();
		LOGGER.info("Root element :" + doc.getDocumentElement().getNodeName());
		
		NodeList statusList = doc.getElementsByTagName("Status");
		if(statusList != null && statusList.getLength() > 0) {
			for(int i = 0 ; i < statusList.getLength();i++) {
				String tempRootEle=statusList.item(i).getParentNode().getNodeName();
				if(tempRootEle==rootElement){
				status = statusList.item(0).getTextContent();
				vmDetails.setStatus(status);
				}
			}	
		}
		
		NodeList nameList = doc.getElementsByTagName("InstanceName");
		LOGGER.info("THe nod list "+nameList);
		if(nameList != null && nameList.getLength() > 0) {
			instanceName = nameList.item(0).getTextContent();
			vmDetails.setInstanceName(instanceName);
		}
	
		NodeList ipAddrsList = doc.getElementsByTagName("IpAddress");
		if(ipAddrsList != null && ipAddrsList.getLength() > 0) {
			ipAddress = ipAddrsList.item(0).getTextContent();
			vmDetails.setIpAddress(ipAddress);
		}
		
		NodeList powerStateList = doc.getElementsByTagName("PowerState");
		if(powerStateList != null && powerStateList.getLength() > 0) {
			powerState = powerStateList.item(0).getTextContent();
			vmDetails.setPowerState(powerState);
		}
		
		
	NodeList instanceEndpointList = doc.getElementsByTagName("InstanceEndpoint");
		if(instanceEndpointList != null && instanceEndpointList.getLength() > 0) {
			for(int i = 0 ; i < instanceEndpointList.getLength();i++) {	
				instanceEndPointDetails= new InstanceEndPointDetails();	
				LOGGER.info("Instance endpoint"+i+".)"+instanceEndpointList.item(i).getNodeName());
				LOGGER.info("\t"+instanceEndpointList.item(i).getTextContent());
				
				if(instanceEndpointList.item(i).hasChildNodes())
				{
				    NodeList instanceEndpointListTemp=instanceEndpointList.item(i).getChildNodes();
				   
					for(int j = 0 ; j < instanceEndpointListTemp.getLength();j++) {
					
						
						if (instanceEndpointListTemp.item(j).getNodeType() == Node.ELEMENT_NODE) {
							LOGGER.info("In Child Loop Node name is:"+instanceEndpointListTemp.item(j).getNodeName()+":Value is :"+instanceEndpointListTemp.item(j).getTextContent()+":");
							LOGGER.info(instanceEndpointListTemp.item(j).getTextContent());
						
							if(instanceEndpointListTemp.item(j).getNodeName().equalsIgnoreCase("name")){
								 instanceEndPointDetails.setName(instanceEndpointListTemp.item(j).getTextContent());
							}
							else if(instanceEndpointListTemp.item(j).getNodeName().equalsIgnoreCase("Vip")){
							     instanceEndPointDetails.setVip(instanceEndpointListTemp.item(j).getTextContent());
							}
							else if(instanceEndpointListTemp.item(j).getNodeName().equalsIgnoreCase("publicPort")){
								 instanceEndPointDetails.setPublicPort(Integer.parseInt(instanceEndpointListTemp.item(j).getTextContent()));
							}		
							else if(instanceEndpointListTemp.item(j).getNodeName().equalsIgnoreCase("localPort")){
								 instanceEndPointDetails.setLocalPort(Integer.parseInt(instanceEndpointListTemp.item(j).getTextContent()));
							}
							else if(instanceEndpointListTemp.item(j).getNodeName().equalsIgnoreCase("protocol")){
								instanceEndPointDetails.setProtocol(instanceEndpointListTemp.item(j).getTextContent());
							}
								
							
						}
					
					}
					instanceEndPointDetailsList.add(instanceEndPointDetails);
					
					for (InstanceEndPointDetails instanceEndPointDetails2 : instanceEndPointDetailsList) {
						LOGGER.info("instanceEndPointDetailsList in service impl VIP  "+instanceEndPointDetails2.getVip());
					}
					
				}
				
			}
			vmDetails.setInstanceEndPointDetails(instanceEndPointDetailsList);
			LOGGER.info("vm details in service impl"+vmDetails.getInstanceEndPointDetails());
			
		}	
	} catch(Exception e)
	{
		e.printStackTrace();
	}

	return vmDetails;
}



	@Transactional
	public void azureCstmLaunchDbupdate(String customVmnames) {
		LOGGER.info("Entered Inside LaunchServiceImpl:azureCstmLaunchDbupdate()");
		try {
			azureServices.azureCstmLaunchDbupdate(customVmnames);
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("Just before leaving LaunchServiceImpl:azureCstmLaunchDbupdate()");

	}



	@Transactional
	public void azureBespkLaunchDbupdate(String bespokeVmNames) {
		LOGGER.info("Entered Inside LaunchServiceImpl:azureBespkLaunchDbupdate()");
		try {
			azureServices.azureBespkLaunchDbupdate(bespokeVmNames);
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("Just before leaving LaunchServiceImpl:azureBespkLaunchDbupdate()");
	}



	@Transactional
	public void azureCstmVmShutDown(String shtdnCustVmName) {
		LOGGER.info("Entered Inside LaunchServiceImpl:azureCstmVmShutDown()");
		try {
			azureServices.azureCstmVmShutDown(shtdnCustVmName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("Just before leaving LaunchServiceImpl:azureCstmVmShutDown()");
	}


}

