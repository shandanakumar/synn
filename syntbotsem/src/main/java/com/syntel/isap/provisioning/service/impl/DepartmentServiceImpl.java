package com.syntel.isap.provisioning.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.syntel.isap.provisioning.mapper.LoginMapper;
import com.syntel.isap.provisioning.service.IDepartmentService;

/**
 * @author KK5007843
 *
 */

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;

	@Autowired
	private LoginMapper loginMapper;

	private static final Logger LOGGER = Logger
			.getLogger(LoginServiceImpl.class.getName());

	@Transactional
	public List<Department> getDepartmentList(int cgId) {
		List<Department> departments = new ArrayList<Department>();
		LOGGER.info("Inside ServiceImpl getDepartmentList");
		departments = departmentDao.getDepartmentList(cgId);
		for (Department dept : departments)
			LOGGER.info("deptId" + dept.getDpt_id());
		return departments;
	}

	@Transactional
	public void addDepartment(Department department, User user,
			String corpAdminName) {
		department.setStatus("Active");
		LOGGER.info("Inside ServiceImpl addDepartment" + department.getStatus());
		department.setCg_id(user.getCg_id());
		department.setCreated_by(corpAdminName);
		department.setOwner(departmentDao.getUserNameById(user.getUsr_id()));
		departmentDao.addDepartment(department);
		user.setDpt_id(department.getDpt_id());
		departmentDao.mapRoleToUser(user);
		departmentDao.mapDepartmentToUser(user.getUsr_id(), user.getDpt_id());
	}

	@Transactional
	public void editDepartment(Department department, User user) {
		// department.setStatus("Active");
		LOGGER.info("Inside ServiceImpl editDepartment"
				+ department.getDpt_id());
		Department owner = departmentDao.getDepartmentById(department
				.getDpt_id());
		LOGGER.info("Inside ServiceImpl editDepartment"
				+ department.getDpt_id() + ":" + owner.getCreated_by());
		if (!owner.getCreated_by().trim().equals("")) {
			if (!owner.getCreated_by().equals(
					departmentDao.getUserNameById(user.getUsr_id()))) {
				User usr = new User();
				usr = departmentDao.getUserIdByDeptId(department.getDpt_id());
				int userId = usr.getUsr_id();
				departmentDao.updateUser(userId);
				departmentDao.updateUserRole(userId);
				user.setDpt_id(department.getDpt_id());
				departmentDao.mapRoleToUser(user);
				departmentDao.mapDepartmentToUser(user.getUsr_id(),
						user.getDpt_id());
			}
		} else {
			user.setDpt_id(department.getDpt_id());
			departmentDao.mapRoleToUser(user);
			departmentDao.mapDepartmentToUser(user.getUsr_id(),
					user.getDpt_id());
		}
		department.setOwner(departmentDao.getUserNameById(user.getUsr_id()));
		LOGGER.info("Inside ServiceImpl editDepartment"
				+ department.getDpt_id() + ":" + department.getDpt_desc() + ":"
				+ department.getDpt_name());
		departmentDao.updateDepartmentWithDepartment(department);
	}

	@Transactional
	public List<User> getUsersByCorporate(User user) {
		LOGGER.info("Inside ServiceImpl getUsersByCorporate" + user.getCg_id());
		return departmentDao.getUsersByCorporateId(user);
	}

	@Transactional
	public void deleteDepartment(Integer deptId) {
	
		LOGGER.info("Inside ServiceImpl deleteDepartment" + deptId);
		/*	User user = new User();
		 user = departmentDao.getUserIdByDeptId(deptId);
		if (user != null) {
			int Id = user.getUsr_id();
			LOGGER.info("Inside ServiceImpl userId of the departemnt" + Id);
			List<Integer> projIds = departmentDao.getProjectByDeptID(deptId);
			if (!projIds.isEmpty()) {
				for (Integer projId : projIds) {
					departmentDao.updateProjectDeleteFlag(projId);
					List<Integer> userIds = departmentDao
							.getUserByDeptID(projId);
					if (!userIds.isEmpty()) {
						for (Integer userID : userIds) {
							departmentDao.updateUserDptProjIds(userID);
							departmentDao.updateUserRole(userID);
						}
					}
				}
			}
			departmentDao.updateUserRole(Id);
			departmentDao.updateUser(Id);
		}*/
		departmentDao.updateDepartment(deptId);
		departmentDao.updateUsersRoleInUsrs(deptId);
	}

	@Transactional
	public Department getDepartmentById(Integer deptId) {
		LOGGER.info("Inside ServiceImpl getDepartmentById" + deptId);
		return departmentDao.getDepartmentById(deptId);
	}

	@Transactional
	public List<User> getUsersByCorporateJSON(User user) {
		LOGGER.info("Inside ServiceImpl getUsersByCorporateJSON");
		List<User> userList = departmentDao.getUsersByCorporateId(user);
		return userList;
	}

	@Transactional
	public User getOwnerByName(String userName) {
		LOGGER.info("Inside ServiceImpl getOwnerByName" + userName);
		User user = new User();
		if (userName.equals(" ") || userName == null) {
			user.setUsr_id(0);
		} else {
			user = departmentDao.getOwnerByName(userName);
		}
		return user;
	}

	@Transactional
	public VdcCgQuotaMap vdcCgQuotaByVdcIDandcgID(int selectedVdcId, int cgId) {
		return departmentDao.vdcCgQuotaByVdcIDandcgID(selectedVdcId, cgId);
	}

	@Transactional
	public List<VdcDeptQuotaMap> getRowsFromVdcDeptQuotaMapTable() {
		return departmentDao.getRowsFromVdcDeptQuotaMapTable();
	}

	@Transactional
	public int getSumOfTotalAllocatedMemoryToDpt(int selectedVdcId) {
		int sumAllocatedMemory = 0;
		sumAllocatedMemory = departmentDao
				.getSumOfTotalAllocatedMemoryToDpt(selectedVdcId);
		return sumAllocatedMemory;
	}

	@Transactional
	public int getSumOftotalAllocatedDptVcpu(int selectedVdcId) {
		int sumAllocatedDptVcpu = 0;
		sumAllocatedDptVcpu = departmentDao
				.getSumOftotalAllocatedDptVcpu(selectedVdcId);
		return sumAllocatedDptVcpu;
	}

	@Transactional
	public int gettotalAllocatedDptDiskStorage(int selectedVdcId) {
		int sumAllocatedDptDiskStorage = 0;
		sumAllocatedDptDiskStorage = departmentDao
				.gettotalAllocatedDptDiskStorage(selectedVdcId);
		return sumAllocatedDptDiskStorage;
	}

	@Transactional
	public void allocateVdcToDepaertmentGroup(
			VdcDeptQuotaMap vdcDeptQuotaMapBean, int cgID) {
		int freeMem;
		int freeDisk;
		int freeVcpu;
		int allocatedmem;
		int allocatedDisk;
		int allocatedVcpu;
		int newFreemem;
		int newFreeDisk;
		int newFreeVcpu;
		vdcDeptQuotaMapBean.setCg_id(cgID);
		departmentDao.allocateVdcToDepaertmentGroup(vdcDeptQuotaMapBean);
		int selectedVdcId = vdcDeptQuotaMapBean.getVdc_id();
		VdcCgQuotaMap cgquotamap = departmentDao.getFreeMemoryinCGQuota(
				selectedVdcId, cgID);
		freeMem = cgquotamap.getFree_mem();
		freeDisk = cgquotamap.getFree_disk_storage();
		freeVcpu = cgquotamap.getFree_vcpu();
		allocatedmem = vdcDeptQuotaMapBean.getTotal_mem();
		allocatedDisk = vdcDeptQuotaMapBean.getTotal_disk_storage();
		allocatedVcpu = vdcDeptQuotaMapBean.getTotal_vcpu();
		newFreemem = freeMem - allocatedmem;
		newFreeDisk = freeDisk - allocatedDisk;
		newFreeVcpu = freeVcpu - allocatedVcpu;
		VdcCgQuotaMap vdcCgQuotaMap = new VdcCgQuotaMap();
		vdcCgQuotaMap.setFree_mem(newFreemem);
		vdcCgQuotaMap.setFree_vcpu(newFreeVcpu);
		vdcCgQuotaMap.setFree_disk_storage(newFreeDisk);
		int cgAvailableMem = vdcCgQuotaMap.getFree_mem();
		int cgAvailableVcpu = vdcCgQuotaMap.getFree_vcpu();
		int cgAvailableDiskStorage = vdcCgQuotaMap.getFree_disk_storage();
		departmentDao.updateFreeSpacesinCgquota(cgAvailableMem,
				cgAvailableVcpu, cgAvailableDiskStorage, selectedVdcId, cgID);

	}

	@Transactional
	public int getAvaliableVcpuAllocated(String totalCgVcpu, int cgId) {
		int avalibaleVcpu = departmentDao.getCurrentavaliabvity(cgId);
		return avalibaleVcpu;
	}

	@Transactional
	public int getVdcdetails(int selectedVdcId, int dptId) {
		VdcDeptQuotaMap vdcDeptQuotaMap = departmentDao.getVdcdetails(
				selectedVdcId, dptId);
		if (vdcDeptQuotaMap != null) {
			return 1;
		}
		return 0;
	}

	@Transactional
	public List<VdcDeptQuotaMap> getRowsFromDptQuotaMapTable(int cgID) {
		List<VdcDeptQuotaMap> vdcDptQuotaMapBeanList = new ArrayList<VdcDeptQuotaMap>();
		vdcDptQuotaMapBeanList = departmentDao
				.getRowsFromDptQuotaMapTable(cgID);
		for (VdcDeptQuotaMap vdcDptQuotaMapBean : vdcDptQuotaMapBeanList) {
			int dptId = vdcDptQuotaMapBean.getDpt_id();
			int vdcID = vdcDptQuotaMapBean.getVdc_id();
			VdcMaster vdcmaster = departmentDao.getVdcName(vdcID);
			Department dptgrp = departmentDao.getDptGrpByID(dptId);
			vdcDptQuotaMapBean.setVdcmasterbean(vdcmaster);
			vdcDptQuotaMapBean.setDptbean(dptgrp);
		}
		return vdcDptQuotaMapBeanList;
	}

	@Transactional
	public Department getDptGrpByID(int dptId) {
		return departmentDao.getDptGrpByID(dptId);
	}

	@Transactional
	public int getfree_mem(int freeMem, int cgID, int selectedVdcId) {

		int freeMeory = departmentDao.getfree_memDB(cgID, selectedVdcId);
		
		if (freeMeory >= freeMem) {
			return 1;
		}
		
		/*int allocatedFreememory = departmentDao.getAllocatedfreeMemory(dptid,selectedVdcId);
		
		int negativalue = freeMem-allocatedFreememory ;
		
		if(negativalue<0)
		{
			return 0;
		}
		
		*/
		return 0;
	}

	@Transactional
	public int getfree_vcpu(int freeCpu, int cgID, int selectedVdcId) {

		int freeVcpu = departmentDao.getfree_vcpuDB(cgID, selectedVdcId);
		if (freeVcpu >= freeCpu) {
			return 1;
		}
		return 0;
	}

	@Transactional
	public int getfree_disk(int freeDiskStorage, int cgID, int selectedVdcId) {

		int freeDisk = departmentDao.getfree_diskDB(cgID, selectedVdcId);

		if (freeDisk >= freeDiskStorage) {
			return 1;
		}
		return 0;
	}

	@Transactional
	public Department getDepartmentName(int dptid) {
		return departmentDao.getDepartmentName(dptid);
	}

	@Transactional
	public void deleteDepartmentQuota(Integer depID, int vdcId) {
		departmentDao.deleteDepartmentQuota(depID, vdcId);

	}

	@Transactional
	public VdcDeptQuotaMap getVdcdetailsByCgID(Integer deptId, int vdcId) {
		return departmentDao.getVdcdetailsByCgID(deptId, vdcId);
	}

	@Transactional
	public void updateFreememoryInCgquota(VdcDeptQuotaMap vdcDeptQuotaMap,
			int cgID) {
		int newcgFreeDisk = 0;
		int newcgFreeMem = 0;
		int newcgFreeCvpu = 0;
		int vdcId = vdcDeptQuotaMap.getVdc_id();
		int dptFreeDisk = vdcDeptQuotaMap.getFree_disk_storage();
		int dptFreeMem = vdcDeptQuotaMap.getFree_mem();
		int dptFreeVcpu = vdcDeptQuotaMap.getFree_vcpu();
		VdcCgQuotaMap vdcCgQuotaMap = new VdcCgQuotaMap();
		vdcCgQuotaMap = departmentDao.getFreeVdcDeatilsInCGQuota(cgID, vdcId);
		int cgFreeDisk = vdcCgQuotaMap.getFree_disk_storage();
		int cgFreeMem = vdcCgQuotaMap.getFree_mem();
		int cgFreeVcpu = vdcCgQuotaMap.getFree_vcpu();
		newcgFreeDisk = cgFreeDisk + dptFreeDisk;
		newcgFreeMem = cgFreeMem + dptFreeMem;
		newcgFreeCvpu = cgFreeVcpu + dptFreeVcpu;
		departmentDao.updateFreememoryIncg(newcgFreeDisk,newcgFreeMem, 
				newcgFreeCvpu, cgID, vdcId);
	}

	@Transactional
	public VdcDeptQuotaMap getAllocatedDetails(Integer dptid, Integer vdcID) {
		return departmentDao.getAllocatedDetails(dptid, vdcID);
	}

	@Transactional
	public int getfree_vcpuprojects(int free_vcpu, int cgID) {
		return 0;
	}

	@Transactional
	public int getfree_diskstorage(int freeDiskStorage, int cgID) {
		return 0;
	}

	@Transactional
	public VdcMaster getVdcName(Integer selectedVdc) {
		return departmentDao.getVdcName(selectedVdc);
	}

	@Transactional
	public void editVdcToDepaertmentGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean,
			int cgID) {
		int dptId = vdcDeptQuotaMapBean.getDpt_id();
		int vdcId = vdcDeptQuotaMapBean.getVdc_id();
		int editFreeMem = vdcDeptQuotaMapBean.getTotal_mem();
		int editFreeDisk = vdcDeptQuotaMapBean.getTotal_disk_storage();
		int editFreeVCPU = vdcDeptQuotaMapBean.getTotal_vcpu();
		VdcDeptQuotaMap vdcdept = new VdcDeptQuotaMap();
		VdcUserQuotaMap userquota = new VdcUserQuotaMap();
		vdcdept = departmentDao.getVdcdetailsByCgID(dptId, vdcId);
		int totalFreemem = vdcdept.getTotal_mem();
		userquota.setTotal_mem(totalFreemem);
		int totalFreedisk = vdcdept.getTotal_disk_storage();
		userquota.setTotal_disk_storage(totalFreedisk);
		int totalFreeVcpu = vdcdept.getTotal_vcpu();
		userquota.setTotal_vcpu(totalFreeVcpu);
		int allocatedFreemem = vdcdept.getFree_mem();
		int alocatedFreedisk = vdcdept.getFree_disk_storage();
		int allocatedFreeVcpu = vdcdept.getFree_vcpu();
		vdcDeptQuotaMapBean.setFree_mem(allocatedFreemem);
		userquota.setFree_mem(allocatedFreemem);
		vdcDeptQuotaMapBean.setFree_disk_storage(alocatedFreedisk);
		userquota.setFree_disk_storage(alocatedFreedisk);
		vdcDeptQuotaMapBean.setFree_vcpu(allocatedFreeVcpu);
		userquota.setFree_vcpu(allocatedFreeVcpu);
		VdcCgQuotaMap vdcCg = new VdcCgQuotaMap();
		vdcCg = departmentDao.getFreeMemoryinCGQuota(vdcId, cgID);
		int cgFreeMem = vdcCg.getFree_mem();
		int cgFreeDisk = vdcCg.getFree_disk_storage();
		int cgFreeVCPU = vdcCg.getFree_vcpu();
		if (editFreeMem > totalFreemem) {
			int extraTotalFreemem = editFreeMem - totalFreemem;
			allocatedFreemem = allocatedFreemem + extraTotalFreemem;
			cgFreeMem = cgFreeMem - extraTotalFreemem;
			vdcDeptQuotaMapBean.setFree_mem(allocatedFreemem);
			userquota.setFree_mem(allocatedFreemem);
		}
		if (editFreeDisk > totalFreedisk) {
			int extraTotalFreeDisk = editFreeDisk - totalFreedisk;
			alocatedFreedisk = alocatedFreedisk + extraTotalFreeDisk;
			cgFreeDisk = cgFreeDisk - extraTotalFreeDisk;
			vdcDeptQuotaMapBean.setFree_disk_storage(alocatedFreedisk);
			userquota.setFree_disk_storage(alocatedFreedisk);
		}
		if (editFreeVCPU > totalFreeVcpu) {
			int extraTotalFreeVCPU = editFreeVCPU - totalFreeVcpu;
			allocatedFreeVcpu = allocatedFreeVcpu + extraTotalFreeVCPU;
			vdcDeptQuotaMapBean.setFree_vcpu(allocatedFreeVcpu);
			userquota.setFree_vcpu(allocatedFreeVcpu);
			cgFreeVCPU = cgFreeVCPU - extraTotalFreeVCPU;
		}
		if (editFreeMem < totalFreemem) {
			int lessTotalFreeMem = totalFreemem - editFreeMem;
			allocatedFreemem = allocatedFreemem - lessTotalFreeMem;
			vdcDeptQuotaMapBean.setFree_mem(allocatedFreemem);
			userquota.setFree_mem(allocatedFreemem);
			cgFreeMem = cgFreeMem + lessTotalFreeMem;
		}
		if (editFreeDisk < totalFreedisk) {
			int lessTotalFreedisk = totalFreedisk - editFreeDisk;
			alocatedFreedisk = alocatedFreedisk - lessTotalFreedisk;
			vdcDeptQuotaMapBean.setFree_disk_storage(alocatedFreedisk);
			userquota.setFree_disk_storage(alocatedFreedisk);
			cgFreeDisk = cgFreeDisk + lessTotalFreedisk;
		}
		if (editFreeVCPU < totalFreeVcpu) {
			int lessTotalFreeVCPU = totalFreeVcpu - editFreeVCPU;
			cgFreeVCPU = cgFreeVCPU + lessTotalFreeVCPU;
			allocatedFreeVcpu = allocatedFreeVcpu - lessTotalFreeVCPU;
			vdcDeptQuotaMapBean.setFree_vcpu(allocatedFreeVcpu);
			userquota.setFree_vcpu(allocatedFreeVcpu);
		}
		
		Department dpt = departmentDao.getDepartmentById(dptId);
		String owner = dpt.getOwner();
		User userDetails = departmentDao.getOwnerByName(owner);
		int id = userDetails.getUsr_id();
		userquota.setUsr_id(id);
		userquota.setVdc_id(vdcId);
		
		departmentDao.updateNewAvalilibtyInCg(cgFreeMem, cgFreeVCPU,cgFreeDisk, cgID, vdcId);
		departmentDao.editVdcToDepaertmentGroup(vdcDeptQuotaMapBean);
		departmentDao.updateVdcdetaisInUserQuota(userquota);
	}

	@Transactional
	public List<User> getDepartmentUserList(int cgId) {
		
		List<User> userList=departmentDao.getDepartmentUserList(cgId);
		
		for (User user : userList) {
			int usrID = user.getUsr_id();
			int DptId =user.getDpt_id();
			String s1= departmentDao.getRoleNames(usrID);
			user.setRole_name(s1);
			String s2 = departmentDao.getDptName(DptId);
			user.setDpt_name(s2);
			System.out.println("role name from service impl"+s1);
			System.out.println("department name from service impl"+s2);
			
		}
		
		return userList;
		
		


		
		
	}

	@Transactional
	public List<Department> getDepartmentNameinList(List<User> usersList) {
		
		/*List<Department> dptList = new ArrayList<Department>();
		for (User user : usersList) {
			int DptId = user.getDpt_id();
			String s2 = departmentDao.getDptName(DptId);
			System.out.println("department name from impl"+dptList);
		}*/
		return null;
	}

	@Transactional
	public void addUserInUserTable(User usr) {
		String userName = null;
		int usrId = 0;
		int projId = 0;
		int endUserRoleId = 5;
		int userRoleId = 6;
		departmentDao.addUserInUserTable(usr);
		userName = usr.getUsr_name();
		usrId = departmentDao.getUserIdByName(userName);
		projId = usr.getProj_id();
		if (projId != 1) {
			departmentDao.insertRoleIdforUserId(endUserRoleId, usrId);
		} else {
			departmentDao.insertRoleIdforUserId(userRoleId, usrId);
		}
	}

	@Transactional
	public void updateInUserQuotaTableGroup(
			VdcDeptQuotaMap vdcDeptQuotaMapBean, int dptId, HttpSession session) {
		LOGGER.info("Inside ServiceImpl updateInUserQuotaTableGroup" + dptId);
		LOGGER.info("Inside ServiceImpl VdcID "
				+ vdcDeptQuotaMapBean.getVdc_id());
		User user = (User) session.getAttribute("userValue");
		Department dpt = departmentDao.getDepartmentById(dptId);
		String owner = dpt.getOwner();
		User userDetails = departmentDao.getOwnerByName(owner);
		int id = userDetails.getUsr_id();
		VdcUserQuotaMap userQuota = new VdcUserQuotaMap();
		userQuota.setUsr_id(id);
		LOGGER.info("Inside ServiceImpl updateInUserQuotaTableGroup USERID:" + id);
		userQuota.setVdc_id(vdcDeptQuotaMapBean.getVdc_id());
		userQuota.setTotal_mem(vdcDeptQuotaMapBean.getTotal_mem());
		userQuota.setTotal_disk_storage(vdcDeptQuotaMapBean
				.getTotal_disk_storage());
		userQuota.setTotal_vcpu(vdcDeptQuotaMapBean.getTotal_vcpu());
		userQuota.setFree_disk_storage(vdcDeptQuotaMapBean
				.getFree_disk_storage());
		userQuota.setFree_mem(vdcDeptQuotaMapBean.getFree_mem());
		userQuota.setFree_vcpu(vdcDeptQuotaMapBean.getFree_vcpu());
		userQuota.setCreated_by(user.getUsr_name());
		departmentDao.updateInUserQuotaTableGroup(userQuota);
	}
	
	@Transactional
	public void deleteQuotaInUserQuota(Integer dpt_id,Integer vdc_id, HttpSession session) {
		
		LOGGER.info("Inside ServiceImpl deleteQuotaInUserQuota" + dpt_id);
	
		//User user = (User) session.getAttribute("userValue");
		Department dpt = departmentDao.getDepartmentById(dpt_id);
		String owner = dpt.getOwner();
		User userDetails = departmentDao.getOwnerByName(owner);
		int id = userDetails.getUsr_id();
		LOGGER.info("Inside ServiceImpl deleteQuotaInUserQuota" + id);
		departmentDao.deleteQuotaInUser(id,vdc_id);
		
	}

	@Transactional
	public List<VdcCgQuotaMap> getVdcListsFromVdcMaster(int dptId,int cgID) {
		
		List<VdcCgQuotaMap> vdcList = new ArrayList<VdcCgQuotaMap>();
		List<Integer> vdcIdList = new ArrayList<Integer>();
	
		vdcIdList =departmentDao.getVdcIdListsByDptIDFromDptQuotaTable(dptId);
		vdcList = departmentDao.getVdcListsFromCgQuota(cgID);
		
		Iterator<Integer> itr1 = vdcIdList.iterator();
		while (itr1.hasNext()){
			int j = itr1.next();
			Iterator<VdcCgQuotaMap> itr = vdcList.iterator();
				for(int k = 0;k<vdcList.size();k++){
					while(itr.hasNext()){
						VdcCgQuotaMap vdcCgBean = new VdcCgQuotaMap();
						vdcCgBean = itr.next();
						if(j == vdcCgBean.getVdc_id())
						{
							itr.remove();
						}
					}
				}
			}
		return vdcList;
		
		
	}
	@Transactional
	public String getVdcNameByID(int vdcid) {
		// TODO Auto-generated method stub
		return departmentDao.getVdcNameByID(vdcid);
	}
	@Transactional
	public int checkAllocatedmem(int freemem, int cgID, int selectedVdcId,int dptid) {
		
		
		VdcDeptQuotaMap dptQuota = departmentDao.getAllocatedfreeMemory(selectedVdcId,dptid);
		
     int Totalmemory = dptQuota.getTotal_mem();
     System.out.println("The Totalmemory value is------------ "+Totalmemory);
     int freeMemory = dptQuota.getFree_mem();
     
     if(freemem<Totalmemory)
     {
     int   newtotalmemory = Totalmemory-freemem;
     int negativeMemory = newtotalmemory - freeMemory;
		
		System.out.println("The negative value is------------ "+negativeMemory);
		
		if(negativeMemory<0)
		{
			return 1;
		}
		
     }
		return 0;
		
	}

	/*public List<User> getRoleNameInList(List<User> usersList) {
		
		List<User> userList=departmentDao.getDepartmentUserList(cgId);

		List<User> usersList1 = new  ArrayList<User>();
		for (User user : usersList1) {
			int usrID = user.getUsr_id();
			userList = departmentDao.getRoleNames(usrID);
			
			
		}
		
		return userList;
	}*/
	@Transactional
	public List<Project> getProjectByDeptID(Integer deptId) {
		// TODO Auto-generated method stub
		return departmentDao.getProjectByDeptID(deptId);
	}
	@Transactional
	public int geDept_name(String dept_name) {
		// TODO Auto-generated method stub
		Department dept=departmentDao.geDept_name1(dept_name);
		if(dept!=null)
		{
			return 1;
		}
		else
		{
			return 0;
		}
		 
	}
	@Transactional
	public List<User> getPoolUsers() {
		return departmentDao.getPoolUsers();

		
	}
	@Transactional
	public void addPoolUsertoCoprporate(User user, String user_name) {
		
		String[] usr_id = user_name.split(",");
		
	for(String str:usr_id)
	{
		int cgID = user.getCg_id();
		int id = Integer.parseInt(str);
		
		departmentDao.updateUserInfo(id,cgID);
	}
		

		
		
	}
	@Transactional
	public List<User> getDepartmentUser1List(int dpt_id) {
       List<User> userList=departmentDao.getDepartmentUser1List(dpt_id);
		
		for (User user : userList) {
			int usr_id=user.getUsr_id();
			String role_name=departmentDao.getPRoleName(usr_id);
			user.setRole_name(role_name);
		}
		
		return userList;
	}
	
	@Transactional
	public List<User> getCPoolUsers(int cg_id) {
		// TODO Auto-generated method stub
		 return departmentDao.getCPoolUsers(cg_id);
	}
	
	@Transactional
	public void addCPoolUsertoDepartment(User user, String user_name) {
		String[] usr_id = user_name.split(",");
		//int cgID = user.getCg_id();
		
		for(String str:usr_id)
		{
			int dept_id=user.getDpt_id();
			System.out.println("the valueof dpt_id is:"+dept_id);
			int id = Integer.parseInt(str);
			departmentDao.updateUserssInfo(dept_id,id);
		}
	}

	public void deletePoolUser(Integer usr_id) {
		departmentDao.deletePoolUser(usr_id);		
	}




	
}
