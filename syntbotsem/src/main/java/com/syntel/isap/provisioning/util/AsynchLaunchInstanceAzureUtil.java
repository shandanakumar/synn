package com.syntel.isap.provisioning.util;

import java.rmi.RemoteException;

import com.syntbots.azure.SyntBotsAzureWebServicesIOExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesKeyManagementExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesKeyStoreExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesMalformedURLExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException;
import com.syntbots.azure.SyntBotsAzureWebServicesStub.CreateVMEP;
import com.syntbots.azure.SyntBotsAzureWebServicesUnrecoverableKeyExceptionException;
import com.syntel.isap.provisioning.soap.AzureServicesA2;

public class AsynchLaunchInstanceAzureUtil extends Thread{

	// should not change values for these below fields
			private String roleSize; // = "Standard_D1";
			private String serviceName; //= "syntbots-chn";
			private String configSetType; //= "WindowsProvisioningConfiguration";

			// can give random values for these below fields
			private String adminPassword; //= "Syntel123$";
			private String adminUser;// = "Syntel123";
			private String deploymentLabel; //= "label";
			private String deploymentName;// = "deploymentSyntbots";
			private String hostname;// = "syncgdc";
			private String imageName;// = "windows";
			private String roleName; //= "syntbots-vmTest";
			
			AzureServicesA2 azureClient = new AzureServicesA2();
			  
			public AsynchLaunchInstanceAzureUtil(CreateVMEP cVmep){
				super();
				this.roleSize = cVmep.getRoleSize();
				this.serviceName = cVmep.getServiceName();
				this.configSetType = cVmep.getConfigSetType();
				this.adminPassword = cVmep.getAdminPassword();
				this.adminUser = cVmep.getAdminUser();
				this.deploymentLabel = cVmep.getDeploymentLabel();
				this.deploymentName = cVmep.getDeploymentName();
				this.hostname = cVmep.getHostname();
				this.imageName = cVmep.getImageName();
				this.roleName = cVmep.getRoleName();
			}
			
			public void run(){  
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
					try {
						azureClient.launchAzureVM(cVmep);
					} catch (SyntBotsAzureWebServicesUnrecoverableKeyExceptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SyntBotsAzureWebServicesIOExceptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SyntBotsAzureWebServicesKeyManagementExceptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SyntBotsAzureWebServicesMalformedURLExceptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SyntBotsAzureWebServicesKeyStoreExceptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				
				
				}
}
