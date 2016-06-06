package com.syntel.isap.provisioning.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author KK5007843
 *
 */

public class CustomVM implements Serializable {

	/**
	 * isap_env_vm_custom_prov table
	 */
	private static final long serialVersionUID = 1L;

	

	private int vm_custom_id;

	private int usr_id;

	private String vm_name;

	private String hostname;

	private String ip_addr;

	private String public_ip_addr;

	private int service_req_dts_id;

	private int service_req_id;

	private int isap_env_vdc_master_vdc_id;

	private String status;

	private List<CustomVMExt> customVMExts;

	private StackList stackList;

	private String startDate;

	private String endDate;

	private int delete_flag;

	private String instance_id;
	
	private int mem;
	
	private int cpu;
	
	private int hdd;
	
	private  String usr_name;
	
	private  String vdc_provider;
	
	private  int vdcId;
	
	
	public int getVdcId() {
		return vdcId;
	}

	public void setVdcId(int vdcId) {
		this.vdcId = vdcId;
	}


	
	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	private  String createdAt;
	
	private  String createdBy;
	


	public String getVdc_provider() {
		return vdc_provider;
	}

	public void setVdc_provider(String vdc_provider) {
		this.vdc_provider = vdc_provider;
	}

	public String getUsr_name() {
		return usr_name;
	}

	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}

	public int getMem() {
		return mem;
	}

	public void setMem(int mem) {
		this.mem = mem;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getHdd() {
		return hdd;
	}

	public void setHdd(int hdd) {
		this.hdd = hdd;
	}

	public String getPublic_ip_addr() {
		return public_ip_addr;
	}

	public void setPublic_ip_addr(String public_ip_addr) {
		this.public_ip_addr = public_ip_addr;
	}

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	public String getStartDate() {
		return startDate;
	}

	public int getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public StackList getStackList() {
		return stackList;
	}

	public void setStackList(StackList stackList) {
		this.stackList = stackList;
	}

	public List<CustomVMExt> getCustomVMExts() {
		return customVMExts;
	}

	public void setCustomVMExts(List<CustomVMExt> customVMExts) {
		this.customVMExts = customVMExts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getService_req_dts_id() {
		return service_req_dts_id;
	}

	public void setService_req_dts_id(int service_req_dts_id) {
		this.service_req_dts_id = service_req_dts_id;
	}

	public int getService_req_id() {
		return service_req_id;
	}

	public void setService_req_id(int service_req_id) {
		this.service_req_id = service_req_id;
	}

	public int getIsap_env_vdc_master_vdc_id() {
		return isap_env_vdc_master_vdc_id;
	}

	public void setIsap_env_vdc_master_vdc_id(int isap_env_vdc_master_vdc_id) {
		this.isap_env_vdc_master_vdc_id = isap_env_vdc_master_vdc_id;
	}

	public int getVm_custom_id() {
		return vm_custom_id;
	}

	public void setVm_custom_id(int vm_custom_id) {
		this.vm_custom_id = vm_custom_id;
	}

	public String getVm_name() {
		return vm_name;
	}

	public void setVm_name(String vm_name) {
		this.vm_name = vm_name;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIp_addr() {
		return ip_addr;
	}

	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}

	@Override
	public String toString() {
		return "CustomVM [vm_custom_id=" + vm_custom_id + ", usr_id=" + usr_id
				+ ", vm_name=" + vm_name + ", hostname=" + hostname
				+ ", ip_addr=" + ip_addr + ", public_ip_addr=" + public_ip_addr
				+ ", service_req_dts_id=" + service_req_dts_id
				+ ", service_req_id=" + service_req_id
				+ ", isap_env_vdc_master_vdc_id=" + isap_env_vdc_master_vdc_id
				+ ", status=" + status + ", customVMExts=" + customVMExts
				+ ", stackList=" + stackList + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", delete_flag=" + delete_flag
				+ ", instance_id=" + instance_id + ", mem=" + mem + ", cpu="
				+ cpu + ", hdd=" + hdd + ", usr_name=" + usr_name
				+ ", vdc_provider=" + vdc_provider + ", vdcId=" + vdcId
				+ ", createdAt=" + createdAt + ", createdBy=" + createdBy + "]";
	}
}
