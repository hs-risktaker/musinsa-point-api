package com.musinsa.point.constant;

public enum ErrorCode {
    E0000("0000", "시스템 오류가 발생하였습니다."),


    E1001("1001", "포인트 타입이 올바르지 않습니다."),
    E1002("1002", "포인트 잔액 부족으로 인해 포인트 사용이 불가합니다."),
    E1003("1003", "해당 포인트 사용 이력이 존재하지 않습니다.");


    private String code;
    private String errMsg;

    ErrorCode(String code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public String getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
