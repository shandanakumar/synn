package com.syntel.isap.provisioning.dao;

import java.util.List;

import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.Role;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;

/**
 * @author KK5007843
 *
 */

public interface IDepartmentDao {

	List<Department> getDepartmentList(int cgId);

	void addDepartment(Department department);

	void addDepartmentAdmin(User user);

	List<User> getUsersByCorporateId(User user);

	void mapRoleToUser(User user);

	void mapDepartmentToUser(int usrId, int dptId);

	String getUserNameById(int usrId);

	void updateDepartment(Integer deptId);

	

	
	Department getDepartmentById(Integer deptId);

	User getOwnerByName(String userName);

	void updateDepartmentWithDepartment(Department department);

	User getOwnerUserId(User user);

	int getUserIdByName(String owner);
	
	void updateUserRole(int userId);
	
	User getUserIdByDeptId(Integer deptId);

	void updateUser(int userIdByDeptId);
	
	
	/*


    List<Integer> getProjectByDeptID(Integer deptId);

	List<Integer> getUserByDeptID(Integer projId);

	void updateUserDptProjIds(Integer userId);

	void updateProjectDeleteFlag(Integer projId);
*/
	VdcCgQuotaMap vdcCgQuotaByVdcIDandcgID(int selectedVdcId, int cgId);

	List<VdcDeptQuotaMap> getRowsFromVdcDeptQuotaMapTable();

	int getSumOfTotalAllocatedMemoryToDpt(int selectedVdcId);

	int getSumOftotalAllocatedDptVcpu(int selectedVdcId);

	int gettotalAllocatedDptDiskStorage(int selectedVdcId);

	void allocateVdcToDepaertmentGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean);

	int getCurrentavaliabvity(int cgId);

	VdcCgQuotaMap getVDCdetailsBycgID(int cgID);

	void updateFreeSpacesinCgquota(int cgAvailableMem, int cgAvailableVcpu,
			int cgAvailableDiskStorage, int selectedVdcId, int cgID);

	VdcDeptQuotaMap getVdcdetails(int selectedVdcId, int dptId);

	List<VdcDeptQuotaMap> getRowsFromDptQuotaMapTable(int cgID);

	Department getDptGrpByID(int dptId);

	int getfree_memDB(int cgID, int selectedVdcId);

	int getfree_vcpuDB(int cgID, int selectedVdcId);

	int getfree_diskDB(int cgID, int selectedVdcId);

	Department getDepartmentName(Integer dptId);

	VdcCgQuotaMap getFreeMemoryinCGQuota(int selectedVdcId, int cgID);

	void deleteDepartmentQuota(Integer depID, int cgID);

	VdcDeptQuotaMap getVdcdetailsByCgID(Integer deptId, int vdcId);

	VdcCgQuotaMap getFreeVdcDeatilsInCGQuota(int cgID, int vdcId);

	void updateFreememoryIncg(int newcgFreeDisk, int newcgFreeMem,
			int newcgFreeCvpu, int cgID, int vdcId);

	VdcDeptQuotaMap getAllocatedDetails(Integer dptid, Integer vdcID);

	VdcMaster getVdcName(Integer selectedVdc);

	void updateNewAvalilibtyInCg(int cgFreeMem, int cgFreeVCPU, int cgFreeDisk,
			int cgID, int vdcId);

	void editVdcToDepaertmentGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean);

	List<User> getDepartmentUserList(int cgId);

	List<CorporateGroups> getCorporateName(int cgId);

	void addUserInUserTable(User usr);

	int getUserIdByuserNameInUsertable(String userName);

	void insertRoleIdforUserId(int endUserRoleId, int usrId);

	void updateInUserQuotaTableGroup(VdcUserQuotaMap userQuota);

	List<Integer> getVdcIdListsByDptIDFromDptQuotaTable(int dptId);

	List<VdcCgQuotaMap> getVdcListsFromCgQuota(int cgID);

	String getVdcNameByID(int vdcid);

	void deleteQuotaInUser(int id, Integer vdc_id);

	void updateVdcdetaisInUserQuota(VdcUserQuotaMap userquota);

	VdcDeptQuotaMap getAllocatedfreeMemory( int selectedVdcId,int dptid);

	String getRoleNames(int usrID);

	String getDptName(Integer dptId);
	

	/*List<User> getRoleNames(int usrID);*/

	List<Project> getProjectByDeptID(Integer deptId);

	void updateUsersRoleInUsrs(Integer deptId);

	Department geDept_name1(String dept_name);

	List<User> getPoolUsers();

	void updateUserInfo(int id, int cgID);

	List<User> getDepartmentUser1List(int dpt_id);

	String getPRoleName(int usr_id);

	List<User> getCPoolUsers(int cg_id);

	void updateUserssInfo(int dept_id, int id);



	void deletePoolUser(Integer usr_id);
}
