package com.syntel.isap.provisioning.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.syntel.isap.provisioning.bean.CorporateGroups;
import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.Role;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.UserRoleMap;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.service.IDepartmentService;


/**
 * @author KK5007843
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	private IDepartmentService departmentService;
	
	private static final Logger LOGGER = Logger.getLogger(DepartmentController.class.getName());

   /**
	 * param 
	 * @param  session 
	 * @return model
	 */
	
	
	@RequestMapping(value="/departmentList", method=RequestMethod.GET)
	  public ModelAndView  departmentList(HttpSession session) {
		  List<User> usersList=new ArrayList<User>();
		  List<Department> departments=new ArrayList<Department>();
	      String view="corporate/departments";
		  LOGGER.info("Inside departmentList()- Get");
		  ModelAndView model = new ModelAndView();
		  User user= (User) session.getAttribute("userValue");
		  if(user!=null){
		  departments=departmentService.getDepartmentList(user.getCg_id());
		  LOGGER.info("user Session Values++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+user.getCg_id());
		  usersList=departmentService.getUsersByCorporate(user);
		  }
		  model.addObject("userList", usersList);
	      model.addObject("list", departments);
	      model.setViewName(view);
	      return model;
	  }
	
	/**
	  * param 
	  * @param  session 
	  * @return model
	  */
	 @RequestMapping(value="/addDepartment", method=RequestMethod.GET)
	  public ModelAndView  addDepartmentGet(HttpSession session){
		  List<Department> departments=new ArrayList<Department>();
		  String view="corporate/departments";
		  LOGGER.info("Inside addDepartment()- Get");
		  ModelAndView model = new ModelAndView();
		  User user= (User) session.getAttribute("userValue");
		  departments=departmentService.getDepartmentList(user.getCg_id());
		  LOGGER.info("user Session Values"+user.getCg_id());
		  List<User> usersList=departmentService.getUsersByCorporate(user);
		  model.addObject("userList", usersList);
	      model.addObject("list", departments);
	      model.setViewName(view);
		  return model;
	  }
	
	/**
	  * param 
	  * @param  @ModelAttribute user and department,session 
	  * @return model
	  */
	 @RequestMapping(value="/addDepartment", method=RequestMethod.POST)
	  public ModelAndView  addDepartment( @ModelAttribute("user") User user, HttpSession session,@ModelAttribute("department") Department department){
		  List<Department> departments=new ArrayList<Department>();
		  User userSession= (User) session.getAttribute("userValue");
		  user.setCg_id(userSession.getCg_id());
		  LOGGER.info("Inside addDepartment()- Post");
		  ModelAndView model = new ModelAndView();
		  String corporateAdminName = userSession.getUsr_name();
		  departmentService.addDepartment(department,user,corporateAdminName);
		  departments=departmentService.getDepartmentList(user.getCg_id());
	      String view="corporate/departments";
	      model.addObject("list", departments);
	      model.setViewName(view);
	      return model;
	  }
	 
	/**
	  * param 
	  * @param  @ModelAttribute user and department,session 
	  * @return model
	  */
	 @RequestMapping(value="/editDepartment", method=RequestMethod.POST)
	  public ModelAndView  editDepartment( @ModelAttribute("user") User user,HttpSession session, @ModelAttribute("department") Department department){
		  List<Department> departments=new ArrayList<Department>();
		  User userSession= (User) session.getAttribute("userValue");
		  user.setCg_id(userSession.getCg_id());
		  LOGGER.info("Inside editDepartment()- Post");
		  LOGGER.info("Inside editDepartment()- deptId"+department.getDpt_id());
		  ModelAndView model = new ModelAndView();
		  departmentService.editDepartment(department,user);
		  departments=departmentService.getDepartmentList(user.getCg_id());
	      String view="corporate/departments";
	      model.addObject("list", departments);
	      model.setViewName(view);
	      return model;
	  }
	 
	/**
	  * param 
	  * @param  @RequestParam deptId,session 
	  * @return model
	  */ 
	 @RequestMapping(value="/deleteDepartment", method=RequestMethod.GET)
	  public ModelAndView  deleteDepartment( @RequestParam("deptId")Integer deptId,HttpSession session){
		  List<Department> departments=new ArrayList<Department>();
		  User user= (User) session.getAttribute("userValue");
		  LOGGER.info("Inside deleteDepartment()- GET");
		  LOGGER.info("Inside deleteDepartment()- dept number "+deptId);
		  ModelAndView model = new ModelAndView();
		  int CgID = user.getCg_id();
		  departmentService.deleteDepartment(deptId);
		  departments=departmentService.getDepartmentList(CgID);
	      String view="corporate/departments";
	      model.addObject("list", departments);
	      model.setViewName(view);
	      return model;
	  }
	 
	

	 @RequestMapping(value ="/allocateVdcToDepartmentGroup" ,method=RequestMethod.GET)
	 public ModelAndView allocateVdcToCorporateGroups(@ModelAttribute VdcDeptQuotaMap vdcDeptQuotaMapBean,HttpSession session) {
		    User user= (User) session.getAttribute("userValue");
		    ModelAndView model = new ModelAndView();
    	    int cgID = user.getCg_id();
    	    int dptID = vdcDeptQuotaMapBean.getDpt_id();
		 	LOGGER.info("Inside departmentQuota GET");
			String view = "corporate/departmentsQuota";	
			List<String> departmentNames = new ArrayList<String>();
			List<VdcDeptQuotaMap> vdcdptQuotaMapBeanList = new ArrayList<VdcDeptQuotaMap>();
	        vdcDeptQuotaMapBean.setFree_mem(vdcDeptQuotaMapBean.getTotal_mem());
		    vdcDeptQuotaMapBean.setFree_vcpu(vdcDeptQuotaMapBean.getTotal_vcpu());
		     vdcDeptQuotaMapBean.setFree_disk_storage(vdcDeptQuotaMapBean.getTotal_disk_storage());
		    vdcDeptQuotaMapBean.setCreated_by(user.getUsr_name());
		    departmentService.allocateVdcToDepaertmentGroup(vdcDeptQuotaMapBean,cgID);
		    departmentService.updateInUserQuotaTableGroup(vdcDeptQuotaMapBean, dptID,session);
		    vdcdptQuotaMapBeanList = departmentService.getRowsFromDptQuotaMapTable(cgID);
		    model.addObject("vdcdptQuotaMapBeanList",vdcdptQuotaMapBeanList);
			model.addObject("departmentNames",departmentNames);
	       model.setViewName(view);
	       return model;
		}
	 
	 
	 @RequestMapping(value="/departmentQuota", method=RequestMethod.GET)
		public ModelAndView listdptQuotaDetails(HttpSession session) {
 	    User user= (User) session.getAttribute("userValue");
 	    int cgID = user.getCg_id();
		 	LOGGER.info("Inside departmentQuota GET");
			String view = "corporate/departmentsQuota";
			ModelAndView model = new ModelAndView();
			List<String> departmentNames = new ArrayList<String>();
			List<String>  vdcMaster = new  ArrayList<String>();
			List<VdcDeptQuotaMap> vdcdptQuotaMapBeanList = new ArrayList<VdcDeptQuotaMap>();
			vdcdptQuotaMapBeanList = departmentService.getRowsFromDptQuotaMapTable(cgID);
		    model.addObject("vdcdptQuotaMapBeanList",vdcdptQuotaMapBeanList);
			model.addObject("departmentNames",departmentNames);
			model.addObject("vdcMaster",vdcMaster);
			model.setViewName(view);
			return model;
		}
	 
	 
	 
	 
	 @RequestMapping(value="/deleteDepartmentQuota", method=RequestMethod.GET)
	  public ModelAndView  deleteDepartmentQuota( @RequestParam("dpt_id")Integer dpt_id,@RequestParam("vdc_id")Integer vdc_id,HttpSession session){
		    User user= (User) session.getAttribute("userValue");
		    ModelAndView model = new ModelAndView();
 	        int cgID = user.getCg_id();
		 	LOGGER.info("Inside departmentQuota GET");
		 	LOGGER.info("VDC ID from for Deleteing the quota "+vdc_id);
			String view = "corporate/departmentsQuota";	
			List<String> departmentNames = new ArrayList<String>();
			List<VdcDeptQuotaMap> vdcdptQuotaMapBeanList = new ArrayList<VdcDeptQuotaMap>();
		    VdcDeptQuotaMap  vdcDeptQuotaMap  = new VdcDeptQuotaMap();
		    vdcDeptQuotaMap = departmentService.getVdcdetailsByCgID(dpt_id,vdc_id);
		    departmentService.updateFreememoryInCgquota(vdcDeptQuotaMap,cgID);
		    departmentService.deleteDepartmentQuota(dpt_id,vdc_id);
		    departmentService.deleteQuotaInUserQuota(dpt_id,vdc_id,session);
		    vdcdptQuotaMapBeanList = departmentService.getRowsFromDptQuotaMapTable(cgID);
		    model.addObject("vdcdptQuotaMapBeanList",vdcdptQuotaMapBeanList);
			model.addObject("departmentNames",departmentNames);
	        model.setViewName(view);
	       return model;
	  }
	 

     @RequestMapping(value ="/editVdcToDepartmentGroup" ,method=RequestMethod.GET)
	 public ModelAndView editVdcToDepartmentGroup(@ModelAttribute VdcDeptQuotaMap vdcDeptQuotaMapBean,HttpSession session) {
    	 User user= (User) session.getAttribute("userValue");
    	    int cgID = user.getCg_id();
		 	LOGGER.info("Inside departmentQuota GET");
			String view = "department/departmentQuota";	
			List<String> departmentNames = new ArrayList<String>();
			List<VdcDeptQuotaMap> vdcdptQuotaMapBeanList = new ArrayList<VdcDeptQuotaMap>();
		   LOGGER.info("Inside deleteDepartment()- GET");
		    ModelAndView model = new ModelAndView();
		  departmentService.editVdcToDepaertmentGroup(vdcDeptQuotaMapBean,cgID);
		  vdcdptQuotaMapBeanList = departmentService.getRowsFromDptQuotaMapTable(cgID);
		   model.addObject("vdcdptQuotaMapBeanList",vdcdptQuotaMapBeanList);
			model.addObject("departmentNames",departmentNames);
			model.setViewName(view);
	      model.setViewName(view);
	      return model;
       }
     
	 
     
     @RequestMapping(value="/corporateUserList", method=RequestMethod.GET)
	  public ModelAndView  corporateUserList(HttpSession session) {
		   List<User> usersList=null;
		   
		 List<Department> dptList = null;
		 List<UserRoleMap> rolelist = null;
	      String view="corporate/corporateUserManagement";
		  LOGGER.info("Inside corporateUserList()- Get");
		  ModelAndView model = new ModelAndView();
		  User user= (User) session.getAttribute("userValue");
		  if(user!=null){
			  usersList=departmentService.getDepartmentUserList(user.getCg_id());
			  dptList = departmentService.getDepartmentNameinList(usersList);
			  /*usersList = departmentService.getRoleNameInList(usersList);*/
			  
			  for(User usr:usersList )
			  {
			  System.out.println("rolename from list"+usr.getRole_name()+usr.getRole_name());
		     }
		      LOGGER.info("user Session Values"+user.getCg_id());
		   
		  }
		  
		  model.addObject("userList", usersList);
		  model.addObject("dptList", dptList);
	      model.setViewName(view);
	      return model;
	  }
     
     
     @RequestMapping(value ="/adddeptform" ,method=RequestMethod.POST)
     public ModelAndView adddeptform( User usr,HttpSession session)
 {
		List<User> usersList = new ArrayList<User>();

		String view = "department/departmentUserManagement";
		LOGGER.info("Inside departmentUserList()- Get");
		ModelAndView model = new ModelAndView();
		if (usr != null) {
			departmentService.addUserInUserTable(usr);
		}
		User user = (User) session.getAttribute("userValue");
		if (user != null) {
			usersList = departmentService
					.getDepartmentUserList(user.getCg_id());

			LOGGER.info("user Session Values" + user.getCg_id());
		}
		model.addObject("userList", usersList);

		model.setViewName(view);
		return model;
       }
     

	 @RequestMapping("/addPoolUser")
     public ModelAndView addPoolUser(@RequestParam("user_name") String User_name,HttpSession session )
     {
		 LOGGER.info("Inside allUserLists()- GET");
		 String view = "corporate/corporateUserManagement";
		System.out.println("The user valeye"+User_name);
		 User user= (User) session.getAttribute("userValue");
		 List<User> usersList=null;
		 ModelAndView model = new ModelAndView();
		 departmentService.addPoolUsertoCoprporate(user,User_name);
		 usersList=departmentService.getDepartmentUserList(user.getCg_id());
		 model.addObject("userList", usersList);
		 model.setViewName(view);
	     return model;

      }
	 
	 @RequestMapping(value="/getPoolUsers", method = RequestMethod.GET)
	  
		public @ResponseBody List<User> getPoolUsers(HttpSession session) {
		  User user= (User) session.getAttribute("userValue");
		 
		  System.out.println("inside getPoolIUsers==:;");
		  LOGGER.info("Inside user getPoolUsers Session Values"+user.getCg_id());
		  List<User> usersList=departmentService.getPoolUsers();
		  
		  System.out.println(usersList);

		  return usersList;
		}
	 @RequestMapping(value="/departmentUserList", method=RequestMethod.GET)
	  public ModelAndView  departmentUserList(HttpSession session) {
		 
		 List<User> usersList=null;
	
	      String view="department/departmentUserManagement";
		  LOGGER.info("Inside departmentUserList()- Get");
		  ModelAndView model = new ModelAndView();
		  User user= (User) session.getAttribute("userValue");
		  if(user!=null){
			  usersList=departmentService.getDepartmentUser1List(user.getDpt_id());
			
		  }
		  
		  model.addObject("userList", usersList);
		
	      model.setViewName(view);
	      return model;
	  }
	 
	 @RequestMapping(value="/getCPoolUsers", method = RequestMethod.GET)
	  
		public @ResponseBody List<User> getCPoolUsers(HttpSession session) {
		  User user= (User) session.getAttribute("userValue");
		 
		  System.out.println("inside getPoolIUsers==:;");
		  LOGGER.info("Inside user getPoolUsers Session Values"+user.getCg_id());
		  List<User> usersList=departmentService.getCPoolUsers(user.getCg_id());
		  
		  System.out.println(usersList);

		  return usersList;
		  }

	 
	 @RequestMapping("/addCPoolUser")
    public ModelAndView addCPoolUser(@RequestParam("user_name") String User_name,HttpSession session )
    {
		
		 String view = "department/departmentUserManagement";
		System.out.println("The user value"+User_name);
		 User user= (User) session.getAttribute("userValue");
		 List<User> usersList=null;
		 ModelAndView model = new ModelAndView();
		 departmentService.addCPoolUsertoDepartment(user,User_name);
		 usersList=departmentService.getDepartmentUser1List(user.getDpt_id());
		 model.addObject("userList", usersList);
		 model.setViewName(view);
	     return model;

     }
	 @RequestMapping(value ="/deletePoolUser")
		public ModelAndView deleteUser(@RequestParam(value = "usr_id") Integer usr_id,HttpSession session ) 
	  {
		/* System.out.println(""+usr_id);*/
		 ModelAndView model = new ModelAndView();
		  String view = "corporate/corporateUserManagement";
		  User user= (User) session.getAttribute("userValue");
		 LOGGER.info("Inside deletePoolUser()- GET");
		 departmentService.deletePoolUser(usr_id);
		 List<User> usersList=new ArrayList<User>();
		  LOGGER.info("after deleteing pool user");
		  	  
		  usersList =departmentService.getDepartmentUserList(user.getCg_id());
		  
		  model.addObject("userList", usersList);
		  LOGGER.info("after deleteing userList");
		  model.setViewName(view);
	      return model;
		
		}

	/**
	  * param 
	  * @param  @PathVariable deptId,session 
	  * @return json
	  */ 
	 @RequestMapping(value="/getDepartment/{deptId}", method = RequestMethod.GET)
		public @ResponseBody Department getDepartmentInJSON(@PathVariable Integer deptId) {
		 Department department = new Department();
		 LOGGER.info("Inside getDepartmentInJSON()- GET");
		 department =departmentService.getDepartmentById(deptId);	
		 return department;
		}
	 
	/**
	  * param 
	  * @param  @PathVariable deptId,session 
	  * @return json
	  */ 
	 @RequestMapping(value="/getOwners/{userName}", method = RequestMethod.GET)
		public @ResponseBody User getOwnersInJSON(@PathVariable String userName,HttpSession session) {
         User user=new User();
		 LOGGER.info("Inside getOwnersInJSON()- GET");
         user=departmentService.getOwnerByName(userName);
		 return user;
		}
	 
	/**
	  * param 
	  * @param  session
	  * @return json
	  */ 
	 @RequestMapping(value="/getDeptUsers", method = RequestMethod.GET)
		public @ResponseBody List<User> getDeptUsersInJSON(HttpSession session) {
		  User user= (User) session.getAttribute("userValue");
		  LOGGER.info("Inside user getDeptUsers Session Values"+user.getCg_id());
		  List<User> usersList=departmentService.getUsersByCorporate(user);
		  return usersList;
		} 
	 
	 
	 @RequestMapping(value="/getVdcDetailsFromCorporateTable/{selectedVdcId}", method = RequestMethod.GET)
	 public @ResponseBody VdcCgQuotaMap getVdcDetailsFromServices(HttpSession session,@PathVariable int selectedVdcId) {
		 User user= (User) session.getAttribute("userValue");
		   int cgId = user.getCg_id(); 
		 VdcCgQuotaMap vdcCgQuotaMap = new  VdcCgQuotaMap();
	      vdcCgQuotaMap = departmentService.vdcCgQuotaByVdcIDandcgID(selectedVdcId,cgId);
		 return vdcCgQuotaMap;
		} 
	 

		  @RequestMapping(value="/checkTotalCgvcpuAvailability", method = RequestMethod.POST)
			public @ResponseBody int getAllocatedVDCforDpt(@RequestParam("total_cg_vcpu") String total_cg_vcpu,HttpSession session)
	        {
			    User user= (User) session.getAttribute("userValue");
			   int cg_id = user.getCg_id();
			    int check =0;
	            LOGGER.info("inside ajax()-");
	   	        check = departmentService.getAvaliableVcpuAllocated(total_cg_vcpu,cg_id);
	   	       return check;
	    	 
	       }
		  
		
		     @RequestMapping(value="/checkVdcAllocates", method = RequestMethod.POST)
				public @ResponseBody int getVDCdetails(@RequestParam("selectedVdcId") int selectedVdcId,@RequestParam("dptId")  int dptId)
		        {
		    	  int check =0;
		         LOGGER.info("inside ajax()--------------------");
		   	     check = departmentService.getVdcdetails(selectedVdcId,dptId);
		   	     return check;
		    	 
		       }
		  
		 
			 
		     @RequestMapping(value="/checkallocatedmemory", method = RequestMethod.POST)
				public @ResponseBody int getallocated_mem(@RequestParam("total_cg_mem") int free_mem ,@RequestParam("selectedVdcId") int selectedVdcId ,HttpSession session)
		        {
		    	  int check =0;
		     	  User user= (User) session.getAttribute("userValue");
		    	  int cgID = user.getCg_id();
	    	      LOGGER.info("inside ajax()-");
		   	      check = departmentService.getfree_mem(free_mem,cgID,selectedVdcId);
		   		   return check;
		    	 
		       }
		     
		     
		     
		     @RequestMapping(value="/checkallocatednegativememory", method = RequestMethod.POST)
				public @ResponseBody int checkallocatednegativememory(@RequestParam("total_cg_mem") int free_mem ,@RequestParam("selectedVdcId") int selectedVdcId,@RequestParam("dptID") int dptID ,HttpSession session)
		        {
		    	  int check =0;
		     	  User user= (User) session.getAttribute("userValue");
		    	  int cgID = user.getCg_id();
	    	      LOGGER.info("inside ajax()-");
		   	      check = departmentService.checkAllocatedmem(free_mem,cgID,selectedVdcId,dptID);
		   		   return check;
		    	 
		       }
		     
		     
		     
		     
		     @RequestMapping(value="/checkallocatedvcpu", method = RequestMethod.POST)
				public @ResponseBody int getallocated_vcpu(@RequestParam("total_cg_vcpu") int free_vcpu,@RequestParam("selectedVdcId") int selectedVdcId ,HttpSession session)
		        {
		    	  int check =0;
		   	      User user= (User) session.getAttribute("userValue");
		    	 int cgID = user.getCg_id();
		         LOGGER.info("inside ajax()-");
		   	     check = departmentService.getfree_vcpu(free_vcpu,cgID,selectedVdcId);
		   	      return check;
		    	 
		       }
		     
		     
		     @RequestMapping(value="/checkallocateddiskstorage", method = RequestMethod.POST)
				public @ResponseBody int getallocated_disk(@RequestParam("total_cg_disk_storage") int free_disk_storage , @RequestParam("selectedVdcId") int selectedVdcId  ,HttpSession session)
		        {
		    	  int check =0;
		    	  User user= (User) session.getAttribute("userValue");
		          int cgID = user.getCg_id();
		          LOGGER.info("inside ajax()-");
		   	      check = departmentService.getfree_disk(free_disk_storage,cgID,selectedVdcId);
		   	      return check;
		    	 
		       }
		     
		     
		     @RequestMapping(value="/getDepartmentName/{dptid}", method = RequestMethod.GET)
				public @ResponseBody Department getDepartmentNameInJSON(@PathVariable Integer dptid) {
				 Department department = new Department();
				 LOGGER.info("Inside getDepartmentNameInJSON()- GET");
				 department =departmentService.getDepartmentName(dptid);	
				 LOGGER.info("department Name -"+department.getDpt_name());
              return department;
				}
		     
		     
		     @RequestMapping(value="/getAllocatedDetails/{dptid}/{vdcID}", method = RequestMethod.GET)
				public @ResponseBody VdcDeptQuotaMap getAllocatedDetailsInJSON(@PathVariable Integer dptid,@PathVariable Integer vdcID) {
		    	 VdcDeptQuotaMap vdcDeptQuotaMap = new VdcDeptQuotaMap();
				 LOGGER.info("Inside getDepartmentNameInJSON()- GET");
				 vdcDeptQuotaMap =departmentService.getAllocatedDetails(dptid,vdcID);	
		        return vdcDeptQuotaMap;
				}
		     
		     
		     @RequestMapping(value="/getVdcName/{selectedVdcId}", method = RequestMethod.GET)
				public @ResponseBody VdcMaster getVdcNameInJSON(@PathVariable Integer selectedVdcId) {
		    	 VdcMaster vdcname = departmentService.getVdcName(selectedVdcId);
				 LOGGER.info("Inside getVdcNameInJSON()- GET");
			   return vdcname;
				}
			 @RequestMapping(value="/getAvailableVdcLists/{dptId}", method = RequestMethod.GET)
				public @ResponseBody List<VdcMaster> getAvailableVdcLists(HttpSession session,@PathVariable int dptId) {
				 
				 User user= (User) session.getAttribute("userValue");
		    	  int cgID = user.getCg_id();
				 LOGGER.info("Inside getAvailableVdcLists()"+dptId);
				 List<VdcCgQuotaMap> vdcList = new ArrayList<VdcCgQuotaMap>();
				List<VdcMaster> vdcmasterList = new ArrayList<VdcMaster>();
				 vdcList = departmentService.getVdcListsFromVdcMaster(dptId,cgID);
				 
				 for(VdcCgQuotaMap vdccgQuotamap:vdcList)
				 {
					 VdcMaster vdcmas = new VdcMaster();
					 int vdc_id = vdccgQuotamap.getVdc_id();
					 String vdc_name = departmentService.getVdcNameByID(vdc_id);
					 vdcmas.setVdc_id(vdc_id);
					 vdcmas.setVdc_name(vdc_name);
					 vdcmasterList.add(vdcmas);
					 
				 }
				 return vdcmasterList;
				 
				} 
			 
			 
			 @RequestMapping(value="/getAvailabledDeptProj/{deptId}", method = RequestMethod.GET)
				public @ResponseBody List<Project> getOwnersInJSON(@PathVariable Integer deptId) {
		     
				 LOGGER.info("Inside getOwnersInJSON()- GET");
				 System.out.println("the deptid"+deptId);
				List<Project> projIds = departmentService.getProjectByDeptID(deptId);
				 return projIds;
				}
			 
			 @RequestMapping(value="/checkDeptNameAvailability", method = RequestMethod.POST)
				public @ResponseBody int geDept_name(@RequestParam("dept_name") String dept_name)
		       {
		   	  int check =0;
		   	 
		   	 System.out.println("====="+dept_name);
		   	    LOGGER.info("inside ajax()-");
		  	        
		   	    check = departmentService.geDept_name(dept_name);
		  	      
		   	    return check;
		   	 
		      }
			
			 
}





