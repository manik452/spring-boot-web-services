package com.mamarbari.testdemo;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

//@SoapFault(faultCode = FaultCode.CLIENT)
@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://mamarbari.com/testdemo}103")
public class CourseException extends RuntimeException {
    public CourseException(String message) {
        super(message);
    }
}
