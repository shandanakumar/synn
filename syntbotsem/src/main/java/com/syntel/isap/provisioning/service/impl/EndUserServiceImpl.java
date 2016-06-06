package com.syntel.isap.provisioning.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.CustomVMExt;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.StackList;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.constants.OpenStackCredentials;
import com.syntel.isap.provisioning.dao.IEndUserDAO;
import com.syntel.isap.provisioning.service.IEndUserService;

@Service("endUserService")
public class EndUserServiceImpl implements IEndUserService {

	@Autowired
	private IEndUserDAO endUserDAO;

	@Transactional
	public int getProjectId(int usr_id) {
		// TODO Auto-generated method stub
		return endUserDAO.getProjectId(usr_id);
	}

	@Transactional
	public List<User> getUsersByProject(int pg_id) {
		// TODO Auto-generated method stub
		
		List<User> usrwithoutrole= endUserDAO.getUsersByProject(pg_id);
		for (User user : usrwithoutrole) {
			int usr_id=user.getUsr_id();
			 String rolename=endUserDAO.getUsersRoleByUserId(usr_id);
			 user.setRole_name(rolename);
			
		}
		
		return usrwithoutrole;
		
	}

	@Transactional
	public String getProjectName(int pgid) {
		// TODO Auto-generated method stub
		return endUserDAO.getProjectName(pgid) ;
	}
	
	@Transactional
	public List<VdcProjQuotaMap> getProjQuotaMap(int pgid) {
		// TODO Auto-generated method stub
		List<VdcProjQuotaMap> vdcprojQuotaMap=endUserDAO.getProjQuotaMap(pgid);
		for (VdcProjQuotaMap vdcProjQuotaMapBean : vdcprojQuotaMap) {
			int vdcID = vdcProjQuotaMapBean.getVdc_id();
			VdcMaster vdcmaster = endUserDAO.getVdcName(vdcID);
			vdcProjQuotaMapBean.setVdcmasterbean(vdcmaster);
		}
		return vdcprojQuotaMap;
	}
	
	@Transactional
	public List<Identity> getKeyPairList(int proj_id) {
		// TODO Auto-generated method stub
		return endUserDAO.getKeyPairList(proj_id);
	}

	public void addDPoolUsertoProject(User user, String user_name) {
		// TODO Auto-generated method stub
		String[] usr_id = user_name.split(",");
		
		for(String str:usr_id)
		{
			int proj_id=user.getProj_id();
			System.out.println("the valueof dpt_id is:"+proj_id);
			int id = Integer.parseInt(str);
			endUserDAO.updateUsersInfo(proj_id,id);
		}
		
	}
	
	
	public List<User> getDPoolUsers(int dpt_id) {
		// TODO Auto-generated method stub
		return endUserDAO.getDPoolUsers(dpt_id);
	}

	public List<User> getVMUsers(int proj_id) {
		// TODO Auto-generated method stub
		return endUserDAO.getVMUsers(proj_id);
	}

	/*public List<CustomVM> getVMList(int usr_id) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Transactional
	public List<CustomVM> getVMList(int usr_id)  {
		List<CustomVMExt> customVMExtDetails = new ArrayList<CustomVMExt>();
		List<CustomVM> customList = new ArrayList<CustomVM>();
		List<CustomVM> customNewList = new ArrayList<CustomVM>();
		String usr_name=endUserDAO.getUserNameByUserId(usr_id);
		
		customList = endUserDAO.getCustomList(usr_id);
		//LOGGER.info("Inside getCustomList" + customList);
		for (CustomVM customVM : customList) {
			StackList list = new StackList();
			int customId = customVM.getVm_custom_id();
			//LOGGER.info("CustomId" + customId);
			customVMExtDetails = endUserDAO.getCustomVMExtDetailsById(customId);
			for (CustomVMExt customVMExt : customVMExtDetails) {
				//LOGGER.info("CustomVMExt Details:" + customVMExt.getParam_val()
					//	+ ":" + customVMExt.getParam_val());
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
			customVM.setUsr_name(usr_name);
			customVM.setCustomVMExts(customVMExtDetails);
			customVM.setStackList(list);
			customNewList.add(customVM);
			
			//LOGGER.info("===Creating Instance in OpenStack Started===");
		}
		return customNewList;
	}
}