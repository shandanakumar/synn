package com.syntel.isap.provisioning.dao;

import java.util.List;
import com.syntel.isap.provisioning.bean.Monitor;
import com.syntel.isap.provisioning.bean.Vdc;
import com.syntel.isap.provisioning.bean.VdcExt;
import com.syntel.isap.provisioning.bean.SCM;

public interface IVdcDao {

	int setOpenstack(Vdc openstackVdc);

	List<String> getKeystone(String keystoneEndpoint);
	
	List<String> getControllerEndpoint(String controllerEndpoint);

	int updateOpenstack(Vdc openstackVdc);

	void deleteOpenstack(Integer vdcId);
	
	List<SCM> getscm();

	int setscmpuppet(SCM scm);

	int setscmchef(SCM scm);

	List<Vdc> getvdc(int scmId);

	SCM editSCm(Integer scmId);

	void updateScm(SCM scm);

	void update(SCM scm);

	void deleteProject(Integer scmId);
	
	List<String> getScmEndpoint(String scmEndpoint);

	List<Monitor> getMonitorDetails();

	List<Vdc> getMonitorVdc(int monId);

	void deleteMonitor(Integer monId);

	Monitor editMon(Integer monId);

	void updateMonitor(Monitor monitor);

	int setMonNagio(Monitor monitor);

	int setMonZabbix(Monitor monitor);

	List<String> getMonEndpoint(String monEndpoint);

	List<String> getVdcName(String vdcName);

	List<String> getScmName(String scmName);

	List<String> getMonName(String monName);

	int getScmid(String scmName);

	void setMap(Vdc openstackVdc);

	int getMonid(String monName);

	void addOpenStackVdcExt(VdcExt vdc);

	List<Vdc> getVdcList();
	List<Vdc> getAzureVdcList() ;

	List<VdcExt> getVdcExtDetailsById(int vdcId);
	
	List<Vdc> getVdcEditList(Integer vdcId);
	

	void updateOpenStackVdcExt(VdcExt vdc);

	List<String> getSubnetAddress(String subnetAddress);

	Vdc getVdcExt(int vdcId);

	int setCred(Vdc openstackVdc);

	
}
