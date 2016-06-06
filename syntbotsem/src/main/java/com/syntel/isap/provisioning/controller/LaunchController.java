package com.syntel.isap.provisioning.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.isap.core.IsapCoreWebservicesStub.GetOpenstackFlavorResp;
import com.syntbots.azure.SyntBotsAzureWebServicesStub.CreateVMEP;
import com.syntel.isap.provisioning.bean.AzureDetails;
import com.syntel.isap.provisioning.bean.AzureEndPointDTO;
import com.syntel.isap.provisioning.bean.BespokePackages;
import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.EnvironmentVDC;
import com.syntel.isap.provisioning.bean.EnvironmentVM;
import com.syntel.isap.provisioning.bean.EnvironmentVMExt;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.ServiceReqMst;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VMDetails;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VmProvision;
import com.syntel.isap.provisioning.constants.OpenStackCredentials;
import com.syntel.isap.provisioning.service.ILaunchService;
import com.syntel.isap.provisioning.soap.AzureServicesA2;
import com.syntel.isap.provisioning.soap.OpenStackServiceA2;
import com.syntel.isap.provisioning.soap.SecurityRules;

/**
 * @author KK5007843
 *
 */

@Controller
@SessionAttributes("bespokeSession")
public class LaunchController {

	@Autowired
	private ILaunchService launchService;


	private OpenStackServiceA2 openStackServices = new OpenStackServiceA2();

	private static final Logger LOGGER = Logger
			.getLogger(LaunchController.class.getName());

	/**
	 * param
	 * 
	 * @param @RequestParam network,image,security,flavor @ModelAttribute
	 *        serviceReqMst and customVM
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/customLaunch", method = RequestMethod.POST)
	public ModelAndView customLaunch(HttpSession session,
			@ModelAttribute("customVM") CustomVM customVM,
			@ModelAttribute("serviceReqMst") ServiceReqMst serviceReqMst,
			@RequestParam("network") String network,
			@RequestParam("image") String image,
			@RequestParam("keypair") String keypair,
			@RequestParam("flavor") String flavor,
			@RequestParam("security") String security) throws Exception {
		List<CustomVM> customList = new ArrayList<CustomVM>();
		LOGGER.info("Inside customLaunch()- Post");
		User userSession = (User) session.getAttribute("userValue");
		int userId = userSession.getUsr_id();
		int cgId = userSession.getCg_id();
		int dptId = userSession.getDpt_id();
		int projId = userSession.getProj_id();
	
		customVM.setUsr_id(userId);
		ModelAndView model = new ModelAndView();
		int adminUserId = launchService.getProjAdminId(projId);
		int vdcId = launchService.getVdcByUser(adminUserId);
		launchService.customLaunch(keypair, image, network, security, flavor,
				customVM, serviceReqMst,vdcId);
				
		customList = launchService.getCustomListAndQuotaManage(userId,vdcId,adminUserId,cgId,dptId,projId);
		String view = "provision/customUserServiceList";
		model.addObject("list", customList);
		model.setViewName(view);
		return model;
	}

	
	
	
	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/customUserServiceList", method = RequestMethod.GET)
	public ModelAndView customUserServiceList(HttpSession session)
			throws Exception {
		List<CustomVM> customList = new ArrayList<CustomVM>();
		ModelAndView model = new ModelAndView();
		String view = "provision/customUserServiceList";
		LOGGER.info("Inside customUserServiceList()- Get");
		User user = (User) session.getAttribute("userValue");
		if (user != null){
			customList = launchService.getCustomList(user.getUsr_id());
		}
		model.addObject("list", customList);
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam vmId,keypair,@ModelAttribute vmProvision
	 * @return model
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/bespokeLaunch", method = RequestMethod.POST)
	public ModelAndView bespokeLaunch(@RequestParam("keypair") String keypair,
			@RequestParam("vmId") String vmId,
			@ModelAttribute("vmProvision") VmProvision vmProvision,
			HttpServletRequest req, HttpSession session) throws Exception {
		ModelAndView model = new ModelAndView();
		String view = "provision/bespokeServiceList";
		LOGGER.info("Inside bespokeLaunch()- Post");
		User userSession = (User) session.getAttribute("userValue");
		launchService.bespokeLaunch(keypair, vmProvision, userSession, req,
				vmId);
		List<VmProvision> bespokeList = launchService
				.getBespokeList(userSession.getUsr_id());
		model.addObject("bespokeSession", bespokeList);
		model.addObject("list", bespokeList);
		model.setViewName(view);
		return model;
	}
	
	
	@RequestMapping(value = "/azurecustomlaunch", method = RequestMethod.POST)
	public @ResponseBody String azureCustomLaunch(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		LOGGER.info("Inside azureCustomLaunch()- Post");
		System.out.println("Inside azureCustomLaunch()- Post");
		String strAzureDetails = request.getParameter("azureDetails");
			System.out.println("strAzureDetails"+strAzureDetails);
		JSONObject azureDetailsJSONObj=new JSONObject();
		HttpSession session = request.getSession(true);
		AzureDetails azureDetails = new AzureDetails();
		if(strAzureDetails != null)
		{
			azureDetailsJSONObj=new JSONObject(strAzureDetails);
			System.out.println("inside launchAzureVM azureDetailsJSONObj"+azureDetailsJSONObj);
			azureDetails.setAzureUserName(azureDetailsJSONObj.getString("azureUserName"));
			azureDetails.setAzurePassword(azureDetailsJSONObj.getString("azurePassword"));
			azureDetails.setAzureCnfPassword(azureDetailsJSONObj.getString("azureCnfPassword"));
			azureDetails.setAzureImage(azureDetailsJSONObj.getString("azureImage"));
			azureDetails.setAzureSize(azureDetailsJSONObj.getString("azureSize"));
			azureDetails.setAzureCloudService(azureDetailsJSONObj.getString("azureCloudService"));
			azureDetails.setAzureRegion(azureDetailsJSONObj.getString("azureRegion"));
			azureDetails.setAzureStorageAccount(azureDetailsJSONObj.getString("azureStorageAccount"));
			azureDetails.setAzureEndPoint(azureDetailsJSONObj.getString("azureEndPoint"));
			azureDetails.setAzureVmName(azureDetailsJSONObj.getString("azureVmName"));
			azureDetails.setAzureStartDate(azureDetailsJSONObj.getString("azureStartDate"));
			azureDetails.setAzureEndDate(azureDetailsJSONObj.getString("azureEndDate"));
			azureDetails.setAzureServiceName(azureDetailsJSONObj.getString("azureServiceName"));
			azureDetails.setAzureServiceDesc(azureDetailsJSONObj.getString("azureServiceDesc"));
			System.out.println("inside launchAzureVM azureDetails::"+azureDetails);
			
			try{
				launchService.azureCustomLaunch(azureDetails,session);	
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		
		LOGGER.info("Just before leaving LaunchController:PazureCustomLaunch()- Post");	

		return "{}";
	}
	
	
	
	@RequestMapping(value = "/azurecustomlaunchdbupdate", method = RequestMethod.POST)
	public @ResponseBody String azureCustomLaunchDBUpdateDbRole(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		LOGGER.info("Inside azureCustomLaunchDBUpdateDbRole()- Post");
		String customVmnames = request.getParameter("strJsonObj");
		LOGGER.info("customVmnames"+customVmnames);
		if(customVmnames!=null){
			try{
		launchService.azureCstmLaunchDbupdate(customVmnames);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return "{}";
	
	}
	

	@RequestMapping(value = "/azurecustomvmshutdown", method = RequestMethod.POST)
	public @ResponseBody String azureCustomVmShutdown(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		LOGGER.info("Inside azurecustomvmshutdown()- Post");
		String shtdnCustVmName = request.getParameter("strShtdwnVmName");
		LOGGER.info("shtdnCustVmName"+shtdnCustVmName+":");
		if(shtdnCustVmName!=null){
			try{
		launchService.azureCstmVmShutDown(shtdnCustVmName);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return "{}";
	
	}
	
	/**
	 * param
	 * 
	 * @param HttpServletRequest request, HttpSession session
	 * @return model
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/bespokeAzureLaunch", method = RequestMethod.POST)
	public @ResponseBody String bespokeAzureLaunch(HttpServletRequest request, HttpSession session) throws Exception {
		//ModelAndView model = new ModelAndView();
		//String view = "provision/bespokeServiceList";
		LOGGER.info("Inside bespokeAzureLaunch()- Post");
		System.out.println("Inside bespokeAzureLaunch()- Post");
		User userSession = (User) session.getAttribute("userValue");
		String strAzureDetails = request.getParameter("azureBespokeDetails");
		JSONObject azureDetailsJSONObj=new JSONObject();
		System.out.println("strAzureDetails is:"+strAzureDetails+":");
		AzureDetails azureDetails = new AzureDetails();
		if(strAzureDetails != null)
		{
			azureDetailsJSONObj=new JSONObject(strAzureDetails);
			System.out.println("inside launchAzureVM azureDetailsJSONObj"+azureDetailsJSONObj);
			azureDetails.setAzureUserName(azureDetailsJSONObj.getString("azureUserName"));
			azureDetails.setAzurePassword(azureDetailsJSONObj.getString("azurePassword"));
			azureDetails.setAzureCnfPassword(azureDetailsJSONObj.getString("azureCnfPassword"));
			//azureDetails.setAzureEndPoint(azureDetailsJSONObj.getString("azureEndPoint"));
			azureDetails.setAzureStartDate(azureDetailsJSONObj.getString("azureStartDate"));
			azureDetails.setAzureEndDate(azureDetailsJSONObj.getString("azureEndDate"));
			azureDetails.setAzureVmId(azureDetailsJSONObj.getString("azureVmId"));
			System.out.println("inside launchAzureVM azureDetails::"+azureDetails);
		}
		launchService.bespokeAzureLaunch(userSession, azureDetails);
		
		//from here integrate aamani's code
		/*List<VmProvision> bespokeList = launchService
				.getBespokeList(userSession.getUsr_id());
		model.addObject("bespokeSession", bespokeList);
		model.addObject("list", bespokeList);
		model.setViewName(view);
		return model;*/
		return "";
	}
	
	
	@RequestMapping(value = "/azurebespokelaunchdbupdate", method = RequestMethod.POST)
	public @ResponseBody String azureBespokeLaunchDBUpdateDbRole(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		LOGGER.info("Inside azureBespokeLaunchDBUpdateDbRole()- Post");
		String bespokeVmNames = request.getParameter("strJsonObj");
		LOGGER.info("vmNames"+bespokeVmNames);
		if(bespokeVmNames!=null){
			try{
		launchService.azureBespkLaunchDbupdate(bespokeVmNames);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return "{}";
	
	}
	
	/**
	 * param
	 * 
	 * @param @RequestParam vmId,keypair,@ModelAttribute vmProvision
	 * @return model
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/bespokeLaunch", method = RequestMethod.GET)
	public ModelAndView bespokeLaunchGet(HttpSession session) throws Exception {
		ModelAndView model = new ModelAndView();
		String view = "provision/bespokeServiceList";
		LOGGER.info("Inside bespokeLaunchGet()- Get");
		User userSession = (User) session.getAttribute("userValue");
		List<VmProvision> bespokeList = launchService
				.getBespokeList(userSession.getUsr_id());
		model.addObject("bespokeSession", bespokeList);
		model.addObject("list", bespokeList);
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @none
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/bespokeUserServiceList", method = RequestMethod.GET)
	public ModelAndView bespokeUserServiceList(HttpSession session)
			throws Exception {
		ModelAndView model = new ModelAndView();
		String view = "provision/bespokeServiceList";
		LOGGER.info("Inside bespokeUserServiceList()- Get");
		User userSession = (User) session.getAttribute("userValue");
		List<VmProvision> bespokeList = launchService
				.getBespokeList(userSession.getUsr_id());
		model.addObject("bespokeSession", bespokeList);
		model.addObject("list", bespokeList);
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam instId
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/terminateInstance", method = RequestMethod.POST)
	public ModelAndView terminateInstance(HttpSession session,
			@RequestParam("instId") String instId) throws Exception {
		List<CustomVM> customList = new ArrayList<CustomVM>();
		ModelAndView model = new ModelAndView();
		String view = "provision/customUserServiceList";
		LOGGER.info("Inside terminateInstance()- POST");
		User user = (User) session.getAttribute("userValue");
		int projId = user.getProj_id();
		int adminUserId = launchService.getProjAdminId(projId);
		int vdcId = launchService.getVdcByUser(adminUserId);
		launchService.terminateInstance(instId,projId,vdcId,adminUserId);
		customList = launchService.getCustomList(user.getUsr_id());
		model.addObject("list", customList);
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam instId
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/addFloatingIP", method = RequestMethod.POST)
	public ModelAndView addFloatingIP(HttpSession session,
			@RequestParam("instId") String instId) throws Exception {
		List<CustomVM> customList = new ArrayList<CustomVM>();
		ModelAndView model = new ModelAndView();
		String view = "provision/customUserServiceList";
		LOGGER.info("Inside associateFloatingIP()- POST");
		User user = (User) session.getAttribute("userValue");
		launchService.addFloatingIP(instId);
		customList = launchService.getCustomList(user.getUsr_id());
		model.addObject("list", customList);
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam instId
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeFloatingIP", method = RequestMethod.POST)
	public ModelAndView removeFloatingIP(HttpSession session,
			@RequestParam("instId") String instId) throws Exception {
		List<CustomVM> customList = new ArrayList<CustomVM>();
		ModelAndView model = new ModelAndView();
		String view = "provision/customUserServiceList";
		LOGGER.info("Inside removeFloatingIP()- POST");
		User user = (User) session.getAttribute("userValue");
		launchService.removeFloatingIP(instId);
		customList = launchService.getCustomList(user.getUsr_id());
		model.addObject("list", customList);
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @no params
	 * @return model
	 */
	@RequestMapping(value = "/customLaunch", method = RequestMethod.GET)
	public ModelAndView customGetLaunch() {
		LOGGER.info("Inside customLaunch()- Get");
		ModelAndView model = new ModelAndView();
		String view = "provision/customTenantUser";
		model.setViewName(view);
		return model;
	}

	@RequestMapping(value = "/templateList", method = RequestMethod.GET)
	public ModelAndView getTemplateList(HttpSession session) {
		LOGGER.info("Inside getTemplateList()- Get");
		ModelAndView model = new ModelAndView();
		List<EnvironmentVM> envVMList = launchService.getEnvVmDetails();
		session.setAttribute("envVMList", envVMList);
		String view = "department/templateListPage";
		model.setViewName(view);
		return model;
	}

	@RequestMapping(value = "/addTemplate", method = RequestMethod.GET)
	public ModelAndView addTemplate(HttpSession session) {
		List<EnvironmentVDC> envVDCList = null;
		ModelAndView model = new ModelAndView();
		String view = "department/bespokeTemplate";
		LOGGER.info("Inside getTemplateList()- Get");
		User user = (User) session.getAttribute("userValue");
		if (user != null){
			envVDCList = launchService.getEnvVdcListById(user.getUsr_id());
		}
		session.setAttribute("envVDCList", envVDCList);
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return model
	 */
	@RequestMapping(value = "/bespokeTenantUser", method = RequestMethod.GET)
	public ModelAndView bespokeTenantUser(HttpSession session) {
		LOGGER.info("Inside bespokeTenantUser()- Get");
		ModelAndView model = new ModelAndView();
		String view = "provision/bespokeTenantUser";
		List<EnvironmentVM> envVMList = launchService.getEnvVmDetails();
		List<EnvironmentVMExt> envVMExtList = launchService.getVMExtParams();
		session.setAttribute("envVMList", envVMList);
		session.setAttribute("envVMExtList", envVMExtList);
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return model
	 */
	@RequestMapping(value = "/stackTenantUser", method = RequestMethod.GET)
	public ModelAndView stackTenantUser() {
		LOGGER.info("Inside stackTenantUser()- Get");
		ModelAndView model = new ModelAndView();
		String view = "provision/stackTenantUser";
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return model
	 */
	@RequestMapping(value = "/userKeyPairList", method = RequestMethod.GET)
	public ModelAndView userKeyPairList(HttpSession session) {
		List<Identity> keyPairList = new ArrayList<Identity>();
		LOGGER.info("Inside userKeyPairList()- Get");
		ModelAndView model = new ModelAndView();
		String view = "provision/userKeyPairList";
		User user = (User) session.getAttribute("userValue");
		if (user != null){
			keyPairList = launchService.getKeyPairList(user.getUsr_id());
		}
		model.addObject("keyPairList", keyPairList);
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @param name
	 * @return pem file
	 */
	@RequestMapping(value = "/addKeyPair", method = RequestMethod.POST)
	@ResponseBody
	public void addKeyPair(HttpServletResponse resp,
			HttpSession session, @RequestParam("name") String keyname)
			throws Exception {
		User user = (User) session.getAttribute("userValue");
		LOGGER.info("Inside addKeyPair()- POST");
		String downloadFileName = keyname + ".pem";
		try {
	
			String data = openStackServices.createOpenstackKeypair(
					keyname,
					OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
					OpenStackCredentials.OPENSTACK_KEYENDPOINT,
					OpenStackCredentials.OPENSTACK_INTERFACE,
					OpenStackCredentials.OPENSTACK_REGION,
					OpenStackCredentials.OPENSTACK_USER_PASSWORD,
					OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
			
			OutputStream out = resp.getOutputStream();
			resp.setContentType("text/plain; charset=utf-8");
			resp.addHeader("Content-Disposition", "attachment; filename=\""
					+ downloadFileName + "\"");
			out.write(data.getBytes(Charset.forName("UTF-8")));
			launchService.addKeyPair(keyname, data, user.getUsr_id());
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error(e);
		}
		LOGGER.info("Inside addKeyPair()- END");
	}

	/**
	 * param
	 * 
	 * @param @param name
	 * @return int
	 */
	@RequestMapping(value = "/checkKeyPair", method = RequestMethod.POST)
	@ResponseBody
	public int checkKeyPair(HttpSession session,
			@RequestParam("name") String name) {
		int check = 0;
		LOGGER.info("Inside checkKeyPair()- POST");
		check = launchService.checkKeyPair(name);
		return check;
	}

	/**
	 * param
	 * 
	 * @param @param name
	 * @return pem file
	 */
	@RequestMapping(value = "/downloadkeyPair", method = RequestMethod.GET)
	@ResponseBody
	public  void downloadkeyPair(HttpServletResponse resp,
			HttpSession session, @RequestParam("keyId") int keyId)
			throws Exception {
		LOGGER.info("Inside downloadkeyPair()- POST");
		Identity identity = launchService.getIdentity(keyId);
		String data = identity.getContent();
		String name = identity.getName();
		String downloadFileName = name + ".pem";
		try {
			OutputStream out = resp.getOutputStream();
			resp.setContentType("text/plain; charset=utf-8");
			resp.addHeader("Content-Disposition", "attachment; filename=\""
					+ downloadFileName + "\"");
			out.write(data.getBytes(Charset.forName("UTF-8")));
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error(e);
		}
		LOGGER.info("Inside downloadkeyPair()- END");
	}

	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return model
	 */
	
	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return json
	 */
	@RequestMapping(value = "/getKeyPairs", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getKeyPairsInJSON(HttpSession session) {
		LOGGER.info("Inside getKeyPairsInJSON()- Get");
		User user = (User) session.getAttribute("userValue");
		return launchService.getKeyPairs(user.getUsr_id());
	}

	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return json
	 */
	@RequestMapping(value = "/getFlavors", method = RequestMethod.GET)
	@ResponseBody
	public  LinkedHashMap<String, String> getFlavorsInJSON() {
		LOGGER.info("Inside getFlavorsInJSON()- Get");
		
		LinkedHashMap<String, String> flavors=null;
		try {
			flavors= openStackServices.getListOpenstackFlavors(
						OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
						OpenStackCredentials.OPENSTACK_KEYENDPOINT,
						OpenStackCredentials.OPENSTACK_INTERFACE,
						OpenStackCredentials.OPENSTACK_REGION,
						OpenStackCredentials.OPENSTACK_USER_PASSWORD,
						OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return flavors;
		}
	
	
	
	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return json
	 */
	@RequestMapping(value = "/getSecurityGroups", method = RequestMethod.GET)
	@ResponseBody
	public LinkedHashMap<String, String> getSecGroupsInJSON() {
		
		LinkedHashMap<String, String> secGrpsList=null; 
		try {
			secGrpsList= openStackServices.getListOpenstackSecurityGroups(
					OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
					OpenStackCredentials.OPENSTACK_KEYENDPOINT,
					OpenStackCredentials.OPENSTACK_INTERFACE,
					OpenStackCredentials.OPENSTACK_REGION,
					OpenStackCredentials.OPENSTACK_USER_PASSWORD,
					OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return secGrpsList;
	}
	
	
	
	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return json
	 */
	@RequestMapping(value = "/getImages", method = RequestMethod.GET)
	@ResponseBody
	public LinkedHashMap<String, String> getImagesInJSON() {
		LinkedHashMap<String, String> secGrpsList=null; 
		try {
			secGrpsList= openStackServices.getListOpenstackImages(
					OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
					OpenStackCredentials.OPENSTACK_KEYENDPOINT,
					OpenStackCredentials.OPENSTACK_INTERFACE,
					OpenStackCredentials.OPENSTACK_REGION,
					OpenStackCredentials.OPENSTACK_USER_PASSWORD,
					OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return secGrpsList;
	}
	
	
	
	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return json
	 */
	@RequestMapping(value = "/getNetworks", method = RequestMethod.GET)
	@ResponseBody
	public LinkedHashMap<String, String> getNetworksInJSON() {
		
		LinkedHashMap<String, String> netWorkList=null; 
		try {
			netWorkList= openStackServices.getListOpenstackNetworks(
					OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
					OpenStackCredentials.OPENSTACK_KEYENDPOINT,
					OpenStackCredentials.OPENSTACK_INTERFACE,
					OpenStackCredentials.OPENSTACK_REGION,
					OpenStackCredentials.OPENSTACK_USER_PASSWORD,
					OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return netWorkList;
	}
	

	
	@RequestMapping(value = "/getFlavorDetails/{flavorId}", method = RequestMethod.GET)
	@ResponseBody
	public GetOpenstackFlavorResp getFlavorDetailsInJSON(
			@PathVariable String flavorId) {
		LOGGER.info("Inside getFlavorDetailsInJSON()- Get");	
		GetOpenstackFlavorResp flavorResp=null;
		try {
			flavorResp= openStackServices.getOpenstackFlavorDetails(
					flavorId,
					OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,
					OpenStackCredentials.OPENSTACK_KEYENDPOINT,
					OpenStackCredentials.OPENSTACK_INTERFACE,
					OpenStackCredentials.OPENSTACK_REGION,
					OpenStackCredentials.OPENSTACK_USER_PASSWORD,
					OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return flavorResp;
	}
	
	


	@RequestMapping(value = "/getSecurityGroupDetails/{securityGroupId}", method = RequestMethod.GET)
	@ResponseBody
	public List<SecurityRules> getSecurityGroupDetailsInJSON(
			@PathVariable String securityGroupId){
		List<SecurityRules> listSecRules=null;
			try {
				listSecRules= openStackServices
						.getSecurityGroupDetails(
								OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,		
								OpenStackCredentials.OPENSTACK_KEYENDPOINT,
								OpenStackCredentials.OPENSTACK_INTERFACE,
								OpenStackCredentials.OPENSTACK_REGION,
								OpenStackCredentials.OPENSTACK_USER_PASSWORD,
								OpenStackCredentials.OPENSTACK_USER_TENANTNAME,
								securityGroupId);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return listSecRules;
		
	}
	
	
	/*
	@RequestMapping(value = "/getSecurityGroupDetails/{securityGroupId}", method = RequestMethod.GET)
	@ResponseBody
	public List<SecurityRules> getSecurityGroupDetailsInJSON(
			@PathVariable String securityGroupId) {
		LOGGER.info("Inside getSecurityGroupDetailsInJSON()- Get");
		try {
			return (List<SecurityRules>) openStackServices
					.getOpenstackSecurityGroupDetails(
							OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME,		
							OpenStackCredentials.OPENSTACK_KEYENDPOINT,
							OpenStackCredentials.OPENSTACK_INTERFACE,
							OpenStackCredentials.OPENSTACK_REGION,
							OpenStackCredentials.OPENSTACK_USER_PASSWORD,
							OpenStackCredentials.OPENSTACK_USER_TENANTNAME,
							securityGroupId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	

	
	
	/*
	 * 



	*//**
	 * param
	 * 
	 * @param @PathVariable securityGroupId
	 * @return json
	 *//*
	
*/
	/**
	 * param
	 * 
	 * @param @no param
	 * @return json
	 */
	@RequestMapping(value = "/getVMAttributes/{vmId}", method = RequestMethod.GET)
	@ResponseBody
	public List<BespokePackages> getBespokeVMAttributesInJSON(
			@PathVariable Integer vmId) {
		return launchService.getBespokeVMAttributes(vmId);
	}
	
	@RequestMapping(value = "/getProjectFreeQuotaDetails", method = RequestMethod.GET)
	@ResponseBody
	public VdcProjQuotaMap getProjectFreeQuotaDetails(HttpSession session) {
		LOGGER.info("Inside getProjectFreeQuotaDetailsinJSOn()- Get");
		User userSession = (User) session.getAttribute("userValue");
		VdcProjQuotaMap vdcProjQuotaMapBean = new VdcProjQuotaMap();
		int projId = userSession.getProj_id();
		LOGGER.info("Inside getting proj id of user+++++++++++++++++++++++"+ projId);
		int adminUserId = launchService.getProjAdminId(projId);
		int vdcId = launchService.getVdcByUser(adminUserId);
		vdcProjQuotaMapBean = launchService.getProjectFreeQuotaDetails(vdcId,projId);
		
		return vdcProjQuotaMapBean;
		
		
	}
	

	
	
	@RequestMapping(value = "/getazureimages", method = RequestMethod.GET)
	@ResponseBody
	public LinkedHashMap<String, String> getAzureImage() {
		LOGGER.info("Inside getazureimages");
		
		LinkedHashMap<String, String> azureImages = new LinkedHashMap<String, String>();
	/*	azureImages.put("WindowsServer2012R2Datacenter0__Image__a699494373c04fc0bc8f2bb1389d6106__Windows-Server-2012-R2-201506.01-en.us-127GB.vhd", "Windows Server 2012 R2 Datacenter");
		azureImages.put("0__Image__a699494373c04fc0bc8f2bb1389d6106__Windows-Server-2012-Datacenter-201506.01-en.us-127GB.vhd", "Windows Server 2012 Datacenter");
		azureImages.put("0__Image__a699494373c04fc0bc8f2bb1389d6106__Win2K8R2SP1-Datacenter-201506.01-en.us-127GB.vhd", "Windows Server 2012 R2 SP1");
		azureImages.put("0__Image__b39f27a8b8c64d52b05eac6a62ebad85__Ubuntu-12_04_5-LTS-amd64-server-20150728-en-us-30GB", "Ubuntu Server 12.04 LTS");
		azureImages.put("0__Image__b39f27a8b8c64d52b05eac6a62ebad85__Ubuntu-14_04_2-LTS-amd64-server-20150706-en-us-30GB", "Ubuntu Server 14.04 LTS");
		*/
		
		azureImages.put("Windows Server 2012 R2 Datacenter", "Windows Server 2012 R2 Datacenter");
		azureImages.put("Windows Server 2012 Datacenter", "Windows Server 2012 Datacenter");
		azureImages.put("Windows Server 2012 R2 SP1", "Windows Server 2012 R2 SP1");
		azureImages.put("Ubuntu Server 12.04 LTS", "Ubuntu Server 12.04 LTS");
		azureImages.put("Ubuntu Server 14.04 LTS", "Ubuntu Server 14.04 LTS");
		
		LOGGER.info("Jusy Before leaving getazureimages()");
		return azureImages;
		}
	
	
	@RequestMapping(value = "/getazuresize", method = RequestMethod.GET)
	@ResponseBody
	public LinkedHashMap<String, String> getAzureSize() {
		LOGGER.info("Inside getazuresize");
		
		LinkedHashMap<String, String> azureSize = new LinkedHashMap<String, String>();
		azureSize.put("A0(sharedcore,768MBmemory)", "A0 (shared core,768 MB memory)");
		azureSize.put("A1(1core,1.75GBmemory)", "A1 (1 core,1.75 GB memory)");
		azureSize.put("A2(2core,3.5GBmemory)", "A2 (2 core,3.5 GB memory)");
		azureSize.put("A3(4core,7GBmemory)", "A3 (4 core,7 GB memory)");
		azureSize.put("A4(8core,14GBmemory)", "A4 (8 core,14 GB memory)");
		LOGGER.info("Jusy Before leaving getazuresize()");
		return azureSize;
		}

	

	@RequestMapping(value = "/getazurecloudservices", method = RequestMethod.GET)
	@ResponseBody
	public LinkedHashMap<String, String> getCloudServices() {
		LOGGER.info("Inside getazurecloudservices");
		LinkedHashMap<String, String> cloudServiceslist = new LinkedHashMap<String, String>();
		cloudServiceslist.put("createanewcloudservice", "create a new cloud service");
		cloudServiceslist.put("syntbots-chn", "syntbots-chn");
		cloudServiceslist.put("syntbots-test", "syntbots-test");
		cloudServiceslist.put("70bbde64-aa9a-49ac-9b72-3979874ffb7f__testSyntbots__testSyntbots", "testSyntbots");
		LOGGER.info("Jusy Before leaving getazurecloudservices()");
		return cloudServiceslist;
		}
	
	
	@RequestMapping(value = "/getazureregions", method = RequestMethod.GET)
	@ResponseBody
	public  LinkedHashMap<String, String> getAzureRegion() {
		LOGGER.info("Inside getazureregions()- Get");
		
		LinkedHashMap<String, String> azureRegion = new LinkedHashMap<String, String>();
		azureRegion.put("BrazilSouth", "Brazil South");
		azureRegion.put("EastUS", "East US");
		azureRegion.put("CentralUS", "Central US");
		azureRegion.put("NorthCentralUS", "North Central US");
		azureRegion.put("SouthCentralUS", "South Central US");
		azureRegion.put("WestUS", "West US");
		azureRegion.put("EastUS2", "East US 2");
		azureRegion.put("NorthEurope", "North Europe");
		azureRegion.put("WestEurope", "West Europe");
		azureRegion.put("EastAsia", "East Asia");
	
		LOGGER.info("Jusy Before leaving getazureregions()");
		return azureRegion;
		}
	
	
	
	@RequestMapping(value = "/getazureStorageAccounts", method = RequestMethod.GET)
	@ResponseBody
	public  LinkedHashMap<String, String> getazureStorageAccounts() {
		LOGGER.info("Inside getazureStorageAccounts()");
		
		LinkedHashMap<String, String> azureStorageAccountList = new LinkedHashMap<String, String>();
		azureStorageAccountList.put("Use an automatically generated storage account", "Use an automatically generated storage account");
		azureStorageAccountList.put("70bbde64-aa9a-49ac-9b72-3979874ffb7f__portalvhdsyw3hzj2tk17lm", "portalvhdsyw3hzj2tk17lm");
	
		LOGGER.info("Jusy Before leaving getazureStorageAccounts()");
		return azureStorageAccountList;
		}
	
	
	@RequestMapping(value = "/getazureendpoints", method = RequestMethod.GET)
	@ResponseBody
	public  LinkedHashMap<String, String> getAzureEndPoint() {
		LOGGER.info("Inside getAzureEndPoints()- Get");
		
		LinkedHashMap<String, String> azureEndPointList = new LinkedHashMap<String, String>();
		azureEndPointList.put("RemoteDesktop", "Remote Desktop");
		azureEndPointList.put("PowerShell", "PowerShell");
		azureEndPointList.put("HTTPS", "HTTPS");
		azureEndPointList.put("LDAP", "LDAP");
		azureEndPointList.put("DNS", "DNS");
		azureEndPointList.put("POP3", "POP3");
		azureEndPointList.put("IMAPS", "IMAPS");
		LOGGER.info("Jusy Before leaving getAzureEndPoints()");
		return azureEndPointList;
		}
	
	
	@RequestMapping(value = "/getazureendpointsbyid/{azureEndPointId}", method = RequestMethod.GET)
	@ResponseBody
	public AzureEndPointDTO getAzureEndPointDetailsById(
			@PathVariable String azureEndPointId) {
		LOGGER.info("Inside getAzureEndPointDetailsById()- Get:azureEndPointId:"+azureEndPointId);
		AzureEndPointDTO azureEndPointDetails= new AzureEndPointDTO();
		List<AzureEndPointDTO> azureEndPointsList = new ArrayList<AzureEndPointDTO>(); 
		AzureEndPointDTO azureEndPointDetails1= new AzureEndPointDTO();
		azureEndPointDetails1.setEndPointName("RemoteDesktop");
		azureEndPointDetails1.setProtocol("TCP");
		azureEndPointDetails1.setPublicPort(3389);
		azureEndPointDetails1.setPrivatePort(3389);
		
		AzureEndPointDTO azureEndPointDetails2= new AzureEndPointDTO();
		azureEndPointDetails2.setEndPointName("PowerShell");
		azureEndPointDetails2.setProtocol("TCP");
		azureEndPointDetails2.setPublicPort(5986);
		azureEndPointDetails2.setPrivatePort(5986);
		
		AzureEndPointDTO azureEndPointDetails3= new AzureEndPointDTO();
		azureEndPointDetails3.setEndPointName("HTTPS");
		azureEndPointDetails3.setProtocol("TCP");
		azureEndPointDetails3.setPublicPort(80);
		azureEndPointDetails3.setPrivatePort(80);
		
		AzureEndPointDTO azureEndPointDetails4= new AzureEndPointDTO();
		azureEndPointDetails4.setEndPointName("LDAP");
		azureEndPointDetails4.setProtocol("TCP");
		azureEndPointDetails4.setPublicPort(389);
		azureEndPointDetails4.setPrivatePort(389);
		
		
		AzureEndPointDTO azureEndPointDetails5= new AzureEndPointDTO();
		azureEndPointDetails5.setEndPointName("DNS");
		azureEndPointDetails5.setProtocol("TCP");
		azureEndPointDetails5.setPublicPort(53);
		azureEndPointDetails5.setPrivatePort(53);
		
		AzureEndPointDTO azureEndPointDetails6= new AzureEndPointDTO();
		azureEndPointDetails6.setEndPointName("POP3");
		azureEndPointDetails6.setProtocol("TCP");
		azureEndPointDetails6.setPublicPort(110);
		azureEndPointDetails6.setPrivatePort(110);
		
		AzureEndPointDTO azureEndPointDetails7= new AzureEndPointDTO();
		azureEndPointDetails7.setEndPointName("IMAP");
		azureEndPointDetails7.setProtocol("TCP");
		azureEndPointDetails7.setPublicPort(143);
		azureEndPointDetails7.setPrivatePort(143);
		
		azureEndPointsList.add(azureEndPointDetails1);
		azureEndPointsList.add(azureEndPointDetails2);
		azureEndPointsList.add(azureEndPointDetails3);
		azureEndPointsList.add(azureEndPointDetails4);
		azureEndPointsList.add(azureEndPointDetails5);
		azureEndPointsList.add(azureEndPointDetails6);
		azureEndPointsList.add(azureEndPointDetails7);
		

		for(AzureEndPointDTO azureEndPointDTO:azureEndPointsList){
			if(azureEndPointId.equals(azureEndPointDTO.getEndPointName())){
				azureEndPointDetails.setEndPointName(azureEndPointDTO.getEndPointName());
				azureEndPointDetails.setProtocol(azureEndPointDTO.getProtocol());
				azureEndPointDetails.setPublicPort(azureEndPointDTO.getPublicPort());
				azureEndPointDetails.setPrivatePort(azureEndPointDTO.getPrivatePort());
			}
			
		}
	
	
		
		//GetOpenstackFlavorResp flavorResp=null;
		System.out.println("azureEndPointDetails:"+azureEndPointDetails);
		return azureEndPointDetails;
	}
	
	
	@RequestMapping(value = "/getVMDetailsFromService/{varVmId}", method = RequestMethod.GET)
	public @ResponseBody VMDetails getVmDetailsFromService(@PathVariable Integer varVmId) {
		System.out.println("inside getVmDetailsFromServiceInJSON ");
		VMDetails vmDetails = new VMDetails();
		LOGGER.info("Inside getVmDetailsFromServiceInJSON()- GET"+varVmId);
		
		vmDetails=launchService.getVmDetailsFromService(varVmId);
		
		LOGGER.info("Just before leaving getVmDetailsFromServiceInJSON()"+vmDetails.getPowerState());
		return vmDetails;
	}

	 @RequestMapping(value="/saveAzureBespokeTemplate", method=RequestMethod.POST)
		public ModelAndView saveAzureBespokeTemplate(HttpSession session) {
						
			
			String view = "corporate/blueprint";
			LOGGER.info("Inside saveAzureBespokeTemplate GET");
			ModelAndView model = new ModelAndView();
			User user= (User) session.getAttribute("userValue");
			
			LOGGER.info("user Session Values"+user.getCg_id());
			List<EnvironmentVM> envVMList = launchService.getEnvVmDetails();
			List<EnvironmentVMExt> envVMExtList = launchService.getVMExtParams();
			session.setAttribute("envVMList", envVMList);
			session.setAttribute("envVMExtList", envVMExtList);
			model.setViewName(view);
			return model;
		}
	 
	 @RequestMapping(value = "/blueprint", method = RequestMethod.GET)
		public ModelAndView getBespokeVMAttributesInJSON(
				HttpSession session) {
			LOGGER.info("Inside bluePrintDtlsList()- Get");
			ModelAndView model = new ModelAndView();
			String view = "corporate/bluePrintDetails";
			List<EnvironmentVM> envVMList = launchService.getEnvVmDetails();
			List<EnvironmentVM> envVMPkagList= new ArrayList<EnvironmentVM>();
			List<EnvironmentVMExt> envVMExtList = launchService.getVMExtParams();
			//List<Vdc> vdcList=new ArrayList<Vdc>();
			
		
			for(EnvironmentVM envVM : envVMList){
	        	List<BespokePackages> envVMPackageList=null;
	        	envVMPackageList= launchService.getBespokeVMAttributes(envVM.getVm_master_id());
	        	//vdcList = registerService.getUserOpenstack();
	        	//System.out.println("vdc Details"+vdcList);
	        	//envVM.setVdcList(vdcList);
	        	System.out.println("VDC*******"+envVM.getVdcList());
	        	
	        	envVM.setPkgList(envVMPackageList);
	        	envVMPkagList.add(envVM);
	       	}
			
			session.setAttribute("envVMList", envVMList);
			session.setAttribute("envVMExtList", envVMExtList);
			
			model.setViewName(view);
			return model;
		}

	
}
