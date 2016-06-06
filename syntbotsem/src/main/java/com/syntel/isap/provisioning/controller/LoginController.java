package com.syntel.isap.provisioning.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;
import com.syntel.isap.provisioning.constants.OpenStackCredentials;
import com.syntel.isap.provisioning.service.ILoginService;

/**
 * @author KK5007843
 *
 */
@Controller
@SessionAttributes("userValue")
public class LoginController {

	@Autowired
	private ILoginService loginService;

	private static final Logger LOGGER = Logger.getLogger(LoginController.class
			.getName());

	/**
	 * param
	 * 
	 * @param @no params
	 * @return model
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		LOGGER.info("Inside Logout()");
		return "redirect:index.jsp";
	}

	/**
	 * param
	 * 
	 * @param @no params
	 * @return model
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		LOGGER.info("Inside Login()- GET");
		return "redirect:index.jsp";
	}

	/**
	 * 
	 * param
	 * 
	 * @param @ModelAttribute user
	 * @return model
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user,HttpSession session) {
	    LOGGER.info("Inside Login()- Post");
		ModelAndView model = new ModelAndView();
		String view = "index";
		String password = user.getPassword();
		User userDb=null;
		UserRoleMap userroledb=null;
		
		 userDb = loginService.getUserDetails(user);
		if (userDb != null) {
			userroledb= loginService.authenticateUser(userDb);
			int roleId=userroledb.getRole_id();
			String rolename=userroledb.getRole_name();
			String userValue1=userDb.getUsr_name();
			/* Temp code Start*/
			int projId = userDb.getProj_id();
			String projectName = loginService.getProjName(projId);
			/* Temp code End*/
			if (roleId != 0) {
				model.addObject("userValue", userDb);
				session.setAttribute("rolename", rolename);
				session.setAttribute("userValue1", userValue1);
				session.setAttribute("projName", projectName);
			}
			if (roleId == OpenStackCredentials.ISAP_ADMIN_ROLE_ID) {
				view = "admin/dashboardAdmin";
			} else if (roleId == OpenStackCredentials.ISAP_CORPORATE_ADMIN_ROLE_ID ) {
				view = "corporate/corporateAdminDashboard";
			} else if (roleId == OpenStackCredentials.ISAP_DEPARTMENT_ADMIN_ROLE_ID ) {
				view = "department/dashboardDepartment";
			} else if (roleId == OpenStackCredentials.ISAP_PROJECT_ADMIN_ROLE_ID) {
				view = "manager/dashboardProject";
			} else if (roleId == OpenStackCredentials.ISAP_ENDUSER_ROLE_ID) {
				view = "provision/dashboardTenantUser";
			}else {
			model.addObject("error", "Invalid username or password!");
			}
		} 
		else{
			model.addObject("error", "Invalid username and password!");
		}
		model.setViewName(view);
		return model;
	}

	/**
	 * param
	 * 
	 * @param @no params
	 * @return model
	 */
	@RequestMapping(value = "/dashboardTenantUser", method = RequestMethod.GET)
	public ModelAndView dashboardTenantUser() {
		LOGGER.info("Inside dashboardTenantUser()- GET");
		ModelAndView model = new ModelAndView();
		model.setViewName("provision/dashboardTenantUser");
		return model;
	}

	/**
	 * param
	 * 
	 * @param @no params
	 * @return model
	 */
	@RequestMapping(value = "/dashboardAdmin", method = RequestMethod.GET)
	public ModelAndView dashboardAdminCorporate() {
		LOGGER.info("Inside dashboardAdminCorporate()- GET");
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/dashboardAdmin");
		return model;
	}
}
