package com.syntel.isap.provisioning.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.syntbots.azure.SyntBotsAzureWebServicesStub.CreateVMEP;
import com.syntel.isap.provisioning.bean.AzureDetails;
import com.syntel.isap.provisioning.bean.BespokePackages;
import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.EnvironmentVDC;
import com.syntel.isap.provisioning.bean.EnvironmentVM;
import com.syntel.isap.provisioning.bean.EnvironmentVMExt;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.ServiceReqMst;
import com.syntel.isap.provisioning.bean.User;
import com.syntel.isap.provisioning.bean.VMDetails;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VmProvision;

/**
 * @author KK5007843
 *
 */
public interface ILaunchService {
	
	List<CustomVM> getCustomListAndQuotaManage(int userId,int vdcId, int adminUserId, int cgId,
			int dptId, int projId) throws Exception;

	List<CustomVM> getCustomList(int userId)throws Exception;
	
	List<EnvironmentVM> getEnvVmDetails();

	List<EnvironmentVMExt> getVMExtParams();

	List<Identity> getKeyPairList(int usrId);

	void addKeyPair(String name, String keyPairPrivate, int usrId);

	Identity getIdentity(int keyId);

	int checkKeyPair(String name);

	List<String> getKeyPairs(int usrId);

	void terminateInstance(String instId, int projId, int vdcId,int AdminUserId);

	EnvironmentVM getEnvVmDetailsByID(Integer envVMId);

	List<EnvironmentVMExt> getVMExtParamsByID(Integer envVMId);

	List<EnvironmentVDC> getEnvVdcList();

	List<EnvironmentVDC> getEnvVdcListById(int userId);

	void addFloatingIP(String instId) throws RemoteException;

	void removeFloatingIP(String instId) throws RemoteException;

	void bespokeLaunch(String keypair, VmProvision vmProvision,
			User userSession, HttpServletRequest req, String vmId)
			throws JMSException, RemoteException;

	List<VmProvision> getBespokeList(int usrId) throws Exception;

	List<BespokePackages> getBespokeVMAttributes(Integer vmId);

	VdcProjQuotaMap getProjectFreeQuotaDetails(int vdcId, int projId);

	void customLaunch(String keypair, String image, String network,
			String security, String flavor, CustomVM customVM,
			ServiceReqMst serviceReqMst, int vdcId)throws JMSException, RemoteException;
	
	int getProjAdminId(int projId);

	int getVdcByUser(int usrId);

	void azureCustomLaunch(AzureDetails azureDetails,HttpSession session);
	
	void bespokeAzureLaunch( User userSession,
			AzureDetails azureDetails);
	
	VMDetails getVmDetailsFromService(Integer varVmId);

	void azureCstmLaunchDbupdate(String vmNames);

	void azureBespkLaunchDbupdate(String vmNames);

	void azureCstmVmShutDown(String shtdnCustVmName);
}
