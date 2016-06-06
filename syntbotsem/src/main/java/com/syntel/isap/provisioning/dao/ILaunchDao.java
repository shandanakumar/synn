package com.syntel.isap.provisioning.dao;

import java.util.List;

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

/**
 * @author KK5007843
 *
 */
public interface ILaunchDao {
	void addCustomVMDetails(CustomVM customVm);

	void addCustomVMExt(CustomVMExt customVmExt);

	void addServiceRequest(ServiceReqMst reqMst);

	void addServiceRequestDts(ServiceReqDts reqDts);

	List<CustomVM> getCustomList(int userId);

	List<CustomVMExt> getCustomVMExtDetailsById(int customId);

	List<EnvironmentVM> getEnvVmDetails();

	List<EnvironmentVMExt> getVMExtParams();

	List<Identity> getKeyPairList(int usrId);

	void addKeyPair(Identity keypair);

	Identity getIdentity(int keyId);

	Identity checkKeyPair(String name);

	List<String> getKeyPairs(int usrId);

	void terminateInstance(int instId);

	EnvironmentVM getEnvVmDetailsByID(Integer envVMId);

	List<EnvironmentVMExt> getVMExtParamsByID(Integer envVMId);

	List<EnvironmentVDC> getEnvVdcList();

	List<EnvironmentVDC> getEnvVdcListById(int userid);

	String getOpenStackInstanceId(int instanceId);

	void updatePublicIP(int instId, String flaotingIpResponse);

	void updatePublicIPToNull(int id);

	List<VmProvision> getBespokeList(int userId);

	List<PackageAttr> getPackageAttrListByVmId(String vmId);

	void saveBespokevmProvision(VmProvision vmProvision);

	void saveBespokeExtParams(PackageAttr attr);

	List<PackageAttributes> getAttributesByPackageId(int packageId);

	void saveBespokevmProvisionPackages(VmProvisionPackages vmProvisionPackage);

	List<PackageAttr> getBespokeVMExtDetailsById(int customId);

	List<String> getPackagesByVmId(int vmId);

	String getPackageNameById(int packageId);

	List<String> getComponentsByVmId(int customId);

	VdcProjQuotaMap getProjectFreeQuotaDetails(int vdcId, int projId);

	VdcUserQuotaMap retrieveUserQuotaBean(int vdcId, int userId);

	void updateReducedProjFreeQouta(VdcProjQuotaMap vdcProjQuotaMapBean);

	void updateReducedUserFreeQuota(VdcUserQuotaMap vdcUserQuotaMapbean);

	OpenStackDetails getNamByVDCId(int vdcId);

	void updateInstanceIdDetails(String instance_Id_details, int customId);
	
	void updateFlavourValueInCustomProvById(int vmRam, int vmVcpu, int vmDisk,
			int customId);
	
	int getProjAdminId(int projId);

	CustomVM retrieveCustomVmProvById(int customVmId);
	
	String getProjOwnerName(int projId);

	VdcDeptQuotaMap getDeptFreeQuotaDetails(int dptId, int vdcId);

	void updateReducedDptFreeQuota(VdcDeptQuotaMap vdcDeptQuotaMap);

	int getVdcByUser(int adminUserId);

	void addAzureCustomVMDetails(CustomVM azureCustomVM);

	String getVdcNameById(int vdcId);

	int getVmCustomIdByVmName(CustomVM azureCustomVM);

	String getPackageVersionById(int packageId);
	
}
