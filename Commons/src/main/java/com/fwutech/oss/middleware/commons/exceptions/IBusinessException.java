package com.fwutech.oss.middleware.commons.exceptions;

import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;

public interface IBusinessException {
    IErrorCode getErrorCode();

    String getAdditionalInfo();

    RequestEvent getRequestEvent();

    Throwable getCause();

    boolean isEnableSuppression();

    boolean isWritableStackTrace();
}

