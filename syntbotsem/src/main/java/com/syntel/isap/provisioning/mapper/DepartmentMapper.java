package com.syntel.isap.provisioning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

public interface DepartmentMapper {

	@Select("SELECT * FROM isap_cmn_depts where cg_id=#{cgId} AND delete_flag=0 order by dpt_id desc")
	List<Department> getDepartmentList(int cgId);

	@Insert("INSERT INTO isap_cmn_depts (dpt_name,dpt_desc,status,cg_id,created_by,owner) VALUES (#{dpt_name},#{dpt_desc},#{status},#{cg_id},#{created_by},#{owner})")
	@Options(useGeneratedKeys = true, keyProperty = "dpt_id")
	void addDepartment(Department department);

	@Insert("INSERT INTO isap_cmn_users (usr_name,usr_desc,cg_id,dpt_id) VALUES (#{usr_name},#{usr_desc},#{cg_id},#{dpt_id})")
	@Options(useGeneratedKeys = true, keyProperty = "usr_id")
	void addDepartmentAdmin(User user);

	@Select("SELECT users.usr_id,users.usr_name FROM isap_cmn_users users,isap_cmn_user_role_map maps WHERE users.usr_id=maps.usr_id AND users.cg_id=#{cg_id} AND users.dpt_id=1 AND maps.role_id=6")
	List<User> getUsersByCorporateId(User user);

	@Update("UPDATE isap_cmn_user_role_map SET role_id =3 where usr_id = #{usr_id}")
	void mapRoleToUser(User user);

	@Update("UPDATE isap_cmn_users SET dpt_id = #{dptId} where usr_id = #{usrId}")
	void mapDepartmentToUser(@Param("usrId") int usrId,
			@Param("dptId") int dptId);

	@Select("SELECT usr_name FROM isap_cmn_users WHERE usr_id = #{usrId}")
	String getUserNameById(int usrId);

	@Update("UPDATE isap_cmn_depts SET delete_flag=1  where dpt_id = #{deptId}")
	void updateDepartment(@Param("deptId") Integer deptId);

	@Update("UPDATE isap_cmn_user_role_map SET role_id=6  where usr_id = #{userId}")
	void updateUserRole(@Param("userId") int userId);

	@Select("SELECT users.usr_id FROM isap_cmn_users users,isap_cmn_user_role_map maps WHERE users.usr_id=maps.usr_id AND users.dpt_id=#{deptId} AND maps.role_id=3")
	User getUserIdByDeptId(Integer deptId);

	@Update("UPDATE isap_cmn_users SET dpt_id=1 where usr_id = #{userId}")
	void updateUser(@Param("userId") int userId);

	@Select("SELECT * FROM isap_cmn_depts WHERE dpt_id = #{deptId}")
	Department getDepartmentById(Integer deptId);

	@Select("SELECT * FROM isap_cmn_users WHERE usr_name = #{userName}")
	User getOwnerByName(String userName);

	@Update("UPDATE isap_cmn_depts SET dpt_name=#{dpt_name},dpt_desc=#{dpt_desc},status=#{status},owner=#{owner},created_by=#{created_by} where dpt_id=#{dpt_id}")
	void updateDepartmentWithDepartment(Department department);

	@Select("SELECT users.usr_id,users.usr_name FROM isap_cmn_users users,isap_cmn_user_role_map maps "
			+ "WHERE users.usr_id=maps.usr_id "
			+ "AND users.dpt_id=#{dpt_id} "
			+ "AND maps.role_id=2")
	User getOwnerUserId(User user);

	@Select("SELECT usr_id FROM isap_cmn_users WHERE usr_name = #{owner}")
	int getUserIdByName(String owner);

	@Select("SELECT proj_id,proj_name FROM isap_cmn_projects WHERE dpt_id = #{deptId} AND delete_flag=0")
	List<Project> getProjectByDeptID(Integer deptId);

	/*@Select("SELECT usr_id FROM isap_cmn_users WHERE proj_id = #{projId} ")
	List<Integer> getUserByDeptID(Integer projId);

	@Update("UPDATE isap_cmn_users SET dpt_id=1,proj_id=1 where usr_id = #{userId}")
	void updateUserDptProjIds(@Param("userID") Integer userId);

	@Update("UPDATE isap_cmn_projects SET delete_flag=1  where proj_id = #{projId}")
	void updateProjectDeleteFlag(@Param("projId") Integer projId);
*/
	@Select("SELECT * FROM isap_env_vdc_cg_quota_map WHERE vdc_id = #{selectedVdcId} AND cg_id = #{cgId} ")
	VdcCgQuotaMap vdcCgQuotaByVdcIDandcgID(@Param("selectedVdcId") Integer selectedVdcId,@Param("cgId") Integer cgId);

	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map")
	List<VdcDeptQuotaMap> getRowsFromVdcDeptQuotaMapTable();

	@Select("SELECT sum(total_mem) FROM isap_env_vdc_dpt_quota_map WHERE vdc_id = #{selectedVdcId}")
	int getSumOfTotalAllocatedMemoryToDpt(@Param("selectedVdcId") int selectedVdcId);

	@Select("SELECT sum(total_vcpu) FROM isap_env_vdc_dpt_quota_map WHERE vdc_id = #{selectedVdcId}")
	int getSumOftotalAllocatedDptVcpu(@Param("selectedVdcId") int selectedVdcId);

	@Select("SELECT sum(total_disk_storage) FROM isap_env_vdc_dpt_quota_map WHERE vdc_id = #{selectedVdcId}")
	int gettotalAllocatedDptDiskStorage(@Param("selectedVdcId") int selectedVdcId);

	@Insert("INSERT INTO isap_env_vdc_dpt_quota_map(dpt_id,vdc_id,total_mem,total_vcpu,total_disk_storage,free_mem,free_vcpu,free_disk_storage,created_by,cg_id) VALUES(#{dpt_id},#{vdc_id},#{total_mem},#{total_vcpu},#{total_disk_storage},#{free_mem},#{free_vcpu},#{free_disk_storage},#{created_by},#{cg_id})")
	void allocateVdcToDepaertmentGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean);

	int getCurrentavaliabvity(int cg_id);

	@Select("SELECT * FROM isap_env_vdc_cg_quota_map WHERE cg_id = #{cgId} ")
	VdcCgQuotaMap getVDCdetailsBycgID(@Param("cgId") Integer cgId);

	@Update("UPDATE isap_env_vdc_cg_quota_map SET free_mem=#{cgAvailableMem},free_vcpu =#{cgAvailableVcpu},free_disk_storage=#{cgAvailableDiskStorage} where vdc_id = #{selectedVdcId} AND cg_id = #{cgID}")
	void updateFreeSpacesinCgquota(@Param("cgAvailableMem") Integer cgAvailableMem,@Param("cgAvailableVcpu") Integer cgAvailableVcpu,@Param("cgAvailableDiskStorage") Integer cgAvailableDiskStorage,@Param("selectedVdcId") Integer selectedVdcId,@Param("cgID") Integer cgID);
	
	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map WHERE vdc_id = #{selectedVdcId} AND dpt_id = #{dptId}")
	VdcDeptQuotaMap getVdcdetails(@Param("selectedVdcId") Integer selectedVdcId,@Param("dptId") Integer dptId);

	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map  WHERE cg_id=#{cgID} ")
	List<VdcDeptQuotaMap> getRowsFromDptQuotaMapTable(@Param("cgID") Integer cgID);

	@Select("Select * FROM isap_cmn_depts WHERE dpt_id = #{dptId}")
	Department getDptjGrpByID(int dpt_id);

	@Select("SELECT free_mem FROM isap_env_vdc_cg_quota_map WHERE cg_id=#{cgID} AND vdc_id = #{selectedVdcId}")
	int getfree_memDB(@Param("cgID") Integer cgID,@Param("selectedVdcId") Integer selectedVdcId);

	@Select("SELECT free_vcpu FROM isap_env_vdc_cg_quota_map WHERE cg_id=#{cgID} AND vdc_id = #{selectedVdcId}")
	int getfree_vcpuDB(@Param("cgID") Integer cgID,@Param("selectedVdcId") Integer selectedVdcId);

	@Select("SELECT free_disk_storage FROM isap_env_vdc_cg_quota_map WHERE cg_id=#{cgID} AND vdc_id = #{selectedVdcId}")
	int getfree_diskDB(@Param("cgID") Integer cgID,@Param("selectedVdcId") Integer selectedVdcId);

	@Select("Select * FROM isap_cmn_depts WHERE dpt_id = #{dptid}")
	Department getDepartmentName(@Param("dptid") Integer dptid);

	@Select("SELECT * FROM isap_env_vdc_cg_quota_map WHERE vdc_id = #{selectedVdcId} AND cg_id=#{cgID}")
	VdcCgQuotaMap getFreeMemoryinCGQuota(@Param("selectedVdcId") Integer selectedVdcId,@Param("cgID") Integer cgID);

	@Delete("Delete FROM isap_env_vdc_dpt_quota_map WHERE dpt_id = #{dptid} AND vdc_id =#{vdcId}")
	void deleteDepartmentQuota(@Param("dptid") Integer dptid,@Param("vdcId") Integer vdcId);

	@Select("Select * FROM isap_env_vdc_dpt_quota_map WHERE dpt_id = #{dptid} AND  vdc_id =#{vdc_id}")
	VdcDeptQuotaMap getVdcdetailsByCgID(@Param("dptid") Integer dptid,@Param("vdc_id") Integer vdc_id);

	@Select("SELECT * FROM isap_env_vdc_cg_quota_map WHERE cg_id=#{cgID} AND  vdc_id =#{vdcId}")
	VdcCgQuotaMap getFreeVdcDeatilsInCGQuota(@Param("cgID") Integer cgID,@Param("vdcId") Integer vdcId);

	@Update("UPDATE isap_env_vdc_cg_quota_map SET free_disk_storage =#{newcgFreeDisk}, free_mem =#{newcgFreeMem},free_vcpu=#{newcgFreeCvpu} where cg_id = #{cgID} AND vdc_id = #{vdcId}")
	void updateFreememoryIncg(@Param("newcgFreeDisk") Integer newcgFreeDisk, @Param("newcgFreeMem") Integer newcgFreeMem,@Param("newcgFreeCvpu") Integer newcgFreeCvpu,@Param("cgID") Integer cgID, @Param("vdcId") Integer vdcId);

	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map WHERE dpt_id =#{dptid} AND vdc_id =#{vdcID}")
	VdcDeptQuotaMap getAllocatedDetails(@Param("dptid") Integer dptid,@Param("vdcID") Integer vdcID);

	@Select("SELECT vdc_name FROM isap_env_vdc_master WHERE vdc_id =#{selectedVdc}")
	VdcMaster getVdcName(Integer selectedVdc);

	@Update("UPDATE isap_env_vdc_cg_quota_map SET free_mem=#{cgFreeMem},free_vcpu =#{cgFreeVCPU},free_disk_storage=#{cgFreeDisk} where cg_id = #{cgID} AND vdc_id =#{vdcId}")
	void updateNewAvalilibtyInCg(@Param("cgFreeMem") Integer cgFreeMem,@Param("cgFreeVCPU") Integer cgFreeVCPU,@Param("cgFreeDisk") Integer cgFreeDisk,@Param("cgID") Integer cgID, @Param("vdcId") Integer vdcId);

	@Update("UPDATE isap_env_vdc_dpt_quota_map SET total_mem =#{total_mem},total_vcpu =#{total_vcpu},total_disk_storage =#{total_disk_storage},free_mem=#{free_mem},free_vcpu =#{free_vcpu},free_disk_storage=#{free_disk_storage} where dpt_id = #{dpt_id} AND vdc_id =#{vdc_id}")
	void editVdcToDepaertmentGroup(VdcDeptQuotaMap vdcDeptQuotaMapBean);

	@Select("SELECT * FROM isap_cmn_users WHERE cg_id = #{cgId} AND delete_flag=0 ")
	List<User> getDepartmentUserList(@Param("cgId") Integer cgId);

	
	

	@Insert("INSERT INTO isap_cmn_users (usr_name,password,proj_id,dpt_id,cg_id) VALUES (#{usr_name},#{password},#{proj_id},#{dpt_id},#{cg_id})")
	void addUserInUserTable(User usr);

	@Insert("INSERT INTO isap_cmn_user_role_map(usr_id,role_id) VALUES (#{usrId},#{roleId})")
	void insertRoleIdforUserId(@Param("roleId") int roleId,@Param("usrId") int usrId);

	@Insert("INSERT INTO isap_env_vdc_usr_quota_map(usr_id,vdc_id,total_mem,total_vcpu,total_disk_storage,free_mem,free_vcpu,free_disk_storage,created_by) VALUES(#{usr_id},#{vdc_id},#{total_mem},#{total_vcpu},#{total_disk_storage},#{free_mem},#{free_vcpu},#{free_disk_storage},#{created_by})")
	void updateInUserQuotaTableGroup(VdcUserQuotaMap userQuota);
	
	@Select("SELECT vdc_id FROM isap_env_vdc_dpt_quota_map WHERE dpt_id =#{dptId} ")
	List<Integer> getVdcIdListsByDptIDFromDptQuotaTable(int dptId);

	@Select("SELECT * FROM isap_env_vdc_cg_quota_map where cg_id = #{cgId}")
	List<VdcCgQuotaMap> getVdcListsFromCgQuota(@Param("cgId") Integer cgId);

	@Select("SELECT vdc_name FROM isap_env_vdc_master WHERE vdc_id =#{selectedVdc}")
	String getVdcNameByID(Integer selectedVdc);

	@Delete("Delete FROM isap_env_vdc_usr_quota_map WHERE usr_id = #{id} AND vdc_id =#{vdc_id}")
	void deleteQuotaInUser(@Param("id") Integer  id, @Param("vdc_id") Integer vdc_id);
	
	@Update("UPDATE isap_env_vdc_usr_quota_map SET total_mem =#{total_mem},total_vcpu =#{total_vcpu},total_disk_storage =#{total_disk_storage},free_mem=#{free_mem},free_vcpu =#{free_vcpu},free_disk_storage=#{free_disk_storage} where usr_id = #{usr_id} AND vdc_id =#{vdc_id}")
	void updateVdcdetaisInUserQuota(VdcUserQuotaMap userquota);

	@Select("SELECT free_mem FROM isap_env_vdc_dpt_quota_map WHERE dpt_id=#{dptid} AND vdc_id = #{selectedVdcId}")
	VdcDeptQuotaMap getAllocatedfreeMemory( @Param("selectedVdcId") Integer selectedVdcId,@Param("dptid") Integer dptid);

	@Select("select role_name from isap_cmn_users u join isap_cmn_user_role_map m on u.usr_id=m.usr_id join isap_cmn_roles r on m.role_id=r.role_id where u.usr_id=#{usrID} AND u.delete_flag=0")
	String getRoleNames(int usrID);


	@Update("UPDATE isap_cmn_users u,isap_cmn_user_role_map m SET dpt_id=1,m.role_id=6 WHERE dpt_id=#{deptId} AND u.usr_id=m.usr_id")
	void updateUsersRoleInUsrs(Integer deptId);
	
	@Select("SELECT * FROM isap_cmn_depts WHERE dpt_name=#{dept_name} AND delete_flag=0")
	Department geDept_name1(String dept_name);

	@Select("SELECT * FROM isap_cmn_corporategroups WHERE cg_id = #{cgId}  ")
	List<CorporateGroups> getCorporateName(int cgId);



	@Select("SELECT dpt_name FROM isap_cmn_depts WHERE dpt_id = #{dptId}  AND delete_flag=0")
	String getDptName(int dptId);



	@Select("SELECT usr_name,usr_id FROM isap_cmn_users WHERE usr_id !=1  AND cg_id =1 AND proj_id=1 AND dpt_id = 1 AND delete_flag=0")
    List<User> getPoolUsers();
	
	
	@Update("UPDATE isap_cmn_users SET cg_id=#{cgID} where usr_id = #{id}")
	void updateUserInfo(@Param("id") Integer id, @Param("cgID") Integer cgID);

	@Select("SELECT u.usr_id,u.usr_name,p.proj_name FROM isap_cmn_users u JOIN isap_cmn_projects p ON u.proj_id= p.proj_id WHERE u.dpt_id=#{dpt_id} AND u.delete_flag=0")
	List<User> getDepartmentUser1List(int dpt_id);

	@Select("SELECT role_name FROM isap_cmn_users u JOIN isap_cmn_user_role_map m ON u.usr_id=m.usr_id JOIN isap_cmn_roles r ON m.role_id=r.role_id WHERE u.usr_id=#{usr_id} AND u.delete_flag=0")
	String getPRoleName(int usr_id);

	
	@Select("SELECT u.usr_name,u.usr_id FROM isap_cmn_users u JOIN isap_cmn_user_role_map m ON u.usr_id=m.usr_id WHERE cg_id=#{cg_id} AND proj_id=1 AND dpt_id=1 AND delete_flag=0 AND m.role_id!=2 AND u.delete_flag=0")
	List<User> getCPoolUsers(int cg_id);

	@Update("UPDATE isap_cmn_users SET dpt_id=#{dept_id} where usr_id = #{id}")
	void updateUserssInfo(@Param("id") Integer id, @Param("dept_id") Integer dept_id);

	
	/* for getting Dpool users
	@Select("SELECT usr_name,usr_id FROM isap_cmn_users WHERE dpt_id=#{dpt_id} AND proj_id=1 AND delete_flag=0;")
	List<User> getDPoolUsers(int dpt_id);*/
	
	

	@Delete("UPDATE isap_cmn_users SET cg_id =1 AND delete_flag =1 where usr_id = #{usr_id}")
	void deletePoolUser(Integer usr_id);

	



}
