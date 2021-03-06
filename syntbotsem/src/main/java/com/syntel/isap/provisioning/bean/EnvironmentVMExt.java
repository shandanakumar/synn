package com.syntel.isap.provisioning.bean;

import java.io.Serializable;

public class EnvironmentVMExt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int vm_master_id;

	private String param_name;

	private String param_val;

	public int getVm_master_id() {
		return vm_master_id;
	}

	public void setVm_master_id(int vm_master_id) {
		this.vm_master_id = vm_master_id;
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

}
