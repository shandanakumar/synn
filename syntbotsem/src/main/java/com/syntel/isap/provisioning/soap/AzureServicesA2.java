package com.syntel.isap.provisioning.soap;

import java.rmi.RemoteException;
import java.util.StringTokenizer;

import org.apache.axis2.AxisFault;

import com.syntbots.azure.SyntBotsAzureWebServicesIOExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesKeyManagementExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesKeyStoreExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesMalformedURLExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesStub;
import com.syntbots.azure.SyntBotsAzureWebServicesStub.CreateVMBSpoke;
import com.syntbots.azure.SyntBotsAzureWebServicesStub.CreateVMEP;
import com.syntbots.azure.SyntBotsAzureWebServicesStub.ShutDownRole;
import com.syntbots.azure.SyntBotsAzureWebServicesStub.UpdateDBRoleDetails;
import com.syntbots.azure.SyntBotsAzureWebServicesStub.UpdateDBRoleDetailsBespoke;
import com.syntbots.azure.SyntBotsAzureWebServicesUnrecoverableKeyExceptionException;
import com.syntel.isap.provisioning.constants.AzureConstants;

public class AzureServicesA2 {

	

	public void launchAzureVM(CreateVMEP vmDetails) throws RemoteException,
			SyntBotsAzureWebServicesUnrecoverableKeyExceptionException,
			SyntBotsAzureWebServicesIOExceptionException,
			SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException,
			SyntBotsAzureWebServicesKeyManagementExceptionException,
			SyntBotsAzureWebServicesMalformedURLExceptionException,
			SyntBotsAzureWebServicesKeyStoreExceptionException {
		
		SyntBotsAzureWebServicesStub syntBotsAzureWebServicesStub = new SyntBotsAzureWebServicesStub();
		syntBotsAzureWebServicesStub.createVMEP(vmDetails);
	}
	
	public void launchBespokeAzureVM(CreateVMBSpoke createVMBSpoke) throws RemoteException,
		SyntBotsAzureWebServicesUnrecoverableKeyExceptionException,
		SyntBotsAzureWebServicesIOExceptionException,
		SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException,
		SyntBotsAzureWebServicesKeyManagementExceptionException,
		SyntBotsAzureWebServicesMalformedURLExceptionException,
		SyntBotsAzureWebServicesKeyStoreExceptionException {
	
		SyntBotsAzureWebServicesStub syntBotsAzureWebServicesStub = new SyntBotsAzureWebServicesStub();
		syntBotsAzureWebServicesStub.createVMBSpoke(createVMBSpoke);
	}
	
	
   public void azureCstmLaunchDbupdate(String customVmnames) throws RemoteException {

		SyntBotsAzureWebServicesStub syntBotsAzureWebServicesStub = new SyntBotsAzureWebServicesStub();

		StringTokenizer strVmnames = new StringTokenizer(customVmnames, ",");
		if (strVmnames != null) {
			while (strVmnames.hasMoreTokens()) {
				String vmname = strVmnames.nextToken();
				String serviceName = vmname;
				String deploymentName = AzureConstants.AZURE_AZURE_DEPLOYMENT_NAME_FOR_DB_UPDATE;
				UpdateDBRoleDetails updateDBRoleDetails = new UpdateDBRoleDetails();
				updateDBRoleDetails.setServiceName(serviceName);
				updateDBRoleDetails.setDeploymentSlot(deploymentName);
				try {
					syntBotsAzureWebServicesStub
							.updateDBRoleDetails(updateDBRoleDetails);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
   
	public void azureCstmVmShutDown(String shtdnCustVmName) throws RemoteException, SyntBotsAzureWebServicesUnrecoverableKeyExceptionException, SyntBotsAzureWebServicesIOExceptionException, SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException, SyntBotsAzureWebServicesKeyManagementExceptionException, SyntBotsAzureWebServicesMalformedURLExceptionException, SyntBotsAzureWebServicesKeyStoreExceptionException {
		
		SyntBotsAzureWebServicesStub syntBotsAzureWebServicesStub = new SyntBotsAzureWebServicesStub();
		if (shtdnCustVmName != null && shtdnCustVmName != "") {
		ShutDownRole shutDownRole=new ShutDownRole();
		shutDownRole.setDeploymentName(AzureConstants.AZURE_AZURE_DEPLOYMENT_NAME);
		shutDownRole.setRoleName(shtdnCustVmName);
		shutDownRole.setServiceName(shtdnCustVmName);
		syntBotsAzureWebServicesStub.shutDownRole(shutDownRole);
		}
	}


   
   public void azureBespkLaunchDbupdate(String bespokeVmNames) throws RemoteException {

		SyntBotsAzureWebServicesStub syntBotsAzureWebServicesStub = new SyntBotsAzureWebServicesStub();

		StringTokenizer strVmnames = new StringTokenizer(bespokeVmNames, ",");
		if (strVmnames != null) {
			while (strVmnames.hasMoreTokens()) {
				String vmname = strVmnames.nextToken();
				String serviceName = vmname;
				String deploymentName = AzureConstants.AZURE_AZURE_DEPLOYMENT_NAME_FOR_DB_UPDATE;
				UpdateDBRoleDetailsBespoke updateDBRoleDetailsBespoke = new UpdateDBRoleDetailsBespoke();
				updateDBRoleDetailsBespoke.setServiceName(serviceName);
				updateDBRoleDetailsBespoke.setDeploymentSlot(deploymentName);
				try {
					syntBotsAzureWebServicesStub
							.updateDBRoleDetailsBespoke(updateDBRoleDetailsBespoke);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
   

	public static void main(String[] args) throws Exception  {

		// should not change values for these below fields
		String roleSize = "Standard_D1";
	//	String serviceName = "syntbots-chn";
		String serviceName = "";
		String configSetType = "WindowsProvisioningConfiguration";

		// can give random values for these below fields
		String adminPassword = "Syntel123$";
		String adminUser = "Syntel123";
		String deploymentLabel = "label";
		//String deploymentName = "deploymentSyntbots3";
		String deploymentName = "deploymentSyntbots2";
		
		String hostname = "syncgdc";
		String imageName = "windows";
		String roleName = "syntbots-vmTest-test-aug07";
		
		AzureServicesA2 azureClient = new AzureServicesA2();
		CreateVMEP cVmep = new CreateVMEP();

		cVmep.setAdminPassword(adminPassword);
		cVmep.setAdminUser(adminUser);
		cVmep.setConfigSetType(configSetType);
		cVmep.setDeploymentLabel(deploymentLabel);
		cVmep.setDeploymentName(deploymentName);
		cVmep.setHostname(hostname);
		cVmep.setImageName(imageName);
		cVmep.setRoleName(roleName);
		cVmep.setRoleSize(roleSize);
		cVmep.setServiceName(serviceName);
		azureClient.launchAzureVM(cVmep);
	}


	

	


}
