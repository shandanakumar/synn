package com.syntel.isap.provisioning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;
import com.syntel.isap.provisioning.dao.ICorporateGroupDAO;
import com.syntel.isap.provisioning.dao.IIsapAdminUserDAO;
import com.syntel.isap.provisioning.service.IIsapAdminUserService;

@Service("isapAdminUserService")
public class IsapAdminUserServiceImpl implements IIsapAdminUserService{

	@Autowired
	private IIsapAdminUserDAO isapAdminUserDAO;
	
	@Autowired
	private ICorporateGroupDAO corporateGroupDAO;
	
	@Transactional
	public List<CorporateGroups> getCorps() {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.getCorps();
	}


	@Transactional
	public List<Department> getDepts() {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.getDepts();
	}

	@Transactional
	public List<Project> getProj() {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.getProj();
	}

	
	//@Override // this annotation giving same error KALYAN
	public List<Department> getDeptsJsonById(Integer depId) {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.getDeptsJsonById(depId);
	}

	//@Override // this annotation giving same error KALYAN
	public List<Project> getProjsJsonById(Integer projId) {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.getProjsJsonById(projId);
	}

	
	@Transactional
	public List<User> getUser() {
			
	List<User> users=isapAdminUserDAO.getUser();
	for (User user : users) {
		int usr_id=user.getUsr_id();
		String role_name=isapAdminUserDAO.getRoleName(usr_id);
		user.setRole_name(role_name);
	}		
	return users;
					
	}


	@Transactional
	public void deleteUser( Integer usr_id)  {
		isapAdminUserDAO.deleteUser(usr_id);
		
	}

	@Transactional
	public List<User> getUserLists(int cg_id) {
		return isapAdminUserDAO.getuserLists(cg_id);
	}
	
	@Transactional
	public void addUserInUserTable(User user){
		String userName=null;
		int usrId = 0;
		int projId = 0;
		int endUserRoleId = 5;
		int userRoleId = 6;
		isapAdminUserDAO.addUserInUserTable(user);
		userName = user.getUsr_name();
		usrId = corporateGroupDAO.getUserIdByuserNameInUsertable(userName);
		System.out.println("UserId+++++++++++++++++++++++++++++"+usrId );
		projId = user.getProj_id();
		System.out.println("ProjectId+++++++++++++++++++++++++++++"+projId );
		if(projId != 1){
			isapAdminUserDAO.insertRoleIdforUserIdInRolemap(endUserRoleId,usrId);
		}else{
			isapAdminUserDAO.insertRoleIdforUserIdInRolemap(userRoleId,usrId);
		}
	}


	public UserRoleMap getAdminDetailsJsonById(Integer id) {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.getAdminDetailsJsonById(id);
	}

	
	@Transactional
	public User getUserById(Integer userId) {
		System.out.println("UserId+++++++++++++++++++++++++++++"+userId );
		return isapAdminUserDAO.getUserById(userId);
	}


	public List<User> getUsersByuser(User user) {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.getUsersByuser(user);
	}


	public void getUserFromUserTable(User user) {
		
		int cg_id=user.getCg_id();
		int dpt_id=user.getDpt_id();
		int proj_id=user.getProj_id();
		
		System.out.println("User id s"+cg_id +dpt_id +proj_id );
		int usrId = user.getUsr_id();
		if(cg_id!=1 && dpt_id!=1 && proj_id!=1)
		{
			
			
			System.out.println("inside if condtion " );
			isapAdminUserDAO.getEndUserFromUserTable(usrId);
			isapAdminUserDAO.getUserFromUserTable(user);
			
		}
		else
		{
			System.out.println("inside else condtion " );
			isapAdminUserDAO.getUserFromUserTable(user);
			isapAdminUserDAO.getEndUserFromUserTable2(usrId);
		}
		
		
		
	}


	public List<UserRoleMap> getNonAdminUsers() {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.getNonAdminUsers();
	}


	public List<User> getNewDeptsUserJsonById(Integer usr_ID, Integer dptId) {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.getNewDeptsUserJsonById(usr_ID, dptId);
	}


	public List<User> geNewCoprUsersInJSONId(Integer usr_ID, Integer cgId) {
		// TODO Auto-generated method stub
		return isapAdminUserDAO.geNewCoprUsersInJSONId(usr_ID, cgId);
	}


	public void updateDeptadminDetails(User user) {
		
		int oldAdminId = user.getUsr_id();
		isapAdminUserDAO.updateOldDeptAdminrole(oldAdminId);
		String newAdmin =user.getUsr_name();
	    int newAdminID = isapAdminUserDAO.getNewAdminIdByName(newAdmin);
		isapAdminUserDAO.updateAdminDetails(newAdminID);
	}


	public void updateCorpAdminDetails(User user) {
		int oldAdminId = user.getUsr_id();
		isapAdminUserDAO.updateOldAdminrole(oldAdminId);
		String newAdmin =user.getUsr_name();
		System.out.println("Admin name"+newAdmin);
	    int newAdminID = isapAdminUserDAO.getNewAdminIdByName(newAdmin);
		isapAdminUserDAO.updateCorpAdminDetails(newAdminID);
		
	}


	public List<User> getNewProjUsersInJSONId(Integer usr_ID, Integer projId) {
		
		return isapAdminUserDAO.getNewProjUsersInJSONId(usr_ID, projId);
	}


	public void updateProjAdminDetails(User user) {
		int oldAdminId = user.getUsr_id();
		isapAdminUserDAO.updateOldProjAdminrole(oldAdminId);
		String newAdmin =user.getUsr_name();
		System.out.println("Admin name"+newAdmin);
	    int newAdminID = isapAdminUserDAO.getNewAdminIdByName(newAdmin);
		isapAdminUserDAO.updateProjAdminDetails(newAdminID);
		
	}


	public List<User> getNewProjUsersJSON(Integer projId) {
		
		return isapAdminUserDAO.getNewProjUsersJSON(projId);
	}


	public List<User> getNewDeptsUseById(Integer dptId) {
		
		return isapAdminUserDAO.getNewDeptsUseById(dptId);
	}



	public List<User> getNewCoprUsersInJSONId(Integer cgId) {
		
	return isapAdminUserDAO.getNewCoprUsersInJSONId(cgId);
		
		
	}


	public List<User> geNewCoprUsersInJSONId(Integer usr_ID) {
		// TODO Auto-generated method stub
		return null;
	}


	public int getUsr_name(String usr_name) {
		User user = isapAdminUserDAO.getUsr_name(usr_name);
		if(user != null)
			
		{
			return 1;
		}
		return 0;
	}


	

	}

	
	
	

