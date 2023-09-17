package com.fwutech.oss.middleware.commons.interfaces.alarm;

import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;

public interface IAlarmMutator {

    default String acknowledgeAlarms(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }
}
