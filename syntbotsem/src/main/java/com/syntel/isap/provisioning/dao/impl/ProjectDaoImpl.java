package com.syntel.isap.provisioning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;
import com.syntel.isap.provisioning.dao.IProjectDao;
import com.syntel.isap.provisioning.mapper.ProjectMapper;

@Service("projectDao")
public class ProjectDaoImpl implements IProjectDao {

	@Autowired
	private ProjectMapper projectMapper;

	public List<Project> getprojectLists(int dptid) {

		return projectMapper.getprojectLists(dptid);
	}

	public void insertData(Project project) {

		projectMapper.insertData(project);

	}

	public List<Integer> getUserinfo(User user) {

		return projectMapper.getUserinfo(user);
	}

	public void deleteProject(Integer proj_id) {

		projectMapper.deleteProject(proj_id);
	}

	public Project editProject(Integer proj_id) {

		return projectMapper.editProject(proj_id);
	}

	public void updateProject(Project project) {
		projectMapper.updateProject(project);
	}

	public List<User> getUsersByCorporate(User user) {

		return projectMapper.getUsersByCorporate(user);
	}

	public void updateRoleMapByUserId(int userID) {

		projectMapper.updateRoleMapByUserId(userID);
	}

	public void updateProjIdInUserTable(int projId, int userID) {
		projectMapper.updateProjIdInUserTable(projId, userID);

	}

	/*public List<Integer> getUserByProjID(Integer proj_id) {
		return projectMapper.getUserByProjID(proj_id);
	}

	public void updateUserProjIds(Integer userID) {
		projectMapper.updateUserProjIds(userID);

	}*/

	public void updateUserRole(Integer userID) {
		projectMapper.updateUserRole(userID);

	}

	public Project getProjectById(int proj_id) {
		return projectMapper.getProjectById(proj_id);
	}

	public String getUserNameById(int usr_id) {
		return projectMapper.getUserNameById(usr_id);
	}

	public int getUserIdByProjId(int proj_id) {
		return projectMapper.getUserIdByProjId(proj_id);
	}

	public void updateUser(int userId) {

		projectMapper.updateUser(userId);
	}

	public void mapRoleToUser(User user) {

		projectMapper.mapRoleToUser(user);
	}

	public void mapProjectToUser(int usr_id, int proj_id) {

		projectMapper.mapProjectToUser(usr_id, proj_id);
	}

	public User getOwnerByName(String userName) {

		return projectMapper.getOwnerByName(userName);
	}

	public List<VdcMaster> getVdcListFromVdcMaster() {
		return projectMapper.getVdcListFromVdcMaster();
	}

	public VdcDeptQuotaMap vdcCgQuotaByVdcIDanddptID(int selectedVdcId,
			int dptId) {

		return projectMapper.vdcCgQuotaByVdcIDanddptID(selectedVdcId, dptId);
	}

	public VdcProjQuotaMap getVdcdetails(int selectedVdcId, int projId) {
		return projectMapper.getVdcdetails(selectedVdcId, projId);
	}

	public void allocateVdcToProjectGroup(VdcProjQuotaMap vdcProjQuotaMapBean) {

		projectMapper.allocateVdcToProjectGroup(vdcProjQuotaMapBean);
	}

	public List<VdcProjQuotaMap> getRowsFromVdcProjQuotaMapTable() {
		return projectMapper.getRowsFromVdcProjQuotaMapTable();
	}

	public int getSumOftotalAllocatedProjVcpu(int selectedVdcId) {
		return projectMapper.getSumOftotalAllocatedProjVcpu(selectedVdcId);
	}

	public int gettotalAllocatedProjDiskStorage(int selectedVdcId) {
		return projectMapper.gettotalAllocatedProjDiskStorage(selectedVdcId);
	}

	public int getSumOfTotalAllocatedMemoryToProj(int selectedVdcId) {
		return projectMapper.getSumOfTotalAllocatedMemoryToProj(selectedVdcId);
	}

	public VdcDeptQuotaMap getVDCdetailsBydptID(int dptID, int selectedVdcId) {
		return projectMapper.getVDCdetailsBydptID(dptID, selectedVdcId);
	}

	public void updateFreeSpacesinDptquota(int cgAvailableMem,
			int cgAvailableVcpu, int cgAvailableDiskStorage, int selectedVdcId,
			int dptID) {

		projectMapper.updateFreeSpacesinDptquota(cgAvailableMem,
				cgAvailableVcpu, cgAvailableDiskStorage, selectedVdcId, dptID);

	}

	public List<VdcProjQuotaMap> getRowsFromProjQuotaMapTable(int dptID) {
		List<VdcProjQuotaMap> vdcProjQuotaMapBeanList = new ArrayList<VdcProjQuotaMap>();
		vdcProjQuotaMapBeanList = projectMapper
				.getRowsFromProjQuotaMapTable(dptID);
		return vdcProjQuotaMapBeanList;
	}

	public Project getProjGrpByID(int proj_id) {
		{
			Project projgrp = new Project();
			projgrp = projectMapper.getProjGrpByID(proj_id);
			return projgrp;
		}
	}

	public int getfree_memory(int dptID, int selectedVdcId) {

		return projectMapper.getfree_memory(dptID, selectedVdcId);
	}

	public int getfree_vcpuDB(int dptID, int selectedVdcId) {

		return projectMapper.getfree_vcpuDB(dptID, selectedVdcId);
	}

	public int getfree_diskDB(int dptID, int selectedVdcId) {

		return projectMapper.getfree_diskDB(dptID, selectedVdcId);

	}

	public Project getProjectName(Integer projId) {
		return projectMapper.getProjectName(projId);
	}

	public VdcProjQuotaMap getVdcdetailsByProjID(int proj_id, int vdc_id) {
		return projectMapper.getVdcdetailsByProjID(proj_id, vdc_id);
	}

	public VdcDeptQuotaMap getFreeVdcDeatilsInDptQuota(int projId, int vdcId) {
		return projectMapper.getFreeVdcDeatilsInDptQuota(projId, vdcId);

	}

	public void updateFreememoryIndpt(int newdptFreeMem, int newdptFreeDisk,
			int newdptFreeCvpu, int dptID, int VdcId) {
		projectMapper.updateFreememoryInDpt(newdptFreeMem, newdptFreeCvpu,
				newdptFreeDisk, dptID, VdcId);

	}

	public void deleteProjectQuota(int proj_id, int dptID) {
		projectMapper.deleteProjectQuota(proj_id, dptID);

	}

	public List<Project> getProjectsList(int proj_id) {
		List<Project> projects = new ArrayList<Project>();
		projects = projectMapper.getProjectsList(proj_id);
		return projects;
	}

	public void updateFreememoryInDpt(int newdptFreeMem, int newdptFreeVcpu,
			int newdptFreeDisk, int dptID, int vdcId) {
		projectMapper.updateFreememoryInDpt(newdptFreeMem, newdptFreeVcpu,
				newdptFreeDisk, dptID, vdcId);

	}

	public void deleteProjectQuota(Integer proj_id, int vdc_id) {
		projectMapper.deleteProjectQuota(proj_id, vdc_id);

	}

	public void updateFreememoryInDptquota(VdcProjQuotaMap vdcProjQuotaMap,
			int dpt_id) {

	}

	public VdcProjQuotaMap getAllocatedDetails(Integer projid, Integer VdcId) {
		return projectMapper.getAllocatedDetails(projid, VdcId);

	}

	public VdcProjQuotaMap vdcProjQuotaByVdcIDanddptID(int selectedVdcId,
			int projId) {
		return projectMapper.vdcProjQuotaByVdcIDanddptID(selectedVdcId, projId);
	}

	public VdcDeptQuotaMap getFreeMemoryinDPTQuota(int selectedVdcId, int dptId) {
		return projectMapper.getFreeMemoryinDPTQuota(selectedVdcId, dptId);
	}

	public VdcProjQuotaMap getVdcdetailsByDptID(int dptID, int vdc_id) {
		return projectMapper.getVdcdetailsByDptID(dptID, vdc_id);
	}

	public void editVdcProject(VdcProjQuotaMap vdcProjQuotaMapBean) {
		projectMapper.editVdcProject(vdcProjQuotaMapBean);

	}

	public void updateNewAvalilibtyInDpt(int dptFreeMem, int dptFreeVCPU,
			int dptFreeDisk, int dptID, int vdcId) {
		projectMapper.updateNewAvalilibtyInDpt(dptFreeMem, dptFreeVCPU,
				dptFreeDisk, dptID, vdcId);

	}

	public VdcMaster getVdcName(Integer selectedVdc) {
		return projectMapper.getVdcName(selectedVdc);
	}

	public void updateInUserQuotaTableGroup(VdcUserQuotaMap userQuota) {
		projectMapper.updateInUserQuotaTableGroup(userQuota);
	}

	public List<Integer> getVdcIdListsByProjIDFromProjQuotaTable(int projId) {
		return projectMapper.getVdcIdListsByProjIDFromProjQuotaTable(projId);
		
	}

	public List<VdcDeptQuotaMap> getVdcListsFromDptQuota(int dptID) {
		return projectMapper.getVdcListsFromDptQuota(dptID);
	}

	public String getVdcNamesByID(int vdc_id) {
		return projectMapper.getVdcNamesByID(vdc_id);
	}

	public void deleteQuotaInUsers(int id, Integer vdc_id) {
		projectMapper.deleteQuotaInUsers(id,vdc_id);
	}

	public List<User> getUserByProjID(Integer projid) {
		// TODO Auto-generated method stub
		return projectMapper.getUserByProjID(projid);
	}

	public void updateUsersRoleInUsrs(Integer proj_id) {
		// TODO Auto-generated method stub
		projectMapper.updateUsersRoleInUsrs(proj_id);
	}

	public Project getProj_name1(String proj_name) {
		// TODO Auto-generated method stub
		return projectMapper.getProj_name(proj_name);
	}

	

}
