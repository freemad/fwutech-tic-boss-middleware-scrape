package com.fwutech.oss.middleware.commons.exceptions;

import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.utils.UuidUtil;
import lombok.Getter;

import java.util.Date;

@Getter
public class BusinessException
        extends Exception
        implements IBusinessException {
    private static final long serialVersionUID = -9075958413755077206L;

    private IErrorCode errorCode;
    private String additionalInfo;
    private RequestEvent requestEvent;
    private Throwable cause;
    private boolean enableSuppression;
    private boolean writableStackTrace;

    public BusinessException(IErrorCode errorCode) {
        this(errorCode,
                "",
                RequestEvent.builder()
                        .timestamp(new Date())
                        .uuid(UuidUtil.generateUuid())
                        .build(),
                false, true);
    }

    public BusinessException(IErrorCode errorCode, Throwable cause) {
        this(errorCode,
                "",
                cause,
                RequestEvent.builder()
                        .timestamp(new Date())
                        .uuid(UuidUtil.generateUuid())
                        .build(),
                false, true);
    }

    public BusinessException(IErrorCode errorCode, String additionalInfo, Throwable cause) {
        this(errorCode,
                additionalInfo,
                cause,
                RequestEvent.builder()
                        .timestamp(new Date())
                        .uuid(UuidUtil.generateUuid())
                        .build(),
                false, true);
    }

    public BusinessException(IErrorCode errorCode, RequestEvent requestEvent) {
        this(errorCode, "", null, requestEvent, false, true);
    }

    public BusinessException(IErrorCode errorCode, Throwable cause, RequestEvent requestEvent) {
        this(errorCode, "", cause, requestEvent, false, true);
    }

    public BusinessException(IErrorCode errorCode, String additionalInfo, RequestEvent requestEvent) {
        this(errorCode, additionalInfo, null, requestEvent, false, true);
    }

    public BusinessException(IErrorCode errorCode, String additionalInfo, Throwable cause, RequestEvent requestEvent) {
        this(errorCode, additionalInfo, cause, requestEvent, false, true);
    }

    protected BusinessException(IErrorCode errorCode, String additionalInfo, RequestEvent requestEvent, boolean enableSuppression, boolean writableStackTrace) {
        this(errorCode, additionalInfo, null, requestEvent, enableSuppression, writableStackTrace);
    }

    protected BusinessException(IErrorCode errorCode, String additionalInfo, Throwable cause, RequestEvent requestEvent, boolean enableSuppression, boolean writableStackTrace) {
        super(errorCode.getMessage(), cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.additionalInfo = additionalInfo;
        this.requestEvent = requestEvent;
        this.cause = cause;
        this.enableSuppression = enableSuppression;
        this.writableStackTrace = writableStackTrace;
    }

    public void setRequestEvent(RequestEvent requestEvent) {
        this.requestEvent = requestEvent;
    }
}
