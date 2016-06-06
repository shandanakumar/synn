package com.syntel.isap.provisioning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


































import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;
import com.syntel.isap.provisioning.bean.VmProvCredential;
import com.syntel.isap.provisioning.dao.ICorporateGroupDAO;
import com.syntel.isap.provisioning.mapper.CorporateGroupMapper;


@Service("CorporategrupDAO")
public class CorporateGroupDAOImpl implements ICorporateGroupDAO {

	@Autowired
	private CorporateGroupMapper corporategroupMapper;

	public List<CorporateGroups> getcorporateGroupList() {
		
		return corporategroupMapper.getcorporateGroupList();
	}

	
     
	public void insertCorpGrpData(CorporateGroups corpGroup) {
		
		corporategroupMapper.insertCorpGrpData(corpGroup);
	}

	public void updateCorpGrpData(CorporateGroups corpGroup) {
		
		corporategroupMapper.updateCorpGrpData(corpGroup);
	}

	public CorporateGroups getCorpGrpByID(int cgId) {
		
		CorporateGroups corpgrp= new CorporateGroups();
		corpgrp = corporategroupMapper.getCorpGrpByID(cgId);
		return corpgrp;
	}

	public int getNewCgIdForUserByOwnerName(String corporateOwner) {
		int userCgIdChange = corporategroupMapper.getNewCgIdForUserByOwnerName(corporateOwner);
		return userCgIdChange;
	}

	public void updateCgIdOfOwnerUserInUserTable(int userCgIdChange,int userId) {
		corporategroupMapper.updateCgIdOfOwnerUserInUserTable(userCgIdChange,userId);
		
	}

	public int getUserIdByuserNameInUsertable(String ownerName) {
		int userId = corporategroupMapper.getUserIdByuserNameInUsertable(ownerName);
		return userId;
	}

	public void updateDeleteFlagInCorpGrp(int cgId) {
		corporategroupMapper.updateDeleteFlagInCorpGrp(cgId);
		
	}

	public User getOwnerByName(String userName) {
		User user= new User();
		user = corporategroupMapper.getOwnerByName(userName);
		return user;
	}

	public List<User> getUsersByCgId(int cgId) {
		List<User> userList = new ArrayList<User>();
		userList = corporategroupMapper.getUsersByCgId(cgId);
		return userList;
	}

	public List<User> getUsersByCgIdAndRoleID(User user) {
		return corporategroupMapper.getUsersByCgIdAndRoleID(user);
	}

	
	public String getUserNameById(User user) {
		return corporategroupMapper.getUserNameById(user);
	}

	public void insertAddCorporateGroupData(CorporateGroups corpGroup) {
		corporategroupMapper.insertAddCorporateGroupData(corpGroup);
		
	}

	public void mapRoleToUser(User user) {
		corporategroupMapper.mapRoleToUser(user);
		
	}

	public void mapCorporateGroupToUser(int usrId, int cgId) {
		corporategroupMapper.mapCorporateGroupToUser(usrId,cgId);
		
	}

	



	/*public List<Integer> getProjIdListByCgId(int cg_id) {
		List<Integer> projIdList = corporategroupMapper.getProjIdListByCgId(cg_id);
		return projIdList;
	}



	public List<Integer> getUserIdListByCgId(int cg_id) {
		List<Integer> userIdList = corporategroupMapper.getUserIdListByCgId(cg_id);
		return userIdList;
	}



	public void updateDeptDeleteFlag(Integer deptId) {
		corporategroupMapper.updateDeptDeleteFlag(deptId);
		
	}



	public void updateProjDeleteFlag(Integer projId) {
		corporategroupMapper.updateProjDeleteFlag(projId);
		
	}



	public void updateCgDeptProjId(Integer userId) {
		corporategroupMapper.updateCgDeptProjId(userId);
		
	}
*/


	public String getOwnerByCgId(int cg_id) {
		String currentOwner = corporategroupMapper.getOwnerByCgId(cg_id);
		return currentOwner;
	}



	public void updateCorpGroupWithoutOwner(CorporateGroups corpGroup) {
		corporategroupMapper.updateCorpGroupWithoutOwner(corpGroup);
	}



	public void updateRoleOfOldOwner(int oldUsrId) {
		corporategroupMapper.updateRoleOfOldOwner(oldUsrId);
		
	}



	public void updateCgIdofOldOwner(int oldUsrId) {
		corporategroupMapper.updateCgIdofOldOwner(oldUsrId);
		
	}



	public List<VdcMaster> getVdcListFromVdcMaster() {
		List<VdcMaster> vdcList = new ArrayList<VdcMaster>();
		vdcList = corporategroupMapper.getVdcListFromVdcMaster();
		return vdcList;
	}



	public VmProvCredential getVdcCredentialByVdcId(int selectedVdcId) {
		VmProvCredential vmProvCredentialbean = new  VmProvCredential();
		vmProvCredentialbean = corporategroupMapper.getVdcCredentialByVdcId(selectedVdcId);
		return vmProvCredentialbean;
	}



	public String getIpAddressControllerEndpointByVdcIdFromVdcExtension(int selectedVdcId) {
		String ipAddress = null;
		ipAddress = corporategroupMapper.getIpAddressControllerEndpointByVdcIdFromVdcExtension(selectedVdcId);
		return ipAddress;
	}



	public String getTenantNameAdminTenantByVdcIdFromVdcExtension(int selectedVdcId) {
		String openStackTenantName = null;
		openStackTenantName = corporategroupMapper.getTenantNameAdminTenantByVdcIdFromVdcExtension(selectedVdcId);
		return openStackTenantName;
	}



	public int getSumOfTotalAllocatedMemoryToCg(int selectedVdcId) {
		int sumTotMem = 0;
		sumTotMem = corporategroupMapper.getSumOfTotalAllocatedMemoryToCg(selectedVdcId);
		return sumTotMem;
	}



	public int getSumOfTotalAllocatedVcpuToCg(int selectedVdcId) {
		int totalAllocatedCgVcpu = 0;
		totalAllocatedCgVcpu = corporategroupMapper.getSumOfTotalAllocatedVcpuToCg(selectedVdcId);
		return totalAllocatedCgVcpu;
	}



	public int getSumOfTotalAllocatedDiskStorageToCg(int selectedVdcId) {
		int totalAllocatedCgDiskStorage = 0;
		totalAllocatedCgDiskStorage = corporategroupMapper.getSumOfTotalAllocatedDiskStorageToCg(selectedVdcId);
		return totalAllocatedCgDiskStorage;
	}



	public List<VdcCgQuotaMap> getRowsFromCgQuotaMapTable() {
		List<VdcCgQuotaMap> vdcCgQuotaMapBeanList = new ArrayList<VdcCgQuotaMap>();
		vdcCgQuotaMapBeanList = corporategroupMapper.getRowsFromCgQuotaMapTable();
		return vdcCgQuotaMapBeanList;
	}
	
	public List<VdcCgQuotaMap> getRowsFromCgQuotaMapTablebyVdcId(int selectedVdcId) {
		List<VdcCgQuotaMap> vdcCgQuotaMapBeanList = new ArrayList<VdcCgQuotaMap>();
		vdcCgQuotaMapBeanList = corporategroupMapper.getRowsFromCgQuotaMapTablebyVdcId(selectedVdcId);
		return vdcCgQuotaMapBeanList;
	}



	public void allocateVdcToCorporateGroup(VdcCgQuotaMap vdcCgQuotaMapBean) {
		corporategroupMapper.allocateVdcToCorporateGroup(vdcCgQuotaMapBean);
	}



	public VdcCgQuotaMap getCgQuotaByCgIdVdcIdFromCgQuotaMap(int cgId, int vdcId) {
		VdcCgQuotaMap vdcCgQuotaMapBean = new VdcCgQuotaMap();
		vdcCgQuotaMapBean = corporategroupMapper.getCgQuotaByCgIdVdcIdFromCgQuotaMap(cgId,vdcId);
		return vdcCgQuotaMapBean;
	}



	public void updateVdcDetailInVdcMaster(VdcMaster vdcMasterBean) {
		corporategroupMapper.updateVdcDetailInVdcMaster(vdcMasterBean);
		
	}



	public VdcCgQuotaMap getVdcdetails(int selectedVdcId, int cgId) {
		// TODO Auto-generated method stub
		return corporategroupMapper.getVdcdetails(selectedVdcId, cgId);
	}



	public List<Integer> getVdcIdListByCgIdFromCgQuotaTable(int cgId) {
		return corporategroupMapper.getVdcIdListByCgIdFromCgQuotaTable(cgId);
	}



	public void allocateVdcQuotaToCorpGroupAdminUserInUserQuotaTable(VdcUserQuotaMap vdcUserQuotaMapBean) {
		corporategroupMapper.allocateVdcQuotaToCorpGroupAdminUserInUserQuotaTable(vdcUserQuotaMapBean);
	}



	public VdcMaster getVdcListFromVdcMasterByVdcId(int vdcId) {
		VdcMaster vdcMasterBean = new VdcMaster();
		vdcMasterBean = corporategroupMapper.getVdcListFromVdcMasterByVdcId(vdcId);
		return vdcMasterBean;
	}



	public void updateCgQuotaTableByVdcIdAndCgId(VdcCgQuotaMap cgQuotaMapBean) {
		corporategroupMapper.updateCgQuotaTableByVdcIdAndCgId(cgQuotaMapBean);
		
	}



	public List<Department> getDptIdListByCgId(Integer cgId) {
		List<Department> dptIdList = corporategroupMapper.getDptIdListByCgId(cgId);
		return dptIdList;
	}



	public void updateUsersRoleInUsrs(int cgId) {
		// TODO Auto-generated method stub
		corporategroupMapper.updateUsersRoleInUsrs(cgId);
	}



	public CorporateGroups getCorpo_namee(String corpo_name) {
		// TODO Auto-generated method stub
		return corporategroupMapper.getCorpo_namee(corpo_name);
	}



	public VdcMaster getAzureVdcDtlsFrmDBByVdcId(int selectedVdcId) {
		
		VdcMaster azureVdcDtls=corporategroupMapper.getVdcListFromVdcMasterByVdcId(selectedVdcId);
		return azureVdcDtls;
	}


	


	

}

