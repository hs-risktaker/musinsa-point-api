package com.musinsa.point.exception;

public class APIException extends RuntimeException{

    private final String errCode;

    public APIException(String errCode, String msg) {
        super(msg, null, false, false);
        this.errCode = errCode;
    }

    public String getErrCode(){
        return errCode;
    }
}
