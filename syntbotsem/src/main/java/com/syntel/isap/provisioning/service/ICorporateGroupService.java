package com.syntel.isap.provisioning.service;

import java.util.List;

import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VmProvCredential;

public interface ICorporateGroupService {

	List<CorporateGroups> getcorporateGroupList();

	void insertCorpGrpData(CorporateGroups corpGroup);

	void updateCorpGrpData(CorporateGroups corpGroup, User user);

	CorporateGroups getCorpGrpByID(int cgId);

	int getNewCgIdForUserByOwnerName(String corporateOwner);

	void updateCgIdOfOwnerUserInUserTable(int userCgIdChange,int userId);

	int getUserIdByuserNameInUsertable(String ownerName);

	void updateDeleteFlagInCorpGrp(int cgId);

	User getOwnerByName(String userName);

	List<User> getUsersByCgId(int cgId);

	List<User> getUsersByCgIdAndRoleID(User user);

	void insertAddCorporateGroupData(CorporateGroups corpGroup, User user);

	void deleteCorporateGroups(int cgId);

	List<VdcMaster> getVdcListFromVdcMaster(int cgId);

	VmProvCredential getVdcCredentialByVdcId(int selectedVdcId);

	String getIpAddressControllerEndpointByVdcIdFromVdcExtension(int selectedVdcId);

	String getTenantNameAdminTenantByVdcIdFromVdcExtension(int selectedVdcId);

	int getSumOfTotalAllocatedMemoryToCg(int selectedVdcId);

	int getSumOfTotalAllocatedVcpuToCg(int selectedVdcId);

	int getSumOfTotalAllocatedDiskStorageToCg(int selectedVdcId);

	List<VdcCgQuotaMap> getRowsFromCgQuotaMapTable();

	void allocateVdcToCorporateGroup(int usrId,VdcCgQuotaMap vdcCgQuotaMapBean);

	VdcCgQuotaMap getCgQuotaByCgIdVdcIdFromCgQuotaMap(int cgId, int vdcId);

	void updateVdcDetailInVdcMaster(VdcMaster vdcMasterBean);

	int getVdcdetails(int selectedVdcId, int cgId);

	List<VdcCgQuotaMap> getRowsFromCgQuotaMapTablebyVdcId(int selectedVdcId);

	String getOwnerByCgId(int cgId);

	

	VdcMaster getVdcListFromVdcMasterByVdcId(int vdcId);

	void updateCgQuotaTableByVdcIdAndCgId(VdcCgQuotaMap cgQuota);

	List<Department> getDptIdListByCgId(Integer cgId);

	int getCorpo_namee(String corpo_name);

	VdcMaster getAzureVdcDtlsFrmDBByVdcId(int selectedVdcId);
}
