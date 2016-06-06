package com.syntel.isap.provisioning.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;
import com.syntel.isap.provisioning.dao.IDepartmentDao;
import com.syntel.isap.provisioning.dao.IProjectDao;
import com.syntel.isap.provisioning.service.IProjectService;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectDao projectDao;

	@Autowired
	private IDepartmentDao departmentDao;

	private static final Logger LOGGER = Logger
			.getLogger(LoginServiceImpl.class.getName());

	@Transactional
	public List<Project> getprojectLists(int dptid) {
		// TODO Auto-generated method stub
		return projectDao.getprojectLists(dptid);
	}

	@Transactional
	public void insertData(Project project, int userID) {
		project.setOwner(departmentDao.getUserNameById(userID));
		project.setCreated_by(departmentDao.getUserNameById(userID));
		project.setStatus("Active");
		projectDao.insertData(project);
		int projId = project.getProj_id();
		projectDao.updateRoleMapByUserId(userID);
		projectDao.updateProjIdInUserTable(projId, userID);

	}

	@Transactional
	public List<Integer> getUserinfo(User user) {
		// TODO Auto-generated method stub
		return projectDao.getUserinfo(user);

	}

	@Transactional
	public void deleteProject(Integer proj_id) {

		LOGGER.info("Inside ServiceImpl deleteDepartment" + proj_id);
		projectDao.deleteProject(proj_id);
		projectDao.updateUsersRoleInUsrs(proj_id);
	/*	List<Integer> userIds = projectDao.getUserByProjID(proj_id);
		for (Integer userID : userIds) {
			projectDao.updateUserProjIds(userID);
			projectDao.updateUserRole(userID);
		}
   */
	}

	public Project editProject(Integer proj_id) {

		Project project = new Project();
		project.setStatus("Active");
		return projectDao.editProject(proj_id);

		/*Project owner = projectDao.getProjectById(proj
					.getDpt_id());
			LOGGER.info("Inside ServiceImpl editproject"
					+ proj.getProj_id() + ":" + owner.getCreated_by());
			if (!owner.getCreated_by().trim().equals("")) {
				if (!owner.getCreated_by().equals(
						projectDao.getUserNameById(user.getUsr_id()))) {
					User usr = new User();
					usr = projectDao.getUserIdByProjId(proj.getProj_id());
					int userId = usr.getUsr_id();
					projectDao.updateUser(userId);
					projectDao.updateUserRole(userId);
					user.setProj_id(proj.getProj_id());
					projectDao.mapRoleToUser(user);
					projectDao.mapProjectToUser(user.getUsr_id(),
							user.getProj_id());
				}
			} else {
				user.setProj_id(proj.getProj_id());
				projectDao.mapRoleToUser(user);
				projectDao.mapProjectToUser(user.getUsr_id(),
						user.getProj_id());
			}
			proj.setOwner(projectDao.getUserNameById(user.getUsr_id()));
			LOGGER.info("Inside ServiceImpl editDepartment"
					+ proj.getProj_id() + ":" + proj.getProj_desc() + ":"
					+ proj.getProj_name());
			projectDao.updateProjectWithProject(proj);
		}
		*/
		
	}

	public void updateProject(Project project, User user) {

		project.setStatus("Active");
		int pid = project.getProj_id();
		LOGGER.info("Inside ServiceImpl editProject" + project.getProj_id());
		Project owner = projectDao.getProjectById(project.getProj_id());
		LOGGER.info("Inside ServiceImpl editProject display"
				+ project.getProj_id() + ":" + owner.getOwner());
		if (!owner.getOwner().equals(
				projectDao.getUserNameById(user.getUsr_id()))) {

			LOGGER.info("Inside ServiceImpl if user equals editDepartment");
			int userId = projectDao.getUserIdByProjId(pid);
			projectDao.updateUser(userId);
			projectDao.updateUserRole(userId);

			LOGGER.info("Inside ServiceImpl editProject project id number"
					+ user.getProj_id());
			user.setProj_id(pid);
			int userID = user.getUsr_id();
			projectDao.updateRoleMapByUserId(userID);
			projectDao.mapProjectToUser(user.getUsr_id(), user.getProj_id());
		}

		project.setOwner(projectDao.getUserNameById(user.getUsr_id()));
		LOGGER.info("Inside ServiceImpl editProject" + project.getProj_id()
				+ ":" + project.getProj_desc() + ":" + project.getProj_name());
		projectDao.updateProject(project);

	}

	public List<User> getUsersByCorporate(User user) {

		return projectDao.getUsersByCorporate(user);
	}

	public User getOwnerByName(String userName) {

		return projectDao.getOwnerByName(userName);
	}

	public List<VdcMaster> getVdcListFromVdcMaster() {

		return projectDao.getVdcListFromVdcMaster();
	}

	public VdcDeptQuotaMap vdcCgQuotaByVdcIDanddptID(int selectedVdcId,
			int dptId) {

		return projectDao.vdcCgQuotaByVdcIDanddptID(selectedVdcId, dptId);
	}

	public int getVdcdetails(int selectedVdcId, int projId) {

		VdcProjQuotaMap vdcProjQuotaMap = new VdcProjQuotaMap();

		vdcProjQuotaMap = projectDao.getVdcdetails(selectedVdcId, projId);

		if (vdcProjQuotaMap != null) {
			return 1;
		}

		return 0;
	}

	public void allocateVdcToProjectGroup(VdcProjQuotaMap vdcProjQuotaMapBean,
			int dptID) {

		int availableMem = 0;
		int availableVcpu = 0;
		int availableDiskStorage = 0;
		int totalMem = 0;
		int totalVcpu = 0;
		int totalDiskStorage = 0;
		int totalAllocatedProMem = 0;
		int totalAllocatedProVcpu = 0;
		int totalAllocatedProDiskStorage = 0;
		vdcProjQuotaMapBean.getDpt_id();
		projectDao.allocateVdcToProjectGroup(vdcProjQuotaMapBean);
		int selectedVdcId = vdcProjQuotaMapBean.getVdc_id();
		List<VdcProjQuotaMap> vdcProjQuotaMapBeanList = new ArrayList<VdcProjQuotaMap>();
		vdcProjQuotaMapBeanList = projectDao.getRowsFromVdcProjQuotaMapTable();

		if (!vdcProjQuotaMapBeanList.isEmpty()) {
			totalAllocatedProMem = projectDao
					.getSumOfTotalAllocatedMemoryToProj(selectedVdcId);
			totalAllocatedProVcpu = projectDao
					.getSumOftotalAllocatedProjVcpu(selectedVdcId);
			totalAllocatedProDiskStorage = projectDao
					.gettotalAllocatedProjDiskStorage(selectedVdcId);
		}

		VdcDeptQuotaMap vdcDeptQuotaMap = new VdcDeptQuotaMap();
		vdcDeptQuotaMap = projectDao.getVDCdetailsBydptID(dptID, selectedVdcId);
		totalMem = vdcDeptQuotaMap.getTotal_mem();
		totalVcpu = vdcDeptQuotaMap.getTotal_vcpu();
		totalDiskStorage = vdcDeptQuotaMap.getTotal_disk_storage();

		availableMem = totalMem - totalAllocatedProMem;
		availableVcpu = totalVcpu - totalAllocatedProVcpu;
		availableDiskStorage = totalDiskStorage - totalAllocatedProDiskStorage;

		vdcDeptQuotaMap.setFree_mem(availableMem);
		vdcDeptQuotaMap.setFree_vcpu(availableVcpu);

		vdcDeptQuotaMap.setFree_disk_storage(availableDiskStorage);

		int cgAvailableMem = vdcDeptQuotaMap.getFree_mem();
		int cgAvailableVcpu = vdcDeptQuotaMap.getFree_vcpu();
		int cgAvailableDiskStorage = vdcDeptQuotaMap.getFree_disk_storage();

		projectDao.updateFreeSpacesinDptquota(cgAvailableMem, cgAvailableVcpu,
				cgAvailableDiskStorage, selectedVdcId, dptID);

	}

	@Transactional
	public List<VdcProjQuotaMap> getRowsFromProjQuotaMapTable(int dptID) {
		List<VdcProjQuotaMap> vdcProjQuotaMapBeanList = new ArrayList<VdcProjQuotaMap>();
		vdcProjQuotaMapBeanList = projectDao
				.getRowsFromProjQuotaMapTable(dptID);
		for (VdcProjQuotaMap vdcProjQuotaMapBean : vdcProjQuotaMapBeanList) {
			int projId = vdcProjQuotaMapBean.getProj_id();
			int vdcID = vdcProjQuotaMapBean.getVdc_id();
			VdcMaster vdcmaster = projectDao.getVdcName(vdcID);
			Project projectgrp = projectDao.getProjGrpByID(projId);
			vdcProjQuotaMapBean.setVdcmasterbean(vdcmaster);
			vdcProjQuotaMapBean.setProjectbean(projectgrp);

		}
		return vdcProjQuotaMapBeanList;
	}

	@Transactional
	public Project getProjGrpByID(int proj_id) {
		Project projgrp = new Project();
		projgrp = projectDao.getProjGrpByID(proj_id);
		return projgrp;
	}
	@Transactional
	public int getfree_memory(int free_mem, int dptID, int selectedVdcId) {

		int freeMem = projectDao.getfree_memory(dptID, selectedVdcId);

		if (freeMem >= free_mem)

		{
			return 1;
		}
		return 0;
	}
	@Transactional
	public int getfree_vcpuprojects(int free_vcpu, int dptID, int selectedVdcId) {

		int freeVcpu = projectDao.getfree_vcpuDB(dptID, selectedVdcId);

		if (freeVcpu >= free_vcpu)

		{
			return 1;
		}
		return 0;
	}

	public int getfree_diskstorage(int free_disk_storage, int dptID,
			int selectedVdcId) {

		int freeDisk = projectDao.getfree_diskDB(dptID, selectedVdcId);

		if (freeDisk >= free_disk_storage)

		{
			return 1;
		}
		return 0;
	}

	public Project getProjectName(Integer projId) {
		return projectDao.getProjectName(projId);
	}

	@Transactional
	public void updateFreememoryInDptquota(VdcProjQuotaMap vdcProjQuotaMap,
			int proj_id) {

		int vdcId = vdcProjQuotaMap.getVdc_id();
		int projFreeDisk = vdcProjQuotaMap.getFree_disk_storage();
		int projFreeMem = vdcProjQuotaMap.getFree_mem();
		int projFreeVcpu = vdcProjQuotaMap.getFree_vcpu();
		VdcDeptQuotaMap VdcDeptQuotaMap = new VdcDeptQuotaMap();
		VdcDeptQuotaMap = projectDao.getFreeVdcDeatilsInDptQuota(proj_id, vdcId);
		int dptFreeDisk = VdcDeptQuotaMap.getFree_disk_storage();
		int dptFreeMem = VdcDeptQuotaMap.getFree_mem();
		int dptFreeVcpu = VdcDeptQuotaMap.getFree_vcpu();
		int newdptFreeDisk = dptFreeDisk + projFreeDisk;
		int newdptFreeMem = dptFreeMem + projFreeMem;
		int newdptFreeVcpu = dptFreeVcpu + projFreeVcpu;
		projectDao.updateFreememoryInDpt(newdptFreeMem, newdptFreeVcpu,
				newdptFreeDisk, proj_id, vdcId);

	}

	@Transactional
	public void deleteProjectQuota(Integer proj_id, int vdc_id) {
		projectDao.deleteProjectQuota(proj_id, vdc_id);

	}

	@Transactional
	public List<Project> getProjectsList(int proj_id) {

		List<Project> projects = new ArrayList<Project>();
		LOGGER.info("Inside ServiceImpl getProjectsList");
		projects = projectDao.getProjectsList(proj_id);
		for (Project proj : projects)
			LOGGER.info("deptId" + proj.getProj_id());
		return projects;
	}

	public VdcProjQuotaMap getVdcdetailsByProjID(int proj_id, int vdc_id) {
		return projectDao.getVdcdetailsByProjID(proj_id, vdc_id);
	}

	public VdcProjQuotaMap getAllocatedDetails(Integer projid, Integer VdcId) {
		return projectDao.getAllocatedDetails(projid, VdcId);
	}

	public VdcProjQuotaMap vdcProjQuotaByVdcIDanddptID(int selectedVdcId,
			int dptId) {
		return projectDao.vdcProjQuotaByVdcIDanddptID(selectedVdcId, dptId);
	}

	@Transactional
	public void editVdcToProject(VdcProjQuotaMap vdcProjQuotaMapBean, int dptID) {

		{
            int projId = vdcProjQuotaMapBean.getProj_id();
			int vdcId = vdcProjQuotaMapBean.getVdc_id();
			int editFreeMem = vdcProjQuotaMapBean.getTotal_mem();
			int editFreeDisk = vdcProjQuotaMapBean.getTotal_disk_storage();
			int editFreeVCPU = vdcProjQuotaMapBean.getTotal_vcpu();
			VdcProjQuotaMap vdcproj = new VdcProjQuotaMap();
			VdcUserQuotaMap userquota = new VdcUserQuotaMap();
			vdcproj = projectDao.getVdcdetailsByDptID(projId, vdcId);
			int totalFreemem = vdcproj.getTotal_mem();
			userquota.setTotal_mem(totalFreemem);
			int totalFreedisk = vdcproj.getTotal_disk_storage();
			userquota.setTotal_disk_storage(totalFreedisk);
			int totalFreeVcpu = vdcproj.getTotal_vcpu();
			userquota.setTotal_vcpu(totalFreeVcpu);
			int allocatedFreemem = vdcproj.getFree_mem();
			userquota.setFree_mem(allocatedFreemem);
			int alocatedFreedisk = vdcproj.getFree_disk_storage();
			userquota.setFree_disk_storage(alocatedFreedisk);
			int allocatedFreeVcpu = vdcproj.getFree_vcpu();
			userquota.setFree_vcpu(allocatedFreeVcpu);
			vdcProjQuotaMapBean.setFree_mem(allocatedFreemem);
			vdcProjQuotaMapBean.setFree_disk_storage(alocatedFreedisk);
			vdcProjQuotaMapBean.setFree_vcpu(allocatedFreeVcpu);
			VdcDeptQuotaMap vdcDpt = new VdcDeptQuotaMap();
			vdcDpt = projectDao.getFreeMemoryinDPTQuota(vdcId, dptID);
			int dptFreeMem = vdcDpt.getFree_mem();
			int dptFreeDisk = vdcDpt.getFree_disk_storage();
			int dptFreeVCPU = vdcDpt.getFree_vcpu();
			if (editFreeMem > totalFreemem) {

				int extraTotalFreemem = editFreeMem - totalFreemem;
				allocatedFreemem = allocatedFreemem + extraTotalFreemem;
				dptFreeMem = dptFreeMem - extraTotalFreemem;
				vdcProjQuotaMapBean.setFree_mem(allocatedFreemem);
				userquota.setFree_mem(allocatedFreemem);
			}

			if (editFreeDisk > totalFreedisk) {
				int extraTotalFreeDisk = editFreeDisk - totalFreedisk;
				alocatedFreedisk = alocatedFreedisk + extraTotalFreeDisk;
				dptFreeDisk = dptFreeDisk - extraTotalFreeDisk;
				vdcProjQuotaMapBean.setFree_disk_storage(alocatedFreedisk);
				userquota.setFree_disk_storage(alocatedFreedisk);
			}
			if (editFreeVCPU > totalFreeVcpu) {
				int extraTotalFreeVCPU = editFreeVCPU - totalFreeVcpu;
				allocatedFreeVcpu = allocatedFreeVcpu + extraTotalFreeVCPU;
				vdcProjQuotaMapBean.setFree_vcpu(allocatedFreeVcpu);
				dptFreeVCPU = dptFreeVCPU - extraTotalFreeVCPU;
				userquota.setFree_vcpu(allocatedFreeVcpu);
			}
			if (editFreeMem < totalFreemem) {
				int lessTotalFreeMem = totalFreemem - editFreeMem;
				allocatedFreemem = allocatedFreemem - lessTotalFreeMem;
				vdcProjQuotaMapBean.setFree_mem(allocatedFreemem);
				dptFreeMem = dptFreeMem + lessTotalFreeMem;
				userquota.setFree_mem(allocatedFreemem);;
			}
			if (editFreeDisk < totalFreedisk) {
				int lessTotalFreedisk = totalFreedisk - editFreeDisk;
				alocatedFreedisk = alocatedFreedisk - lessTotalFreedisk;
				vdcProjQuotaMapBean.setFree_disk_storage(alocatedFreedisk);
				dptFreeDisk = dptFreeDisk + lessTotalFreedisk;
				userquota.setFree_disk_storage(alocatedFreedisk);
			}
			if (editFreeVCPU < totalFreeVcpu) {
				int lessTotalFreeVCPU = totalFreeVcpu - editFreeVCPU;
				dptFreeVCPU = dptFreeVCPU + lessTotalFreeVCPU;
				allocatedFreeVcpu = allocatedFreeVcpu - lessTotalFreeVCPU;
				vdcProjQuotaMapBean.setFree_vcpu(allocatedFreeVcpu);
				userquota.setFree_vcpu(allocatedFreeVcpu);
			}
			
			Project proj = projectDao.getProjectById(projId);
			String owner = proj.getOwner();
			User userDetails = projectDao.getOwnerByName(owner);
			int id = userDetails.getUsr_id();
			userquota.setUsr_id(id);
			userquota.setVdc_id(vdcId);
			
			projectDao.updateNewAvalilibtyInDpt(dptFreeMem, dptFreeVCPU,
					dptFreeDisk, dptID, vdcId);
			projectDao.editVdcProject(vdcProjQuotaMapBean);
			
			

		}

	}

	public void updateInUserQuotaTableGroup(
			VdcProjQuotaMap vdcProjQuotaMapBean, int projID, HttpSession session) {
		LOGGER.info("Inside ServiceImpl updateInUserQuotaTableGroup" + projID);
		LOGGER.info("Inside ServiceImpl VdcID "
				+ vdcProjQuotaMapBean.getVdc_id());
		User user = (User) session.getAttribute("userValue");
		Project proj = projectDao.getProjectById(projID);
		String owner = proj.getOwner();
		User userDetails = projectDao.getOwnerByName(owner);
		int id = userDetails.getUsr_id();
		VdcUserQuotaMap userQuota = new VdcUserQuotaMap();
		userQuota.setUsr_id(id);
		LOGGER.info("Inside ServiceImpl updateInUserQuotaTableGroup USERID:" + id);
		userQuota.setVdc_id(vdcProjQuotaMapBean.getVdc_id());
		userQuota.setTotal_mem(vdcProjQuotaMapBean.getTotal_mem());
		userQuota.setTotal_disk_storage(vdcProjQuotaMapBean
				.getTotal_disk_storage());
		userQuota.setTotal_vcpu(vdcProjQuotaMapBean.getTotal_vcpu());
		userQuota.setFree_disk_storage(vdcProjQuotaMapBean
				.getFree_disk_storage());
		userQuota.setFree_mem(vdcProjQuotaMapBean.getFree_mem());
		userQuota.setFree_vcpu(vdcProjQuotaMapBean.getFree_vcpu());
		userQuota.setCreated_by(user.getUsr_name());
		projectDao.updateInUserQuotaTableGroup(userQuota);
		
	}

	public List<VdcDeptQuotaMap> getVdcFromVdcMaster(int projId, int dptID) {

		List<VdcDeptQuotaMap> vdcList = new ArrayList<VdcDeptQuotaMap>();
		List<Integer> vdcIdList = new ArrayList<Integer>();
	
		vdcIdList =projectDao.getVdcIdListsByProjIDFromProjQuotaTable(projId);
		vdcList = projectDao.getVdcListsFromDptQuota(dptID);
		
		Iterator<Integer> itr1 = vdcIdList.iterator();
		while (itr1.hasNext()){
			int j = itr1.next();
			Iterator<VdcDeptQuotaMap> itr = vdcList.iterator();
				for(int k = 0;k<vdcList.size();k++){
					while(itr.hasNext()){
						VdcDeptQuotaMap vdcdptBean = new VdcDeptQuotaMap();
						vdcdptBean = itr.next();
						if(j == vdcdptBean.getVdc_id())
						{
							itr.remove();
						}
					}
				}
			}
		return vdcList;
	}

	public String getVdcNamesByID(int vdc_id) {
		return projectDao.getVdcNamesByID(vdc_id);
	}

	public Project editProject(Project proj, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteQuotaInUserQuota(Integer proj_id, Integer vdc_id,
			HttpSession session) {
		LOGGER.info("Inside ServiceImpl deleteQuotaInUserQuota" + proj_id);
		
		//User user = (User) session.getAttribute("userValue");
		Project proj = projectDao.getProjectById(proj_id);
		String owner = proj.getOwner();
		User userDetails = projectDao.getOwnerByName(owner);
		int id = userDetails.getUsr_id();
		LOGGER.info("Inside ServiceImpl deleteQuotaInUserQuota" + id);
		projectDao.deleteQuotaInUsers(id,vdc_id);
	}

	public List<User> getUserByProjID(Integer projid) {
		// TODO Auto-generated method stub
		return projectDao.getUserByProjID(projid);
	}

	public int getProj_name(String proj_name) {
		// TODO Auto-generated method stub
		 
		 Project user =  projectDao.getProj_name1(proj_name);
			if(user != null)
				
			{
				return 1;
			}
			return 0;
		}
	}

