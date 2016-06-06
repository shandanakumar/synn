package com.syntel.isap.provisioning.dao.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.CustomVMExt;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.dao.IEndUserDAO;
import com.syntel.isap.provisioning.mapper.EndUserMapper;

@Service("endUserDAO")
public class EndUserDAOImpl implements IEndUserDAO {

	 @Autowired
	    private EndUserMapper endUserMapper;
	
	public int getProjectId(int usr_id) {
		// TODO Auto-generated method stub
		return endUserMapper.getProjectId(usr_id);
	}

	public List<User> getUsersByProject(int pg_id) {
		// TODO Auto-generated method stub
		return endUserMapper.getUsersByProject(pg_id);
	}

	public List<VdcProjQuotaMap> getProjQuotaMap(int pgid) {
		
		return endUserMapper.getProjQuotaMap(pgid);
	}

	public String getProjectName(int pgid) {
		// TODO Auto-generated method stub
		return endUserMapper.getProjectName(pgid);
	}

	public VdcMaster getVdcName(int vdcID) {
		// TODO Auto-generated method stub
		
			return endUserMapper.getVdcName(vdcID);
		
	}

	public String getUsersRoleByUserId(int usr_id) {
		// TODO Auto-generated method stub
		return endUserMapper.getUsersRoleByUserId(usr_id);
	}

	public List<Identity> getKeyPairList(int proj_id) {
		// TODO Auto-generated method stub
		return endUserMapper.getKeyPairList(proj_id);
	}

	public void updateUsersInfo(int proj_id, int id) {
		// TODO Auto-generated method stub
		endUserMapper.updateUsersInfo(proj_id,id);
	}

	public List<User> getDPoolUsers(int dpt_id) {
		// TODO Auto-generated method stub
		return endUserMapper.getDPoolUsers(dpt_id);
	}

	public List<User> getVMUsers(int proj_id) {
		// TODO Auto-generated method stub
		return endUserMapper.getVMUsers(proj_id);
	}

	public List<CustomVM> getCustomList(int usr_id) {
		// TODO Auto-generated method stub
		return endUserMapper.getCustomList(usr_id);
	}

	public List<CustomVMExt> getCustomVMExtDetailsById(int customId) {
		// TODO Auto-generated method stub
		return endUserMapper.getCustomVMExtDetailsById(customId);
	}

	public String getUserNameByUserId(int usr_id) {
		// TODO Auto-generated method stub
		return endUserMapper.getUserNameByUserId(usr_id);
	}
	
	
}