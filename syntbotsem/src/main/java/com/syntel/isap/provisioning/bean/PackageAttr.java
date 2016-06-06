package com.syntel.isap.provisioning.bean;

import java.io.Serializable;

/**
 * @author KK5007843
 *
 */

public class PackageAttr implements Serializable {

	/**
	 * isap_env_scm_pkg_attr and isap_env_vm_prov_ext tables
	 */
	private static final long serialVersionUID = 1L;

	public PackageAttr(String param_name, String param_val, int vm_id) {
		super();
		this.param_name = param_name;
		this.param_val = param_val;
		this.vm_id = vm_id;
	}

	public PackageAttr() {
		super();
	}

	private String param_name;

	private String param_val;

	private int vm_master_id;

	private int vm_id;

	public int getVm_id() {
		return vm_id;
	}

	public void setVm_id(int vm_id) {
		this.vm_id = vm_id;
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	public String getParam_val() {
		return param_val;
	}

	public void setParam_val(String param_val) {
		this.param_val = param_val;
	}

	public int getVm_master_id() {
		return vm_master_id;
	}

	public void setVm_master_id(int vm_master_id) {
		this.vm_master_id = vm_master_id;
	}

}
