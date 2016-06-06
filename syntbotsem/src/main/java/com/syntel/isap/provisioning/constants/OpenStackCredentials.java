package com.syntel.isap.provisioning.constants;

/**
 * @author KK5007843
 *
 */
public interface OpenStackCredentials {
	String OPENSTACK_ZONE = "STG-CHN-AZ01";
	//String OPENSTACK_USERID = "admin";
	String OPENSTACK_USERID = "20";
	String OPENSTACK_IP = "192.168.175.153";
	String OPENSTACK_TYPE = "openstack";
	String OPENSTACK_USER_NAME = "kalyanraju_kucharlapati@syntelinc.com";
	String OPENSTACK_USER_PASSWORD = "synlab123$";
	String OPENSTACK_USER_TENANTNAME = "ISAP-STGMA";
	String OPENSTACK_ADMIN_USER_NAME = "admin";
	String OPENSTACK_ADMIN_PASSWORD = "synlab123$";
	String OPENSTACK_ADMIN_TENANTNAME = "admin";
	String OPENSTACK_CUSTOM_NETWORK = "network";
	String OPENSTACK_CUSTOM_FLAVOR = "flavor";
	String OPENSTACK_CUSTOM_KEYPAIR = "keypair";
	String OPENSTACK_CUSTOM_IMAGE = "image";
	String OPENSTACK_CUSTOM_SECURITY = "security";
	String OPENSTACK_CUSTOM_STATUS = "Requested";
	String OPENSTACK_POOL = "Public_Network";
	int OPENSTACK_BESPOKE = 0;
	int OPENSTACK_BESPOKE_ONE = 1;
	
	String OPENSTACK_KEYENDPOINT = "http://192.168.175.153:35357";
	//String OPENSTACK_INTERFACE = "admin";
	String OPENSTACK_INTERFACE = "public";
	String OPENSTACK_REGION = "RegionOne";
	//String OPENSTACK_REGION_ONE = "RegionOne";
	String AWS_USERNAME = "Dummy";
	int RANDOM=9999;
	int ISAP_ADMIN_ROLE_ID=1;
	int ISAP_CORPORATE_ADMIN_ROLE_ID=2;
	int ISAP_DEPARTMENT_ADMIN_ROLE_ID=3;
	int ISAP_PROJECT_ADMIN_ROLE_ID=4;
	int ISAP_ENDUSER_ROLE_ID=5;
	
}
