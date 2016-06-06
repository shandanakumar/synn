package com.syntel.isap.provisioning.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syntel.isap.provisioning.bean.Monitor;
import com.syntel.isap.provisioning.bean.Vdc;
import com.syntel.isap.provisioning.bean.VdcExt;
import com.syntel.isap.provisioning.bean.SCM;
import com.syntel.isap.provisioning.dao.IVdcDao;
import com.syntel.isap.provisioning.mapper.VdcMapper;

@Service("registerdao")
public class VdcDaoImpl implements IVdcDao {

	@Autowired
	private VdcMapper registerMapper;

	public int setOpenstack(Vdc openstackVdc) {
		return registerMapper.setVdcMaster(openstackVdc);
	}

	public List<String> getKeystone(String keystoneEndpoint) {
		return registerMapper.getKeystone(keystoneEndpoint);
	}

	public int updateOpenstack(Vdc openstackVdc) {
		return registerMapper.updateOpenstackMaster(openstackVdc);
	}

	public void deleteOpenstack(Integer vdcId) {
		registerMapper.deleteOpenstack(vdcId);
	}

	public List<SCM> getscm() {
		List<SCM> list=new ArrayList<SCM>();
		list = registerMapper.getscm();
		return list;
	}

	public int setscmpuppet(SCM scm) {
		return registerMapper.setscmpuppet(scm);
	}

	public int setscmchef(SCM scm) {		
		return  registerMapper.setscmchef(scm);
	}

	public List<Vdc> getvdc(int scmId) {
		List<Vdc> vdc = new ArrayList<Vdc>();
		vdc=registerMapper.getvdc(scmId);
		return vdc;
	}
	
	public SCM editSCm(Integer scmId) {
		return registerMapper.editScm(scmId);
	}

	public void updateScm(SCM scm) {
		registerMapper.updateScm(scm);
	}

	public void update(SCM scm) {
		registerMapper.update(scm.getScm_id(), scm.getScm_name(),
				scm.getHost_name(), scm.getScm_endpoint(), scm.getAdmin_user(),
				scm.getAdmin_pass());
	}

	public void deleteProject(Integer scmId) {
		registerMapper.deleteProject(scmId);
	}

	public List<String> getVdcName(String vdcName) {
		return registerMapper.getVdcName(vdcName);
	}

	public List<String> getScmEndpoint(String scmEndpoint) {
		return registerMapper.getScmEndpoint(scmEndpoint);
	}

	public List<String> getControllerEndpoint(String controllerEndpoint) {
		return registerMapper.getControllerEndpoint(controllerEndpoint);
	}
	
	public List<Monitor> getMonitorDetails() {
		return registerMapper.getMonitorDetails();
	}

	public List<Vdc> getMonitorVdc(int monId) {
		return registerMapper.getMonitorVdc(monId);
	}

	public void deleteMonitor(Integer monId) {
		registerMapper.deleteMonitor(monId);
	}

	public Monitor editMon(Integer monId) {
		return registerMapper.editMon(monId);
	}

	public void updateMonitor(Monitor monitor) {
		registerMapper.updateMonitor(monitor);
	}

	public int setMonNagio(Monitor monitor) {
		return registerMapper.setMonNagio(monitor);
	}

	public int setMonZabbix(Monitor monitor) {
		return registerMapper.setMonZabbix(monitor);
	}

	public List<String> getMonEndpoint(String monEndpoint) {
		return registerMapper.getMonEndpoint(monEndpoint);
	}

	public List<String> getScmName(String scmName) {
		return registerMapper.getScmName(scmName);
	}

	public List<String> getMonName(String monName) {
		return registerMapper.getMonName(monName);
	}

	public int getScmid(String scmName) {
		return registerMapper.getScmid(scmName);
	}

	public void setMap(Vdc openstackVdc) {
		registerMapper.setMap(openstackVdc);
	}

	public int getMonid(String monName) {
		return registerMapper.getMonid(monName);
	}

	public void addOpenStackVdcExt(VdcExt vdc) {
		registerMapper.addOpenStackVdcExt(vdc);
	}

	public List<Vdc> getVdcList() {
		return registerMapper.getVdcList();
	}
	public List<Vdc> getAzureVdcList(Integer vdcId) {
		return registerMapper.getAzureVdcList();
	}

	public List<VdcExt> getVdcExtDetailsById(int vdcId) {
		return registerMapper.getVdcExtDetailsById(vdcId);
	}
	
	public List<Vdc> getVdcEditList(Integer vdcId) {
		return registerMapper.getVdcEditList(vdcId);
	}
	public List<Vdc> getAzureVdcList() {
		return registerMapper.getAzureVdcList();
	}
	
	
	public void updateOpenStackVdcExt(VdcExt vdc) {
		registerMapper.updateOpenStackVdcExt(vdc);
	}

	public List<String> getSubnetAddress(String subnetAddress) {
		return registerMapper.getSubnetAddress(subnetAddress);
	}

	public Vdc getVdcExt(int vdcId) {
		return registerMapper.getVdcExt(vdcId);
	}

	public int setCred(Vdc openstackVdc) {
		return registerMapper.setCred(openstackVdc);
	}

	

	
}
