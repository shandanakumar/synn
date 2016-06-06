package com.syntel.isap.provisioning.controller;

import java.rmi.RemoteException;
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

import com.isap.core.IsapCoreWebservicesStub.GetOpenstackHypervisorDetailsResp;
import com.syntel.isap.provisioning.bean.AvailableVdcQuota;
import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.EnvironmentVM;
import com.syntel.isap.provisioning.bean.EnvironmentVMExt;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;
import com.syntel.isap.provisioning.bean.VmProvCredential;
import com.syntel.isap.provisioning.constants.OpenStackCredentials;
import com.syntel.isap.provisioning.service.ICorporateGroupService;
import com.syntel.isap.provisioning.service.ILaunchService;
import com.syntel.isap.provisioning.soap.OpenStackServiceA2;

@Controller
public class CorporateGroupController {

	@Autowired
	private ICorporateGroupService corporateGroupService;
	
	@Autowired
	private ILaunchService launchService;
	
	private OpenStackServiceA2 openStackServices = new OpenStackServiceA2();
	
	private static final Logger LOGGER = Logger.getLogger(CorporateGroupController.class.getName());
	
	/**
	 * 
	 * @param session 
	 * @return model
	 */
	@RequestMapping(value="/corporategroups", method=RequestMethod.GET)
	public ModelAndView corporateGroupLists(HttpSession session) {
					
		List<CorporateGroups> corporateGroupList = new ArrayList<CorporateGroups>();
		String view = "admin/corporateGroups";
		LOGGER.info("Inside CorporateGroups GET");
		ModelAndView model = new ModelAndView();
		User user= (User) session.getAttribute("userValue");
		corporateGroupList = corporateGroupService.getcorporateGroupList();
		LOGGER.info("user Session Values"+user.getCg_id());
		model.addObject("corpgrplist", corporateGroupList);
		model.setViewName(view);
		return model;
	}
	
	/**
	 * 
	 * @param corpGroup
	 * @param user
	 * @param session
	 * @return model
	 */
	@RequestMapping(value ="/addcorporategroup" ,method=RequestMethod.POST)
	public ModelAndView insertCorporateGroup(@ModelAttribute("corpGroup") CorporateGroups corpGroup,
										@ModelAttribute("user") User user,
										HttpSession session) {
		
		List<CorporateGroups> corporateGroupList = new ArrayList<CorporateGroups>();
		LOGGER.info("Inside addCorporate()- Post");
		ModelAndView model = new ModelAndView();
		String view = "admin/corporateGroups";
		if(corpGroup!=null){
			corporateGroupService.insertAddCorporateGroupData(corpGroup,user);
		}
		corporateGroupList = corporateGroupService.getcorporateGroupList();
		model.addObject("corpgrplist", corporateGroupList);
		model.setViewName(view);
		return  model;
	}
	
	/**
	 * 
	 * @param corpgrp
	 * @param user
	 * @return model
	 */
	@RequestMapping(value ="/editcorporategroup" ,method=RequestMethod.POST)
	public ModelAndView editData(@ModelAttribute(value="corpgrp") CorporateGroups corpgrp,@ModelAttribute("user") User user) {
				
		List<CorporateGroups> corporateGroupList = new ArrayList<CorporateGroups>();
		ModelAndView model = new ModelAndView();
		if (corpgrp != null){
			corporateGroupService.updateCorpGrpData(corpgrp,user);
		}
		String view = "admin/corporateGroups";
		corporateGroupList = corporateGroupService.getcorporateGroupList();
		model.addObject("corpgrplist", corporateGroupList);
		model.setViewName(view);
		return  model;
	}
	
	@RequestMapping(value = "editAllocatedVdcToCorporateGroup",method = RequestMethod.POST)
	public ModelAndView editAllocatedVdc(@ModelAttribute(value = "cgQuota") VdcCgQuotaMap cgQuota) {
		
		ModelAndView model = new ModelAndView();
		String view = "admin/corporateGroupsQuota";
		model.setViewName(view);
		if(cgQuota != null){
			corporateGroupService.updateCgQuotaTableByVdcIdAndCgId(cgQuota);
		}
		List<VdcCgQuotaMap> vdcCgQuotaMapBeanList = new ArrayList<VdcCgQuotaMap>();
		vdcCgQuotaMapBeanList = corporateGroupService.getRowsFromCgQuotaMapTable();
		for(VdcCgQuotaMap vdcCgQuotaMapBean :vdcCgQuotaMapBeanList){
			 int cgId = vdcCgQuotaMapBean.getCg_id();
			 CorporateGroups CorpGrp = corporateGroupService.getCorpGrpByID(cgId);
			 vdcCgQuotaMapBean.setCorpbean(CorpGrp);
			 int vdcId = vdcCgQuotaMapBean.getVdc_id();
			 VdcMaster vdcMasterBean = corporateGroupService.getVdcListFromVdcMasterByVdcId(vdcId);
			 vdcCgQuotaMapBean.setVdcMasterBean(vdcMasterBean);
		 }
		model.addObject("vdcCgQuotaMapBeanList",vdcCgQuotaMapBeanList);
		return  model;
	}
	
	/**
	 * 
	 * @param cg_id
	 * @return model
	 */
	@RequestMapping(value ="/deletecorporategroup1" ,method=RequestMethod.POST)
	public ModelAndView deleteCorporateGroup(@RequestParam int cg_id) {
		
		String view = "admin/corporateGroups";
		List<CorporateGroups> corporateGroupList = new ArrayList<CorporateGroups>();
		ModelAndView model = new ModelAndView();
		corporateGroupService.deleteCorporateGroups(cg_id);
		corporateGroupList = corporateGroupService.getcorporateGroupList();
		model.addObject("corpgrplist", corporateGroupList);
		model.setViewName(view);
		return  model;
	}
	
	/**
	  *
	  * @param  @PathVariable deptId,session 
	  * @return json
	  */ 
	@RequestMapping(value="/getcorporate/{corpId}", method = RequestMethod.GET)
	public @ResponseBody CorporateGroups getCorporateGroupInJSON(@PathVariable Integer corpId) {
		 
		 CorporateGroups corporategrp = new CorporateGroups();
		 corporategrp =corporateGroupService.getCorpGrpByID(corpId);	
		 return corporategrp;
	}
	
	
	@RequestMapping(value="/getAvailableCorpdept/{cgId}", method = RequestMethod.GET)
	public @ResponseBody List<Department> getAvailableCorpdeptInJSON(@PathVariable Integer cgId) {
		System.out.println("the cgid "+cgId);
		List<Department> dptIdList = new ArrayList<Department>();
		dptIdList = corporateGroupService.getDptIdListByCgId(cgId);
		 /*CorporateGroups corporategrp = new CorporateGroups();
		 corporategrp =corporateGroupService.getCorpGrpByID(cgId);	*/
		 return dptIdList;
	}
	 
	/**
	  *  
	  * @param  @PathVariable deptId,session 
	  * @return json
	  */ 
	@RequestMapping(value="/getOwner/{userName}", method = RequestMethod.GET)
	public @ResponseBody User getOwnersInJSON(@PathVariable String userName,HttpSession session) {
        
		User user=new User();
        user=corporateGroupService.getOwnerByName(userName);
		return user;
	}
	 
	/**
	  * param 
	  * @param session
	  * @return json
	  */ 
	@RequestMapping(value="/getCorpUsers", method = RequestMethod.GET)
	public @ResponseBody List<User> getCorpUsersInJSON(HttpSession session) {
		
		List<User> usersList = new ArrayList<User>();
		User user=new User();
		usersList =  corporateGroupService.getUsersByCgIdAndRoleID(user);
		return usersList;
	}
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getAvailableVdcList/{cgId}", method = RequestMethod.GET)
	public @ResponseBody List<VdcMaster> getAvailableVdcList(HttpSession session,@PathVariable int cgId) {
		
		List<VdcMaster> vdcList = new ArrayList<VdcMaster>();
		vdcList = corporateGroupService.getVdcListFromVdcMaster(cgId);
		return vdcList;
	} 
	
	/**
	 * 
	 * @param session
	 * @param vdcId
	 * @return
	 */
	@RequestMapping(value="/getVdcNameByVdcId/{vdcId}", method = RequestMethod.GET)
	public @ResponseBody VdcMaster getVdcNameByVdcId(HttpSession session,@PathVariable int vdcId) {
		
		VdcMaster vdcMasterbean = new VdcMaster();
		vdcMasterbean = corporateGroupService.getVdcListFromVdcMasterByVdcId(vdcId);
		return vdcMasterbean;
	} 
	
	/**
	 * 
	 * @param vdcCgQuotaMapBean
	 * @param session
	 * @return
	 */
	@RequestMapping(value ="/allocateVdcToCorporateGroup" ,method=RequestMethod.GET)
	public ModelAndView allocateVdcToCorporateGroups(@ModelAttribute VdcCgQuotaMap vdcCgQuotaMapBean,HttpSession session) {
		
		String view = "admin/corporateGroupsQuota";
		List<CorporateGroups> corporateGroupList = new ArrayList<CorporateGroups>();
		ModelAndView model = new ModelAndView();
		User user= (User) session.getAttribute("userValue");
		int cgId = vdcCgQuotaMapBean.getCg_id();
		String corpAdmin = corporateGroupService.getOwnerByCgId(cgId);
		int usrId = corporateGroupService.getUserIdByuserNameInUsertable(corpAdmin);
		vdcCgQuotaMapBean.setFree_mem(vdcCgQuotaMapBean.getTotal_mem());
		vdcCgQuotaMapBean.setFree_vcpu(vdcCgQuotaMapBean.getTotal_vcpu());
		vdcCgQuotaMapBean.setFree_disk_storage(vdcCgQuotaMapBean.getTotal_disk_storage());
		vdcCgQuotaMapBean.setCreated_by(user.getUsr_name());
		corporateGroupService.allocateVdcToCorporateGroup(usrId,vdcCgQuotaMapBean);
		corporateGroupList = corporateGroupService.getcorporateGroupList();
		/* List<String> corpGrpNames = new ArrayList<String>();*/
		/* User user= (User) session.getAttribute("userValue");*/
		 VdcUserQuotaMap vdcUserQuotaMapBean = new VdcUserQuotaMap();
		 vdcUserQuotaMapBean.setUsr_id(user.getUsr_id());
		 List<VdcCgQuotaMap> vdcCgQuotaMapBeanList = new ArrayList<VdcCgQuotaMap>();
		 vdcCgQuotaMapBeanList = corporateGroupService.getRowsFromCgQuotaMapTable();
		 for(VdcCgQuotaMap vdcCgQuotaMapBean1 :vdcCgQuotaMapBeanList){
			 int cgId1 = vdcCgQuotaMapBean1.getCg_id();
			 CorporateGroups CorpGrp = corporateGroupService.getCorpGrpByID(cgId1);
			 vdcCgQuotaMapBean1.setCorpbean(CorpGrp);
			 int vdcId = vdcCgQuotaMapBean1.getVdc_id();
			 VdcMaster vdcMasterBean = corporateGroupService.getVdcListFromVdcMasterByVdcId(vdcId);
			 vdcCgQuotaMapBean1.setVdcMasterBean(vdcMasterBean);
		 }
		 model.addObject("vdcCgQuotaMapBeanList",vdcCgQuotaMapBeanList);
		/* model.addObject("corpGrpNames",corpGrpNames);*/
		model.addObject("corpgrplist", corporateGroupList);
		model.setViewName(view);
		return  model;
	}
	 
	 /**
	  * 
	  * @param session
	  * @param selectedVdcId
	  * @return
	 * @throws RemoteException 
	  */
	 @RequestMapping(value="/getVdcDetailsFromService/{selectedVdcId}", method = RequestMethod.GET)
	 public @ResponseBody GetOpenstackHypervisorDetailsResp getVdcDetailsFromServices(HttpSession session,@PathVariable int selectedVdcId) throws RemoteException {
		
		 
		 LOGGER.info("Selected vdcID for  service "+selectedVdcId);
		 String ipAddress = "0.0.0.0";
		 String openStackUserName = null;
		 String openStackPassword = null;
		 String openStackTenantName = null;
		 VdcMaster vdcMasterBean = new VdcMaster();
		 GetOpenstackHypervisorDetailsResp openstackHypervisorDetailsResp = new GetOpenstackHypervisorDetailsResp();
		 VmProvCredential vmProvCredentialbean = new  VmProvCredential();
		 vmProvCredentialbean = corporateGroupService.getVdcCredentialByVdcId(selectedVdcId);
		 openStackUserName = vmProvCredentialbean.getUsername();
		 openStackPassword = vmProvCredentialbean.getPassword();
		 ipAddress = corporateGroupService.getIpAddressControllerEndpointByVdcIdFromVdcExtension(selectedVdcId);
		 openStackTenantName = corporateGroupService.getTenantNameAdminTenantByVdcIdFromVdcExtension(selectedVdcId);
		 openstackHypervisorDetailsResp = openStackServices.getHypervisorDetails(OpenStackCredentials.OPENSTACK_ADMIN_USER_NAME, OpenStackCredentials.OPENSTACK_KEYENDPOINT,OpenStackCredentials.OPENSTACK_INTERFACE,  OpenStackCredentials.OPENSTACK_REGION, OpenStackCredentials.OPENSTACK_ADMIN_PASSWORD,OpenStackCredentials.OPENSTACK_USER_TENANTNAME);
		 vdcMasterBean.setVdc_id(selectedVdcId);
		 vdcMasterBean.setTotal_mem(openstackHypervisorDetailsResp.getMemory_mb());
		 vdcMasterBean.setTotal_vcpu(openstackHypervisorDetailsResp.getVcpus());
		 vdcMasterBean.setTotal_disk_storage(openstackHypervisorDetailsResp.getLocal_gb());
		 vdcMasterBean.setFree_mem(openstackHypervisorDetailsResp.getFree_ram_mb());
		 LOGGER.info("total memory from openstackHypervisorDetailsResp --- getVcpus"+openstackHypervisorDetailsResp.getVcpus());
		 LOGGER.info("total memory from openstackHypervisorDetailsResp --- getLocalGb"+openstackHypervisorDetailsResp.getLocal_gb());
		 LOGGER.info("total memory from openstackHypervisorDetailsResp --- getFreeRamMb"+openstackHypervisorDetailsResp.getFree_ram_mb());
		 LOGGER.info("total memory from openstackHypervisorDetailsResp --- getMemoryMb"+openstackHypervisorDetailsResp.getMemory_mb());
		 int freeVcpu = openstackHypervisorDetailsResp.getVcpus() - openstackHypervisorDetailsResp.getVcpus_used();
		 vdcMasterBean.setFree_vcpu(freeVcpu);
		 vdcMasterBean.setFree_disk_storage(openstackHypervisorDetailsResp.getFree_disk_gb());
		 LOGGER.info("total memory from openstackHypervisorDetailsResp --- getFreeDiskGb"+openstackHypervisorDetailsResp.getFree_disk_gb());
		 corporateGroupService.updateVdcDetailInVdcMaster(vdcMasterBean);
		 return openstackHypervisorDetailsResp;
	 } 
	 
	 /**
	  * 
	  * @param session
	  * @return
	  */
	 @RequestMapping(value="/getVdcDetailsFromCgQuotaTable/{cgId}/{selectedVdcId}/{totalMem}/{totalVcpu}/{totalDiskStorage}", method = RequestMethod.GET)
	 public @ResponseBody AvailableVdcQuota getVdcDetailsFromCgQuotaTables(HttpSession session,@PathVariable int cgId,@PathVariable int selectedVdcId,@PathVariable int totalMem,@PathVariable int totalVcpu,@PathVariable int totalDiskStorage) {
		 
		 AvailableVdcQuota availableVdcQuota = new AvailableVdcQuota();
		 int availableMem = 0;
		 int availableVcpu = 0;
		 int availableDiskStorage = 0;
		 int totalAllocatedCgMem = 0;
		 int totalAllocatedCgVcpu = 0;
		 int totalAllocatedCgDiskStorage = 0;
		 List<VdcCgQuotaMap> vdcCgQuotaMapBeanList = new ArrayList<VdcCgQuotaMap>();
		 vdcCgQuotaMapBeanList = corporateGroupService.getRowsFromCgQuotaMapTablebyVdcId(selectedVdcId);
		 if(!vdcCgQuotaMapBeanList.isEmpty()){
		 totalAllocatedCgMem = corporateGroupService.getSumOfTotalAllocatedMemoryToCg(selectedVdcId);
		 totalAllocatedCgVcpu = corporateGroupService.getSumOfTotalAllocatedVcpuToCg(selectedVdcId);
		 totalAllocatedCgDiskStorage = corporateGroupService.getSumOfTotalAllocatedDiskStorageToCg(selectedVdcId);
		 }
		 if(totalMem != 0){
			 availableMem = totalMem - totalAllocatedCgMem;
			 availableVcpu = totalVcpu - totalAllocatedCgVcpu;
			 availableDiskStorage = totalDiskStorage - totalAllocatedCgDiskStorage;
		 }
		 availableVdcQuota.setAvailableMem(availableMem);
		 availableVdcQuota.setAvailableVcpu(availableVcpu);
		 availableVdcQuota.setAvailableDiskStorage(availableDiskStorage);
		 return availableVdcQuota;
	 }
	 
	 @RequestMapping(value="/getSelectedRowFromCgQuota/{cgId}/{vdcId}", method = RequestMethod.GET)
		public @ResponseBody VdcCgQuotaMap getSelectedRowFromCgQuota(HttpSession session,@PathVariable int cgId,@PathVariable int vdcId) {
			
			VdcCgQuotaMap vdcCgQuotaMapBean = new VdcCgQuotaMap();
			vdcCgQuotaMapBean = corporateGroupService.getCgQuotaByCgIdVdcIdFromCgQuotaMap(cgId, vdcId);
			return vdcCgQuotaMapBean;
		} 
	 
	 /**
	  * 
	  * @param session
	  * @return
	  * 
	  * 
	  * 
	  * 
	  */
	 
	/* Method to list the cg quota table*/
	 @RequestMapping(value="/listCgQuotaDetails", method=RequestMethod.GET)
	 public ModelAndView listCgQuotaDetails(HttpSession session) {
		 LOGGER.info("Inside CorporateGroupsQuota GET");
		 String view = "admin/corporateGroupsQuota";
		 ModelAndView model = new ModelAndView();
		 List<String> corpGrpNames = new ArrayList<String>();
		 User user= (User) session.getAttribute("userValue");
		 VdcUserQuotaMap vdcUserQuotaMapBean = new VdcUserQuotaMap();
		 vdcUserQuotaMapBean.setUsr_id(user.getUsr_id());
		 List<VdcCgQuotaMap> vdcCgQuotaMapBeanList = new ArrayList<VdcCgQuotaMap>();
		 vdcCgQuotaMapBeanList = corporateGroupService.getRowsFromCgQuotaMapTable();
		 for(VdcCgQuotaMap vdcCgQuotaMapBean :vdcCgQuotaMapBeanList){
			 int cgId = vdcCgQuotaMapBean.getCg_id();
			 CorporateGroups CorpGrp = corporateGroupService.getCorpGrpByID(cgId);
			 vdcCgQuotaMapBean.setCorpbean(CorpGrp);
			 int vdcId = vdcCgQuotaMapBean.getVdc_id();
			 VdcMaster vdcMasterBean = corporateGroupService.getVdcListFromVdcMasterByVdcId(vdcId);
			 vdcCgQuotaMapBean.setVdcMasterBean(vdcMasterBean);
		 }
		 model.addObject("vdcCgQuotaMapBeanList",vdcCgQuotaMapBeanList);
		 model.addObject("corpGrpNames",corpGrpNames);
		 model.setViewName(view);
		 return model;
	}
	
	 @RequestMapping(value="/checkCorpoNameAvailability", method = RequestMethod.POST)
		public @ResponseBody int getCorpo_namee(@RequestParam("corpo_name") String corpo_name)
    {
	  int check =0;
	 
	 System.out.println("====="+corpo_name);
	    LOGGER.info("inside ajax()-");
	        
	    check = corporateGroupService.getCorpo_namee(corpo_name);
	      
	    return check;
	 
   }
	 
	 @RequestMapping(value="/bespokeBluePrint", method=RequestMethod.GET)
		public ModelAndView bespokeBluePrint(HttpSession session) {
						
			
			String view = "corporate/blueprint";
			LOGGER.info("Inside bespokeBluePrint GET");
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
	 
	 
	 @RequestMapping(value="/getAzureVdcDtlsFrmDB/{selectedVdcId}", method = RequestMethod.GET)
		public @ResponseBody VdcMaster getAzureVdcDtlsFrmDB(HttpSession session,@PathVariable int selectedVdcId) {
			
			VdcMaster vdcMasterbean = new VdcMaster();
			
			vdcMasterbean = corporateGroupService.getAzureVdcDtlsFrmDBByVdcId(selectedVdcId);
			return vdcMasterbean;
		} 
	 
}
