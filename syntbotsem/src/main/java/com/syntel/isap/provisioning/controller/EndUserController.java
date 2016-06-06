package com.syntel.isap.provisioning.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.service.IDepartmentService;
import com.syntel.isap.provisioning.service.IEndUserService;
import com.syntel.isap.provisioning.service.ILaunchService;

@Controller
public class EndUserController {
	
	@Autowired
	private IEndUserService endUserService;
	
	
	
	private static final Logger LOGGER = Logger.getLogger(DepartmentController.class.getName());

   /**
	 * param 
	 * @param  session 
	 * @return model
	 */
	@RequestMapping(value="/endUserList", method=RequestMethod.GET)
	  public ModelAndView  departmentList(HttpSession session) {
		  List<User> usersList=new ArrayList<User>();
		  
	      String view="manager/projectUsers";
		  LOGGER.info("Inside departmentList()- Get");
		  ModelAndView model = new ModelAndView();
		  User user= (User) session.getAttribute("userValue");
		  System.out.println("**************----------************************"+user.getUsr_id());
		  int pg_id=endUserService.getProjectId(user.getUsr_id());
		  /*LOGGER.info("user Session Values"+user.getCg_id());*/
		  LOGGER.info("***********************************"+pg_id+"****************"+user.getUsr_id());
		  usersList=endUserService.getUsersByProject(pg_id);
		  for (User user2 : usersList) {
			System.out.println("the value of role name is :"+user2.getRole_name());
		}
		
		  model.addObject("userList", usersList);
	     
	      model.setViewName(view);
	      return model;
	  }
	
	
	@RequestMapping(value = "/endUserProjectQuota", method = RequestMethod.GET)
	public ModelAndView listpjQuotaDetails(HttpSession session) {

		LOGGER.info("Inside projectQuota GET");
		User user = (User) session.getAttribute("userValue");
		int pgid = user.getProj_id();
		String view = "manager/endUserProjectQuota";
		ModelAndView model = new ModelAndView();
		String projectName = endUserService.getProjectName(pgid);
		
		 LOGGER.info("***********************************"+pgid+"****************"+projectName);
		List<VdcProjQuotaMap> vdcprojQuotaMap =endUserService.getProjQuotaMap(pgid);
		model.addObject("vdcprojQuotaMap", vdcprojQuotaMap);
		model.addObject("ProjectName", projectName);
	
		model.setViewName(view);
		
		return model;
	}
	
	@RequestMapping(value = "/projectKeyPairList", method = RequestMethod.GET)
	public ModelAndView userKeyPairList(HttpSession session) {
		List<Identity> keyPairList = new ArrayList<Identity>();
		LOGGER.info("Inside userKeyPairList()- Get");
		ModelAndView model = new ModelAndView();
		String view = "manager/projectKeyPairList";
		User user = (User) session.getAttribute("userValue");
		if (user != null){
			keyPairList = endUserService.getKeyPairList(user.getProj_id());
		}
		model.addObject("keyPairList", keyPairList);
		model.setViewName(view);
		return model;
	}

	 @RequestMapping("/addDPoolUser")
	 public ModelAndView addDPoolUser(@RequestParam("user_name") String User_name,HttpSession session )
	 {
	 	
	 	 String view = "manager/projectUsers";
	 	System.out.println("The user value"+User_name);
	 	 User user= (User) session.getAttribute("userValue");
	 	 List<User> usersList=null;
	 	 ModelAndView model = new ModelAndView();
	 	endUserService.addDPoolUsertoProject(user,User_name);
	 	int pg_id=endUserService.getProjectId(user.getUsr_id());
	 	 usersList=endUserService.getUsersByProject(pg_id);
	 	 model.addObject("userList", usersList);
	 	 model.setViewName(view);
	      return model;

	  }
	
	 @RequestMapping(value="/getDPoolUsers", method = RequestMethod.GET)
	  
		public @ResponseBody List<User> getCPoolUsers(HttpSession session) {
		  User user= (User) session.getAttribute("userValue");
		 
		  System.out.println("inside getDPoolIUsers==:;");
		  LOGGER.info("Inside user getPoolUsers Session Values"+user.getCg_id());
		  List<User> usersList=endUserService.getDPoolUsers(user.getDpt_id());
		  
		  System.out.println(usersList);

		  return usersList;
		  }
	 
	 @RequestMapping(value = "/endUserVMList", method = RequestMethod.GET)
		public ModelAndView endUserVMList(HttpSession session)
				throws Exception {
			List<CustomVM> customList = new ArrayList<CustomVM>();
			List<User> usrsList = new ArrayList<User>();
			ModelAndView model = new ModelAndView();
			String view = "manager/projectVMList";
			LOGGER.info("Inside endUserVMList()- Get");
			User user = (User) session.getAttribute("userValue");
			usrsList=endUserService.getVMUsers(user.getProj_id());
			for (User obj : usrsList) {
				System.out.println("The users are............."+obj.getUsr_id());
				System.out.println("The users are............."+obj.getUsr_name());
				customList = endUserService.getVMList(obj.getUsr_id());
				obj.setCustom_VMList(customList);
			}
			for (CustomVM user3 : customList) {
				System.out.println("the values......"+user3.getUsr_name());
			}
			/*if (user != null){
				customList = launchService.getCustomList(user.getUsr_id());
			}*/
			model.addObject("list", usrsList);
			session.setAttribute("userlist", usrsList);
			model.setViewName(view);
			return model;
		}
	 
}
