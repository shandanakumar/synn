package com.syntel.isap.provisioning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.syntel.isap.provisioning.dao.IDepartmentDao;
import com.syntel.isap.provisioning.mapper.DepartmentMapper;

/**
 * @author KK5007843
 *
 */

@Service("departmentDao")
public class DepartmentDaoImpl implements IDepartmentDao {

    @Autowired
    private DepartmentMapper departmentMapper;
	
	public List<Department> getDepartmentList(int cgId) {
		 List<Department> departments=new ArrayList<Department>();
		 departments=departmentMapper.getDepartmentList(cgId);
		return departments;
	}

	public void addDepartment(Department department) {
		departmentMapper.addDepartment(department);
	}

	public void addDepartmentAdmin(User user) {
		departmentMapper.addDepartmentAdmin(user);
	}

	public List<User> getUsersByCorporateId(User user) {
		return departmentMapper.getUsersByCorporateId(user);
	}

	public void mapRoleToUser(User user) {
		departmentMapper.mapRoleToUser(user);
	}

	public void mapDepartmentToUser(int usrId, int dptId) {
		departmentMapper.mapDepartmentToUser(usrId,dptId);
	}

	public String getUserNameById(int usrId) {
		return departmentMapper.getUserNameById(usrId);
	}

	public void updateDepartment(Integer deptId) {
		departmentMapper.updateDepartment(deptId);
	}

	public void updateUserRole(int userId) {
		departmentMapper.updateUserRole(userId);
	}

	public User getUserIdByDeptId(Integer deptId) {
		return departmentMapper.getUserIdByDeptId(deptId);	
	}

	public void updateUser(int userId) {
		departmentMapper.updateUser(userId);		
	}

	public Department getDepartmentById(Integer deptId) {
		return departmentMapper.getDepartmentById(deptId);
	}

	public User getOwnerByName(String userName) {
		return departmentMapper.getOwnerByName(userName);
	}

	public void updateDepartmentWithDepartment(Department department) {
		departmentMapper.updateDepartmentWithDepartment(department);
	}

	public User getOwnerUserId(User user) {
		return departmentMapper.getOwnerUserId(user);
	}

	public int getUserIdByName(String owner) {
		return departmentMapper.getUserIdByName(owner);
	}

/*	public List<Integer> getProjectByDeptID(Integer deptId) {
		return departmentMapper.getProjectByDeptID(deptId);
	}

	public List<Integer> getUserByDeptID(Integer projId) {
		return departmentMapper.getUserByDeptID(projId);
	}

	public void updateUserDptProjIds(Integer userID) {
		departmentMapper.updateUserDptProjIds(userID);
	}

	public void updateProjectDeleteFlag(Integer projId) {
		departmentMapper.updateProjectDeleteFlag(projId);
	}*/

	public VdcCgQuotaMap vdcCgQuotaByVdcIDandcgID(int selectedVdcId, int cgId) {
		return departmentMapper.vdcCgQuotaByVdcIDandcgID(selectedVdcId, cgId);
	}

	public List<VdcDeptQuotaMap> getRowsFromVdcDeptQuotaMapTable() {
		return departmentMapper.getRowsFromVdcDeptQuotaMapTable();
	}

	public int getSumOfTotalAllocatedMemoryToDpt(int selectedVdcId) {
		int sumAllocatedMemory =0; 
		sumAllocatedMemory =departmentMapper.getSumOfTotalAllocatedMemoryToDpt(selectedVdcId);
		return sumAllocatedMemory;
				
	}

	public int getSumOftotalAllocatedDptVcpu(int selectedVdcId) {
		int sumtotalAllocatedDptVcpu =0;
		sumtotalAllocatedDptVcpu =departmentMapper.getSumOftotalAllocatedDptVcpu(selectedVdcId);
		return sumtotalAllocatedDptVcpu;
	}

	public int gettotalAllocatedDptDiskStorage(int selectedVdcId) {
		int sumtotalAllocatedDptDiskStorage =0;
		sumtotalAllocatedDptDiskStorage =departmentMapper.gettotalAllocatedDptDiskStorage(selectedVdcId);
		return sumtotalAllocatedDptDiskStorage;
	}

	public void allocateVdcToDepaertmentGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean) {
		departmentMapper.allocateVdcToDepaertmentGroup(vdcDeptQuotaMapBean);
	}

	public int getCurrentavaliabvity(int cgId) {
		return departmentMapper.getCurrentavaliabvity(cgId);
	}

	public VdcCgQuotaMap getVDCdetailsBycgID(int cgID) {
		return departmentMapper.getVDCdetailsBycgID(cgID);
	}

	
	public void updateFreeSpacesinCgquota(int cgAvailableMem ,int cgAvailableVcpu,int cgAvailableDiskStorage,
			int selectedVdcId,int cgID) {
		departmentMapper.updateFreeSpacesinCgquota(cgAvailableMem,cgAvailableVcpu,cgAvailableDiskStorage,
				 selectedVdcId,cgID);
		
	}

	public VdcDeptQuotaMap getVdcdetails(int selectedVdcId, int dptId) {
		return departmentMapper.getVdcdetails(selectedVdcId,dptId);
	}

	public List<VdcDeptQuotaMap> getRowsFromDptQuotaMapTable(int cgID) {
		return departmentMapper.getRowsFromDptQuotaMapTable(cgID);
	}

	public Department getDptGrpByID(int dptId) {
		return departmentMapper.getDptjGrpByID(dptId);
	}

	public int getfree_memDB(int cgID,int selectedVdcId) {
     	return departmentMapper.getfree_memDB(cgID,selectedVdcId);		
	}

	public int getfree_vcpuDB(int cgID,int selectedVdcId) {
		return departmentMapper.getfree_vcpuDB(cgID,selectedVdcId);	
	}
	
	public int getfree_diskDB(int cgID,int selectedVdcId) {
			return departmentMapper.getfree_diskDB(cgID,selectedVdcId);	
	}
	
	public Department getDepartmentName(Integer dptid) {
		return departmentMapper.getDepartmentName(dptid);
	}

	public VdcCgQuotaMap getFreeMemoryinCGQuota(int selectedVdcId, int cgID) {
		return departmentMapper.getFreeMemoryinCGQuota(selectedVdcId,cgID);
	}

	public void deleteDepartmentQuota(Integer depID, int vdcId) {
		departmentMapper.deleteDepartmentQuota( depID,  vdcId);	
	}
	public VdcDeptQuotaMap getVdcdetailsByCgID(Integer deptId, int vdcId) {
		return departmentMapper.getVdcdetailsByCgID(deptId,vdcId);
	}

	public VdcCgQuotaMap getFreeVdcDeatilsInCGQuota(int cgID,int vdcId) {
		return departmentMapper.getFreeVdcDeatilsInCGQuota(cgID,vdcId);
	}

	public void updateFreememoryIncg(int newcgFreeDisk, int newcgFreeMem,int newcgFreeCvpu, int cgID,int vdcId) {
		departmentMapper.updateFreememoryIncg( newcgFreeDisk,newcgFreeMem,newcgFreeCvpu, cgID,vdcId);
	}

	public VdcDeptQuotaMap getAllocatedDetails(Integer dptid,Integer vdcID) {
		return departmentMapper.getAllocatedDetails(dptid,vdcID);
	}

	public VdcMaster getVdcName(Integer selectedVdc) {
		return departmentMapper.getVdcName(selectedVdc);
	}
	
	public void updateNewAvalilibtyInCg(int cgFreeMem,int cgFreeVCPU,int cgFreeDisk, int cgID,int vdcId) {
		departmentMapper.updateNewAvalilibtyInCg(cgFreeMem,cgFreeVCPU,cgFreeDisk,cgID,vdcId);	
	}

	public void editVdcToDepaertmentGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean) {
		
		departmentMapper.editVdcToDepaertmentGroup(vdcDeptQuotaMapBean);
	}

	public List<User> getDepartmentUserList(int cgId) {
		return departmentMapper.getDepartmentUserList(cgId) ;
	}

	public List<CorporateGroups> getCorporateName(int cgId) {
		return departmentMapper.getCorporateName(cgId);
	}

	public void addUserInUserTable(User usr) {
		departmentMapper.addUserInUserTable(usr);
	}

	public int getUserIdByuserNameInUsertable(String userName) {
		return 0;
	}

	public void insertRoleIdforUserId(int endUserRoleId, int usrId) {
		departmentMapper.insertRoleIdforUserId(endUserRoleId,usrId);
	}

	
	public void updateInUserQuotaTableGroup(VdcUserQuotaMap userQuota) {
		
		departmentMapper.updateInUserQuotaTableGroup(userQuota);
	}

	public List<Integer> getVdcIdListsByDptIDFromDptQuotaTable(int dptId) {
		// TODO Auto-generated method stub
		return departmentMapper.getVdcIdListsByDptIDFromDptQuotaTable(dptId);
	}

	public List<VdcCgQuotaMap> getVdcListsFromCgQuota(int cgID) {
		// TODO Auto-generated method stub
		return departmentMapper.getVdcListsFromCgQuota(cgID);
	}

	public String getVdcNameByID(int vdcid) {
		// TODO Auto-generated method stub
		return departmentMapper.getVdcNameByID(vdcid);
	}

	public void deleteQuotaInUser(int id, Integer vdc_id) {
		
		departmentMapper.deleteQuotaInUser(id,vdc_id);
	}

	public void updateVdcdetaisInUserQuota(VdcUserQuotaMap userquota) {
		
		departmentMapper.updateVdcdetaisInUserQuota(userquota);
	}

	public VdcDeptQuotaMap getAllocatedfreeMemory( int selectedVdcId,int dptid) {
		// TODO Auto-generated method stub
		return departmentMapper.getAllocatedfreeMemory(selectedVdcId,dptid);
	}

	public String getRoleNames(int usrID) {
		// TODO Auto-generated method stub
		return departmentMapper.getRoleNames(usrID);
	}


	public List<Project> getProjectByDeptID(Integer deptId) {
		// TODO Auto-generated method stub
		return departmentMapper.getProjectByDeptID(deptId);
	}

	public String getDptName(Integer dptId) {
		return departmentMapper.getDptName(dptId);
	}



	public void updateUsersRoleInUsrs(Integer deptId) {
		// TODO Auto-generated method stub
		departmentMapper.updateUsersRoleInUsrs(deptId);
	}

	public Department geDept_name1(String dept_name){
		// TODO Auto-generated method stub
		return departmentMapper.geDept_name1(dept_name);
	}

	public List<User> getPoolUsers() {
		// TODO Auto-generated method stub
		return departmentMapper.getPoolUsers();
	}

	public void updateUserInfo(int id, int cgID) {
		
		departmentMapper.updateUserInfo(id,cgID);
	}

	public List<User> getDepartmentUser1List(int dpt_id) {
		// TODO Auto-generated method stub
		return departmentMapper.getDepartmentUser1List(dpt_id);
	}

	public String getPRoleName(int usr_id) {
		// TODO Auto-generated method stub
		return departmentMapper.getPRoleName(usr_id);
	}

	public List<User> getCPoolUsers(int cg_id) {
		// TODO Auto-generated method stub
		return departmentMapper.getCPoolUsers(cg_id);
	}

	public void updateUserssInfo(int id,int dept_id) {
		// TODO Auto-generated method stub
		departmentMapper.updateUserssInfo(dept_id,id);
	}

	

	public void deletePoolUser(Integer usr_id) {
		departmentMapper.deletePoolUser(usr_id);
		
	}




	
	


}
