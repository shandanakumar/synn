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
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;

public interface IsapAdminUserMapper {

	@Select("SELECT * FROM isap_cmn_corporategroups WHERE delete_flag=0")
	List<CorporateGroups> getCorps();
	
	@Select("SELECT * FROM isap_cmn_depts WHERE delete_flag=0")
	List<Department> getDepts();
	
	@Select("SELECT * FROM isap_cmn_projects WHERE delete_flag=0")
	List<Project> getProj();
	
	@Select("SELECT usr_name,proj_name, dpt_name, corporate_name, e.status, e.usr_id  FROM isap_cmn_users e JOIN isap_cmn_depts r ON e.dpt_id=r.dpt_id JOIN isap_cmn_projects d ON e.proj_id=d.proj_id JOIN isap_cmn_corporategroups c on e.cg_id=c.cg_id where e.delete_flag=0;")
    public  List<User> getUser();

	@Select("SELECT * FROM isap_cmn_depts where (cg_id=#{cgId} OR cg_id=1) AND delete_flag=0")
	List<Department> getDeptsJson(Integer cgId);

	@Select("SELECT * FROM isap_cmn_projects where (dpt_id=#{dptId} OR dpt_id=1) AND delete_flag=0")
	List<Project> getProjsJson(Integer deptId);
	
	@Insert("INSERT INTO isap_cmn_users(usr_name,password,proj_id,dpt_id,cg_id) VALUES (#{usr_name},#{password},#{proj_id},#{dpt_id},#{cg_id})")
	public void addUserInUserTable(User usr);
	
	@Delete("UPDATE isap_cmn_users SET proj_id= 1,dpt_id =1,cg_id =1,delete_flag =1 where usr_id = #{usr_id}")
	void deleteUser(int usr_id);
	

	@Select("SELECT * FROM isap_cmn_users where cg_id = #{cg_id}  order by usr_id desc")
	void getuserLists(Integer cg_id);

	@Insert("INSERT INTO isap_cmn_user_role_map(usr_id,role_id) VALUES (#{usrId},#{roleId})")
	void insertRoleIdforUserIdInRolemap(@Param("roleId")int roleId,@Param("usrId") int usrId);

	
	@Select("SELECT role_id FROM isap_cmn_user_role_map where usr_id = #{id}")
	UserRoleMap getAdminDetailsJsonById(Integer id);

	@Select("SELECT * FROM isap_cmn_users WHERE usr_id = #{usrId}")
	User getUserById(Integer userId);


	 final String GET_USER_BY_USR_ID = "SELECT users.usr_id,users.usr_name FROM isap_cmn_users users,isap_cmn_user_role_map maps,isap_cmn_corporategroups corp "
				+ "WHERE users.usr_id=maps.usr_id "
				+ "AND users.dpt_id=#{DeptId} ";
				/*+ "AND users.cg_id = 2 AND corps.cg_id=2";*/
	
	
	@Select(GET_USER_BY_USR_ID)
	List<User> getUsersByuser(User user);

	@Update("UPDATE isap_cmn_users SET usr_name=#{usr_name},proj_id=#{proj_id},dpt_id=#{dpt_id},cg_id=#{cg_id} where usr_id=#{usr_id}")
	void getUserFromUserTable(User user);
	
	@Update("UPDATE isap_cmn_user_role_map SET role_id = 5 WHERE usr_id =#{usrID}")
	/*@Update("UPDATE  isap_cmn_users u, isap_cmn_user_role_map m SET u.usr_name=#{usr_name},u.proj_id=#{proj_id},u.dpt_id=#{dpt_id},u.cg_id=#{cg_id} ,m.role_id=5 WHERE u.usr_id=#{usr_id} AND u.usr_id=m.usr_id")*/
	void getEndUserFromUserTable(int usrID);
    
	
	@Update("UPDATE isap_cmn_user_role_map SET role_id = 6 WHERE usr_id =#{usrID}")
	/*@Update("UPDATE  isap_cmn_users u, isap_cmn_user_role_map m SET u.usr_name=#{usr_name},u.proj_id=#{proj_id},u.dpt_id=#{dpt_id},u.cg_id=#{cg_id} ,m.role_id=5 WHERE u.usr_id=#{usr_id} AND u.usr_id=m.usr_id")*/
	void getEndUserFromUserTable2(int usrID);  
	
	
	@Select("SELECT usr_id FROM isap_cmn_user_role_map where role_id =5 OR role_id = 6")
	List<UserRoleMap> getNonAdminUsers();
    
	@Select("SELECT usr_name,usr_id FROM isap_cmn_users WHERE usr_id = #{usr_ID} AND dpt_id=#{dptId}")
	List<User> getNewDeptsUserJsonById(@Param("usr_ID")int usr_ID,@Param("dptId")int dptId);
    
	@Select("SELECT usr_name,usr_id FROM isap_cmn_users WHERE usr_id = #{usr_ID} AND cg_id=#{cgId}")
	List<User> geNewCoprUsersInJSONId(@Param("usr_ID")int usr_ID,@Param("cgId")int cgId);

    @Update("UPDATE isap_cmn_user_role_map SET role_id = 3 WHERE usr_id =#{usrId}")
	void updateAdminDetails(int usrId);
	 
    @Update("UPDATE isap_cmn_users u,isap_cmn_user_role_map m SET  u.cg_id=1,role_id = 6 WHERE u.usr_id =#{usr_ID} AND m.usr_id=u.usr_id")
	void updateOldAdminrole(@Param("usr_ID")int usr_ID);
    
    
    @Update("UPDATE isap_cmn_users u,isap_cmn_user_role_map m SET  u.dpt_id=1,role_id = 6 WHERE u.usr_id =#{usr_ID} AND m.usr_id=u.usr_id")
    void updateOldDeptAdminrole(@Param("usr_ID")int usr_ID);
    
    @Update("UPDATE isap_cmn_users u,isap_cmn_user_role_map m SET  u.proj_id=1,role_id = 6 WHERE u.usr_id =#{usr_ID} AND m.usr_id=u.usr_id")
    void updateOldProjAdminrole(int oldAdminId);

	@Select("SELECT usr_id FROM isap_cmn_users WHERE usr_name = #{usrName}")
	int getNewAdminIdByName(String usrName);
	
	@Update("UPDATE isap_cmn_users u,isap_cmn_user_role_map m SET  u.proj_id=1, u.dpt_id=1 role_id = 2 WHERE u.usr_id =#{usr_ID} AND m.usr_id=u.usr_id")
	void updateCorpAdminDetails(int newAdminID);
	
	@Select("SELECT usr_name,usr_id FROM isap_cmn_users WHERE usr_id = #{usr_ID} AND dpt_id=#{projId}")
	List<User> getNewProjUsersInJSONId(@Param("usr_ID")int usr_ID, @Param("projId")int projId);
	
	@Update("UPDATE isap_cmn_user_role_map SET role_id = 4 WHERE usr_id =#{usrId}")
	void updateProjAdminDetails(int newAdminID);

	@Select("SELECT usr_name FROM isap_cmn_user_role_map map JOIN isap_cmn_users user ON map.usr_id = user.usr_id where map.role_id =5  AND user.proj_id=#{projId} AND delete_flag=0")
	List<User> getNewProjUsersJSON(@Param("projId")int projId);
	
	@Select("SELECT usr_name FROM isap_cmn_user_role_map map JOIN isap_cmn_users user ON map.usr_id = user.usr_id where map.role_id = 6 AND user.dpt_id=#{dptId} AND user.delete_flag=0")
	List<User> getNewDeptsUseById(@Param("dptId")int dptId);
	
	@Select("SELECT usr_name FROM isap_cmn_user_role_map map JOIN isap_cmn_users user ON map.usr_id = user.usr_id where map.role_id = 6 AND user.cg_id=#{cgId} AND user.delete_flag=0")
	List<User> getNewCoprUsersInJSONId(@Param("cgId")int cgId);

	@Select("SELECT * FROM isap_cmn_users WHERE usr_name=#{usr_name} AND delete_flag=0")
	User getUsr_name(String usr_name);
	
	
	@Select("SELECT role_name FROM isap_cmn_users u JOIN isap_cmn_user_role_map m ON u.usr_id=m.usr_id JOIN isap_cmn_roles r ON m.role_id=r.role_id WHERE u.usr_id=#{usr_id}")
	String getRoleName(int usr_id);



	

	

}
