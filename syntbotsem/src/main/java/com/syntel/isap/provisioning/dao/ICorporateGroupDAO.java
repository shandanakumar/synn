package com.syntel.isap.provisioning.dao;

import java.util.List;

import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;
import com.syntel.isap.provisioning.bean.VmProvCredential;

public interface ICorporateGroupDAO {

List<CorporateGroups> getcorporateGroupList();

void insertCorpGrpData(CorporateGroups corpGroup);

void updateCorpGrpData(CorporateGroups corpGroup);

CorporateGroups getCorpGrpByID(int cgId);

int getNewCgIdForUserByOwnerName(String corporateOwner);

void updateCgIdOfOwnerUserInUserTable(int userCgIdChange,int userId);

int getUserIdByuserNameInUsertable(String ownerName);

void updateDeleteFlagInCorpGrp(int cgId);

User getOwnerByName(String userName);

List<User> getUsersByCgId(int cgId);

List<User> getUsersByCgIdAndRoleID(User user);

String getUserNameById(User user);

void insertAddCorporateGroupData(CorporateGroups corpGroup);

void mapRoleToUser(User user);

void mapCorporateGroupToUser(int usrId, int cgId);

/*List<Integer> getDptIdListByCgId(int cgId);

List<Integer> getProjIdListByCgId(int cgId);

List<Integer> getUserIdListByCgId(int cgId);

void updateDeptDeleteFlag(Integer deptId);

void updateProjDeleteFlag(Integer projId);

void updateCgDeptProjId(Integer userId);
*/
String getOwnerByCgId(int cgId);

void updateCorpGroupWithoutOwner(CorporateGroups corpGroup);

void updateRoleOfOldOwner(int oldUsrId);

void updateCgIdofOldOwner(int oldUsrId);

List<VdcMaster> getVdcListFromVdcMaster();

VmProvCredential getVdcCredentialByVdcId(int selectedVdcId);

String getIpAddressControllerEndpointByVdcIdFromVdcExtension(int selectedVdcId);

String getTenantNameAdminTenantByVdcIdFromVdcExtension(int selectedVdcId);

int getSumOfTotalAllocatedMemoryToCg(int selectedVdcId);

int getSumOfTotalAllocatedVcpuToCg(int selectedVdcId);

int getSumOfTotalAllocatedDiskStorageToCg(int selectedVdcId);

List<VdcCgQuotaMap> getRowsFromCgQuotaMapTable();

void allocateVdcToCorporateGroup(VdcCgQuotaMap vdcCgQuotaMapBean);

VdcCgQuotaMap getCgQuotaByCgIdVdcIdFromCgQuotaMap(int cgId, int vdcId);

void updateVdcDetailInVdcMaster(VdcMaster vdcMasterBean);

VdcCgQuotaMap getVdcdetails(int selectedVdcId, int cgId);

	List<VdcCgQuotaMap> getRowsFromCgQuotaMapTablebyVdcId(int selectedVdcId);

	List<Integer> getVdcIdListByCgIdFromCgQuotaTable(int cgId);

	void allocateVdcQuotaToCorpGroupAdminUserInUserQuotaTable(VdcUserQuotaMap vdcUserQuotaMapBean);

	VdcMaster getVdcListFromVdcMasterByVdcId(int vdcId);

	void updateCgQuotaTableByVdcIdAndCgId(VdcCgQuotaMap cgQuotaMapBean);

	List<Department> getDptIdListByCgId(Integer cgId);

	void updateUsersRoleInUsrs(int cgId);

	CorporateGroups getCorpo_namee(String corpo_name);

	VdcMaster getAzureVdcDtlsFrmDBByVdcId(int selectedVdcId);

	
}
