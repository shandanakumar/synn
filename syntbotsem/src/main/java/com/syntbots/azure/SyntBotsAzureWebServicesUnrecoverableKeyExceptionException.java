
/**
 * SyntBotsAzureWebServicesUnrecoverableKeyExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

package com.syntbots.azure;

public class SyntBotsAzureWebServicesUnrecoverableKeyExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1438940861063L;
    
    private com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesUnrecoverableKeyException faultMessage;

    
        public SyntBotsAzureWebServicesUnrecoverableKeyExceptionException() {
            super("SyntBotsAzureWebServicesUnrecoverableKeyExceptionException");
        }

        public SyntBotsAzureWebServicesUnrecoverableKeyExceptionException(java.lang.String s) {
           super(s);
        }

        public SyntBotsAzureWebServicesUnrecoverableKeyExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SyntBotsAzureWebServicesUnrecoverableKeyExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesUnrecoverableKeyException msg){
       faultMessage = msg;
    }
    
    public com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesUnrecoverableKeyException getFaultMessage(){
       return faultMessage;
    }
}
    