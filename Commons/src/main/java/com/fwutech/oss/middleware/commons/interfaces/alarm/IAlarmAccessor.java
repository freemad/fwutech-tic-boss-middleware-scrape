package com.fwutech.oss.middleware.commons.interfaces.alarm;

import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;

public interface IAlarmAccessor {

    default String getAllEmsAndNeActiveAlarms(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllActiveAlarms(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getActiveAlarmsCount(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getNeHistoryAlarms(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllEmsSystemActiveAlarms(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAlarmsCount(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getThresholdCrossingAlerts(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }
    default String syncActiveAlarmFromNe(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAlarmById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllEvents(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }
    default String getEventById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }
    default String getAllGroupAlarmSummary(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllAlarmDeviceGroups(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }
}
