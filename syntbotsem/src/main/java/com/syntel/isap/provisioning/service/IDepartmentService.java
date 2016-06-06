package com.syntel.isap.provisioning.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.Role;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;

/**
 * @author KK5007843
 *
 */

public interface IDepartmentService {

	List<Department> getDepartmentList(int cgId);

	void addDepartment(Department department, User user, String corpAdminName);

	List<User> getUsersByCorporate(User user);

	void deleteDepartment(Integer deptId);

	Department getDepartmentById(Integer deptId);

	List<User> getUsersByCorporateJSON(User user);

	User getOwnerByName(String userName);

	void editDepartment(Department department, User user);

	VdcCgQuotaMap vdcCgQuotaByVdcIDandcgID(int selectedVdcId, int cgId);

	List<VdcDeptQuotaMap> getRowsFromVdcDeptQuotaMapTable();

	int getSumOfTotalAllocatedMemoryToDpt(int selectedVdcId);

	int getSumOftotalAllocatedDptVcpu(int selectedVdcId);

	int gettotalAllocatedDptDiskStorage(int selectedVdcId);

	void allocateVdcToDepaertmentGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean,
			int cgID);

	int getAvaliableVcpuAllocated(String totalCgVcpu, int cgId);

	int getVdcdetails(int selectedVdcId, int dptId);

	List<VdcDeptQuotaMap> getRowsFromDptQuotaMapTable(int cgID);

	Department getDptGrpByID(int dptId);

	int getfree_mem(int freeMem, int cgID, int selectedVdcId);

	int getfree_vcpu(int freeVcpu, int cgID, int selectedVdcId);

	int getfree_disk(int freeDiskStorage, int cgID, int selectedVdcId);

	Department getDepartmentName(int dptid);

	void deleteDepartmentQuota(Integer depID, int cgID);

	VdcDeptQuotaMap getVdcdetailsByCgID(Integer deptId, int vdcId);

	void updateFreememoryInCgquota(VdcDeptQuotaMap vdcDeptQuotaMap, int cgID);

	VdcDeptQuotaMap getAllocatedDetails(Integer dptId, Integer vdcID);

	VdcMaster getVdcName(Integer selectedVdc);

	void editVdcToDepaertmentGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean, int cgID);

	List<User> getDepartmentUserList(int cgId);

	List<Department> getDepartmentNameinList(List<User> usersList);

	void addUserInUserTable(User usr);

	void updateInUserQuotaTableGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean,
			int id, HttpSession session);

	List<VdcCgQuotaMap> getVdcListsFromVdcMaster(int dptId, int cgID);

	String getVdcNameByID(int vdcid);

	void deleteQuotaInUserQuota(Integer dpt_id,Integer vdc_id, HttpSession session);

	int checkAllocatedmem(int free_mem, int cgID, int selectedVdcId, int dptid);

	/*List<User>getRoleNameInList(List<User> usersList);*/

	List<Project> getProjectByDeptID(Integer deptId);

	int geDept_name(String dept_name);

	List<User> getPoolUsers();

	void addPoolUsertoCoprporate(User user, String user_name);

	List<User> getDepartmentUser1List(int dpt_id);

	List<User> getCPoolUsers(int cg_id);

	void addCPoolUsertoDepartment(User user, String user_name);



	void deletePoolUser(Integer usr_id);


}
