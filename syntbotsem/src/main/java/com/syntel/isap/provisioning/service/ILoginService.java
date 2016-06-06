package com.syntel.isap.provisioning.service;

import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;
/**
 * @author KK5007843
 *
 */
public interface ILoginService {
	
	User getUserDetails(User user);

	UserRoleMap authenticateUser(User user);

	String getProjName(int projId);
}
