package com.syntel.isap.provisioning.bean;

import java.io.Serializable;

/**
 * @author KK5007843
 *
 */

public class VmProvisionPackages implements Serializable {

	/**
	 * isap_env_vm_prov_pkgs table
	 */
	private static final long serialVersionUID = 1L;

	private int vm_id;

	private int pkg_id;

	private int required;

	private String param_name;

	private String param_value;

	public int getVm_id() {
		return vm_id;
	}

	public void setVm_id(int vm_id) {
		this.vm_id = vm_id;
	}

	public int getPkg_id() {
		return pkg_id;
	}

	public void setPkg_id(int pkg_id) {
		this.pkg_id = pkg_id;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	public String getParam_value() {
		return param_value;
	}

	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}

}
