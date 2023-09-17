package com.fwutech.oss.middleware.core.utils;

import com.fwutech.oss.middleware.commons.beans.base.Receipt;
import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.dtos.BaseDto;
import com.fwutech.oss.middleware.commons.exceptions.Error;
import com.fwutech.oss.middleware.commons.exceptions.IBusinessException;
import com.fwutech.oss.middleware.commons.responses.Result;
import com.fwutech.oss.middleware.commons.utils.DateUtil;

import java.util.Date;
import java.util.UUID;

public class ReceiptUtil {

    public static Result<Receipt> prepareSuccessResult(RequestEvent requestEvent) {
        Result<Receipt> result = new Result<>();
        result.setUuid(requestEvent.getUuid().toString());
        BaseDto<Receipt> receiptGeneralDto = new BaseDto<>(
                requestEvent.getAdapterType().getVendorType(),
                requestEvent.getAdapterType().getNetworkType(),
                requestEvent.getAdapterType().getProtocolType());
        receiptGeneralDto.setBeanObj(new Receipt(requestEvent.getUuid().toString()));
        result.setData(receiptGeneralDto);
        result.setTimestamp(DateUtil.dateToIso8601Basic(requestEvent.getTimestamp(), "UTC"));
        return result;
    }

    public static Result<Receipt> prepareErrorResult(IBusinessException businessException) {
        Result<Receipt> result = new Result<>();
        RequestEvent requestEvent = businessException.getRequestEvent();
        if (requestEvent == null) {
            requestEvent = new RequestEvent();
            requestEvent.setTimestamp(new Date());
            requestEvent.setUuid(UUID.randomUUID());
        }
        result.setUuid(businessException.getRequestEvent().getUuid().toString());
        result.setError(new Error(businessException, false));
        result.setTimestamp(DateUtil.dateToIso8601Basic(businessException.getRequestEvent().getTimestamp(), "UTC"));
        return result;
    }
}