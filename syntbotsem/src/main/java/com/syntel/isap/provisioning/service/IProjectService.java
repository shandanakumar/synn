package com.syntel.isap.provisioning.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
/**
 * @author SK5004144
 *
 */

public interface IProjectService {
	
	void insertData(Project project, int userId);
	List<Project> getprojectLists(int dpid);
	List<Integer> getUserinfo(User user);
	void deleteProject(Integer proj_id);
	Project editProject(Project proj,User user);
	void updateProject(Project project,User user);
	
	List<User> getUsersByCorporate( User user);
	User getOwnerByName(String userName);
	List<VdcMaster> getVdcListFromVdcMaster();
	VdcDeptQuotaMap vdcCgQuotaByVdcIDanddptID(int selectedVdcId,int dptId);
	int getVdcdetails(int selectedVdcId, int projId);
	void allocateVdcToProjectGroup(VdcProjQuotaMap vdcProjQuotaMapBean, int dptID);
	
    List<VdcProjQuotaMap> getRowsFromProjQuotaMapTable(int dptID);
    Project getProjGrpByID(int proj_id);
	int getfree_diskstorage(int free_disk_storage, int dptID,int selectedVdcId);
	int getfree_vcpuprojects(int free_vcpu, int dptID,int selectedVdcId);
	int getfree_memory(int free_mem, int dptID,int selectedVdcId);
	Project getProjectName(Integer projId);
	
	void updateFreememoryInDptquota(VdcProjQuotaMap vdcProjQuotaMap, int proj_id);
	void deleteProjectQuota(Integer proj_id, int vdc_id);
	List<Project> getProjectsList(int proj_id);
    VdcProjQuotaMap getVdcdetailsByProjID(int proj_id, int vdc_id);
    VdcProjQuotaMap getAllocatedDetails(Integer projid, Integer VdcId);
	

    VdcProjQuotaMap vdcProjQuotaByVdcIDanddptID(int selectedVdcId, int dptId);
	void editVdcToProject(VdcProjQuotaMap vdcProjQuotaMapBean, int dptID);
	void updateInUserQuotaTableGroup(VdcProjQuotaMap vdcProjQuotaMapBean,
			int projID, HttpSession session);
	List<VdcDeptQuotaMap> getVdcFromVdcMaster(int projId, int dptID);
	String getVdcNamesByID(int vdc_id);
	Project editProject(Integer proj_id);
	void deleteQuotaInUserQuota(Integer proj_id, Integer vdc_id,
			HttpSession session);
	
	List<User> getUserByProjID(Integer projid);
	
	int getProj_name(String proj_name);
	
	


}
