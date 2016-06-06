
/**
 * SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

package com.syntbots.azure;

public class SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1438940861074L;
    
    private com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesNoSuchAlgorithmException faultMessage;

    
        public SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException() {
            super("SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException");
        }

        public SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException(java.lang.String s) {
           super(s);
        }

        public SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SyntBotsAzureWebServicesNoSuchAlgorithmExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesNoSuchAlgorithmException msg){
       faultMessage = msg;
    }
    
    public com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesNoSuchAlgorithmException getFaultMessage(){
       return faultMessage;
    }
}
    