package com.fwutech.oss.middleware.commons.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fwutech.oss.middleware.commons.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Copyright 2020 (C) fwutech.com
 *
 * @author : Esmaeil Sadeghi {@link "mailto:e.sadeghi@fwutech.com"}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class Error implements Serializable {
    private static final long serialVersionUID = -2671835353302010467L;

    private int code;
    private String message;
    private String detail;
    private String timestamp;

    public Error() {
        this.timestamp = DateUtil.dateToIso8601Basic(new Date(), "UTC");
    }

    public Error(IErrorCode err) {
        this();
        this.code = err.getCode();
        this.message = err.getMessage();
        this.detail = err.getDetail();
    }

    public Error(IBusinessException businessException, boolean writableStackTrace) {
        this(businessException.getErrorCode());
        this.message = businessException.getAdditionalInfo() != null &&
                businessException.getAdditionalInfo().length() > 0
                ? this.message + " (" + businessException.getAdditionalInfo() + ")"
                : this.message;
        this.detail = writableStackTrace
                ? Arrays.toString(businessException.getCause().getStackTrace())
                : businessException.getErrorCode().getDetail();
    }
}
