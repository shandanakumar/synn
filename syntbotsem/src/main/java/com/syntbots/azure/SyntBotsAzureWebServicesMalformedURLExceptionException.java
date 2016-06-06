
/**
 * SyntBotsAzureWebServicesMalformedURLExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

package com.syntbots.azure;

public class SyntBotsAzureWebServicesMalformedURLExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1438940861170L;
    
    private com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesMalformedURLException faultMessage;

    
        public SyntBotsAzureWebServicesMalformedURLExceptionException() {
            super("SyntBotsAzureWebServicesMalformedURLExceptionException");
        }

        public SyntBotsAzureWebServicesMalformedURLExceptionException(java.lang.String s) {
           super(s);
        }

        public SyntBotsAzureWebServicesMalformedURLExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SyntBotsAzureWebServicesMalformedURLExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesMalformedURLException msg){
       faultMessage = msg;
    }
    
    public com.syntbots.azure.SyntBotsAzureWebServicesStub.SyntBotsAzureWebServicesMalformedURLException getFaultMessage(){
       return faultMessage;
    }
}
    