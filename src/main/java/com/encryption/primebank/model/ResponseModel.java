package com.encryption.primebank.model;

import java.util.List;

public class ResponseModel {

    private String returnCode;
    private String responseMessage;
    private List<String> errors;
    private String access_token;
    private String id_token;
    private String expires_i;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public String getExpires_i() {
        return expires_i;
    }

    public void setExpires_i(String expires_i) {
        this.expires_i = expires_i;
    }
}
