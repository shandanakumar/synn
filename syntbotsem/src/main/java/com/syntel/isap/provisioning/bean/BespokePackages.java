package com.syntel.isap.provisioning.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author KK5007843
 *
 */

public class BespokePackages implements Serializable {

	/**
	 * for bespoke service
	 */
	private static final long serialVersionUID = 1L;

	private List<PackageAttributes> packageAttributes;

	private String package_name;
	
	private String package_version;
	

	public String getPackage_version() {
		return package_version;
	}

	public void setPackage_version(String package_version) {
		this.package_version = package_version;
	}

	public List<PackageAttributes> getPackageAttributes() {
		return packageAttributes;
	}

	public void setPackageAttributes(List<PackageAttributes> packageAttributes) {
		this.packageAttributes = packageAttributes;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

}
