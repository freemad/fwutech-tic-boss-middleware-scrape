package com.fwutech.oss.middleware.commons.enums;

import com.fwutech.oss.middleware.commons.exceptions.IErrorCode;
import com.fwutech.oss.middleware.commons.globals.ResponseConstant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MsgBrokerErrorCode implements IErrorCode {

    // TODO: 11.11.20  ERROR_CODE_COMMON_START_INDEX to yml
    MESSAGE_CONVERSION_EXCEPTION(ResponseConstant.MSG_BROKER_ERROR_CODE_START_INDEX + 1,
            "MESSAGE_CONVERSION_EXCEPTION", "MESSAGE CONVERSION EXCEPTION"),
    PRODUCE_REQUEST_EXCEPTION(ResponseConstant.MSG_BROKER_ERROR_CODE_START_INDEX + 2,
            "PRODUCE_REQUEST_EXCEPTION", "PRODUCE REQUEST EXCEPTION"),
    CONSUME_REQUEST_EXCEPTION(ResponseConstant.MSG_BROKER_ERROR_CODE_START_INDEX + 3,
            "CONSUME_REQUEST_EXCEPTION", "CONSUME REQUEST EXCEPTION"),
    PRODUCE_RESPONSE_EXCEPTION(ResponseConstant.MSG_BROKER_ERROR_CODE_START_INDEX + 4,
            "PRODUCE_RESPONSE_EXCEPTION", "PRODUCE RESPONSE EXCEPTION"),
    CONSUME_RESPONSE_EXCEPTION(ResponseConstant.MSG_BROKER_ERROR_CODE_START_INDEX + 5,
            "CONSUME_RESPONSE_EXCEPTION", "CONSUME RESPONSE EXCEPTION"),
    CONSUME_MESSAGE_EXCEPTION(ResponseConstant.MSG_BROKER_ERROR_CODE_START_INDEX + 6,
            "CONSUME_MESSAGE_EXCEPTION", "CONSUME MESSAGE EXCEPTION");

    private int code;
    private String message;
    private String detail;

    MsgBrokerErrorCode(int code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public static List<MsgBrokerErrorCode> getCodesAsList() {
        return Stream.of(MsgBrokerErrorCode.values()).collect(Collectors.toList());
    }
}