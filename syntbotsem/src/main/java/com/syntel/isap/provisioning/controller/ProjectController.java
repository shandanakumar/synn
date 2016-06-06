package com.syntel.isap.provisioning.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.syntel.isap.provisioning.bean.Department;
import com.syntel.isap.provisioning.bean.Project;
import com.syntel.isap.provisioning.bean.User;


import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcMaster;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.service.IDepartmentService;
import com.syntel.isap.provisioning.service.IProjectService;

/**
 * @author SK5004144
 *
 */

@Controller
public class ProjectController {
	
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IDepartmentService departmentService;

	private static final Logger LOGGER = Logger.getLogger(ProjectController.class.getName());
	  
		  

	    /**
		 * param 
		 * @param @ModelAttribute("userLogin")		 
		 * @return model
		 */
	  
  @RequestMapping(value="/projects", method=RequestMethod.GET)
	public ModelAndView projectLists(HttpSession session) {
		LOGGER.info("Inside projectLists()- GET");
		String view = "department/projects";
		List<Project> projectList = new ArrayList<Project>();
		User user = (User) session.getAttribute("userValue");

		LOGGER.info("Inside ServiceImpl addDepartment" + user.getDpt_id());
		ModelAndView model = new ModelAndView();
		projectList = projectService.getprojectLists(user.getDpt_id());
		model.addObject("prolist", projectList);

		model.setViewName(view);
		return model;
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public ModelAndView inserProject(HttpSession session,
			@ModelAttribute(value = "user") User userData,
			@ModelAttribute(value = "project") Project project,
			HttpServletRequest request) {

		LOGGER.info("Inside inserProject()- POST");
		ModelAndView model = new ModelAndView();
		List<Project> projectList = new ArrayList<Project>();
		User userSession= (User) session.getAttribute("userValue");
		userData.setDpt_id(userSession.getDpt_id());
		/*String projectnames = userSession.getUsr_name();*/
		if (project != null) {

			User user = (User) session.getAttribute("userValue");
			project.setDpt_id(user.getDpt_id());
			project.setCg_id(user.getCg_id());

			
			LOGGER.info("Project owner name" + project.getOwner());
			Department department = new Department();
			department = departmentService.getDepartmentById(user.getDpt_id());
			
			LOGGER.info("Department name for the project"
					+ department.getDpt_name());
			projectService.insertData(project, userData.getUsr_id());
			projectList = projectService.getprojectLists(user.getDpt_id());
			String view = "department/projects";
			model.addObject("prolist", projectList);
			model.setViewName(view);
		}
		return model;
	}

	@RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
	public ModelAndView deleteProject(
			@RequestParam(value = "proj_id") Integer proj_id,
			HttpSession session) {
		
		LOGGER.info("Inside deleteProject()- GET");
		projectService.deleteProject(proj_id);
		List<Project> projectList = new ArrayList<Project>();
		LOGGER.info("Inside deleteProject()- GET");
		ModelAndView model = new ModelAndView();
		String view = "department/projects";
		User user = (User) session.getAttribute("userValue");
		projectList = projectService.getprojectLists(user.getDpt_id());
		model.addObject("prolist", projectList);
		model.setViewName(view);
		
		return model;

	}

	@RequestMapping(value = "/editProjectList", method = RequestMethod.GET)
	public ModelAndView editProject(
			@RequestParam(value = "proj_id") Integer proj_id, Project project,
			HttpSession session) {

		LOGGER.info("Inside editProject()- GET");
		project = projectService.editProject(proj_id);
        session.setAttribute("ProjectEditList", project);

		ModelAndView model = new ModelAndView();

		String view = "department/editProject";

		model.setViewName(view);
		return model;

	}

	@RequestMapping(value = "/editProjectDetails", method = RequestMethod.POST)
	public ModelAndView getEditProjectDetails(HttpSession session,
			@ModelAttribute("user") User user,
			@ModelAttribute(value = "project") Project project) {

		LOGGER.info("Inside getEditWorkflow()- GET");
		List<Project> projectList = new ArrayList<Project>();
		projectService.updateProject(project, user);
		ModelAndView model = new ModelAndView();
		String view = "department/projects";
		User userSession = (User) session.getAttribute("userValue");
		projectList = projectService.getprojectLists(userSession.getDpt_id());
		model.addObject("prolist", projectList);
		model.setViewName(view);
		
		return model;
	}

	/**
	 * param
	 * 
	 * @param @PathVariable deptId,session
	 * @return json
	 */


	@RequestMapping(value = "/allocateVdcToProject", method = RequestMethod.GET)
	public ModelAndView allocateVdcToProject(
			@ModelAttribute VdcProjQuotaMap vdcProjQuotaMapBean,
			HttpSession session) {
		List<Project> projectList = new ArrayList<Project>();
	
		User user = (User) session.getAttribute("userValue");
		
		int dptID = user.getDpt_id();
		int projID = vdcProjQuotaMapBean.getProj_id();
		LOGGER.info("Inside deleteDepartment()- GET");
		String view = "department/projectQuota";
		List<VdcProjQuotaMap> vdcprojQuotaMapBeanList = new ArrayList<VdcProjQuotaMap>();
		
		ModelAndView model = new ModelAndView();
		vdcProjQuotaMapBean.setDpt_id(dptID);
		vdcProjQuotaMapBean.setFree_mem(vdcProjQuotaMapBean.getTotal_mem());
		vdcProjQuotaMapBean.setFree_vcpu(vdcProjQuotaMapBean.getTotal_vcpu());
		vdcProjQuotaMapBean.setFree_disk_storage(vdcProjQuotaMapBean.getTotal_disk_storage());
		vdcProjQuotaMapBean.setCreated_by(user.getUsr_name());
		
		projectService.allocateVdcToProjectGroup(vdcProjQuotaMapBean, dptID);
		projectService.updateInUserQuotaTableGroup(vdcProjQuotaMapBean, projID,session);
		vdcprojQuotaMapBeanList = projectService.getRowsFromProjQuotaMapTable(dptID);
		
		projectList = projectService.getprojectLists(user.getDpt_id());
		model.addObject("vdcprojQuotaMapBeanList",vdcprojQuotaMapBeanList);
		model.addObject("prolist", projectList);
		model.setViewName(view);
		
		return model;

	}
	
	

    @RequestMapping(value ="/editVdcToProject" ,method=RequestMethod.GET)
	 public ModelAndView editVdcToProject(@ModelAttribute VdcProjQuotaMap VdcProjQuotaMapBean,HttpSession session) {
    	User user = (User) session.getAttribute("userValue");
		int dptID = user.getDpt_id();
		String view = "department/projectQuota";
		ModelAndView model = new ModelAndView();
		List<String> projectNames = new ArrayList<String>();
		List<VdcProjQuotaMap> vdcprojQuotaMapBeanList = new ArrayList<VdcProjQuotaMap>();
		projectService.editVdcToProject(VdcProjQuotaMapBean,dptID);
		vdcprojQuotaMapBeanList = projectService.getRowsFromProjQuotaMapTable(dptID);
		model.addObject("vdcprojQuotaMapBeanList", vdcprojQuotaMapBeanList);
	    model.addObject("ProjectNames", projectNames);
		model.setViewName(view);
	      return model;
      }

	@RequestMapping(value = "/projectQuota", method = RequestMethod.GET)
	public ModelAndView listpjQuotaDetails(HttpSession session) {

		LOGGER.info("Inside projectQuota GET");
		User user = (User) session.getAttribute("userValue");
		int dptID = user.getDpt_id();
		String view = "department/projectQuota";
		ModelAndView model = new ModelAndView();
		List<String> projectNames = new ArrayList<String>();
		List<VdcProjQuotaMap> vdcprojQuotaMapBeanList = new ArrayList<VdcProjQuotaMap>();
		vdcprojQuotaMapBeanList = projectService.getRowsFromProjQuotaMapTable(dptID);
		model.addObject("vdcprojQuotaMapBeanList", vdcprojQuotaMapBeanList);
		model.addObject("ProjectNames", projectNames);
		model.setViewName(view);
		
		return model;
	}

	

	@RequestMapping(value = "/deleteProjectQuota", method = RequestMethod.GET)
	public ModelAndView deleteProjectQuota(
			@RequestParam("proj_id") Integer proj_id,
			@RequestParam("vdc_id") Integer vdc_id, HttpSession session) {
		String view = "department/projectQuota";
		ModelAndView model = new ModelAndView();
		User user = (User) session.getAttribute("userValue");
		int dptID = user.getDpt_id();
		LOGGER.info("Inside deleteProjectQuota()- GET");
		LOGGER.info("VDC ID from for Deleteing the quota " + vdc_id);
		List<String> projectNames = new ArrayList<String>();
		List<VdcProjQuotaMap> vdcprojQuotaMapBeanList = new ArrayList<VdcProjQuotaMap>();
				VdcProjQuotaMap VdcProjQuotaMap = new VdcProjQuotaMap();
		VdcProjQuotaMap = projectService.getVdcdetailsByProjID(proj_id, vdc_id);
		projectService.updateFreememoryInDptquota(VdcProjQuotaMap, proj_id);
		projectService.deleteProjectQuota(proj_id, vdc_id);
		projectService.deleteQuotaInUserQuota(proj_id,vdc_id,session);
		vdcprojQuotaMapBeanList = projectService.getRowsFromProjQuotaMapTable(dptID);
		model.addObject("vdcprojQuotaMapBeanList", vdcprojQuotaMapBeanList);
		model.addObject("ProjectNames", projectNames);
		model.setViewName(view);
		
		return model;
	}
	
	
	

	@RequestMapping(value = "/getAvailableVdcListforProject/{projId}", method = RequestMethod.GET)
	public @ResponseBody
	List<VdcMaster> getAvailableVdcListforProject(HttpSession session,
			@PathVariable int projId) {

		User user = (User) session.getAttribute("userValue");
		int dptID = user.getDpt_id();
		LOGGER.info("Inside getAvailableVdcLists()" + projId);
		List<VdcDeptQuotaMap> vdcList = new ArrayList<VdcDeptQuotaMap>();
		List<VdcMaster> vdcmasterList = new ArrayList<VdcMaster>();
		vdcList = projectService.getVdcFromVdcMaster(projId, dptID);

		for (VdcDeptQuotaMap vdcdptQuotamap : vdcList) {
			VdcMaster vdcmas = new VdcMaster();
			int vdc_id = vdcdptQuotamap.getVdc_id();
			String vdc_name = projectService.getVdcNamesByID(vdc_id);
			vdcmas.setVdc_id(vdc_id);
			vdcmas.setVdc_name(vdc_name);
			vdcmasterList.add(vdcmas);

		}
		return vdcmasterList;

	}
	
	@RequestMapping(value = "/checkallocatedmemoryforprojects", method = RequestMethod.POST)
	public @ResponseBody int getallocated_memory(@RequestParam("total_dpt_mem") int free_mem,
			@RequestParam("selectedVdcId") int selectedVdcId,
			HttpSession session) {
		
		int check = 0;
		User user = (User) session.getAttribute("userValue");
		int dptID = user.getDpt_id();
		LOGGER.info("inside ajax()-");
		check = projectService.getfree_memory(free_mem, dptID, selectedVdcId);

		return check;

	}

	@RequestMapping(value = "/checkallocatedvcpuforprojects", method = RequestMethod.POST)
	public @ResponseBody int getallocated_vcpuprojects(@RequestParam("total_dpt_vcpu") int free_vcpu,
			HttpSession session,
			@RequestParam("selectedVdcId") int selectedVdcId) {
		int check = 0;
		User user = (User) session.getAttribute("userValue");
		int dptID = user.getDpt_id();
		LOGGER.info("inside ajax()-");
		check = projectService.getfree_vcpuprojects(free_vcpu, dptID,selectedVdcId);
		
		return check;
	}

	@RequestMapping(value = "/checkallocateddiskstorageforprojects", method = RequestMethod.POST)
	public @ResponseBody int getallocated_diskstorage(
			@RequestParam("total_dpt_disk_storage") int free_disk_storage,
			@RequestParam("selectedVdcId") int selectedVdcId,
			HttpSession session) {
		int check = 0;
		User user = (User) session.getAttribute("userValue");
		int dptID = user.getDpt_id();
		LOGGER.info("inside ajax()-");
		check = projectService.getfree_diskstorage(free_disk_storage, dptID,selectedVdcId);

		return check;

	}
	
	@RequestMapping(value="/getAllocatedDetailspj/{projid}/{VdcId}", method = RequestMethod.GET)
	public @ResponseBody VdcProjQuotaMap getAllocatedDetailsInJSON(@PathVariable Integer projid,@PathVariable Integer VdcId) {
		VdcProjQuotaMap VdcProjQuotaMap = new VdcProjQuotaMap();
		LOGGER.info("Inside getAllocatedDetailsInJSON()- GET");
		VdcProjQuotaMap = projectService.getAllocatedDetails(projid, VdcId);

    return VdcProjQuotaMap;
	}
	
	
	 
    @RequestMapping(value="/getVdcNames/{selectedVdcId}", method = RequestMethod.GET)
	public @ResponseBody VdcMaster getVdcNames(@PathVariable Integer selectedVdcId) {
   	 VdcMaster vdcname = departmentService.getVdcName(selectedVdcId);
		 LOGGER.info("Inside getVdcNameInJSON()- GET");
	   return vdcname;
		}
    
    
    @RequestMapping(value="/getVdcDetailsFromDeptTable/{selectedVdcId}/{dptId}", method = RequestMethod.GET)
	public @ResponseBody VdcProjQuotaMap getVdcDetailsFromService(HttpSession session,@PathVariable int selectedVdcId,@PathVariable int dptId) {
		
    	VdcProjQuotaMap VdcProjQuotaMap = new  VdcProjQuotaMap();
    	VdcProjQuotaMap = projectService.vdcProjQuotaByVdcIDanddptID(selectedVdcId,dptId);
		 return VdcProjQuotaMap;
		}

	@RequestMapping(value = "/getProjectName/{projId}", method = RequestMethod.GET)
	public @ResponseBody Project getProjectNameInJSON(@PathVariable Integer projId) {
		Project project = new Project();
		LOGGER.info("Inside getProjectNameInJSON()- GET");
		project = projectService.getProjectName(projId);
		LOGGER.info("project Name from getProjectName() -"+ project.getProj_name());
		
		return project;
	}

	@RequestMapping(value = "/getProject/{projId}", method = RequestMethod.GET)
	public @ResponseBody Project getProjectInJSON(@PathVariable Integer projId) {
		LOGGER.info("Inside getProjectInJSON()- GET");
		Project project = new Project();
		project = projectService.editProject(projId);
				
		return project;
	}

	@RequestMapping(value = "/getOwnersofProject/{userName}", method = RequestMethod.GET)
	public @ResponseBody User getOwnersOwnersofProjectInJSON(@PathVariable String userName,
			HttpSession session) {
		User user = new User();
		user = projectService.getOwnerByName(userName);
		
		return user;

	}

	/**
	 * param
	 * 
	 * @param session
	 * @return json
	 */
	@RequestMapping(value = "/getDeptUsersList", method = RequestMethod.GET)
	public @ResponseBody List<User> getDeptUsersListInJSON(HttpSession session) {
		User user = (User) session.getAttribute("userValue");
		LOGGER.info("Inside user getDeptUsers Session Values" + user.getCg_id());
		List<User> usersList = projectService.getUsersByCorporate(user);

		return usersList;
	}



	@RequestMapping(value = "/getVdcDetailsFromDepartmentTable/{selectedVdcId}/{dptId}", method = RequestMethod.GET)
	public @ResponseBody VdcDeptQuotaMap getVdcDetailsFromServices(HttpSession session,
			@PathVariable int selectedVdcId, @PathVariable int dptId) {
		VdcDeptQuotaMap vdcDeptQuotaMap = new VdcDeptQuotaMap();
		vdcDeptQuotaMap = projectService.vdcCgQuotaByVdcIDanddptID(selectedVdcId, dptId);

		return vdcDeptQuotaMap;
	}
	
	
	
	

 


	@RequestMapping(value = "/checkVdcAllocated", method = RequestMethod.POST)
	public @ResponseBody int getVDCdetails(@RequestParam("selectedVdcId") int selectedVdcId,
			@RequestParam("projId") int projId) {

		LOGGER.info("inside ajax() of checkVDCAllocated");
		int check = 0;
		LOGGER.info("inside ajax()");
		check = projectService.getVdcdetails(selectedVdcId, projId);
		return check;

	}
	
	
	@RequestMapping(value="/getAvailableProjUser/{projid}", method = RequestMethod.GET)
	public @ResponseBody List<User> getAvailableProjUser(@PathVariable Integer projid) {
   	
		 LOGGER.info("Inside getVdcNameInJSON()- GET");
		 System.out.println(("the project id is"+projid));
		 List<User> userIds = projectService.getUserByProjID(projid);
	   return userIds;
		}
	
	

    @RequestMapping(value="/checkProjNameAvailability", method = RequestMethod.POST)
		public @ResponseBody int getUsr_name(@RequestParam("proj_name") String proj_name)
       {
   	  int check =0;
   	 
   	 System.out.println("====="+proj_name);
   	    LOGGER.info("inside ajax()-");
  	        
   	    check = projectService.getProj_name(proj_name);
  	      
   	    return check;
   	 
      }
}

