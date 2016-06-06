package com.syntel.isap.provisioning.service;

import java.util.List;

import com.syntel.isap.provisioning.bean.Monitor;
import com.syntel.isap.provisioning.bean.Vdc;
import com.syntel.isap.provisioning.bean.SCM;

public interface IVdcService {

List<Vdc> getUserOpenstack();

void setOpenstack(Vdc openstackVdc);

int getKeystone(String keystonEndpoint);

int getControllerEndpoint(String controllerEndpoint);

Vdc getDetails(Integer vdcId);

void updateOpenstack(Vdc openstackVdc);

void deleteOpenstack(Integer vdcId);

List<SCM> getScm();

void setscmpuppet(SCM scm);

void setscmchef(SCM scm);

SCM editScm(Integer scmId);

void update(SCM scm);

void deleteProject(Integer scmId);

int getScmEndpoint(String scmEndpoint);

List<Monitor> getMonitorDetails();

void deleteMonitor(Integer monId);

void updateMonitor(Monitor monitor);

Monitor editMon(Integer monId);

void setMonNagio(Monitor monitor);

void setMonZabbix(Monitor monitor);

int getMonEndpoint(String monEndpoint);

int getVdcName(String vdcName);

int getScmName(String scmName);

int getMonName(String monName);

int getScmId(String scmName);

void setMap(Vdc openstackVdc);

int getMonId(String monName);

int checkConnection(String keystone_endpoint, String tenant, String user,
		String password );

int getSubnetAddress(String subnetAddress);

void getUsage(int vdcId);


List<Vdc> getAzureVdcList();	

}
