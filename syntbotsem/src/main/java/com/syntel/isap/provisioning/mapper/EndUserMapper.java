package com.syntel.isap.provisioning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.CustomVMExt;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;

public interface EndUserMapper {

	@Select("SELECT proj_id FROM isap_cmn_users WHERE usr_id = #{usr_id}")
	int getProjectId(int usr_id);
	
	@Select("SELECT * FROM isap_cmn_users WHERE proj_id = #{pg_id}")
	List<User> getUsersByProject(int pg_id);

	@Select("Select proj_name FROM isap_cmn_projects WHERE proj_id = #{projId}")
	String getProjectName(int pgid);

	@Select("SELECT * FROM isap_env_vdc_proj_quota_map WHERE proj_id = #{pgid} ")
	List<VdcProjQuotaMap> getProjQuotaMap(int pgid);

	@Select("SELECT vdc_name FROM isap_env_vdc_master WHERE vdc_id =#{vdcID}")
	VdcMaster getVdcName(int vdcID);
    
	@Select("SELECT role_name FROM isap_cmn_users u JOIN isap_cmn_user_role_map m ON u.usr_id=m.usr_id JOIN isap_cmn_roles r ON m.role_id=r.role_id WHERE u.usr_id=#{usr_id}")
	String getUsersRoleByUserId(int usr_id);
    
	@Select("SELECT i.name,i.created_at,u.usr_name FROM isap_cmn_users u JOIN isap_env_identity i ON u.usr_id=i.usr_id WHERE u.proj_id=#{proj_id}")
	List<Identity> getKeyPairList(int proj_id);
	
	
	/* end user query*/
	@Update("UPDATE isap_cmn_users u,isap_cmn_user_role_map m SET u.proj_id=#{proj_id},m.role_id=5 WHERE u.usr_id=#{id} AND u.usr_id=m.usr_id")
	void updateUsersInfo(@Param("proj_id") Integer proj_id,@Param("id") Integer id);
	
	
	@Select("SELECT u.usr_name,u.usr_id FROM isap_cmn_users u JOIN isap_cmn_user_role_map m ON u.usr_id=m.usr_id WHERE dpt_id=#{dpt_id}  AND proj_id=1 AND m.role_id!=3 AND u.delete_flag=0 ")
	List<User> getDPoolUsers(int dpt_id);

	@Select("SELECT DISTINCT u.usr_id FROM isap_cmn_users u JOIN isap_env_vm_custom_prov p ON u.usr_id=p.usr_id WHERE u.proj_id=#{proj_id} AND p.delete_flag=0 ")
	List<User> getVMUsers(int proj_id);

	@Select("SELECT * FROM isap_env_vm_custom_prov where usr_id=#{userId} AND delete_flag=0 order by vm_custom_id  desc")
	List<CustomVM> getCustomList(int usr_id);

	@Select("SELECT * FROM isap_env_vm_custom_prov_ext where isap_env_vm_custom_prov_vm_custom_id= #{customId}")
	List<CustomVMExt> getCustomVMExtDetailsById(int customId);

	@Select("SELECT usr_name from isap_cmn_users where usr_id=#{usr_id}")
	String getUserNameByUserId(int usr_id);

}
