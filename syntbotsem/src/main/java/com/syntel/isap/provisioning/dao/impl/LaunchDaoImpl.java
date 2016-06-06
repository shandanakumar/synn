package com.syntel.isap.provisioning.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntel.isap.provisioning.bean.CustomVM;
import com.syntel.isap.provisioning.bean.CustomVMExt;
import com.syntel.isap.provisioning.bean.EnvironmentVDC;
import com.syntel.isap.provisioning.bean.EnvironmentVM;
import com.syntel.isap.provisioning.bean.EnvironmentVMExt;
import com.syntel.isap.provisioning.bean.Identity;
import com.syntel.isap.provisioning.bean.OpenStackDetails;
import com.syntel.isap.provisioning.bean.PackageAttr;
import com.syntel.isap.provisioning.bean.PackageAttributes;
import com.syntel.isap.provisioning.bean.ServiceReqDts;
import com.syntel.isap.provisioning.bean.ServiceReqMst;
import com.syntel.isap.provisioning.bean.VdcCgQuotaMap;
import com.syntel.isap.provisioning.bean.VdcDeptQuotaMap;
import com.syntel.isap.provisioning.bean.VdcProjQuotaMap;
import com.syntel.isap.provisioning.bean.VdcUserQuotaMap;
import com.syntel.isap.provisioning.bean.VmProvision;
import com.syntel.isap.provisioning.bean.VmProvisionPackages;
import com.syntel.isap.provisioning.dao.ILaunchDao;
import com.syntel.isap.provisioning.mapper.LaunchMapper;

/**
 * @author KK5007843
 *
 */
@Service("launchDao")
public class LaunchDaoImpl implements ILaunchDao {
	
    @Autowired
    private LaunchMapper launchMapper;
	
	public void addCustomVMDetails(CustomVM customVM) {
		launchMapper.addCustomVM(customVM);
	}

	public void addCustomVMExt(CustomVMExt cust) {
		launchMapper.addCustomVMExt(cust);
	}

	public void addServiceRequest(ServiceReqMst reqMst) {
		launchMapper.addServiceRequest(reqMst);
	}

	public void addServiceRequestDts(ServiceReqDts reqDts) {
		launchMapper.addServiceRequestDts(reqDts);
	}

	public List<CustomVM> getCustomList(int userId) {
		return launchMapper.getCustomList(userId);
	}

	public List<CustomVMExt> getCustomVMExtDetailsById(int customId) {
		return launchMapper.getCustomVMExtDetailsById(customId);
	}

	public List<EnvironmentVM> getEnvVmDetails() {
		 List<EnvironmentVM> envVMList=launchMapper.getEnvVmDetails();
		 return envVMList;
	}

	public List<EnvironmentVMExt> getVMExtParams() {
		 List<EnvironmentVMExt> envVMExtList=launchMapper.getVMExtParams();
		 return envVMExtList;
	}

	public List<Identity> getKeyPairList(int usrId) {
		return launchMapper.getKeyPairList(usrId);
	}

	public void addKeyPair(Identity keypair) {
		launchMapper.addKeyPair(keypair);
	}

	public Identity getIdentity(int keyId) {
		return launchMapper.getIdentity(keyId);
	}

	public Identity checkKeyPair(String name) {
		return launchMapper.checkKeyPair(name);
	}

	public List<String> getKeyPairs(int usrId) {
		return launchMapper.getKeyPairs(usrId);
	}

	public void terminateInstance(int instId) {
		launchMapper.terminateInstance(instId);
	}

	public EnvironmentVM getEnvVmDetailsByID(Integer envVMid) {
		EnvironmentVM envVM=launchMapper.getEnvVmDetailsByID(envVMid);
		return envVM;
	}

	public List<EnvironmentVMExt> getVMExtParamsByID(Integer envVMid) {
		List<EnvironmentVMExt> envVMExtDet=launchMapper.getVMExtParamsByID(envVMid);
		return envVMExtDet;
	}

	public List<EnvironmentVDC> getEnvVdcList() {
		List<EnvironmentVDC> envVDCList=launchMapper.getEnvVdcList();
		 return envVDCList;
	}

	public List<EnvironmentVDC> getEnvVdcListById(int userid){
		int deptId=launchMapper.getDeptbyUserId(userid);				
		int vdcId=launchMapper.getVdcBydeptId(deptId);		
		 List<EnvironmentVDC> envVdcList=null;
		 envVdcList = launchMapper.getVdcById(vdcId);	
		 return envVdcList;
	}

	public String getOpenStackInstanceId(int instanceId) {
		return launchMapper.getOpenStackInstanceId(instanceId);
	}

	public void updatePublicIP(int instId, String flaotingIpResponse) {
		launchMapper.updatePublicIP(instId,flaotingIpResponse);
	}

	public void updatePublicIPToNull(int instId) {
		launchMapper.updatePublicIPToNull(instId);
	}
	
	public List<PackageAttributes> getAttributesByPackageId(int packageId) {
		return launchMapper.getAttributesByPackageId(packageId);
	}

	public String getPackageNameById(int packageId) {
		return launchMapper.getPackageNameById(packageId);
	}

	public EnvironmentVM getvmMaster(String vmId) {
		return launchMapper.getvmMaster(vmId);
	}

	public void saveBespokevmProvision(VmProvision vmProvision) {
		launchMapper.saveBespokevmProvision(vmProvision);
	}

	public void saveBespokevmProvisionPackages(
			VmProvisionPackages vmProvisionPackage) {
		launchMapper.saveBespokevmProvisionPackages(vmProvisionPackage);
	}

	public List<PackageAttr> getPackageAttrListByVmId(String vmId) {
		return launchMapper.getPackageAttrListByVmId(vmId);
	}

	public void saveBespokeExtParams(PackageAttr attr) {
		launchMapper.saveBespokeExtParams(attr);
	}

	public List<VmProvision> getBespokeList(int userId) {
		return launchMapper.getBespokeList(userId);
	}

	public List<PackageAttr> getBespokeVMExtDetailsById(int customId) {
		return launchMapper.getBespokeVMExtDetailsById(customId);
	}
	
	public List<String> getPackagesByVmId(int vmId) {
		return launchMapper.getPackagesByVmId(vmId);
	}

	public List<String> getComponentsByVmId(int customId) {
		return launchMapper.getComponentsByVmId(customId);
	}

	public VdcProjQuotaMap getProjectFreeQuotaDetails(int vdcId, int projId) {
		
		return launchMapper.getProjectFreeQuotaDetails(vdcId,projId);
	}

	public VdcUserQuotaMap retrieveUserQuotaBean(int vdcId, int userId) {
		return launchMapper.retrieveUserQuotaBean(vdcId,userId);
	}

	public void updateReducedProjFreeQouta(VdcProjQuotaMap vdcProjQuotaMapBean) {
		launchMapper.updateReducedProjFreeQouta(vdcProjQuotaMapBean);
		
	}

	public void updateReducedUserFreeQuota(VdcUserQuotaMap vdcUserQuotaMapbean) {
		launchMapper.updateReducedUserFreeQuota(vdcUserQuotaMapbean);
		
	}

	public OpenStackDetails getNamByVDCId(int vdcId) {
		return launchMapper.getNamByVDCId(vdcId);
	}

	public void updateInstanceIdDetails(String instance_Id_details, int customId) {
		launchMapper.updateInstanceIdDetails(instance_Id_details, customId);
		
	}

	public String getProjOwnerName(int projId) {
		
		return launchMapper.getProjOwnerNameByProjId(projId);
	}

	public int getProjAdminId(int projId) {
		return launchMapper.getProjAdminId(projId);
	}

	public void updateFlavourValueInCustomProvById(int vmRam, int vmVcpu,
			int vmDisk, int customId) {
		launchMapper.updateFlavourValueInCustomProvById(vmRam,vmVcpu,vmDisk,customId);
		
	}
	
	public CustomVM retrieveCustomVmProvById(int customVmId) {
		return  launchMapper.retrieveCustomVmProvById(customVmId);
	}


	public VdcDeptQuotaMap getDeptFreeQuotaDetails(int dptId, int vdcId) {
		return launchMapper.getDeptFreeQuotaDetails(dptId,vdcId);
	}


	public void updateReducedDptFreeQuota(VdcDeptQuotaMap vdcDeptQuotaMap) {
		launchMapper.updateReducedDptFreeQuota(vdcDeptQuotaMap);
		
	}

	public int getVdcByUser(int adminUserId) {
		return launchMapper.getVdcByUser(adminUserId);
	}

	public void addAzureCustomVMDetails(CustomVM azureCustomVM) {
		
		try{
			launchMapper.addAzureCustomVMDetails(azureCustomVM);	
			}catch(Exception e){
				e.printStackTrace();
			}
			
	}

	public String getVdcNameById(int vdcId) {
		
		return launchMapper.getVdcNameById(vdcId);
	}

	public int getVmCustomIdByVmName(CustomVM azureCustomVM) {
		int vmCustomId = 0;
		try{
		vmCustomId= launchMapper.getVmCustomIdByVmName(azureCustomVM);
		}catch(Exception e){
			e.printStackTrace();
		}
		return vmCustomId;
	}

	public String getPackageVersionById(int packageId) {
		return launchMapper.getPackageVersionById(packageId);
	}


}
