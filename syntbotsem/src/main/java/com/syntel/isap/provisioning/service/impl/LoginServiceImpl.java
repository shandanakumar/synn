package com.syntel.isap.provisioning.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;
import com.syntel.isap.provisioning.dao.ILoginDao;
import com.syntel.isap.provisioning.service.ILoginService;

/**
 * @author KK5007843
 *
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginDao loginDao;

	private static final Logger LOGGER = Logger
			.getLogger(LoginServiceImpl.class.getName());

	@Transactional
	public User getUserDetails(User user) {
		return loginDao.getUser(user);
	}

	@Transactional
	public UserRoleMap authenticateUser(User user) {
		 
		return loginDao.getRole(user.getUsr_id());
	}

	public String getProjName(int projId) {
		
		return loginDao.getProjName(projId);
	}

}
