package com.syntel.isap.provisioning.bean;

import java.io.Serializable;

/**
 * @author KK5007843
 *
 */

public class PackageAttributes implements Serializable {

	/**
	 * isap_env_scm_pkg_attr for bespoke service
	 */
	private static final long serialVersionUID = 1L;

	private String attr_name;

	private String default_val;

	private String req_val;

	public String getAttr_name() {
		return attr_name;
	}

	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}

	public String getDefault_val() {
		return default_val;
	}

	public void setDefault_val(String default_val) {
		this.default_val = default_val;
	}

	public String getReq_val() {
		return req_val;
	}

	public void setReq_val(String req_val) {
		this.req_val = req_val;
	}

}
