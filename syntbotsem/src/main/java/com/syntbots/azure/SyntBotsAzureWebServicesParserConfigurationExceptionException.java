
/**
 * SyntBotsAzureWebServicesParserConfigurationExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

package com.syntbots.azure;

public class SyntBotsAzureWebServicesParserConfigurationExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1438940860869L;
    
    private com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesParserConfigurationException faultMessage;

    
        public SyntBotsAzureWebServicesParserConfigurationExceptionException() {
            super("SyntBotsAzureWebServicesParserConfigurationExceptionException");
        }

        public SyntBotsAzureWebServicesParserConfigurationExceptionException(java.lang.String s) {
           super(s);
        }

        public SyntBotsAzureWebServicesParserConfigurationExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SyntBotsAzureWebServicesParserConfigurationExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesParserConfigurationException msg){
       faultMessage = msg;
    }
    
    public com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesParserConfigurationException getFaultMessage(){
       return faultMessage;
    }
}
    