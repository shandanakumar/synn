package com.syntel.isap.provisioning.dao;

import java.util.List;

import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.CustomVMExt;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;

public interface IEndUserDAO {

	int getProjectId(int usr_id);

	List<User> getUsersByProject(int pg_id);

	List<VdcProjQuotaMap> getProjQuotaMap(int pgid);

	String getProjectName(int pgid);

	VdcMaster getVdcName(int vdcID);

	String getUsersRoleByUserId(int usr_id);

	List<Identity> getKeyPairList(int proj_id);

	void updateUsersInfo(int proj_id, int id);

	List<User> getDPoolUsers(int dpt_id);

	List<User> getVMUsers(int proj_id);

	List<CustomVM> getCustomList(int usr_id);

	List<CustomVMExt> getCustomVMExtDetailsById(int customId);

	String getUserNameByUserId(int usr_id);

}
