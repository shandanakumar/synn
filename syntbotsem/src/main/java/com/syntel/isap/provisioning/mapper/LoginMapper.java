package com.syntel.isap.provisioning.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;

/**
 * @author KK5007843
 *
 */
public interface LoginMapper {
	
	@Select("SELECT role_name,r.role_id FROM  isap_cmn_user_role_map r JOIN isap_cmn_roles e ON r.role_id=e.role_id WHERE usr_id = #{userId}")
	UserRoleMap getRoleByUserId(int userId);

	@Select("SELECT role_name FROM isap_cmn_roles WHERE role_id = #{roleId}")
	String getRoleName(int roleId);

	@Select("Select proj_name From isap_cmn_projects WHERE proj_id = #{projId} AND delete_flag=0 ")
	String getProjName(@Param("projId")int projId);
	
	@Select("SELECT * FROM isap_cmn_users WHERE usr_name = #{userName} AND password=#{password} AND delete_flag=0")
	User getUserByUserName(@Param("userName")String userName, @Param("password")String password);
}
