package com.syntel.isap.provisioning.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author KK5007843
 *
 */

public class VmProvision implements Serializable {

	/**
	 * isap_env_vm_prov table
	 */
	private static final long serialVersionUID = 1L;

	private int vm_id;

	private int vm_master_id;

	private int user_id;

	private int proj_id;

	private int dpt_id;

	private int cg_id;

	private int req_id;

	private int vdc_id;

	private int firewall_master_id;

	private String vdc_provider;
	
	private String vm_name;
	
	private String vdc_name;
	
	private String bspoke_mst_vm_name;
	
	private String status;

	private String ip_addr;

	private String public_ip_addr;

	private String instance_id;

	private String startDate;

	private String endDate;

	private StackList stackList;

	private List<String> components;

	private int delete_flag;
	

	

	
	public String getBspoke_mst_vm_name() {
		return bspoke_mst_vm_name;
	}

	public void setBspoke_mst_vm_name(String bspoke_mst_vm_name) {
		this.bspoke_mst_vm_name = bspoke_mst_vm_name;
	}

	public String getVdc_provider() {
		return vdc_provider;
	}

	public void setVdc_provider(String vdc_provider) {
		this.vdc_provider = vdc_provider;
	}

	public String getVdc_name() {
		return vdc_name;
	}

	public void setVdc_name(String vdc_name) {
		this.vdc_name = vdc_name;
	}

	
	/*private String */

	public List<String> getComponents() {
		return components;
	}

	public void setComponents(List<String> components) {
		this.components = components;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	public StackList getStackList() {
		return stackList;
	}

	public void setStackList(StackList stackList) {
		this.stackList = stackList;
	}

	public String getStartDate() {
		return startDate;
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

	public String getIp_addr() {
		return ip_addr;
	}

	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getVm_master_id() {
		return vm_master_id;
	}

	public void setVm_master_id(int vm_master_id) {
		this.vm_master_id = vm_master_id;
	}

	public int getVm_id() {
		return vm_id;
	}

	public void setVm_id(int vm_id) {
		this.vm_id = vm_id;
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}

	public int getDpt_id() {
		return dpt_id;
	}

	public void setDpt_id(int dpt_id) {
		this.dpt_id = dpt_id;
	}

	public int getCg_id() {
		return cg_id;
	}

	public void setCg_id(int cg_id) {
		this.cg_id = cg_id;
	}

	public int getReq_id() {
		return req_id;
	}

	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}

	public int getVdc_id() {
		return vdc_id;
	}

	public void setVdc_id(int vdc_id) {
		this.vdc_id = vdc_id;
	}

	public int getFirewall_master_id() {
		return firewall_master_id;
	}

	public void setFirewall_master_id(int firewall_master_id) {
		this.firewall_master_id = firewall_master_id;
	}

	public String getVm_name() {
		return vm_name;
	}

	public void setVm_name(String vm_name) {
		this.vm_name = vm_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "VmProvision [vm_id=" + vm_id + ", vm_master_id=" + vm_master_id
				+ ", user_id=" + user_id + ", proj_id=" + proj_id + ", dpt_id="
				+ dpt_id + ", cg_id=" + cg_id + ", req_id=" + req_id
				+ ", vdc_id=" + vdc_id + ", firewall_master_id="
				+ firewall_master_id + ", vm_name=" + vm_name + ", vdc_name="
				+ vdc_name + ", status=" + status + ", ip_addr=" + ip_addr
				+ ", public_ip_addr=" + public_ip_addr + ", instance_id="
				+ instance_id + ", startDate=" + startDate + ", endDate="
				+ endDate + ", stackList=" + stackList + ", components="
				+ components + ", delete_flag=" + delete_flag
				+ ", getVdc_name()=" + getVdc_name() + ", getComponents()="
				+ getComponents() + ", getDelete_flag()=" + getDelete_flag()
				+ ", getStackList()=" + getStackList() + ", getStartDate()="
				+ getStartDate() + ", getEndDate()=" + getEndDate()
				+ ", getIp_addr()=" + getIp_addr() + ", getPublic_ip_addr()="
				+ getPublic_ip_addr() + ", getInstance_id()="
				+ getInstance_id() + ", getUser_id()=" + getUser_id()
				+ ", getVm_master_id()=" + getVm_master_id() + ", getVm_id()="
				+ getVm_id() + ", getProj_id()=" + getProj_id()
				+ ", getDpt_id()=" + getDpt_id() + ", getCg_id()=" + getCg_id()
				+ ", getReq_id()=" + getReq_id() + ", getVdc_id()="
				+ getVdc_id() + ", getFirewall_master_id()="
				+ getFirewall_master_id() + ", getVm_name()=" + getVm_name()
				+ ", getStatus()=" + getStatus() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
