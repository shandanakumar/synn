package com.syntel.isap.provisioning.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.syntel.isap.provisioning.bean.Monitor;
import com.syntel.isap.provisioning.bean.Vdc;
import com.syntel.isap.provisioning.bean.SCM;
import com.syntel.isap.provisioning.constants.OpenStackCredentials;
import com.syntel.isap.provisioning.service.IVdcService;

@Controller
public class VdcController {

	@Autowired
	private IVdcService registerService;

	private static final Logger LOGGER = Logger.getLogger(VdcController.class
			.getName());
    
	/**
	 * param
	 * 
	 * @param @param @no params
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping("/register")
	public ModelAndView register(HttpSession req)
			throws Exception {
		LOGGER.info("Inside register()- ");
		ModelAndView model = new ModelAndView("admin/registerVdcAdmin");
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<Vdc> azureVdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
	    azureVdcList=registerService.getAzureVdcList();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("azureVdcList", azureVdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}
	
	/**
	 * param
	 * 
	 * @param  @ModelAttribute openstackVdc 
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertVdc", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("openstackVdc") Vdc openstackVdc) 
			throws Exception {
		LOGGER.info("Inside insertVdc()-POST ");
		int scmDetails,monDetails;
		ModelAndView model = new ModelAndView("admin/registerVdcAdmin");
		registerService.setOpenstack(openstackVdc);
	   /* scmDetails = registerService.getScmId(openstackVdc.getScm_name());
		openstackVdc.setScm_id(scmDetails);
	    monDetails = registerService.getMonId(openstackVdc.getMon_name());
		openstackVdc.setMon_id(monDetails);
		registerService.setMap(openstackVdc);*/
		
		
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam keystone_endpoint
	 * @return int
	 */
	@RequestMapping(value = "/checkKeystoneEndpointAvailability", method = RequestMethod.GET)
	public @ResponseBody int getKeystone(HttpSession session,
			@RequestParam("keystone_endpoint") String keystoneEndpoint) {
		LOGGER.info("inside checkKeystoneEndpointAvailability()-GET");
		int keystone;
		keystone = registerService.getKeystone(keystoneEndpoint);
		return keystone;
	}
	
	/**
	 * param
	 * 
	 * @param @RequestParam subnet_address
	 * @return int
	 */
	@RequestMapping(value = "/checkSubnetAddressAvailability", method = RequestMethod.GET)
	public @ResponseBody int getSubnetAddress(HttpSession session,
			@RequestParam("subnet_address") String subnetAddress) {
		LOGGER.info("inside checkSubnetAddressAvailability()-GET");
		int subnet;
		subnet= registerService.getSubnetAddress(subnetAddress);
		return subnet;
	}
    
	/**
	 * param
	 * 
	 * @param @param vdc_id
	 * @return Vdc Object
	 */
	@RequestMapping(value = "/getOpenstack/{vdc_id}", method = RequestMethod.GET)
	public @ResponseBody Vdc getProjectInJSON(@PathVariable Integer vdc_id) {
		LOGGER.info("Inside getProjectInJSON()- GET");
		Vdc vdc = new Vdc();
		vdc = registerService.getDetails(vdc_id);
		return vdc;
	}
	
	/**
	 * param
	 * 
	 * @param  @ModelAttribute openstackVdc 
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/editVdc", method = RequestMethod.POST)
	public ModelAndView UpdateOpenstack(@ModelAttribute("openstackVdc") Vdc openstackVdc)
			throws Exception {
		LOGGER.info("Inside editVdc()- POST");
		ModelAndView model = new ModelAndView("admin/registerVdcAdmin");
		registerService.updateOpenstack(openstackVdc);
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}
    
	/**
	 * param
	 * 
	 * @param  @RequestParam vdcId 
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteOpenstack", method = RequestMethod.GET)
	public ModelAndView deleteOpenstack(
			@RequestParam(value = "vdc_id") Integer vdcId, HttpSession session) 
					throws Exception {
		LOGGER.info("Inside deleteOpenstack()- GET");
		registerService.deleteOpenstack(vdcId);
		ModelAndView model = new ModelAndView( "admin/registerVdcAdmin");
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}

	/**
	 * param
	 * 
	 * @param  @ModelAttribute scm 
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/addpuppet", method = RequestMethod.POST)
	public ModelAndView addpuppet(@ModelAttribute("scm") SCM scm)
			throws Exception {
		LOGGER.info("Inside addpuppet()- POST");
		ModelAndView model = new ModelAndView("admin/registerVdcAdmin");
		registerService.setscmpuppet(scm);
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}
    
	/**
	 * param
	 * 
	 * @param  @ModelAttribute scm 
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/addchef", method = RequestMethod.POST)
	public ModelAndView addchef(@ModelAttribute("scm") SCM scm)
			throws Exception {
		LOGGER.info("Inside addchef()- POST");
		ModelAndView model = new ModelAndView("admin/registerVdcAdmin");
		registerService.setscmchef(scm);
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @param scmid
	 * @return SCM Object
	 */
	@RequestMapping(value = "/editscm/{scmid}", method = RequestMethod.GET)
	public @ResponseBody SCM getscmInJSON(@PathVariable Integer scmid) {
		LOGGER.info("Inside editscmInJSON()- GET");
		SCM scmEdit = new SCM();
		scmEdit = registerService.editScm(scmid);
		return scmEdit;
	}

	/**
	 * param
	 * 
	 * @param  @RequestParam scm_id,scm_endpoint,host_name,scm_name,admin_user,admin_pass
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateScm", method = RequestMethod.POST)
	public ModelAndView getEditScmDetails(HttpSession session,
			@RequestParam(value = "scm_id") Integer scmId, @RequestParam(value = "scm_endpoint") String scmEndpoint,
			@RequestParam(value = "host_name") String hostName, @RequestParam(value = "scm_name") String scmName,
			@RequestParam(value = "admin_user") String adminUser, @RequestParam(value = "admin_pass") String adminPass)
					throws Exception {
		LOGGER.info("Inside updateScm()- POST");
		SCM scm = new SCM();
		scm.setAdmin_pass(adminPass);
		scm.setAdmin_user(adminUser);
		scm.setScm_name(scmName);
		scm.setScm_id(scmId);
		scm.setHost_name(hostName);
		scm.setScm_endpoint(scmEndpoint);
		registerService.update(scm);
		ModelAndView model = new ModelAndView( "admin/registerVdcAdmin");
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}

	/**
	 * param
	 * 
	 * @param  @RequestParam scm_id
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteScm", method = RequestMethod.GET)
	public ModelAndView deleteProject(
			@RequestParam(value = "scm_id") Integer scmId, HttpSession session)
					throws Exception {
		LOGGER.info("Inside deleteScm()- GET");
		registerService.deleteProject(scmId);
		ModelAndView model = new ModelAndView("admin/registerVdcAdmin");
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam scm_endpoint
	 * @return int
	 */
	@RequestMapping(value = "/checkEndpointAvailabilty", method = RequestMethod.GET)
	public @ResponseBody int getEndpoint(HttpSession session,
			@RequestParam("scm_endpoint") String scmEndpoint) {
		LOGGER.info("inside checkEndpointAvailabilty()-GET");
		int endpoint;
		endpoint= registerService.getScmEndpoint(scmEndpoint);
		return endpoint;
	}

	/**
	 * param
	 * 
	 * @param  @RequestParam mon_id
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMonitor", method = RequestMethod.GET)
	public ModelAndView deleteMonitor(
			@RequestParam(value = "mon_id") Integer monId, HttpSession session)
					throws Exception {
		LOGGER.info("Inside deleteMonitor()- GET");
		registerService.deleteMonitor(monId);
		ModelAndView model = new ModelAndView( "admin/registerVdcAdmin");
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @param mon_id
	 * @return Monitor Object
	 */
	@RequestMapping(value = "/editmon/{mon_id}", method = RequestMethod.GET)
	public @ResponseBody Monitor getmonInJSON(@PathVariable Integer mon_id) {
		LOGGER.info("Inside editmonInJSON()- GET");
		Monitor monEdit = new Monitor();
		monEdit = registerService.editMon(mon_id);
		return monEdit;
	}

	/**
	 * param
	 * 
	 * @param  @ModelAttribute monitor
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMonitor", method = RequestMethod.POST)
	public ModelAndView getEditMonitorDetails(HttpSession session,
			@ModelAttribute("monitor") Monitor monitor)
					throws Exception {
		LOGGER.info("Inside updateMonitor()- POST");
		registerService.updateMonitor(monitor);
		ModelAndView model = new ModelAndView("admin/registerVdcAdmin");
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}

	/**
	 * param
	 * 
	 * @param  @ModelAttribute monitor
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/addnagios", method = RequestMethod.POST)
	public ModelAndView addnagios(@ModelAttribute("monitor") Monitor monitor) 
			throws Exception {
		LOGGER.info("Inside addnagios()- POST");
		ModelAndView model = new ModelAndView("admin/registerVdcAdmin");
		registerService.setMonNagio(monitor);
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}

	/**
	 * param
	 * 
	 * @param  @ModelAttribute monitor
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/addzabbix", method = RequestMethod.POST)
	public ModelAndView addzabbix(@ModelAttribute("monitor") Monitor monitor) 
			throws Exception {
		LOGGER.info("Inside addzabbix()-POST ");
		ModelAndView model = new ModelAndView("admin/registerVdcAdmin");
		registerService.setMonZabbix(monitor);
		List<Vdc> vdcList=new ArrayList<Vdc>();
		List<SCM> scmList=new ArrayList<SCM>();
		List<Monitor> monitorList=new ArrayList<Monitor>();
		vdcList = registerService.getUserOpenstack();
		scmList = registerService.getScm();
		monitorList = registerService.getMonitorDetails();
		model.addObject("vdcList", vdcList);
		model.addObject("monitorList", monitorList);
		model.addObject("scmList", scmList);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam mon_endpoint
	 * @return int
	 */
	@RequestMapping(value = "/checkMonitorEndpointAvailabilty", method = RequestMethod.GET)
	public @ResponseBody int getMonitorEndpoint(HttpSession session,
			@RequestParam("mon_endpoint") String monEndpoint) {
		LOGGER.info("inside checkMonitorEndpointAvailabilty()-GET");
		int endpoint;
		endpoint= registerService.getMonEndpoint(monEndpoint);
		return endpoint;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam controller_endpoint
	 * @return int
	 */
	@RequestMapping(value = "/checkControllerEndpointAvailability", method = RequestMethod.GET)
	public @ResponseBody int getControllerEndpoint(HttpSession session,
			@RequestParam("controller_endpoint") String controllerEndpoint) {
		LOGGER.info("inside checkControllerEndpointAvailability()-GET");
		int endpoint ;
		endpoint=registerService.getControllerEndpoint(controllerEndpoint);
		return endpoint;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam vdc_name
	 * @return int
	 */
	@RequestMapping(value = "/checkVdcNameAvailability", method = RequestMethod.GET)
	public @ResponseBody int getVdcName(HttpSession session,
			@RequestParam("vdc_name") String vdcName) {
		LOGGER.info("inside checkVdcNameAvailability()-GET");
		int name;
		name = registerService.getVdcName(vdcName);
		return name;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam scm_name
	 * @return int
	 */
	@RequestMapping(value = "/checkScmNameAvailability", method = RequestMethod.GET)
	public @ResponseBody int getScmName(HttpSession session,
			@RequestParam("scm_name") String scmName) {
		LOGGER.info("inside checkScmNameAvailability()-GET");
		int name;
		name = registerService.getScmName(scmName);
		return name;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam mon_name
	 * @return int
	 */
	@RequestMapping(value = "/checkMonNameAvailability", method = RequestMethod.GET)
	public @ResponseBody int getMonName(HttpSession session,
			@RequestParam("mon_name") String monName) {
		LOGGER.info("inside checkMonNameAvailability()-GET");
		int name;
	    name = registerService.getMonName(monName);
		return name;
	}

	/**
	 * param
	 * 
	 * @param @RequestParam subnet_address,tenant,user,password
	 * @return int
	 */
	@RequestMapping(value = "/testOpenstakConnection", method = RequestMethod.POST)
	public @ResponseBody int testConnection(
			@RequestParam("keystone_endpoint") String keystone_endpoint,
			@RequestParam("region") String region,
			@RequestParam("tenant") String tenant,
			@RequestParam("user") String user,
			@RequestParam("password") String password)
	{
		LOGGER.info("inside ajax() testConnection-" + keystone_endpoint);
		int response = 0;
		response = registerService.checkConnection(keystone_endpoint, tenant, user, password);
		LOGGER.info("Responsecode: "+response);
		return response;
	}

	@RequestMapping(value = "/getUsage", method = RequestMethod.POST)
	public @ResponseBody int getUsage(@RequestParam("vdcId") int vdcId) {
		LOGGER.info("inside ajax() getUsage-" + vdcId);
		int response = 0;
		registerService.getUsage(vdcId);
		return response;
	}
}