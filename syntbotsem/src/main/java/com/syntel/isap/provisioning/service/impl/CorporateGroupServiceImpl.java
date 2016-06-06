package com.syntel.isap.provisioning.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;
import com.syntel.isap.provisioning.bean.VmProvCredential;
import com.syntel.isap.provisioning.dao.ICorporateGroupDAO;
import com.syntel.isap.provisioning.dao.IDepartmentDao;
import com.syntel.isap.provisioning.service.ICorporateGroupService;

@Service("corporategroupDAO")
public class CorporateGroupServiceImpl implements ICorporateGroupService {

	@Autowired
	private ICorporateGroupDAO corporateGroupDAO;

	@Autowired
	private IDepartmentDao departmentDao;

	private static final Logger LOGGER = Logger
			.getLogger(LoginServiceImpl.class.getName());

	@Transactional
	public List<CorporateGroups> getcorporateGroupList() {
		
		return corporateGroupDAO.getcorporateGroupList();
	}

	@Transactional
	public void insertCorpGrpData(CorporateGroups corpGroup) {
		
		corporateGroupDAO.insertCorpGrpData(corpGroup);
	}

	@Transactional
	public void updateCorpGrpData(CorporateGroups corpGroup, User user) {

		int usrId = user.getUsr_id();
		int cgId = corpGroup.getCg_id();
		String usrName = corporateGroupDAO.getUserNameById(user);
		String oldOwner = corporateGroupDAO.getOwnerByCgId(cgId);
		int oldUsrId = corporateGroupDAO
				.getUserIdByuserNameInUsertable(oldOwner);
		if (usrName.equals(oldOwner)) {
			corporateGroupDAO.updateCorpGroupWithoutOwner(corpGroup);
		} else {
			corpGroup.setCorporate_owner(usrName);
			corporateGroupDAO.updateCorpGrpData(corpGroup);
			corporateGroupDAO.updateCgIdOfOwnerUserInUserTable(cgId, usrId);
			corporateGroupDAO.mapRoleToUser(user);
			corporateGroupDAO.updateCgIdofOldOwner(oldUsrId);
			corporateGroupDAO.updateRoleOfOldOwner(oldUsrId);
		}
	}

	@Transactional
	public CorporateGroups getCorpGrpByID(int cgId) {

		return corporateGroupDAO.getCorpGrpByID(cgId);
	}

	@Transactional
	public int getNewCgIdForUserByOwnerName(String corporateOwner) {

		return  corporateGroupDAO
				.getNewCgIdForUserByOwnerName(corporateOwner);
	}

	@Transactional
	public void updateCgIdOfOwnerUserInUserTable(int userCgIdChange, int userId) {
		
		corporateGroupDAO.updateCgIdOfOwnerUserInUserTable(userCgIdChange,
				userId);
	}

	@Transactional
	public int getUserIdByuserNameInUsertable(String ownerName) {
	
		return  corporateGroupDAO
				.getUserIdByuserNameInUsertable(ownerName);
	}

	@Transactional
	public void updateDeleteFlagInCorpGrp(int cgId) {
		
		corporateGroupDAO.updateDeleteFlagInCorpGrp(cgId);
	}

	@Transactional
	public User getOwnerByName(String userName) {

		return  corporateGroupDAO.getOwnerByName(userName);
	}

	@Transactional
	public List<User> getUsersByCgId(int cgId) {
	
		return  corporateGroupDAO.getUsersByCgId(cgId);
	}

	@Transactional
	public List<User> getUsersByCgIdAndRoleID(User user) {
		
		return corporateGroupDAO.getUsersByCgIdAndRoleID(user);
	}

	@Transactional
	public void insertAddCorporateGroupData(CorporateGroups corpGroup, User user) {
		
		corpGroup.setStatus("Active");
		LOGGER.info("Inside ServiceImpl addCorporateGroup"
				+ corpGroup.getStatus());
		corpGroup.setCorporate_owner(corporateGroupDAO.getUserNameById(user));
		corporateGroupDAO.insertAddCorporateGroupData(corpGroup);
		int corpId = corpGroup.getCg_id();
		user.setCg_id(corpId);
		corporateGroupDAO.mapRoleToUser(user);
		corporateGroupDAO.mapCorporateGroupToUser(user.getUsr_id(), corpId);
	}

	@Transactional
	public void deleteCorporateGroups(int cgId) {

		LOGGER.info("Inside ServiceImpl deleteCorporate" + cgId);
		corporateGroupDAO.updateDeleteFlagInCorpGrp(cgId);
		corporateGroupDAO.updateUsersRoleInUsrs(cgId);
		
		/*List<Integer> dptIdList = new ArrayList<Integer>();
		List<Integer> projIdList = new ArrayList<Integer>();
		List<Integer> userIdList = new ArrayList<Integer>();
		dptIdList = corporateGroupDAO.getDptIdListByCgId(cgId);
		projIdList = corporateGroupDAO.getProjIdListByCgId(cgId);
		userIdList = corporateGroupDAO.getUserIdListByCgId(cgId);
		
		if (!dptIdList.isEmpty()) {
			for (Integer deptId : dptIdList) {
				corporateGroupDAO.updateDeptDeleteFlag(deptId);
			}
		}
		if (!projIdList.isEmpty()) {
			for (Integer projId : projIdList) {
				corporateGroupDAO.updateProjDeleteFlag(projId);
			}
		}
		if (!userIdList.isEmpty()) {
			for (Integer userId : userIdList) {
				corporateGroupDAO.updateCgDeptProjId(userId);
				departmentDao.updateUserRole(userId);
			}
		}*/
	}

	@Transactional
	public List<VdcMaster> getVdcListFromVdcMaster(int cgId) {
		List<VdcMaster> vdcList = new ArrayList<VdcMaster>();
		List<Integer> vdcIdList = new ArrayList<Integer>();
		vdcIdList = corporateGroupDAO.getVdcIdListByCgIdFromCgQuotaTable(cgId);
		vdcList = corporateGroupDAO.getVdcListFromVdcMaster();
		Iterator<Integer> itr1 = vdcIdList.iterator();
		while (itr1.hasNext()) {
			int vdcId = itr1.next();
			Iterator<VdcMaster> itr = vdcList.iterator();
			for (int index = 0; index < vdcList.size(); index++) {
				while (itr.hasNext()) {
					VdcMaster vdcMasterBean = new VdcMaster();
					vdcMasterBean = itr.next();
					if (vdcId == vdcMasterBean.getVdc_id()) {
						itr.remove();
					}
				}
			}
		}
		return vdcList;
	}

	@Transactional
	public VmProvCredential getVdcCredentialByVdcId(int selectedVdcId) {
		
		return corporateGroupDAO
		.getVdcCredentialByVdcId(selectedVdcId);
	}

	@Transactional
	public String getIpAddressControllerEndpointByVdcIdFromVdcExtension(
			int selectedVdcId) {
		
		String ipAddress = null;
		ipAddress = corporateGroupDAO
				.getIpAddressControllerEndpointByVdcIdFromVdcExtension(selectedVdcId);
		return ipAddress;
	}

	@Transactional
	public String getTenantNameAdminTenantByVdcIdFromVdcExtension(
			int selectedVdcId) {
		
		String openStackTenantName = null;
		openStackTenantName = corporateGroupDAO
				.getTenantNameAdminTenantByVdcIdFromVdcExtension(selectedVdcId);
		return openStackTenantName;
	}

	@Transactional
	public int getSumOfTotalAllocatedMemoryToCg(int selectedVdcId) {
		
		int totalAllocatedCgMem = 0;
		totalAllocatedCgMem = corporateGroupDAO
				.getSumOfTotalAllocatedMemoryToCg(selectedVdcId);
		return totalAllocatedCgMem;
	}

	@Transactional
	public int getSumOfTotalAllocatedVcpuToCg(int selectedVdcId) {
		
		int totalAllocatedCgVcpu = 0;
		totalAllocatedCgVcpu = corporateGroupDAO
				.getSumOfTotalAllocatedVcpuToCg(selectedVdcId);
		return totalAllocatedCgVcpu;
	}

	@Transactional
	public int getSumOfTotalAllocatedDiskStorageToCg(int selectedVdcId) {
		
		int totalAllocatedCgDiskStorage = 0;
		totalAllocatedCgDiskStorage = corporateGroupDAO
				.getSumOfTotalAllocatedDiskStorageToCg(selectedVdcId);
		return totalAllocatedCgDiskStorage;
	}

	@Transactional
	public List<VdcCgQuotaMap> getRowsFromCgQuotaMapTable() {
		
		List<VdcCgQuotaMap> vdcCgQuotaMapBeanList = new ArrayList<VdcCgQuotaMap>();
		vdcCgQuotaMapBeanList = corporateGroupDAO.getRowsFromCgQuotaMapTable();
		return vdcCgQuotaMapBeanList;
	}

	@Transactional
	public List<VdcCgQuotaMap> getRowsFromCgQuotaMapTablebyVdcId(
			int selectedVdcId) {
		
		return corporateGroupDAO
				.getRowsFromCgQuotaMapTablebyVdcId(selectedVdcId);
	}

	@Transactional
	public void allocateVdcToCorporateGroup(int usrId,VdcCgQuotaMap vdcCgQuotaMapBean) {
		VdcUserQuotaMap vdcuserQuotaMapBean = new VdcUserQuotaMap();
		vdcuserQuotaMapBean.setUsr_id(usrId);
		if(vdcCgQuotaMapBean!= null){
			vdcuserQuotaMapBean.setVdc_id(vdcCgQuotaMapBean.getVdc_id());
			vdcuserQuotaMapBean.setTotal_mem(vdcCgQuotaMapBean.getTotal_mem());
			vdcuserQuotaMapBean.setTotal_vcpu(vdcCgQuotaMapBean.getTotal_vcpu());
			vdcuserQuotaMapBean.setTotal_disk_storage(vdcCgQuotaMapBean.getTotal_disk_storage());
			vdcuserQuotaMapBean.setFree_mem(vdcCgQuotaMapBean.getFree_mem());
			vdcuserQuotaMapBean.setFree_vcpu(vdcCgQuotaMapBean.getFree_vcpu());
			vdcuserQuotaMapBean.setFree_disk_storage(vdcCgQuotaMapBean.getTotal_disk_storage());
		}
		corporateGroupDAO.allocateVdcToCorporateGroup(vdcCgQuotaMapBean);
		corporateGroupDAO.allocateVdcQuotaToCorpGroupAdminUserInUserQuotaTable(vdcuserQuotaMapBean);
	}

	@Transactional
	public VdcCgQuotaMap getCgQuotaByCgIdVdcIdFromCgQuotaMap(int cgId, int vdcId) {
		
		return corporateGroupDAO.getCgQuotaByCgIdVdcIdFromCgQuotaMap(cgId,
				vdcId);
	}

	@Transactional
	public void updateVdcDetailInVdcMaster(VdcMaster vdcMasterBean) {
		
		corporateGroupDAO.updateVdcDetailInVdcMaster(vdcMasterBean);

	}

	@Transactional
	public int getVdcdetails(int selectedVdcId, int cgId) {
		
		VdcCgQuotaMap vdcCgQuotaMap = new VdcCgQuotaMap();
		vdcCgQuotaMap = corporateGroupDAO.getVdcdetails(selectedVdcId, cgId);
		if (vdcCgQuotaMap != null) {
			return 1;
		}
		return 0;
	}

	@Transactional
	public String getOwnerByCgId(int cgId) {

		return corporateGroupDAO.getOwnerByCgId(cgId);
	}

	

	@Transactional
	public VdcMaster getVdcListFromVdcMasterByVdcId(int vdcId) {
		
		return corporateGroupDAO.getVdcListFromVdcMasterByVdcId(vdcId);
	}

	@Transactional
	public void updateCgQuotaTableByVdcIdAndCgId(VdcCgQuotaMap cgQuota) {
		
		 int cgId = cgQuota.getCg_id();
	 	 int vdcId = cgQuota.getVdc_id(); 
         int editedTotalMem = cgQuota.getTotal_mem();
		 int editedTotalVCPU= cgQuota.getTotal_vcpu();
		 int editedTotalDisk = cgQuota.getTotal_disk_storage();
		 VdcCgQuotaMap cgQuotaMapBean = new VdcCgQuotaMap();
		 cgQuotaMapBean = corporateGroupDAO.getCgQuotaByCgIdVdcIdFromCgQuotaMap(cgId, vdcId);
		 int existingTotalMem = cgQuotaMapBean.getTotal_mem();
		 int existingTotalVcpu = cgQuotaMapBean.getTotal_vcpu();
		 int existingTotalDisk = cgQuotaMapBean.getTotal_disk_storage();
		 int existingFreeMem = cgQuotaMapBean.getFree_mem();
		 int existingFreeVcpu = cgQuotaMapBean.getFree_vcpu();
		 int existingFreeDisk = cgQuotaMapBean.getFree_disk_storage();
		 if(editedTotalMem >= existingTotalMem){
			 int extraTotalMem = 0;
			 int newFreeMem = 0;
			 extraTotalMem = editedTotalMem - existingTotalMem;
			 cgQuotaMapBean.setTotal_mem(editedTotalMem);
			 if(extraTotalMem != 0){
				 newFreeMem = existingFreeMem + extraTotalMem;
				 cgQuotaMapBean.setFree_mem(newFreeMem);
			 }
		 }else{
		 	int reducedMem = 0;
			int newReducedFreeMem = 0;
			reducedMem = existingTotalMem - editedTotalMem; 
			cgQuotaMapBean.setTotal_mem(editedTotalMem);
			if(reducedMem != 0){
				newReducedFreeMem = existingFreeMem - reducedMem;
				cgQuotaMapBean.setFree_mem(newReducedFreeMem);
			 }
		 }
		 if(editedTotalVCPU >= existingTotalVcpu){
			 int extraTotalVcpu = 0;
			 int newFreeVcpu = 0;
			 extraTotalVcpu = editedTotalVCPU - existingTotalVcpu;
			 cgQuotaMapBean.setTotal_vcpu(editedTotalVCPU);
			 if(extraTotalVcpu != 0){
				 newFreeVcpu = existingFreeVcpu + extraTotalVcpu;
				 cgQuotaMapBean.setFree_vcpu(newFreeVcpu);
			 }
		 }else{
			 int reducedVcpu = 0;
			 int newReducedFreeVcpu = 0;
			 reducedVcpu = existingFreeVcpu - editedTotalVCPU;
			 if(reducedVcpu != 0){
				 newReducedFreeVcpu = existingFreeVcpu - reducedVcpu;
				 cgQuotaMapBean.setFree_vcpu(newReducedFreeVcpu);
			 }
		 }
		 if(editedTotalDisk >= existingTotalDisk){
			 int extraTotalDisk = 0;
			 int newFreeDisk = 0;
			 extraTotalDisk = editedTotalDisk - existingTotalDisk;
			 cgQuotaMapBean.setTotal_disk_storage(editedTotalDisk);
			 if(extraTotalDisk != 0){
				 newFreeDisk = existingFreeDisk + extraTotalDisk;
				 cgQuotaMapBean.setFree_disk_storage(newFreeDisk);
			 }
		 }else{
			 int reducedDisk = 0;
			 int newReducedFreeDisk = 0;
			 reducedDisk = editedTotalDisk - existingFreeDisk;
			 if(reducedDisk != 0){
				 newReducedFreeDisk = existingFreeDisk - reducedDisk;
				 cgQuotaMapBean.setFree_disk_storage(newReducedFreeDisk);
			 }
		}
		 corporateGroupDAO.updateCgQuotaTableByVdcIdAndCgId(cgQuotaMapBean);
		
		 
		
		
	}
	
	@Transactional
	public List<Department> getDptIdListByCgId(Integer cgId) {
		// TODO Auto-generated method stub
		return  corporateGroupDAO.getDptIdListByCgId(cgId);
	}

	public int getCorpo_namee(String corpo_name) {
		// TODO Auto-generated method stub
	CorporateGroups cops= corporateGroupDAO.getCorpo_namee(corpo_name);
	if(cops!=null)
	{
		return 1;
	}
	else
	{
		return 0;
	}
	}

	public VdcMaster getAzureVdcDtlsFrmDBByVdcId(int selectedVdcId) {
		
		VdcMaster azureVdcDtls=corporateGroupDAO.getAzureVdcDtlsFrmDBByVdcId(selectedVdcId);
		return azureVdcDtls;
	}

}
