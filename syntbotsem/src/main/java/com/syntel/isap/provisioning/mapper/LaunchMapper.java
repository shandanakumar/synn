package com.syntel.isap.provisioning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
public interface LaunchMapper {
	@Insert("INSERT INTO isap_env_vm_custom_prov "
			+ "(vm_name,startDate,endDate,service_req_dts_id,service_req_id,isap_env_vdc_master_vdc_id,status,usr_id) VALUES "
			+ "(#{vm_name},#{startDate},#{endDate},#{service_req_dts_id},#{service_req_id}"
			+ ",#{isap_env_vdc_master_vdc_id},#{status},#{usr_id})")
	@Options(useGeneratedKeys = true, keyProperty = "vm_custom_id")
	void addCustomVM(CustomVM customVM);
	



	@Insert("INSERT INTO isap_env_vm_custom_prov_ext "
			+ "(param_name,param_val,isap_env_vm_custom_prov_vm_custom_id) VALUES "
			+ "(#{param_name},#{param_val},#{isap_env_vm_custom_prov_vm_custom_id})")
	@Options(useGeneratedKeys = true, keyProperty = "isap_env_vm_custom_prov_ext_id")
	void addCustomVMExt(CustomVMExt cust);

	@Insert("INSERT INTO isap_cmn_service_req_mst "
			+ "(service_req_name,service_desc,status,service_req_flow_id) VALUES "
			+ "(#{service_req_name},#{service_desc},#{status},#{service_req_flow_id})")
	@Options(useGeneratedKeys = true, keyProperty = "service_req_id")
	void addServiceRequest(ServiceReqMst reqMst);

	@Insert("INSERT INTO isap_cmn_service_req_dts "
			+ "(req_user_id,service_req_id) VALUES "
			+ "(#{req_user_id},#{service_req_id})")
	@Options(useGeneratedKeys = true, keyProperty = "service_req_dts_id")
	void addServiceRequestDts(ServiceReqDts reqDts);

	@Select("SELECT * FROM isap_env_vm_custom_prov where usr_id=#{userId} AND delete_flag=0 order by vm_custom_id  desc")
	List<CustomVM> getCustomList(int userId);

	@Select("SELECT * FROM isap_env_vm_custom_prov_ext where isap_env_vm_custom_prov_vm_custom_id= #{customId}")
	List<CustomVMExt> getCustomVMExtDetailsById(int customId);

	@Select("SELECT * FROM isap_env_vm_mst")
	List<EnvironmentVM> getEnvVmDetails();

	@Select("SELECT * FROM isap_env_vm_mst_ext")
	List<EnvironmentVMExt> getVMExtParams();

	@Select("SELECT * FROM isap_env_identity where usr_id=#{usrId}")
	List<Identity> getKeyPairList(int usrId);

	@Insert("INSERT INTO isap_env_identity" + "(name,content,usr_id) VALUES "
			+ "(#{name},#{content},#{usr_id})")
	@Options(useGeneratedKeys = true, keyProperty = "identity_id")
	void addKeyPair(Identity keypair);

	@Select("SELECT * FROM isap_env_identity where identity_id=#{keyId}")
	Identity getIdentity(int keyId);

	@Select("SELECT * FROM isap_env_identity where name=#{name}")
	Identity checkKeyPair(String name);

	@Select("SELECT name FROM isap_env_identity where usr_id=#{usrId}")
	List<String> getKeyPairs(int usrId);

	@Update("UPDATE  isap_env_vm_custom_prov SET  delete_flag=1 where vm_custom_id=#{instId}")
	void terminateInstance(@Param("instId") int instId);

	@Select("SELECT * FROM isap_env_vm_mst where vm_master_id= #{envVMId}")
	EnvironmentVM getEnvVmDetailsByID(Integer envVMId);

	@Select("SELECT * FROM isap_env_vm_mst_ext where vm_master_id= #{envVMId}")
	List<EnvironmentVMExt> getVMExtParamsByID(Integer envVMId);

	@Select("SELECT * FROM isap_env_vdc_master")
	List<EnvironmentVDC> getEnvVdcList();

	@Select("Select dpt_id FROM isap_cmn_users WHERE usr_id = #{userId}")
	int getDeptbyUserId(int userId);

	@Select("Select vdc_id FROM isap_env_vdc_dpt_quota_map WHERE dpt_id = #{deptId}")
	int getVdcBydeptId(int deptId);

	@Select("Select * FROM isap_env_vdc_master WHERE vdc_id = #{vdcId}")
	List<EnvironmentVDC> getVdcById(int vdcId);

	@Select("SELECT instance_id from  isap_env_vm_custom_prov WHERE  vm_custom_id=#{instanceId}")
	String getOpenStackInstanceId(int instanceId);

	@Update("UPDATE isap_env_vm_custom_prov SET public_ip_addr=#{floatingIpResponse} where vm_custom_id=#{instId}")
	void updatePublicIP(@Param("instId") int instId,
			@Param("floatingIpResponse") String floatingIpResponse);

	@Update("UPDATE isap_env_vm_custom_prov SET public_ip_addr='-' where vm_custom_id=#{instId}")
	void updatePublicIPToNull(int instId);

	@Select("Select param_val FROM isap_env_vm_mst_ext WHERE param_name='packageId' AND vm_master_id = #{vmId}")
	List<String> getPackagesByVmId(int vmId);

	@Select("Select * FROM isap_env_scm_pkg_attr WHERE pkg_id = #{packageId}")
	List<PackageAttributes> getAttributesByPackageId(int packageId);

	@Select("Select pkg_name FROM isap_env_scm_pkg WHERE pkg_id = #{packageId}")
	String getPackageNameById(int packageId);

	@Select("SELECT * FROM isap_env_vm_mst where vm_master_id= #{vmId}")
	EnvironmentVM getvmMaster(String vmId);

	@Insert("INSERT INTO isap_env_vm_prov"
			+ "(vm_name,status,user_id,proj_id,dpt_id,"
			+ "cg_id,vm_master_id,firewall_master_id,req_id,vdc_id,"
			+ "instance_id,ip_addr,public_ip_addr,startDate,endDate) VALUES "
			+ "(#{vm_name},#{status},#{user_id},#{proj_id},#{dpt_id},#{cg_id},"
			+ "#{vm_master_id},#{firewall_master_id},#{req_id},#{vdc_id},"
			+ "#{instance_id},#{ip_addr},#{public_ip_addr},#{startDate},#{endDate})")
	@Options(useGeneratedKeys = true, keyProperty = "vm_id")
	void saveBespokevmProvision(VmProvision vmProvision);

	@Insert("INSERT INTO isap_env_vm_prov_pkgs"
			+ "(vm_id,pkg_id,param_name,param_value,required) VALUES "
			+ "(#{vm_id},#{pkg_id},#{param_name},#{param_value},#{required})")
	void saveBespokevmProvisionPackages(VmProvisionPackages vmProvisionPackage);

	@Select("Select * FROM isap_env_vm_mst_ext WHERE  vm_master_id = #{vmId}")
	List<PackageAttr> getPackageAttrListByVmId(String vmId);

	@Insert("INSERT INTO isap_env_vm_prov_ext "
			+ "(param_name,param_val,vm_id) VALUES "
			+ "(#{param_name},#{param_val},#{vm_id})")
	void saveBespokeExtParams(PackageAttr attr);

	//@Select("SELECT * FROM isap_env_vm_prov where user_id=#{userId} AND delete_flag=0  order by vm_id  desc")
	@Select("SELECT vmprov.vm_id,vmprov.vm_name,vmprov.status,vmprov.user_id,vmprov.proj_id,vmprov.dpt_id,cg_id,"
			+ "vmprov.vm_master_id,vmprov.firewall_master_id,vmprov.req_id,vmprov.created_at,vmprov.deleted_at,vmprov.modified_at,"
			+ "vmprov.created_by,vmprov.modified_by,vmprov.deleted_by,vmprov.vdc_id,vmprov.public_ip_addr,vmprov.ip_addr,"
			+ "vmprov.instance_id,vmprov.startDate,vmprov.endDate,vmprov.delete_flag,vdcmaster.vdc_name "
			+ "FROM isap_env_vm_prov vmprov JOIN isap_env_vdc_master vdcmaster ON vmprov.vdc_id=vdcmaster.vdc_id "
			+ "WHERE vmprov.user_id=#{userId} AND vmprov.delete_flag=0  ORDER BY vmprov.vm_id  DESC")
	List<VmProvision> getBespokeList(int userId);

	@Select("SELECT * FROM isap_env_vm_prov_ext where vm_id=#{customId}")
	List<PackageAttr> getBespokeVMExtDetailsById(int customId);

	@Select("SELECT DISTINCT t.pkg_name from isap_env_scm_pkg t JOIN isap_env_vm_prov_pkgs p ON p.pkg_id=t.pkg_id AND vm_id=#{customId}")
	List<String> getComponentsByVmId(int customId);

	@Select("SELECT * FROM isap_env_vdc_proj_quota_map WHERE vdc_id = #{vdcId} AND proj_id = #{projId}")
	VdcProjQuotaMap getProjectFreeQuotaDetails(@Param("vdcId")int vdcId,@Param("projId") int projId);

	@Select("SELECT * FROM isap_env_vdc_usr_quota_map WHERE vdc_id = #{vdcId} AND usr_Id = #{userId}")
	VdcUserQuotaMap retrieveUserQuotaBean(@Param("vdcId")int vdcId,@Param("userId")int userId);

	@Update("UPDATE isap_env_vdc_proj_quota_map SET free_mem=#{free_mem},free_vcpu=#{free_vcpu}, free_disk_storage=#{free_disk_storage} where proj_id=#{proj_id} AND vdc_id=#{vdc_id}")
	void updateReducedProjFreeQouta(VdcProjQuotaMap vdcProjQuotaMapBean);

	@Update("UPDATE isap_env_vdc_usr_quota_map SET free_mem=#{free_mem},free_vcpu=#{free_vcpu}, free_disk_storage=#{free_disk_storage} where usr_id=#{usr_id} AND vdc_id=#{vdc_id}")
	void updateReducedUserFreeQuota(VdcUserQuotaMap vdcUserQuotaMapbean);
	
	@Select("SELECT * FROM isap_env_vm_prov_cred WHERE vdc_id = #{vdcId}")
	
	OpenStackDetails getNamByVDCId(int vdcId);


	
	@Update("UPDATE  isap_env_vm_custom_prov SET  instance_id=#{instance_Id_details} where vm_custom_id=#{vm_custom_id}")
	void updateInstanceIdDetails(@Param("instance_Id_details") String instance_Id_details,@Param("vm_custom_id") int vm_custom_id);
	
	

	@Select("Select owner FROM isap_cmn_projects WHERE proj_id = #{projId}")
	String getProjOwnerNameByProjId(@Param("projId")int projId);



	@Select("Select owner FROM isap_cmn_projects WHERE proj_id = #{projId}")
	int getProjAdminId(@Param("projId")int projId);
	

	@Update("UPDATE isap_env_vm_custom_prov SET mem=#{vmRam},cpu=#{vmVcpu},hdd=#{vmDisk} WHERE vm_custom_id = #{customVmId} ")
	void updateFlavourValueInCustomProvById(@Param("vmRam")int vmRam,@Param("vmVcpu") int vmVcpu,@Param("vmDisk") int vmDisk,
			@Param("customVmId")int customId);

	
	@Select("SELECT * FROM isap_env_vm_custom_prov WHERE vm_custom_id = #{customVmId} ")
	CustomVM retrieveCustomVmProvById(@Param("customVmId")int customVmId);


	@Select("SELECT * FROM isap_env_vdc_dpt_quota_map WHERE dpt_id = #{dptId} AND vdc_id = #{vdcId}  ")

	VdcDeptQuotaMap getDeptFreeQuotaDetails(int dptId, int vdcId);


	@Update("UPDATE isap_env_vdc_dpt_quota_map SET free_mem=#{free_mem},free_vcpu=#{free_vcpu}, free_disk_storage=#{free_disk_storage} where dpt_id=#{dpt_id} AND vdc_id=#{vdc_id}")

	void updateReducedDptFreeQuota(VdcDeptQuotaMap vdcDeptQuotaMap);

	@Select("SELECT vdc_id FROM isap_env_vdc_usr_quota_map WHERE usr_id = #{adminUserId}")
	int getVdcByUser(int adminUserId);
	
	@Insert("INSERT INTO isap_env_vm_custom_prov (vm_name,hostname,ip_addr,"
			+ "public_ip_addr,service_req_dts_id,service_req_id,isap_env_vdc_master_vdc_id,status,startDate,endDate,"
			+ "delete_flag,instance_id,mem,cpu,hdd,vdc_provider,created_at,usr_id,created_by) VALUES (#{vm_name},#{hostname},#{ip_addr},"
			+ "#{public_ip_addr},#{service_req_dts_id},#{service_req_id},#{isap_env_vdc_master_vdc_id},#{status},#{startDate},#{endDate},"
			+ "#{delete_flag},#{instance_id},#{mem},#{cpu},#{hdd},#{vdc_provider},#{createdAt},#{usr_id},#{createdBy})")
	void addAzureCustomVMDetails(CustomVM azureCustomVM);



	@Select("SELECT vdc_name FROM isap_env_vdc_master WHERE vdc_id = #{vdcId}")
	String getVdcNameById(int vdcId);



	@Select("SELECT vm_custom_id FROM isap_env_vm_custom_prov WHERE vm_name =#{vm_name}")
	int getVmCustomIdByVmName(CustomVM azureCustomVM);



	@Select("Select pkg_version FROM isap_env_scm_pkg WHERE pkg_id = #{packageId}")
	String getPackageVersionById(int packageId);
	
	
}