package com.syntel.isap.provisioning.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.syntel.isap.provisioning.bean.Monitor;
import com.syntel.isap.provisioning.bean.Vdc;
import com.syntel.isap.provisioning.bean.VdcExt;
import com.syntel.isap.provisioning.bean.SCM;

public interface VdcMapper {

       @Insert("INSERT INTO isap_env_vdc_master (vdc_name,vdc_provider,vdc_location) VALUES "
    		+ "(#{vdc_name},'Openstack',#{vdc_location})")
       @Options(useGeneratedKeys = true, keyProperty = "vdc_id")
	   int setVdcMaster(Vdc openstackVdc);
 
        @Insert("INSERT INTO isap_env_vm_prov_cred(cred_id,vdc_id,type,vm_id,isap_env_identity_identity_id,username,password) "
       		+ "VALUES "
    		+ "(1,#{vdc_id},'Openstack',1,3,#{user},#{password})")
	    int setCred(Vdc openstackVdc); 

        @Select("SELECT param_val FROM isap_env_vdc_ext e JOIN isap_env_vdc_master m ON m.vdc_id=e.vdc_id  WHERE m.delete_flag=0 AND param_name='keystoneEndpoint' and param_val=#{keystoneEndpoint}")
	    List<String> getKeystone(String keystoneEndpoint);

        @Update("UPDATE  isap_env_vdc_master  SET vdc_name=#{vdc_name},vdc_status=#{vdc_status},vdc_location=#{vdc_location},updated_at=CURRENT_TIMESTAMP  WHERE vdc_id=#{vdc_id}")
	    int updateOpenstackMaster(Vdc openstackVdc);

	    @Update("UPDATE isap_env_vdc_master  SET delete_flag=1,deleted_at=CURRENT_TIMESTAMP WHERE vdc_id=#{vdcId}") 
	    void deleteOpenstack(Integer vdcId);

	    @Select("SELECT scm_id,scm_name,scm_type,host_name FROM isap_env_scm_mst where delete_flag=0")
		List<SCM> getscm();

	    @Insert("INSERT INTO isap_env_scm_mst (scm_name,scm_type,host_name,scm_endpoint,admin_user,admin_pass) VALUES (#{scm_name},'puppet',#{host_name},#{scm_endpoint},#{admin_user},#{admin_pass})")
	    int setscmpuppet(SCM scm);

	    @Insert("INSERT INTO isap_env_scm_mst (scm_name,scm_type,host_name,scm_endpoint,admin_user,admin_pass) VALUES (#{scm_name},'chef',#{host_name},#{scm_endpoint},#{admin_user},#{admin_pass})")
	    int setscmchef(SCM scm);

	    @Select("SELECT vdc_name,p.scm_id FROM isap_env_vdc_master m JOIN isap_env_vdc_scm_map p ON m.vdc_id=p.vdc_id JOIN isap_env_scm_mst t ON t.scm_id=p.scm_id WHERE p.scm_id=#{scmId} AND m.delete_flag=0")
	    List<Vdc> getvdc(int scmId);

	    @Select("SELECT scm_name,host_name,scm_endpoint,admin_user,admin_pass FROM isap_env_scm_mst WHERE scm_id=#{scmId}")
		SCM editScm(Integer scmId);
      
	    @Update("UPDATE isap_env_scm_mst SET scm_name =#{scm_name},scm_endpoint=#{scm_endpoint},admin_user=#{admin_user},admin_pass=#{admin_pass} WHERE scm_id=#{scm_id}")
		void updateScm(SCM scm);

	    @Update("UPDATE isap_env_scm_mst SET scm_name =#{scmName},host_name=#{hostName},scm_endpoint=#{scmEndpoint},admin_user=#{adminUser},admin_pass=#{adminPass} WHERE scm_id=#{scmId}")
	    void update(@Param("scmId") int scmId, @Param("scmName") String scmName,@Param("hostName") String hostName,@Param("scmEndpoint") String scmEndpoint,
			@Param("adminUser") String adminUser,@Param("adminPass")  String adminPass);

	    @Update("UPDATE  isap_env_scm_mst  SET delete_flag=1 WHERE scm_id=#{scmId}")
	    void deleteProject(Integer scmId);
	    	    
	    @Select("SELECT scm_endpoint FROM isap_env_scm_mst WHERE scm_endpoint=#{scm_endpoint}")
	    List<String> getScmEndpoint(String scmEndpoint);

	    @Select("SELECT mon_id,mon_name,mon_type,host_name FROM  isap_env_mon_mst where delete_flag=0")
	    List<Monitor> getMonitorDetails();
  
	    @Select("SELECT  DISTINCT vdc_name ,p.mon_id FROM isap_env_vdc_master m JOIN isap_env_vdc_scm_map p ON m.vdc_id=p.vdc_id JOIN isap_env_mon_mst t ON t.mon_id=p.mon_id WHERE p.mon_id=#{monId} AND m.delete_flag=0")
	    List<Vdc> getMonitorVdc(int monId);

	    @Update("UPDATE  isap_env_mon_mst  SET delete_flag=1 WHERE mon_id=#{monId}")
	    void deleteMonitor(Integer monId);

	    @Select("SELECT mon_name,mon_endpoint,host_name,admin_user,admin_pass FROM isap_env_mon_mst WHERE mon_id=#{monId}")
	    Monitor editMon(Integer monId);

	    @Update("UPDATE isap_env_mon_mst SET mon_name =#{mon_name},mon_endpoint=#{mon_endpoint},host_name=#{host_name},admin_user=#{admin_user},admin_pass=#{admin_pass} WHERE mon_id=#{mon_id}")
	    void updateMonitor(Monitor monitor);

	    @Insert("INSERT INTO isap_env_mon_mst (mon_name,mon_type,host_name,mon_endpoint,admin_user,admin_pass) VALUES (#{mon_name},'Nagios',#{host_name},#{mon_endpoint},#{admin_user},#{admin_pass})")
	    int setMonNagio(Monitor monitor);

	    @Insert("INSERT INTO isap_env_mon_mst (mon_name,mon_type,host_name,mon_endpoint,admin_user,admin_pass) VALUES (#{mon_name},'Zabbix',#{host_name},#{mon_endpoint},#{admin_user},#{admin_pass})")
	    int setMonZabbix(Monitor monitor);

	    @Select("SELECT mon_endpoint FROM isap_env_mon_mst WHERE mon_endpoint=#{monEndpoint}")
	    List<String> getMonEndpoint(String monEndpoint);

	    @Select("SELECT param_val FROM isap_env_vdc_ext e JOIN isap_env_vdc_master m ON m.vdc_id=e.vdc_id  WHERE m.delete_flag=0 AND param_name='controllerEndpoint' and param_val=#{controllerEndpoint} ")
	    List<String> getControllerEndpoint(String controllerEndpoint);

	    @Select("SELECT * FROM isap_env_vdc_master WHERE vdc_name=#{vdcName} AND delete_flag=0")
	    List<String> getVdcName(String vdcName);

	    @Select("SELECT * FROM isap_env_scm_mst WHERE scm_name=#{scmName}")
	    List<String> getScmName(String scmName);

	    @Select("SELECT * FROM isap_env_mon_mst WHERE mon_name=#{monName}")
	    List<String> getMonName(String monName);
        
	    @Select("SELECT scm_id from isap_env_scm_mst where scm_name=#{scmName}")
	    int getScmid(String scmName);

        @Insert("INSERT INTO isap_env_vdc_scm_map (vdc_id,scm_id,mon_id) VALUES (#{vdc_id},#{scm_id},#{mon_id})")
	    void setMap(Vdc openstackVdc);

        @Select("SELECT mon_id from isap_env_mon_mst where mon_name=#{monName}")
	    int getMonid(String monName);

        @Insert("INSERT INTO isap_env_vdc_ext (vdc_id,param_name,param_val) VALUES "
        		+ "(#{vdc_id},#{param_name},#{param_val})")
        void addOpenStackVdcExt(VdcExt vdc);

        @Select("SELECT vdc_name,vdc_location,created_at,vdc_provider,vdc_status,vdc_id FROM isap_env_vdc_master WHERE vdc_provider='Openstack'  AND delete_flag=0")
	    List<Vdc> getVdcList();
      //getting list AzureVdc deatils
        @Select("SELECT vdc_name,vdc_location,created_at,vdc_provider,vdc_status,vdc_id FROM isap_env_vdc_master WHERE vdc_provider='Azure' AND delete_flag=0")
	    List<Vdc> getAzureVdcList();

        @Select("SELECT * from isap_env_vdc_ext WHERE vdc_id=#{vdcId}")
	    List<VdcExt> getVdcExtDetailsById(int vdcId);

        @Select("SELECT vdc_name,vdc_location,vdc_status FROM isap_env_vdc_master WHERE vdc_id=#{vdcId}")
	    List<Vdc> getVdcEditList(Integer vdcId);

        @Update("UPDATE isap_env_vdc_ext SET param_val=#{param_val}  WHERE vdc_id=#{vdc_id} AND param_name=#{param_name}")
	    void updateOpenStackVdcExt(VdcExt vdc);

        @Select("SELECT param_val FROM isap_env_vdc_ext e JOIN isap_env_vdc_master m ON m.vdc_id=e.vdc_id  WHERE m.delete_flag=0 AND param_name='subnetAddress' and param_val=#{param_val}")
	    List<String> getSubnetAddress(String subnetAddress);

	    Vdc getVdcExt(int vdcId);
	   
}