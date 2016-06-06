package com.syntel.isap.provisioning.dao;

import java.util.List;





import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;


public interface IProjectDao {

	void insertData(Project project);

	void deleteProject(Integer proj_id);

	void updateProject(Project project);

	List<Project> getprojectLists(int dptid);

	List<Integer> getUserinfo(User user);

	Project editProject(Integer proj_id);

	List<User> getUsersByCorporate(User user);

	//List<Integer> getUserByProjID(Integer proj_id);
	
	void updateRoleMapByUserId(int userID);

	void updateProjIdInUserTable(int projId, int userID);

	//void updateUserProjIds(Integer userID);

	void updateUserRole(Integer userID);

	Project getProjectById(int proj_id);

	String getUserNameById(int i);

	int getUserIdByProjId(int proj_id);

	void updateUser(int userId);

	void mapRoleToUser(User user);

	void mapProjectToUser(int usr_id, int proj_id);

	User getOwnerByName(String userName);

	List<VdcMaster> getVdcListFromVdcMaster();

	VdcDeptQuotaMap vdcCgQuotaByVdcIDanddptID(int selectedVdcId, int dptId);

	VdcProjQuotaMap getVdcdetails(int selectedVdcId, int projId);

	void allocateVdcToProjectGroup(VdcProjQuotaMap vdcProjQuotaMapBean);

	List<VdcProjQuotaMap> getRowsFromVdcProjQuotaMapTable();

	int getSumOftotalAllocatedProjVcpu(int selectedVdcId);

	int gettotalAllocatedProjDiskStorage(int selectedVdcId);

	int getSumOfTotalAllocatedMemoryToProj(int selectedVdcId);

	VdcDeptQuotaMap getVDCdetailsBydptID(int dptID, int selectedVdcId);

	void updateFreeSpacesinDptquota(int cgAvailableMem, int cgAvailableVcpu,
			int cgAvailableDiskStorage, int selectedVdcId, int dptID);

	List<VdcProjQuotaMap> getRowsFromProjQuotaMapTable(int dptID);

	Project getProjGrpByID(int projId);

	int getfree_memory(int dptID, int selectedVdcId);

	int getfree_vcpuDB(int dptID, int selectedVdcId);

	int getfree_diskDB(int dptID, int selectedVdcId);

	Project getProjectName(Integer projId);

	VdcProjQuotaMap getVdcdetailsByProjID(int proj_id, int vdc_id);

	VdcDeptQuotaMap getFreeVdcDeatilsInDptQuota(int projId, int vdcId);

	void updateFreememoryInDptquota(VdcProjQuotaMap vdcProjQuotaMap, int dpt_id);

	void deleteProjectQuota(Integer proj_id, int vdc_id);

	List<Project> getProjectsList(int proj_id);

	void updateFreememoryInDpt(int newdptFreeMem, int newdptFreeVcpu,
			int newdptFreeDisk, int dptID, int vdcId);

	VdcProjQuotaMap getAllocatedDetails(Integer projid, Integer VdcId);

	VdcProjQuotaMap vdcProjQuotaByVdcIDanddptID(int selectedVdcId, int dptId);

	VdcDeptQuotaMap getFreeMemoryinDPTQuota(int vdcId, int projId);

	/*VdcProjQuotaMap getVdcdetailsByProjID(int dptID, int vdcId);*/

	void editVdcProject(VdcProjQuotaMap vdcProjQuotaMapBean);

	void updateNewAvalilibtyInDpt(int dptFreeMem, int dptFreeVCPU,
			int dptFreeDisk, int dptID, int vdcId);

	VdcProjQuotaMap getVdcdetailsByDptID(int projId, int vdcId);

	VdcMaster getVdcName(Integer selectedVdc);

	void updateInUserQuotaTableGroup(VdcUserQuotaMap userQuota);

	List<Integer> getVdcIdListsByProjIDFromProjQuotaTable(int projId);

	List<VdcDeptQuotaMap> getVdcListsFromDptQuota(int dptID);

	String getVdcNamesByID(int vdc_id);

	void deleteQuotaInUsers(int id, Integer vdc_id);

	List<User> getUserByProjID(Integer projid);

	void updateUsersRoleInUsrs(Integer proj_id);

	Project getProj_name1(String proj_name);

}
