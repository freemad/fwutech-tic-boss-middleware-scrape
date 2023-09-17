package com.fwutech.oss.middleware.commons.exceptions;

public interface IErrorCode {
    int getCode();

    String getMessage();

    String getDetail();
}
