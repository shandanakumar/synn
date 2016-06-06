package com.syntel.isap.provisioning.service;

import java.util.List;

import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;

public interface IEndUserService {

	int getProjectId(int usr_id);

	List<User> getUsersByProject(int pg_id);

	String getProjectName(int pgid);

	List<VdcProjQuotaMap> getProjQuotaMap(int pgid);

	List<Identity> getKeyPairList(int proj_id);

	void addDPoolUsertoProject(User user, String user_name);

	List<User> getDPoolUsers(int dpt_id);

	List<User> getVMUsers(int proj_id);

	List<CustomVM> getVMList(int usr_id);

}
