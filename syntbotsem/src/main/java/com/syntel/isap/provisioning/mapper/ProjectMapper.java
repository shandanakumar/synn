package com.syntel.isap.provisioning.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;





import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;


public interface ProjectMapper {

	
	final String GET_USER_BY_CORPT_OWNER_ID = "SELECT users.usr_id,users.usr_name FROM isap_cmn_users users,isap_cmn_user_role_map maps WHERE users.usr_id=maps.usr_id AND users.dpt_id=#{dpt_id} AND users.proj_id = 1 AND maps.role_id=6";

	final String GET_USERID_BY_PROJ_ID_ROLE = "SELECT users.usr_id FROM isap_cmn_users users,isap_cmn_user_role_map maps WHERE users.usr_id=maps.usr_id AND users.proj_id=#{proj_id} AND maps.role_id=4";

	@Select("SELECT * FROM isap_cmn_projects where dpt_id = #{dpt_id} AND delete_flag=0 AND proj_id!=1")
	List<Project> getprojectLists(int dptid);

	@Insert("INSERT INTO isap_cmn_projects (proj_name,proj_desc,status, created_by,dpt_id,cg_id,owner) VALUES (#{proj_name},#{proj_desc},#{status},#{created_by},#{dpt_id},#{cg_id},#{owner})")
	@Options(useGeneratedKeys = true, keyProperty = "proj_id")
	void insertData(Project project);

	@Select("select dpt_id,cg_id from isap_cmn_users where usr_name=#{usr_name}")
	List<Integer> getUserinfo(User user);

	@Update("UPDATE isap_cmn_projects SET delete_flag=1 where proj_id = #{proj_id}")
	void deleteProject(Integer proj_id);

	@Update("update isap_cmn_projects set proj_name =#{proj_name},proj_desc=#{proj_desc},status =#{status} ,owner =#{owner} where proj_id= #{proj_id}")
	void updateProject(Project project);

	@Select("SELECT * FROM isap_cmn_projects where proj_id =#{proj_id}")
	Project editProject(Integer proj_id);

	@Select(GET_USER_BY_CORPT_OWNER_ID)
	List<User> getUsersByCorporate(User user);

	@Update("UPDATE isap_cmn_user_role_map SET role_id =4 where usr_id=#{userID}")
	void updateRoleMapByUserId(@Param("userID") int userID);

	@Update("UPDATE isap_cmn_users SET proj_id = #{proj_id} where usr_id = #{usr_id}")
	void updateProjIdInUserTable(@Param("proj_id") int proj_id,@Param("usr_id") int usr_id);

	/*@Update("UPDATE isap_cmn_users SET proj_id=1 where usr_id = #{userID}")
	void updateUserProjIds(@Param("userID") Integer userID);*/

	@Update("UPDATE isap_cmn_user_role_map SET role_id=6  where usr_id = #{userID}")
	void updateUserRole(@Param("userID") Integer userID);

    @Select("SELECT e.usr_id,e.usr_name FROM isap_cmn_users e JOIN isap_cmn_user_role_map  m  ON e.usr_id=m.usr_id WHERE proj_id =#{projid}  AND delete_flag=0 AND role_id!=4;")
	List<User> getUserByProjID(Integer projid);

	@Select("SELECT * FROM isap_cmn_projects where proj_id =#{proj_id}")
	Project getProjectById(int proj_id);

	@Select("SELECT usr_name FROM isap_cmn_users WHERE usr_id = #{usr_id}")
	String getUserNameById(int usr_id);

	@Select(GET_USERID_BY_PROJ_ID_ROLE)
	int getUserIdByProjId(int proj_id);

	@Update("UPDATE isap_cmn_user_role_map SET role_id=4 where usr_id = #{usr_id}")
	void mapRoleToUser(User user);

	@Update("UPDATE isap_cmn_users SET proj_id = #{proj_id} where usr_id = #{usr_id}")
	void mapProjectToUser(@Param("usr_id") int usr_id,@Param("proj_id") int proj_id);

	@Select("SELECT * FROM isap_cmn_users WHERE usr_name = #{userName}")
	User getOwnerByName(String userName);

	@Update("UPDATE isap_cmn_users SET proj_id =1 where usr_id = #{userId}")
	void updateUser(int userId);

	@Select("SELECT * FROM isap_env_vdc_master")
	List<VdcMaster> getVdcListFromVdcMaster();

	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map WHERE vdc_id = #{selectedVdcId} AND dpt_id = #{dptId} ")
	VdcDeptQuotaMap vdcCgQuotaByVdcIDanddptID(@Param("selectedVdcId") int selectedVdcId, @Param("dptId") int dptId);

	@Select("SELECT * FROM isap_env_vdc_proj_quota_map WHERE vdc_id = #{selectedVdcId} AND proj_id = #{projId}")
	VdcProjQuotaMap getVdcdetails(@Param("selectedVdcId") int selectedVdcId,@Param("projId") int projId);

	@Insert("INSERT INTO isap_env_vdc_proj_quota_map(proj_id,vdc_id,total_mem,total_vcpu,total_disk_storage,free_mem,free_vcpu,free_disk_storage,created_by,dpt_id) VALUES(#{proj_id},#{vdc_id},#{total_mem},#{total_vcpu},#{total_disk_storage},#{free_mem},#{free_vcpu},#{free_disk_storage},#{created_by},#{dpt_id})")
	void allocateVdcToProjectGroup(VdcProjQuotaMap vdcProjQuotaMapBean);

	@Select("SELECT * FROM isap_env_vdc_proj_quota_map")
	List<VdcProjQuotaMap> getRowsFromVdcProjQuotaMapTable();

	@Select("SELECT sum(total_vcpu) FROM isap_env_vdc_proj_quota_map WHERE vdc_id = #{selectedVdcId}")
	int getSumOftotalAllocatedProjVcpu(@Param("selectedVdcId") Integer selectedVdcId);

	@Select("SELECT sum(total_disk_storage) FROM isap_env_vdc_proj_quota_map WHERE vdc_id = #{selectedVdcId}")
	int gettotalAllocatedProjDiskStorage(@Param("selectedVdcId") Integer selectedVdcId);

	@Select("SELECT sum(total_mem) FROM isap_env_vdc_proj_quota_map WHERE vdc_id = #{selectedVdcId}")
	int getSumOfTotalAllocatedMemoryToProj(@Param("selectedVdcId") Integer selectedVdcId);

	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map WHERE dpt_id = #{dptID} AND vdc_id = #{selectedVdcId}")
	VdcDeptQuotaMap getVDCdetailsBydptID(@Param("dptID") Integer dptID,
			@Param("selectedVdcId") Integer selectedVdcId);

	@Update("UPDATE isap_env_vdc_dpt_quota_map SET free_mem=#{dptAvailableMem},free_vcpu =#{dptAvailableVcpu},free_disk_storage=#{dptAvailableDiskStorage} where vdc_id = #{selectedVdcId} AND dpt_id = #{dptID}")
	void updateFreeSpacesinDptquota(
			@Param("dptAvailableMem") Integer dptAvailableMem,
			@Param("dptAvailableVcpu") Integer dptAvailableVcpu,
			@Param("dptAvailableDiskStorage") Integer dptAvailableDiskStorage,
			@Param("selectedVdcId") Integer selectedVdcId,
			@Param("dptID") Integer dptID);

	@Select("SELECT * FROM isap_env_vdc_proj_quota_map WHERE dpt_id = #{dptID} ")
	List<VdcProjQuotaMap> getRowsFromProjQuotaMapTable(
			@Param("dptID") Integer dptID);

	@Select("Select * FROM isap_cmn_projects WHERE proj_id = #{projId}")
	Project getProjGrpByID(int proj_id);

	@Select("SELECT free_mem FROM isap_env_vdc_dpt_quota_map WHERE dpt_id = #{dptID} AND vdc_id=#{selectedVdcId}")
	int getfree_memory(@Param("dptID") Integer dptID,
			@Param("selectedVdcId") Integer selectedVdcId);

	@Select("SELECT free_vcpu FROM isap_env_vdc_dpt_quota_map WHERE dpt_id = #{dptID} AND vdc_id=#{selectedVdcId}")
	int getfree_vcpuDB(@Param("dptID") Integer dptID,
			@Param("selectedVdcId") Integer selectedVdcId);

	@Select("SELECT free_disk_storage FROM isap_env_vdc_dpt_quota_map WHERE dpt_id = #{dptID} AND vdc_id=#{selectedVdcId}")
	int getfree_diskDB(@Param("dptID") Integer dptID,
			@Param("selectedVdcId") Integer selectedVdcId);

	@Select("Select * FROM isap_cmn_projects WHERE proj_id = #{projId}")
	Project getProjectName(@Param("projId") Integer projId);

	

	@Update("UPDATE isap_env_vdc_dpt_quota_map SET free_mem=#{newdptFreeMem},free_vcpu =#{newdptFreeVcpu},free_disk_storage=#{newdptFreeDisk} where dpt_id = #{dptID} AND vdc_id = #{vdcId}")
	void updateFreememoryInDpt(@Param("newdptFreeMem") Integer newdptFreeMem,
			@Param("newdptFreeVcpu") Integer newdptFreeVcpu,
			@Param("newdptFreeDisk") Integer newdptFreeDisk,
			@Param("dptID") Integer dptID, @Param("vdcId") Integer vdcId);

	@Delete("Delete FROM isap_env_vdc_proj_quota_map WHERE proj_id = #{proj_id} AND vdc_id=#{vdc_id}")
	void deleteProjectQuota(@Param("proj_id") Integer proj_id,
			@Param("vdc_id") Integer vdc_id);

	@Select("SELECT * FROM isap_cmn_projects where proj_id=#{projId} AND delete_flag=0 AND proj_id!=1  order by proj_id desc")
	List<Project> getProjectsList(int proj_id);

	@Select("SELECT * FROM isap_env_vdc_proj_quota_map WHERE proj_id=#{proj_id} AND  vdc_id =#{vdc_id}")
	VdcDeptQuotaMap getFreeVdcDeatilsInDptQuota(
			@Param("proj_id") Integer proj_id, @Param("vdc_id") Integer vdc_id);

	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map WHERE vdc_id = #{selectedVdcId} AND dpt_id=#{dptID}")
	void updateFreememoryInDptquota(
			@Param("selectedVdcId") VdcProjQuotaMap vdcProjQuotaMap,
			@Param("dptID") Integer dptID);

	@Select("SELECT * FROM isap_env_vdc_proj_quota_map WHERE proj_id =#{projid} AND vdc_id =#{VdcId}")
	VdcProjQuotaMap getAllocatedDetails(@Param("projid") Integer projid,
			@Param("VdcId") Integer VdcId);

	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map WHERE vdc_id = #{selectedVdcId} AND dpt_id = #{dptId} ")
	VdcProjQuotaMap vdcProjQuotaByVdcIDanddptID(
			@Param("selectedVdcId") Integer selectedVdcId,
			@Param("dpt_id") Integer dptId);

	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map WHERE vdc_id = #{selectedVdcId} AND dpt_id=#{dptId}")
	VdcDeptQuotaMap getFreeMemoryinDPTQuota(
			@Param("selectedVdcId") Integer selectedVdcId,
			@Param("dptId") Integer dptId);

	@Select("Select * FROM isap_env_vdc_proj_quota_map WHERE proj_id = #{proj_id} AND vdc_id=#{vdc_id}")
	VdcProjQuotaMap getVdcdetailsByProjID(@Param("proj_id") Integer proj_id,
			@Param("vdc_id") Integer vdc_id);
	@Select("SELECT * FROM isap_env_vdc_proj_quota_map WHERE proj_id = #{projId} AND vdc_id = #{selectedVdcId}")
	VdcProjQuotaMap getVdcdetailsByDptID(@Param("projId") Integer projId,
			@Param("selectedVdcId") Integer selectedVdcId);

	@Update("UPDATE isap_env_vdc_proj_quota_map SET total_mem =#{total_mem},total_vcpu =#{total_vcpu},total_disk_storage =#{total_disk_storage},free_mem=#{free_mem},free_vcpu =#{free_vcpu},free_disk_storage=#{free_disk_storage} where proj_id = #{proj_id} AND vdc_id =#{vdc_id}")
	void editVdcProject(VdcProjQuotaMap vdcProjQuotaMapBean);

	@Update("UPDATE isap_env_vdc_dpt_quota_map SET free_mem=#{dptFreeMem},free_vcpu =#{dptFreeVCPU},free_disk_storage=#{dptFreeDisk} where dpt_id = #{dptID} AND vdc_id =#{vdcId}")
	void updateNewAvalilibtyInDpt(@Param("dptFreeMem") Integer dptFreeMem,
			@Param("dptFreeVCPU") Integer dptFreeVCPU,
			@Param("dptFreeDisk") Integer dptFreeDisk,
			@Param("dptID") Integer dptID, @Param("vdcId") Integer vdcId);
	
	@Select("SELECT vdc_name FROM isap_env_vdc_master WHERE vdc_id =#{selectedVdc}")
	VdcMaster getVdcName(Integer selectedVdc);

	@Insert("INSERT INTO isap_env_vdc_usr_quota_map(usr_id,vdc_id,total_mem,total_vcpu,total_disk_storage,free_mem,free_vcpu,free_disk_storage,created_by)"
			+ "VALUES(#{usr_id},#{vdc_id},#{total_mem},#{total_vcpu},#{total_disk_storage},#{free_mem},#{free_vcpu},#{free_disk_storage},#{created_by})")
	void updateInUserQuotaTableGroup(VdcUserQuotaMap userQuota);

	@Select("SELECT vdc_id FROM isap_env_vdc_proj_quota_map WHERE proj_id =#{projId} ")
	List<Integer> getVdcIdListsByProjIDFromProjQuotaTable(int projId);

	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map where dpt_id = #{dptID}")
	List<VdcDeptQuotaMap> getVdcListsFromDptQuota(int dptID);

	@Select("SELECT vdc_name FROM isap_env_vdc_master WHERE vdc_id =#{selectedVdc}")
	String getVdcNamesByID(int vdc_id);

	@Delete("Delete FROM isap_env_vdc_usr_quota_map WHERE usr_id = #{id} AND vdc_id =#{vdc_id}")
	void deleteQuotaInUsers(@Param("id") Integer  id, @Param("vdc_id") Integer vdc_id);

	@Update("UPDATE isap_cmn_users u,isap_cmn_user_role_map m SET proj_id=1,m.role_id=6 WHERE proj_id=#{proj_id} AND u.usr_id=m.usr_id")
	void updateUsersRoleInUsrs(Integer proj_id);
	
	@Select("SELECT * FROM isap_cmn_projects WHERE proj_name=#{proj_name} AND delete_flag=0")
	Project getProj_name(String proj_name);


	
	
	

}
